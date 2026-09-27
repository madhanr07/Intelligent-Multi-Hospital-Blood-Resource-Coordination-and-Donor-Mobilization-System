# HemoNexus - Architecture

## Overview

This document describes the architectural design of HemoNexus, including the six-layer architecture, high-level system architecture, institutional multi-hospital architecture, and the relationships between frontend, backend, database, and ML components.

**Development Status:** Module 0 - Project Specification & Development Rules (PLANNED)

---

## Six-Layer Architecture

HemoNexus follows a conceptual six-layer architecture:

```
┌─────────────────────────────────────────────────────────────┐
│                    1. Presentation Layer                    │
│  Central Admin Portal | Hospital Portal | Donor Portal      │
└─────────────────────────────────────────────────────────────┘
                              │
┌─────────────────────────────────────────────────────────────┐
│              2. Application & Business Logic Layer            │
│  REST APIs | Business Services | Workflows | Validation     │
└─────────────────────────────────────────────────────────────┘
                              │
┌─────────────────────────────────────────────────────────────┐
│               3. Intelligence & Analytics Layer               │
│  ML Service | Demand Forecasting | Risk Assessment           │
└─────────────────────────────────────────────────────────────┘
                              │
┌─────────────────────────────────────────────────────────────┐
│                   4. Data Management Layer                   │
│  MySQL Database | JPA Entities | Repositories               │
└─────────────────────────────────────────────────────────────┘
                              │
┌─────────────────────────────────────────────────────────────┐
│              5. Integration & Communication Layer            │
│  Notification Service | Email | SMS | External Integrations   │
└─────────────────────────────────────────────────────────────┘
                              │
┌─────────────────────────────────────────────────────────────┐
│                6. Security & Access Control Layer            │
│  Authentication | RBAC | Authorization | Audit Logging      │
└─────────────────────────────────────────────────────────────┘
```

### Layer 1: Presentation Layer

**Responsibilities:**
- User interface rendering
- User interaction handling
- Form validation (client-side)
- API communication
- Route protection
- Role-aware navigation

**Components:**
- Central Admin Portal
- Hospital Portal (Blood Bank Staff, Platelet Staff)
- Donor Portal

**Technology:**
- React.js
- JavaScript
- HTML
- CSS
- Bootstrap or suitable UI component library
- React Router

### Layer 2: Application & Business Logic Layer

**Responsibilities:**
- REST API endpoint implementation
- Business logic execution
- Workflow orchestration
- Request validation
- Response formatting
- Error handling

**Components:**
- REST Controllers
- Business Services
- Workflow Services
- DTOs (Data Transfer Objects)
- Validators

**Technology:**
- Java
- Spring Boot
- Spring Web
- Spring Data JPA

### Layer 3: Intelligence & Analytics Layer

**Responsibilities:**
- Blood demand forecasting
- Shortage-risk assessment
- Data analysis
- Model training and evaluation
- Decision support

**Components:**
- ML Service
- Forecasting Models
- Risk Assessment Engine
- Analytics Service

**Technology:**
- Python
- Pandas
- NumPy
- Scikit-learn
- Jupyter (for development/experimentation)

### Layer 4: Data Management Layer

**Responsibilities:**
- Data persistence
- Data retrieval
- Data integrity
- Transaction management
- Query optimization

**Components:**
- MySQL Database
- JPA Entities
- Repositories
- Database Migrations

**Technology:**
- MySQL
- Spring Data JPA
- Hibernate (via Spring Data JPA)

### Layer 5: Integration & Communication Layer

**Responsibilities:**
- Notification delivery
- External service integration
- Email communication
- SMS communication
- In-app notifications

**Components:**
- Notification Service
- Email Service
- SMS Service
- In-App Notification Service

**Technology:**
- Spring Boot
- Email libraries (e.g., JavaMail)
- SMS API integration (future)

### Layer 6: Security & Access Control Layer

**Responsibilities:**
- User authentication
- Role-based authorization
- Permission-based authorization
- Hospital-level data isolation
- Institution-level access rules
- Audit logging

**Components:**
- Authentication Service
- Authorization Service
- RBAC Engine
- Audit Logger

**Technology:**
- Spring Security
- JWT (JSON Web Tokens)
- BCrypt (password hashing)

---

## High-Level System Architecture

