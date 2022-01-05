package com.jachs.j_se_batch.example;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.dao.JdbcExecutionContextDao;
import org.springframework.batch.core.repository.dao.JdbcJobExecutionDao;
import org.springframework.batch.core.repository.dao.JdbcJobInstanceDao;
import org.springframework.batch.core.repository.dao.JdbcStepExecutionDao;
import org.springframework.batch.core.repository.dao.MapExecutionContextDao;
import org.springframework.batch.core.repository.dao.MapJobExecutionDao;
import org.springframework.batch.core.repository.dao.MapJobInstanceDao;
import org.springframework.batch.core.repository.dao.MapStepExecutionDao;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.repository.support.SimpleJobRepository;
import org.springframework.core.task.SyncTaskExecutor;

/***
 * 
 * @author zhanchaohan
 *
 */
public class T1 {
	JdbcJobInstanceDao jdbcJobInstanceDao;
	JdbcJobExecutionDao jdbcJobExecutionDao;
	JdbcStepExecutionDao jdbcStepExecutionDao;
	JdbcExecutionContextDao jdbcExecutionContextDao;
	
	@Before
	public void init_JobInstanceDao() {
		MapJobInstanceDao mapJobInstanceDao;
		
		jdbcJobInstanceDao=new JdbcJobInstanceDao();
	}
	@Before
	public void init_JobExecutionDao() {
		MapJobExecutionDao mapJobExecutionDao;
		
		jdbcJobExecutionDao=new JdbcJobExecutionDao();
	}
	@Before
	public void init_StepExecutionDao() {
		MapStepExecutionDao mapStepExecutionDao;
		
		jdbcStepExecutionDao=new JdbcStepExecutionDao();
	}
	@Before
	public void init_ExecutionContextDao() {
		MapExecutionContextDao mapExecutionContextDao;
		
		jdbcExecutionContextDao=new JdbcExecutionContextDao();
	}
	
	@Test
	public void test1() throws Exception {
		JobRepositoryFactoryBean jobRepositoryFactoryBean=new JobRepositoryFactoryBean();
		
		SimpleJobRepository simpleJobRepository=new SimpleJobRepository(jdbcJobInstanceDao, jdbcJobExecutionDao, jdbcStepExecutionDao, jdbcExecutionContextDao);
		
		JobBuilderFactory jobBuilderFactory=new JobBuilderFactory(simpleJobRepository);
		StepBuilderFactory stepBuilderFactory=new StepBuilderFactory(simpleJobRepository, jobRepositoryFactoryBean.getTransactionManager());
		SimpleJobLauncher simpleJobLauncher=new SimpleJobLauncher();
		
		simpleJobLauncher.setJobRepository(simpleJobRepository);
		simpleJobLauncher.setTaskExecutor(new SyncTaskExecutor());//设置线程池
		
		simpleJobLauncher.afterPropertiesSet();//开始初始化实例
		
		simpleJobLauncher.run(null, new JobParameters());
		
//		jobBuilderFactory.get("job1");
//		stepBuilderFactory.get("step1");
	}
}
