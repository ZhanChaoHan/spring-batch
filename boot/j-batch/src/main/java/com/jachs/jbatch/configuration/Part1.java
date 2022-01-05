package com.jachs.jbatch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * 
 * @author zhanchaohan
 *
 */
@Configuration
public class Part1 {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
    public Step step() {
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution,
                                                ChunkContext chunkContext) {
                        System.out.println("Hello, World!");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
	 public Step step1() {
		 return stepBuilderFactory.get("step2").tasklet(new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				 System.out.println("Hello, Batch!");
				return RepeatStatus.FINISHED;
			}
		}).build();
	 }
    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("job")
                .start(step())
                .next(step1())
                .build();
    }
	
}
