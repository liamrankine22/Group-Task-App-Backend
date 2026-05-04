package com.liamrankine.taskmanager.repositories;

import com.liamrankine.taskmanager.entities.AppUser;
import com.liamrankine.taskmanager.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
