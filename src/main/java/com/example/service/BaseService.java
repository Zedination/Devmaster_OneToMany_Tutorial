package com.example.service;

import com.example.dto.PersonDTO;
import com.example.dto.request.AddressRequest;
import com.example.dto.request.PersonRequest;
import com.example.entity.Address;
import com.example.entity.Person;

import java.util.List;

public interface BaseService {

    List<Person> findAllPerson();

    List<PersonDTO> findAllPersonDTO();

    List<Address> findAllAddress();

    void createPerson(PersonRequest request);

    void updatePerson(PersonRequest request, Long id);

    void createAddress(AddressRequest request);

    void deleteAddress(Long id);
}
