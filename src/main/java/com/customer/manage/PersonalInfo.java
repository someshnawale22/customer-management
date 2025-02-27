package com.customer.manage;


import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfo {
    private String dateOfBirth;  // Example: "1980-01-01"
    private String gender;       // Example: "Male", "Female", "Other"
}

