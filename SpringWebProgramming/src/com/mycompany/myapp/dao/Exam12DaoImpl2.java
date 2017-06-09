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
		Connection conn = null;
		try {
			//JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			//연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			//연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결 성공");
			//매개 변수화된 SQL 작성
			String sql = "update board set bhitcount=? where bno=?";
			//SQL 문을 전송해서 실행
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bhitcount);
			pstmt.setInt(2, bno);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//연결 끊기
			try { 
				conn.close(); 
				LOGGER.info("연결 끊김");
			} catch (SQLException e) { }
		}
	}
	
	@Override
	public void boardUpdate(Exam12Board board) {
		Connection conn = null;
		try {
			//JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			//연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			//연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			//매개 변수화된 SQL 작성
			String sql;
			if(board.getBoriginalfilename() != null) {
				sql = "update board set btitle=?, bcontent=?, bpassword=?, bdate=sysdate, boriginalfilename=?, bsavedfilename=?, bfilecontent=?  where bno=?";
			} else {
				sql = "update board set btitle=?, bcontent=?, bpassword=?, bdate=sysdate  where bno=?";
			}
			//SQL 문을 전송해서 실행
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBtitle());
			pstmt.setString(2, board.getBcontent());
			pstmt.setString(3, board.getBpassword());
			if(board.getBoriginalfilename() != null) {
				pstmt.setString(4, board.getBoriginalfilename());
				pstmt.setString(5, board.getBsavedfilename());
				pstmt.setString(6, board.getBfilecontent());
				pstmt.setInt(7, board.getBno());
			} else {
				pstmt.setInt(4, board.getBno());
			}
			pstmt.executeUpdate();
			pstmt.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//연결 끊기
			try { 
				conn.close(); 
				LOGGER.info("연결 끊김");
			} catch (SQLException e) { }
		}
	}	
	
	@Override
	public void boardDelete(int bno) {
		Connection conn = null;
		try {
			//JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			//연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			//연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			//매개 변수화된 SQL 작성
			String sql = "delete from board where bno=?";
			//SQL 문을 전송해서 실행
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//연결 끊기
			try { 
				conn.close(); 
				LOGGER.info("연결 끊김");
			} catch (SQLException e) { }
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public String memberInsert(Exam12Member member) {
		String mid = null;
		Connection conn = null;
		try {
			//JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			//연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			//연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결 성공");
			//매개 변수화된 SQL 작성
			String sql = "insert into member ";
			sql += "(mid, mname, mpassword, mdate, mtel, memail, mage, maddress, moriginalfilename, msavedfilename, mfilecontent) ";
			sql += "values ";
			sql += "(?, ?, ?, sysdate, ?, ?, ?, ?, ?, ?, ?) ";
			//SQL 문을 전송해서 실행
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMid());
			pstmt.setString(2, member.getMname());
			pstmt.setString(3, member.getMpassword());
			pstmt.setString(4, member.getMtel());
			pstmt.setString(5, member.getMemail());
			pstmt.setInt(6, member.getMage());
			pstmt.setString(7, member.getMaddress());
			pstmt.setString(8, member.getMoriginalfilename());
			pstmt.setString(9, member.getMsavedfilename());
			pstmt.setString(10, member.getMfilecontent());
			pstmt.executeUpdate();
			pstmt.close();
			//리턴값 설정
			mid = member.getMid();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//연결 끊기
			try { 
				conn.close(); 
				LOGGER.info("연결 끊김");
			} catch (SQLException e) { }
		}
		return mid;
	}	
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		Exam12DaoImpl2 test = new Exam12DaoImpl2();
		List<Exam12Board> list = test.boardSelectPage(2, 10);
		for(Exam12Board board : list) {
			LOGGER.info(board.getBtitle());
		}
	}
}









