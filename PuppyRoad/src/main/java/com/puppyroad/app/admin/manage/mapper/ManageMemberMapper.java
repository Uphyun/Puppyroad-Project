package com.puppyroad.app.admin.manage.mapper;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.puppyroad.app.main.service.PageDTO;
import com.puppyroad.app.member.service.MemberVO;

public interface ManageMemberMapper {
	//승인 할 도그워커 수 확인
	public int getWalkerCnt();
	//멤버 리스트
	public List<MemberVO> selectMemberList(@Param("pageDTO")PageDTO pageDTO, @Param("memberVO")MemberVO memberVO, @Param("condition")String condition);
	//의뢰인 상세정보
	public MemberVO selectMemberInfo(String memberCode);
	//회원 리스트 검색
	public List<MemberVO> selectSearchMemberList(@Param("memberVO")MemberVO memberVO, @Param("condition")String condition);
	//회원 총 데이터 카운트
	public int selectMemberCnt(@Param("memberVO")MemberVO memberVO, @Param("condition")String condition);
	//계정 상태 업데이트
	public int updateAccountState(MemberVO memberVO);
}
