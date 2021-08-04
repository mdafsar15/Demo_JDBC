package genx.uppcl.ewallet.urban.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonFormat
public class WSOResponse {

	private String access_token;
	private String refresh_token;
	private String scope;
	private String token_type;
	private int expires_in;
}
