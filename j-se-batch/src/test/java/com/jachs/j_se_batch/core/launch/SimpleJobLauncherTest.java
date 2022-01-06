package com.jachs.j_se_batch.core.launch;


import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;

/***
 * 
 * @author zhanchaohan
 *
 */
public class SimpleJobLauncherTest {
	private SimpleJobLauncher sjl;
	
	@Before
	public void init() throws Exception {
		sjl=new SimpleJobLauncher();
		
		sjl.setJobRepository(null);//設置任務管理器
		sjl.setTaskExecutor(null);//設置任務處理器
		sjl.afterPropertiesSet();//初始化
	}
	
	@Test
	public void test1() {
		
	}
}
