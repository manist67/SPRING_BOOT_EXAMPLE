package kr.hsoft.boot.service;

import java.util.ArrayList;
import java.util.List;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hsoft.boot.mapper.ApplicationMapper;
import kr.hsoft.boot.dto.ApplicationDTO;
import kr.hsoft.boot.exception.ApplicationNotFoundException;
import kr.hsoft.boot.domain.ApplicationDomain;
import kr.hsoft.boot.domain.UserDomain;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationMapper applicationMapper;

	public List<ApplicationDomain> getApplications(UserDomain user){
		List<ApplicationDTO> results = applicationMapper.getApplications(user.getSeq());

		List<ApplicationDomain> resultDomain = new ArrayList<>();
		for(ApplicationDTO result : results) {
			ApplicationDomain AppDomain = new ApplicationDomain();
			
			AppDomain.setChildrenCount(result.getChildrenCount());
			AppDomain.setContents(result.getContents());
			AppDomain.setEnable(result.getEnable());
			AppDomain.setProposal(result.getProposal());
			AppDomain.setUser(user);

			resultDomain.add(AppDomain);
		}
		
		return resultDomain;
	}
	
	public ApplicationDomain getApplication(int seq, UserDomain user) throws ApplicationNotFoundException, AuthenticationException {
		ApplicationDTO detailDTO = applicationMapper.getApplication(seq);
		
		if(detailDTO == null) {
			throw new ApplicationNotFoundException();
		}
		
		if(user.getSeq() != detailDTO.getUser())
			throw new AuthenticationException();

		ApplicationDomain detailDomain = new ApplicationDomain();
		detailDomain.setChildrenCount(detailDTO.getChildrenCount());
		detailDomain.setContents(detailDTO.getContents());
		detailDomain.setEnable(detailDTO.getEnable());
		detailDomain.setProposal(detailDTO.getProposal());
		detailDomain.setSeq(detailDTO.getSeq());
		detailDomain.setUser(user);
		

		return detailDomain;
	}
}
