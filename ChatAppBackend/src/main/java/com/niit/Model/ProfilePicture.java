package com.niit.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="profilepic")
public class ProfilePicture 
{
	
	
		@Id
	private String username;
		@Lob
	private byte[] pimage;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public byte[] getPimage() {
			return pimage;
		}
		public void setPimage(byte[] pimage) {
			this.pimage = pimage;
		}
		
		
	}

