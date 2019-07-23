package kr.hsoft.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.hsoft.boot.domain.MathResultDomain;
import kr.hsoft.boot.dto.MathDTO;

@Mapper
public interface MathMapper {
	void insertResult(MathResultDomain mathResultDomain);
	List<MathDTO> selectResults();
}
