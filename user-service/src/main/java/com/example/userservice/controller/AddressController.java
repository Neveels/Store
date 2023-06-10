package com.example.userservice.controller;

import com.example.userservice.dto.request.AddressRequest;
import com.example.userservice.dto.response.AddressResponse;
import com.example.userservice.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<AddressResponse> createAddress(@RequestBody AddressRequest addressRequest) {
        return ResponseEntity
                .ok()
                .body(addressService.addAddress(addressRequest));
    }

    @PutMapping
    public ResponseEntity<AddressResponse> editAddress(@RequestBody AddressRequest address) {
        addressService.editAddress(address);
        return ResponseEntity
                .ok()
                .body(addressService.editAddress(address));
    }

    @GetMapping
    public ResponseEntity<List<AddressResponse>> getAllAddresses() {
        return ResponseEntity
                .ok()
                .body(addressService.getAllAddresses());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Integer id) {
        addressService.deleteById(id);
    }

    @PutMapping("/{id}/setActive")
    public ResponseEntity<List<AddressResponse>> changeAddressStatusForMain(@PathVariable Integer id) {
        return ResponseEntity
                .ok()
                .body(addressService.changeActiveAddress(id));
    }

}
