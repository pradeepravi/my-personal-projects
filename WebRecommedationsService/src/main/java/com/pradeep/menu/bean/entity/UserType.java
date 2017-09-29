package com.pradeep.menu.bean.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_TYPE")
public class UserType {
	public UserType(){
		
	}
	public UserType(String type, String desc, boolean isActive, Date createdDate) {
		super();
		this.type = type;
		this.desc = desc;
		this.isActive = isActive;
		this.createdDate = createdDate;
	}

	private Long id;
	private String type;
	private String desc;
	private boolean isActive;
	private Date createdDate;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_type_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="user_type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name="user_type_desc")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(name="is_active")
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	@Column(name="createdDate")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
