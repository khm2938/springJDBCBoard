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
		// 추후 작성 예정
		return null;
	}

	@Override
	public int updateMember(Member member) throws Exception {
		// 추후 작성 예정
		return 0;
	}

	@Override
	public int deleteMember(int no) throws Exception {
		// 추후 작성 예정
		return 0;
	}
	
	@Override
	public List<Member> memberList() throws Exception {
		// 추후 작성 예정
		return null;
	}

}
