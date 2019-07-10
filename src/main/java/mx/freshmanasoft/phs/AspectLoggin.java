package mx.freshmanasoft.phs;


import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

import mx.freshmanasoft.phs.entity.Loggin;
import mx.freshmanasoft.phs.repository.LogginRepository;

@Configuration
@Aspect
public class AspectLoggin {
	
	
	@Autowired
	private LogginRepository repository;
	
	private Loggin loggin = null;
	private String logginUser;
	
	@After("execution(* mx.freshmanasoft.phs.controller.BankController.*(..))")
	public void logBank(JoinPoint point) {
		
		loggin = new Loggin();
		loggin.setMethod(point.getSignature().getName());
		loggin.setEntity("Bancos");
		loggin.setDate(new Date());
		String params = "";
		for(Object j : point.getArgs()) {
			params += j.toString(); 
		}
		loggin.setData(params);
		loggin.setUser(logginUser);
		repository.save(loggin);
	}
	
	@After("execution(* mx.freshmanasoft.phs.controller.BankAccountController.*(..))")
	public void logBankAccount(JoinPoint point) {
		
		loggin = new Loggin();
		loggin.setMethod(point.getSignature().getName());
		loggin.setEntity("Cuentas de inversión");
		loggin.setDate(new Date());
		String params = "";
		for(Object j : point.getArgs()) {
			params += j.toString(); 
		}
		loggin.setData(params);
		loggin.setUser(logginUser);
		repository.save(loggin);
	}
	
	@After("execution(* mx.freshmanasoft.phs.controller.BankActionController.*(..))")
	public void logBankAction(JoinPoint point) {
		
		loggin = new Loggin();
		loggin.setMethod(point.getSignature().getName());
		loggin.setEntity("Instrumentos de inversión");
		loggin.setDate(new Date()); 
		loggin.setUser(logginUser);
		String params = "";
		for(Object j : point.getArgs()) {
			params += j.toString(); 
		}
		loggin.setData(params);
		repository.save(loggin);
	}
	
	@After("execution(* mx.freshmanasoft.phs.controller.CompanyController.*(..))")
	public void logCompany(JoinPoint point) {
		
		loggin = new Loggin();
		loggin.setMethod(point.getSignature().getName());
		loggin.setEntity("Empresas");
		loggin.setDate(new Date()); 
		loggin.setUser(logginUser);
		String params = "";
		for(Object j : point.getArgs()) {
			params += j.toString(); 
		}
		loggin.setData(params);
		repository.save(loggin);
	}
	
	@After("execution(* mx.freshmanasoft.phs.controller.BankAccountTypeController.*(..))")
	public void logBankAccountType(JoinPoint point) {
		
		loggin = new Loggin();
		loggin.setMethod(point.getSignature().getName());
		loggin.setEntity("Tipos de cuenta");
		loggin.setDate(new Date()); 
		loggin.setUser(logginUser);
		String params = "";
		for(Object j : point.getArgs()) {
			params += j.toString(); 
		}
		loggin.setData(params);
		repository.save(loggin);
	}
	
	@After("execution(* mx.freshmanasoft.phs.controller.LogController.*(..))")
	public void logLog(JoinPoint point) {
		
		loggin = new Loggin();
		loggin.setMethod(point.getSignature().getName());
		loggin.setEntity("Log");
		loggin.setDate(new Date()); 
		loggin.setUser(logginUser);
		String params = "";
		for(Object j : point.getArgs()) {
			params += j.toString(); 
		}
		loggin.setData(params);
		repository.save(loggin);
	}
	
	@After("execution(* mx.freshmanasoft.phs.controller.BatchController.*(..))")
	public void logBatch(JoinPoint point) {
		
		loggin = new Loggin();
		loggin.setMethod(point.getSignature().getName());
		loggin.setEntity("Batch");
		loggin.setDate(new Date()); 
		loggin.setUser(logginUser);
		String params = "";
		for(Object j : point.getArgs()) {
			params += j.toString(); 
		}
		loggin.setData(params);
		repository.save(loggin);
	}
	
	@After("execution(* mx.freshmanasoft.phs.controller.MainController.*(..))")
	public void logMain(JoinPoint point) {
		loggin = new Loggin();
		loggin.setMethod(point.getSignature().getName());
		loggin.setEntity("Principal");
		loggin.setDate(new Date()); 
		loggin.setUser(logginUser);
		String params = "";
		if(!point.getSignature().getName().equals("logoutApp")) {
			for(Object j : point.getArgs()) {
				params += j.toString(); 
			}
		}
		loggin.setData(params);
		repository.save(loggin);
	}
	
	 @AfterReturning(pointcut="execution(* org.springframework.security.authentication.AuthenticationManager.authenticate(..))"
	            ,returning="result")
    public void after(JoinPoint point,Object result) throws Throwable {
	 	System.out.println(">>> LOGGIN USER: " + ((Authentication) result).getName());
        logginUser = ((Authentication) result).getName();
		loggin = new Loggin();
		loggin.setMethod(point.getSignature().getName());
		loggin.setEntity("Principal");
		loggin.setDate(new Date()); 
		loggin.setUser(logginUser);
		loggin.setData("");
		repository.save(loggin);
    }
}
