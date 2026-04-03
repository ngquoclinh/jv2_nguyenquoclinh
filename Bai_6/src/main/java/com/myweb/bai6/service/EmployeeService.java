package com.myweb.bai6.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    public List<HashMap<String, Object>> getData() {
        List<HashMap<String, Object>> EMPLOYERS = new ArrayList<>();
        //Employee 1
        HashMap<String, Object> employee1 = new HashMap<>();
        employee1.put("name", "Alice");
        employee1.put("email", "alice.smith@example.com");
        employee1.put("department", "HR");
        employee1.put("salary", 7000);
        EMPLOYERS.add(employee1);
        //Employee 2
        HashMap<String, Object> employee2 = new HashMap<>();
        employee2.put("name", "Johnson");
        employee2.put("email", "js.me@gmail.com");
        employee2.put("department", "HR");
        employee2.put("salary", 5000);
        EMPLOYERS.add(employee2);
        //Employee 3
        HashMap<String, Object> employee3 = new HashMap<>();
        employee3.put("name", "Bob");
        employee3.put("email", "bod.dylan@dylan.com");
        employee3.put("department", "IT");
        employee3.put("salary", 3000);
        EMPLOYERS.add(employee3);

        return EMPLOYERS;
    }

    // Unit 5.2
    public List<HashMap<String, Object>> searchEmployees(String keyword) {
        List<HashMap<String, Object>> employees = getData();
        List<HashMap<String, Object>> results = new ArrayList<>();

        for(HashMap<String, Object> emp : employees) {
            String name = emp.get("name").toString().toLowerCase();
            if(name.contains(keyword.toLowerCase())) {
                results.add(emp);
            }
        }
        return results;
    }

    // Unit 5.3
    public List<HashMap<String, Object>> searchEmployees2(String department, Integer min_salary) {
        List<HashMap<String, Object>> employees = getData();
        List<HashMap<String, Object>> results = new ArrayList<>();

        for(HashMap<String, Object> emp : employees) {
            String depa = emp.get("department").toString().toLowerCase();
            Integer sala = (Integer) emp.get("salary");
            if(depa.contains(department.toLowerCase()) && sala > min_salary ) {
                results.add(emp);
            }
        }
        return results;
    }

    // Unit 5.4
    private List<HashMap<String, Object>> EMPLOYERS = new ArrayList<>(getData());
    public List<HashMap<String, Object>> createEmployees(String name, String email, String department, Integer salary) {

        HashMap<String, Object> newEmployee = new HashMap<>();
        newEmployee.put("name", name);
        newEmployee.put("email", email);
        newEmployee.put("department", department);
        newEmployee.put("salary", salary);

        EMPLOYERS.add(newEmployee);
        return EMPLOYERS;
    }

    // Unit 5.5
    public List<HashMap<String, Object>> createEmployees2(Map<String, Object> param) {
        HashMap<String, Object> newEmployee = new HashMap<>(param);
        EMPLOYERS.add(newEmployee);
        return EMPLOYERS;
    }

    // Unit 5.6
    public List<HashMap<String, Object>> updateEmployees(Map<String, Object> param) {
        String name = param.get("name").toString();

        for (HashMap<String, Object> emp : EMPLOYERS) {
            if (emp.get("name").toString().equalsIgnoreCase(name)) {
                if (param.containsKey("email")) emp.put("email", param.get("email"));
                if (param.containsKey("department")) emp.put("department", param.get("department"));
                if (param.containsKey("salary")) emp.put("salary", param.get("salary"));
                break;
            }
        }
        return EMPLOYERS;
    }

}
