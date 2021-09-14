package com.example.demo1.controller;

import java.util.List;
import com.example.demo1.model.Employee;
import com.example.demo1.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }


    @GetMapping("/employees")
    public String listUsers(Model model) {

        List<Employee> listEmployees = employeeService.getAllEmployees();
        model.addAttribute("listEmployees", listEmployees);
        log.info("getting list of employees");
        return "index_logged";
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {


        // create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        log.info("view new employee adding form");
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {

        // save employee to database
        employeeService.saveEmployee(employee);
        log.info("saving / updating employee");
        return "redirect:/employees";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {


        // get employee from the service
        Employee employee = employeeService.getEmployeeById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);
        log.info("show employee update form");
        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable (value = "id") long id) {

        // call delete employee method
        this.employeeService.deleteEmployeeById(id);
        log.info("delete employee");
        return "redirect:/employees";
    }



}
