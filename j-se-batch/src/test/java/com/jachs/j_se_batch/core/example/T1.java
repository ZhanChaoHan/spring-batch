package com.jachs.j_se_batch.core.example;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.ExecutionContextSerializer;
import org.springframework.batch.core.repository.dao.DefaultExecutionContextSerializer;
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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;

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
	//序列化方式
	ExecutionContextSerializer executionContextSerializer=new DefaultExecutionContextSerializer();
//	ExecutionContextSerializer jackson2ExecutionContextStringSerializer=new Jackson2ExecutionContextStringSerializer();
//	ExecutionContextSerializer xStreamExecutionContextStringSerializer=new XStreamExecutionContextStringSerializer();
	//表名前缀
	private static final String prefix="BATCH_";
	
	//主键自增处理器根据不同数据库类型
	DataFieldMaxValueIncrementer mysqlIncrementer;
//	DataFieldMaxValueIncrementer oracleIncrementer=new OracleSequenceMaxValueIncrementer();
	
	
	@Before
	public void init() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("dbcp.xml");

		JdbcTemplate jdbcTemplate=ctx.getBean("jdbcTemplate",JdbcTemplate.class);
		
		mysqlIncrementer=new MySQLMaxValueIncrementer(ctx.getBean("dbcpDataSource",BasicDataSource.class),"","JOB_INSTANCE_ID");
		
		MapJobInstanceDao mapJobInstanceDao;
		
		jdbcJobInstanceDao=new JdbcJobInstanceDao();
		jdbcJobInstanceDao.setClobTypeToUse(0);
		jdbcJobInstanceDao.setJdbcTemplate(jdbcTemplate);
		jdbcJobInstanceDao.setJobIncrementer(mysqlIncrementer);
		jdbcJobInstanceDao.setTablePrefix(prefix);
	
		MapJobExecutionDao mapJobExecutionDao;
		
		jdbcJobExecutionDao=new JdbcJobExecutionDao();
		
		jdbcJobExecutionDao.setClobTypeToUse(0);
		jdbcJobExecutionDao.setExitMessageLength(0);
		jdbcJobExecutionDao.setJdbcTemplate(jdbcTemplate);
		jdbcJobExecutionDao.setJobExecutionIncrementer(mysqlIncrementer);
		jdbcJobExecutionDao.setTablePrefix(prefix);
	
		MapStepExecutionDao mapStepExecutionDao;
		
		jdbcStepExecutionDao=new JdbcStepExecutionDao();
		
		jdbcStepExecutionDao.setClobTypeToUse(0);
		jdbcStepExecutionDao.setExitMessageLength(0);
		jdbcStepExecutionDao.setJdbcTemplate(jdbcTemplate);
		jdbcStepExecutionDao.setStepExecutionIncrementer(mysqlIncrementer);
		jdbcStepExecutionDao.setTablePrefix(prefix);
	
		MapExecutionContextDao mapExecutionContextDao;
		
		jdbcExecutionContextDao=new JdbcExecutionContextDao();
		
		jdbcExecutionContextDao.setClobTypeToUse(0);
		jdbcExecutionContextDao.setJdbcTemplate(jdbcTemplate);
		jdbcExecutionContextDao.setLobHandler(null);
		jdbcExecutionContextDao.setSerializer(executionContextSerializer);
		jdbcExecutionContextDao.setShortContextLength(0);
		jdbcExecutionContextDao.setShortContextLength(0);
		jdbcExecutionContextDao.setTablePrefix(prefix);
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
		Job job1=jobBuilderFactory.get("footballJob").start(st1)
				.next(st2)
				.build();
		
		simpleJobLauncher.run(job1, new JobParameters());
		
	}
}
