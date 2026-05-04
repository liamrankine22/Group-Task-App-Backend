package com.liamrankine.taskmanager.repositories;

import com.liamrankine.taskmanager.entities.TaskAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, Long> {
    Optional<TaskAssignment> findByTask_IdAndAssignedUser_Id(Long taskId, Long userId);
}
