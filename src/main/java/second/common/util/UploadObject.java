package second.common.util;

import java.io.InputStream;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;


public class UploadObject {
	private static final String BURKETNAME = "pch8388";
	private static final String ACCESS_KEY = "AKIAIWASEEEIZHDIZ2EA";
	private static final String SECRET_KEY = "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";
	private AmazonS3 amazonS3;
	
	@SuppressWarnings("deprecation")
	public UploadObject(){
		AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY,SECRET_KEY);
		amazonS3 = new AmazonS3Client(awsCredentials);
	}
	
	public void upload(String keyName, InputStream inputStream,ObjectMetadata metadata){
		if(amazonS3!=null)
		try{
			amazonS3.putObject(new PutObjectRequest(BURKETNAME,keyName,inputStream,metadata));
		}catch(AmazonClientException ace){
			ace.printStackTrace();
		} finally {
			amazonS3 = null;
		}
	}
}
