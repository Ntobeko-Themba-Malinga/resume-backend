package com.resume.resume.service.impl;

import com.resume.resume.exception.ContactNotFoundException;
import com.resume.resume.model.Contact;
import com.resume.resume.repository.ContactRepository;
import com.resume.resume.service.ContactService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final String CONTACT_NOT_FOUND_MSG = "Contact with id %d not found!";
    private final ContactRepository contactRepository;

    @Override
    public List<Contact> retrieveAllContact() {
        return contactRepository.findAll();
    }

    @Override
    public void addContact(Contact contact) {
        contact.setDate(LocalDate.now());
        contactRepository.save(contact);
    }

    @Override
    public void deleteContact(long contactId) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ContactNotFoundException(
                        String.format(CONTACT_NOT_FOUND_MSG, contactId)
                ));
        contactRepository.delete(contact);
    }

    @Transactional
    @Override
    public void updateContact(long contactId,
                              String name,
                              String email,
                              String message) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ContactNotFoundException(
                        String.format(CONTACT_NOT_FOUND_MSG, contactId)
                ));

        if (name != null && !name.isEmpty()) contact.setName(name);

        if (email != null && !email.isEmpty()) contact.setEmail(email);

        if (message != null && !message.isEmpty()) contact.setMessage(message);
    }
}
