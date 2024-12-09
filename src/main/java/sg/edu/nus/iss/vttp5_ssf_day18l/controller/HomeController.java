package sg.edu.nus.iss.vttp5_ssf_day18l.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession; //the import for session
import sg.edu.nus.iss.vttp5_ssf_day18l.model.Person;

import java.util.*;

@Controller
@RequestMapping("/persons")
public class HomeController {
    
    @GetMapping("/home")
    public String getHome() {
        return "home";
    }

    @GetMapping("/create")
    public String createPerson(HttpSession session, Model model){
        Person person = new Person();
        model.addAttribute("person", person);
        return "create";
    }

    @PostMapping("/create")
    public String postCreatePerson(@ModelAttribute Person person, HttpSession session, Model model){
        
        List<Person> personList = (List<Person>) session.getAttribute("personList");
        
        if (personList == null){
            personList = new ArrayList<>();
        }
        personList.add(person);
        session.setAttribute("personList", personList);

        //session.setAttribute("person",person);
        return "redirect:/persons/home";
    }

    @GetMapping("/invalidate")
    public String invalidate(HttpSession session){
        session.invalidate();
        return "redirect:/persons/home";
    }

    @GetMapping("/personList")
    public String getPersonList(HttpSession session, Model model){
        List<Person> personList = (List<Person>) session.getAttribute("personList");
        
        if (personList == null){
            personList = new ArrayList<>();
        }

        model.addAttribute("personList", session.getAttribute("personList"));
        return "personlist";
    }

}
