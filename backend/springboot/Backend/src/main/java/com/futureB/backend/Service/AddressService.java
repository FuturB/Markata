package com.futureB.backend.Service;

import com.futureB.backend.Entity.Address;
import com.futureB.backend.dtos.AddressDTO;
import com.futureB.backend.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AddressService {

    @Autowired
    private final AddressRepository addressRepository;

    public AddressDTO addressToDTO(Address address){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setUnit_number(address.getUnit_number());
        addressDTO.setState(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setPostal_code(address.getPostal_code());
        addressDTO.setCountry(address.getCountry());
        return addressDTO;
    }

//    public List<AddressDTO> addressToDTO(){
//        return addressRepository.findAll()
//                .stream()
//    }
}
