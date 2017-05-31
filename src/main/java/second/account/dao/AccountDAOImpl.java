package second.account.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import second.account.dto.AccountDTO;
import second.account.dto.AccountIncomeVO;

@Repository("accountDAO")
public class AccountDAOImpl implements AccountDAO {

	@Autowired
	SqlSessionTemplate sqlSession;

	@Override
	public void accountSave(AccountDTO dto) throws Exception {
		sqlSession.insert("account.accountSave", dto);
	}

	@Override
	public List<AccountDTO> accountList(Map<String,Object> map) throws Exception {
		return sqlSession.selectList("account.accountList", map);
	}

	@Override
	public List<AccountDTO> accountListSum(AccountDTO dto) throws Exception {
		return sqlSession.selectList("account.accountListSum", dto);
	}

	@Override
	public void accountIncomeSave(AccountIncomeVO vo) throws Exception {
		sqlSession.insert("account.accountIncomeSave", vo);
	}

	@Override
	public List<AccountIncomeVO> accountIncomeList(Map<String,Object> map) throws Exception {
		return sqlSession.selectList("account.accountIncomeList", map);
	}

	@Override
	public String monthIncome(AccountIncomeVO vo) throws Exception {
		return sqlSession.selectOne("account.monthIncome", vo);
	}

	@Override
	public AccountDTO monthSpend(AccountDTO dto) throws Exception {
		return sqlSession.selectOne("account.monthSpend", dto);
	}

	@Override
	public void accountDelete(String checkBoxValues) throws Exception {
		sqlSession.delete("account.accountDelete", checkBoxValues);
	}

	@Override
	public void incomeDelete(String checkBoxValues) throws Exception {
		sqlSession.delete("account.incomeDelete",checkBoxValues);
	}
}
