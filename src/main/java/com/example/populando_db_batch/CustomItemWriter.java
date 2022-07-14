package com.example.populando_db_batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class CustomItemWriter implements ItemWriter<String> {

	@Override
	public void write(List<? extends String> items) throws Exception {
		for(String item : items) {
			System.out.println("Writer: " + item);
		}
	}


}
