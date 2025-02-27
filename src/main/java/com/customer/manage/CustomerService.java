package com.customer.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(UUID id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Optional<Customer> findByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> updateCustomer(UUID id, Customer updatedCustomer) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setName(updatedCustomer.getName());
                    existingCustomer.setEmail(updatedCustomer.getEmail());
                    existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
                    existingCustomer.setAddress(updatedCustomer.getAddress());
                    existingCustomer.setContactInfo(updatedCustomer.getContactInfo());
                    existingCustomer.setPersonalInfo(updatedCustomer.getPersonalInfo());
                    existingCustomer.setAdditionalPhoneNumbers(updatedCustomer.getAdditionalPhoneNumbers());
                    existingCustomer.setAdditionalEmailAddresses(updatedCustomer.getAdditionalEmailAddresses());
                    return customerRepository.save(existingCustomer);
                });
    }

    public void deleteById(UUID id) {
        customerRepository.deleteById(id);
    }
}

