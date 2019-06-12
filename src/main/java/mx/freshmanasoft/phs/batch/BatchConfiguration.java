package mx.freshmanasoft.phs.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import mx.freshmanasoft.phs.entity.BankAction;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    // tag::readerwriterprocessor[]
    @Bean
    public FlatFileItemReader<BankAction> reader() {
        return new FlatFileItemReaderBuilder<BankAction>()
        		.linesToSkip(1)
        		.encoding("UTF-8")
        		.name("personItemReader")
        		.resource(new ClassPathResource("static/data/sample-data.csv"))
        		.delimited()
        		.names(new String[]{"name", "cusip"})
        		.fieldSetMapper(new BeanWrapperFieldSetMapper<BankAction>() {{
                setTargetType(BankAction.class);
        		}})
            .build();
    }

    @Bean
    public BankActionItemProcessor processor() {
        return new BankActionItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<BankAction> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<BankAction>()
            .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
            .sql("INSERT INTO bank_action (name, cusip, status) VALUES (:name, :cusip, 0)")
            .dataSource(dataSource)
            .build();
    }
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job importActionsJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importActionsJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(step1)
            .end()
            .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<BankAction> writer) {
        return stepBuilderFactory.get("step1")
            .<BankAction, BankAction> chunk(10)
            .reader(reader())
            .processor(processor())
            .writer(writer)
            .build();
    }
    // end::jobstep[]
}