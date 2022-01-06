package com.jachs.j_se_batch.se.part1;

import org.junit.Test;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/***
 * 
 * @author zhanchaohan
 *
 */
public class Demo {
	private static final String configerPath="/se/part1/job-context.xml";
	
	@Test
	public void test1() {
		ApplicationContext context = new ClassPathXmlApplicationContext(configerPath);
		JobLauncher launcher = context.getBean("jobLauncher",JobLauncher.class);
		
		System.out.println();
	}
}
