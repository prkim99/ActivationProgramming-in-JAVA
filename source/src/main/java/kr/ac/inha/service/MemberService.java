package kr.ac.inha.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.ac.inha.mapper.MemberMapper;


@Service
public class MemberService {
	@Autowired
	MemberMapper mapper;

	public String loginMember(String id) {
		return mapper.loginMember(id);
	}
	
	public int regitMember(HashMap<String, String> hashmap) throws Exception {
		
		return mapper.regitMember(hashmap);
	}
	
	public HashMap<String, String> infoMember(String id) {
		return mapper.infoMember(id);
	}
	public int updateMember(HashMap<String, String> hashmap) throws Exception{
		
		return mapper.updateMember(hashmap);
	}
	public int delete(String id) throws Exception{
		return mapper.delete(id);
	}
}
