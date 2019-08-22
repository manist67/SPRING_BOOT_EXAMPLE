package kr.hsoft.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.hsoft.boot.dto.ProposalDTO;

@Mapper
public interface ProposalMapper {
	public List<ProposalDTO> selectProposals();
	public ProposalDTO selectProposalsForUser(String location);
	public ProposalDTO selectProposalsForMaster(String location);
	public ProposalDTO selectProposal(int seq);
	public void insertProposal(ProposalDTO proposalDTO);
	public void putProposal(ProposalDTO proposalDTO);
	public void putProposalState();
}
