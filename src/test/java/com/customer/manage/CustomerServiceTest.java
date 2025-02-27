package com.customer.manage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;
import java.util.List;
import java.util.Arrays;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhoneNumber("123-456-7890");
    }

    @Test
    public void testFindAll() {
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));

        List<Customer> customers = customerService.findAll();
        assertNotNull(customers);
        assertEquals(1, customers.size());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        UUID id = customer.getId();
        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        Optional<Customer> foundCustomer = customerService.findById(id);
        assertTrue(foundCustomer.isPresent());
        assertEquals(customer.getName(), foundCustomer.get().getName());
        verify(customerRepository, times(1)).findById(id);
    }

    @Test
    public void testFindByEmail() {
        String email = customer.getEmail();
        when(customerRepository.findByEmail(email)).thenReturn(Optional.of(customer));

        Optional<Customer> foundCustomer = customerService.findByEmail(email);
        assertTrue(foundCustomer.isPresent());
        assertEquals(customer.getEmail(), foundCustomer.get().getEmail());
        verify(customerRepository, times(1)).findByEmail(email);
    }

    @Test
    public void testFindByPhoneNumber() {
        String phoneNumber = customer.getPhoneNumber();
        when(customerRepository.findByPhoneNumber(phoneNumber)).thenReturn(Optional.of(customer));

        Optional<Customer> foundCustomer = customerService.findByPhoneNumber(phoneNumber);
        assertTrue(foundCustomer.isPresent());
        assertEquals(customer.getPhoneNumber(), foundCustomer.get().getPhoneNumber());
        verify(customerRepository, times(1)).findByPhoneNumber(phoneNumber);
    }

    @Test
    public void testSave() {
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer savedCustomer = customerService.save(customer);
        assertNotNull(savedCustomer);
        assertEquals(customer.getName(), savedCustomer.getName());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testUpdateCustomer() {
        UUID id = customer.getId();
        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(id);
        updatedCustomer.setName("Jane Smith");
        updatedCustomer.setEmail("jane.smith@example.com");
        updatedCustomer.setPhoneNumber("098-765-4321");

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<Customer> result = customerService.updateCustomer(id, updatedCustomer);

        assertTrue(result.isPresent());
        assertEquals("Jane Smith", result.get().getName());
        assertEquals("jane.smith@example.com", result.get().getEmail());
        assertEquals("098-765-4321", result.get().getPhoneNumber());

        verify(customerRepository, times(1)).save(argThat(argument ->
                argument.getName().equals("Jane Smith") &&
                        argument.getEmail().equals("jane.smith@example.com") &&
                        argument.getPhoneNumber().equals("098-765-4321")
        ));
    }



    @Test
    public void testDeleteById() {
        UUID id = customer.getId();
        doNothing().when(customerRepository).deleteById(id);

        customerService.deleteById(id);
        verify(customerRepository, times(1)).deleteById(id);
    }
}
