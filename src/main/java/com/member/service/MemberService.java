package com.member.service;

import java.util.List;

import com.member.domain.Member;

public interface MemberService {
	public int insertMember(Member member) throws Exception;
	public Member selectByNo(int no) throws Exception;
	public int updateMember(Member member) throws Exception;
	public int deleteMember(Member member) throws Exception; 
	public List<Member> memberList() throws Exception;
	public List<Member> memberSearch(Member member)throws Exception;
	Member selectByUserId(String userId);
}
