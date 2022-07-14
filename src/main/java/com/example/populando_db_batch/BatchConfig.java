package com.example.populando_db_batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class BatchConfig {

	@Autowired
	JobBuilderFactory jobFactory;
	
	@Autowired
	StepBuilderFactory stepFactory;
	
    @Value("teste.txt")
    private String inputCsv;
    
	@Bean
	public FlatFileItemReader<String> reader() {
		FlatFileItemReader<String> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new ClassPathResource("teste.txt"));
        itemReader.setLineMapper(new PassThroughLineMapper());
        
        return itemReader;
	}
	
	@Bean
	public TextProcessor getProcessor() {
		return new TextProcessor();
	}
	
	@Bean
	public Job chunkJob() {
		return jobFactory.get("chunkJob")
	      .incrementer(new RunIdIncrementer())
	      .flow(step1())
	      .end()
	      .build();
	}
	
	@Bean
	public Step step1() {
		return stepFactory.get("step1").<String,String> chunk(1).reader(reader()).processor(getProcessor()).writer(getWriter()).build();
	}

//	private Tasklet taskletHelloWorld() {
//		return (new Tasklet() {
//
//			@Override
//			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//				
//				System.out.println("Hello World !");
//				return RepeatStatus.FINISHED;
//			}
//			
//		});
//	}
	
	@Bean
	public CustomItemWriter getWriter() {
		return new CustomItemWriter(); 
	}
	
}
