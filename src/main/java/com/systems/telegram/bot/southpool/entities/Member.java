package com.systems.telegram.bot.southpool.entities;

import java.util.Date;

public interface Member {

	public Long getId();
	public String getName();
	public String getFacebookProfileLink();
	public String getMobileNumber();
	public String getCarPlateNumber();
	public String getUsername();
	public String getYouAre();
	public String getPicUpLoc();
	public String getDropOffLoc();
	public String getRoute();
	public String getAvailableSlots();
	public String getEta();
	public String getEtd();
	public Date getUpdateDate();
	public Date getRegisterDate();
	public String getCustomMessage();
	public String getTag();
	public Integer getPostCount();
	public String getAdmin();
	public long getStar();
	public long getChatId();
	public String getPageAccessToken();
	public String getCreatePage();
	public String getProfilePostId();
	public String getProfilePostLink();
	
	public void setId(Long id);
	public void setName(String name);
	public void setFacebookProfileLink(String facebookProfileLink);
	public void setMobileNumber(String mobileNumber);
	public void setCarPlateNumber(String carPlateNumber);
	public void setUsername(String username);
	public void setYouAre(String youAre);
	public void setPicUpLoc(String picUpLoc);
	public void setDropOffLoc(String dropOffLoc);
	public void setRoute(String route);
	public void setAvailableSlots(String availableSlots);
	public void setEta(String eta);
	public void setEtd(String etd);
	public void setUpdateDate(Date updateDate);
	public void setRegisterDate(Date registerDate);
	public void setCustomMessage(String custom_message);
	public void setTag(String tag);
	public void setPostCount(Integer postCount);
	public void setAdmin(String admin);
	public void setStar(long star);
	public void setChatId(long chatId);
	public void setPageAccessToken(String pageAccessToken);
	public void setCreatePage(String createPage);
	public void setProfilePostId(String profilePostId);
	public void setProfilePostLink(String profilePostLink);
	
}
