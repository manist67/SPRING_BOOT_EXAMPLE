package kr.hsoft.boot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hsoft.boot.domain.MathResultDomain;
import kr.hsoft.boot.dto.MathDTO;
import kr.hsoft.boot.mapper.MathMapper;

@Service
public class MathResultService {
	@Autowired
	private MathMapper mathMapper;
	
	public void insertResult(MathResultDomain domain) {
		mathMapper.insertResult(domain);
	}
	
	public List<MathResultDomain> getResults() {
		List<MathDTO> results = mathMapper.selectResults();
		List<MathResultDomain> resultsDomain = new ArrayList<>();
		
		for( MathDTO result : results ) {
			MathResultDomain resultDomain = new MathResultDomain();
			resultDomain.setResult(result.getResult());
			resultDomain.setExpression(result.getExpression());
			
			resultsDomain.add(resultDomain);
		}
		
		return resultsDomain;
	}
}
