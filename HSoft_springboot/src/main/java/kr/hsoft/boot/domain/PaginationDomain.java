package kr.hsoft.boot.domain;

public class PaginationDomain {
	private int page = 0;
	private int unit = 15;
	private int total;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public int getOffset() {
		return page * unit;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
