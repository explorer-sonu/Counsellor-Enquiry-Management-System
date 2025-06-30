package in.sonu.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.sonu.entities.Enquiry;

public interface EnquriyRepo extends JpaRepository<Enquiry, Integer> {

	@Query(value = "select * from enquiry_tbl where counsellor_id=:counsellorId",nativeQuery = true)
	public List<Enquiry> getEnquriesByCounsellorId(Integer counsellorId);
}
