package com.example.trainningspringproject.service;

import com.example.trainningspringproject.Exeptions.EmptyException;
import com.example.trainningspringproject.entity.Machine;
import com.example.trainningspringproject.repository.MachineRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MachineService {
    @Autowired
    private MachineRepository machineRepositor;
    public List<Machine> listAll(int user) throws Exception {
        Machine[] userMachines = machineRepositor.findUserMachines(user);
        if(userMachines.length == 0){
            throw new Exception("The user don't have any machine");
        }
        else{
            return List.of(userMachines);
        }
    }

    public boolean checkMachineById(int id){
        boolean exists =  machineRepositor.existsById(id);
        if(!exists){
            return false;
        }
        return true;
    }
    public void deleteMachine(int user) throws Exception {

        int [] userMachines = machineRepositor.findMachinesByUserId(user);
        if(userMachines.length == 0){
            throw new Exception("The user don't have any machine");
        }else {
             for(int i=0; i<userMachines.length; i++) {
                 machineRepositor.deleteById(userMachines[i]);
            }
        }
    }

    public Machine addNewMachine(Machine machine){
       return machineRepositor.save(machine);
    }
@Transactional
   public Machine updateMachine(int id, String Name, String Location) throws EmptyException {
        Machine machine = machineRepositor.findById(id).orElseThrow(()-> new IllegalStateException(
                "Machine with id" + id +"does not exists"
        ));
        System.out.println(Name);
        if(Name != null &&
        Name.length() > 0 &&
        !Objects.equals(machine.getName(),Name)){
            machine.setName(Name);
        }

        if(Location != null &&
                Location.length() > 0 &&
                !Objects.equals(machine.getLocation(),Location)){
            machine.setLocation(Location);
        }

        return machine;
    }

}
