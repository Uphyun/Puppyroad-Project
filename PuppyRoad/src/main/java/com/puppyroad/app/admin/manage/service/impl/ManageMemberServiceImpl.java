package com.puppyroad.app.admin.manage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puppyroad.app.admin.manage.mapper.ManageMemberMapper;
import com.puppyroad.app.admin.manage.service.ManageMemberService;
import com.puppyroad.app.main.service.PageDTO;
import com.puppyroad.app.member.service.MemberVO;

@Service
public class ManageMemberServiceImpl implements ManageMemberService{
	
	@Autowired
	ManageMemberMapper manageMemberMapper;

	@Override
	public List<MemberVO> getMemberList(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return manageMemberMapper.selectMemberList(memberVO);
	}

	@Override
	public MemberVO getMemberInfo(String memberCode) {

		return manageMemberMapper.selectMemberInfo(memberCode);
	}

	@Override
	public Map<String, Object> updateAccountState(MemberVO memberVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSuccess = false;
		
		int result = manageMemberMapper.updateAccountState(memberVO);
		
		if(result == 1) {
			isSuccess = true;
		}
		
		map.put("isSuccess", isSuccess);
		map.put("vo", memberVO);

		return map;
	}

}
