package genx.uppcl.ewallet.urban.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonFormat
public class BillPostPayload {

	private String consumerName;
	private String mobileNumber;
	private String discom;
	private String divisionName;
	private String billAmount;
}
