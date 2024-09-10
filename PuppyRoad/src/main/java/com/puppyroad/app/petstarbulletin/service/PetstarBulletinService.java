package com.puppyroad.app.petstarbulletin.service;

import java.util.List;
import java.util.Map;


public interface PetstarBulletinService {
	
	// 전체조회
	public List<PetstarBulletinVO> getBulletinList(PetstarBulletinVO bulletinVO);
	
	// 단건조회
	public PetstarBulletinVO getBulletinInfo(PetstarBulletinVO bulletinVO);
	
	// 등록
	public int addBulletin(PetstarBulletinVO bulletinVO);
	
	// 수정
	public Map<String, Object> modifyBulletin(PetstarBulletinVO bulletinVO);
	
	// 삭제
	public int removeBulletin(int bno);
}
