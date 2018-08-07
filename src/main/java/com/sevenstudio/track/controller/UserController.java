package com.sevenstudio.track.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sevenstudio.track.dao.OrderRepository;
import com.sevenstudio.track.dao.UserRepository;
import com.sevenstudio.track.entity.Tborder;
import com.sevenstudio.track.service.OrderService;

@Controller
@RequestMapping(value = "/smanager")
public class UserController {
	@Autowired
	OrderService orderService;
	@Autowired
	UserRepository userRepository;

	@PostMapping("/save")
	@ResponseBody
	public Tborder updateOrder(@RequestParam("tbnumber") String tbnumber, @RequestParam("status") String status) {
		System.out.println(status);
		Tborder o = new Tborder();
		o.setStatus(status);
		o.setTbnumber(tbnumber);
		o.setUpdatetime(new Date());
		return orderService.updateOrder(o);

	}

	@GetMapping("/index")
	public String toManager() {
		return "manager";

	}

	@GetMapping("/check")
	public String check(@RequestParam("user") String user, @RequestParam("pwd") String pwd) {
		if (userRepository.getByUserIsAndPasswordIs(user, pwd) != null) {
			return "order";
		}else {
			
			return "error";
		}

	}
	@GetMapping("/queryAll")
	
	public String findAll(Model map) {
		Map thead = new HashMap<>();
		thead.put("id", "编号");
		thead.put("tbnumber", "淘宝订单号");
		thead.put("status", "状态");
		thead.put("updatetime", "更新时间");
		thead.put("edit", "操作");
		map.addAttribute("thead",thead);
		map.addAttribute("orders",orderService.findAll());
		return "manager";
	}

}
