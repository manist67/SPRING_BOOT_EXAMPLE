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
	
	public ProposalDomain getProposal(int seq, UserDomain userDomain) {
		
		ProposalDTO proposal = new ProposalDTO();
		
		String authLevel = userDomain.getAuth();
		
		if(authLevel == "USER") {
			proposal = proposalMapper.selectProposalForUser(seq);
		}else if(authLevel == "ADMIN") {
			proposal = proposalMapper.selectProposalForAdmin(seq);
		}else if(authLevel == "MASTER") {
			proposal = proposalMapper.selectProposalForMaster(seq);
		}
		
		UserDTO user = userMapper.selectUserBySeq(proposal.getUser());
		UserDomain writer = new UserDomain();
		writer.setUserID(user.getUserID());
		writer.setEmail(user.getEmail());
		writer.setAddress1(user.getAddress1());
		writer.setAddress2(user.getAddress2());
		writer.setGender(user.getGender());
		writer.setNickname(user.getNickname());
		writer.setPhone(user.getPhone());
		
		ProposalDomain proposalDomain =  new ProposalDomain();
		proposalDomain.setSeq(proposal.getSeq());
		proposalDomain.setTitle(proposal.getTitle());
		proposalDomain.setUser(writer);
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

	public void postProposal(UserDomain user, ProposalDomain proposalDomain) {
		
		// 예외처리,, valid..?
		ProposalDTO proposalDTO = new ProposalDTO();
		proposalDTO.setTitle(proposalDomain.getTitle());
		proposalDTO.setMinAge(proposalDomain.getMinAge());
		proposalDTO.setMaxAge(proposalDomain.getMaxAge());
		proposalDTO.setTargetGender(proposalDomain.getTargetGender());
		proposalDTO.setCategory(proposalDomain.getCategory());
		proposalDTO.setAddress1(proposalDomain.getAddress1());
		proposalDTO.setAddress2(proposalDomain.getAddress2());
		proposalDTO.setDate(proposalDomain.getDate());
		proposalDTO.setFee(proposalDomain.getFee());
		proposalDTO.setMinParticipants(proposalDomain.getMinParticipants());
		proposalDTO.setMaxParticipants(proposalDomain.getMaxAge());
		proposalDTO.setRequirements(proposalDomain.getRequirements());
		proposalDTO.setContents(proposalDomain.getContents());	
		proposalDTO.setUser(user.getSeq());
		
		proposalMapper.insertProposal(proposalDTO);
		
	}
	
	public void putProposal(ProposalDomain proposalDomain) {
		
		// 모든 정보를 수정 할 수 있나? 
		// 이미 신청자가 있는 경우는..?
		
		ProposalDTO proposalDTO = new ProposalDTO();
		proposalDTO.setTitle(proposalDomain.getTitle());
		proposalDTO.setMinAge(proposalDomain.getMinAge());
		proposalDTO.setMaxAge(proposalDomain.getMaxAge());
		proposalDTO.setTargetGender(proposalDomain.getTargetGender());
		proposalDTO.setCategory(proposalDomain.getCategory());
		proposalDTO.setAddress1(proposalDomain.getAddress1());
		proposalDTO.setAddress2(proposalDomain.getAddress2());
		proposalDTO.setDate(proposalDomain.getDate());
		proposalDTO.setFee(proposalDomain.getFee());
		proposalDTO.setMinParticipants(proposalDomain.getMinParticipants());
		proposalDTO.setMaxParticipants(proposalDomain.getMaxAge());
		proposalDTO.setRequirements(proposalDomain.getRequirements());
		proposalDTO.setContents(proposalDomain.getContents());
		
		proposalMapper.putProposal(proposalDTO);
	}
	
	
}
