package com.resume.resume.unit.service.impl;

import com.resume.resume.exception.ContactNotFoundException;
import com.resume.resume.model.Contact;
import com.resume.resume.repository.ContactRepository;
import com.resume.resume.service.impl.ContactServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactServiceImplTest {

    @Mock private ContactRepository contactRepository;
    private ContactServiceImpl underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new ContactServiceImpl(contactRepository);
    }

    @Test
    void retrieveAllContact() {
        underTest.retrieveAllContact();

        verify(contactRepository).findAll();
    }

    @Test
    void addContact() {
        // given
        Contact contact = new Contact(
                "Test User",
                "testuser@email.com",
                "This is a message from test user",
                LocalDate.now()
        );

        // act
        underTest.addContact(contact);

        // then
        ArgumentCaptor<Contact> contactArgumentCaptor = ArgumentCaptor
                .forClass(Contact.class);
        verify(contactRepository).save(contactArgumentCaptor.capture());

        assertThat(contactArgumentCaptor.getValue()).isEqualTo(contact);
    }

    @Test
    void deleteContactFound() {
        // Given
        long contactId = 1;
        Contact contact = new Contact(
                "Test User 2",
                "testuser2@email.com",
                "This is a message from test user 2",
                LocalDate.now()
        );

        when(contactRepository.findById(contactId))
                .thenReturn(Optional.of(contact));

        // act
        underTest.deleteContact(contactId);

        // then
        ArgumentCaptor<Contact> contactArgumentCaptor = ArgumentCaptor
                .forClass(Contact.class);
        verify(contactRepository).delete(contactArgumentCaptor.capture());

        assertThat(contactArgumentCaptor.getValue()).isEqualTo(contact);
    }

    @Test
    void deleteContactNotFound() {
        // Given
        long contactId = 1;

        // Act
        // Then
        assertThatThrownBy(() -> underTest.deleteContact(contactId))
                .isInstanceOf(ContactNotFoundException.class)
                .hasMessage("Contact with id " + contactId + " not found!");
    }

    @Test
    void updateContactExistingContact() {
        // Given
        long contactId = 1;
        String name = "Test User";
        String email = "testuser@email.com";
        String message = "This is a message from the test user";
        Contact contact = new Contact(
                name,
                email,
                message,
                LocalDate.now()
        );

        when(contactRepository.findById(contactId))
                .thenReturn(Optional.of(contact));

        // act
        email = "updatedtestuser@email.com";
        underTest.updateContact(contactId,
                null,
                email,
                null);

        // then
        assertThat(contact.getName()).isEqualTo(name);
        assertThat(contact.getEmail()).isEqualTo(email);
        assertThat(contact.getMessage()).isEqualTo(message);
    }

    @Test
    void updateContactNotExistingContact() {
        // Given
        long contactId = 1;
        String name = "Test User";
        String email = "testuser@email.com";
        String message = "This is a message from the test user";

        // Act
        // Then
        assertThatThrownBy(() -> underTest.updateContact(
                contactId,
                name,
                email,
                message
        ));
    }
}