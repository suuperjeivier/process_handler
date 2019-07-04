package mx.freshmanasoft.phs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.freshmanasoft.phs.entity.Loggin;
import mx.freshmanasoft.phs.service.LogService;

@RestController
@RequestMapping(path="log")
public class LogController {
	
	@Autowired
	private LogService service;
	
	@GetMapping
	public @ResponseBody Iterable<Loggin> getAllLog(){
		return service.fetch();
	}
}
