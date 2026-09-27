# HemoNexus - Module Roadmap

## Overview

This document describes the approved implementation roadmap for HemoNexus, including the objective of each module, dependencies between modules, and what each module should and should not implement.

**Development Status:** Module 0 - Project Specification & Development Rules (PLANNED)

---

## Module Sequence

```
Module 0: Project Specification & Development Rules
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

---

## Module 0: Project Specification & Development Rules

### Objective
Establish a complete, professional, consistent technical specification for HemoNexus before application development begins. Create the technical source of truth for all future implementation modules.

### What to Implement
- Project specification documentation
- Architecture documentation
- Module roadmap documentation
- User roles and permissions documentation
- Core workflows documentation
- Database design principles documentation
- API design guidelines documentation
- Security and access rules documentation
- Development rules documentation
- Root README.md

### What NOT to Implement
- Application source code
- Backend code
- Frontend code
- Database schema
- Entity classes
- Controllers
- Repositories
- Services
- Authentication implementation
- ML implementation
- APIs
- Dashboards
- Business logic

### Dependencies
None (first module)

### Deliverables
- docs/PROJECT_SPECIFICATION.md
- docs/ARCHITECTURE.md
- docs/MODULE_ROADMAP.md
- docs/USER_ROLES_AND_PERMISSIONS.md
- docs/CORE_WORKFLOWS.md
- docs/DATABASE_DESIGN_PRINCIPLES.md
- docs/API_DESIGN_GUIDELINES.md
- docs/SECURITY_AND_ACCESS_RULES.md
- docs/DEVELOPMENT_RULES.md
- README.md

---

## Module 1: Project Foundation

### Objective
Establish the project structure, build configuration, and development environment setup.

### What to Implement
- Project directory structure (frontend, backend, ml-service)
- Backend Maven configuration (pom.xml)
- Frontend package.json configuration
- ML service requirements.txt
- Basic Spring Boot application skeleton
- Basic React application skeleton
- Basic Python ML service skeleton
- Environment variable template (.env.example)
- Git branch creation (module-1-foundation)

### What NOT to Implement
- Business logic
- Database entities
- Authentication
- APIs beyond basic health check
- ML models
- UI components

### Dependencies
- Module 0 (project specification)

### Deliverables
- backend/pom.xml
- frontend/package.json
- ml-service/requirements.txt
- backend/src/main/java/.../HemoNexusApplication.java
- frontend/src/App.js (skeleton)
- ml-service/main.py (skeleton)
- .env.example

---

## Module 2: Complete Database & Data Model

### Objective
Design and implement the complete database schema with all tables, relationships, constraints, and JPA entity classes.

### What to Implement
- Complete database schema design
- All database tables with proper relationships
- Primary keys, foreign keys, unique constraints
- Indexes for performance
- Status fields and audit fields
- JPA entity classes for all domain entities
- Repository interfaces
- Database migration scripts (Flyway or Liquibase)
- Seed data for initial setup (roles, permissions)

### What NOT to Implement
- Business logic services
- Controllers
- Authentication
- ML implementation
- Frontend components

### Dependencies
- Module 1 (project foundation)
- Module 0 (database design principles)

### Deliverables
- Database schema documentation
- SQL migration scripts
- JPA entity classes
- Repository interfaces
- Seed data scripts

---

## Module 3: Authentication & RBAC

### Objective
Implement user authentication, role-based access control, and authorization enforcement.

### What to Implement
- User authentication (JWT-based)
- Login/logout functionality
- Password hashing (BCrypt)
- JWT token generation and validation
- Role-based access control (RBAC)
- Permission-based authorization
- Authorization filters
- Hospital-level data isolation enforcement
- Institution-level access rules
- Protected API endpoints
- Authentication API endpoints

### What NOT to Implement
- Business logic beyond authentication
- Blood bank operations
- Donor operations
- ML implementation
- Frontend authentication UI (basic only)

### Dependencies
- Module 2 (database and entities for User, Role, Permission)

### Deliverables
- Authentication service
- JWT utility classes
- Authorization filters
- Authentication controllers
- Login/logout API endpoints
- Frontend login page (basic)
- Frontend authentication context

---

## Module 4: Central Admin / Institution Management

### Objective
Implement institution-level administration capabilities for Central Admin users.

### What to Implement
- Institution management (CRUD)
- Hospital management (CRUD)
- Staff/user management (CRUD)
- Role management (CRUD)
- Permission management (CRUD)
- Role-permission assignment
- User-role assignment
- User-hospital assignment
- Institution-wide resource overview
- Blood overview dashboard
- Platelet overview dashboard
- Shortage-risk overview dashboard
- Donor verification oversight
- Donor mobilization oversight
- Inter-hospital coordination oversight
- Reports (basic)
- Audit logs (basic viewing)

### What NOT to Implement
- Blood bank operational transactions
- Platelet operational transactions
- Donor portal
- ML implementation
- Detailed reports (deferred to Module 18)

### Dependencies
- Module 3 (authentication and RBAC)
- Module 2 (database entities)

### Deliverables
- Institution management services
- Hospital management services
- User management services
- Role and permission management services
- Central Admin controllers
- Central Admin frontend pages
- Overview dashboards

---

## Module 5: Blood Bank Management

### Objective
Implement blood inventory management capabilities for Blood Bank Staff.

### What to Implement
- Blood unit registration
- Blood inventory tracking
- Blood unit status management
- Blood type and Rh factor management
- Component tracking
- Inventory search and filtering
- Blood unit availability view
- Blood usage recording
- Basic inventory reports

### What NOT to Implement
- Blood request workflows (deferred to Module 6)
- ML implementation
- Donor operations
- Inter-hospital coordination

### Dependencies
- Module 3 (authentication and RBAC)
- Module 2 (database entities)

### Deliverables
- Blood unit management services
- Inventory management services
- Blood Bank Staff controllers
- Blood Bank Staff frontend pages
- Inventory dashboard

---

## Module 6: Blood Request Management

### Objective
Implement blood request creation, management, and fulfillment workflows.

### What to Implement
- Blood request creation
- Blood request item specification
- Request status tracking
- Request approval/rejection
- Request fulfillment workflow
- Blood unit reservation for requests
- Blood unit issuance
- Request history
- Request reports

### What NOT to Implement
- ML demand forecasting (deferred to Module 7)
- Donor mobilization (deferred to Module 10)
- Inter-hospital coordination (deferred to Module 13)

### Dependencies
- Module 5 (blood inventory management)
- Module 3 (authentication and RBAC)

### Deliverables
- Blood request services
- Request workflow services
- Blood request controllers
- Blood request frontend pages
- Request management dashboard

---

## Module 7: Blood Intelligence & ML

### Objective
Implement blood demand forecasting and shortage-risk assessment using machine learning.

### What to Implement
- Historical demand data collection
- Data preprocessing and feature engineering
- ML model training (demand forecasting)
- ML model evaluation (MAE, RMSE, R²)
- Demand prediction API
- Shortage-risk assessment
- Risk scoring and alerting
- Trend analysis
- Decision support interface
- ML service integration with backend

### What NOT to Implement
- Donor mobilization (deferred to Module 10)
- Inter-hospital coordination (deferred to Module 13)
- Fabricated accuracy values
- Claims of predicting exact events

### Dependencies
- Module 6 (blood request data for training)
- Module 2 (database entities for demand records)
- Module 1 (ML service foundation)

### Deliverables
- ML service implementation
- Demand forecasting models
- Risk assessment engine
- ML API endpoints
- Blood intelligence dashboard
- Model evaluation documentation

---

## Module 8: Donor Registration & Verification

### Objective
Implement donor registration and verification workflows.

### What to Implement
- Donor self-registration
- Hospital-assisted donor registration
- Donor profile management
- Document/information submission
- Donor verification workflow
- Staff review process
- Verification status tracking
- Approval/rejection workflow
- Account activation
- Donor document management

### What NOT to Implement
- Donor portal UI (deferred to Module 9)
- Donor mobilization (deferred to Module 10)

### Dependencies
- Module 3 (authentication and RBAC)
- Module 2 (database entities for Donor)

### Deliverables
- Donor registration services
- Donor verification services
- Donor management controllers
- Hospital staff donor management pages
- Verification workflow implementation

---

## Module 9: Donor Portal

### Objective
Implement the donor-facing portal for donor self-service capabilities.

### What to Implement
- Donor login
- Donor profile management
- Donation history viewing
- Verification status viewing
- Request viewing
- Request response capability
- Appointment management
- Preference management
- Donor authentication

### What NOT to Implement
- Donor mobilization logic (deferred to Module 10)
- Hospital administrative dashboards

### Dependencies
- Module 8 (donor registration and verification)
- Module 3 (authentication)

### Deliverables
- Donor portal frontend
- Donor services
- Donor controllers
- Donor authentication integration
- Donor profile pages
- Donation history pages

---

## Module 10: Donor Mobilization

### Objective
Implement donor mobilization capabilities for targeted donor engagement.

### What to Implement
- Donor identification and matching
- Blood type matching
- Location-based filtering
- Availability filtering
- Targeted notification sending
- Donor response tracking
- Appointment scheduling
- Mobilization campaign management
- Donor preference consideration

### What NOT to Implement
- Inter-hospital coordination (deferred to Module 13)
- Notification delivery (deferred to Module 16)

### Dependencies
- Module 9 (donor portal)
- Module 8 (donor registration)
- Module 7 (blood intelligence for demand prediction)

### Deliverables
- Donor mobilization services
- Donor matching algorithms
- Mobilization controllers
- Mobilization management pages
- Donor response tracking

---

## Module 11: Advanced Platelet Management

### Objective
Implement comprehensive platelet product lifecycle management.

### What to Implement
- Platelet product registration
- Platelet inventory tracking
- Platelet status management
- Platelet lifecycle management
- Collection → Registration → Available → Reserved → Issued/Transferred → Used
- Platelet product attributes
- Platelet inventory search and filtering
- Basic platelet reports

### What NOT to Implement
- Platelet request workflows (deferred to Module 12)
- Expiry monitoring (deferred to Module 15)
- ML-based platelet prediction (not in scope)

### Dependencies
- Module 3 (authentication and RBAC)
- Module 2 (database entities for PlateletProduct)

### Deliverables
- Platelet management services
- Platelet inventory services
- Platelet Staff controllers
- Platelet Staff frontend pages
- Platelet inventory dashboard

---

## Module 12: Platelet Request Management

### Objective
Implement platelet request creation, management, and fulfillment workflows.

### What to Implement
- Platelet request creation
- Platelet request item specification
- Request status tracking
- Request approval/rejection
- Platelet reservation for requests
- Platelet issuance
- Request history
- Request reports

### What NOT to Implement
- Inter-hospital coordination (deferred to Module 13)
- Expiry monitoring (deferred to Module 15)

### Dependencies
- Module 11 (platelet inventory management)
- Module 3 (authentication and RBAC)

### Deliverables
- Platelet request services
- Platelet workflow services
- Platelet request controllers
- Platelet request frontend pages
- Request management dashboard

---

## Module 13: Multi-Hospital Coordination

### Objective
Implement inter-hospital resource coordination capabilities.

### What to Implement
- Inter-hospital blood resource requests
- Inter-hospital platelet resource requests
- Availability evaluation
- Accept/reject workflow
- Request authorization
- Hospital selection for requests
- Coordination oversight for Central Admin
- Coordination history

### What NOT to Implement
- Physical transfer tracking (deferred to Module 14)
- Notification delivery (deferred to Module 16)

### Dependencies
- Module 6 (blood request management)
- Module 12 (platelet request management)
- Module 4 (hospital management)

### Deliverables
- Coordination services
- Inter-hospital request services
- Coordination controllers
- Coordination management pages
- Request evaluation interface

---

## Module 14: Reservation & Transfer Workflow

### Objective
Implement resource reservation and transfer tracking workflows.

### What to Implement
- Blood unit reservation
- Platelet product reservation
- Reservation status management
- Reservation cancellation
- Transfer request initiation
- Transfer dispatch tracking
- In-transit monitoring
- Receipt confirmation
- Inventory update after transfer
- Transfer history
- Transfer audit trail

### What NOT to Implement
- Expiry monitoring (deferred to Module 15)
- Notification delivery (deferred to Module 16)

### Dependencies
- Module 13 (multi-hospital coordination)
- Module 6 (blood requests)
- Module 12 (platelet requests)

### Deliverables
- Reservation services
- Transfer services
- Transfer workflow implementation
- Transfer tracking pages
- Reservation management pages

---

## Module 15: Expiry, Wastage & Lifecycle Alerts

### Objective
Implement expiry monitoring, wastage recording, and lifecycle alerting.

### What to Implement
- Blood unit expiry monitoring
- Platelet product expiry monitoring
- Near-expiry alerts
- Expiry status updates
- Wastage recording
- Disposal recording
- Disposal authorization
- Reason documentation
- Expiry-based prioritization
- Lifecycle status transitions

### What NOT to Implement
- Notification delivery (deferred to Module 16)

### Dependencies
- Module 5 (blood inventory)
- Module 11 (platelet inventory)
- Module 14 (reservations)

### Deliverables
- Expiry monitoring services
- Wastage recording services
- Alert generation services
- Expiry monitoring pages
- Wastage recording pages
- Alert configuration

---

## Module 16: Notification System

### Objective
Implement comprehensive notification and alerting system.

### What to Implement
- Notification service
- In-app notification delivery
- Email notification delivery
- SMS notification delivery (optional)
- Notification templates
- Notification preferences
- Notification history
- Notification targeting by role
- Notification targeting by hospital
- Event-driven notification triggers

### What NOT to Implement
- Business logic for specific notifications (handled by respective modules)

### Dependencies
- All previous modules that generate notifications

### Deliverables
- Notification service
- Email service
- SMS service (optional)
- Notification controllers
- Notification management pages
- Notification preferences pages

---

## Module 17: Role-Based Dashboards & Analytics

### Objective
Implement comprehensive role-based dashboards with analytics.

### What to Implement
- Central Admin dashboard with institution-wide analytics
- Blood Bank Staff dashboard with blood-specific analytics
- Platelet Staff dashboard with platelet-specific analytics
- Donor dashboard with donor-specific information
- Key performance indicators (KPIs)
- Trend visualizations
- Comparative analytics
- Hospital-level analytics
- Institution-level analytics
- Interactive filters

### What NOT to Implement
- Detailed reports (deferred to Module 18)

### Dependencies
- All previous modules for data sources

### Deliverables
- Analytics services
- Dashboard controllers
- Dashboard frontend pages
- KPI calculation logic
- Visualization components

---

## Module 18: Reports

### Objective
Implement comprehensive reporting capabilities.

### What to Implement
- Inventory reports
- Usage reports
- Transfer reports
- Donor activity reports
- Shortage-risk reports
- Expiry reports
- Wastage reports
- Coordination reports
- Custom report builder
- Report scheduling (optional)
- Report export (PDF, Excel, CSV)

### What NOT to Implement
- New business logic beyond reporting

### Dependencies
- All previous modules for data sources

### Deliverables
- Report generation services
- Report controllers
- Report frontend pages
- Report templates
- Export functionality

---

## Module 19: Audit Logging

### Objective
Implement comprehensive audit logging for traceability and compliance.

### What to Implement
- Audit logging service
- Operation logging
- User action tracking
- Entity change tracking
- Audit record storage
- Audit log viewing
- Audit log filtering
- Audit log export
- Audit report generation

### What NOT to Implement
- Business logic modifications

### Dependencies
- All previous modules for audit events

### Deliverables
- Audit logging service
- Audit log controllers
- Audit log viewing pages
- Audit log filters
- Audit export functionality

---

## Module 20: Complete System Integration

### Objective
Integrate all modules and ensure end-to-end functionality.

### What to Implement
- End-to-end workflow testing
- Integration testing
- Cross-module data flow validation
- Error handling validation
- Performance optimization
- Security validation
- Data consistency validation
- Bug fixes identified during integration

### What NOT to Implement
- New features (only integration and fixes)

### Dependencies
- All previous modules

### Deliverables
- Integrated system
- Integration test results
- Performance test results
- Security validation results
- Bug fix documentation

---

## Module 21: Testing & Validation

### Objective
Comprehensive testing and validation of the complete system.

### What to Implement
- Unit tests
- Integration tests
- End-to-end tests
- Performance tests
- Security tests
- User acceptance testing (UAT)
- Test documentation
- Test coverage reports
- Known issues documentation

### What NOT to Implement
- New features

### Dependencies
- Module 20 (complete integration)

### Deliverables
- Test suite
- Test documentation
- Test coverage reports
- Known issues list
- Validation report

---

## Module 22: Deployment & Final Documentation

### Objective
Prepare the system for deployment and create final documentation.

### What to Implement
- Deployment configuration
- Production environment setup
- Database migration scripts for production
- Environment variable documentation
- Deployment scripts
- User documentation
- Administrator documentation
- API documentation
- Developer documentation
- Installation guide
- Troubleshooting guide
- Final README updates
- Version release

### What NOT to Implement
- New features

### Dependencies
- Module 21 (testing and validation)

### Deliverables
- Deployment scripts
- Production configuration
- User documentation
- Administrator documentation
- API documentation
- Developer documentation
- Installation guide
- Updated README
- Release notes

---

## Module Dependency Summary

### Foundation Modules
- Module 0: Project Specification (no dependencies)
- Module 1: Project Foundation (depends on Module 0)
- Module 2: Database & Data Model (depends on Module 1, Module 0)

### Core Infrastructure
- Module 3: Authentication & RBAC (depends on Module 2)

### Administrative Modules
- Module 4: Central Admin (depends on Module 3, Module 2)

### Blood Management Modules
- Module 5: Blood Bank Management (depends on Module 3, Module 2)
- Module 6: Blood Request Management (depends on Module 5, Module 3)
- Module 7: Blood Intelligence & ML (depends on Module 6, Module 2, Module 1)

### Donor Modules
- Module 8: Donor Registration & Verification (depends on Module 3, Module 2)
- Module 9: Donor Portal (depends on Module 8, Module 3)
- Module 10: Donor Mobilization (depends on Module 9, Module 8, Module 7)

### Platelet Modules
- Module 11: Platelet Management (depends on Module 3, Module 2)
- Module 12: Platelet Request Management (depends on Module 11, Module 3)

### Coordination Modules
- Module 13: Multi-Hospital Coordination (depends on Module 6, Module 12, Module 4)
- Module 14: Reservation & Transfer Workflow (depends on Module 13, Module 6, Module 12)

### Lifecycle Modules
- Module 15: Expiry, Wastage & Alerts (depends on Module 5, Module 11, Module 14)

### Cross-Cutting Modules
- Module 16: Notification System (depends on all previous modules)
- Module 17: Dashboards & Analytics (depends on all previous modules)
- Module 18: Reports (depends on all previous modules)
- Module 19: Audit Logging (depends on all previous modules)

### Finalization Modules
- Module 20: Complete System Integration (depends on all previous modules)
- Module 21: Testing & Validation (depends on Module 20)
- Module 22: Deployment & Documentation (depends on Module 21)

---

## Git Branch Strategy

Each module should be developed on a dedicated branch:

```
main (stable)
  ↓
