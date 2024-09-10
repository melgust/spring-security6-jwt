package gt.edu.umg.demodb.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tc_menu")
public class TcMenu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long menuId;

	@Column(unique = true)
	private String menuDesc;

	@Column(unique = true)
	private String shortName;

	private String tooltip;

	private String icon;

	private String iconColor;

	private String textColor;

	private byte statusId;

	private long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	private long updatedBy = 0;

	@ManyToOne
	@JoinColumn(name = "father_id")
	@JsonBackReference
	private TcMenu tcFather;

	@Transient
	private List<TcMenuRole> children;

	public List<TcMenuRole> getChildren() {
		return children;
	}

	public void setChildren(List<TcMenuRole> children) {
		this.children = children;
	}

	public TcMenu getTcFather() {
		return tcFather;
	}

	public void setTcFather(TcMenu tcFather) {
		this.tcFather = tcFather;
	}

	public long getMenuId() {
		return menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconColor() {
		return iconColor;
	}

	public void setIconColor(String iconColor) {
		this.iconColor = iconColor;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
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
