package second.common.util;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;


public class UploadObject {
	@Value("#{config['burket']}")
	private String BURKETNAME;
	@Value("#{config['access_key']}")
	private String ACCESS_KEY;
	@Value("#{config['secret_key']}")
	private String SECRET_KEY;
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
