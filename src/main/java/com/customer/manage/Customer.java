package com.customer.manage;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/*
 * Customer class represents the details of a customer including their contact information,
 * address, and personal information.
 * Author: Somesh Nawale
 */

@Entity
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType") // <-------------------------- THIS LINE
    private UUID id;

    @NotNull(message = "Name must not be null")
    @Column(nullable = false)
    private String name;

    @Email(message = "Email should be valid")
    @NotNull(message = "Email must not be null")
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull(message = "Phone number must not be null")
    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Embedded
    private Address address;

    @Embedded
    private ContactInfo contactInfo;

    @Embedded
    private PersonalInfo personalInfo;

    @ElementCollection
    @CollectionTable(name = "customer_phone_numbers", joinColumns = @JoinColumn(name = "customer_id"))
    @Column(name = "phone_number")
    private List<String> additionalPhoneNumbers;

    @ElementCollection
    @CollectionTable(name = "customer_email_addresses", joinColumns = @JoinColumn(name = "customer_id"))
    @Column(name = "email_address")
    private List<@Email String> additionalEmailAddresses;

}