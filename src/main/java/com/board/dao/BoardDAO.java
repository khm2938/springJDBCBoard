package com.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.board.domain.Board;

@Repository
public class BoardDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int insertBoard(Board board) {
		String query = "insert into jdbcBoard values(jdbcBoard_seq.nextval, ?, ?, ?, sysdate)";
		int count = jdbcTemplate.update(query, board.getTitle(), board.getContent(), board.getWriter());

		return count;
	}

	public List<Board> boardList() {
		String query = "select * from jdbcBoard where no > 0 order by no desc, regDate desc";
		List<Board> boardList = jdbcTemplate.query(query, new RowMapper<Board>() {
			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Board board = new Board();
				board.setNo(rs.getInt("no"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriter(rs.getString("writer"));
				board.setRegDate(rs.getDate("regDate"));
				return board;
			}
		});

		return boardList;
	}

	public Board selectByNo(Board board) {
		System.out.println("-----> DAO 호출 번호: " + board.getNo()); //디버그 확인용
		String query = "select * from jdbcBoard where no = ?";

		List<Board> boardList = jdbcTemplate.query(query, new Object[] { board.getNo() }, // 파라미터를 배열로 묶어서 전달
				new RowMapper<Board>() {
					@Override
					public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
						Board b = new Board();
						b.setNo(rs.getInt("NO"));
						b.setTitle(rs.getString("TITLE"));
						b.setContent(rs.getString("CONTENT"));
						b.setWriter(rs.getString("WRITER"));
						b.setRegDate(rs.getDate("REGDATE"));
						return b;
					}
				});

		return boardList.isEmpty() ? null : boardList.get(0);
	}
}
