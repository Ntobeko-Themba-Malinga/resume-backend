package com.resume.resume.service;

import com.resume.resume.model.Contact;

import java.util.List;

public interface ContactService {

    public List<Contact> retrieveAllContact();

    public void addContact(Contact contact);

    public void deleteContact(long contactId);

    public void updateContact(long contactId,
                              String name,
                              String email,
                              String message);
}
