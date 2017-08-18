package com.niit.Model;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="job")
public class Job {
	public int getJid() {
		return jid;
	}
	public void setJid(int jid) {
		this.jid = jid;
	}
	public String getJtitle() {
		return jtitle;
	}
	public void setJtitle(String jtitle) {
		this.jtitle = jtitle;
	}
	public String getJdesc() {
		return jdesc;
	}
	public void setJdesc(String jdesc) {
		this.jdesc = jdesc;
	}
	public String getJskills() {
		return jskills;
	}
	public void setJskills(String jskills) {
		this.jskills = jskills;
	}
	public String getJyrsofexp() {
		return jyrsofexp;
	}
	public void setJyrsofexp(String jyrsofexp) {
		this.jyrsofexp = jyrsofexp;
	}
	public String getJsalary() {
		return jsalary;
	}
	public void setJsalary(String jsalary) {
		this.jsalary = jsalary;
	}
	public String getJcompany() {
		return jcompany;
	}
	public void setJcompany(String jcompany) {
		this.jcompany = jcompany;
	}
	public String getJlocation() {
		return jlocation;
	}
	public void setJlocation(String jlocation) {
		this.jlocation = jlocation;
	}
	public Date getJpostedon() {
		return jpostedon;
	}
	public void setJpostedon(Date jpostedon) {
		this.jpostedon = jpostedon;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int jid;
	@NotEmpty
	private String jtitle;
	@NotEmpty
	private String jdesc;
	@NotEmpty
	private String jskills;
	@NotEmpty
	private String jyrsofexp;
	@NotEmpty
private String jsalary;
	@NotEmpty
	private String jcompany;
	@NotEmpty
	private String jlocation;

	private Date jpostedon;
	
	

}
