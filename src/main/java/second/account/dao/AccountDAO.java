package second.account.dao;

import second.account.dto.AccountDTO;

public interface AccountDAO {

	void accountSave(AccountDTO dto) throws Exception;

}
