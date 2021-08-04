package genx.uppcl.ewallet.urban.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Data
@Entity
@Table(name = "ConsumerBillDetails")

public class BillPaymentDetailsModel {

	
	/*@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;*/
	
	@Id
	@Column(name = "ConsumerNumber")
	private String consumerNumber;

	@Column(name = "Discom")
	private String discom;

	@Column(name = "DivisionCode", length = 20)
	private String divisionCode;

	@Column(name = "DivisionName", length = 100)
	private String divisionName;

	@Column(name = "BillNumber", length = 50)
	private String billNumber;

	@Column(name = "BillAmount", length = 50)
	private String billAmount;

	@Column(name = "BillDueDate")
	private String billDueDate;

	@Column(name = "ConsumerName", length = 100)
	private String consumerName;

	@Column(name = "AccountInfo", length = 10)
	private String accountInfo;

	@Column(name = "MobileNumber")
	private String mobileNumber;

	@Column(name = "BillDate")
	private String billDate;

	@Column(name = "BillingType")
	private String BillingType;

	@Column(name = "ModifyDate", length = 10)
	private Date modifyDate;
	
	@Column(name = "Rebate")
	private String rebate;
}
