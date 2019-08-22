package kr.hsoft.boot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hsoft.boot.domain.PaginationDomain;
import kr.hsoft.boot.domain.ProposalReadDomain;
import kr.hsoft.boot.domain.SignUpDomain;
import kr.hsoft.boot.domain.UserDomain;
import kr.hsoft.boot.dto.ProposalDTO;
import kr.hsoft.boot.dto.UserDTO;
import kr.hsoft.boot.exception.SignUpErrorException;
import kr.hsoft.boot.mapper.ProposalMapper;
import kr.hsoft.boot.mapper.UserMapper;
import kr.hsoft.boot.utils.SHA256;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	ProposalMapper proposalMapper;

	public List<UserDomain> getUsers(PaginationDomain pagination) {
		List<UserDTO> users = userMapper.selectUsers(pagination);
		List<UserDomain> userDomains = new ArrayList<UserDomain>();

		for (UserDTO user : users) {
			UserDomain userDomain = new UserDomain();
			userDomain.setUserID(user.getUserID());

			userDomains.add(userDomain);
		}

		return userDomains;
	}

	public void postUsers(SignUpDomain userInfo) throws SignUpErrorException {
		if(userMapper.countUsersByID(userInfo.getUserID()) > 0) {
			throw new SignUpErrorException("userID", "아이디 중복");
		}
		
		if(userMapper.countUsersByEmail(userInfo.getEmail()) > 0) {
			throw new SignUpErrorException("email", "이메일 중복");
		}

		String gender = userInfo.getGender();
		if(!gender.equals("M") && !gender.equals("F")) {
			throw new SignUpErrorException("gender", "성별은 M / F");
		}
		
		if(userMapper.countUsersByPhone(userInfo.getPhone()) > 0) {
			throw new SignUpErrorException("phone", "휴대폰 중복");
		}

		// TODO: PHONE AUTHENTICATION
		String password = SHA256.sha256(userInfo.getPassword());
		userInfo.setPassword(password);
		
		UserDTO userDTO = new UserDTO();

		userDTO.setUserID(userInfo.getUserID());
		userDTO.setPassword(password);
		userDTO.setAddress1(userInfo.getAddress1());
		userDTO.setAddress2(userInfo.getAddress2());
		userDTO.setPhone(userInfo.getPhone());
		userDTO.setEmail(userInfo.getEmail());
		userDTO.setName(userInfo.getName());
		userDTO.setGender(userInfo.getGender());
		userDTO.setNickname(userInfo.getNickname());
		userDTO.setNickname(userInfo.getName());
		userDTO.setLocation(userInfo.getLocation());
		
		userMapper.insertUser(userDTO);
	}
	
	public List<ProposalReadDomain> getUserProposals(int seq){
		// seq는 user의 seq임
		List<ProposalDTO> proposals = proposalMapper.selectProposalByUser(seq);
		
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
			
			ProposalReadDomain proposalDomain = new ProposalReadDomain();
			proposalDomain.setSeq(proposal.getSeq());
			proposalDomain.setTitle(proposal.getTitle());
			proposalDomain.setUser(writer);
			//TODO: SET CATEGORY BY CATEGORY TABLE
			proposalDomain.setCategory("");
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
}
