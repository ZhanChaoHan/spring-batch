package com.jachs.j_se_batch.example;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
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
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.jdbc.support.JdbcTransactionManager;

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
		jdbcJobInstanceDao.setClobTypeToUse(0);
		jdbcJobInstanceDao.setJdbcTemplate(null);
		jdbcJobInstanceDao.setJobIncrementer(null);
		jdbcJobInstanceDao.setTablePrefix(null);
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
		
		jobRepositoryFactoryBean.setTransactionManager(new JdbcTransactionManager() );//设置事务
		
		SimpleJobRepository simpleJobRepository=new SimpleJobRepository(jdbcJobInstanceDao, jdbcJobExecutionDao, jdbcStepExecutionDao, jdbcExecutionContextDao);
		
		JobBuilderFactory jobBuilderFactory=new JobBuilderFactory(simpleJobRepository);
		StepBuilderFactory stepBuilderFactory=new StepBuilderFactory(simpleJobRepository, jobRepositoryFactoryBean.getTransactionManager());
		SimpleJobLauncher simpleJobLauncher=new SimpleJobLauncher();
		
		simpleJobLauncher.setJobRepository(simpleJobRepository);
		simpleJobLauncher.setTaskExecutor(new SyncTaskExecutor());//设置线程池
		
		simpleJobLauncher.afterPropertiesSet();//开始初始化实例
		
		
		TaskletStep st1=stepBuilderFactory.get("step1").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("第一个task");
				return RepeatStatus.FINISHED;
			}
		}).build();
		TaskletStep st2=stepBuilderFactory.get("step2").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("第2个task");
				return RepeatStatus.FINISHED;
			}
		}).build();
		Job job1=jobBuilderFactory.get("job1").start(st1)
				.next(st2)
				.build();
		
		simpleJobLauncher.run(job1, new JobParameters());
		
	}
}
