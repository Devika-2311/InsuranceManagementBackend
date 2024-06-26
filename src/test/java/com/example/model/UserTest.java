package com.example.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class UserTest {

    @Test
    public void testUserConstructorAndGetters() {
        LocalDate dob = LocalDate.of(1990, 1, 1);
        LocalDate createdAt = LocalDate.now();
        User user = new User(1L, "John", "Doe", dob, "1234567890", "john.doe@example.com", "123 Main St", "password", "admin", createdAt);

        assertEquals(1L, user.getUserId());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals(dob, user.getDob());
        assertEquals("1234567890", user.getPhoneNo());
        assertEquals("john.doe@example.com", user.getEmailId());
        assertEquals("123 Main St", user.getAddress());
        assertEquals("password", user.getPassword());
        assertEquals("admin", user.getRole());
        assertEquals(createdAt, user.getCreatedAt());
    }

    @Test
    public void testUserSetters() {
        User user = new User();
        LocalDate dob = LocalDate.of(1990, 1, 1);
        LocalDate createdAt = LocalDate.now();

        user.setUserId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setDob(dob);
        user.setPhoneNo("1234567890");
        user.setEmailId("john.doe@example.com");
        user.setAddress("123 Main St");
        user.setPassword("password");
        user.setRole("admin");
        user.setCreatedAt(createdAt);

        assertEquals(1L, user.getUserId());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals(dob, user.getDob());
        assertEquals("1234567890", user.getPhoneNo());
        assertEquals("john.doe@example.com", user.getEmailId());
        assertEquals("123 Main St", user.getAddress());
        assertEquals("password", user.getPassword());
        assertEquals("admin", user.getRole());
        assertEquals(createdAt, user.getCreatedAt());
    }

    @Test
    public void testToString() {
        LocalDate dob = LocalDate.of(1990, 1, 1);
        LocalDate createdAt = LocalDate.now();
        User user = new User(1L, "John", "Doe", dob, "1234567890", "john.doe@example.com", "123 Main St", "password", "admin", createdAt);

        String expectedString = "User [userId=1, firstName=John, lastName=Doe, dob=1990-01-01, phoneNo=1234567890, emailId=john.doe@example.com, address=123 Main St, password=password, role=admin, createdAt=" + createdAt + "]";
        assertEquals(expectedString, user.toString());
    }

    @Test
    public void testDefaultConstructor() {
        User user = new User();
        assertNotNull(user);
    }
}
