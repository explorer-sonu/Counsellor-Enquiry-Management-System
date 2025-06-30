package in.sonu.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.sonu.dto.ViewEnqsFilterRequest;
import in.sonu.entities.Enquiry;
import in.sonu.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	
	private EnquiryService enqService;
	
	public EnquiryController(EnquiryService enqService) {
		this.enqService = enqService;
	}
	
	@PostMapping("/filter-enqs")
	public String filterEnquries(ViewEnqsFilterRequest viewEnqsFilterRequest, HttpServletRequest req, Model model) {
			
		// get exesting session obj
		HttpSession session = req.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");
		
		List<Enquiry> enqsList = enqService.getEnquiresWithFilter(viewEnqsFilterRequest, counsellorId);
		
		model.addAttribute("enquiries", enqsList);

		return "viewEnqsPage";
	}
	
	@GetMapping("/view-enquiries")
	public String getEnquries(HttpServletRequest request, Model model) {
		
		// get exesting session obj
		HttpSession session = request.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");
		
		List<Enquiry> enqList = enqService.getAllEnquiries(counsellorId);
		
		model.addAttribute("enquiries", enqList);
		
		// search from binding object 
		ViewEnqsFilterRequest filterReq = new ViewEnqsFilterRequest();
		model.addAttribute("viewEnqsFilterRequest", filterReq);
		
		return "viewEnqsPage";
	}
	
	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model) {
		
		Enquiry enquiry = new Enquiry();
		model.addAttribute("enquiry", enquiry);
		
		return "enquiryForm";
	}
	
	@GetMapping("/editEnq")
	public String editEnquiry(@RequestParam("enqId") Integer enqId, Model  model) {
		
		Enquiry enquiry = enqService.getEnquiryById(enqId);
		model.addAttribute("enquiry", enquiry);
		
		return "enquiryForm";
	}
	
	@PostMapping("/addEnq")
	public String handleAddEnquiry(Enquiry enquiry, HttpServletRequest req, Model model) throws Exception{
		
		// get exesting session obj
		HttpSession session = req.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");
		
		boolean isSaved = enqService.addEnquiry(enquiry, counsellorId);
		
		if(isSaved) {
			model.addAttribute("smsg", "Enquiry Added");
		}else {
			model.addAttribute("emsg", "Failed To Add Enquiry");
		}
		
		return "enquiryForm";
	}
}
