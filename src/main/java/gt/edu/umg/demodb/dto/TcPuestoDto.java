package gt.edu.umg.demodb.dto;

import java.util.Date;

public class TcPuestoDto {

	private long puestoId;

	private String puestoDesc;

	private byte statusId = 1;

	private Date createdAt = new Date();

	private long createdBy;

	private Date updatedAt;

	private long updatedBy;

	public long getPuestoId() {
		return puestoId;
	}

	public void setPuestoId(long puestoId) {
		this.puestoId = puestoId;
	}

	public String getPuestoDesc() {
		return puestoDesc;
	}

	public void setPuestoDesc(String puestoDesc) {
		this.puestoDesc = puestoDesc;
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
