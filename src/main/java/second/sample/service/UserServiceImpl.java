package second.sample.service;

import java.security.PrivateKey;

import javax.annotation.Resource;
import javax.crypto.Cipher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import second.sample.dao.UserDAO;
import second.sample.dto.LoginDTO;
import second.sample.user.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	
	@Resource(name="userDAO")
	private UserDAO dao;
	
	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return dao.login(dto);
	}

	@Override
	public void join(UserVO vo) throws Exception {
		dao.join(vo);
	}

	@Override
	public UserVO idCheck(String id) throws Exception {
		return dao.idCheck(id);
	}

	@Override
	public String decryptRsa(PrivateKey privateKey, String securedValue) {
		String decryptedValue = "";
		try{
			Cipher cipher = Cipher.getInstance("RSA");
			/*
			 * 암호화 된 값은 byte 배열
			 * 이를 문자열 폼으로 전송하기 위해 16진 문자열(hex)로 변경
			 * 서버측에서도 값을 받을 때 hex 문자열을 받아서 이를 다시 byte 배열로 바꾼 뒤에 복호화 과정 수행
			 */
			byte[] encrytedBytes = hexToByteArray(securedValue);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] decryptedBytes = cipher.doFinal(encrytedBytes);
			decryptedValue = new String(decryptedBytes,"utf-8");
		}catch(Exception e){
			log.info("decryptRsa Exception : " + e.getMessage());
		}
		return decryptedValue;
	}
	
	// hex string to byte[]
	public static byte[] hexToByteArray(String hex){
		if(hex == null || hex.length() == 0){
			return null;
		}
		byte[] ba = new byte[hex.length() / 2];
		for(int i=0; i<ba.length; i++){
			ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2),16);
		}
		return ba;
	}
	
	//byte[] to hex string
	public static String byteArrayToHex(byte[] ba){
		if(ba == null || ba.length == 0){
			return null;
		}
		StringBuffer sb = new StringBuffer(ba.length * 2);
		String hexNumber;
		for(int x = 0; x<ba.length; x++){
			hexNumber = "0" + Integer.toHexString(0xff & ba[x]);
			
			sb.append(hexNumber.substring(hexNumber.length() - 2));
		}
		return sb.toString();
	}
}
