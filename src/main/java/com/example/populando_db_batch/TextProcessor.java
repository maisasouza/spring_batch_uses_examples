package com.example.populando_db_batch;

import org.springframework.batch.item.ItemProcessor;

public class TextProcessor implements ItemProcessor<String, String> {

	@Override
	public String process(String item) throws Exception {
		
		return item.concat("-passeinoprocessor");
	}

}
