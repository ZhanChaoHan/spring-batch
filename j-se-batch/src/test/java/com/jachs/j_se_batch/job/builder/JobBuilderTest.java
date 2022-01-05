package com.jachs.j_se_batch.job.builder;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.JobInterruptedException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.builder.JobBuilder;

/***
 * 
 * @author zhanchaohan
 *
 */
public class JobBuilderTest {
	JobBuilder jb;
	
	@Before
	private void init() {
		jb=new JobBuilder("myJobBuilder");
	}
	
	@Test
	public void test1() {
		
	}
}
