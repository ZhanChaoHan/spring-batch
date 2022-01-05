package com.jachs.j_se_batch.job.builder;

import org.junit.Before;
import org.springframework.batch.core.job.builder.JobFlowBuilder;

/***
 * 
 * @author zhanchaohan
 *
 */
public class JobFlowBuilderTest {
	private JobFlowBuilder jfb;
	
	@Before
	public void init() {
		jfb=new JobFlowBuilder(null);
	}
}
