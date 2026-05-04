package com.liamrankine.taskmanager.entities;

import com.liamrankine.taskmanager.enumerations.GroupRole;
import jakarta.persistence.*;

@Entity
public class GroupMembership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser member;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GroupRole role;

    //Constructors
    public GroupMembership() {}
    public GroupMembership(Group group, AppUser member, GroupRole role) {
        this.group = group;
        this.member = member;
        this.role = role;
    }

    //Setters
    public void setGroup(Group group) { this.group = group; }
    public void setMember(AppUser member) { this.member = member; }
    public void setRole(GroupRole role) { this.role = role; }

    //Getters
    public Long getId() { return id; }
    public Group getGroup() { return group; }
    public AppUser getMember() { return member; }
    public GroupRole getRole() { return role; }
}
