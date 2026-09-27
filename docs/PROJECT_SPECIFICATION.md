# HemoNexus - Project Specification

## Project Overview

**Project Title:** Intelligent Multi-Hospital Blood Resource Coordination and Donor Mobilization System

**Project Short Name:** HemoNexus

**Domain:** Healthcare Management, Blood Resource Management, Machine Learning, Donor Mobilization, Multi-Hospital Coordination, Decision Support

**Development Status:** Module 0 - Project Specification & Development Rules (PLANNED)

---

## Executive Summary

HemoNexus is a comprehensive institutional multi-hospital blood resource coordination and donor mobilization system. The system enables a single institution managing multiple hospitals to coordinate blood and platelet resources, forecast demand, assess shortage risks, mobilize donors, and facilitate inter-hospital resource transfers through a unified platform.

The system follows a donor-first escalation principle: local inventory is checked first, followed by donor mobilization, and only when still insufficient, inter-hospital coordination is initiated.

---

## Project Objectives

### Primary Objectives

1. **Institutional Multi-Hospital Management**
   - Enable a single institution to manage blood resources across multiple hospitals
   - Provide centralized oversight with hospital-level operational autonomy
   - Support role-based and hospital-level access control

2. **Blood Inventory Management**
   - Track blood units across blood types, Rh factors, and components
   - Monitor inventory levels, availability, and status
   - Support reservation, issuance, and usage recording

3. **Blood Demand Forecasting**
   - Use machine learning to forecast expected blood demand
   - Identify potential shortage risks proactively
   - Support data-driven inventory planning

4. **Blood Shortage-Risk Assessment**
   - Provide decision support for proactive resource management
   - Alert hospitals to potential shortages before critical situations
   - Enable timely donor mobilization and inter-hospital coordination

5. **Donor Registration and Verification**
   - Support donor self-registration through a dedicated portal
   - Enable hospital-assisted donor registration
   - Implement donor verification workflow with staff review

6. **Donor Portal**
   - Provide donors with profile management capabilities
   - Enable donors to view donation history and verification status
   - Support donor response to mobilization requests and appointment management

7. **Donor Mobilization**
   - Identify suitable verified donors based on blood type, location, and availability
   - Send targeted notifications for donation requests
   - Track donor responses and appointment scheduling

8. **Advanced Platelet Management**
   - Manage platelet product lifecycle from collection to disposal
   - Support platelet registration, inventory, reservations, and transfers
   - Implement expiry monitoring and near-expiry alerts

9. **Blood and Platelet Requests**
   - Enable hospitals to create and manage blood requests
   - Support platelet requests with reservation workflows
   - Facilitate request tracking and status updates

10. **Inter-Hospital Coordination**
    - Enable authorized hospitals to request resources from each other
    - Support availability evaluation, acceptance/rejection, and reservation
    - Track resource transfers with dispatch, transit, and receipt states

11. **Resource Transfer Tracking**
    - Monitor transfer status across the complete lifecycle
    - Provide audit trail for all inter-hospital resource movements
    - Support both blood and platelet transfers

12. **Expiry Monitoring**
    - Monitor blood unit and platelet product expiry dates
    - Generate near-expiry alerts for proactive action
    - Support expiry-based prioritization for usage or transfer

13. **Wastage and Disposal Recording**
    - Record expired or unusable blood units and platelet products
    - Document disposal reasons and authorization
    - Maintain audit trail for wastage events

14. **Notifications and Alerts**
    - Provide in-app notifications for relevant events
    - Support optional email and SMS notifications
    - Enable targeted alerts based on role and hospital

15. **Role-Based Dashboards**
    - Provide tailored dashboards for Central Admin, Blood Bank Staff, Platelet Staff, and Donors
    - Display relevant information based on user role and hospital scope
    - Support actionable insights and quick access to common tasks

16. **Reports**
    - Generate inventory reports, usage reports, and transfer reports
    - Support donor activity reports and shortage-risk reports
    - Enable custom reporting based on institution and hospital scope

17. **Audit Logging**
    - Log important operations for traceability
    - Capture user, role, hospital, action, entity, and timestamp
    - Support compliance and accountability

