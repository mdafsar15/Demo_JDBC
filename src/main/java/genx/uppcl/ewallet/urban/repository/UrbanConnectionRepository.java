package genx.uppcl.ewallet.urban.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import genx.uppcl.ewallet.urban.model.UrbanConnectionMaster;

@Repository
public interface UrbanConnectionRepository extends JpaRepository<UrbanConnectionMaster, Long> {

	List<UrbanConnectionMaster> findByGovtDept(String govtDept);

	List<UrbanConnectionMaster> findByGovtDeptAndDiscom(String govtDept, String discom);

	List<UrbanConnectionMaster> findByGovtDeptAndDivCode(String govtDept, String divCode);

	List<UrbanConnectionMaster> findByGovtDeptAndAccountId(String govtDept, String accountId);

	List<UrbanConnectionMaster> findByGovtDeptAndDiscomAndDivCode(String govtDept, String discom, String divCode);

	List<UrbanConnectionMaster> findByGovtDeptAndDivCodeAndAccountId(String govtDept, String divCode, String accountId);

	List<UrbanConnectionMaster> findByGovtDeptAndDiscomAndAccountId(String govtDept, String discom, String accountId);

	List<UrbanConnectionMaster> findByGovtDeptAndDiscomAndDivCodeAndAccountId(String govtDept, String discom,
			String divCode, String accountId);

	List<UrbanConnectionMaster> findByGovtDeptAndDistrictName(String govtDept, String districtName);

	List<UrbanConnectionMaster> findByGovtDeptAndDiscomAndDistrictName(String govtDept, String discom,
			String districtName);

	List<UrbanConnectionMaster> findByGovtDeptAndDistrictNameAndAccountId(String govtDept, String districtName,
			String accountId);

	List<UrbanConnectionMaster> findByGovtDeptAndDiscomAndDistrictNameAndAccountId(String govtDept, String discom,
			String districtName, String accountId);

	List<UrbanConnectionMaster> findByGovtDeptAndDiscomAndDistrictNameAndAccountIdAndDivCode(String govtDept,
			String discom, String districtName, String accountId, String divCode);

}
