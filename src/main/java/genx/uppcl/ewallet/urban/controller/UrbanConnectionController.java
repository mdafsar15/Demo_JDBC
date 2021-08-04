package genx.uppcl.ewallet.urban.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import genx.uppcl.ewallet.urban.model.UrbanConnectionMaster;
import genx.uppcl.ewallet.urban.repository.UrbanConnectionRepository;
// NOT USE This Class
@RestController
@RequestMapping("/urban/v1")
public class UrbanConnectionController {
	
	private static final Logger log = LoggerFactory.getLogger(UrbanConnectionController.class);
	
	@Autowired
	private UrbanConnectionRepository urbanConnectionRepository;

	@GetMapping("/getUrbanConnection")
	public ResponseEntity<List<UrbanConnectionMaster>> getUrbanConnection(
			@RequestParam(required = true, name = "govtDept") String govtDept,
			@RequestParam(required = false, name = "discom") String discom,
			@RequestParam(required = false, name = "divCode") String divCode,
			@RequestParam(required = false, name = "accountId") String accountId,
			@RequestParam(required = false, name = "districtName") String districtName) throws SQLException {
		
		log.info("getUrbanConnection request : --> govtDept : "+ govtDept +", discom:  "+discom+", divCode : "+divCode +", accountId : "+ accountId +", districtName :"+districtName);		
		List<UrbanConnectionMaster> urbanConnection = new ArrayList<UrbanConnectionMaster>();
		if (discom == null && divCode == null && accountId == null && districtName == null) {
			urbanConnection = urbanConnectionRepository.findByGovtDept(govtDept);
		} else if (discom != null && divCode == null && accountId == null && districtName == null ) {
			urbanConnection = urbanConnectionRepository.findByGovtDeptAndDiscom(govtDept, discom);
		} else if (discom == null && divCode != null && accountId == null && districtName == null) {
			urbanConnection = urbanConnectionRepository.findByGovtDeptAndDivCode(govtDept, divCode);
		} else if (discom == null && divCode == null && accountId != null && districtName == null) {
			urbanConnection = urbanConnectionRepository.findByGovtDeptAndAccountId(govtDept, accountId);
		} else if (discom != null && divCode != null && accountId == null && districtName == null) {
			urbanConnection = urbanConnectionRepository.findByGovtDeptAndDiscomAndDivCode(govtDept, discom, divCode);
		} else if (discom == null && divCode != null && accountId != null && districtName == null) {
			urbanConnection = urbanConnectionRepository.findByGovtDeptAndDivCodeAndAccountId(govtDept, divCode, accountId);
		} else if (discom != null && divCode == null && accountId != null && districtName == null) {
			urbanConnection = urbanConnectionRepository.findByGovtDeptAndDiscomAndAccountId(govtDept, discom, accountId);
		} else if (discom != null && divCode != null && accountId != null && districtName == null) {
			urbanConnection = urbanConnectionRepository.findByGovtDeptAndDiscomAndDivCodeAndAccountId(govtDept, discom, divCode, accountId);
		} else if (discom == null && divCode == null && accountId == null && districtName != null) {
			urbanConnection = urbanConnectionRepository.findByGovtDeptAndDistrictName(govtDept, districtName);
		} else if (discom != null && divCode == null && accountId == null && districtName != null) {
			urbanConnection = urbanConnectionRepository.findByGovtDeptAndDiscomAndDistrictName(govtDept, discom, districtName);
		}  else if (discom == null && divCode == null && accountId != null && districtName != null) {
			urbanConnection = urbanConnectionRepository.findByGovtDeptAndDistrictNameAndAccountId(govtDept, districtName, accountId);
		} else if (discom != null && divCode == null && accountId != null && districtName != null) {
			urbanConnection = urbanConnectionRepository.findByGovtDeptAndDiscomAndDistrictNameAndAccountId(govtDept, discom, districtName, accountId);
		} else if (discom != null && divCode != null && accountId != null && districtName != null) {
			urbanConnection = urbanConnectionRepository.findByGovtDeptAndDiscomAndDistrictNameAndAccountIdAndDivCode(govtDept, discom, districtName, accountId,divCode);
		}		
		log.info("getUrbanConnection  Response: -->"+ urbanConnection.toString());
		return new ResponseEntity<List<UrbanConnectionMaster>>(urbanConnection, HttpStatus.OK);
	}
	
