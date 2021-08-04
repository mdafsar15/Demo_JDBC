package genx.uppcl.ewallet.urban.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import genx.uppcl.ewallet.urban.model.BillPaymentDetailsModel;
import genx.uppcl.ewallet.urban.model.ConsumerBillDetails;



public interface BillPaymentDetailsRepository extends  JpaRepository<BillPaymentDetailsModel, Long>{
	
	@Procedure(procedureName = "ruralStatus")
	public List<Object[]> ruralStatus(String AccountNumber);

	
	//public EmployeeDetail updateUser(long employeeDetailId , EmployeeDetail empData);
}
