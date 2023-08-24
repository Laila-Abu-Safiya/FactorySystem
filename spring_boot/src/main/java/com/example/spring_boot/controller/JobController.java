package com.example.spring_boot.controller;

import com.example.spring_boot.Entity.Job;
import com.example.spring_boot.Entity.JobMachineRequest;
import com.example.spring_boot.exptions.ValidateForExistsUser;
import com.example.spring_boot.exptions.ValidateUSerOwner;
import com.example.spring_boot.service.JobService;
import com.example.spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class JobController {

    private final JobService jobService;
    private final UserService userService;

    @Autowired
    public JobController(JobService jobService, UserService userService) {
        this.jobService = jobService;
        this.userService = userService;
    }

    @PostMapping(path = "user/{userId}/job/task")
    public Job addNewMachine(@RequestBody Job job, @PathVariable("userId") int userId) throws Exception {
        boolean userExists = userService.checkIfUserExists(userId);

        checkIfUserExists(userExists);
        return jobService.addNewJob(job);

    }

    @DeleteMapping(path = "tenant/{userId}/job/task")
    public void deleteTask(@PathVariable("userId") int userId, @RequestBody int taskId) throws Exception {
        boolean userExists = userService.checkIfUserExists(userId);
        boolean isUserOwner = jobService.checkIfUserIsOwner(userId, taskId);

        checkIfUserExists(userExists);
        checkIfTheUserOwner(isUserOwner);

        jobService.deleteTask(taskId);

    }

    @GetMapping(path = "tenant/{userId}/job/task/{taskId}")
    public Optional<Job> getTaskById(@PathVariable("taskId") int taskId, @PathVariable("userId") int userId) throws Exception {
        boolean exists = jobService.CheckIfTaskExists(taskId);


        if (exists) {
            boolean userExists = userService.checkIfUserExists(userId);

            boolean isUserOwner = jobService.checkIfUserIsOwner(userId, taskId);

            checkIfUserExists(userExists);
            checkIfTheUserOwner(isUserOwner);

            Optional<Job> task = jobService.getTaskById(taskId);
            return task;
        } else {
            throw new Exception("Task is not exists");
        }
    }

    @PostMapping(path = "tenant/{userId}/job/task/{taskId}")
    public Job resubmitSpecificTask(@RequestBody Job job, @PathVariable("userId") int userId, @PathVariable("taskId") int taskId) throws Exception {
        boolean userExists = userService.checkIfUserExists(userId);

        checkIfUserExists(userExists);

        Optional<Job> task = jobService.getTaskById(taskId);
        if (task.get().getStatus().equals("pending")) {
            throw new Exception("This task is not in final state");
        }
        job.setTaskid(taskId);
        return jobService.addNewJob(job);

    }

    public void checkIfUserExists(boolean exists) throws ValidateForExistsUser {
        if (!exists) {
            throw new ValidateForExistsUser("User is not exist");
        }
    }

    public void checkIfTheUserOwner(boolean exists) throws ValidateUSerOwner {
        if (!exists) {
            throw new ValidateUSerOwner("The user is not task owner!");
        }
    }
}