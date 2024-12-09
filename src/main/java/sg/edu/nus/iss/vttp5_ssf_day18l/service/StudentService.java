package sg.edu.nus.iss.vttp5_ssf_day18l.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.vttp5_ssf_day18l.model.Student;
import sg.edu.nus.iss.vttp5_ssf_day18l.repo.HashRepo;
import sg.edu.nus.iss.vttp5_ssf_day18l.constants.*;

@Service
public class StudentService {
    
    @Autowired
    HashRepo studentRepo;

    @Autowired
    DataService dataService;

    public Map<String,Student> getStudentMap() {
        Map<String,String> studentStringMap = studentRepo.entries(Constant.redisListName);
        Map<String, Student> studentMap = new HashMap<>();
        for (Map.Entry<String,String> studentStringEntry : studentStringMap.entrySet()){
            String[] studentStringSplit = studentStringEntry.getValue().split(",");
            String name = studentStringSplit[0];
            String email = studentStringSplit[1];
            Integer phoneNumber = Integer.parseInt(studentStringSplit[2]);
            Date dateOfBirth = new Date(Long.parseLong(studentStringSplit[3]));
            Student student = new Student(name,email,phoneNumber,dateOfBirth);
            studentMap.put(studentStringEntry.getKey(),student);
        }

        return studentMap;
    }


    public List<String> loadIds(){
        Map<String,String> studentStringMap = studentRepo.entries(Constant.redisListName);
        List<String> idList = new ArrayList<String>(studentStringMap.keySet());
        return idList;
    }

    public void addStudent(Student student){
        String studentString = student.toString();
        String hexCode = dataService.getHexCode();
        studentRepo.put(Constant.redisListName, hexCode, studentString);
    }

    public Boolean hasKey(String key){
        return studentRepo.hasKey(Constant.redisListName, key);
    }

    public Student getStudent(String key){
        String[] studentStringSplit = ((String)studentRepo.get(Constant.redisListName, key)).split(",");
        String name = studentStringSplit[0];
        String email = studentStringSplit[1];
        Integer phoneNumber = Integer.parseInt(studentStringSplit[2]);
        Date dateOfBirth = new Date(Long.parseLong(studentStringSplit[3]));
        Student student = new Student(name,email,phoneNumber,dateOfBirth);
        return student;
    }
    
} 
