package com.example.spring_boot.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.spring_boot.repository.MachineRepository;
import com.example.spring_boot.Entity.Machine;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MachineService {
    @Autowired
    private MachineRepository machineRepositor;

    public List<Machine> listAll(int userId) throws Exception {
        Machine[] userMachines = machineRepositor.findUserMachines(userId);
        if (userMachines.length == 0) {
            throw new Exception("The user don't have any machine");
        } else {
            return List.of(userMachines);
        }
    }
    public Optional<Machine> getMachineById(int machineId) {
        return machineRepositor.findById(machineId);

    }

    public boolean checkMachineById(int machineId) {
        boolean exists = machineRepositor.existsById(machineId);
        if (!exists) {
            return false;
        }
        return true;
    }

    public void deleteMachine(int userId) throws Exception {

        int[] userMachines = machineRepositor.findMachinesByUserId(userId);
        if (userMachines.length == 0) {
            throw new Exception("The user don't have any machine");
        } else {
            for (int i = 0; i < userMachines.length; i++) {
                machineRepositor.deleteById(userMachines[i]);
            }
        }
    }

    public Machine addNewMachine(Machine machine) {
        return machineRepositor.save(machine);
    }

    @Transactional
    public Machine updateMachine(int machineId, String Name, String Location) {
        Machine machine = machineRepositor.findById(machineId).orElseThrow(() -> new IllegalStateException(
                "Machine with id" + machineId + "does not exists"
        ));
        System.out.println(Name);
        if (Name != null &&
                Name.length() > 0 &&
                !Objects.equals(machine.getName(), Name)) {
            machine.setName(Name);
        }

        if (Location != null &&
                Location.length() > 0 &&
                !Objects.equals(machine.getLocation(), Location)) {
            machine.setLocation(Location);
        }

        return machine;
    }

}
