package com.example;

import com.example.dto.PersonDTO;
import com.example.dto.request.AddressRequest;
import com.example.dto.request.PersonRequest;
import com.example.entity.Address;
import com.example.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BaseController {

    private final BaseService baseService;

    @GetMapping("/find-all-person")
    public List<PersonDTO> findAllPerson() {
        return baseService.findAllPersonDTO();
    }

    @GetMapping("/find-all-address")
    public List<Address> findAllAddress() {
        return baseService.findAllAddress();
    }

    @PostMapping("/create-person")
    public void createPerson(@RequestBody PersonRequest personRequest) {
        this.baseService.createPerson(personRequest);
    }

    @PutMapping("/update-person/{id}")
    public void updatePerson(@RequestBody PersonRequest request, @PathVariable("id") Optional<Long> idOpt){
        idOpt.ifPresent(id -> this.baseService.updatePerson(request, id));
    }

    @PostMapping("/create-address")
    public void createAddress(@RequestBody AddressRequest request) {
        this.baseService.createAddress(request);
    }
    
    @DeleteMapping("/delete-address/{id}")
    public void deleteAddress(@PathVariable("id") Optional<Long> idOpt){
        idOpt.ifPresent(this.baseService::deleteAddress);
    }
}
