package com.jachs.spring.batch.configer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import com.jachs.spring.batch.entity.StudentDTO;

import java.util.List;

/**
 * This {@code ItemWriter} writes the received {@link StudentDTO} objects
 * to a log file. The goal of this component is to help us to demonstrate
 * that our item reader reads the correct information from the CSV file.
 */
public class LoggingItemWriter implements ItemWriter<StudentDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingItemWriter.class);

    @Override
    public void write(List<? extends StudentDTO> list) throws Exception {
        LOGGER.info("Writing students: {}", list);
    }
}
