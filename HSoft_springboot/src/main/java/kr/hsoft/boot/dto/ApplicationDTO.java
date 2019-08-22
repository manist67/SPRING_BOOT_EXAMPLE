package kr.hsoft.boot.dto;

import java.sql.Timestamp;


public class ApplicationDTO {
	private int seq;
	private int user;
	private int proposal;
	private String contents;
	private int childrenCount;
	private Timestamp create;
	private Timestamp modify;
	private int enable;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
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
	public Timestamp getCreate() {
		return create;
	}
	public void setCreate(Timestamp create) {
		this.create = create;
	}
	public Timestamp getModify() {
		return modify;
	}
	public void setModify(Timestamp modify) {
		this.modify = modify;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
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
}
