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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="member_from_home_to_work")
public class SouthPoolMemberHomeToWork implements Serializable, Member {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5366672216320841960L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="facebook_profile_link")
	private String facebookProfileLink;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Column(name="car_plate_number")
	private String carPlateNumber;
	
	@Column(name="username")
	private String username;
	
	@Column(name="you_are")
	private String youAre;
	
	@Column(name="pickup_loc")
	private String picUpLoc;
	
	@Column(name="drop_off_loc")
	private String dropOffLoc;
	
	@Column(name="route")
	private String route;
	
	@Column(name="available_slots")
	private String availableSlots;
	
	@Column(name="eta")
	private String eta;
	
	@Column(name="etd")
	private String etd;
	
	@UpdateTimestamp
	@Column(name="update_date")
	private Date updateDate;
	
	@CreationTimestamp
	@Column(name="register_date")
	private Date registerDate;
	
	@Column(name="custom_message")
	private String customMessage;
	
	@Column(name="post_count")
	private Integer postCount;
	
	@Column(name="allowed")
	private String allowed;
	
	@Column(name="admin")
	private String admin;
	
	@Column(name="chat_id")
	private long chatId;
	
	@Column(name="page_access_token")
	private String pageAccessToken;
	
	@Column(name="create_page")
	private String createPage;
	
	@Column(name="profile_post_id")
	private String profilePostId;
	
	@Column(name="profile_post_link")
	private String profilePostLink;

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

	@Override
	public long getStar() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setStar(long star) {
		// TODO Auto-generated method stub
		
	}
}
