package com.example.board;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.board.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public int insertBoard(BoardVO vo) {

		String sql = "insert into member (`userid`, `username`, `password`, `email`, `blogurl`, `photo`, `detail`) values ("
				+ "'" + vo.getUserId() + "',"
				+ "'" + vo.getUserName() + "',"
				+ "'" + vo.getPassword() + "',"
				+ "'" + vo.getEmail() + "',"
				+ "'" + vo.getBlogUrl() + "',"
				+ "'" + vo.getPhoto() + "',"
				+ "'" + vo.getDetail() + "')";

		return jdbcTemplate.update(sql);
	}

	// 글 삭제
	public int deleteBoard(int sid) {

		String sql = "delete from member where sid=" + sid;

		return jdbcTemplate.update(sql);
	}


	public int updateBoard(BoardVO vo) {

		String sql = "update member set "
				+ "userid='" + vo.getUserId() + "',"
				+ "username='" + vo.getUserName() + "',"
				+ "password='" + vo.getPassword() + "',"
				+ "email='" + vo.getEmail() + "',"
				+ "blogurl='" + vo.getBlogUrl() + "',"
				+ "photo='" + vo.getPhoto() + "',"
				+ "detail='" + vo.getDetail() + "' where sid=" + vo.getSid();

		return jdbcTemplate.update(sql);
	}

	class BoardRowMapper implements RowMapper<BoardVO> {

		@Override
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			BoardVO vo = new BoardVO();
			vo.setSid(rs.getInt("sid"));
			vo.setUserId(rs.getString("userId"));
			vo.setUserName(rs.getString("userName"));
			vo.setPassword(rs.getString("password"));
			vo.setEmail(rs.getString("email"));
			vo.setBlogUrl(rs.getString("blogUrl"));
			vo.setPhoto(rs.getString("photo"));
			vo.setDetail(rs.getString("detail"));
			vo.setRegdate(rs.getDate("regdate"));
			return vo;
		}

	}
	
	public BoardVO getBoard(int sid) {

		String sql = "select * from member  where sid=" + sid;

		return jdbcTemplate.queryForObject(sql, new BoardRowMapper());
	}
	
	public List<BoardVO> getBoardList() {

		String sql = "select * from member order by sid desc";

		return jdbcTemplate.query(sql, new BoardRowMapper());
	}

}
