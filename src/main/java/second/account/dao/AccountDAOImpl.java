package second.account.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import second.account.dto.AccountDTO;
import second.sample.user.UserVO;

@Repository("accountDAO")
public class AccountDAOImpl implements AccountDAO {

	@Autowired
	SqlSessionTemplate sqlSession;

	@Override
	public void accountSave(AccountDTO dto) throws Exception {
		sqlSession.insert("account.accountSave", dto);
	}

	@Override
	public List<AccountDTO> accountList(UserVO vo) throws Exception {
		return sqlSession.selectList("account.accountList", vo);
	}

	@Override
	public List<AccountDTO> accountListSum(UserVO vo) throws Exception {
		return sqlSession.selectList("account.accountListSum", vo);
	}
}
