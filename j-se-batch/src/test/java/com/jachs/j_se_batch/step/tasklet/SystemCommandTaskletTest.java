package com.jachs.j_se_batch.step.tasklet;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.step.tasklet.SystemCommandTasklet;

/***
 * 
 * @author zhanchaohan
 *
 */
public class SystemCommandTaskletTest {
	private SystemCommandTasklet sct;
	
	@Before
	private void init() {
		sct=new SystemCommandTasklet();
	}
	@Test
	public void test1() throws Exception {
		sct.setCommand(null);
		
		
		sct.afterPropertiesSet();
	}
}
