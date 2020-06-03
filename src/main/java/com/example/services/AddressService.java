package com.example.services;

import com.example.address.AddressDTO;
import com.example.mappers.AddressMapper;
import com.example.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class AddressService {
    private AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<AddressDTO> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(AddressMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AddressDTO findById(Long id) {
        return addressRepository.findById(id)
                .map(AddressMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<AddressDTO> addAddress(String file) {
        Pattern streetPattern = Pattern.compile("\\d+\\s[A-Z0-9]{1,}[a-z]+\\sSt.", Pattern.MULTILINE);
        Pattern cityPattern = Pattern.compile("([A-Z][a-z]+\\s)?[A-Z][a-z]+(-[a-z]+)?\\s[A-Z]{2}", Pattern.MULTILINE);
        Pattern zipCodePattern = Pattern.compile("\\d{5}", Pattern.MULTILINE);

        List<String> streets = streetPattern.matcher(file).results()
            .map(MatchResult::group)
            .collect(Collectors.toList());
        List<String> cities = cityPattern.matcher(file).results()
                .map(MatchResult::group)
                .collect(Collectors.toList());
        List<String> zipCodes = zipCodePattern.matcher(file).results()
                .map(MatchResult::group)
                .collect(Collectors.toList());

        int size;
        if(streets.size() != cities.size() || streets.size() != zipCodes.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            size = streets.size();
        }
        List<AddressDTO> addresses = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            addresses.add(
                    new AddressDTO(
                            null,
                            streets.get(i),
                            cities.get(i),
                            zipCodes.get(i)
                    )
            );
        }
        return addresses;
    }
}
