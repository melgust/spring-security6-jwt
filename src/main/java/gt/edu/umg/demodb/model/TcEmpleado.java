package gt.edu.umg.demodb.model;

import java.util.Date;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tc_empleado")
public class TcEmpleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long empleadoId;

	@NotNull
	private String empleadoDesc;

	@NotNull
	private byte statusId = 1;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt = new Date();

	private long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	private long updatedBy;

	@ManyToOne
	@JoinColumn(name = "puesto_id")
	private TcPuesto tcPuesto;

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

	public TcPuesto getTcPuesto() {
		return tcPuesto;
	}

	public void setTcPuesto(TcPuesto tcPuesto) {
		this.tcPuesto = tcPuesto;
	}

}