18. **Role-Based and Hospital-Level Access Control**
    - Enforce authentication and authorization
    - Implement role-based access control (RBAC)
    - Ensure hospital-level data isolation and institution-level access rules

---

## System Scope

### In-Scope Features

The following features are within the approved HemoNexus project scope:

1. **Institutional Multi-Hospital Management**
   - Institution configuration and management
   - Hospital registration and management
   - Staff/user management
   - Role and permission management

2. **Blood Inventory Management**
   - Blood unit registration and tracking
   - Inventory status management
   - Blood type and Rh factor management
   - Component tracking (whole blood, packed cells, plasma, platelets)

3. **Blood Demand Forecasting**
   - Historical demand analysis
   - ML-based demand prediction
   - Shortage-risk identification
   - Proactive planning support

4. **Blood Shortage-Risk Assessment**
   - Risk scoring and alerting
   - Trend analysis
   - Decision support for resource allocation

5. **Donor Registration**
   - Donor self-registration portal
   - Hospital-assisted registration
   - Document/information submission
   - Profile management

6. **Donor Verification Workflow**
   - Staff review process
   - Verification status tracking
   - Approval/rejection workflow
   - Account activation

7. **Donor Portal**
   - Donor authentication
   - Profile management
   - Donation history viewing
   - Verification status viewing
   - Request viewing and response
   - Appointment management
   - Preference management

8. **Donor Mobilization**
   - Donor identification and matching
   - Targeted notifications
   - Response tracking
   - Appointment scheduling

9. **Advanced Platelet Management**
   - Platelet product registration
   - Inventory tracking
   - Reservation management
   - Issue/receipt workflows
   - Transfer support

10. **Blood Requests**
    - Request creation and management
    - Request item specification
    - Status tracking
    - Fulfillment workflows

11. **Platelet Requests**
    - Request creation and management
    - Reservation workflows
    - Status tracking
    - Fulfillment workflows

12. **Reservation Workflows**
    - Blood unit reservations
    - Platelet product reservations
    - Reservation status management
    - Reservation cancellation

13. **Inter-Hospital Blood Coordination**
    - Blood resource requests between hospitals
    - Availability evaluation
    - Acceptance/rejection
    - Transfer tracking

14. **Inter-Hospital Platelet Coordination**
    - Platelet resource requests between hospitals
    - Availability evaluation
    - Acceptance/rejection
    - Transfer tracking

15. **Resource Transfer Tracking**
    - Transfer request initiation
    - Dispatch tracking
    - In-transit monitoring
    - Receipt confirmation
    - Inventory update

16. **Expiry Monitoring**
    - Expiry date tracking
    - Near-expiry alerts
    - Expiry status updates

17. **Wastage/Disposal Recording**
    - Wastage recording
    - Disposal authorization
    - Reason documentation
    - Audit trail

18. **Notifications and Alerts**
    - In-app notifications
    - Email notifications (optional)
    - SMS notifications (optional)
    - Alert configuration

19. **Role-Based Dashboards**
    - Central Admin dashboard
    - Blood Bank Staff dashboard
    - Platelet Staff dashboard
    - Donor dashboard

20. **Reports**
    - Inventory reports
    - Usage reports
    - Transfer reports
    - Donor reports
    - Shortage-risk reports

21. **Audit Logging**
    - Operation logging
    - User action tracking
    - Entity change tracking
    - Audit report generation

22. **Role-Based and Hospital-Level Access Control**
    - Authentication
    - Role-based authorization
    - Permission-based authorization
    - Hospital-level data isolation
    - Institution-level access rules

### Out-of-Scope Features

The following features are explicitly **NOT** part of the HemoNexus project scope:

1. **Organ Donation and Transplant Management**
   - The system does not manage organ donation, organ matching, or transplant workflows

2. **Accident/Disaster Prediction**
   - The ML component does not predict accidents, disasters, or exact emergency events
   - The system does not claim to predict every future blood shortage

3. **Medical Certification**
   - The software does not medically certify donors
   - Actual medical screening and eligibility decisions remain with authorized healthcare personnel
   - The system does not replace clinical judgment

