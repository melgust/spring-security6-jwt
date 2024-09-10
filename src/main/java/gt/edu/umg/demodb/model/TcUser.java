package gt.edu.umg.demodb.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "tc_user", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }),
		@UniqueConstraint(columnNames = { "email" }), @UniqueConstraint(columnNames = { "identification_document_id" , "documentNumber" }) })
public class TcUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;

	private String fullname;

	private String username;

	private String email;

	private long documentNumber;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	@ManyToOne
	@JoinColumn(name = "identification_document_id")
	private TcIdentificationDocument tcIdentificationDocument;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private TcRole tcRole;

	private String password;

	private byte statusId = 1;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt = new Date();

	private long createdBy = 0;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	private long updatedBy = 0;

	private String retypePassword;

	private String photo;

	private String phone;

	public TcUser() {
	}

	public TcUser(long userId) {
		this.userId = userId;
	}

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
