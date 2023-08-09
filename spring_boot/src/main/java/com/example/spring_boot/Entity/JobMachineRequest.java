package com.example.spring_boot.Entity;

import java.util.Set;

public class JobMachineRequest {
    private Job job;
    private Set<Machine> machines;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Set<Machine> getMachines() {
        return machines;
    }

    public void setMachines(Set<Machine> machines) {
        this.machines = machines;
    }
}

/*
@RestController
@RequestMapping("/api")
public class EnrollmentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/enroll")
    public ResponseEntity<String> enrollStudentInCourses(@RequestBody StudentCourseRequest request) {
        Student student = request.getStudent();
        Set<Course> courses = request.getCourses();

        student.setCourses(courses);
        studentRepository.save(student);

        return ResponseEntity.status(HttpStatus.CREATED).body("Student enrolled in courses successfully");
    }
}

public class StudentCourseRequest {
    private Student student;
    private Set<Course> courses;

    // Getters and setters
}

 */