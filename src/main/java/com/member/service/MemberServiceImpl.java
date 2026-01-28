package com.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.member.dao.MemberDAO;
import com.member.domain.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public int insertMember(Member member) throws Exception {
		int count = memberDAO.insertMember(member);
		return count;
	}

	@Override
	public Member selectByNo(int no) throws Exception {
		return memberDAO.selectByNo(no);
	}

	@Override
	public int updateMember(Member member) throws Exception {
		int count = memberDAO.updateMember(member);
		return count;
	}

	@Override
	public int deleteMember(Member member) throws Exception {
		int count = memberDAO.deleteMember(member);
		return count;
	}
	
	@Override
	public List<Member> memberList() throws Exception {
		List<Member> memberList = memberDAO.memberList();
		return memberList;
	}

	@Override
	public List<Member> memberSearch(Member member) throws Exception {
		return memberDAO.memberSearch(member);
	}

	@Override
	public Member selectByUserId(String userId) {
		return memberDAO.selectByUserId(userId);
	}

}
