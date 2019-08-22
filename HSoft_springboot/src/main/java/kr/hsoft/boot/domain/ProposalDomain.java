package kr.hsoft.boot.domain;

import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class ProposalDomain {
	private int seq;
	private String title;
	private UserDomain user;
	private String category;
	private String address1;
	private String address2;
	private String targetGender;
	private String date;
	private int minAge;
	private int maxAge;
	private int fee;
	private int minParticipants;
	private int maxParticipants;
	private String requirements;
	private String contents; /* html type in here */
	private String create;
	private String modify;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTargetGender() {
		return targetGender;
	}
	public void setTargetGender(String targetGender) {
		this.targetGender = targetGender;
	}
	public int getMinAge() {
		return minAge;
	}
	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}
	public int getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public UserDomain getUser() {
		return user;
	}
	public void setUser(UserDomain user) {
		this.user = user;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public int getMinParticipants() {
		return minParticipants;
	}
	public void setMinParticipants(int minParticipants) {
		this.minParticipants = minParticipants;
	}
	public int getMaxParticipants() {
		return maxParticipants;
	}
	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
	}
	public String getRequirements() {
		return requirements;
	}
	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getCreate() {
		return create;
	}
	public void setCreate(String create) {
		this.create = create;
	}
	public String getModify() {
		return modify;
	}
	public void setModify(String modify) {
		this.modify = modify;
	}
}
