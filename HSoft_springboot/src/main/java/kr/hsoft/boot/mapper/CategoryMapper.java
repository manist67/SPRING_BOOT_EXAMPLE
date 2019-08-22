package kr.hsoft.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.hsoft.boot.dto.CategoryDTO;

@Mapper
public interface CategoryMapper {
	@Select("select NAME from CATEGORY WHERE SEQ = #{seq}")
	public String getCategory(int seq);
	public List<CategoryDTO> getCategories();

}
