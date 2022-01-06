package com.jachs.jbatch.configuration.part2;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.jachs.jbatch.entity.Book;

/***
 * 
 * @author zhanchaohan
 *
 */
@Configuration
public class Part2 {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public FlatFileItemReader<Book> reader() {
		return new FlatFileItemReaderBuilder<Book>()
			.name("bookReader")
			.resource(new ClassPathResource("file/book.txt"))
			.delimited()
			.names(new String[]{"name", "price"})
			.fieldSetMapper(new BeanWrapperFieldSetMapper<Book>() {{
				setTargetType(Book.class);
			}})
			.build();
	}
	@Bean
	public BookItemProcessor processor() {
		return new BookItemProcessor();
	}
	@Bean
	public FlatFileItemWriter<Book> writer() {
		return new FlatFileItemWriterBuilder().encoding("UTF-8")
				.resource(new ClassPathResource("file/book1.txt"))
				.lineAggregator(null)
				.build();
	}
	@Bean
	public Step step1(FlatFileItemWriter<Book> writer) {
		return stepBuilderFactory.get("step1")
			.<Book, Book> chunk(10)
			.reader(reader())
			.processor(processor())
			.writer(writer)
			.build();
	}
	@Bean
	public Job importUserJob(Step step1) {
		return jobBuilderFactory.get("importUserJob")
			.incrementer(new RunIdIncrementer())
			.flow(step1)
			.end()
			.build();
	}
}
