package com.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

	public List<Member> memberList() {
		String query = "select * from MEMBER where no > 0 order by no desc, regDate desc";
		List<Member> memberList = jdbcTemplate.query(query, new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member member = new Member();
				member.setNo(rs.getInt("NO"));
				member.setUserId(rs.getString("USERID"));
				member.setName(rs.getString("NAME"));
				member.setAge(rs.getInt("AGE"));
				member.setGender(rs.getString("GENDER"));
				member.setRegDate(rs.getDate("REGDATE"));
				return member;
			}
		});
		return memberList;
	}

	public List<Member> memberSearch(Member member) {
		
		String searchItem = member.getSearchType();
		List<String> searchList = Arrays.asList("userId", "name");
		//검색타입이 존재하지 않으면 userId 디폴트
		if(!searchList.contains(member.getSearchType())) {
			searchItem = "userId";
		}
		
		//select * from MEMBER where no > 0 and userId like '%1%';
		//select * from MEMBER where no > 0 and name = '홍길동';
		String query = "select * from MEMBER where no > 0 and "+searchItem+" like '%"+member.getKeyword()+"%'";
		
		List<Member> memberList = jdbcTemplate.query(query, new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException{
				Member member = new Member();
				member.setNo(rs.getInt("NO"));
				member.setUserId(rs.getString("USERID"));
				member.setName(rs.getString("NAME"));
				member.setGender(rs.getString("GENDER"));
				member.setAge(rs.getInt("AGE"));
				member.setRegDate(rs.getDate("regDate"));
				return member;
			}
		});
		return memberList;
	}

	public Member selectByNo(int no) { 
		String query = "select * from MEMBER where no = ?";
		
		List<Member> memberList = jdbcTemplate.query(query, new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException{
				Member m = new Member();
				m.setNo(rs.getInt("NO"));
				m.setUserId(rs.getString("USERID"));
				m.setName(rs.getString("NAME"));
				m.setGender(rs.getString("GENDER"));
				m.setAge(rs.getInt("AGE"));
				m.setRegDate(rs.getDate("REGDATE"));
				return m;
			}
		}, no);
		
		return memberList.isEmpty() ? null : memberList.get(0);
	}

	public int deleteMember(Member member) {
		String query = "delete from MEMBER where no = ?";
		int count = jdbcTemplate.update(query, member.getNo());
		return count;
	}

	public int updateMember(Member member) {
		String query = "update MEMBER set password = ? where no = ?";
		int count = jdbcTemplate.update(query, member.getPassword(), member.getNo());
		return count;
	}
	
	public Member selectByUserId(String userId) {
	    String query = "SELECT * FROM MEMBER WHERE userId = ?";
	    
	    List<Member> list = jdbcTemplate.query(query, (rs, rowNum) -> {
	        Member m = new Member();
	        m.setNo(rs.getInt("NO"));
	        m.setUserId(rs.getString("USERID"));
	        m.setName(rs.getString("NAME"));
	        m.setGender(rs.getString("GENDER"));
	        m.setAge(rs.getInt("AGE"));
	        m.setRegDate(rs.getDate("REGDATE"));
	        return m;
	    }, userId);

	    return list.isEmpty() ? null : list.get(0);
	}
	
	
}



























