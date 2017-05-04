package second.account.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import second.account.dto.AccountDTO;

@Repository("accountDAO")
public class AccountDAOImpl implements AccountDAO {

	@Autowired
	SqlSessionTemplate sqlSession;

	@Override
	public void accountSave(AccountDTO dto) throws Exception {
		sqlSession.insert("account.accountSave", dto);
	}
}
