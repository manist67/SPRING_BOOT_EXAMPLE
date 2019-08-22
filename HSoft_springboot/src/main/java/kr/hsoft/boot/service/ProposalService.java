package kr.hsoft.boot.service;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.AuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hsoft.boot.domain.ProposalReadDomain;
import kr.hsoft.boot.domain.ProposalWriteDomain;
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
	
	
	
	public List<ProposalReadDomain> getProposals(UserDomain userDomain) throws AuthException{
		
		String location = userDomain.getLocation();
		String authLevel = userDomain.getAuth();
		
		List<ProposalDTO> proposals;
		
		if(authLevel.equals("USER")) {
			proposals = proposalMapper.selectProposalsForUser(location);
		}else if(authLevel.equals("ADMIN")) {
			proposals = proposalMapper.selectProposals();
		}else if(authLevel.equals("MASTER")) {
			proposals = proposalMapper.selectProposalsForMaster(location);
		} else {
			throw new AuthException();
		}
		
		List<ProposalReadDomain> proposalDomains = new ArrayList<ProposalReadDomain>();
		
		
		for(ProposalDTO proposal: proposals) {
			
			UserDTO user = userMapper.selectUserBySeq(proposal.getUser());
			UserDomain writer = new UserDomain();
			writer.setUserID(user.getUserID());
			writer.setEmail(user.getEmail());
			writer.setAddress1(user.getAddress1());
			writer.setAddress2(user.getAddress2());
			writer.setGender(user.getGender());
			writer.setNickname(user.getNickname());
			writer.setPhone(user.getPhone());
			writer.setLocation(user.getLocation());
			
			ProposalReadDomain proposalDomain = new ProposalReadDomain();
			proposalDomain.setSeq(proposal.getSeq());
			proposalDomain.setTitle(proposal.getTitle());
			proposalDomain.setUser(writer);
			// TODO: set category
			proposalDomain.setCategory("");
			proposalDomain.setAddress1(proposal.getAddress1());
			proposalDomain.setAddress2(proposal.getAddress2());
			proposalDomain.setTargetGender(proposal.getTargetGender());
			proposalDomain.setDate(proposal.getDate());
			proposalDomain.setMinAge(proposal.getMinAge());
			
			//bug
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
	
	public ProposalReadDomain getProposal(int seq) {
		
		ProposalDTO proposal = new ProposalDTO();
		
		UserDTO user = userMapper.selectUserBySeq(proposal.getUser());
		UserDomain writer = new UserDomain();
		writer.setUserID(user.getUserID());
		writer.setEmail(user.getEmail());
		writer.setAddress1(user.getAddress1());
		writer.setAddress2(user.getAddress2());
		writer.setGender(user.getGender());
		writer.setNickname(user.getNickname());
		writer.setPhone(user.getPhone());
		
		ProposalReadDomain proposalDomain =  new ProposalReadDomain();
		proposalDomain.setSeq(proposal.getSeq());
		proposalDomain.setTitle(proposal.getTitle());
		proposalDomain.setUser(writer);
		
		proposalDomain.setCategory("");
		//TODO: get category in table
		
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
		proposalDomain.setCreate(proposal.getCreate());
		proposalDomain.setModify(proposal.getModify());
		
		return proposalDomain;
	}

	public void postProposal(UserDomain user, ProposalWriteDomain proposalDomain) {
		
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
	
	public void putProposalForUser(int seq, ProposalWriteDomain proposalDomain) {
		
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
		
		proposalMapper.putProposal(seq, proposalDTO);
	}
	
	public void putProposalForAdmin(int seq, ProposalWriteDomain proposalDomain) {
		
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
		
		proposalMapper.putProposal(seq, proposalDTO);
	}
	
	public void putProposalState(int seq) {
		// proposalMapper.putProposalState(seq);
	}
	
	public void deleteProposal(int seq) {
		// proposalMapper.deleteProposal(seq);
	}
	
}
