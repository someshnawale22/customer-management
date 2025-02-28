package com.customer.manage;

import lombok.*;

import javax.persistence.Embeddable;
/*
 * Address class represents the address details of a customer.
 * Author: Somesh Nawale
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
