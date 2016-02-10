package com.cyc.model;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * this is a base class to save user details 
 * 
 */

@Document(collection="user")
public class User implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private Date creation_time;
	private Date last_update_time;
	private String email;
	private String mobile;
	private String status;
	
	/*@OneToMany(cascade=CascadeType.ALL,mappedBy="user")
	private Set<UserAddress> addr;
	*/
	
	public User() {	}
	
	public User(String id, String firstName, String lastName,
			Date creation_time, Date last_update_time, String email,
			String mobile, String status) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.creation_time = creation_time;
		this.last_update_time = last_update_time;
		this.email = email;
		this.mobile = mobile;
		this.status = status;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
		public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreation_time() {
		return creation_time;
	}
	public void setCreation_time(Date creation_time) {
		this.creation_time = creation_time;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLast_update_time() {
		return last_update_time;
	}
	public void setLast_update_time(Date last_update_time) {
		this.last_update_time = last_update_time;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	/*@JsonIgnore
	public Set<UserAddress> getAddr() {
		return addr;
	}
	public void setAddr(Set<UserAddress> addr) {
		this.addr = addr;
	}
	*/
}
