package kr.hsoft.boot.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.lang.Nullable;

public class ApplicationWriteDomain {
	@Nullable
	private int user;
	@Nullable
	private int proposal;
	@NotNull
	private String contents;
	@Positive
	@NotNull
	private int childrenCount;
	
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getProposal() {
		return proposal;
	}
	public void setProposal(int proposal) {
		this.proposal = proposal;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getChildrenCount() {
		return childrenCount;
	}
	public void setChildrenCount(int childrenCount) {
		this.childrenCount = childrenCount;
	}
}
