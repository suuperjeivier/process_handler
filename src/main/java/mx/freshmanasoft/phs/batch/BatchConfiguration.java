package mx.freshmanasoft.phs.batch;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Locale;

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
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DefaultFieldSetFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;

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
	private final Path rootLocation;

	@Autowired
	public BatchConfiguration(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}   

	// tag::readerwriterprocessor[]
	@Bean
	@StepScope
	public FlatFileItemReader<BankAction> reader(@Value("#{jobParameters['fileName']}") final String actionsFileName) {
		try {
			Locale locale = Locale.US; // set your locale
			DefaultFieldSetFactory fieldSetFactory = new DefaultFieldSetFactory();


			fieldSetFactory.setNumberFormat(NumberFormat.getCurrencyInstance(locale));
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			fieldSetFactory.setDateFormat(dateFormat);
			return new FlatFileItemReaderBuilder<BankAction>()
					.linesToSkip(1)
					.encoding("UTF-8")
					.name("actionItemReader")
					.resource(new UrlResource(rootLocation.resolve(actionsFileName).toUri()))
					.delimited()
					.fieldSetFactory(fieldSetFactory)							
					.names(new String[]{"institucion", "instrumento", "Cusip", "ISIN_Serie",	"Sec_Id",	 "Titulos", "Unit_Cost", "Valor_a_costo", "Market_Price", "Valor_de_mercado", "Plusv_minusv_acumulado", "Int_Devengado",
							"T_C", "mxn_dls", "Registro_Contable", "Valor", "Moneda_Original", "Fecha_Inicio", "Fecha_Final", "Saldo_Inicial", "Depositos", "Dividendos", "Intereses_Devengado", "Intereses_Cobrado", "Cambio_Mxn_Vs_Dis",
							"Valuacion_Al_Cierre", "Cancelacion_De_Valuacion_X_Vta", "Cancelacion_De_Interes_Devengado", "Compras", "Ventas", "Utilidad_Perdida", "Retiros", "Gastos", "Impuestos", "Neto_Mov",	"Saldo_Final", "Dls_Al_Inicio",
							"TC_ Inicial", "TC_Final", "Valuacion_Dls_Al_Inicio", "Valuacion_Dls_Al_Final", "Utilidad_Perdida_Por_Valuacion", "Utilidad_Perdida_Por_Valuacion_Miles"})

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
	public BankActionItemProcessor processor(@Value("#{jobParameters['accountId']}") final String accountId) {
		Long longId = Long.parseLong(accountId);
		return new BankActionItemProcessor(longId);
	}

	@Bean
	public JdbcBatchItemWriter<BankAction> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<BankAction>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO bank_action ("
						+ "`accounting_record`, `acquisition_date`, `cambio_mxn_vs_dis`, "
						+ "`cancelacion_de_interes_devengado`, `cancelacion_de_valuacionxvta`, `compras`, `cusip`,"
						+ " `depositos`, `dividendos`, `dls_al_inicio`, `fecha_final`, `fecha_inicio`, `gastos`, `impuestos`,"
						+ " `institucion`, `instrumento`, `int_devengado`, `intereses_cobrado`, `intereses_devengado`, `isin_serie`, "
						+ "`market_price`, `moneda_original`, `mxn_dls`, `name`, `neto_mov`, `original_coin`, `plusv_minusv_acumulado`,"
						+ " `quantity`, `registro_contable`, `retiros`, `saldo_final`, `saldo_inicial`, `sec_id`, `security`, `status`,"
						+ " `tc`, `tc_final`, `tc_inicial`, `titulos`, `unit_cost`, `utilidad_perdida`, `utilidad_perdida_por_valuacion`,"
						+ " `utilidad_perdida_por_valuacion_miles`, `valor`, `valoracosto`, `valor_de_mercado`, `valuacion_al_cierre`, "
						+ "`valuacion_dls_al_final`, `valuacion_dls_al_inicio`, `value`, `value_at_cost`, `ventas`, `fk_id_bank_account`)"
						+ " VALUES ("+
						":accountingRecord,\r\n" +
						":acquisitionDate,\r\n" + 
						":cambioMxnVsDis,\r\n" + 
						":cancelacionDeInteresDevengado,\r\n" + 
						":cancelacionDeValuacionXVta,\r\n" + 
						":compras,\r\n" +
						":cusip,\r\n" +
						":depositos,\r\n" + 
						":dividendos,\r\n" + 
						":dlsAlInicio,\r\n" + 
						":fechaFinal,\r\n" + 
						":fechaInicio,\r\n" + 
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
						":netoMov,\r\n" + 
						":originalCoin,\r\n" + 
						":plusvMinusvAcumulado,\r\n" + 
						":quantity,\r\n" + 
						":registroContable,\r\n" + 
						":retiros,\r\n" + 
						":saldoFinal,\r\n" + 
						":saldoInicial,\r\n" + 
						":secId,\r\n" + 
						":security,\r\n" + 
						":status,\r\n" + 
						":tC,\r\n" + 
						":tcFinal,\r\n" + 
						":tcInicial,\r\n" + 
						":titulos,\r\n" + 
						":unitCost,\r\n" + 
						":utilidadPerdida,\r\n" + 
						":utilidadPerdidaPorValuacion,\r\n" + 
						":utilidadPerdidaPorValuacionMiles,\r\n" + 
						":valor,\r\n" + 
						":valorACosto,\r\n" + 
						":valorDeMercado,\r\n" + 
						":valuacionAlCierre,\r\n" + 
						":valuacionDlsAlFinal,\r\n" + 
						":valuacionDlsAlInicio,\r\n" + 
						"0,\r\n" + 
						":valorACosto,\r\n" + 
						":ventas,\r\n" + 
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
				.processor(processor(_ACCOUNT_ID))
				.writer(writer)
				.build();
	}
	// end::jobstep[]
}