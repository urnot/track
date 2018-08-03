package com.sevenstudio.track.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sevenstudio.track.dao.OrderRepository;
import com.sevenstudio.track.entity.Tborder;
import com.sevenstudio.track.service.OrderService;

@Controller
@RequestMapping(value = "/track")
public class OrderController {

	@Autowired
	OrderService orderService;

	@GetMapping("/index")
	public String toIndex() {
		return "index";

	}

	@PostMapping("/detail/{tracknum}")
	@ResponseBody
	public String getOrder(@PathVariable("tracknum") String number) {
		String result = orderService.trackDetail(number);
		return result;
	}

	@PostMapping("/check/{tracknum}")
	@ResponseBody
	public String checkNum(@PathVariable("tracknum") String number) {

		if (!orderService.checkOrder(number)) {
			return "false";
		} else {
			return "true";
		}
	}

}
