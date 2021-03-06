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
@Table(name="previous_message")
public class PreviousMessage implements Serializable, Member {

	/**
	 * 
	 */
	private static final long serialVersionUID = -221537474103729100L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name="username")
	private String username;
	
	@Column(name="prev_message")
	private String prevMessage;
	
	@UpdateTimestamp
	@Column(name="update_date")
	private Date updateDate;
	
	@Column(name="tag")
	private String tag;

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFacebookProfileLink() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMobileNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCarPlateNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getYouAre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPicUpLoc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDropOffLoc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRoute() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAvailableSlots() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEtd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getRegisterDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCustomMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFacebookProfileLink(String facebookProfileLink) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMobileNumber(String mobileNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCarPlateNumber(String carPlateNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setYouAre(String youAre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPicUpLoc(String picUpLoc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDropOffLoc(String dropOffLoc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRoute(String route) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAvailableSlots(String availableSlots) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEta(String eta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEtd(String etd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRegisterDate(Date registerDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCustomMessage(String custom_message) {
		// TODO Auto-generated method stub
		
	}

	@Transient
	@Override
	public Integer getPostCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	@Override
	public void setPostCount(Integer postCount) {
		// TODO Auto-generated method stub
		
	}

	@Transient
	@Override
	public String getAdmin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	@Override
	public void setAdmin(String admin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getStar() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setStar(long star) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getChatId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setChatId(long chatId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPageAccessToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPageAccessToken(String pageAccessToken) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCreatePage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreatePage(String createPage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getProfilePostId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProfilePostId(String profilePostId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getProfilePostLink() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProfilePostLink(String profilePostLink) {
		// TODO Auto-generated method stub
		
	}
}
