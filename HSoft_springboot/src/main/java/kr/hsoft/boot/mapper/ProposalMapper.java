package kr.hsoft.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import kr.hsoft.boot.domain.PaginationDomain;
import kr.hsoft.boot.dto.ProposalDTO;

@Mapper
public interface ProposalMapper {
	public List<ProposalDTO> selectProposals();
	public List<ProposalDTO> selectProposalsForUser(
			@Param("location") String location, 
			@Param("pagination") PaginationDomain paginataion);
	public int countProposalsForUser(@Param("location") String location);
	public List<ProposalDTO> selectProposalsForMaster(@Param("location") String location);
	public ProposalDTO selectProposal(int seq);
	@Select("SELECT * FROM PROPOSAL WHERE USER = #{seq}")
	public List<ProposalDTO> selectProposalByUser(int seq); // 해당 seq는 user의 seq
	public void insertProposal(ProposalDTO proposalDTO);
	public void putProposal(@Param("seq") int seq, @Param("proposal") ProposalDTO proposalDTO);
	public void putProposalState(@Param("seq") int seq, @Param("state") boolean state);
	public void deleteProposal(int seq);
}
