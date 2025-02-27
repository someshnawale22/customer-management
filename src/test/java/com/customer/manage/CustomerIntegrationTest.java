package com.customer.manage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @WithMockUser(username = "admin", roles = {"USER"})
    public void testCreateAndGetCustomer() throws Exception {
        // Create a new customer
        String customerJson = "{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"phoneNumber\":\"123-456-7890\"}";

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));

        // Verify the customer is in the database
        Customer customer = customerRepository.findByEmail("john.doe@example.com").orElse(null);
        assertThat(customer).isNotNull();
        assertThat(customer.getName()).isEqualTo("John Doe");

        // Get the customer by ID
        mockMvc.perform(get("/api/customers/" + customer.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER"})
    public void testUpdateCustomer() throws Exception {
        // Create a new customer
        Customer customer = new Customer();
        customer.setName("Jane Doe");
        customer.setEmail("jane.doe@example.com");
        customer.setPhoneNumber("098-765-4321");
        customerRepository.save(customer);

        // Update the customer
        String updatedCustomerJson = "{\"name\":\"Jane Smith\",\"email\":\"jane.smith@example.com\",\"phoneNumber\":\"098-765-4321\"}";

        mockMvc.perform(put("/api/customers/" + customer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedCustomerJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Smith"))
                .andExpect(jsonPath("$.email").value("jane.smith@example.com"));

        // Verify the update in the database
        Customer updatedCustomer = customerRepository.findById(customer.getId()).orElse(null);
        assertThat(updatedCustomer).isNotNull();
        assertThat(updatedCustomer.getName()).isEqualTo("Jane Smith");
        assertThat(updatedCustomer.getEmail()).isEqualTo("jane.smith@example.com");
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER"})
    public void testDeleteCustomer() throws Exception {
        // Create a new customer with a unique email and phone number
        Customer customer = new Customer();
        customer.setName("Jane Doe");
        customer.setEmail("jane.doe" + UUID.randomUUID() + "@example.com"); // Ensure uniqueness
        customer.setPhoneNumber("098-765-4321" + UUID.randomUUID()); // Ensure uniqueness
        customerRepository.save(customer);

        // Delete the customer
        mockMvc.perform(delete("/api/customers/" + customer.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Verify the customer is deleted
        assertThat(customerRepository.findById(customer.getId())).isEmpty();
    }

}