```
                    HemoNexus
                        │
        ┌───────────────┼────────────────┐
        │               │                │
 Central Admin     Hospital Portal    Donor Portal
        │               │                │
        └───────────────┼────────────────┘
                        │
              Application Services
                        │
       ┌────────────────┼─────────────────┐
       │                │                 │
 Authentication     Business Logic    ML Service
 RBAC / Security    Workflows         Analytics
       │                │                 │
       └────────────────┼─────────────────┘
                        │
                  Data Management
                        │
                     MySQL
```

### Component Descriptions

#### Central Admin Portal
- Institution-level administration interface
- Hospital management
- Staff/user management
- Role and permission management
- Institution-wide resource overview
- Reports and audit logs

#### Hospital Portal
- Blood Bank Staff interface
- Platelet Staff interface
- Inventory management
- Request management
- Donor mobilization
- Inter-hospital coordination

#### Donor Portal
- Donor registration
- Profile management
- Donation history
- Request viewing and response
- Appointment management

#### Application Services
- REST API endpoints
- Business logic implementation
- Workflow orchestration
- Data validation

#### Authentication & RBAC
- User authentication
- Role-based access control
- Permission enforcement
- Hospital-level data isolation

#### Business Logic
- Inventory management logic
- Request processing logic
- Transfer coordination logic
- Reservation logic

#### ML Service
- Demand forecasting
- Risk assessment
- Analytics
- Decision support

#### Data Management
- Database operations
- Entity management
- Query execution
- Transaction management

---

## Institutional Multi-Hospital Architecture

### Institutional Model

```
                    Institution
                        │
        ┌───────────────┼────────────────┐
        │               │                │
    Hospital A      Hospital B      Hospital C
        │               │                │
    ┌───┴───┐       ┌───┴───┐       ┌───┴───┐
    │       │       │       │       │       │
 Blood  Platelet  Blood  Platelet  Blood  Platelet
 Bank   Staff    Bank   Staff    Bank   Staff
 Staff           Staff           Staff
```

### Key Architectural Principles

1. **Single Institution, Multiple Hospitals**
   - The system represents one institution containing multiple hospitals
   - All hospitals belong to the same institution
   - Inter-hospital coordination is restricted to hospitals within the same institution

2. **Hospital-Level Autonomy**
   - Each hospital maintains its operational scope
   - Hospital-level data isolation is enforced
   - Hospital staff can only access their hospital's data

3. **Institution-Level Oversight**
   - Central Admin has institution-wide visibility
   - Central Admin can manage hospitals and users
   - Institution-wide reports and analytics are available

4. **Authorized Inter-Hospital Coordination**
   - Resource sharing only between authorized hospitals
   - Transfer requests require authorization
   - Transfer decisions are made by authorized staff

### Data Isolation Model

```
┌─────────────────────────────────────────────────────────┐
│                    Institution                          │
│  ┌─────────────────────────────────────────────────┐   │
│  │              Central Admin Access               │   │
│  │  - All hospitals                                │   │
│  │  - Institution-wide reports                     │   │
│  │  - User and role management                      │   │
│  └─────────────────────────────────────────────────┘   │
│                                                         │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐     │
│  │ Hospital A  │  │ Hospital B  │  │ Hospital C  │     │
│  │             │  │             │  │             │     │
│  │ Staff can   │  │ Staff can   │  │ Staff can   │     │
│  │ access only │  │ access only │  │ access only │     │
│  │ Hospital A  │  │ Hospital B  │  │ Hospital C  │     │
│  │ data        │  │ data        │  │ data        │     │
│  └─────────────┘  └─────────────┘  └─────────────┘     │
└─────────────────────────────────────────────────────────┘
```

---

## Frontend/Backend/Database/ML Relationships

### Technology Stack Relationships

```
┌─────────────────────────────────────────────────────────────┐
│                      Frontend (React)                        │
│  - Central Admin Portal                                       │
│  - Hospital Portal                                            │
│  - Donor Portal                                               │
└─────────────────────────────────────────────────────────────┘
                              │
                              │ HTTP/REST API
                              │
┌─────────────────────────────────────────────────────────────┐
│                   Backend (Spring Boot)                      │
│  ┌─────────────────────────────────────────────────────┐   │
│  │              Controllers (REST Endpoints)            │   │
│  └─────────────────────────────────────────────────────┘   │
│                              │                              │
│  ┌─────────────────────────────────────────────────────┐   │
│  │              Business Services                       │   │
│  └─────────────────────────────────────────────────────┘   │
│                              │                              │
│  ┌─────────────────────────────────────────────────────┐   │
│  │              Repositories (JPA)                       │   │
│  └─────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
                              │
                              │ JDBC/Hibernate
                              │
┌─────────────────────────────────────────────────────────────┐
│                    Database (MySQL)                          │
│  - Institution, Hospital, User, Role, Permission            │
│  - Donor, Donation, Appointment                             │
│  - BloodUnit, BloodRequest, BloodReservation                 │
│  - PlateletProduct, PlateletRequest, PlateletReservation     │
│  - ResourceTransfer, Notification, AuditLog                  │
└─────────────────────────────────────────────────────────────┘
                              │
                              │ REST API / Data Export
                              │
┌─────────────────────────────────────────────────────────────┐
│                   ML Service (Python)                        │
│  - Demand Forecasting                                        │
│  - Risk Assessment                                           │
│  - Analytics                                                 │
└─────────────────────────────────────────────────────────────┘
```

