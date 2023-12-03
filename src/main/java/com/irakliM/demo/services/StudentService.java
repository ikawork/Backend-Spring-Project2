package com.irakliM.demo.services;

import com.irakliM.demo.dto.AddStudent;
import com.irakliM.demo.dto.SearchStudent;
import com.irakliM.demo.dto.request.Paging;
import com.irakliM.demo.entities.Address;
import com.irakliM.demo.entities.Student;
import com.irakliM.demo.repositories.StudentRepository;
import com.irakliM.demo.utils.GeneralUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.Date;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AddressService addressService;

    public List<Student> getAllStudent(){ return studentRepository.findAll();}

    public  Student getStudentById(Long id) throws Exception {
        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("RECORD_NOT_FOUND"));
    }

    @Transactional
    public Student add(AddStudent addStudent)throws Exception{
        Student student = new Student();
        student.setCreateDate(new Date());
        GeneralUtil.getCopyOf(addStudent, student);

        Address address = addressService.getById(addStudent.getAddressId());

        student.setAddress(address);
        return studentRepository.save(student);
    }

    @Transactional
    public Student edit(Long id, AddStudent addStudent) throws Exception {
        Student student = getStudentById(id);
        GeneralUtil.getCopyOf(addStudent, student);
        if (addStudent.getAddressId() != null && !addStudent.getAddressId().equals(student.getAddress().getAddressId())) {
            Address address = addressService.getById(addStudent.getAddressId());
            student.setAddress(address);
        }
        return studentRepository.save(student);
    }





    public Slice<Student> search(SearchStudent searchStudent, Paging paging){
        String name = null;
        if(searchStudent.getName() !=null && !searchStudent.getName().equals("")){
            name = "%" + searchStudent.getName() + "%";
        }
        Pageable pageable = PageRequest.of(paging.getPage(), paging.getSize(), Sort.by("create_date").descending());
        return studentRepository.search(searchStudent.getActive(),name,pageable);

    }



}
