package com.example.spring_boot.controller;
import com.example.spring_boot.Entity.Job;
import com.example.spring_boot.service.JobService;
import com.example.spring_boot.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "user/job/task")
public class JobController {

    private final JobService jobService;
    private final MachineService machineService;
    @Autowired
    public JobController(JobService jobService, MachineService machineService) {
        this.jobService = jobService;
        this.machineService = machineService;
    }



    @GetMapping
    public List<Job> getAllJobs(){
        return jobService.listAllJobs();
    }

    @PostMapping
    public void addNewJob(@RequestBody Job job){
        boolean exists = machineService.checkMachineById(job.getMachineid());
        if(exists) {
            if(job.getProirty() >=1 && job.getProirty()<=5){
                jobService.addNewJob(job);
            }
            else {
                throw new IllegalStateException("Priority value should be [1,5]");
            }
        }
        else {
            throw new IllegalStateException("Machine with id "+ job.getMachineid() +" is not exists");

        }
    }

    @GetMapping("{id}")
    public Optional<Job> getJob(@PathVariable int id) {
        return jobService.getSpecificJob(id);}

}