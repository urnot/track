package com.sevenstudio.track.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sevenstudio.track.dao.OrderRepository;
import com.sevenstudio.track.dao.UserRepository;
import com.sevenstudio.track.entity.Tborder;

@Controller
@RequestMapping(value = "/smanager")
public class UserController {
	@Autowired
	OrderRepository orderRepository;
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
		return orderRepository.save(o);

	}

	@GetMapping("/index")
	public String toManager() {
		return "order";

	}

	@GetMapping("/check")
	public String check(@RequestParam("user") String user, @RequestParam("pwd") String pwd) {
		if (userRepository.getByUserIsAndPasswordIs(user, pwd) != null) {
			return "order";
		}else {
			
			return "error";
		}

	}

}