4. **Regulatory or Clinical Certification Claims**
   - The system does not claim regulatory or clinical certification unless explicitly established later
   - Any future regulatory or institutional compliance requirements must be separately documented

5. **Unrestricted Hospital Network**
   - The system is not designed as an unrestricted network of unrelated hospitals
   - Inter-hospital coordination only occurs between authorized hospitals belonging to the same institution

6. **Automated Clinical Decisions**
   - The system does not automate clinical eligibility decisions
   - Medical decisions remain the responsibility of authorized healthcare personnel

---

## System Boundaries

### Institutional Boundary

- The system represents **one institution** containing **multiple hospitals**
- Hospitals belong to the same institution
- Inter-hospital coordination is restricted to hospitals within the same institution
- No cross-institution resource sharing is supported

### User Role Boundaries

- **Central Admin**: Institution-level oversight, not operational blood-bank transactions
- **Blood Bank Staff**: Blood inventory and blood intelligence operations, not platelet operations
- **Platelet Staff**: Platelet operations, not blood intelligence operations
- **Donors**: Donor portal only, no access to hospital administrative dashboards

### Data Access Boundaries

- Hospital-level data isolation: Users can only access data for their assigned hospital
- Institution-level access: Central Admin can access institution-wide data
- Role-based access: Permissions are enforced based on user role
- Least privilege: Users receive minimum necessary access to perform their duties

### ML Component Boundaries

- ML is used for **decision support**, not guarantees
- ML forecasts expected demand, does not predict exact events
- ML identifies potential shortage risks, does not predict all shortages
- Model performance must be evaluated using actual metrics (MAE, RMSE, R²)
- No fabricated accuracy values are permitted

### Platelet Management Boundaries

- Platelet management is primarily database/workflow driven, not ML-based
- Platelet lifecycle supports: Collection → Registration → Available → Reserved → Issued/Transferred → Used
- Expiry management: Available → Expiry Monitoring → Near Expiry → Alert → Expired → Unavailable → Wastage/Disposal → Audit
- Clinical eligibility decisions remain with authorized healthcare personnel

---

## Major Capabilities

### 1. Multi-Hospital Resource Visibility

- Institution-wide overview of blood and platelet resources
- Hospital-specific inventory views
- Real-time availability status
- Centralized resource monitoring

### 2. Donor-First Escalation

- Local inventory check as first priority
- Donor mobilization as second priority
- Inter-hospital coordination as third priority
- Consistent escalation approach across all workflows

### 3. Intelligent Demand Forecasting

- ML-based blood demand prediction
- Shortage-risk assessment
- Proactive planning support
- Data-driven decision making

### 4. Targeted Donor Mobilization

- Donor identification based on blood type, location, and availability
- Targeted notifications for specific requests
- Response tracking and appointment scheduling
- Donor preference management

### 5. Comprehensive Platelet Lifecycle Management

- End-to-end platelet product tracking
- Expiry monitoring and alerts
- Reservation and transfer workflows
- Wastage recording and audit

### 6. Inter-Hospital Coordination

- Authorized resource sharing between hospitals
- Availability evaluation and decision workflow
- Transfer tracking with status updates
- Complete audit trail

### 7. Role-Based Access Control

- Four primary user types with distinct responsibilities
- Hospital-level data isolation
- Institution-level oversight
- Permission-based authorization

### 8. Audit and Compliance

- Comprehensive audit logging
- Operation traceability
- User action tracking
- Compliance support

---

## Important Project Assumptions

### Technical Assumptions

1. **Technology Stack**
   - Frontend: React.js, JavaScript, HTML, CSS, Bootstrap or suitable UI library, React Router
   - Backend: Java, Spring Boot, Spring Web, Spring Data JPA, Spring Security, Maven
   - Database: MySQL
   - Machine Learning: Python, Pandas, NumPy, Scikit-learn, Jupyter where appropriate

2. **Deployment Environment**
   - Single institution deployment
   - Multiple hospitals connected to the same system instance
   - Secure network connectivity between hospitals
   - Appropriate backup and disaster recovery infrastructure

3. **Data Management**
   - Relational database design with normalization
   - Primary keys, foreign keys, and constraints
   - Indexes for performance optimization
   - Audit fields for traceability

