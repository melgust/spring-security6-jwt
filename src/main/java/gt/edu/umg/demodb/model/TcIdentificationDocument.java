package gt.edu.umg.demodb.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tc_identification_document")
public class TcIdentificationDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long identificationDocumentId;

	private String identificationDocumentDesc;

	private byte statusIde = 1;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt = new Date();

	private long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	private long updatedBy = 0;

	public long getIdentificationDocumentId() {
		return identificationDocumentId;
	}

	public void setIdentificationDocumentId(long identificationDocumentId) {
		this.identificationDocumentId = identificationDocumentId;
	}

	public String getIdentificationDocumentDesc() {
		return identificationDocumentDesc;
	}

	public void setIdentificationDocumentDesc(String identificationDocumentDesc) {
		this.identificationDocumentDesc = identificationDocumentDesc;
	}

	public byte getStatusIde() {
		return statusIde;
	}

	public void setStatusIde(byte statusIde) {
		this.statusIde = statusIde;
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
