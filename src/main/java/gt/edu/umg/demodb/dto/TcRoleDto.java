package gt.edu.umg.demodb.dto;

import java.util.Date;

public class TcRoleDto {

	private long roleId;
	private String roleDesc;
	private String code;
	private byte roleType;
	private byte statusId = 1;
	private Date createdAt = new Date();
	private long createdBy;
	private Date updatedAt;
	private long updatedBy = 0;

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public byte getRoleType() {
		return roleType;
	}

	public void setRoleType(byte roleType) {
		this.roleType = roleType;
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
