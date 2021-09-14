package com.example.service.impl;

import com.example.dto.PersonDTO;
import com.example.dto.request.AddressRequest;
import com.example.dto.request.PersonRequest;
import com.example.entity.Address;
import com.example.entity.Person;
import com.example.repository.AddressRepository;
import com.example.repository.PersonRepository;
import com.example.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BaseServiceImpl implements BaseService {

    private final PersonRepository personRepository;

    private final AddressRepository addressRepository;

    @Override
    public List<Person> findAllPerson() {
        return personRepository.findAll();
    }

    @Override
    public List<PersonDTO> findAllPersonDTO() {
        return this.personRepository.findAll().stream().map(this::personMapper)
                .collect(Collectors.toList());
    }

    @Override
    public List<Address> findAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public void createPerson(PersonRequest request) {
        Optional<Address> address = this.addressRepository.findById(request.getAddressId());

        Person person = new Person();
        person.setName(request.getName());
        address.ifPresent(person::setAddress);
        this.personRepository.save(person);
    }

    @Override
    public void updatePerson(PersonRequest request, Long id) {
        Optional<Person> personOptional = this.personRepository.findById(id);
        personOptional.ifPresent(person -> {
            person.setName(request.getName());
            Optional<Address> addressOptional = this.addressRepository.findById(request.getAddressId());
            person.setAddress(addressOptional.orElse(null));
            this.personRepository.save(person);
        });
    }

    @Override
    public void createAddress(AddressRequest request) {
        Address address = new Address();
        address.setCity(request.getCity());
        address.setProvince(request.getProvince());
        Person person = new Person();
        person.setName("test person");
        Set<Person> personSet = new HashSet<>(Collections.singletonList(person));
        address.setPersons(personSet);
        this.addressRepository.save(address);
    }

    @Override
    public void deleteAddress(Long id) {
        this.addressRepository.deleteById(id);
    }

    private PersonDTO personMapper(Person person) {
        return PersonDTO.builder()
                .id(person.getId()).name(person.getName())
                .address(person.getAddress().getCity() + " - " + person.getAddress().getProvince()).build();
    }

}
