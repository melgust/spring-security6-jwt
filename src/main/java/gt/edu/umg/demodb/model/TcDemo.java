package gt.edu.umg.demodb.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tc_demo")
public class TcDemo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long demoId;

	@NotNull
	private String demoDesc;

	@NotNull
	private byte statusId = 1;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt = new Date();

	private long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	private long updatedBy;

	public long getDemoId() {
		return demoId;
	}

	public void setDemoId(long demoId) {
		this.demoId = demoId;
	}

	public String getDemoDesc() {
		return demoDesc;
	}

	public void setDemoDesc(String demoDesc) {
		this.demoDesc = demoDesc;
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

}
