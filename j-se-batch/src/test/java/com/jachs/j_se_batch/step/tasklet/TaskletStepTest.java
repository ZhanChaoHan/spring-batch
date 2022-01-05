package com.jachs.j_se_batch.step.tasklet;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.step.tasklet.TaskletStep;

/**
 * 
 * @author zhanchaohan
 *
 */
public class TaskletStepTest {
	private TaskletStep ts;
	
	@Before
	private void init() {
		ts=new TaskletStep("myTaskletStep");
	}
	@Test
	public void test1() throws Exception {
		ts.setAllowStartIfComplete(false);
		
		ts.afterPropertiesSet();
	}
}
