package com.employee.project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.employee.project.entity.Employee;
import com.employee.project.service.EmpService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmpController {
    
    @Autowired
    private EmpService service;
    
    @GetMapping("/")
    public String index(HttpSession session, Model model) {
    	
    	// Retrieve all employees and add them to the model    	
    	List<Employee> emp =service.getAllEmp();
    	model.addAttribute("emp", emp);
    	
    	// Retrieve the message from the session if available
        String message = (String) session.getAttribute("msg");
        if (message != null) {
            model.addAttribute("msg", message);
            session.removeAttribute("msg"); // Remove the message after use
        }
        
        return "index";
    }
    
    @GetMapping("/addemp")
    public String addEmpForm() {
        return "add_emp";
    }
    
    @PostMapping("/register")
    public String empRegister(@ModelAttribute Employee e, HttpSession session) {
        System.out.println(e);
        service.addEmp(e);
        session.setAttribute("msg", "Employee Added Successfully..!");
        return "redirect:/"; // Redirect to index page
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model m)
    {
    	Employee e=service.getEmpById(id);
    	m.addAttribute("emp", e);
    	return "edit";
    }
    @PostMapping("update")
    public String updateEmp(@ModelAttribute Employee e, HttpSession session) {
    	
    	service.addEmp(e);
    	session.setAttribute("msg", "Emp Data Update Sucessfully..!");
    	return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deleteEmp(@PathVariable int id, HttpSession session)
    {
    	service.deleteEmp(id);
    	session.setAttribute("msg", "Emp Data Delete Sucessfully..!");
    	return "redirect:/";
    }
}
