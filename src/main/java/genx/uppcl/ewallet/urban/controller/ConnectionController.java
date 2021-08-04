package genx.uppcl.ewallet.urban.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import genx.uppcl.ewallet.urban.model.ConsumerBillDetails;
import genx.uppcl.ewallet.urban.model.ConsumerBillPostDetails;
import genx.uppcl.ewallet.urban.model.GovOrganizationDetails;
import genx.uppcl.ewallet.urban.repository.ConnectionRepository;
import genx.uppcl.ewallet.urban.repository.GovOrganizationRepository;

@RestController
@RequestMapping("/connection/v1")
public class ConnectionController {

	private static final Logger log = LoggerFactory.getLogger(ConnectionController.class);

	@Autowired
	private ConnectionRepository connectionRepository;

	@Autowired
	private GovOrganizationRepository govOrganizationRepository;

	@GetMapping("/getConnection")
	public ResponseEntity<List<ConsumerBillDetails>> getUrbanConnection(
			@RequestParam(required = true, name = "govtName") String govtName,
			@RequestParam(required = false, name = "discom") String discom,
			@RequestParam(required = false, name = "divisionCode") String divisionCode,
			@RequestParam(required = false, name = "divisionName") String divisionName,
			@RequestParam(required = false, name = "accountId") String consumerNumber) throws SQLException {

		log.info("getConnection request :- govtName= " + govtName + ", discom=" + discom + ", divisionCode="
				+ divisionCode + ", divisionName=" + divisionName);

		List<ConsumerBillDetails> urbanConnection = new ArrayList<ConsumerBillDetails>();

		String approvalStatus = "REJECT";
		if (govtName == null && discom == null && divisionName == null && divisionCode == null
				&& consumerNumber == null) {
			urbanConnection = connectionRepository.findAll();
		} else if (govtName != null && discom == null && divisionName == null && divisionCode == null
				&& consumerNumber == null) {
			urbanConnection = connectionRepository.findByOrganizationNameAndApprovalStatusNotIn(govtName,
					approvalStatus);
		} else if (govtName != null && discom != null && divisionName == null && divisionCode == null
				&& consumerNumber == null) {
			urbanConnection = connectionRepository.findByOrganizationNameAndDiscomAndApprovalStatusNotIn(govtName,
					discom, approvalStatus);
		} else if (govtName != null && discom != null && divisionName != null && divisionCode == null
				&& consumerNumber == null) {
			urbanConnection = connectionRepository.findByOrganizationNameAndDiscomAndDivisionNameAndApprovalStatusNotIn(
					govtName, discom, divisionName, approvalStatus);
		} else if (govtName != null && discom != null && divisionCode != null && consumerNumber == null) {
			urbanConnection = connectionRepository.findByOrganizationNameAndDiscomAndDivisionCodeAndApprovalStatusNotIn(
					govtName, discom, divisionCode, approvalStatus);
		} else if (govtName != null && discom != null && divisionCode != null && consumerNumber == null) {
			urbanConnection = connectionRepository.findByOrganizationNameAndDiscomAndDivisionCodeAndApprovalStatusNotIn(
					govtName, discom, divisionCode, approvalStatus);
		} else if (govtName != null && discom != null && divisionName != null && divisionCode == null
				&& consumerNumber != null) {
			urbanConnection = connectionRepository
					.findByOrganizationNameAndDiscomAndDivisionNameAndConsumerNumberAndApprovalStatusNotIn(govtName,
							discom, divisionName, consumerNumber, approvalStatus);
		} else if (govtName != null && discom == null && divisionName != null && consumerNumber == null) {
			urbanConnection = connectionRepository.findByOrganizationNameAndDivisionNameAndApprovalStatusNotIn(govtName,
					divisionName, approvalStatus);
		} else if (govtName != null && discom == null && divisionName == null && consumerNumber != null) {
			urbanConnection = connectionRepository.findByOrganizationNameAndConsumerNumberAndApprovalStatusNotIn(
					govtName, consumerNumber, approvalStatus);
		} else if (govtName != null && discom == null && divisionName != null && consumerNumber != null) {
			urbanConnection = connectionRepository
					.findByOrganizationNameAndDivisionNameAndConsumerNumberAndApprovalStatusNotIn(govtName,
							divisionName, consumerNumber, approvalStatus);
		} else if (govtName != null && discom != null && divisionName == null && consumerNumber != null) {
			urbanConnection = connectionRepository
					.findByOrganizationNameAndDiscomAndConsumerNumberAndApprovalStatusNotIn(govtName, discom,
							consumerNumber, approvalStatus);
		}
		log.info("getConnection  Response: -->" + urbanConnection.toString());
		return new ResponseEntity<List<ConsumerBillDetails>>(urbanConnection, HttpStatus.OK);
	}

	@GetMapping("/getGovOrganization")
	public ResponseEntity<List<GovOrganizationDetails>> getGovOrganization(
			@RequestParam(required = false, name = "govCode") String OrganizationCode,
			@RequestParam(required = false, name = "govName") String OrganizationName) throws SQLException {
		log.info("getGovOrganization request ");
		List<GovOrganizationDetails> govOrganizationDetails = new ArrayList<GovOrganizationDetails>();

		if (OrganizationCode == null && OrganizationName == null) {
			govOrganizationDetails = govOrganizationRepository.findAll();
		} else if (OrganizationCode != null && OrganizationName == null) {
			govOrganizationDetails = govOrganizationRepository.findByOrganizationCode(OrganizationCode);
		} else if (OrganizationCode == null && OrganizationName != null) {
			govOrganizationDetails = govOrganizationRepository.findByOrganizationName(OrganizationName);
		} else if (OrganizationCode != null && OrganizationName != null) {
			govOrganizationDetails = govOrganizationRepository
					.findByOrganizationCodeAndOrganizationName(OrganizationCode, OrganizationName);
		}

		log.info("getGovOrganization  Response: -->" + govOrganizationDetails.toString());
		return new ResponseEntity<List<GovOrganizationDetails>>(govOrganizationDetails, HttpStatus.OK);
	}

