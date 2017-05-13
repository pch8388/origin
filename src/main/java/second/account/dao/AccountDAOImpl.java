package second.account.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import second.account.dto.AccountDTO;
import second.account.dto.AccountIncomeVO;
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

	@Override
	public void accountIncomeSave(AccountIncomeVO vo) throws Exception {
		sqlSession.insert("account.accountIncomeSave", vo);
	}

	@Override
	public List<AccountIncomeVO> accountIncomeList(AccountIncomeVO vo) throws Exception {
		return sqlSession.selectList("account.accountIncomeList", vo);
	}

	@Override
	public String monthIncome(AccountIncomeVO vo) throws Exception {
		return sqlSession.selectOne("account.monthIncome", vo);
	}

	@Override
	public AccountDTO monthSpend(AccountDTO dto) throws Exception {
		return sqlSession.selectOne("account.monthSpend", dto);
	}
}
