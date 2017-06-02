package second.common.controller;

import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import second.common.common.CommandMap;
import second.common.service.CommonService;

@Controller
public class CommonController {
	private static final String BURKETNAME = "pch8388";
	private static final String ACCESS_KEY = "AKIAIWASEEEIZHDIZ2EA";
	private static final String SECRET_KEY = "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";
	private AmazonS3 amazonS3;
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="commonService")
	private CommonService commonService;
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/common/downloadFile")
	public ResponseEntity<byte[]> downLoadFile(CommandMap commandMap,HttpServletResponse response) throws Exception{
		Map<String,Object> map = commonService.selectFileInfo(commandMap.getMap());
		String storedFileName = (String)map.get("STORED_FILE_NAME");
		String originalFileName = (String)map.get("ORIGINAL_FILE_NAME");
		
		
		
		AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY,SECRET_KEY);
		amazonS3 = new AmazonS3Client(awsCredentials);
		S3Object s3object = amazonS3.getObject(new GetObjectRequest(BURKETNAME,storedFileName));
		
		S3ObjectInputStream objectInputStream = s3object.getObjectContent();
		byte[] bytes = IOUtils.toByteArray(objectInputStream);
		String fileName= URLEncoder.encode(originalFileName,"UTF-8").replaceAll("\\+", "%20");
		
		HttpHeaders httpHeaders = new HttpHeaders();
		
		httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		httpHeaders.setContentLength(bytes.length);
		httpHeaders.setContentDispositionFormData("attachment", fileName);
		
		return new ResponseEntity<>(bytes,httpHeaders,HttpStatus.OK);
	}
}
