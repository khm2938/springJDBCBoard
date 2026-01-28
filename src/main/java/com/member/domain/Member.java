package com.member.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Member {
	private int no;
	private String userId;
	private String password;
	private String name;
	private String gender;
	private int age;
	private Date regDate;	
	private String searchType;
	private String keyword;
}
