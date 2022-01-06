package com.jachs.j_se_batch.core.job.builder;

import org.junit.Before;
import org.springframework.batch.core.job.builder.FlowJobBuilder;

/***
 * 
 * @author zhanchaohan
 *
 */
public class FlowJobBuilderTest {
	private FlowJobBuilder fjb;
	
	/**
	 * 
	 */
	@Before
	public void init() {
		fjb=new FlowJobBuilder(fjb);
	}
}
