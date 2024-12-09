package sg.edu.nus.iss.vttp5_ssf_day18l.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
//import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import sg.edu.nus.iss.vttp5_ssf_day18l.model.Student;
import sg.edu.nus.iss.vttp5_ssf_day18l.service.DataService;
import sg.edu.nus.iss.vttp5_ssf_day18l.service.StudentService;



@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    DataService dataservice;

    @Autowired
    StudentService studentservice;


    @GetMapping("")
    public String getHome(Model model) {
        //System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO" + dataDir);
        //System.out.println("\n\n\n\n\n\n\n\n");
        return "studenthome";
    }
    
    @GetMapping("/contact")
    public String getContact(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "studentcreate";
    }

    @PostMapping("/contact")
    public String postContact(@Valid @ModelAttribute Student student, BindingResult result, Model model) throws IOException {

        if(result.hasErrors()){
            return "studentcreate";
        }

        //Global error example
        /* if (dataservice.isAgeWithinRange(person.getDateOfBirth()) == false){
            ObjectError err = new ObjectError("globalError", "Age needs to be between 10 and 100 years old");
            result.addError(err);
            return "personcreate";
        } */

        studentservice.addStudent(student);
        System.out.println ("person created");
        return "redirect:/student";
        //return HttpStatus.CREATED + "Person " + person.getName() + " is successfully created";
    }


    @GetMapping("/contact/{id}")
    public String getContactStudent(@PathVariable String id, Model model) throws IOException {
        
        if (studentservice.hasKey(id)){
            Student student = studentservice.getStudent(id);
            model.addAttribute("student",student);
            return "studentinfo";
        }
        else{
            //return HttpStatus.NOT_FOUND + ": could not find file !! "; //returning HTTPS_STATUS 
            return "cannotfind";
        }
        
    }

    @GetMapping("/contacts")
    public String getContacts( Model model) throws IOException {
        List<String> idList = studentservice.loadIds();
        model.addAttribute("idList", idList);
        return "studentlist";
        
    }
    
    
    

    
}


