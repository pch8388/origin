package second.sample.service;

import java.security.PrivateKey;

import second.sample.dto.LoginDTO;
import second.sample.user.UserVO;


public interface UserService {
	
	public UserVO login(LoginDTO dto) throws Exception;

	public void join(UserVO vo) throws Exception;

	public UserVO idCheck(String id) throws Exception;

	public String decryptRsa(PrivateKey privateKey, String securedValue);
	
}
