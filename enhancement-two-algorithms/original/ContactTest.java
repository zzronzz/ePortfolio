package com.contacts.test;
import com.contacts.Contact;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {
    /**
     * Tests the successful creation of a valid Contact object
     * Verifies that all constructor parameters are correctly set
     */
    @Test
    public void testValidContact() {
        Contact contact = new Contact("C001234567", "John", "Doe", "0123456789", "123 Main Street");
        assertNotNull(contact);
        assertEquals("C001234567", contact.getContactId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("0123456789", contact.getPhone());
        assertEquals("123 Main Street", contact.getAddress());
    }

    /**
     * Tests that an IllegalArgumentException is thrown when 
     * creating a Contact with an invalid (too long) contact ID
     */
    @Test
    public void testContactIdBoundaryCases() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("C01234567891", "John", "Doe", "1234567890", "123 Main St")); // 11 characters
    }

    /**
     * Verifies that a contact can be created with a phone number 
     * starting with a leading zero
     */
    @Test
    public void testPhoneWithLeadingZero() {
        Contact contact = new Contact("C009", "MaxLen", "MaxLast", "0123456789", "456 Elm St");
        assertEquals("0123456789", contact.getPhone());
    }

    /**
     * Tests that all setter methods work correctly with valid input
     * Ensures each setter updates the corresponding field as expected
     */
    @Test
    public void testSettersWithValidData() {
        Contact contact = new Contact("C100", "Alice", "Smith", "9876543210", "456 Maple Ave");
        contact.setFirstName("Bob");
        contact.setLastName("Johnson");
        contact.setPhone("2223334444");
        contact.setAddress("789 Oak St");
        assertEquals("Bob", contact.getFirstName());
        assertEquals("Johnson", contact.getLastName());
        assertEquals("2223334444", contact.getPhone());
        assertEquals("789 Oak St", contact.getAddress());
    }

    /**
     * Verifies that setters throw IllegalArgumentException 
     * when invalid data is provided
     * Checks for invalid first name length, phone number, and null address
     */
    @Test
    public void testSettersWithInvalidData() {
        Contact contact = new Contact("C200", "Charlie", "Brown", "1112223333", "101 Pine St");
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName("NameTooLongForTest"));
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("short"));
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress(null));
    }

    /**
     * Tests that address is automatically trimmed of leading and trailing whitespaces
     */
    @Test
    public void testAddressTrimming() {
        Contact contact = new Contact("C300", "Eve", "Adams", "5555555555", "   789 Oak Lane   ");
        assertEquals("789 Oak Lane", contact.getAddress(), "Address should be trimmed.");
    }

    /**
     * Checks handling of maximum allowed lengths for contact fields
     * Ensures boundary conditions are handled correctly
     */
    @Test
    public void testBoundaryLengthForNames() {
        Contact contact = new Contact("C400", "1234567890", "1234567890", "1234567890", "123456789012345678901234567890");
        assertEquals("1234567890", contact.getFirstName());
        assertEquals("1234567890", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123456789012345678901234567890", contact.getAddress());
    }

    /**
     * Verifies that setters reject empty string inputs
     * Ensures all setter methods validate input against empty strings
     */
    @Test
    public void testEmptyStringForSetters() {
        Contact contact = new Contact("C500", "Eve", "Adams", "5555555555", "202 Birch St");
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName(""));
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName(""));
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone(""));
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress(""));
    }

    /**
     * Tests that an IllegalArgumentException is thrown 
     * when attempting to create a contact with a non-numeric phone number
     */
    @Test
    public void testNonNumericPhone() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("C600", "John", "Doe", "12345ABCDE", "456 Elm St"));
    }

    /**
     * Verifies that address can be set to the maximum allowed length
     */
    @Test
    public void testAddressWithMaxLength() {
        Contact contact = new Contact("C700", "Ann", "Lee", "9999999999", "123456789012345678901234567890");
        assertEquals("123456789012345678901234567890", contact.getAddress());
    }
}