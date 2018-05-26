/**
 * @author ssaleh
 *
 * Created date Jul 12, 2017
 */
package com.schawk.tornado.junit;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.sh4m.project.dto.UserDTO;
import net.sh4m.project.service.UserService;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@SqlGroup({@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:create-db.sql","classpath:insert-data.sql"}),
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:drop-table.sql") })
public class RepositoryTest {
	//this is unit test
	
	
	@Autowired
	private UserService userService;
	
	
	//create new @Test if need to re-init the db.
	@Test
	public void mainTest(){
		//getAllLocationSuccess();
	
	}



	private void getAllUsers() {
		List<UserDTO> lists = userService.getAll();
		Assert.assertTrue(lists.size() > 0);
	}

	
}