	/*@GetMapping("/getRuralConnection")
	public ResponseEntity<List<UrbanConnectionMaster>> getRuralConnection(
			@RequestParam(required = true, name = "govtDept") String govtDept,
			@RequestParam(required = false, name = "discom") String discom,
			@RequestParam(required = false, name = "divCode") String divCode,
			@RequestParam(required = false, name = "accountId") String accountId,
			@RequestParam(required = false, name = "districtName") String districtName) throws SQLException {
		
		log.info("getUrbanConnection request : --> govtDept : "+ govtDept +", discom:  "+discom+", divCode : "+divCode +", accountId : "+ accountId +", districtName :"+districtName);		
		List<UrbanConnectionMaster> urbanConnection = new ArrayList<UrbanConnectionMaster>();
		if (discom == null && divCode == null && accountId == null && districtName == null) {
			urbanConnection = urbanConnectionRepository.findByGovtDept(govtDept);
		} else if (discom != null && divCode == null && accountId == null && districtName == null ) {
			urbanConnection = urbanConnectionRepository.findByGovtDeptAndDiscom(govtDept, discom);
		} else if (discom == null && divCode != null && accountId == null && districtName == null) {
			urbanConnection = urbanConnectionRepository.findByGovtDeptAndDivCode(govtDept, divCode);
		} else if (discom == null && divCode == null && accountId != null && districtName == null) {
			urbanConnection = urbanConnectionRepository.findByGovtDeptAndAccountId(govtDept, accountId);
		} else if (discom != null && divCode != null && accountId == null && districtName == null) {
			urbanConnection = urbanConnectionRepository.findByGovtDeptAndDiscomAndDivCode(govtDept, discom, divCode);
		} else if (discom == null && divCode != null && accountId != null && districtName == null) {
			urbanConnection = urbanConnectionRepository.findByGovtDeptAndDivCodeAndAccountId(govtDept, divCode, accountId);
		} else if (discom != null && divCode == null && accountId != null && districtName == null) {
			urbanConnection = urbanConnectionRepository.findByGovtDeptAndDiscomAndAccountId(govtDept, discom, accountId);
		} else if (discom != null && divCode != null && accountId != null && districtName == null) {
			urbanConnection = urbanConnectionRepository.findByGovtDeptAndDiscomAndDivCodeAndAccountId(govtDept, discom, divCode, accountId);
		} else if (discom == null && divCode == null && accountId == null && districtName != null) {
			urbanConnection = urbanConnectionRepository.findByGovtDeptAndDistrictName(govtDept, districtName);
		} else if (discom != null && divCode == null && accountId == null && districtName != null) {
			urbanConnection = urbanConnectionRepository.findByGovtDeptAndDiscomAndDistrictName(govtDept, discom, districtName);
		}  else if (discom == null && divCode == null && accountId != null && districtName != null) {
			urbanConnection = urbanConnectionRepository.findByGovtDeptAndDistrictNameAndAccountId(govtDept, districtName, accountId);
		} else if (discom != null && divCode == null && accountId != null && districtName != null) {
			urbanConnection = urbanConnectionRepository.findByGovtDeptAndDiscomAndDistrictNameAndAccountId(govtDept, discom, districtName, accountId);
		} else if (discom != null && divCode != null && accountId != null && districtName != null) {
			urbanConnection = urbanConnectionRepository.findByGovtDeptAndDiscomAndDistrictNameAndAccountIdAndDivCode(govtDept, discom, districtName, accountId,divCode);
		}		
		log.info("getUrbanConnection  Response: -->"+ urbanConnection.toString());
		return new ResponseEntity<List<UrbanConnectionMaster>>(urbanConnection, HttpStatus.OK);
	}
	*/
}
