package mx.freshmanasoft.phs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.freshmanasoft.phs.entity.BankAction;

public interface BankActionRepository extends CrudRepository<BankAction, Long>{
	Iterable<BankAction> findByStatus(int status);

	Iterable<BankAction> findByAccountId(Long accountId);

	Iterable<BankAction> findByCusipOrIsinSerieOrSecId(String cusip, String isinSerie, String secId);

	List<BankAction> findByCusipAndIsinSerieNotNullAndSecIdNotNull(String cusip);

	List<BankAction> findByIsinSerieAndCusipNotNullAndSecIdNotNull(String isinSerie);

	List<BankAction> findBySecIdAndCusipNotNullAndIsinSerieNotNull(String isinSerie);

	List<BankAction> findByCusipAndIsinSerieIsNullAndSecIdIsNull(String cusip);

	List<BankAction> findByIsinSerieAndCusipIsNullAndSecIdIsNull(String isinSerie);

	List<BankAction> findBySecIdAndCusipIsNullAndIsinSerieIsNull(String secId);

	List<BankAction> findByCusipAndIsinSerieIsNullAndSecId(String cusip, String secId);

	List<BankAction> findByCusipAndIsinSerieAndSecIdIsNull(String cusip, String secId);

	List<BankAction> findByCusipAndSecIdAndIsinSerieIsNull(String cusip, String secId);

	List<BankAction> findByIsinSerieAndSecIdAndCusipIsNull(String isinSerie, String secId);

	List<BankAction> findByCusipAndSecIdAndIsinSerie(String cusip, String secId, String isinSerie);

}
