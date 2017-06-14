package com.mycompany.myapp.dao;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.mycompany.myapp.ApplicationContextLoader;
import com.mycompany.myapp.dto.Exam12Board;

public class Exam12DaoImpl3Test extends ApplicationContextLoader {
	@Resource(name="exam12DaoImpl3")
	private Exam12Dao dao;
	
	@Test
	public void insertTest() {
		Exam12Board board = new Exam12Board();
		board.setBtitle("제목");
		board.setBcontent("내용");
		board.setBwriter("글쓴이");
		board.setBpassword("12345");
		int bno = dao.boardInsert(board);
		
		Exam12Board dbBoard = dao.boardSelectByBno(bno);
		
		Assert.assertNotNull(dbBoard);
		Assert.assertEquals(board.getBtitle(), dbBoard.getBtitle());
		Assert.assertEquals(board.getBcontent(), dbBoard.getBcontent());
		Assert.assertEquals(board.getBwriter(), dbBoard.getBwriter());
		Assert.assertEquals(board.getBpassword(), dbBoard.getBpassword());
	}
	
	@Test
	public void insertWithAttachTest() {
		Exam12Board board = new Exam12Board();
		board.setBtitle("제목");
		board.setBcontent("내용");
		board.setBwriter("글쓴이");
		board.setBpassword("12345");
		board.setBoriginalfilename("test.jpg");
		board.setBsavedfilename("test.jpg");
		board.setBfilecontent("image/jpeg");
		int bno = dao.boardInsert(board);
		
		Exam12Board dbBoard = dao.boardSelectByBno(bno);
		
		Assert.assertNotNull(dbBoard);
		Assert.assertEquals(board.getBtitle(), dbBoard.getBtitle());
		Assert.assertEquals(board.getBcontent(), dbBoard.getBcontent());
		Assert.assertEquals(board.getBwriter(), dbBoard.getBwriter());
		Assert.assertEquals(board.getBpassword(), dbBoard.getBpassword());
		Assert.assertEquals(board.getBoriginalfilename(), dbBoard.getBoriginalfilename());
		Assert.assertEquals(board.getBsavedfilename(), dbBoard.getBsavedfilename());
		Assert.assertEquals(board.getBfilecontent(), dbBoard.getBfilecontent());
	}	
	
	@Test
	public void updateTest() {
		Exam12Board board = new Exam12Board();
		board.setBtitle("제목");
		board.setBcontent("내용");
		board.setBwriter("글쓴이");
		board.setBpassword("12345");
		board.setBoriginalfilename("test.jpg");
		board.setBsavedfilename("test.jpg");
		board.setBfilecontent("image/jpeg");
		int bno = dao.boardInsert(board);
		
		board.setBtitle("제목2");
		board.setBcontent("내용2");
		dao.boardUpdate(board);
		
		Exam12Board dbBoard = dao.boardSelectByBno(bno);
		
		Assert.assertNotNull(dbBoard);
		Assert.assertEquals(board.getBtitle(), dbBoard.getBtitle());
		Assert.assertEquals(board.getBcontent(), dbBoard.getBcontent());
	}
	
	@Test
	public void deleteTest() {
		Exam12Board board = new Exam12Board();
		board.setBtitle("제목");
		board.setBcontent("내용");
		board.setBwriter("글쓴이");
		board.setBpassword("12345");
		board.setBoriginalfilename("test.jpg");
		board.setBsavedfilename("test.jpg");
		board.setBfilecontent("image/jpeg");
		int bno = dao.boardInsert(board);
		
		Exam12Board dbBoard = dao.boardSelectByBno(bno);
		Assert.assertNotNull(dbBoard);
		
		dao.boardDelete(bno);
		
		dbBoard = dao.boardSelectByBno(bno);
		Assert.assertNull(dbBoard);
	}
}











