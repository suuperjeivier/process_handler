package mx.freshmanasoft.phs;

import java.time.LocalDateTime;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import mx.freshmanasoft.phs.entity.Loggin;
import mx.freshmanasoft.phs.repository.LogginRepository;

@Configuration
@Aspect
public class AspectLoggin {
	
	
	@Autowired
	private LogginRepository repository;
	
	private Loggin loggin = null;
	private String logginUser;
	private String logoutUser;
	@After("execution(* mx.freshmanasoft.phs.controller.BankController.*(..))")
	public void logBank(JoinPoint point) {
		
		loggin = new Loggin();
		loggin.setMethod(point.getSignature().getName());
		loggin.setEntity("Bank");
		loggin.setDate(LocalDateTime.now().toString());
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
		loggin.setEntity("Bank Account");
		loggin.setDate(LocalDateTime.now().toString());
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
		loggin.setEntity("Bank Action");
		loggin.setDate(LocalDateTime.now().toString()); 
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
		loggin.setEntity("Company");
		loggin.setDate(LocalDateTime.now().toString()); 
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
		loggin.setEntity("Main");
		loggin.setDate(LocalDateTime.now().toString()); 
		loggin.setUser(logginUser);
		String params = "";
		for(Object j : point.getArgs()) {
			params += j.toString(); 
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
			loggin.setEntity("Main");
			loggin.setDate(LocalDateTime.now().toString()); 
			loggin.setUser(logginUser);
			loggin.setData("security");
			repository.save(loggin);
	    }
	 @Before("execution(* org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler.logout(..))")
	 public void beforeLogout(JoinPoint joinPoint) throws Throwable {      
		 logoutUser = SecurityContextHolder.getContext().getAuthentication().getName();
	     System.out.println(
	                 ">>> Aspect : User " +  logoutUser + " successfully logged out.");
	     
	        LocalDateTime ldt = LocalDateTime.now();
			ZonedDateTime zdt = ldt.atZone(ZoneId.of("Mexico/General")); 
			LocalDateTime utc = zdt.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
			loggin = new Loggin();
			loggin.setMethod(joinPoint.getSignature().getName());
			loggin.setEntity("Main");
			loggin.setDate(utc); 
			loggin.setUser(logoutUser);
			repository.save(loggin);
	 }
}
