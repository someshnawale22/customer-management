package com.customer.manage;


import lombok.*;

import javax.persistence.Embeddable;

/*
 * PersonalInfo class represents the personal details of a customer,
 * including their date of birth and gender.
 * Author: Somesh Nawale
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfo {
    private String dateOfBirth;  // Example: "1980-01-01"
    private String gender;       // Example: "Male", "Female", "Other"
}

