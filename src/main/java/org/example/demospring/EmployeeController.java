package org.example.demospring;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    ArrayList<Employee> employees = new ArrayList<>();

    // (B) ADD EMPLOYEE - POST

    @PostMapping
    public ResponseEntity<Employee> addEmployee(
            @RequestBody Employee emp) {

        employees.add(emp);

        return ResponseEntity.ok(emp);
    }


    // (C) GET ALL EMPLOYEES

    @GetMapping
    public List<Employee> getAllEmployees() {

        return employees;
    }


    // GET EMPLOYEE BY ID

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(
            @PathVariable int id) {

        for (Employee e : employees) {

            if (e.getId() == id) {
                return ResponseEntity.ok(e);
            }
        }

        return ResponseEntity.notFound().build();
    }


    // (D) UPDATE EMPLOYEE - PUT

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable int id,
            @RequestBody Employee newEmp) {

        for (Employee e : employees) {

            if (e.getId() == id) {

                e.setName(newEmp.getName());
                e.setDepartment(newEmp.getDepartment());
                e.setSalary(newEmp.getSalary());

                return ResponseEntity.ok(e);
            }
        }

        return ResponseEntity.notFound().build();
    }


    // (E) DELETE EMPLOYEE - DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable int id) {

        Iterator<Employee> iterator = employees.iterator();

        while (iterator.hasNext()) {

            Employee e = iterator.next();

            if (e.getId() == id) {

                iterator.remove();

                return ResponseEntity.ok(
                        "Employee Deleted Successfully"
                );
            }
        }

        return ResponseEntity.status(404)
                .body("Employee Not Found");
    }
}