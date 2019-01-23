package com.systems.telegram.bot.southpool.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="followers")
public class Followers implements Serializable, Member {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1334380683599549912L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name="username")
	private String username;
	
	@Column(name="follower")
	private String follower;
	
	@Column(name="chat_id")
	private String chatId;
	
	@Column(name="active")
	private String active;

	@Transient
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	@Override
	public String getFacebookProfileLink() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	@Override
	public String getMobileNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	@Override
	public String getCarPlateNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	@Override
	public String getYouAre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	@Override
	public String getPicUpLoc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	@Override
	public String getDropOffLoc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	@Override
	public String getRoute() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	@Override
	public String getAvailableSlots() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	@Override
	public String getEta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	@Override
	public String getEtd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	@Override
	public Date getUpdateDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	@Override
	public Date getRegisterDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	@Override
	public String getCustomMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Transient
	@Override
	public void setFacebookProfileLink(String facebookProfileLink) {
		// TODO Auto-generated method stub
		
	}

	@Transient
	@Override
	public void setMobileNumber(String mobileNumber) {
		// TODO Auto-generated method stub
		
	}

	@Transient
	@Override
	public void setCarPlateNumber(String carPlateNumber) {
		// TODO Auto-generated method stub
		
	}

	@Transient
	@Override
	public void setYouAre(String youAre) {
		// TODO Auto-generated method stub
		
	}

	@Transient
	@Override
	public void setPicUpLoc(String picUpLoc) {
		// TODO Auto-generated method stub
		
	}

	@Transient
	@Override
	public void setDropOffLoc(String dropOffLoc) {
		// TODO Auto-generated method stub
		
	}

	@Transient
	@Override
	public void setRoute(String route) {
		// TODO Auto-generated method stub
		
	}

	@Transient
	@Override
	public void setAvailableSlots(String availableSlots) {
		// TODO Auto-generated method stub
		
	}

	@Transient
	@Override
	public void setEta(String eta) {
		// TODO Auto-generated method stub
		
	}

	@Transient
	@Override
	public void setEtd(String etd) {
		// TODO Auto-generated method stub
		
	}

	@Transient
	@Override
	public void setUpdateDate(Date updateDate) {
		// TODO Auto-generated method stub
		
	}

	@Transient
	@Override
	public void setRegisterDate(Date registerDate) {
		// TODO Auto-generated method stub
		
	}

	@Transient
	@Override
	public void setCustomMessage(String custom_message) {
		// TODO Auto-generated method stub
		
	}

	@Transient
	@Override
	public String getTag() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	@Override
	public void setTag(String tag) {
		// TODO Auto-generated method stub
		
	}

}
