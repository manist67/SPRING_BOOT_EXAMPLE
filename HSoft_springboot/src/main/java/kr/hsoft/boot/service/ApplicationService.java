package kr.hsoft.boot.service;

import java.util.ArrayList;
import java.util.List;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hsoft.boot.mapper.ApplicationMapper;
import kr.hsoft.boot.mapper.ProposalMapper;
import kr.hsoft.boot.dto.ApplicationDTO;
import kr.hsoft.boot.dto.ProposalDTO;
import kr.hsoft.boot.exception.ApplicationNotFoundException;
import kr.hsoft.boot.domain.ApplicationDomain;
import kr.hsoft.boot.domain.ApplicationWriteDomain;
import kr.hsoft.boot.domain.ProposalReadDomain;
import kr.hsoft.boot.domain.UserDomain;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationMapper applicationMapper;
	
	@Autowired
	private ProposalMapper proposalMapper;

	public List<ApplicationDomain> getApplications(UserDomain user){
		List<ApplicationDTO> results = applicationMapper.getApplications(user.getSeq());

		List<ApplicationDomain> resultDomain = new ArrayList<>();
		for(ApplicationDTO result : results) {
			ApplicationDomain AppDomain = new ApplicationDomain();
			
			AppDomain.setChildrenCount(result.getChildrenCount());
			AppDomain.setContents(result.getContents());
			AppDomain.setEnable(result.getEnable());

			ProposalReadDomain proposalDomain = new ProposalReadDomain();
			ProposalDTO proposalDTO = proposalMapper.selectProposal(result.getProposal());
			proposalDomain.setSeq(proposalDTO.getSeq());
			proposalDomain.setTitle(proposalDTO.getTitle());
			
			AppDomain.setProposal(proposalDomain);
			AppDomain.setUser(user);

			resultDomain.add(AppDomain);
		}
		
		return resultDomain;
	}
	
	public List<ApplicationDomain> getApplicationsByProposal(int seq){
		List<ApplicationDTO> dbData = applicationMapper.getApplicationsByProposal(seq);
		List<ApplicationDomain> results = new ArrayList<>();
		
		for(ApplicationDTO dtoDatum: dbData) {
			ApplicationDomain domainDatum = new ApplicationDomain();
			
			domainDatum.setSeq(dtoDatum.getSeq());
			//domainDatum.setUser(dtoDatum.getUser());
			domainDatum.setEnable(dtoDatum.getEnable());
			domainDatum.setContents(dtoDatum.getContents());
			domainDatum.setChildrenCount(dtoDatum.getChildrenCount());
			
			results.add(domainDatum);
		}
		return results;
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
		
		ProposalReadDomain proposalDomain = new ProposalReadDomain();
		ProposalDTO proposalDTO = proposalMapper.selectProposal(detailDTO.getProposal());
		proposalDomain.setSeq(proposalDTO.getSeq());
		proposalDomain.setTitle(proposalDTO.getTitle());
		
		detailDomain.setProposal(proposalDomain);
		
		detailDomain.setSeq(detailDTO.getSeq());
		detailDomain.setUser(user);
		

		return detailDomain;
	}
	
	public void postApplication(int seq, UserDomain user, ApplicationWriteDomain application) {
		application.setUser(user.getSeq());
		application.setProposal(seq);
		
		ApplicationDTO appDTO = new ApplicationDTO();
		
		appDTO.setProposal(seq);
		appDTO.setUser(user.getSeq());
		appDTO.setChildrenCount(application.getChildrenCount());
		appDTO.setContents(application.getContents());
		
		applicationMapper.insertApplication(appDTO);
	}
}
