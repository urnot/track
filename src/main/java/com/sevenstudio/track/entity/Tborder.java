package com.sevenstudio.track.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tborder {
	@Id
	@GeneratedValue
	private int id;
	private String tbnumber;
	private String status;
	private Date updatetime;

	public String getTbnumber() {
		return tbnumber;
	}

	public String getStatus() {
		return status;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setTbnumber(String tbnumber) {
		this.tbnumber = tbnumber;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
