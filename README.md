# Group Workflow Organizer - Backend

## Overview
Backend portion of the Group Workflow Organizer that handles user authentication, API requests, database operations and data control.


---

## Tech Stack

- Language: [Java]
- Framework: [Spring Boot]
- Database: [PostgreSQL]
- Authentication: [Session-based]
- API Type: [REST]

---

## Features

- User authentication and authorization
- CRUD operations
- Database integration
- Role-based access control
- Input validation
- Error handling and logging

---

---

## API Mappings

---

# Users

### GET

| Endpoint | Description |
|----------|-------------|
| `origin/api/users` | Get all users |
| `origin/api/users/{id}` | Get user with specified ID |
| `origin/api/users/username/{username}` | Get user with specified username |

### POST

| Endpoint | Description |
|----------|-------------|
| `origin/api/users/register` | Register a new user |

### PATCH

| Endpoint | Description |
|----------|-------------|
| `origin/api/users/update/{id}` | Update user with specified ID |

### DELETE

| Endpoint | Description |
|----------|-------------|
| `origin/api/users/delete/{id}` | Delete user with specified ID |

---

# Groups

### GET

| Endpoint | Description |
|----------|-------------|
| `origin/api/groups` | Get all groups |
| `origin/api/groups/{id}` | Get group with specified ID |
| `origin/api/groups/user/{id}` | Get all groups that contain user with specified ID |

### POST

| Endpoint | Description |
|----------|-------------|
| `origin/api/groups/register` | Create a new group |
| `origin/api/groups/membership/create` | Create a new group membership |

### PATCH

| Endpoint | Description |
|----------|-------------|
| `origin/api/groups/update/{id}` | Update group with specified ID |
| `origin/api/groups/membership/update` | Update an existing group membership |

### DELETE

| Endpoint | Description |
|----------|-------------|
| `origin/api/groups/delete/{id}` | Delete group with specified ID |
| `origin/api/groups/membership/delete/{groupId}/{memberId}` | Delete membership between group and member |

---

# Tasks

### GET

| Endpoint | Description |
|----------|-------------|
| `origin/api/tasks` | Get all tasks |
| `origin/api/tasks/{id}` | Get task with specified ID |
| `origin/api/tasks/group/{groupId}` | Get all tasks belonging to specified group |

### POST

| Endpoint | Description |
|----------|-------------|
| `origin/api/tasks/create` | Create a new task |
| `origin/api/tasks/assignment/create` | Assign a task to a user |

### PATCH

| Endpoint | Description |
|----------|-------------|
| `origin/api/tasks/update` | Update an existing task |

### DELETE

| Endpoint | Description |
|----------|-------------|
| `origin/api/tasks/delete/{id}` | Delete task with specified ID |
| `origin/api/tasks/assignment/delete/{taskId}/{userId}` | Remove assignment between task and user |

---

## Project Structure

```bash
taskmanagerbackend/
├──.mvn/wrapper
│   ├── maven-wrapper.properties
├── src/
│   │── main/java/com/liamrankine/taskmanager
│   │             ├── configurations/
│   │             │   ├── CorsConfig.java
│   │             │   └── SecurityConfig.java
│   │             ├── controllers/
│   │             │   ├── AppUserController.java
│   │             │   ├── GroupController.java
│   │             │   ├── MeController.java
│   │             │   └── TaskController.java
│   │             ├── datatransfer/
│   │             │   ├── requests/
│   │             │   │   ├── appuser/
│   │             │   │   │   ├── AppUserRegistrationRequest.java
│   │             │   │   │   └── AppUserUpdateRequest.java
│   │             │   │   ├── group/
│   │             │   │   │   ├── GroupCreation.java
│   │             │   │   │   └── GroupUpdateRequest.java
│   │             │   │   ├── groupmembership/
│   │             │   │   │   ├── GroupMembershipCreateRequest.java
│   │             │   │   │   └── GroupMembershipUpdateRequest.java
│   │             │   │   ├── task/
│   │             │   │   │   ├── TaskCreationRequest.java
│   │             │   │   │   └── TaskUpdateRequest.java
│   │             │   │   └── taskassignment/
│   │             │   │       ├── TaskAssignmentCreationRequest.java
│   │             │   │       └── TaskAssignmentRemovalRequest.java
│   │             │   └── responses/
│   │             │       ├── AppUserResponse.java
│   │             │       ├── GroupMembershipResponse.java
│   │             │       ├── GroupResponse.java
│   │             │       ├── GroupSummaryResponse.java
│   │             │       ├── TaskAssignmeentResponse.java
│   │             │       └── TaskResponse.java
│   │             ├── entities/
│   │             │   ├── AppUser.java
│   │             │   ├── Group.java
│   │             │   ├── GroupMembership.java
│   │             │   ├── Task.java
│   │             │   └── TaskAssignment.java
│   │             ├── enumerations/
│   │             │   ├── GroupRole.java
│   │             │   └── UpdateType.java
│   │             ├── repositories/
│   │             │   ├── AppUserRepository.java
│   │             │   ├── GroupMembershipRepository.java
│   │             │   ├── GroupRepository.java
│   │             │   ├── TaskAssignmentRepository.java
│   │             │   └── TaskRepository.java
│   │             ├── services/
│   │             │   ├── AppUserService.java
│   │             │   ├── GroupService.java
│   │             │   └── TaskService.java
│   │             └── Application.java
│   └── test/java/com/liamrankine/taskmanager
│       └── ApplicationTests.java.java
├── .gitattributes
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md