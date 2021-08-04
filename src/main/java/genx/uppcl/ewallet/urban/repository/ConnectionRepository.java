package genx.uppcl.ewallet.urban.repository;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import genx.uppcl.ewallet.urban.model.ConsumerBillDetails;

@Repository
public interface ConnectionRepository extends JpaRepository<ConsumerBillDetails, String> {

	List<ConsumerBillDetails> findAll();

	List<ConsumerBillDetails> findByOrganizationName(String govtName);

	List<ConsumerBillDetails> findByOrganizationNameAndDiscom(String govtName, String discom);

	List<ConsumerBillDetails> findByOrganizationNameAndDiscomAndDivisionName(String govtName, String discom,
			String divisionName);

	List<ConsumerBillDetails> findByOrganizationNameAndDiscomAndDivisionCode(String govtName, String discom,
			String divisionCode);

	@Procedure(procedureName = "updateApprovalStatus")
	public int updateApprovalStatus(String ConsumerNumber, String ApprovalStatus);

	List<ConsumerBillDetails> findByOrganizationNameAndDiscomAndDivisionNameAndConsumerNumber(String govtName,
			String discom, String divisionName, String consumerNumber);

	List<ConsumerBillDetails> findByOrganizationNameAndApprovalStatusNotIn(String govtName, String approvalStatus);

	List<ConsumerBillDetails> findByOrganizationNameAndDiscomAndDivisionNameAndApprovalStatusNotIn(String govtName,
			String discom, String divisionName, String approvalStatus);

	List<ConsumerBillDetails> findByOrganizationNameAndDiscomAndApprovalStatusNotIn(String govtName, String discom,
			String approvalStatus);

	List<ConsumerBillDetails> findByOrganizationNameAndDiscomAndDivisionCodeAndApprovalStatusNotIn(String govtName,
			String discom, String divisionCode, String approvalStatus);

	List<ConsumerBillDetails> findByOrganizationNameAndDiscomAndDivisionNameAndConsumerNumberAndApprovalStatusNotIn(
			String govtName, String discom, String divisionName, String consumerNumber, String approvalStatus);

	List<ConsumerBillDetails> findByOrganizationNameAndDivisionNameAndApprovalStatusNotIn(String govtName,
			String divisionName, String approvalStatus);

	List<ConsumerBillDetails> findByOrganizationNameAndConsumerNumberAndApprovalStatusNotIn(String govtName,
			String consumerNumber, String approvalStatus);

	List<ConsumerBillDetails> findByOrganizationNameAndDivisionNameAndConsumerNumberAndApprovalStatusNotIn(
			String govtName, String divisionName, String consumerNumber, String approvalStatus);

	List<ConsumerBillDetails> findByOrganizationNameAndDiscomAndConsumerNumberAndApprovalStatusNotIn(String govtName,
			String discom, String consumerNumber, String approvalStatus);
	
	@Query(nativeQuery = true, value="call getRuralConnectionDropdown(:consumerNumber,:discom,:districtName,:divisionCode,:divisionName,:govtName)")
	public List<Map<String,String>> getRuralConnectionDropdown(String consumerNumber,String discom,String districtName,String divisionCode, String divisionName,String govtName);
	
	@Query(nativeQuery = true, value="call getLimitForConsumerBill(:consumerNumber,:discom,:districtName,:divisionCode,:divisionName,:govtName,:pageNo,:showNumberOfRec,:approvalStatus)")
	public List<Map<String,String>> getLimitForConsumerBill(String consumerNumber,String discom,String districtName,String divisionCode, String divisionName,String govtName,int pageNo,int showNumberOfRec,String approvalStatus);
	
	@Query(nativeQuery = true, value="call getCountForConsumerBill(:consumerNumber,:discom,:districtName,:divisionCode,:divisionName,:govtName,:approvalStatus)")
	public Map<String,Object> getCountForConsumerBill(String consumerNumber,String discom,String districtName,String divisionCode, String divisionName,String govtName,String approvalStatus);
	
	@Query(nativeQuery = true, value="call saveConnectionBill(:billingType, :pollUrl, :consumerNumber, :billNumber, :billPostPayload, :govtName)")
	Map<String, String> saveConnectionBill(String billingType, String pollUrl, String consumerNumber, String billNumber,
			String billPostPayload, String govtName);
	

}
