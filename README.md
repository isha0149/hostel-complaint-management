# Hostel Complaint Management System

This is a web application designed to streamline the process of submitting, tracking, and managing complaints and service requests within a college hostel.

## Features

  - **Complaint Submission:** Students can easily submit new complaints with details like name, room number, category, and description.
  - **Complaint Tracking:** Users can view the status of their complaints (pending, in-progress, resolved) and filter them by status or category.
  - **Reporting Dashboard:** An administrative dashboard provides a quick overview of key metrics, including the total number of complaints, and a breakdown by status and category.

## Technologies Used

### Frontend

  - HTML
  - CSS
  - JavaScript

### Backend

  - Java
  - Spring Boot
  - Maven
  - H2 Database (In-memory)

## How to Run Locally

1.  **Prerequisites:** Ensure you have Java 17, Maven, and Git installed on your system.

2.  **Clone the repository:**

    ```bash
    git clone https://github.com/isha0149/hostel-complaint-management.git
    ```

3.  **Build the application:**
    Navigate to the project's root directory and build the project using Maven.

    ```bash
    mvn clean package
    ```

4.  **Run the application:**
    Once the build is successful, run the application using the generated executable JAR file.

    ```bash
    java -jar target/complaint-management-0.0.1-SNAPSHOT.jar
    ```

5.  **Access the web application:**
    Open your web browser and navigate to `http://localhost:8080` to view the website.
