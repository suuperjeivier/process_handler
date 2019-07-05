package mx.freshmanasoft.phs.batch;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.DefaultFieldSetFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import mx.freshmanasoft.phs.config.properties.StorageProperties;
import mx.freshmanasoft.phs.entity.BankAction;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	private final static Logger logger = LoggerFactory.getLogger(BatchConfiguration.class);

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	private static final String _FILE_NAME = null;
	private static final String _ACCOUNT_ID = null;
	private static final String __SUB_ACCOUNT_ID = null;
	private final Path rootLocation;

	@Autowired
	public BatchConfiguration(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}   
	@InitBinder     
	public void initBinder(WebDataBinder binder){
	     binder.registerCustomEditor(       Date.class,     
	                         new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10));   
	}
	// tag::readerwriterprocessor[]
	@Bean
	@StepScope
	public FlatFileItemReader<BankAction> reader(@Value("#{jobParameters['fileName']}") final String actionsFileName) {
		try {
			Locale locale = Locale.US; // set your locale
			DefaultFieldSetFactory fieldSetFactory = new DefaultFieldSetFactory();

			TimeZone.setDefault(TimeZone.getTimeZone("GMT-5:00"));
			fieldSetFactory.setNumberFormat(NumberFormat.getCurrencyInstance(locale));
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", locale);  
			fieldSetFactory.setDateFormat(dateFormat);
			return new FlatFileItemReaderBuilder<BankAction>()
					.linesToSkip(1)
					.encoding("UTF-8")
					.name("actionItemReader")
					.resource(new UrlResource(rootLocation.resolve(actionsFileName).toUri()))
					.delimited()
					.fieldSetFactory(fieldSetFactory)	
					.delimiter(",")
					.includedFields(new Integer[] {0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 26, 27, 32, 33})//26
					.names(new String[]{
							"institucion",
							"instrumento",
							"Cusip",
							"ISIN_Serie",
							"Sec_Id",
							"Titulos",
							"Unit_Cost",							
							"Market_Price",
							"Valor_de_mercado",
							"Plusv_minusv_acumulado",
							"Int_Devengado",
							"T_C",
							"mxn_dls",
							"Registro_Contable",
							"Valor",
							"Moneda_Original",
							"Fecha_Inicio",
							"Fecha_Final",
							"Saldo_Inicial",
							"Depositos",
							"Dividendos",
							"Intereses_Devengado",
							"Intereses_Cobrado",							
							"Cancelacion_De_Valuacion_X_Vta",
							"Cancelacion_De_Interes_Devengado",
							"Gastos",
							"Impuestos"
							})
					.fieldSetMapper(new BeanWrapperFieldSetMapper<BankAction>() {{						
						setTargetType(BankAction.class);
					}})
					.build();
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Bean
	@StepScope
	public BankActionItemProcessor processor(@Value("#{jobParameters['accountId']}") final String accountId, @Value("#{jobParameters['subAccountId']}") final String subAccountId) {
		logger.debug("params form controller: " + accountId +  ", subAccountId: " + subAccountId);
		Long longId = Long.parseLong(accountId);
		int intsubAId = Integer.parseInt(subAccountId);
		return new BankActionItemProcessor(longId, intsubAId);
	}

	@Bean
	public JdbcBatchItemWriter<BankAction> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<BankAction>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO bank_action ("																
						+ "	`cancelacion_de_interes_devengado`,"
						+ " `cancelacion_de_valuacionxvta`,"						
						+ " `cusip`,"
						+ " `depositos`,"
						+ " `dividendos`,"						
						+ " `fecha_final`,"
						+ " `fecha_inicio`,"
						+ " `fecha_final_real`,"
						+ " `fecha_inicio_real`,"
						+ " `fecha_de_adquisicion`,"
						+ " `gastos`,"
						+ " `impuestos`,"
						+ " `institucion`,"
						+ " `instrumento`,"
						+ " `int_devengado`,"
						+ " `intereses_cobrado`,"
						+ " `intereses_devengado`,"
						+ " `isin_serie`, "
						+ "	`market_price`,"
						+ " `moneda_original`,"
						+ " `mxn_dls`,"
						+ " `name`,"					
						+ " `plusv_minusv_acumulado`,"						
						+ " `registro_contable`,"						
						+ " `saldo_inicial`,"
						+ " `sec_id`,"						
						+ " `status`,"
						+ " `tc`,"						
						+ " `titulos`,"
						+ " `unit_cost`,"						
						+ " `valor`,"						
						+ " `valor_de_mercado`, "	
						+ " `n_sub_account_type`, "						
						+ " `fk_id_bank_account`)"
						+ " VALUES ("+															
						":cancelacionDeInteresDevengado,\r\n" + 
						":cancelacionDeValuacionXVta,\r\n" + 						
						":cusip,\r\n" +
						":depositos,\r\n" + 
						":dividendos,\r\n" + 						
						":fechaFinal,\r\n" + 
						":fechaInicio,\r\n" + 
						":fechaFinalReal,\r\n" + 
						":fechaInicioReal,\r\n" +
						":fechaDeAdquisicion,\r\n" +
						":gastos,\r\n" + 
						":impuestos,\r\n" +
						":institucion,\r\n" +						
						":instrumento,\r\n" + 
						":intDevengado,\r\n" + 
						":interesesCobrado,\r\n" + 
						":interesesDevengado,\r\n" + 
						":isinSerie,\r\n" + 
						":marketPrice,\r\n" + 
						":monedaOriginal,\r\n" + 
						":mxnDls,\r\n" + 
						":name,\r\n" + 						
						":plusvMinusvAcumulado,\r\n" + 						
						":registroContable,\r\n" +						
						":saldoInicial,\r\n" + 
						":secId,\r\n" + 						
						":status,\r\n" + 
						":tC,\r\n" + 						
						":titulos,\r\n" + 
						":unitCost,\r\n" +						 
						":valor,\r\n" +
						":valorDeMercado,\r\n" +
						":subAccountType,\r\n" +
						":accountingRecord\r\n" +  
						")")
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
				.reader(reader(_FILE_NAME))
				.processor(processor(_ACCOUNT_ID, __SUB_ACCOUNT_ID))
				.writer(writer)
				.build();
	}
	// end::jobstep[]
}