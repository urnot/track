package com.sevenstudio.track.service;

import java.util.List;

import com.sevenstudio.track.entity.Tborder;

public interface OrderService {
	Boolean checkOrder(String number);

	String trackDetail(String number);
	
	List<Tborder> findAll();
	
	Tborder updateOrder(Tborder to);
	
	void delOrder(String number);

}
