package com.customer.manage;


import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfo {
    private String secondaryEmail;
    private String faxNumber;
    private String socialMediaProfile;
}

