package com.example.exwecise01;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/Api/v1/employee")
public class EmployeesController {
    ArrayList<Employees> arr = new ArrayList<>();
    @GetMapping
    public ResponseEntity getEmployee(){
        return ResponseEntity.status(200).body(arr);
    }
    @PostMapping
   public ResponseEntity addEmployee(@RequestBody @Valid Employees employees,Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiComment (message,400));
        }
        if (employees.getEmploymentYear()<1982 && employees.getEmploymentYear()>2022){
            return ResponseEntity.status(400).body(new ApiComment("Wrong year",400));
        }
        employees.setOnLeave(false);
        arr.add(employees);
        return ResponseEntity.status(201).body(new ApiComment ("New employee add",201));
    }
    @PutMapping("/{index}")
    public ResponseEntity putEmployee(@PathVariable @Valid int index,Employees employees , Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiComment(message, 400));

        }
        if (index >= arr.size()){
            return ResponseEntity.status(400).body(new ApiComment("invalid text", 400));
        }
        arr.remove(index);
        return ResponseEntity.status(201).body(new ApiComment ("update employee",201));
    }
    @DeleteMapping("/{index}")
    public ResponseEntity deleteEmployee(@PathVariable int index){
    if (index>=arr.size()){
        return ResponseEntity.status(400).body(new ApiComment("invalid index", 400));
    }
        return ResponseEntity.status(201).body(new ApiComment ("update employee",201));
    }

}
