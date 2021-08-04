package genx.uppcl.ewallet.urban.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "urbanConnectionMaster")
public class UrbanConnectionMaster {

	/*
	 * @Id
	 * 
	 * @Column(name = "ID")
	 * 
	 * @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
	 */
	@JsonIgnore
	@Column(name = "DISCOM")
	private String discom;

	@JsonIgnore
	@Column(name = "DIV_CODE")
	private String divCode;

	@Column(name = "District_Name")
	private String districtName;
	
	@JsonIgnore
	@Id
	@Column(name = "ACCT_ID")
	private String accountId;

	@JsonIgnore
	@Column(name = "CONSUMPTION_CURR_MNTH")
	private String consumptionCurrMnth;

	@JsonIgnore
	@Column(name = "Govt_Dept")
	private String govtDept;
	
	@JsonIgnore
	@Column(name = "CONNECTION_TYPE")
	private String connectionType;
	
	@JsonIgnore
	@Column(name = "CON_STATUS")
	private String conStatus;
	
	@JsonIgnore
	@Column(name = "TOTAL_OUTSTANDING")
	private String totalOutstanding;
	
	@JsonIgnore
	@Column(name = "CURRENT_ASSESSMENT")
	private String currentAssessment;

}
