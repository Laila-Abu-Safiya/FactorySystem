package com.example.trainningspringproject.controller;

import com.example.trainningspringproject.Exeptions.EmptyException;
import com.example.trainningspringproject.entity.Machine;
import com.example.trainningspringproject.service.JobService;
import com.example.trainningspringproject.service.MachineService;
import com.example.trainningspringproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MachineController {

    private final MachineService machineService;
    private final UserService userService;
    @Autowired
    public MachineController(MachineService machineService, UserService userService) {
        this.machineService = machineService;
        
        this.userService = userService;
    }

    @GetMapping(path = "tenant/{user}/machine/device")
    public List<Machine> getAllMachines(@PathVariable("user") int user) throws Exception{
        boolean exists = userService.checkIfUserExists(user);
        if(exists) {
            return machineService.listAll(user);
        }else {
            throw new Exception("User is not exists");
        }
    }

    @PostMapping(path = "tenant/{user}/machine/device")
    public Machine addNewMachine(@RequestBody Machine machine, @PathVariable("user") int user) throws Exception {
        boolean exists = userService.checkIfUserExists(user);
        if(exists){
            machine.setUser(user);
            return machineService.addNewMachine(machine);}
        else {
            throw new Exception("User is not exists");

        }

    }

    @DeleteMapping(path = "tenant/{user}/machine/device")
    public void deleteMachine(@PathVariable("user") int user) throws Exception {
        boolean exists = userService.checkIfUserExists(user);
        if(exists) {
            machineService.deleteMachine(user);
        }else {
            throw new Exception("User is not exists");
        }

    }

   @PutMapping(path = "tenant/{user}/machine/device/{id}")
    public Machine updateMachineInfo(@PathVariable("id") int id,
                                     @RequestBody(required = false) Machine machine,
                                     @PathVariable("user") int user) throws Exception {
       boolean exists = userService.checkIfUserExists(user);
       if(exists) {
           if(machine.getUser() == user){
               return machineService.updateMachine(id, machine.getName(), machine.getLocation());
           }else {
               throw new Exception("User is not the Machine owner");
           }

       }else {
           throw new Exception("User is not exists");
       }

    }
}
