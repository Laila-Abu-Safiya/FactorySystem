package com.example.spring_boot.service;

import com.example.spring_boot.Entity.Job;
import com.example.spring_boot.Entity.JobMachineRequest;
import com.example.spring_boot.Entity.Machine;
import com.example.spring_boot.repository.JobRepository;
import com.example.spring_boot.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private MachineRepository machineRepository;

    public Job addNewJob(Job task) {
        return jobRepository.save(task);
    }

    public void deleteTask(int taskId) {

        jobRepository.deleteById(taskId);
    }

    public boolean CheckIfTaskExists(int taskId) {
        boolean exists = jobRepository.existsById(taskId);
        return exists;
    }

    public Optional<Job> getTaskById(int taskId) {
        return jobRepository.findById(taskId);

    }

    public boolean checkIfUserIsOwner(int userId, int taskId) throws Exception {
        Machine[] jobMachine = jobRepository.CheckJobOwner(taskId);
        Machine[] userMachines = machineRepository.findUserMachines(userId);

        if(jobMachine[0].getId() == userMachines[0].getId()){
            return true;
        }else {
            return false;
        }
    }

}

