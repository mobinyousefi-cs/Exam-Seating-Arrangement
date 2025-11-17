# Exam Seating Arrangement System

A complete JSP/Servlet and MySQL-based web application for managing students, rooms, exams, and generating automated seating allocations.  
This system is designed to support exam administrators in efficiently organizing seat assignments while maintaining clarity, scalability, and maintainability.

---

## 1. Features

### **Core Functionalities**
- **Admin Login System** (demo: `admin / admin123`)
- **Student Management**
  - Add, view, delete students
- **Room Management**
  - Add exam halls with capacity
- **Exam Management**
  - Define courses, dates, and start times
- **Seating Allocation**
  - Automatically generates seating based on room capacity and student list
  - Displays room-wise seat numbering and assigned students
- **Fully Modular Architecture** using MVC + DAO + Services
- **Bootstrap-based UI** (Responsive & minimal)

---

## 2. Technology Stack

| Layer | Technology |
|-------|------------|
| Backend | Java 17, JSP, Servlets, JDBC |
| Frontend | JSP, HTML5, CSS3, Bootstrap 5 |
| Database | MySQL 8.x |
| Build Tool | Maven |
| Server | Apache Tomcat 9/10 |

---

## 3. Project Structure

```
exam-seating-arrangement/
│
├── pom.xml
├── schema_mysql_exam_seating.sql
│
├── src/main/java/com/mobinyousefi/cs/examseating/
│   ├── config/
│   ├── dao/
│   ├── model/
│   ├── service/
│   └── web/
│
└── src/main/webapp/
    ├── login.jsp
    ├── WEB-INF/web.xml
    └── WEB-INF/views/
        ├── layout/header.jspf
        ├── layout/footer.jspf
        ├── dashboard.jsp
        ├── students.jsp
        ├── rooms.jsp
        ├── exams.jsp
        └── allocations.jsp
```

---

## 4. Database Setup

1. Open MySQL or phpMyAdmin.
2. Run the provided SQL file:

```
schema_mysql_exam_seating.sql
```

This will create:
- `students`
- `rooms`
- `exams`
- `allocations`

---

## 5. Configuration

Modify database credentials inside:
```
src/main/webapp/WEB-INF/web.xml
```

```
<context-param>
    <param-name>DB_URL</param-name>
    <param-value>jdbc:mysql://localhost:3306/exam_seating_db</param-value>
</context-param>
<context-param>
    <param-name>DB_USERNAME</param-name>
    <param-value>root</param-value>
</context-param>
<context-param>
    <param-name>DB_PASSWORD</param-name>
    <param-value>password</param-value>
</context-param>
```

---

## 6. Build & Deployment

### **Using Maven**
```
mvn clean package
```
This generates:
```
target/exam-seating-arrangement.war
```

### **Deploy to Tomcat**
1. Copy the WAR file to:
   ```
   <TOMCAT_HOME>/webapps/
   ```
2. Start Tomcat.
3. Access via:
   ```
   http://localhost:8080/exam-seating-arrangement
   ```

---

## 7. Default Login

```
Username: admin
Password: admin123
```
*(Located in `AuthServlet.java`. Replace in production environments.)*

---

## 8. How Seating Allocation Works

1. Students are sorted by **roll number**.
2. Rooms are processed sequentially.
3. Seats are filled from seat `1` to `capacity`.
4. Allocations are saved to the database.

To generate allocations:
- Go to **Allocations** page → Select Exam → Click **Generate Plan**

---

## 9. Future Improvements
- Role-based authentication (Multi-admin system)
- Randomized seating instead of sequential
- PDF export of seat plans
- Bulk import of students & rooms via CSV
- REST API endpoints
- Docker + Docker Compose environment

---

## 10. Author
**Mobin Yousefi**  
GitHub: **https://github.com/mobinyousefi-cs**

---

## 11. License
This project is licensed under the **MIT License**.

