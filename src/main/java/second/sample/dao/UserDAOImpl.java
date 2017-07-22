package second.sample.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import second.sample.dto.LoginDTO;
import second.sample.user.UserVO;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return sqlSession.selectOne("member.login", dto);
	}

	@Override
	public void join(UserVO vo) throws Exception {
		sqlSession.insert("member.join", vo);
	}

	@Override
	public UserVO idCheck(String id) throws Exception {
		return sqlSession.selectOne("member.idCheck", id);
	}

}
