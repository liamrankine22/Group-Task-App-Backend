package com.liamrankine.taskmanager.datatransfer.requests.taskassignment;

public class TaskAssignmentRemovalRequest {

    private Long userId;
    private Long taskId;

    //Constructors
    public TaskAssignmentRemovalRequest() {}
    public TaskAssignmentRemovalRequest(Long userId, Long taskId) {
        this.userId = userId;
        this.taskId = taskId;
    }

    //Setters
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    //Getters
    public Long getUserId() {
        return userId;
    }
    public Long getTaskId() {
        return taskId;
    }
}
