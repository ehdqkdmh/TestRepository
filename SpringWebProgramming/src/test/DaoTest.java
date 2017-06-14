package test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.myapp.dao.Exam15Dao;

public class DaoTest extends ApplicationContextLoader {
	@Autowired
	private Exam15Dao dao;
	
	@Test
	public void insertTest() {
		//테스트 코드
		Assert.assertNotNull(dao);
	}	
	
	@Test
	public void selectTest() {
		//테스트 코드
	}
}
