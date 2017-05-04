package second.account.dto;

public class AccountDTO {

	private String account_date;    //날짜
	private String use_detail;    //사용내역
	private Integer cash;         //현금
	private Integer card;         //카드
	private String classification;  //분류
	private String memo;           //메모
		
	
	public String getAccount_date() {
		return account_date;
	}
	public void setAccount_date(String account_date) {
		this.account_date = account_date;
	}
	public String getUse_detail() {
		return use_detail;
	}
	public void setUse_detail(String use_detail) {
		this.use_detail = use_detail;
	}
	public Integer getCash() {
		return cash;
	}
	public void setCash(Integer cash) {
		this.cash = cash;
	}
	public Integer getCard() {
		return card;
	}
	public void setCard(Integer card) {
		this.card = card;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
