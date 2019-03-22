package me.jinhyun.springdatajpa;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String state;
    private String city;
    private String street;
    private String zipCode;
}
