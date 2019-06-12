package mx.freshmanasoft.phs.controller;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.freshmanasoft.phs.batch.BatchConfiguration;
import mx.freshmanasoft.phs.batch.JobCompletionNotificationListener;

@RestController
@RequestMapping(path="batch")
public class BatchController {
	@Autowired
	JobCompletionNotificationListener jobC;
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	BatchConfiguration batchConfig;

	@PostMapping
	public void post() throws Exception{
		//TODO call to this API
		//CountDownLatch latch = new CountDownLatch(1);
		

	}


}
