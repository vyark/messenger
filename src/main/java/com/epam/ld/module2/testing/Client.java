package com.epam.ld.module2.testing;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Email;

/**
 * The type Client.
 */
@Getter
@Setter
public class Client {
    @Value("${address}")
    @Email
    private String addresses;
    private String subject;

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
