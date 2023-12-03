package com.irakliM.demo.services;


import com.irakliM.demo.entities.Address;
import com.irakliM.demo.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {


    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAll(){return addressRepository.findAll();}

    public Address getById(Long id) throws Exception {
        return addressRepository.findById(id).orElseThrow(() -> new Exception("RECORD_NOT_FOUND"));
    }





}
