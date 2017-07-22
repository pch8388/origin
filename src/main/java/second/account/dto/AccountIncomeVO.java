package second.account.dto;

public class AccountIncomeVO {
	
	private String salary;
	private String income;
	private String id;
	private String income_date;
	private int idx;
	private int TOTAL_COUNT;
	
	
	public int getTOTAL_COUNT() {
		return TOTAL_COUNT;
	}
	public void setTOTAL_COUNT(int tOTAL_COUNT) {
		TOTAL_COUNT = tOTAL_COUNT;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getIncome_date() {
		return income_date;
	}
	public void setIncome_date(String income_date) {
		this.income_date = income_date;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "[salary = "+salary+" ,income = "+income+", id = "+id+", income_date = "+income_date+"]";
	}
}
