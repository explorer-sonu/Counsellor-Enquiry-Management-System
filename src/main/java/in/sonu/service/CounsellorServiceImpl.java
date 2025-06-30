package in.sonu.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sonu.dto.DashboardResponse;
import in.sonu.entities.Counsellor;
import in.sonu.entities.Enquiry;
import in.sonu.repos.CounsellorRepo;
import in.sonu.repos.EnquriyRepo;

@Service
public class CounsellorServiceImpl implements CounsellorService {

	@Autowired
	private CounsellorRepo counsellorRepo;

	@Autowired
	private EnquriyRepo enqRepo;

	public CounsellorServiceImpl(CounsellorRepo counsellorRepo,EnquriyRepo enqRepo) {
		this.counsellorRepo = counsellorRepo;
		this.enqRepo = enqRepo;
	}

	@Override
	public Counsellor findByEmail(String email) {
		return counsellorRepo.findByEmail(email);
	}

	@Override
	public boolean register(Counsellor counsellor) {
		Counsellor savedCounsellor = counsellorRepo.save(counsellor);

		if(null != savedCounsellor.getCounsellorId()) {
			return true;
		}

		return false;
	}

	@Override
	public Counsellor login(String email, String pwd) {

		Counsellor counsellor = counsellorRepo.findByEmailAndPwd(email, pwd);
		return counsellor;
	}

	@Override
	public DashboardResponse getDashboardInfo(Integer counsellorId) {

		DashboardResponse response = new DashboardResponse();

		List<Enquiry> enqList = enqRepo.getEnquriesByCounsellorId(counsellorId);

		int totalEnq = enqList.size();

		int enrolledEnqs = enqList.stream()
									.filter(e -> e.getEnqStatus().equals("Enrolled"))
								    .collect(Collectors.toList())
								    .size();

		int lostEnqs = enqList.stream()
									.filter(e -> e.getEnqStatus().equals("Lost"))
								    .collect(Collectors.toList())
								    .size();

		int openEnqs = enqList.stream()
									.filter(e -> e.getEnqStatus().equals("Open"))
								    .collect(Collectors.toList())
								    .size();

		response.setTotalEnqs(totalEnq);
		response.setEnrolledEnqs(enrolledEnqs);
		response.setLostEnqs(lostEnqs);
		response.setOpenEnqs(openEnqs);

		return response;
	}

}
