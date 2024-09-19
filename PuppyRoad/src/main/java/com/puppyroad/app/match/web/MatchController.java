package com.puppyroad.app.match.web;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppyroad.app.match.service.MatchService;
import com.puppyroad.app.match.service.MatchVO;
import com.puppyroad.app.match.service.MatchingPuppyVO;
import com.puppyroad.app.puppy.service.PuppyVO;
import com.puppyroad.app.util.SecurityUtil;


@Controller
public class MatchController {
	
	@Autowired
	MatchService matchService;
	
	
	@GetMapping("user/match")
	public String match(Model model) {
		return "match/match";
	}
	
	// 전체조회 : get
	@GetMapping("user/matchList")
	public String matchList(Model model) {
		List<MatchVO> list = matchService.getMatchList();
		model.addAttribute("matchs", list);
		return "match/matchList";
	}

	// real 전체조회 : get
	@GetMapping("user/realMatch")
	public String RealDogList(PuppyVO puppyVO, Model model) {
		String mcode = SecurityUtil.memberCode();
		puppyVO.setClientUserId(mcode);
		
		List<PuppyVO> list = matchService.getDogList(puppyVO);
		model.addAttribute("matchDogs", list);
		
		return "match/realMatch";
	}
	
	// 단건조회 : get
	@GetMapping("user/matchInfo")
	public String matchInfo(MatchVO matchVO, Integer bulletinNo, Model model) {
		MatchVO findVO = matchService.getMatchInfo(matchVO);
		bulletinNo = matchVO.getBulletinNo();
		
		List<PuppyVO> list = matchService.getMatchingDogList(bulletinNo);
		
		model.addAttribute("match", findVO);
		model.addAttribute("matchingDog", list);
		return "match/matchInfo";
	}
	
	//  등록 + 개 전체 조회 : get
	@GetMapping("user/matchInsert")
	public String matchDogList(PuppyVO puppyVO, Model model) {
		String mcode = SecurityUtil.memberCode();
		puppyVO.setClientUserId(mcode);
		
		List<PuppyVO> list = matchService.getDogList(puppyVO);
		model.addAttribute("matchDogs", list);
		
		return "match/matchInsert";
	}
	
	// 등록 - 처리 : post
	@PostMapping("user/matchInsert")
	@ResponseBody
	public int matchInsertProcess(@RequestBody MatchVO matchVO) {
		System.err.println(matchVO);
		int bno = matchService.addMatch(matchVO);
		return bno;
	}
	
	// 수정 - 페이지
	@GetMapping("user/matchUpdate")
	public String matchUpdateForm(MatchVO matchVO, PuppyVO puppyVO, Model model) {
		MatchVO findVO = matchService.getMatchInfo(matchVO);
		String mcode = SecurityUtil.memberCode();
		puppyVO.setClientUserId(mcode);
		
		List<PuppyVO> list = matchService.getDogList(puppyVO);
		model.addAttribute("matchDogs", list);
		model.addAttribute("match", findVO);
		return "match/matchUpdate";
	}
	// 수정 - 처리
	@PostMapping("user/matchUpdate")
	@ResponseBody // AJAX
	public Map<String, Object> matchUpdate(@RequestBody MatchVO matchVO){
		return matchService.modifyMatch(matchVO);
		
	}
	
	// 삭제 - 처리
	@GetMapping("user/matchDelete")
	public String matchDelete(Integer bulletinNo) {
		matchService.removeMatch(bulletinNo);
		return "redirect:/user/matchList";
	}
	
	//매칭내역
	@GetMapping("user/myMatchingList")
	public String myMatchingList(String wrtier, Model model) {
		String mcode = SecurityUtil.memberCode();
		List<MatchVO> list = matchService.myMatchingList(mcode);
		model.addAttribute("matchs", list);
		return "match/myMatchingList";
	}
	
	
	
}
