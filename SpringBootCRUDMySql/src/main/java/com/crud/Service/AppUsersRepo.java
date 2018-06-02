package com.crud.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.Models.AppUsers;

public interface AppUsersRepo extends JpaRepository<AppUsers, Integer> {

}
