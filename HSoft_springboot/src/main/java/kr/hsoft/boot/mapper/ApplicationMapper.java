package kr.hsoft.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.hsoft.boot.dto.ApplicationDTO;

@Mapper
public interface ApplicationMapper {
	void insertApplication(ApplicationDTO applicationDTO);
	void updateApplication(ApplicationDTO applicationDTO);
	List<ApplicationDTO> getApplications();
	ApplicationDTO getApplication(int seq);
}
