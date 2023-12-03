package com.irakliM.demo.controllers;


import com.irakliM.demo.dto.AddStudent;
import com.irakliM.demo.dto.SearchStudent;
import com.irakliM.demo.dto.request.RequestData;
import com.irakliM.demo.entities.Student;
import com.irakliM.demo.services.StudentService;
import com.irakliM.demo.utils.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/student")
@PreAuthorize("hasRole('ADMIN')")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/all", method = RequestMethod.GET,produces = {"application/json"})
    public List<Student> getAll(){return studentService.getAllStudent();}

    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = {"application/json"})
    public Student  getById(@PathVariable Long id)throws Exception{return studentService.getStudentById(id);}


    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = {"application/json"})
    public Student add(@RequestBody AddStudent addStudent)throws Exception{
        GeneralUtil.checkRequiredProperties(addStudent, Arrays.asList("firstName","lastName","addressId","studyingYear"));
        return studentService.add(addStudent);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT,produces = {"application/json"})
    public  Student edit(@PathVariable Long id,@RequestBody AddStudent addStudent)throws Exception{
        GeneralUtil.checkRequiredProperties(addStudent, Arrays.asList("firstName","lastName","addressId","studyingYear"));
        return studentService.edit(id,addStudent);
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST,produces = {"application/json"})
    public Slice<Student> search(@RequestBody RequestData<SearchStudent>rd){
        return studentService.search(rd.getData(), rd.getPaging());
    }





}
