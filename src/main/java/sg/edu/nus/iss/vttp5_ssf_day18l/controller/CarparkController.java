package sg.edu.nus.iss.vttp5_ssf_day18l.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.vttp5_ssf_day18l.model.Carpark;
import sg.edu.nus.iss.vttp5_ssf_day18l.service.CarparkService;
//IMPT THIS CONTROLLER WORKS WITH WK17L CODE. NEED TO RUN THAT ON PORT 3000 THIS ON PORT 2000
@Controller
@RequestMapping("/carpark")
public class CarparkController {
    
    @Autowired
    CarparkService carparkService;

    @GetMapping("showList")
    public String getCarparkList(Model model){
        List<Carpark> carparkList = carparkService.getCarparkList();
        model.addAttribute("carparkList", carparkList);

        return "carparktable";
    }

}
