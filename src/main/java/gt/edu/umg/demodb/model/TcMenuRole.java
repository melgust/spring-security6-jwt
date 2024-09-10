package gt.edu.umg.demodb.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "tc_menu_role", uniqueConstraints = { @UniqueConstraint(columnNames = { "menu_id", "role_id" }) })
public class TcMenuRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long menuRoleId;

	@ManyToOne
	@JoinColumn(name = "menu_id")
	private TcMenu tcMenu;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private TcRole tcRole;

	private String urlMenu;

	private byte statusId = 1;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt = new Date();

	private long createdBy = 0;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	private long updatedBy = 0;

	private byte hidden = 0;
	
	@Transient
	private List<TcMenuRole> children;

	public List<TcMenuRole> getChildren() {
		return children;
	}

	public void setChildren(List<TcMenuRole> children) {
		this.children = children;
	}

	public long getMenuRoleId() {
		return menuRoleId;
	}

	public void setMenuRoleId(long menuRoleId) {
		this.menuRoleId = menuRoleId;
	}

	public TcMenu getTcMenu() {
		return tcMenu;
	}

	public void setTcMenu(TcMenu tcMenu) {
		this.tcMenu = tcMenu;
	}

	public TcRole getTcRole() {
		return tcRole;
	}

	public void setTcRole(TcRole tcRole) {
		this.tcRole = tcRole;
	}

	public String getUrlMenu() {
		return urlMenu;
	}

	public void setUrlMenu(String urlMenu) {
		this.urlMenu = urlMenu;
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

	public byte getHidden() {
		return hidden;
	}

	public void setHidden(byte hidden) {
		this.hidden = hidden;
	}

}