### Communication Patterns

#### Frontend to Backend
- **Protocol:** HTTP/REST
- **Format:** JSON
- **Authentication:** JWT tokens
- **Authorization:** Role-based and permission-based

#### Backend to Database
- **Protocol:** JDBC via Hibernate
- **ORM:** JPA/Hibernate
- **Transaction Management:** Spring @Transactional

#### Backend to ML Service
- **Protocol:** HTTP/REST (internal service communication)
- **Format:** JSON
- **Data Flow:** Backend sends historical data → ML Service returns predictions

#### Backend to Notification Services
- **Protocol:** HTTP/REST or SMTP (email)
- **Format:** JSON or email protocol
- **Async Processing:** Queue-based (future consideration)

---

## Module Interaction Architecture

### Module Dependency Overview

```
Module 0: Project Specification
    ↓
Module 1: Project Foundation
    ↓
Module 2: Complete Database & Data Model
    ↓
Module 3: Authentication & RBAC
    ↓
Module 4: Central Admin / Institution Management
    ↓
Module 5: Blood Bank Management
    ↓
Module 6: Blood Request Management
    ↓
Module 7: Blood Intelligence & ML
    ↓
Module 8: Donor Registration & Verification
    ↓
Module 9: Donor Portal
    ↓
Module 10: Donor Mobilization
    ↓
Module 11: Advanced Platelet Management
    ↓
Module 12: Platelet Request Management
    ↓
Module 13: Multi-Hospital Coordination
    ↓
Module 14: Reservation & Transfer Workflow
    ↓
Module 15: Expiry, Wastage & Lifecycle Alerts
    ↓
Module 16: Notification System
    ↓
Module 17: Role-Based Dashboards & Analytics
    ↓
Module 18: Reports
    ↓
Module 19: Audit Logging
    ↓
Module 20: Complete System Integration
    ↓
Module 21: Testing & Validation
    ↓
Module 22: Deployment & Final Documentation
```

### Cross-Module Data Flow

```
┌─────────────────────────────────────────────────────────────┐
│                    Authentication (Module 3)                 │
│  Provides authentication and authorization for all modules   │
└─────────────────────────────────────────────────────────────┘
                              │
        ┌─────────────────────┼─────────────────────┐
        │                     │                     │
┌───────┴────────┐  ┌────────┴────────┐  ┌────────┴────────┐
│ Blood Modules  │  │ Donor Modules   │  │ Platelet Modules│
│ (5, 6, 7)      │  │ (8, 9, 10)      │  │ (11, 12)        │
└────────────────┘  └─────────────────┘  └─────────────────┘
        │                     │                     │
        └─────────────────────┼─────────────────────┘
                              │
                    ┌─────────┴─────────┐
                    │ Coordination      │
                    │ Modules (13, 14)  │
                    └───────────────────┘
                              │
                    ┌─────────┴─────────┐
                    │ Cross-Cutting     │
                    │ Modules (15-19)   │
                    └───────────────────┘
```

---

## Security Architecture

### Authentication Flow

```
User
  │
  │ 1. Login Request (username, password)
  ↓
Authentication Service
  │
  │ 2. Validate Credentials
  ↓
Database
  │
  │ 3. Return User Data
  ↓
Authentication Service
  │
  │ 4. Generate JWT Token
  ↓
User
  │
  │ 5. Store JWT Token
  ↓
Subsequent Requests
  │
  │ 6. Send JWT Token in Header
  ↓
Authorization Filter
  │
  │ 7. Validate Token & Extract Roles
  ↓
Controller
  │
  │ 8. Check Permissions
  ↓
Business Logic
```

### Authorization Model

