package kr.ac.inha.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	
	public String loginMember(String id);
	
	public int regitMember(HashMap<String, String> hashmap) throws Exception;

	public HashMap<String, String> infoMember(String id);
	
	public int updateMember(HashMap<String, String> hashmap) throws Exception;
	
	public int delete(String id) throws Exception;
}
