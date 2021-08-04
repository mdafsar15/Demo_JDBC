package genx.uppcl.ewallet.urban.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import genx.uppcl.ewallet.urban.model.GovOrganizationDetails;

@Repository
public interface GovOrganizationRepository extends JpaRepository<GovOrganizationDetails, Long> {

	List<GovOrganizationDetails> findAll();

	List<GovOrganizationDetails> findByOrganizationCode(String organizationCode);

	List<GovOrganizationDetails> findByOrganizationName(String organizationName);
	
	List<GovOrganizationDetails> findByOrganizationCodeAndOrganizationName(String organizationCode, String organizationName);


}
