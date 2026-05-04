package com.liamrankine.taskmanager.repositories;

import com.liamrankine.taskmanager.entities.GroupMembership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupMembershipRepository extends JpaRepository<GroupMembership, Long> {
    Optional<GroupMembership> findByGroup_IdAndMember_Id(Long groupId, Long userId);
    List<GroupMembership> findByMember_Id(Long userId);
}
