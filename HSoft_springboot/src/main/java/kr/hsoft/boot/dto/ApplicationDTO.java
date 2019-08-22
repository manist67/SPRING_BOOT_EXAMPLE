package kr.hsoft.boot.dto;

import kr.hsoft.boot.domain.ProposalDomain;
import kr.hsoft.boot.domain.UserDomain;

public class ApplicationDTO {
	private int seq;
	private UserDomain user;
	private ProposalDomain proposal;
	private String contents;
	private int childrenCount;
	private int create;
	private int modify;
	private int enable;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public UserDomain getUser() {
		return user;
	}
	public void setUser(UserDomain user) {
		this.user = user;
	}
	public ProposalDomain getProposal() {
		return proposal;
	}
	public void setProposal(ProposalDomain proposal) {
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
	public int getCreate() {
		return create;
	}
	public void setCreate(int create) {
		this.create = create;
	}
	public int getModify() {
		return modify;
	}
	public void setModify(int modify) {
		this.modify = modify;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
}
