package genx.uppcl.ewallet.urban.externalapi;

public class BillPaymentDetailsConstant {
	
	public static final String CCB_BILL_FETCH_API = "http://49.50.81.11:8081/fetchCcbApi";
	
	public static final String MPOWER_BILL_FETCH_API = "http://ewallet-test.uppclonline.com:8280/mpowerbillfetchapi/1.0.0";
//	public static final String MPOWER_BILL_FETCH_API = "http://49.50.81.11:8081/fetchMpowerApi";
	public static final String GRANT_TYPE = "password";
	public static final String USERNAME = "uppclgenx";
	public static final String PASSWORD = "APimTDu!2019@";
	
//	public static final String WSO2_TOKEN_GEN = "http://ewallet-test.uppclonline.com:8280/token?grant_type=GRANT_TYPE&username=USERNAME&password=PASSWORD";
	
	public static final String WSO2_TOKEN_GEN = "http://ewallet-test.uppclonline.com:8280/token?grant_type=password&username=uppclgenx&password=APimTDu!2019@";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	
	public static final String BASE64_TOKEN = "bUp0VERIbUVzWDlJWHR1TVduOTdvbTRyUGxRYTpzd3ZzNXdmaHdxaFZoMHhhdXRNck9xZ2RQaFlh";
}
