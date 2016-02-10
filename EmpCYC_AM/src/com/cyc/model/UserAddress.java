package com.cyc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="UserAddress",schema="cyc")
public class UserAddress {
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="ADDR_ID")
		private int addr_id;
		
		@Column(name="CITY")
		private String city;
		@Column(name="STATE")
		private String state;
		
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name = "CREATION_TIME", nullable = false, length = 19)
		private Date creation_time;
		
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name = "LAST_MOD_TIME", nullable = false, length = 19)
		private Date last_modified_time;
		
		@ManyToOne
		@JoinColumn(name="USER_ID")
		private User user;

		public int getAddr_id() {
			return addr_id;
		}

		public void setAddr_id(int addr_id) {
			this.addr_id = addr_id;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Date getCreation_time() {
			return creation_time;
		}

		public void setCreation_time(Date creation_time) {
			this.creation_time = creation_time;
		}

		public Date getLast_modified_time() {
			return last_modified_time;
		}

		public void setLast_modified_time(Date last_modified_time) {
			this.last_modified_time = last_modified_time;
		}
		
		
 	  
}
