package kr.hsoft.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.hsoft.boot.dto.ProposalDTO;

@Mapper
public interface ProposalMapper {
	public List<ProposalDTO> selectProposals();
	public ProposalDTO selectProposalBySeq(int seq);
	public void postProposal(String token);

}
