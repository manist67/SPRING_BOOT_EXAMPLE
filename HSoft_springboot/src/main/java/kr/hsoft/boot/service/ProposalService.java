package kr.hsoft.boot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hsoft.boot.domain.ProposalDomain;
import kr.hsoft.boot.domain.UserDomain;
import kr.hsoft.boot.dto.ProposalDTO;
import kr.hsoft.boot.dto.UserDTO;
import kr.hsoft.boot.mapper.ProposalMapper;
import kr.hsoft.boot.mapper.UserMapper;

@Service
public class ProposalService{
	@Autowired
	ProposalMapper proposalMapper;
	
	@Autowired
	UserMapper userMapper;
	
	
	public List<ProposalDomain> getProposals() {
		
		List<ProposalDTO> proposals = proposalMapper.selectProposals();
		
		List<ProposalDomain> proposalDomains = new ArrayList<ProposalDomain>();
		
		
		for(ProposalDTO proposal: proposals) {
			
			UserDTO user = userMapper.selectUserBySeq(proposal.getUser());
			UserDomain userDomain = new UserDomain();
			userDomain.setUserID(user.getUserID());
			userDomain.setEmail(user.getEmail());
			userDomain.setAddress1(user.getAddress1());
			userDomain.setAddress2(user.getAddress2());
			userDomain.setGender(user.getGender());
			userDomain.setNickname(user.getNickname());
			userDomain.setPhone(user.getPhone());
			
			ProposalDomain proposalDomain = new ProposalDomain();
			proposalDomain.setSeq(proposal.getSeq());
			proposalDomain.setTitle(proposal.getTitle());
			proposalDomain.setUser(userDomain);
			proposalDomain.setCategory(proposal.getCategory());
			proposalDomain.setAddress1(proposal.getAddress1());
			proposalDomain.setAddress2(proposal.getAddress2());
			proposalDomain.setTargetGender(proposal.getTargetGender());
			proposalDomain.setDate(proposal.getDate());
			proposalDomain.setMinAge(proposal.getMinAge());
			proposalDomain.setMaxAge(proposal.getMaxAge());
			proposalDomain.setFee(proposal.getFee());
			proposalDomain.setMinParticipants(proposal.getMinParticipants());
			proposalDomain.setMaxParticipants(proposal.getMaxParticipants());
			proposalDomain.setRequirements(proposal.getRequirements());
			proposalDomain.setContents(proposal.getContents());
			
			proposalDomains.add(proposalDomain);
		}
		
		return proposalDomains;
	
		
	}
	
	public ProposalDomain getProposal(int seq) {
		
		ProposalDTO proposal = proposalMapper.selectProposalBySeq(seq);
		
		UserDTO user = userMapper.selectUserBySeq(proposal.getUser());
		UserDomain userDomain = new UserDomain();
		userDomain.setUserID(user.getUserID());
		userDomain.setEmail(user.getEmail());
		userDomain.setAddress1(user.getAddress1());
		userDomain.setAddress2(user.getAddress2());
		userDomain.setGender(user.getGender());
		userDomain.setNickname(user.getNickname());
		userDomain.setPhone(user.getPhone());
		
		ProposalDomain proposalDomain =  new ProposalDomain();
		proposalDomain.setSeq(proposal.getSeq());
		proposalDomain.setTitle(proposal.getTitle());
		proposalDomain.setUser(userDomain);
		proposalDomain.setCategory(proposal.getCategory());
		proposalDomain.setAddress1(proposal.getAddress1());
		proposalDomain.setAddress2(proposal.getAddress2());
		proposalDomain.setTargetGender(proposal.getTargetGender());
		proposalDomain.setDate(proposal.getDate());
		proposalDomain.setMinAge(proposal.getMinAge());
		proposalDomain.setMaxAge(proposal.getMaxAge());
		proposalDomain.setFee(proposal.getFee());
		proposalDomain.setMinParticipants(proposal.getMinParticipants());
		proposalDomain.setMaxParticipants(proposal.getMaxParticipants());
		proposalDomain.setRequirements(proposal.getRequirements());
		proposalDomain.setContents(proposal.getContents());
		
		return proposalDomain;
	}
	
	public void postProposal(String token, ProposalDomain proposalDomain) {
		
		ProposalDTO proposalDTO = new ProposalDTO();
		proposalDTO.setTitle(proposalDomain.getTitle());
		
		return;
	}
	
	
}
