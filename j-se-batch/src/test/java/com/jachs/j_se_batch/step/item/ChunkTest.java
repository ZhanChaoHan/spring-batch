package com.jachs.j_se_batch.step.item;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.step.item.Chunk;

import com.jachs.j_se_batch.entity.Dog;

/**
 * 
 * @author zhanchaohan
 *
 */
public class ChunkTest {
	private Chunk chunk;
	
	@Before
	public void init() {
		chunk=new Chunk<Dog>();
		
		List<Dog>logList=new ArrayList<Dog>();
		chunk=new Chunk<Dog>(logList);
	}
	@Test
	public void test1() {
		
	}
}
