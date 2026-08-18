package com.liamrankine.taskmanager.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.liamrankine.taskmanager.enumerations.InvitationStatus;

@Entity
public class GroupInvitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvitationStatus status;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    public GroupInvitation() {}

    public GroupInvitation(
            Group group,
            String email,
            String token,
            InvitationStatus status,
            LocalDateTime expiresAt
    ) {
        this.group = group;
        this.email = email;
        this.token = token;
        this.status = status;
        this.expiresAt = expiresAt;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Group getGroup() {
        return group;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public InvitationStatus getStatus() {
        return status;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    // Setters
    public void setGroup(Group group) {
        this.group = group;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setStatus(InvitationStatus status) {
        this.status = status;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}
