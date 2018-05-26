package com.schawk.tornado.junit;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.sh4m.project.util.CommonFileUtil;

/**
 * @author ssaleh
 *
 * Created date Jun 12, 2017
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@SqlGroup({@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:create-db.sql","classpath:insert-data.sql"}),
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:drop-table.sql") })
public class MainTest {

	

//	@Autowired
//	private LocationService locService;
//	
	
	
	//@Test
	public void getFileInDirSuccess(){
		List<String> list = null;
		try{
			list = CommonFileUtil.getAllDir("C:\\");
		} catch (Exception e){
			
		}
		Assert.assertEquals(list.size() > 0,true);
	}
	
	//@Test
	public void getFileInDirFailed(){
		List<String> list = null;
		try{
			list = CommonFileUtil.getAllDir("C:\\dummyfolder\\");
		} catch (Exception e){
			
		}
		Assert.assertEquals(list.size() == 0,true);
	}

	@Test
	public void getLocationAllSuccess(){
//		List<LocationDTO> loc = new ArrayList<LocationDTO>();
//		loc = locService.getAll();
//		Assert.assertTrue(loc.size() > 0);
	}
	
	
}
