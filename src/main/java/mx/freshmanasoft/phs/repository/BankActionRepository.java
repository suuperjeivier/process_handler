package mx.freshmanasoft.phs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import mx.freshmanasoft.phs.entity.BankAction;

public interface BankActionRepository extends CrudRepository<BankAction, Long>{
	Iterable<BankAction> findByStatus(int status);

	@Query(
			value = "SELECT * FROM bank_action a WHERE a.status = 1 AND a.fk_id_bank_account = :accountId GROUP BY a.cusip, a.isin_serie, a.sec_id", 
			nativeQuery = true)
	Iterable<BankAction> findByAccountId(@Param("accountId") Long accountId);
	
	@Query(
			value = "SELECT * FROM bank_action a WHERE a.status = 1 GROUP BY a.cusip, a.isin_serie, a.sec_id", 
			nativeQuery = true)
	Iterable<BankAction> findAllGrouped();	
	
	Iterable<BankAction> findByCusipOrIsinSerieOrSecId(String cusip, String isinSerie, String secId);
	
	Iterable<BankAction> findAllByAccountIdAndCusipIsNullAndIsinSerieIsNullAndSecIdIsNullOrderByFechaInicioReal(@Param("accountId") Long accountId);

	List<BankAction> findByCusipAndIsinSerieNotNullAndSecIdNotNullOrderByFechaInicioReal(String cusip);

	List<BankAction> findByIsinSerieAndCusipNotNullAndSecIdNotNullOrderByFechaInicioReal(String isinSerie);

	List<BankAction> findBySecIdAndCusipNotNullAndIsinSerieNotNullOrderByFechaInicioReal(String isinSerie);

	List<BankAction> findByCusipAndIsinSerieIsNullAndSecIdIsNullOrderByFechaInicioReal(String cusip);

	List<BankAction> findByIsinSerieAndCusipIsNullAndSecIdIsNullOrderByFechaInicioReal(String isinSerie);

	List<BankAction> findBySecIdAndCusipIsNullAndIsinSerieIsNullOrderByFechaInicioReal(String secId);

	List<BankAction> findByCusipAndIsinSerieIsNullAndSecIdOrderByFechaInicioReal(String cusip, String secId);

	List<BankAction> findByCusipAndIsinSerieAndSecIdIsNullOrderByFechaInicioReal(String cusip, String secId);

	List<BankAction> findByCusipAndSecIdAndIsinSerieIsNullOrderByFechaInicioReal(String cusip, String secId);

	List<BankAction> findByIsinSerieAndSecIdAndCusipIsNullOrderByFechaInicioReal(String isinSerie, String secId);

	List<BankAction> findByCusipAndSecIdAndIsinSerieOrderByFechaInicioReal(String cusip, String secId, String isinSerie);

}
