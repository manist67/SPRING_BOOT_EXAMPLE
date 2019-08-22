package kr.hsoft.boot.domain;

import java.sql.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonComponent
public class ProposalWriteDomain { // ? write!
	@NotNull
	private String title;
	
	private UserDomain user; // ?? input 
	@NotNull
	private int category; // ? integer => category table 
							 // if get => string
							 // if post => integer
	@NotNull
	private String address1;
	@Nullable
	private String address2;
	@NotNull
	private String targetGender; // only "F", "M", "A"
	
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date date;
	
	@NotNull
	@PositiveOrZero
	private int minAge;
	@NotNull
	@PositiveOrZero
	private int maxAge;
	
	@NotNull
	@PositiveOrZero
	private int fee;

	@NotNull
	@PositiveOrZero
	private int minParticipants;
	
	@NotNull
	@PositiveOrZero
	private int maxParticipants;
	
	@Nullable
	private String requirements;
	@NotNull
	private String contents; /* html type in here */
	
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
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
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
}
