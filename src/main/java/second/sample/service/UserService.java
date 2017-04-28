package second.sample.service;

import second.sample.dto.LoginDTO;
import second.sample.member.UserVO;


public interface UserService {
	
	public UserVO login(LoginDTO dto) throws Exception;
	
}
