package kr.hsoft.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.hsoft.boot.domain.LoginDomain;
import kr.hsoft.boot.domain.PaginationDomain;
import kr.hsoft.boot.dto.UserDTO;

@Mapper
public interface UserMapper {
	public UserDTO selectUserByIDandPassword(LoginDomain loginDomain);
	public UserDTO selectUserBySeq(Integer seq);
	public List<UserDTO> selectUsers(PaginationDomain pagination);
	public int countUsersByID(String userID);
	public int countUsersByEmail(String email);
	public int countUsersByPhone(String phone);
	public void insertUser(UserDTO user);
}
