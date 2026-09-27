# HemoNexus

**Intelligent Multi-Hospital Blood Resource Coordination and Donor Mobilization System**

---

## Project Overview

HemoNexus is a comprehensive institutional multi-hospital blood resource coordination and donor mobilization system. The system enables a single institution managing multiple hospitals to coordinate blood and platelet resources, forecast demand, assess shortage risks, mobilize donors, and facilitate inter-hospital resource transfers through a unified platform.

The system follows a **donor-first escalation principle**: local inventory is checked first, followed by donor mobilization, and only when still insufficient, inter-hospital coordination is initiated.

---

## Major Capabilities

### Institutional Multi-Hospital Management
- Single institution with multiple hospitals
- Centralized oversight with hospital-level operational autonomy
- Role-based and hospital-level access control

### Blood Inventory Management
- Blood unit tracking across blood types, Rh factors, and components
- Inventory monitoring and status management
- Reservation, issuance, and usage recording

### Blood Demand Forecasting
- ML-based demand prediction
- Shortage-risk identification
- Proactive planning support

### Donor Registration & Verification
- Donor self-registration through dedicated portal
- Hospital-assisted registration
- Verification workflow with staff review

### Donor Portal
- Donor profile management
- Donation history viewing
- Request viewing and response
- Appointment management

### Donor Mobilization
- Targeted donor identification
- Blood type and location-based matching
- Notification and response tracking

### Advanced Platelet Management
- Platelet product lifecycle management
- Expiry monitoring and alerts
- Reservation and transfer workflows

### Inter-Hospital Coordination
- Authorized resource sharing between hospitals
- Availability evaluation and decision workflow
- Transfer tracking with status updates

### Resource Transfer Tracking
- Complete transfer lifecycle tracking
- Dispatch, transit, and receipt monitoring
- Audit trail for all transfers

### Expiry & Wastage Management
- Expiry monitoring for blood and platelets
- Near-expiry alerts
- Wastage recording and disposal authorization

### Notifications & Alerts
- In-app notifications
- Optional email and SMS notifications
- Role-based alert targeting

### Role-Based Dashboards
- Central Admin dashboard (institution-wide)
- Blood Bank Staff dashboard (blood-specific)
- Platelet Staff dashboard (platelet-specific)
- Donor dashboard (personal)

### Reports
- Inventory reports
- Usage reports
- Transfer reports
- Donor activity reports
- Shortage-risk reports

### Audit Logging
- Operation traceability
- User action tracking
- Entity change tracking
- Compliance support

---

## Technology Direction

### Frontend
- React.js
- JavaScript
- HTML
- CSS
- Bootstrap or suitable UI component library
- React Router

### Backend
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- Maven

### Database
- MySQL

### Machine Learning
- Python
- Pandas
- NumPy
- Scikit-learn
- Jupyter (for development/experimentation)

---

## Architecture Overview

HemoNexus follows a six-layer architecture:

1. **Presentation Layer** - Central Admin Portal, Hospital Portal, Donor Portal
2. **Application & Business Logic Layer** - REST APIs, Business Services, Workflows
3. **Intelligence & Analytics Layer** - ML Service, Demand Forecasting, Risk Assessment
4. **Data Management Layer** - MySQL Database, JPA Entities, Repositories
5. **Integration & Communication Layer** - Notification Service, Email, SMS
6. **Security & Access Control Layer** - Authentication, RBAC, Authorization, Audit Logging

For detailed architecture information, see [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md).

---

## User Types

### Central Admin
Institution-level administration and oversight:
- Institution and hospital management
- Staff/user management
- Role and permission management
- Institution-wide resource overview
- Reports and audit logs

### Blood Bank Staff
Blood inventory and blood intelligence operations:
- Blood inventory management
- Blood request management
- Blood demand intelligence
- Donor mobilization
- Authorized blood coordination

### Platelet Staff
Platelet lifecycle and platelet operations:
- Platelet registration and inventory
- Platelet request management
- Expiry monitoring
- Platelet transfers

### Donor
Donor self-service through dedicated portal:
- Profile management
- Donation history viewing
- Request viewing and response
- Appointment management

For detailed role and permission information, see [docs/USER_ROLES_AND_PERMISSIONS.md](docs/USER_ROLES_AND_PERMISSIONS.md).

---

## Development Status

**Current Module:** Module 0 - Project Specification & Development Rules

**Status:** PLANNING

All application functionality described in this project is **PLANNED**. No implementation has been completed yet.

