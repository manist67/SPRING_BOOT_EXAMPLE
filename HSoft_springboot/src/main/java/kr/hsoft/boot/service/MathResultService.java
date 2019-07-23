package kr.hsoft.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hsoft.boot.domain.MathResultDomain;
import kr.hsoft.boot.mapper.MathMapper;

@Service
public class MathResultService {
	@Autowired
	private MathMapper mathMapper;
	
	public void insertResult(MathResultDomain domain) {
		mathMapper.insertResult(domain);
	}
}
