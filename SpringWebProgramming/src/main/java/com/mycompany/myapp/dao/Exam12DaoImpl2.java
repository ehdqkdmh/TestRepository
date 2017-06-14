package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;

@Component
public class Exam12DaoImpl2 implements Exam12Dao {
	private static final Logger LOGGER = LoggerFactory.getLogger(Exam12DaoImpl2.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public int boardInsert(Exam12Board board) {
		final String sql = "insert into board " +
				"(bno, btitle, bcontent, bwriter, bdate, bpassword, bhitcount, boriginalfilename, bsavedfilename, bfilecontent) " +
				"values " +
				"(board_bno_seq.nextval, ?, ?, ?, sysdate, ?, 0, ?, ?, ?) ";
		/*
		jdbcTemplate.update(
			sql, 
			board.getBtitle(), board.getBcontent(), board.getBwriter(), board.getBpassword(),
			board.getBoriginalfilename(), board.getBsavedfilename(), board.getBfilecontent()
		);
		*/
		
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"bno"});
				pstmt.setString(1, board.getBtitle());
				pstmt.setString(2, board.getBcontent());
				pstmt.setString(3, board.getBwriter());
				pstmt.setString(4, board.getBpassword());
				pstmt.setString(5, board.getBoriginalfilename());
				pstmt.setString(6, board.getBsavedfilename());
				pstmt.setString(7, board.getBfilecontent());
				return pstmt;
			}
		};
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(psc, keyHolder);
		int bno = keyHolder.getKey().intValue();
		
		LOGGER.info(String.valueOf(bno));
		
		return bno;
	}

	@Override
	public List<Exam12Board> boardSelectAll() {
		String sql = "select bno, btitle, bwriter, bdate, bhitcount ";
		sql += "from board ";
		sql += "order by bno desc ";

		RowMapper<Exam12Board> rowMapper = new RowMapper<Exam12Board>() {
			@Override
			public Exam12Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Exam12Board board = new Exam12Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString(3));
				board.setBdate(rs.getDate("bdate"));
				board.setBhitcount(rs.getInt("bhitcount"));
				return board;
			}
		};
		
		List<Exam12Board> list = jdbcTemplate.query(sql, rowMapper);
		return list;
	}
	
	@Override
	public List<Exam12Board> boardSelectPage(int pageNo, int rowsPerPage) {
		String sql = "select * ";
		sql += "from ( ";
		sql += "  select rownum as r, bno, btitle, bwriter, bdate, bhitcount ";
		sql += "  from ( ";
		sql += "    select bno, btitle, bwriter, bdate, bhitcount from board order by bno desc ";
		sql += "  ) ";
		sql += "  where rownum<=? ";
		sql += ") ";
		sql += "where r>=? ";
		
		Object[] args = { (pageNo*rowsPerPage), ((pageNo-1) * rowsPerPage + 1) };
		
		RowMapper<Exam12Board> rowMapper = new RowMapper<Exam12Board>() {
			@Override
			public Exam12Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Exam12Board board = new Exam12Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString(3));
				board.setBdate(rs.getDate("bdate"));
				board.setBhitcount(rs.getInt("bhitcount"));
				return board;
			}
		};
		
		List<Exam12Board> list = jdbcTemplate.query(sql, args, rowMapper);
		return list;
	}	
	
	@Override
	public int boardCountAll() {
		String sql = "select count(*) from board";
		int count = jdbcTemplate.queryForObject(sql, Integer.class);	
		return count;
	}
	
	@Override
	public Exam12Board boardSelectByBno(int bno) {
		String sql = "select * from board where bno=?";
		RowMapper<Exam12Board> rowMapper = new RowMapper<Exam12Board>() {
			@Override
			public Exam12Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Exam12Board board = new Exam12Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBpassword(rs.getString("bpassword"));
				board.setBdate(rs.getDate("bdate"));
				board.setBhitcount(rs.getInt("bhitcount"));
				board.setBoriginalfilename(rs.getString("boriginalfilename"));
				board.setBsavedfilename(rs.getString("bsavedfilename"));
				board.setBfilecontent(rs.getString("bfilecontent"));
				return board;
			}
		};
		Exam12Board board = jdbcTemplate.queryForObject(sql, rowMapper, bno);
		return board;
	}
	
	@Override
	public void boardUpdateBhitcount(int bno, int bhitcount) {
		String sql = "update board set bhitcount=? where bno=?";
		jdbcTemplate.update(sql, bhitcount, bno);
	}
	
	@Override
	public void boardUpdate(Exam12Board board) {
		String sql;
		if(board.getBoriginalfilename() != null) {
			sql = "update board set btitle=?, bcontent=?, bpassword=?, bdate=sysdate, boriginalfilename=?, bsavedfilename=?, bfilecontent=?  where bno=?";
			jdbcTemplate.update(sql, board.getBtitle(), board.getBcontent(), board.getBpassword(), board.getBoriginalfilename(), board.getBsavedfilename(), board.getBfilecontent(), board.getBno());
		} else {
			sql = "update board set btitle=?, bcontent=?, bpassword=?, bdate=sysdate  where bno=?";
			jdbcTemplate.update(sql, board.getBtitle(), board.getBcontent(), board.getBpassword(), board.getBno());
		}
	}	
	
	@Override
	public void boardDelete(int bno) {
		String sql = "delete from board where bno=?";
		jdbcTemplate.update(sql, bno);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public String memberInsert(Exam12Member member) {
		String sql = "insert into member ";
		sql += "(mid, mname, mpassword, mdate, mtel, memail, mage, maddress, moriginalfilename, msavedfilename, mfilecontent) ";
		sql += "values ";
		sql += "(?, ?, ?, sysdate, ?, ?, ?, ?, ?, ?, ?) ";
		
		jdbcTemplate.update(
			sql,
			member.getMid(), member.getMname(), member.getMpassword(), member.getMtel(),
			member.getMemail(), member.getMage(), member.getMaddress(),
			member.getMoriginalfilename(), member.getMsavedfilename(), member.getMfilecontent()
		);
		
		return member.getMid();
	}	
}









