package com.sevenstudio.track.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sevenstudio.track.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User getByUserIsAndPasswordIs(String user, String pwd);
}
