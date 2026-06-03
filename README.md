# CS 499 Computer Science Capstone ePortfolio

## Professional Self-Assessment

This ePortfolio contains my completed work for the CS 499 Computer Science Capstone at Southern New Hampshire University. The artifacts in this repository show my growth in software design, algorithms and data structures, and databases.

## Module Two: Code Review

For Module Two, I completed a code review of my selected artifacts for the CS 499 Computer Science Capstone. In this video, I reviewed my original projects, explained the issues I planned to improve, and connected each enhancement to the required capstone categories: software design and engineering, algorithms and data structures, and databases.

[Watch Code Review Video](https://youtu.be/kiaiKg_v-xs)

## Enhancement One: Software Design and Engineering

Artifact: Travlr Getaways  
Course: CS 465 Full Stack Development  

For Enhancement One, I improved the Travlr Getaways full-stack application by adding stronger login validation, blank-field validation for adding trips, and user feedback for add, update, and delete actions.

Folder: enhancement-one-software-design

## Enhancement Two: Algorithms and Data Structures

Artifact: Contact Service  
Course: CS 320 Software Testing, Automation, and Quality Assurance  

For Enhancement Two, I improved an existing HashMap-based Contact Service by strengthening validation, defensive programming, exception handling, and JUnit test coverage. The enhancement added validation for null contacts and null or blank contact IDs before HashMap operations. I also added helper methods for checking whether a contact exists and tracking the total number of stored contacts.

The ContactServiceTest suite increased from 6 tests to 13 tests. After the enhancement, ContactServiceTest passed 13 out of 13 tests, and ContactTest passed 10 out of 10 tests.

Folder: enhancement-two-algorithms

## Enhancement Three: Databases

Artifact: Grazioso Salvare Dashboard  
Course: CS 340 Advanced Programming Concepts  

For Enhancement Three, I improved the Grazioso Salvare Dashboard by strengthening MongoDB CRUD error handling, input validation, database connection handling, and fallback data loading. The enhanced CRUD module now provides clearer database connection messages and checks whether the MongoDB collection is available before attempting database operations.

I also updated the dashboard so it can continue running when MongoDB is unavailable. If the MongoDB connection fails or returns no records, the dashboard loads the local CSV fallback dataset. This allows the dashboard to still display table data, a breed distribution chart, and a geolocation map.

Folder: enhancement-three-databases
