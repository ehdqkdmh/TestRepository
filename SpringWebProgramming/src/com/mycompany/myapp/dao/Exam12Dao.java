package com.mycompany.myapp.dao;

import java.util.List;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;

public interface Exam12Dao {
	public int boardInsert(Exam12Board board);	
	public List<Exam12Board> boardSelectAll();
	public List<Exam12Board> boardSelectPage(int pageNo, int rowsPerPage);
	public int boardCountAll();
	public Exam12Board boardSelectByBno(int bno);
	public void boardUpdateBhitcount(int bno, int bhitcount);
	public void boardUpdate(Exam12Board board);
	public void boardDelete(int bno);
	
	public String memberInsert(Exam12Member member);
}
