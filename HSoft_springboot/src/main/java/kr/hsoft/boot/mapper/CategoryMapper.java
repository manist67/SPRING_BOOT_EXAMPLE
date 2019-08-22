package kr.hsoft.boot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CategoryMapper {
	@Select("select NAME from CATEGORY WHERE SEQ = #{seq}")
	public String getCategory(int seq);
}
