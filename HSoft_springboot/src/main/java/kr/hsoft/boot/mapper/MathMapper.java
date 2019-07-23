package kr.hsoft.boot.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.hsoft.boot.domain.MathResultDomain;

@Mapper
public interface MathMapper {
	void insertResult(MathResultDomain mathResultDomain);
}
