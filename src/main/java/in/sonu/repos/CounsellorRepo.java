package in.sonu.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sonu.entities.Counsellor;

public interface CounsellorRepo extends JpaRepository<Counsellor, Integer> {

	//SELECT * FROM counsellor_tbl where email=:email
	public Counsellor findByEmail(String email);

	//select * from counsellor_tbl where email=:email and pwd=:pwd
	public Counsellor findByEmailAndPwd(String email, String pwd);
}
