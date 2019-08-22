package kr.hsoft.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.hsoft.boot.dto.ApplicationDTO;

@Mapper
public interface ApplicationMapper {
	void insertApplication(ApplicationDTO applicationDTO);
	void updateApplication(ApplicationDTO applicationDTO);
	List<ApplicationDTO> getApplications(int userID);//user가 작성한 신청서를 보여줌. query : userID에 해당하는 레코드
	List<ApplicationDTO> getDistrictApplications(int userID);//user에 해당하는 구역에 대한 신청서.query : userID가 속한 district에 해당하는 레코드.
	ApplicationDTO getSelectedApplication(int seq);//seq에 해당하는 신청
	ApplicationDTO getApplication(int userID);
}