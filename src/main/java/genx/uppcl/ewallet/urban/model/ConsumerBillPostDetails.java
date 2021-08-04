package genx.uppcl.ewallet.urban.model;

import java.util.List;
import java.util.Map;

import javax.persistence.OneToMany;

import org.json.JSONObject;

import genx.uppcl.ewallet.urban.dto.BillPostPayload;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ConsumerBillPostDetails {

	private String consumerNumber;

	private String billingType;

	private String pollUrl;

	private String billNumber;

	private BillPostPayload billPostPayload;

	private String govtName;

}
