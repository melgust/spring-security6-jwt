package gt.edu.umg.demodb.dto;

import java.util.Date;

import gt.edu.umg.demodb.model.TcIdentificationDocument;
import gt.edu.umg.demodb.model.TcRole;

public class TcUserDto {

	private long userId;
	private String fullname;
	private String username;
	private String email;
	private long documentNumber;
	private Date birthday;
	private TcIdentificationDocument tcIdentificationDocument;
	private TcRole tcRole;
	private String password;
	private byte statusId = 1;
	private Date createdAt;
	private long createdBy = 0;
	private Date updatedAt;
	private long updatedBy = 0;
	private String retypePassword;
	private String photo;
	private String phone;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(long documentNumber) {
		this.documentNumber = documentNumber;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public TcIdentificationDocument getTcIdentificationDocument() {
		return tcIdentificationDocument;
	}

	public void setTcIdentificationDocument(TcIdentificationDocument tcIdentificationDocument) {
		this.tcIdentificationDocument = tcIdentificationDocument;
	}

	public TcRole getTcRole() {
		return tcRole;
	}

	public void setTcRole(TcRole tcRole) {
		this.tcRole = tcRole;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte getStatusId() {
		return statusId;
	}

	public void setStatusId(byte statusId) {
		this.statusId = statusId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getRetypePassword() {
		return retypePassword;
	}

	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
