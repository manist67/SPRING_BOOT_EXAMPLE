package kr.hsoft.boot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import kr.hsoft.boot.domain.CategoryDomain;
import kr.hsoft.boot.domain.ProposalReadDomain;
import kr.hsoft.boot.dto.CategoryDTO;
import kr.hsoft.boot.dto.ProposalDTO;
import kr.hsoft.boot.mapper.CategoryMapper;

public class CategoryService {
	@Autowired
	CategoryMapper categoryMapper;
	
	public List<CategoryDomain> getCategories(){
		
		List<CategoryDTO> categories = categoryMapper.getCategories();
		
		List<CategoryDomain> categoryDomains = new ArrayList<CategoryDomain>();
		
		
		for(CategoryDTO category: categories) {
			CategoryDomain categoryDomain = new CategoryDomain();
			categoryDomain.setIdx(category.getIdx());
			categoryDomain.setName(category.getName());
			
			categoryDomains.add(categoryDomain);
		}
		return categoryDomains;
	}
}
