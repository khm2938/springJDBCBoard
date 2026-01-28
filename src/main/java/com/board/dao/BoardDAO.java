package com.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
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

	public int deleteBoard(Board board) {
		String query = "delete from jdbcBoard where no = ?";
		int count = jdbcTemplate.update(query, board.getNo());
		return count;
	}

	public int updateBoard(Board board) {
		String query = "update jdbcBoard set writer = ?, title = ?, content = ? where no = ?";
		int count = jdbcTemplate.update(query, board.getWriter(), board.getTitle(), board.getContent(), board.getNo());
		return count;
	}

	public List<Board> boardSearch(Board board) {

        String searchItem = board.getSearchType(); 
        List<String> searchList = Arrays.asList("title","content","writer");
        //검색타입이 존재하지 않으면 기본검색은 title로 하게된다. 
        if(!searchList.contains(board.getSearchType())) {
            searchItem = "title";
        }
        
        //select * from jdbcBoard where no > 0 and title like '%1%';
      	//select * from jdbcBoard where no > 0 and writer like '%z%';
      	//select * from jdbcBoard where no > 0 and content like '%z%';
        String query =  "select * from jdbcBoard where "+searchItem+" like '%"+board.getKeyword()+"%'";

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
	
	
	
}










