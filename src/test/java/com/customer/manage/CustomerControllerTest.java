package com.customer.manage;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;
import java.util.Arrays;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhoneNumber("123-456-7890");
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER"})
    public void testGetAllCustomers() throws Exception {
        when(customerService.findAll()).thenReturn(Arrays.asList(customer));

        mockMvc.perform(get("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER"})
    public void testGetCustomerById() throws Exception {
        UUID id = customer.getId();
        when(customerService.findById(id)).thenReturn(Optional.of(customer));

        mockMvc.perform(get("/api/customers/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER"})
    public void testGetCustomerByEmail() throws Exception {
        String email = customer.getEmail();
        when(customerService.findByEmail(email)).thenReturn(Optional.of(customer));

        mockMvc.perform(get("/api/customers/search")
                        .param("email", email)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER"})
    public void testGetCustomerByPhoneNumber() throws Exception {
        String phoneNumber = customer.getPhoneNumber();
        when(customerService.findByPhoneNumber(phoneNumber)).thenReturn(Optional.of(customer));

        mockMvc.perform(get("/api/customers/search")
                        .param("phoneNumber", phoneNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER"})
    public void testCreateCustomer() throws Exception {
        when(customerService.save(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"phoneNumber\":\"123-456-7890\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER"})
    public void testUpdateCustomer() throws Exception {
        UUID id = customer.getId();
        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(id);
        updatedCustomer.setName("Jane Smith");
        updatedCustomer.setEmail("jane.smith@example.com");
        updatedCustomer.setPhoneNumber("098-765-4321");

        when(customerService.findById(id)).thenReturn(Optional.of(customer));
        when(customerService.updateCustomer(eq(id), any(Customer.class))).thenReturn(Optional.of(updatedCustomer));

        mockMvc.perform(put("/api/customers/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Jane Smith\",\"email\":\"jane.smith@example.com\",\"phoneNumber\":\"098-765-4321\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Smith"))
                .andExpect(jsonPath("$.email").value("jane.smith@example.com"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER"})
    public void testDeleteCustomer() throws Exception {
        UUID id = customer.getId();
        // Ensure the customer is found when findById is called
        when(customerService.findById(id)).thenReturn(Optional.of(customer));

        // Simulate successful deletion
        doNothing().when(customerService).deleteById(id);

        mockMvc.perform(delete("/api/customers/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