	@PutMapping("/updateApprovalStatus/{consumerNumber}")
	public ResponseEntity<?> updateApprovalStatus(@RequestBody ConsumerBillDetails consumerBillDetails,
			@PathVariable("consumerNumber") String consumerNumber) {
		connectionRepository.updateApprovalStatus(consumerNumber, consumerBillDetails.getApprovalStatus());
		JSONObject object = new JSONObject();
		object.put("message",
				"Update Approval Status in " + consumerNumber + " To " + consumerBillDetails.getApprovalStatus());
		return ResponseEntity.ok(object.toString());
	}

	@GetMapping("/getRuralConnection")
	public ResponseEntity<Map<String, Object>> getRuralConnection(
			@RequestParam(required = true, name = "govtName") String govtName,
			@RequestParam(required = false, name = "discom") String discom,
			@RequestParam(required = false, name = "divisionCode") String divisionCode,
			@RequestParam(required = false, name = "divisionName") String divisionName,
			@RequestParam(required = false, name = "accountId") String consumerNumber,
			@RequestParam(required = false, name = "districtName") String districtName,
			@RequestParam(required = false, name = "pageNo") int pageNo,
			@RequestParam(required = false, name = "showNumberOfRec") int showNumberOfRec,
			@RequestParam(required = false, name = "approvalStatus") String approvalStatus) throws SQLException {

		log.info("getConnection request :- govtName= " + govtName + ", discom=" + discom + ", divisionCode="
				+ divisionCode + ", divisionName=" + divisionName);
		
		List<Map<String, String>> urbanConnection = new ArrayList<Map<String, String>>();
		
		Map<String, Object> count = new HashMap<String, Object>();
		    
		urbanConnection = connectionRepository.getLimitForConsumerBill(consumerNumber, discom, districtName, divisionCode,
				divisionName, govtName,pageNo,showNumberOfRec,approvalStatus);

		count = connectionRepository.getCountForConsumerBill(consumerNumber, discom, districtName, divisionCode,
				divisionName, govtName,approvalStatus);
		
		Map<String, Object> map = new HashMap<String,Object>();
	    map.put("count", count.get("cnt"));
	    map.put("payableAmount",count.get("payableAmount"));
	    map.put("Result", urbanConnection);
		    
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	
	@GetMapping("/getRuralConnectionDropdown")
	public ResponseEntity<List<Map<String, String>>> getRuralConnection(
			@RequestParam(required = true, name = "govtName") String govtName,
			@RequestParam(required = false, name = "discom") String discom,
			@RequestParam(required = false, name = "divisionCode") String divisionCode,
			@RequestParam(required = false, name = "divisionName") String divisionName,
			@RequestParam(required = false, name = "accountId") String consumerNumber,
			@RequestParam(required = false, name = "districtName") String districtName)         throws SQLException {

		log.info("getConnection request :- govtName= " + govtName + ", discom=" + discom + ", divisionCode="
				+ divisionCode + ", divisionName=" + divisionName);
		
		List<Map<String, String>> urbanConnection = new ArrayList<Map<String, String>>();

		urbanConnection = connectionRepository.getRuralConnectionDropdown(consumerNumber, discom, districtName, divisionCode,
				divisionName, govtName);

		return new ResponseEntity<List<Map<String, String>>>(urbanConnection, HttpStatus.OK);
	}

	@PostMapping("/saveConnectionBill")
	public ResponseEntity<Map<String, String>> saveConnectionBill(
			@RequestBody ConsumerBillPostDetails consumerBillPostDetails) throws SQLException, JsonProcessingException {
		 ObjectMapper mapper = new ObjectMapper();
	      //Converting the Object to JSONString
	      String billPostPayLoad = mapper.writeValueAsString(consumerBillPostDetails.getBillPostPayload());
	      System.out.println(billPostPayLoad);
		log.info("saveConnectionBill request :- BillingType= " + consumerBillPostDetails.getBillingType() + ", PollUrl="
				+ consumerBillPostDetails.getPollUrl() + ", ConsumerNumber="
				+ consumerBillPostDetails.getConsumerNumber() + ", billNumber="
				+ consumerBillPostDetails.getBillNumber() 
				+ " BillPostPayload= "+ consumerBillPostDetails.getBillPostPayload() 
				+ " GovtName = "
				+ consumerBillPostDetails.getGovtName());

		Map<String, String> urbanConnection = new HashMap<String, String>();

		urbanConnection = connectionRepository.saveConnectionBill(consumerBillPostDetails.getBillingType(),
				consumerBillPostDetails.getPollUrl(), consumerBillPostDetails.getConsumerNumber(),
				consumerBillPostDetails.getBillNumber(),  billPostPayLoad,
				consumerBillPostDetails.getGovtName());
		Map<String, String> urbanConnection1 = new HashMap<String, String>();
		urbanConnection1.put("message", "SUCCESS");
		return new ResponseEntity<Map<String, String>>(urbanConnection1, HttpStatus.OK);

	}
}
