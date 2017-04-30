package second.sample.service;

import second.sample.dto.LoginDTO;
import second.sample.user.UserVO;


public interface UserService {
	
	public UserVO login(LoginDTO dto) throws Exception;

	public void join(UserVO vo) throws Exception;
	
}
