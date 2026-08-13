package com.liamrankine.taskmanager.services;

import com.liamrankine.taskmanager.datatransfer.requests.task.TaskCreationRequest;
import com.liamrankine.taskmanager.datatransfer.requests.task.TaskUpdateRequest;
import com.liamrankine.taskmanager.datatransfer.requests.taskassignment.TaskAssignmentCreationRequest;
import com.liamrankine.taskmanager.datatransfer.responses.TaskResponse;
import com.liamrankine.taskmanager.entities.*;
import com.liamrankine.taskmanager.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final AppUserRepository appUserRepo;
    private final GroupRepository groupRepo;
    private final TaskRepository taskRepo;
    private final GroupMembershipRepository groupMembershipRepo;
    private final TaskAssignmentRepository taskAssignmentRepo;
    private final PasswordEncoder passwordEncoder;

    public TaskService(AppUserRepository appUserRepo, GroupRepository groupRepo,
                          TaskRepository taskRepo, PasswordEncoder passwordEncoder,
                          GroupMembershipRepository groupMembershipRepo,
                          TaskAssignmentRepository taskAssignmentRepo
    ) {
        this.appUserRepo = appUserRepo;
        this.groupRepo = groupRepo;
        this.taskRepo = taskRepo;
        this.groupMembershipRepo = groupMembershipRepo;
        this.taskAssignmentRepo = taskAssignmentRepo;
        this.passwordEncoder = passwordEncoder;
    }

    //GETS
    public List<TaskResponse> getTasks() {
        List<Task> tasks = taskRepo.findAll();
        List<TaskResponse> taskResponses = new ArrayList<>();
        for (Task task : tasks) {
            TaskResponse response = new TaskResponse(task);
            taskResponses.add(response);
        }
        return taskResponses;
    }

    public TaskResponse getTaskByID(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find task with id: " + id));
        return new TaskResponse(task);
    }

    public List<TaskResponse> getTaskByUserId(Long userId) {
        AppUser user = appUserRepo.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find user with id: " + userId));

        List<Group> userGroups = user.getMemberships()
                .stream()
                .map(GroupMembership::getGroup)
                .toList();

        List<Task> userTasks = new ArrayList<>();
        for (Group group : userGroups) {
            userTasks.addAll(group.getTasks());
        }

        List<TaskResponse> taskResponses = new ArrayList<>();
        for (Task task : userTasks) {
            TaskResponse response = new TaskResponse(task);
            taskResponses.add(response);
        }

        return taskResponses;
    }

    public List<TaskResponse> getTasksByGroupId(Long groupId) {
        List<Task> tasks = taskRepo.findByGroupId(groupId);

        List<TaskResponse> taskResponses = new ArrayList<>();
        for (Task task : tasks) {
            TaskResponse response = new TaskResponse(task);
            taskResponses.add(response);
        }

        return taskResponses;
    }

    //POSTS
    @Transactional
    public void createTask(TaskCreationRequest request) {
        Task task = new Task(request.getTitle(), request.getDescription(), request.getStatus(), request.getDueDate());
        task.setCreatedBy(appUserRepo.findById(request.getCreatorId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find user")));
        task.setGroup(groupRepo.findById(request.getGroupId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find group")));
        taskRepo.save(task);
    }

    @Transactional
    public void createTaskAssignment(TaskAssignmentCreationRequest request) {
        Task task = taskRepo.findById(request.getTaskId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find task"));
        AppUser user = appUserRepo.findById(request.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find user"));
        TaskAssignment newAssignment = new TaskAssignment(user, task, LocalDate.now());

        task.addTaskAssignment(newAssignment);
        user.addAssignment(newAssignment);
        taskAssignmentRepo.save(newAssignment);
    }

    //PATCHES
    @Transactional
    public void updateTask(TaskUpdateRequest request) {
        Task updateTask = taskRepo.findById(request.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
        updateTask.updateByRequest(request);
        taskRepo.save(updateTask);
    }

    //DELETES
    @Transactional
    public void deleteTask(Long id) {
        Task deleteTask = taskRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
        taskRepo.delete(deleteTask);
    }

    @Transactional
    public void deleteTaskAssignment(Long taskId, Long userId) {
        TaskAssignment assignment = taskAssignmentRepo.findByTask_IdAndAssignedUser_Id(taskId, userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task/User not found"));
        taskAssignmentRepo.delete(assignment);
    }


}
