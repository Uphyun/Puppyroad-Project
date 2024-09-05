package com.puppyroad.app.match.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class AssiMatchVO {
	private Integer bulletinNo;
	private String  coverPhoto;
	private String  title;
	private String  writer;
	private Integer matchingState;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date    startTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date    endTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date    writingTime;
	private Integer animalCount;
	private String  walkPlaceAddress;
	private String  matchingKind;
	private String  clientCode;
	private String  puppyCode;
	private String  walkerUserId;
	private Integer dayFare;
	private Integer regularityFare;
	
}
