package com.puppyroad.app.websocket.service;

import java.util.List;

public interface ChatRoomService {
	// 전체 조회
	public List<ChatRoomDTO> getRoomList();
	// 단건 조회
	public ChatRoomDTO getRoomInfo(ChatRoomDTO chatRoomDTO);
	// 등록
	public int addRoom(ChatRoomDTO chatRoomDTO);
}
