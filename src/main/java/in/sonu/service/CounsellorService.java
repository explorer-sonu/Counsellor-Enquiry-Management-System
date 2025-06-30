package in.sonu.service;

import in.sonu.dto.DashboardResponse;
import in.sonu.entities.Counsellor;

public interface CounsellorService {

	public Counsellor findByEmail(String email);

	public boolean register(Counsellor counsellor);

	public Counsellor login(String email, String pwd);

	public DashboardResponse getDashboardInfo(Integer counsellorId);
}
