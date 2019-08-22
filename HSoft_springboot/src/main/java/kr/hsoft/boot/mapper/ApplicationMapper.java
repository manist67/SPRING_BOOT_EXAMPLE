package kr.hsoft.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.hsoft.boot.dto.ApplicationDTO;

@Mapper
public interface ApplicationMapper {
	void insertApplication(ApplicationDTO applicationDTO);
	void updateApplication(ApplicationDTO applicationDTO);
	List<ApplicationDTO> getApplications(int userID);
	List<ApplicationDTO> getApplicationsByProposal(int seq);
	ApplicationDTO getApplication(int seq);//seq에 해당하는 신청
	
}