The project will be implemented according to the approved module roadmap (see [docs/MODULE_ROADMAP.md](docs/MODULE_ROADMAP.md)).

---

## Planned Modules

1. **Module 0** - Project Specification & Development Rules ✅
2. **Module 1** - Project Foundation
3. **Module 2** - Complete Database & Data Model
4. **Module 3** - Authentication & RBAC
5. **Module 4** - Central Admin / Institution Management
6. **Module 5** - Blood Bank Management
7. **Module 6** - Blood Request Management
8. **Module 7** - Blood Intelligence & ML
9. **Module 8** - Donor Registration & Verification
10. **Module 9** - Donor Portal
11. **Module 10** - Donor Mobilization
12. **Module 11** - Advanced Platelet Management
13. **Module 12** - Platelet Request Management
14. **Module 13** - Multi-Hospital Coordination
15. **Module 14** - Reservation & Transfer Workflow
16. **Module 15** - Expiry, Wastage & Lifecycle Alerts
17. **Module 16** - Notification System
18. **Module 17** - Role-Based Dashboards & Analytics
19. **Module 18** - Reports
20. **Module 19** - Audit Logging
21. **Module 20** - Complete System Integration
22. **Module 21** - Testing & Validation
23. **Module 22** - Deployment & Final Documentation

For detailed module information, see [docs/MODULE_ROADMAP.md](docs/MODULE_ROADMAP.md).

---

## Documentation

### Project Documentation
- [Project Specification](docs/PROJECT_SPECIFICATION.md) - Complete project overview, objectives, scope, and assumptions
- [Architecture](docs/ARCHITECTURE.md) - Six-layer architecture, system architecture, and component relationships
- [Module Roadmap](docs/MODULE_ROADMAP.md) - Detailed module sequence and dependencies
- [User Roles and Permissions](docs/USER_ROLES_AND_PERMISSIONS.md) - Role definitions, responsibilities, and permission matrix
- [Core Workflows](docs/CORE_WORKFLOWS.md) - Blood requirement, donor mobilization, platelet lifecycle, and coordination workflows

### Design Documentation
- [Database Design Principles](docs/DATABASE_DESIGN_PRINCIPLES.md) - Relational design principles, entity ownership, and candidate entities
- [API Design Guidelines](docs/API_DESIGN_GUIDELINES.md) - REST principles, endpoint naming, HTTP methods, and validation
- [Security and Access Rules](docs/SECURITY_AND_ACCESS_RULES.md) - Authentication, authorization, RBAC, and data protection
- [Development Rules](docs/DEVELOPMENT_RULES.md) - Development process, coding standards, and Git strategy

---

## Important Principles

### Donor-First Escalation
The system follows a donor-first escalation approach:
1. Check local inventory
2. Mobilize donors if insufficient
3. Coordinate with other hospitals if still insufficient

### Institutional Multi-Hospital Model
- One institution containing multiple hospitals
- Hospitals belong to the same institution
- Inter-hospital coordination restricted to same institution
- Hospital-level data isolation for staff roles

### ML Scope
- ML is used for decision support, not guarantees
- ML forecasts expected demand, does not predict exact events
- ML identifies potential risks, does not predict all shortages
- Model performance evaluated using actual metrics (MAE, RMSE, R²)

### Platelet Scope
- Platelet management is primarily database/workflow driven
- Not an ML prediction module
- Clinical eligibility decisions remain with authorized personnel

### Medical Decision Responsibility
- The software does not medically certify donors
- Actual medical screening and eligibility decisions remain with authorized healthcare personnel
- The system does not claim regulatory or clinical certification

---

## Getting Started (Future)

### Prerequisites
- Java 17 or higher
- Node.js 18 or higher
- Python 3.9 or higher
- MySQL 8.0 or higher
- Maven 3.8 or higher

### Installation (Future)
Installation instructions will be provided in Module 22 after implementation is complete.

### Development Setup (Future)
Development setup instructions will be provided in Module 1.

---

## Contributing

This project is developed following a structured module-by-module approach. Each module is implemented on a dedicated Git branch and merged to main only after completion and testing.

For development rules and guidelines, see [docs/DEVELOPMENT_RULES.md](docs/DEVELOPMENT_RULES.md).

---

## License

[To be determined]

---

## Contact

[To be determined]

---

## Acknowledgments

This project is developed as a final-year engineering project.

---

**Note:** This project is currently in the planning phase (Module 0). All features described are planned for implementation. No application functionality has been implemented yet.
