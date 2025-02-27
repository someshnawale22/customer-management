package com.customer.manage;


import lombok.*;

/*
 * ContactInfo class represents the secondary contact details of a customer.
 * Author: Somesh Nawale
 */
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

