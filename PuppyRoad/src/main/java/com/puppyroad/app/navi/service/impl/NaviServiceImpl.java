package com.puppyroad.app.navi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puppyroad.app.match.service.MatchVO;
import com.puppyroad.app.navi.mapper.NaviMapper;
import com.puppyroad.app.navi.service.NaviService;
import com.puppyroad.app.navi.service.NaviVO;
import com.puppyroad.app.puppy.service.PuppyVO;

@Service
public class NaviServiceImpl implements NaviService {
	
	@Autowired
	NaviMapper naviMapper;
	
	@Override

	public List<PuppyVO> getPuppyList(String bulletinNo){
		return naviMapper.selectPuppyList(bulletinNo);
	};

	@Override
	public int getBoardNo(String writer) {
		return naviMapper.selectBulletinNo(writer);
	}

	@Override
	public Map<String, Object> callBackNavi(NaviVO naviVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<NaviVO> list = null;
		if(naviVO.getPuppyCode() != null) {
			naviMapper.setCallDogNavi(naviVO);
			list = naviVO.getCNavi();
		} else if (naviVO.getMatchCode() != 0) {
			list = naviMapper.getMatchingList(naviVO.getMatchCode());
		}
		
		map.put("naviList", list);

		return map;
	}

	@Override
	public MatchVO getMarkerInfo(int bulletinNo) {
		return naviMapper.selectMarkerInfo(bulletinNo);
	}

	@Override
	public Map<String, Object> setMatching(NaviVO naviVO) {
		boolean isSuccess = false;
		Map<String, Object> map = new HashMap<String, Object>();
		
		int result = naviMapper.insertMatchingInfo(naviVO);
		
		if(result == 1) {
			isSuccess = true;
		}
		
		map.put("isSuccess", isSuccess);
		map.put("naviVO", naviVO);
		
		return map;
	}

	@Override
	public String endMatching(int matchCode) {
		int result = naviMapper.deleteMatchingInfo(matchCode);
		
		if(result == 1) {
			return "success";
		}
		return "fail";
	}

}
