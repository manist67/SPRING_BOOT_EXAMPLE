package kr.hsoft.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import kr.hsoft.boot.domain.ApplicationWriteDomain;
import kr.hsoft.boot.dto.ApplicationDTO;

@Mapper
public interface ApplicationMapper {
	@Insert("insert into APPLICATION (USER, PROPOSAL, CONTENTS, CHILDREN_COUNT) values ( #{user}, #{proposal}, #{content}, #{childrenCount}")
	void insertApplication(ApplicationDTO applicationDTO);
	void updateApplication(ApplicationDTO applicationDTO);
	List<ApplicationDTO> getApplications(int userID);
	List<ApplicationDTO> getApplicationsByProposal(int seq);
	ApplicationDTO getApplication(int seq);
	
}