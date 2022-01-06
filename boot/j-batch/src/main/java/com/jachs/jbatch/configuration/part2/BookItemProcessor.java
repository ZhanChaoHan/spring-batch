package com.jachs.jbatch.configuration.part2;

import org.springframework.batch.item.ItemProcessor;

import com.jachs.jbatch.entity.Book;


/***
 * 
 * @author zhanchaohan
 *
 */
public class BookItemProcessor implements ItemProcessor<Book, Book>{

	@Override
	public Book process(Book item) throws Exception {
		
		return null;
	}

}
