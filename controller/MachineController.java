package com.example.spring_boot.controller;

import com.example.spring_boot.Entity.Machine;
import com.example.spring_boot.exptions.ValidateForExistsUser;
import com.example.spring_boot.service.MachineService;
import com.example.spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MachineController {

    private final MachineService machineService;
    private final UserService userService;

    @Autowired
    public MachineController(MachineService machineService, UserService userService) {
        this.machineService = machineService;
        this.userService = userService;
    }

    @GetMapping(path = "tenant/{userId}/machine/device")
    public List<Machine> getAllMachines(@PathVariable("userId") int userId) throws Exception {
        boolean exists = userService.checkIfUserExists(userId);
        checkIfUserExists(exists);
        return machineService.listAll(userId);
    }

    @PostMapping(path = "tenant/{userId}/machine/device")
    public Machine addNewMachine(@RequestBody Machine machine, @PathVariable("userId") int userId) throws Exception {
        boolean exists = userService.checkIfUserExists(userId);
        checkIfUserExists(exists);
        machine.setUser(userId);
        return machineService.addNewMachine(machine);


    }

    @DeleteMapping(path = "tenant/{userId}/machine/device")
    public void deleteMachine(@PathVariable("userId") int userId) throws Exception {
        boolean exists = userService.checkIfUserExists(userId);
        checkIfUserExists(exists);
        machineService.deleteMachine(userId);

    }

    @PutMapping(path = "tenant/{userId}/machine/device/{machinId}")
    public Machine updateMachineInfo(@PathVariable("machinId") int machinId, @RequestBody(required = false) Machine machine, @PathVariable("userId") int userId) throws Exception {
        boolean exists = machineService.checkMachineById(machinId);
        if (exists) {
            Optional<Machine> machine1 = machineService.getMachineById(machinId);
            if (machine1.get().getUser() == userId) {//get hte machine by its machinId then compare
                return machineService.updateMachine(machinId, machine.getName(), machine.getLocation());
            } else {
                throw new Exception("User is not the Machine owner");
            }

        } else {
            throw new Exception("Machine is not exists");
        }

    }

    public void checkIfUserExists(boolean exists) throws ValidateForExistsUser {
        if (!exists) {
            throw new ValidateForExistsUser("User is not exist");
        }
    }
}
