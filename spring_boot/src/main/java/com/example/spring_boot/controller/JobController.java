//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.spring_boot.controller;

import com.example.spring_boot.Entity.Job;
import com.example.spring_boot.exptions.ValidateForExistsUser;
import com.example.spring_boot.exptions.ValidateUSerOwner;
import com.example.spring_boot.service.JobService;
import com.example.spring_boot.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {
    private final JobService jobService;
    private final UserService userService;

    @Autowired
    public JobController(JobService jobService, UserService userService) {
        this.jobService = jobService;
        this.userService = userService;
    }

    @PostMapping(
        path = {"user/{userId}/job/task"}
    )
    public Job addNewMachine(@RequestBody Job job, @PathVariable("userId") int userId) throws Exception {
        return this.jobService.addNewJob(job);
    }

    @DeleteMapping(
        path = {"tenant/{userId}/job/task"}
    )
    public void deleteTask(@PathVariable("userId") int userId, @RequestBody int taskId) throws Exception {
        boolean userExists = this.userService.checkIfUserExists(userId);
        this.checkIfUserExists(userExists);
        this.jobService.deleteTask(taskId);
    }

    @GetMapping(
        path = {"tenant/{userId}/job/task/{taskId}"}
    )
    public Optional<Job> getTaskById(@PathVariable("userId") int userId, @PathVariable("taskId") int taskId) throws Exception {
        boolean userExists = this.userService.checkIfUserExists(userId);
        this.checkIfUserExists(userExists);
        boolean exists = this.jobService.CheckIfTaskExists(taskId);
        if (exists) {
            Optional<Job> task = this.jobService.getTaskById(taskId);
            return task;
        } else {
            throw new Exception("Task is not exists");
        }
    }

    @PostMapping(
        path = {"tenant/{userId}/job/task/{taskId}"}
    )
    public Job resubmitSpecificTask(@RequestBody Job job, @PathVariable("userId") int userId, @PathVariable("taskId") int taskId) throws Exception {
        boolean userExists = this.userService.checkIfUserExists(userId);
        this.checkIfUserExists(userExists);
        Optional<Job> task = this.jobService.getTaskById(taskId);
        if (((Job)task.get()).getStatus().equals("pending")) {
            throw new Exception("This task is not in final state");
        } else {
            job.setTaskid(taskId);
            return this.jobService.addNewJob(job);
        }
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
