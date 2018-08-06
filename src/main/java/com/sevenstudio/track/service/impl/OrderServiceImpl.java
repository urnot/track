package com.sevenstudio.track.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sevenstudio.track.dao.OrderRepository;
import com.sevenstudio.track.entity.Tborder;
import com.sevenstudio.track.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderRepository orderRepository;

	@Override
	public Boolean checkOrder(String number) {
		if (number != null && number.length() == 18) {
			// 正则表达式规则
			String regEx = "^[1-9]\\d{17}$";
			// 编译正则表达式
			Pattern pattern = Pattern.compile(regEx);
			// 忽略大小写的写法
			// Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(number.trim());
			// 查找字符串中是否有匹配正则表达式的字符/字符串
			if (matcher.find()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public String trackDetail(String number) {
		Map map = new HashMap<>();
		Tborder to = orderRepository.getByTbnumberIs(number);
		if (to != null) {
			map.put("status", "success");
			map.put("data", to);
		} else {
			map.put("status", "fail");
		}
		return JSON.toJSONString(map);
	}

	@Override
	public List<Tborder> findAl() {
		return orderRepository.findAll();
	}

	@Override
	public boolean updateOrder(Tborder to) {
		// TODO Auto-generated method stub
		boolean result = false;
		Tborder t = orderRepository.getByTbnumberIs(to.getTbnumber());
		if (t == null) {
			return result;
		} else {
			t.setStatus(to.getStatus());
			t.setUpdatetime(to.getUpdatetime());
			orderRepository.save(t);
			result = true;
		}
		return result;
	}

	@Override
	public void delOrder(String number) {
		// TODO Auto-generated method stub
		
	}


}
