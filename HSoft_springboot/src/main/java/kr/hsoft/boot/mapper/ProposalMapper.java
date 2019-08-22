package kr.hsoft.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.hsoft.boot.dto.ProposalDTO;

@Mapper
public interface ProposalMapper {
	public List<ProposalDTO> selectProposals();
	public List<ProposalDTO> selectProposalsForUser(String location);
	public List<ProposalDTO> selectProposalsForMaster(String location);
	public ProposalDTO selectProposal(int seq);
	public List<ProposalDTO> selectProposalByUser(int seq); // 해당 seq는 user의 seq
	public void insertProposal(ProposalDTO proposalDTO);
	public void putProposal(int seq, ProposalDTO proposalDTO);
	public void putProposalState(int seq);
	public void deleteProposal(int seq);
}
