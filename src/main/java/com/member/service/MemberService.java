package com.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.member.domain.Member;

@Service
public interface MemberService {
	public int insertMember(Member member) throws Exception;
	public Member selectByNo(int no) throws Exception;
	public int updateMember(Member member) throws Exception;
	public int deleteMember(int no) throws Exception; 
	public List<Member> memberList() throws Exception; 
}
