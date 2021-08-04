package genx.uppcl.ewallet.urban.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.transaction.Transactional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import genx.uppcl.ewallet.urban.dto.WSOResponse;
import genx.uppcl.ewallet.urban.enumeration.BillingType;
import genx.uppcl.ewallet.urban.externalapi.BillPaymentDetailsConstant;
import genx.uppcl.ewallet.urban.model.BillPaymentDetailsModel;
import genx.uppcl.ewallet.urban.repository.BillPaymentDetailsRepository;
import genx.uppcl.ewallet.urban.repository.ConnectionRepository;
import io.swagger.annotations.Authorization;

@Service
@Transactional
public class BillPaymentDetailsServiceImpl {
	BillPaymentDetailsModel model = new BillPaymentDetailsModel();
	private final Logger logger = LoggerFactory.getLogger(BillPaymentDetailsServiceImpl.class);

	@Autowired
	private BillPaymentDetailsRepository billPaymentRepo;

	@Autowired
	ConnectionRepository connectionRepository;
	
	WSOResponse tokenPass = new WSOResponse();
	
	public ResponseEntity<BillPaymentDetailsModel> callCcbBillFetchApi(String AccountNumber)
			throws JsonProcessingException {
		String json = null;
		Map<String, String> payload = new HashMap<>();
		payload.put("accountNo", AccountNumber);
		ObjectMapper objectMapper = new ObjectMapper();
		json = objectMapper.writeValueAsString(payload);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<String> requestEntity = new HttpEntity<>(json, requestHeaders);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> result = restTemplate.exchange(BillPaymentDetailsConstant.CCB_BILL_FETCH_API,
				HttpMethod.POST, requestEntity, String.class);
		JSONObject object = new JSONObject(result.getBody());

		logger.info("==============================  " + object.get("Body"));

		JSONObject objectPaymentDetailsResponse = object.getJSONObject("Body").getJSONObject("PaymentDetailsResponse");
		model.setConsumerNumber(objectPaymentDetailsResponse.getString("KNumber"));
		model.setDiscom(objectPaymentDetailsResponse.getString("Discom").split("-")[0]);
		model.setDivisionCode(objectPaymentDetailsResponse.getString("DivCode"));
		model.setDivisionName(objectPaymentDetailsResponse.getString("Division"));
		model.setBillNumber(objectPaymentDetailsResponse.getString("BillID"));
//		model.setBillAmount(objectPaymentDetailsResponse.getString("BillAmount"));
		//model.setSanctionedLoadInKW(objectPaymentDetailsResponse.getString("SanctionedLoadInKW"));
		String startDateString = objectPaymentDetailsResponse.getString("BillDueDate");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		System.out.println(LocalDate.parse(startDateString, formatter).format(formatter2));
		model.setBillDueDate(LocalDate.parse(startDateString, formatter).format(formatter2));
		model.setConsumerName(objectPaymentDetailsResponse.getString("ConsumerName"));
		model.setAccountInfo(objectPaymentDetailsResponse.getString("AccountInfo"));
		model.setMobileNumber(objectPaymentDetailsResponse.getString("MobileNumber"));
		startDateString = objectPaymentDetailsResponse.getString("BillDate");
		model.setBillDate(LocalDate.parse(startDateString, formatter).format(formatter2));
		//model.setConnectionType(objectPaymentDetailsResponse.getString("ConnectionType"));
		model.setBillingType("RAPDRP");
		model.setModifyDate(new Date());
		billPaymentRepo.save(model);
		return new ResponseEntity<BillPaymentDetailsModel>(model, HttpStatus.OK);
	}
	
	public String getToken() {
		ResponseEntity<WSOResponse> response = null;
		
		String url = BillPaymentDetailsConstant.WSO2_TOKEN_GEN;
		String basic1 = BillPaymentDetailsConstant.BASE64_TOKEN;
		
//		url += "&username=" + adminWSO2UserName + "&password=" + adminWSO2Password;
		logger.info("Request for getToken url=" + url);
		try {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", "application/x-www-form-urlencoded");
		headers.add("Authorization", "Basic " + basic1);

		 HttpEntity<Object> request = new HttpEntity<Object>("", headers);
		response = restTemplate.exchange(url, HttpMethod.POST, request, WSOResponse.class, 1);
		logger.info("response for getToken url=" + url + " response = " + response.toString());
		} catch (Exception e) {
		e.printStackTrace();
		logger.info(e.toString(), e);
		return "Error " + e;
		}
		logger.info("access token ========================= " + response.getBody().getAccess_token());
		return response.getBody().getAccess_token();
		}
	
	
	
//	static String mpowerProdKey = BillPaymentDetailsConstant.MPOWER_BILL_FETCH_API;
//	static String paymentSource = BillPaymentDetailsConstant.WSO2_TOKEN_GEN;
//	static String basic = BillPaymentDetailsConstant.BASE64_TOKEN;

	public ResponseEntity<BillPaymentDetailsModel> callMpowerBillFetchApiRural(String AccountNumber)
			throws JsonProcessingException {
			try {
			String json = null;
			Map<String, String> payload = new HashMap<>();
			payload.put("accountNo", AccountNumber);
			ObjectMapper objectMapper = new ObjectMapper();
			json = objectMapper.writeValueAsString(payload);
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("Authorization", "Bearer " + getToken());
			requestHeaders.add("Content-Type", "application/json");
			
			HttpEntity<String> requestEntity = new HttpEntity<>(json, requestHeaders);

			RestTemplate restTemplate = new RestTemplate();
			
			ResponseEntity<String> result = restTemplate.exchange(BillPaymentDetailsConstant.MPOWER_BILL_FETCH_API,
					HttpMethod.POST, requestEntity, String.class);

			JSONObject object = new JSONObject(result.getBody());
			
			logger.info("=====================" + object);

			model.setConsumerNumber(object.getString("ACCOUNT_NO"));
			model.setDiscom(object.getString("DISCOMENAME").split("-")[0]);
			model.setDivisionCode(object.getString("DIVISION_CODE"));
			model.setDivisionName(object.getString("DIVISION_NAME"));
			model.setBillNumber(object.getString("BILLNUMBER"));
			model.setBillAmount(object.getString("AMOUNT"));
//		        model.setSanctionedLoadInKW(object.getString("SanctionedLoadInKW"));
			String startDateString = object.getString("BILLDUEDATE").replace(" 00:00", "");
			model.setBillDueDate(startDateString);
			model.setConsumerName(object.getString("NAME"));
			model.setAccountInfo(object.getString("AMOUNT"));
			model.setRebate(object.getString("REBATE"));
//		        model.setMobileNumber(object.getString("MobileNumber"));
			SimpleDateFormat parser = new SimpleDateFormat("MM/yyyy");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		    model.setBillDate(formatter.format(parser.parse(object.getString("BILLMONTH"))));
//		        model.setConnectionType(object.getString("ConnectionType"));
			model.setBillingType("NON_RAPDRP");
			model.setModifyDate(new Date());
			
			billPaymentRepo.save(model);
			}catch (Exception e) {
				e.printStackTrace();
			}
		return new ResponseEntity<BillPaymentDetailsModel>(model, HttpStatus.OK);
	}
	
	
}
