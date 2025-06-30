package in.sonu.service;

import java.util.List;

import in.sonu.dto.ViewEnqsFilterRequest;
import in.sonu.entities.Enquiry;

public interface EnquiryService {

	public boolean addEnquiry(Enquiry enq,Integer counsellorId)throws Exception;

	public List<Enquiry> getAllEnquiries(Integer counsellorId);

	public List<Enquiry> getEnquiresWithFilter(ViewEnqsFilterRequest filterReq, Integer counsellorId);

	public Enquiry getEnquiryById(Integer enqId);

}
