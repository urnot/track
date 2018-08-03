package com.sevenstudio.track.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sevenstudio.track.entity.Tborder;

public interface OrderRepository extends JpaRepository<Tborder, Integer> {
	Tborder getByTbnumberIs(String number);

}
