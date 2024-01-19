package com.resume.resume.controller;

import com.resume.resume.model.Contact;
import com.resume.resume.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/contact")
@AllArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public List<Contact> retrievingAllContacts() {
        return contactService.retrieveAllContact();
    }

    @PostMapping
    public void savingAContact(@RequestBody Contact contact) {
        contactService.addContact(contact);
    }

    @PutMapping
    public void updatingContact(@RequestParam Long id,
                                @RequestParam String name,
                                @RequestParam String email,
                                @RequestParam String message) {
        contactService.updateContact(id, name, email, message);
    }

    @DeleteMapping(path = "{contactId}")
    public void deletingAContact(@PathVariable("contactId") Long contactId) {
        contactService.deleteContact(contactId);
    }
}
