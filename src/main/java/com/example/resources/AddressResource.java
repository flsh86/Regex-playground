package com.example.resources;

import com.example.address.Address;
import com.example.address.AddressDTO;
import com.example.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressResource {
    private AddressService addressService;

    @Autowired
    public AddressResource(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AddressDTO>> findAll() {
        List<AddressDTO> addresses = addressService.findAll();
        return ResponseEntity.ok(addresses);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/{id}"
    )
    public ResponseEntity<AddressDTO> findById(@PathVariable Long id) {
        AddressDTO address = addressService.findById(id);
        return ResponseEntity.ok(address);
    }

    @PostMapping(
            consumes = MediaType.TEXT_PLAIN_VALUE,
            value = "/uploadAddress"
    )
    public ResponseEntity<?> addAddress(@RequestBody String file) {
        List<AddressDTO> dto = addressService.addAddress(file);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/complete")
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

}