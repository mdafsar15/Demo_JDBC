package genx.uppcl.ewallet.urban.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "ConsumerBillDetails")
public class ConsumerBillDetails {

	@Column(name = "Discom")
	private String discom;
	
	@Id
	@Column(name = "ConsumerNumber")
	private String consumerNumber;
	
	@Column(name = "ConsumerName")
	private String consumerName;

	@Column(name = "DivisionCode")
	private String divisionCode;
	
	@Column(name = "DivisionName")
	private String divisionName;

	@Column(name = "OrganizationCode")
	private String organizationCode;

	@Column(name = "OrganizationName")
	private String organizationName;
	
	@Column(name = "ConnectionStatus")
	private String connectionStatus;
	
	@Column(name = "BillNumber")
	private String billNumber;
	
	@Column(name = "BillAmount")
	private String billAmount;
	
	@Column(name = "SanctionedLoadInKW")
	private String sanctionedLoadInKW;
	
	@Column(name = "BillDueDate")
	private String billDueDate;
	
	@Column(name = "AccountInfo")
	private String accountInfo;
	  
	@Column(name = "MobileNumber")
	private String mobileNumber;
	
	@Column(name = "BillDate")
	private String billDate;
	
	@Column(name = "BillingType")
	private String billingType;
	
	@Column(name = "ApprovalStatus")
	private String approvalStatus;
	
	
}
