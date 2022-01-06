package com.jachs.j_se_batch.core.job.builder;

import org.junit.Before;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;

/**
 * 
 * @author zhanchaohan
 *
 */
public class SimpleJobBuilderTest {
	SimpleJobBuilder  sjb;
	
	@Before
	public void init() {
		sjb=new SimpleJobBuilder(sjb);
	}
}