module-0-project-specification
module-1-foundation
module-2-database
module-3-auth-rbac
module-4-central-admin
module-5-blood-bank
module-6-blood-request
module-7-blood-intelligence
module-8-donor-registration
module-9-donor-portal
module-10-donor-mobilization
module-11-platelet-management
module-12-platelet-request
module-13-multi-hospital-coordination
module-14-reservation-transfer
module-15-expiry-wastage
module-16-notification
module-17-dashboards
module-18-reports
module-19-audit-logging
module-20-integration
module-21-testing
module-22-deployment
```

### Branch Rules
- main must remain stable
- One branch per implementation module
- Meaningful commits
- No secrets committed
- No .env committed
- No force push
- No destructive Git operations
- Developer reviews changes before commit
- Merge to main only after module completion

---

## Related Documentation

- [Project Specification](PROJECT_SPECIFICATION.md)
- [Architecture](ARCHITECTURE.md)
- [User Roles and Permissions](USER_ROLES_AND_PERMISSIONS.md)
- [Core Workflows](CORE_WORKFLOWS.md)
- [Database Design Principles](DATABASE_DESIGN_PRINCIPLES.md)
- [API Design Guidelines](API_DESIGN_GUIDELINES.md)
- [Security and Access Rules](SECURITY_AND_ACCESS_RULES.md)
- [Development Rules](DEVELOPMENT_RULES.md)
