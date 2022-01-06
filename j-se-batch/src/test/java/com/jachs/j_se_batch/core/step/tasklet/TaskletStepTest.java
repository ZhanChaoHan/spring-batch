package com.jachs.j_se_batch.core.step.tasklet;


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
		ts.setBeanName(null);
		ts.setChunkListeners(null);
		ts.setInterruptionPolicy(null);
		ts.setJobRepository(null);
		ts.setName(null);
		ts.setStartLimit(0);
		ts.setStepExecutionListeners(null);
		ts.setStepOperations(null);
		ts.setStreams(null);
		ts.setTasklet(null);
		ts.setTransactionAttribute(null);
		ts.setTransactionManager(null);
		
		ts.afterPropertiesSet();
	}
}
