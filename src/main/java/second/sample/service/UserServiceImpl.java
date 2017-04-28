package second.sample.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import second.sample.dao.UserDAO;
import second.sample.dto.LoginDTO;
import second.sample.member.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name="userDAO")
	private UserDAO dao;
	
	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return dao.login(dto);
	}

}