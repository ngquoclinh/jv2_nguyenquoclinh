package com.myweb.bai6.controller;

import com.myweb.bai6.models.Book;
import com.myweb.bai6.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DemoController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/api/items")
    public ResponseEntity getProduct(
            @RequestParam String name,
            @RequestParam String category,
            @RequestParam double price
    ) {
        System.out.println("Tên sản phẩm: " + name);
        System.out.println("Loại: " + category);
        System.out.println("Giá: " + price);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Unit 5.2
    @GetMapping("/api/search_employees")
    public ResponseEntity<List<HashMap<String, Object>>> searchEmployees(
            @RequestParam String keyword
    ) {
        return ResponseEntity.ok(employeeService.searchEmployees(keyword));
    }

    //Unit 5.3
    @GetMapping("/api/search_employees_2")
    public ResponseEntity<List<HashMap<String, Object>>> searchEmployees2(
            @RequestParam String department,
            @RequestParam Integer min_salary
    ) {
        return  ResponseEntity.ok(employeeService.searchEmployees2(department, min_salary));
    }

    // Unit 5.4
    @PostMapping("/api/employee/create")
    public ResponseEntity<List<HashMap<String, Object>>> createEmployees(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String department,
            @RequestParam Integer salary) {
        return ResponseEntity.ok(employeeService.createEmployees(name, email,department, salary));
    }

    // Unit 5.5
    @PostMapping("/api/employee/create_2")
    public ResponseEntity<List<HashMap<String, Object>>> createEmployees2(
            @RequestBody Map<String, Object> param) {
        return ResponseEntity.ok(employeeService.createEmployees2(param));
    }

    // Unit 5.6
    @PutMapping("/api/employee/update")
    public ResponseEntity<List<HashMap<String, Object>>> updateEmployees(
            @RequestBody Map<String, Object> param) {

        return ResponseEntity.ok(employeeService.updateEmployees(param));
    }


    public static void main(String[] args) {
        Book book = Book.builder()
                .title("The Hitchhiker to the Galaxy")
                .author("Douglas Adams")
                .pages(224)
                .isbn("978-0345391803")
                .summary("A hilarious adventure")
                .build();

        System.out.println(book.getTitle());
    }
}
