package mx.freshmanasoft.phs.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.freshmanasoft.phs.batch.BatchConfiguration;

@RestController
@RequestMapping(path="batch")
public class BatchController {
	
	@Autowired
	JobLauncher jobLauncher;
	@Autowired
    Job job;

	@Autowired
	BatchConfiguration batchConfig;

	@PostMapping("/file/{fileName}")
	public void post(@PathVariable("fileName") final String fileName, @RequestParam("accountId") final String accountId) throws Exception{
		//TODO call to this API
		//CountDownLatch latch = new CountDownLatch(1);
		
		JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .addString("fileName", fileName)
                .addString("accountId", accountId)
                .toJobParameters();
    jobLauncher.run(job, params);
		
	}


}
