package genx.uppcl.ewallet.urban.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "GovOrganizationDetails")
public class GovOrganizationDetails {
	
	@Id
	@Column(name = "OrganizationCode")
	private String organizationCode;

	@Column(name = "OrganizationName")
	private String organizationName;
	
	@Column(name = "StandardWallet")
	private String standardWallet;
	
	@Column(name = "Year")
	private String year;

}