### Operational Assumptions

1. **Hospital Operations**
   - Each hospital has authorized staff for blood bank and platelet operations
   - Hospitals follow standard blood bank practices
   - Medical screening is performed by authorized healthcare personnel
   - Donor verification is performed by authorized staff

2. **Donor Operations**
   - Donors have access to the internet for portal access
   - Donors can provide required information and documents
   - Donors respond to mobilization requests voluntarily
   - Donor preferences are respected

3. **Inter-Hospital Coordination**
   - Hospitals agree to share resources within the institution
   - Transfer logistics (transportation, cold chain) are managed externally
   - Transfer authorization follows institutional policies
   - Transfer decisions are made by authorized staff

### Security Assumptions

1. **Authentication and Authorization**
   - All users authenticate before accessing the system
   - Role-based access control is enforced
   - Hospital-level data isolation is maintained
   - Least privilege principle is followed

2. **Data Protection**
   - Sensitive donor and healthcare information is protected
   - Role-based access control limits data exposure
   - Secure authentication and authorization are implemented
   - Audit logging provides traceability
   - Secure secret management is used for credentials

3. **Medical Decision Responsibility**
   - Actual medical eligibility decisions remain with authorized healthcare personnel
   - The software does not replace clinical judgment
   - The system does not claim medical certification

### ML Assumptions

1. **Model Performance**
   - Model performance is evaluated using actual metrics (MAE, RMSE, R²)
   - No fabricated accuracy values are reported
   - Model performance is based on experimental results
   - Models are retrained and updated as needed

2. **Decision Support**
   - ML provides decision support, not guarantees
   - Human oversight is required for ML-based recommendations
   - ML forecasts expected demand, does not predict exact events
   - ML identifies potential risks, does not predict all shortages

---

## Non-Scope Clarifications

### Clinical Decisions

The system does not make clinical decisions. All medical eligibility determinations, blood transfusion decisions, and clinical judgments remain the responsibility of authorized healthcare personnel.

### Regulatory Certification

The system does not claim regulatory or clinical certification unless explicitly established later. Any future regulatory or institutional compliance requirements must be separately documented and implemented.

### External Logistics

The system does not manage physical transportation logistics (e.g., ambulance services, cold chain management). Transfer logistics are assumed to be managed externally by the institution.

### Unrestricted Hospital Network

The system is not designed as an unrestricted network of unrelated hospitals. Inter-hospital coordination is restricted to hospitals belonging to the same institution.

### Organ Donation

The system does not manage organ donation, organ matching, or transplant workflows. This is explicitly out of scope.

---

## Healthcare Resource Management Principles

### Sensitive Information Protection

The system is intended for healthcare resource management and decision support. Sensitive donor and healthcare-related information must be protected using:

- Role-based access control
- Least-privilege access
- Secure authentication
- Authorization
- Audit logging
- Secure secret management

### Medical Decision Responsibility

Actual medical eligibility decisions remain the responsibility of authorized healthcare/blood-bank personnel. The software should not claim regulatory or clinical certification unless such certification is explicitly established later.

### Future Compliance Requirements

Any future regulatory or institutional compliance requirements can be incorporated as a separate documented requirement if provided. No external regulatory frameworks are assumed at this stage.

---

## Development Status

**Current Module:** Module 0 - Project Specification & Development Rules

**Status:** PLANNING

All application functionality described in this document is **PLANNED**. No implementation has been completed yet.

Future modules will implement the features described in this specification according to the approved module roadmap.

---

## Related Documentation

- [Architecture](ARCHITECTURE.md)
- [Module Roadmap](MODULE_ROADMAP.md)
- [User Roles and Permissions](USER_ROLES_AND_PERMISSIONS.md)
- [Core Workflows](CORE_WORKFLOWS.md)
- [Database Design Principles](DATABASE_DESIGN_PRINCIPLES.md)
- [API Design Guidelines](API_DESIGN_GUIDELINES.md)
- [Security and Access Rules](SECURITY_AND_ACCESS_RULES.md)
- [Development Rules](DEVELOPMENT_RULES.md)
