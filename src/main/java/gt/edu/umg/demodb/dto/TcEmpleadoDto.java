package gt.edu.umg.demodb.dto;

import java.util.Date;

public class TcEmpleadoDto {

	private long empleadoId;

	private String empleadoDesc;

	private byte statusId = 1;

	private Date createdAt = new Date();

	private long createdBy;

	private Date updatedAt;

	private long updatedBy;

	private TcPuestoDto tcPuesto;

	public long getEmpleadoId() {
		return empleadoId;
	}

	public void setEmpleadoId(long empleadoId) {
		this.empleadoId = empleadoId;
	}

	public String getEmpleadoDesc() {
		return empleadoDesc;
	}

	public void setEmpleadoDesc(String empleadoDesc) {
		this.empleadoDesc = empleadoDesc;
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

	public TcPuestoDto getTcPuesto() {
		return tcPuesto;
	}

	public void setTcPuesto(TcPuestoDto tcPuesto) {
		this.tcPuesto = tcPuesto;
	}

}
