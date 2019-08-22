package kr.hsoft.boot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hsoft.boot.mapper.ApplicationMapper;
import kr.hsoft.boot.dto.ApplicationDTO;
import kr.hsoft.boot.domain.ApplicationDomain;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationMapper applicationMapper;

	public void insertApplication(ApplicationDomain applicationDomain)  {
		ApplicationDTO result = new ApplicationDTO();
		result.setChildrenCount(applicationDomain.getChildrenCount());
		result.setContents(applicationDomain.getContents());
		result.setEnable(applicationDomain.getEnable());
		result.setProposal(applicationDomain.getProposal());
		result.setUser(applicationDomain.getUser());

		applicationMapper.insertApplication(result);
	}

	public void updateApplication(int seq, ApplicationDomain applicationDomain) {
		ApplicationDTO result = new ApplicationDTO();
		
		result.setSeq(seq);
		
		result.setChildrenCount(applicationDomain.getChildrenCount());
		result.setContents(applicationDomain.getContents());
		result.setEnable(applicationDomain.getEnable());
		result.setProposal(applicationDomain.getProposal());
		result.setUser(applicationDomain.getUser());
		
		applicationMapper.updateApplication(result);
	}
	
	public List<ApplicationDomain> getApplications(){
		List<ApplicationDTO> results = applicationMapper.getApplications();
		List<ApplicationDomain> resultDomain = new ArrayList<>();
		
		for(ApplicationDTO app : results) {
			ApplicationDomain temp = new ApplicationDomain();
			temp.setChildrenCount(app.getChildrenCount());
			temp.setContents(app.getContents());
			temp.setEnable(app.getEnable());
			temp.setProposal(app.getProposal());
			temp.setUser(app.getUser());
			
			resultDomain.add(temp);
		}
		
		return resultDomain;
	}
	
	public ApplicationDomain getApplication(int seq) {
		ApplicationDTO detailDTO = applicationMapper.getApplication(seq);
		ApplicationDomain detailDomain = new ApplicationDomain();
		detailDomain.setChildrenCount(detailDTO.getChildrenCount());
		detailDomain.setContents(detailDTO.getContents());
		detailDomain.setEnable(detailDTO.getEnable());
		detailDomain.setProposal(detailDTO.getProposal());
		detailDomain.setSeq(detailDTO.getSeq());
		detailDomain.setUser(detailDTO.getUser());
		
		
		return detailDomain;
	}
}
