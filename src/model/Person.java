package model;

import anotation.Init;
import anotation.Json;
import anotation.JsonSerializable;

@JsonSerializable
public class Person {

    @Json
    private String firstName;

    @Json(key="apelido")
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Init
    private void validate(){
        this.firstName = firstName.substring(0,1).toUpperCase().concat(firstName.substring(1).toLowerCase());
        this.lastName = lastName.substring(0,1).toUpperCase().concat(lastName.substring(1).toLowerCase());
    }



}
