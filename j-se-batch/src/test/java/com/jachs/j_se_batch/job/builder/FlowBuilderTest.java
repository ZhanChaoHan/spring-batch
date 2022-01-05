package com.jachs.j_se_batch.job.builder;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.job.builder.FlowBuilder;

import com.jachs.j_se_batch.entity.Dog;

/***
 * 
 * @author zhanchaohan
 *@see 
 */
public class FlowBuilderTest {
	private FlowBuilder fb;
	
	@Before
	public void init() {
		fb=new FlowBuilder<Dog>("myFlowBuilder");
	}
	@Test
	public void test1() {
		
	}
}