```
User
  │
  ├─ Role (Central Admin | Blood Bank Staff | Platelet Staff | Donor)
  │
  └─ Hospital Assignment
      │
      └─ Permissions (Read | Write | Delete | Approve)
```

### Hospital-Level Data Isolation

```
User Request
  │
  │ 1. Authenticate User
  ↓
Authentication Service
  │
  │ 2. Extract User Role and Hospital
  ↓
Authorization Filter
  │
  │ 3. Apply Hospital Filter
  ↓
Repository
  │
  │ 4. Query with Hospital Constraint
  ↓
Database
  │
  │ 5. Return Hospital-Specific Data
  ↓
Response
```

---

## ML Integration Architecture

### ML Service Integration

```
Backend Service
  │
  │ 1. Collect Historical Data
  │    - Blood demand records
  │    - Inventory levels
  │    - Seasonal patterns
  ↓
Data Preparation
  │
  │ 2. Format and Validate Data
  ↓
ML Service API
  │
  │ 3. Send Data to ML Service
  ↓
ML Service (Python)
  │
  │ 4. Load Trained Model
  │ 5. Generate Predictions
  │ 6. Assess Risk
  ↓
Backend Service
  │
  │ 7. Receive Predictions
  │ 8. Store in Database
  │ 9. Display in Dashboard
  ↓
User Interface
```

### ML Model Lifecycle

```
Data Collection
  ↓
Data Preprocessing
  ↓
Feature Engineering
  ↓
Model Training
  ↓
Model Evaluation (MAE, RMSE, R²)
  ↓
Model Deployment
  ↓
Prediction Generation
  ↓
Model Monitoring
  ↓
Retraining (as needed)
```

---

## Notification Architecture

### Notification Flow

```
Business Event
  │
  │ 1. Trigger Notification
  ↓
Notification Service
  │
  │ 2. Create Notification Record
  ↓
Database
  │
  │ 3. Store Notification
  ↓
Notification Service
  │
  │ 4. Determine Delivery Channels
  ↓
┌─────────────┬─────────────┬─────────────┐
│ In-App      │ Email       │ SMS         │
│ Notification│ Notification│ Notification│
└─────────────┴─────────────┴─────────────┘
  │              │              │
  ↓              ↓              ↓
User Portal    Email Service  SMS Service
```

---

## Scalability Considerations

### Horizontal Scaling

- **Backend:** Stateless Spring Boot services can be horizontally scaled
- **Database:** Read replicas can be added for reporting workloads
- **ML Service:** Can be scaled independently for heavy computation

### Vertical Scaling

- **Database:** MySQL can be vertically scaled with increased resources
- **Application:** Spring Boot can utilize increased CPU and memory

### Caching Strategy (Future)

- **Application-level caching:** Redis for frequently accessed data
- **Database query caching:** MySQL query cache
- **ML prediction caching:** Cache predictions for repeated queries

---

## Deployment Architecture

### Target Deployment Model

```
┌─────────────────────────────────────────────────────────────┐
│                     Production Server                        │
│  ┌─────────────────────────────────────────────────────┐   │
│  │              Frontend (React Build)                 │   │
│  │              Served by Web Server (Nginx)           │   │
│  └─────────────────────────────────────────────────────┘   │
│  ┌─────────────────────────────────────────────────────┐   │
│  │              Backend (Spring Boot JAR)              │   │
│  │              Running on Application Server          │   │
│  └─────────────────────────────────────────────────────┘   │
│  ┌─────────────────────────────────────────────────────┐   │
│  │              ML Service (Python)                    │   │
│  │              Running as Separate Service            │   │
│  └─────────────────────────────────────────────────────┘   │
│  ┌─────────────────────────────────────────────────────┐   │
│  │              Database (MySQL)                        │   │
│  │              Running on Database Server              │   │
│  └─────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
```

### Environment Configuration

- **Development:** Local development environment with all services
- **Testing:** Integration testing environment
- **Production:** Production environment with proper security and monitoring

---

## Related Documentation

- [Project Specification](PROJECT_SPECIFICATION.md)
- [Module Roadmap](MODULE_ROADMAP.md)
- [User Roles and Permissions](USER_ROLES_AND_PERMISSIONS.md)
- [Core Workflows](CORE_WORKFLOWS.md)
- [Database Design Principles](DATABASE_DESIGN_PRINCIPLES.md)
- [API Design Guidelines](API_DESIGN_GUIDELINES.md)
- [Security and Access Rules](SECURITY_AND_ACCESS_RULES.md)
- [Development Rules](DEVELOPMENT_RULES.md)
