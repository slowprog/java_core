package com.company;

public class PersonException extends RuntimeException {
    private String personName;
    private String personId;

    public PersonException(String personName, String personId) {
        this.personName = personName;
        this.personId = personId;
    }
}
