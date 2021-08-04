package genx.uppcl.ewallet.urban.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import genx.uppcl.ewallet.urban.dto.AccountBillTypeParameter;
import genx.uppcl.ewallet.urban.dto.WSOResponse;
import genx.uppcl.ewallet.urban.externalapi.BillPaymentDetailsConstant;
import genx.uppcl.ewallet.urban.repository.BillPaymentDetailsRepository;
import genx.uppcl.ewallet.urban.repository.ConnectionRepository;
import genx.uppcl.ewallet.urban.service.BillPaymentDetailsServiceImpl;

@RestController
@RequestMapping("/connection/v1/bill")
public class BillPaymentDetailsController {
	private final Logger logger = LoggerFactory.getLogger(BillPaymentDetailsController.class);
	@Autowired
	BillPaymentDetailsServiceImpl billPaymentDetails;
	
	@Autowired
	BillPaymentDetailsRepository billPaymentDetailsRepository;

	@Autowired	
	ConnectionRepository connectionRepository;
	
	@PostMapping("/featchAndUpdateDetails")
	public void featchAndUpdateDetails(@RequestBody AccountBillTypeParameter data) throws JsonProcessingException {

		logger.info("getdetails======================== ");
		logger.info("account ===========   " + data.getAccountNumber() + "     " + data.getBillType());
		if (data.getBillType().equals("RAPDRP")) {
			logger.info("inside if ======================");
			billPaymentDetails.callCcbBillFetchApi(data.getAccountNumber());
		}

		else if (data.getBillType().equals("NON_RAPDRP")) {
			/*List<Map<String,String>> accountS = connectionRepository.districtRural("", "", "", "","", "IRRIGATION - State Tube Well");
	        int i=0;
			for (Map<String, String> map : accountS) {
			  String accountNumber = map.get("consumerNumber");
			  i =i+1;
			  System.out.println("inside else ===================="+ accountNumber +" ==" + i);
			  billPaymentDetails.callMpowerBillFetchApiRural(accountNumber);
			}*/
			billPaymentDetails.callMpowerBillFetchApiRural(data.getAccountNumber());
		}
		
	}
	
}
