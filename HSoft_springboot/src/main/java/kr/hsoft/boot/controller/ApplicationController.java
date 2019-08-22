package kr.hsoft.boot.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import kr.hsoft.boot.domain.ApplicationDomain;
import kr.hsoft.boot.service.ApplicationService;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
	@Autowired
	ApplicationService applicationService;

	@RequestMapping(method=RequestMethod.GET)
	public List<ApplicationDomain> ListUp(){
		/**
		 * 1. 권한 나누기 controller
		 */
		List<ApplicationDomain> resultList = applicationService.getApplications();
		return resultList;
	}
	
	@RequestMapping(value="/{seq}", method=RequestMethod.GET)
	public ApplicationDomain getApplicationDetail(@PathVariable("seq") int seq) {
		ApplicationDomain detailResult = applicationService.getApplication(seq);
		
		return detailResult;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public void postApplication(@RequestBody ApplicationDomain applicationDomain) {
		applicationService.insertApplication(applicationDomain);
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.PUT)
	public void updateApplication(@PathVariable("seq") int seq,@RequestBody @NotNull ApplicationDomain applicationDomain) {
		applicationService.updateApplication(seq, applicationDomain);
	}
}