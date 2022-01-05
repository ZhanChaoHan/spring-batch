package com.jachs.j_se_batch.step.builder;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.step.builder.StepBuilder;

/***
 * 
 * @author zhanchaohan
 *
 */
public class StepBuilderTest {
	private StepBuilder sb;
	
	@Before
	public void init() {
		sb=new StepBuilder("myStepBuilder");
	}
	@Test
	public void test1() {
		sb.chunk(10)//一次读10行
		.startLimit(0)//第几行开始
		.build();
	}
}
