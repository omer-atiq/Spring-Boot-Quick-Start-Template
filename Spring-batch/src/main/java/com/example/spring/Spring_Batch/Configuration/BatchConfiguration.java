package com.example.spring.Spring_Batch.Configuration;

import com.example.spring.Spring_Batch.Models.User;
import com.example.spring.Spring_Batch.Processor.UserProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfiguration {

    @Bean
    @BatchDataSource
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:org/springframework/batch/core/schema-h2.sql")
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public FlatFileItemReader<User> reader() {
        System.out.println("Hello bellow");
        System.out.println("CSV exists? " + new ClassPathResource("sample-data.csv").exists());

        return new FlatFileItemReaderBuilder<User>()
                .name("userReader")
                .resource(new ClassPathResource("sample-data.csv"))
                .delimited()
                .names("id", "name")
                .targetType(User.class)
                .build();
    }

    @Bean
    public UserProcessor processor() {
        return new UserProcessor();
    }

    @Bean
    public FlatFileItemWriter<User> writer() {
        return new FlatFileItemWriterBuilder<User>()
                .name("userWriter")
                .resource(new FileSystemResource("output-data.csv"))
                .delimited()
                .names("id", "name")
                .build();
    }

    @Bean
    public Step processDataStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                                FlatFileItemReader<User> reader, UserProcessor processor, FlatFileItemWriter<User> writer) {
        return new StepBuilder("processDataStep", jobRepository)
                .<User, User>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }



    @Bean
    public Job importUserJob(JobRepository jobRepository, Step processDataStep) {
        System.out.println(">>> CREATING JOB BEAN");
        return new JobBuilder("importUserJob", jobRepository)
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        System.out.println(">>> JOB STARTED");
                    }
                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        System.out.println(">>> JOB FINISHED: " + jobExecution.getStatus());
                    }
                })
                .start(processDataStep)
                .build();
    }
}