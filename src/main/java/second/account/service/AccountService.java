package second.account.service;

import java.util.List;

import second.account.dto.AccountDTO;
import second.sample.user.UserVO;

public interface AccountService {

	public void accountSave(AccountDTO dto) throws Exception;

	public List<AccountDTO> accountList(UserVO vo)throws Exception;

}
