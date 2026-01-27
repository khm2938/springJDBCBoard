package com.member.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.member.domain.Member;

@Repository
public class MemberDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insertMember(Member member) {
		String query = "insert into MEMBER values(MEMBER_SEQ.nextval, ?, ?, ?, ?, ?, sysdate)";
		
		int count = jdbcTemplate.update(query, 
				member.getUserId(),
				member.getPassword(),
				member.getName(),
				member.getGender(),
				member.getAge()
			);
		
		return count;
	}

}

