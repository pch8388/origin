package second.sample.dao;

import second.sample.dto.LoginDTO;
import second.sample.user.UserVO;

public interface UserDAO {
	
	public UserVO login(LoginDTO dto) throws Exception;

	public void join(UserVO vo) throws Exception;
	
}
