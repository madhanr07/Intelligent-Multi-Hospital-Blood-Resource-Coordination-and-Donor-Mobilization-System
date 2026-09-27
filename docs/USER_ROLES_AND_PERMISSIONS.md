# HemoNexus - User Roles and Permissions

## Overview

This document describes the four primary user types in HemoNexus, their responsibilities, module access, hospital scope, institution scope, and access restrictions.

**Development Status:** Module 0 - Project Specification & Development Rules (PLANNED)

---

## User Types

HemoNexus has four primary user types:

1. **Central Admin** - Institution-level administration and oversight
2. **Blood Bank Staff** - Blood inventory and blood intelligence operations
3. **Platelet Staff** - Platelet lifecycle and platelet operations
4. **Donor** - Donor self-service through donor portal

---

## 1. Central Admin

### Role Description
Institution-level administration and oversight. Central Admin users have institution-wide visibility and management capabilities but should not automatically perform every operational blood-bank or platelet transaction.

### Responsibilities

#### Institution Management
- Institution configuration and management
- Hospital registration and management
- Hospital status monitoring
- Institution-wide resource overview

#### User and Role Management
- Staff/user management across the institution
- Role management
- Permission management
- Role-permission assignment
- User-role assignment
- User-hospital assignment

#### Resource Oversight
- Institution-wide blood resource overview
- Institution-wide platelet resource overview
- Blood shortage-risk overview
- Platelet shortage-risk overview

#### Donor Oversight
- Donor verification oversight
- Donor mobilization oversight
- Donor activity monitoring

#### Coordination Oversight
- Inter-hospital coordination oversight
- Transfer activity monitoring
- Coordination approval (if required by policy)

#### Reports and Audit
- Institution-wide reports
- Audit log viewing
- System-level alerts
- Performance analytics

### Module Access

| Module | Access Level | Notes |
|--------|--------------|-------|
| Module 3: Authentication & RBAC | Full | Can manage users, roles, permissions |
| Module 4: Central Admin | Full | Primary user of this module |
| Module 5: Blood Bank Management | Read-only | Oversight, not operational transactions |
| Module 6: Blood Request Management | Read-only | Oversight, not operational transactions |
| Module 7: Blood Intelligence & ML | Read-only | Can view forecasts and risk assessments |
| Module 8: Donor Registration & Verification | Read/Approve | Can oversee verification, not perform routine verification |
| Module 9: Donor Portal | No Access | Donor portal is for donors only |
| Module 10: Donor Mobilization | Read-only | Oversight, not operational mobilization |
| Module 11: Platelet Management | Read-only | Oversight, not operational transactions |
| Module 12: Platelet Request Management | Read-only | Oversight, not operational transactions |
| Module 13: Multi-Hospital Coordination | Read/Approve | Can oversee and approve if required |
| Module 14: Reservation & Transfer | Read-only | Oversight, not operational transfers |
| Module 15: Expiry, Wastage & Alerts | Read-only | Oversight, not operational recording |
| Module 16: Notification System | Configure | Can configure notification rules |
| Module 17: Dashboards & Analytics | Full | Institution-wide analytics |
| Module 18: Reports | Full | Institution-wide reports |
| Module 19: Audit Logging | Full | Full audit log access |

### Hospital Scope
- **Institution-level access**: Can access data across all hospitals in the institution
- **No hospital restriction**: Not limited to a specific hospital
- **Cross-hospital visibility**: Can view and compare data across hospitals

### Institution Scope
- **Full institution access**: Complete access to institution-level data and functions
- **Institution management**: Can manage institution configuration
- **Hospital management**: Can manage all hospitals within the institution

### Access Restrictions
- Should not automatically perform routine operational blood-bank transactions
- Should not automatically perform routine operational platelet transactions
- Cannot access donor portal (reserved for donors)
- Cannot modify individual donor preferences without authorization
- Cannot override hospital-level operational decisions without authorization

---

## 2. Blood Bank Staff

### Role Description
Operational management of blood inventory, blood requests, blood intelligence, and donor mobilization. Blood Bank Staff focus on blood-related operations and do not automatically receive access to platelet operations.

### Responsibilities

#### Blood Inventory Management
- Blood unit registration
- Blood inventory tracking
- Blood unit status management
- Blood type and Rh factor management
- Component tracking
- Inventory search and filtering
- Blood availability monitoring

#### Blood Request Management
- Blood request creation
- Blood request item specification
- Request status tracking
- Request approval/rejection
- Request fulfillment workflow
- Blood unit reservation for requests
- Blood unit issuance
- Request history

#### Blood Usage Recording
- Blood usage recording
- Usage documentation
- Usage history tracking

#### Blood Demand Intelligence
- Blood demand intelligence viewing
- Shortage-risk assessment viewing
- Demand forecast interpretation
- Proactive planning based on intelligence

#### Donor Mobilization
- Donor identification for blood requirements
- Targeted donor notification for blood requests
- Donor response tracking relevant to blood requirements
- Donation operational records
- Appointment scheduling for blood donations

#### Authorized Blood Coordination
- Inter-hospital blood resource requests
- Blood availability evaluation
- Blood transfer acceptance/rejection
- Blood transfer coordination

### Module Access

| Module | Access Level | Notes |
|--------|--------------|-------|
| Module 3: Authentication & RBAC | Standard | Can authenticate, cannot manage users/roles |
| Module 4: Central Admin | No Access | Reserved for Central Admin |
| Module 5: Blood Bank Management | Full | Primary user of this module |
| Module 6: Blood Request Management | Full | Primary user of this module |
| Module 7: Blood Intelligence & ML | Full | Can view and use intelligence for planning |
| Module 8: Donor Registration & Verification | Read/Verify | Can verify donors, not manage system-wide donor settings |
| Module 9: Donor Portal | No Access | Donor portal is for donors only |
| Module 10: Donor Mobilization | Full | Primary user of this module for blood mobilization |
| Module 11: Platelet Management | No Access | Reserved for Platelet Staff |
| Module 12: Platelet Request Management | No Access | Reserved for Platelet Staff |
| Module 13: Multi-Hospital Coordination | Full | For blood resource coordination only |
| Module 14: Reservation & Transfer | Full | For blood transfers only |
| Module 15: Expiry, Wastage & Alerts | Full | For blood units only |
| Module 16: Notification System | Send | Can send blood-related notifications |
| Module 17: Dashboards & Analytics | Blood-specific | Blood-focused dashboards and analytics |
| Module 18: Reports | Blood-specific | Blood-focused reports |
| Module 19: Audit Logging | Hospital-level | Can view hospital-level audit logs |

### Hospital Scope
- **Hospital-level access**: Can only access data for their assigned hospital
- **Hospital isolation**: Cannot view data from other hospitals
- **Hospital-specific operations**: All operations are scoped to their hospital

### Institution Scope
- **Limited institution access**: Can view institution-level reports if authorized
- **No institution management**: Cannot manage institution configuration
- **No hospital management**: Cannot manage other hospitals

### Access Restrictions
- Cannot access platelet management functions
- Cannot access platelet request functions
- Cannot access donor portal
- Cannot view data from other hospitals
- Cannot manage users, roles, or permissions
- Cannot modify institution configuration
- Cannot manage other hospitals

---

## 3. Platelet Staff

### Role Description
Operational management of platelet lifecycle, platelet inventory, platelet requests, and platelet transfers. Platelet Staff focus on platelet-related operations and do not automatically receive access to blood intelligence operational functions.

### Responsibilities

#### Platelet Registration
- Platelet product registration
- Platelet attribute recording
- Platelet collection recording

#### Platelet Inventory
- Platelet inventory tracking
- Platelet status management
- Platelet availability monitoring
- Platelet inventory search and filtering

#### Platelet Requests
- Platelet request creation
- Platelet request item specification
- Request status tracking
- Request approval/rejection
- Request fulfillment workflow

#### Reservations
- Platelet reservation for requests
- Reservation status management
- Reservation cancellation

#### Issue and Receipt
- Platelet issuance
- Platelet receipt recording
- Usage documentation

#### Expiry Monitoring
- Platelet expiry monitoring
- Near-expiry alert viewing
- Expiry status updates
- Expiry-based prioritization

#### Wastage and Disposal
- Wastage recording
- Disposal recording
- Disposal authorization
- Reason documentation

#### Platelet Transfers
- Inter-hospital platelet resource requests
- Platelet availability evaluation
- Platelet transfer acceptance/rejection
- Platelet transfer coordination

#### Platelet Reports
- Platelet inventory reports
- Platelet usage reports
- Platelet transfer reports
- Platelet expiry reports
- Platelet wastage reports

### Module Access

| Module | Access Level | Notes |
|--------|--------------|-------|
| Module 3: Authentication & RBAC | Standard | Can authenticate, cannot manage users/roles |
| Module 4: Central Admin | No Access | Reserved for Central Admin |
| Module 5: Blood Bank Management | No Access | Reserved for Blood Bank Staff |
| Module 6: Blood Request Management | No Access | Reserved for Blood Bank Staff |
| Module 7: Blood Intelligence & ML | No Access | Reserved for Blood Bank Staff |
| Module 8: Donor Registration & Verification | No Access | Not involved in donor verification |
| Module 9: Donor Portal | No Access | Donor portal is for donors only |
| Module 10: Donor Mobilization | No Access | Not involved in donor mobilization |
| Module 11: Platelet Management | Full | Primary user of this module |
| Module 12: Platelet Request Management | Full | Primary user of this module |
| Module 13: Multi-Hospital Coordination | Full | For platelet resource coordination only |
| Module 14: Reservation & Transfer | Full | For platelet transfers only |
| Module 15: Expiry, Wastage & Alerts | Full | Primary user of this module for platelets |
| Module 16: Notification System | Send | Can send platelet-related notifications |
| Module 17: Dashboards & Analytics | Platelet-specific | Platelet-focused dashboards and analytics |
| Module 18: Reports | Platelet-specific | Platelet-focused reports |
| Module 19: Audit Logging | Hospital-level | Can view hospital-level audit logs |

### Hospital Scope
- **Hospital-level access**: Can only access data for their assigned hospital
- **Hospital isolation**: Cannot view data from other hospitals
- **Hospital-specific operations**: All operations are scoped to their hospital

### Institution Scope
- **Limited institution access**: Can view institution-level reports if authorized
- **No institution management**: Cannot manage institution configuration
- **No hospital management**: Cannot manage other hospitals

### Access Restrictions
- Cannot access blood bank management functions
- Cannot access blood request functions
- Cannot access blood intelligence functions
- Cannot access donor portal
- Cannot access donor mobilization functions
- Cannot view data from other hospitals
- Cannot manage users, roles, or permissions
- Cannot modify institution configuration
- Cannot manage other hospitals

---

## 4. Donor

### Role Description
Donors have a separate dedicated portal for self-service capabilities. Donors can manage their profile, view donation history, respond to mobilization requests, and manage appointments.

### Responsibilities

#### Donor Registration
- Self-registration through donor portal
- Enter required information
- Submit required documents/information
- Profile completion

#### Profile Management
- Manage own profile
- Update personal information
- Update contact information
- Manage preferences

#### Verification Status
- View verification status
- View verification requirements
- View verification history

#### Donation History
- View donation history
- View donation records
- View donation dates and locations

#### Requests and Notifications
- Receive donation requests
- Receive emergency requests
- Receive appointment notifications
- View request details
- Respond to requests (accept/decline)
- View request status

#### Appointment Management
- Manage appointments
- Schedule appointments
- Cancel appointments
- View appointment history

#### Preferences
- Manage notification preferences
- Manage availability preferences
- Manage contact preferences

### Module Access

| Module | Access Level | Notes |
|--------|--------------|-------|
| Module 3: Authentication & RBAC | Standard | Can authenticate to donor portal |
| Module 4: Central Admin | No Access | Reserved for Central Admin |
| Module 5: Blood Bank Management | No Access | Reserved for Blood Bank Staff |
| Module 6: Blood Request Management | No Access | Reserved for Blood Bank Staff |
| Module 7: Blood Intelligence & ML | No Access | Reserved for Blood Bank Staff |
| Module 8: Donor Registration & Verification | Self-service | Can register and view own verification status |
| Module 9: Donor Portal | Full | Primary user of this module |
| Module 10: Donor Mobilization | Receive | Can receive and respond to mobilization requests |
| Module 11: Platelet Management | No Access | Reserved for Platelet Staff |
| Module 12: Platelet Request Management | No Access | Reserved for Platelet Staff |
| Module 13: Multi-Hospital Coordination | No Access | Reserved for hospital staff |
| Module 14: Reservation & Transfer | No Access | Reserved for hospital staff |
| Module 15: Expiry, Wastage & Alerts | No Access | Reserved for hospital staff |
| Module 16: Notification System | Receive | Can receive notifications |
| Module 17: Dashboards & Analytics | Personal | Can view personal donation history and statistics |
| Module 18: Reports | Personal | Can view personal donation reports |
| Module 19: Audit Logging | No Access | Reserved for staff and admin |

### Hospital Scope
- **No hospital assignment**: Donors are not assigned to a specific hospital
- **Institution-level**: Donors belong to the institution, not a specific hospital
- **Hospital association**: Donors may have preferred donation hospitals

### Institution Scope
- **Institution-level access**: Donors are associated with the institution
- **No institution management**: Cannot manage institution configuration
- **No hospital management**: Cannot manage hospitals

### Access Restrictions
- Cannot access hospital administrative dashboards
- Cannot access other users' information
- Cannot access blood inventory data
- Cannot access platelet inventory data
- Cannot access blood intelligence data
- Cannot perform operational transactions
- Cannot manage users, roles, or permissions
- Cannot modify institution configuration
- Cannot manage hospitals
- Cannot view other donors' information

---

## Permission Matrix

### High-Level Permission Categories

| Permission Category | Central Admin | Blood Bank Staff | Platelet Staff | Donor |
|---------------------|---------------|------------------|----------------|-------|
| Institution Management | Full | None | None | None |
| Hospital Management | Full | None | None | None |
| User Management | Full institution | None | None | Self only |
| Role Management | Full | None | None | None |
| Permission Management | Full | None | None | None |
| Blood Inventory | Read-only | Full (own hospital) | None | None |
| Blood Requests | Read-only | Full (own hospital) | None | None |
| Blood Intelligence | Read-only | Full (own hospital) | None | None |
| Donor Verification | Read/Approve | Verify (own hospital) | None | Self-view |
| Donor Portal | None | None | None | Full |
| Donor Mobilization | Read-only | Full (own hospital) | None | Receive/Respond |
| Platelet Inventory | Read-only | None | Full (own hospital) | None |
| Platelet Requests | Read-only | None | Full (own hospital) | None |
| Inter-hospital Coordination | Read/Approve | Blood only (own hospital) | Platelet only (own hospital) | None |
| Reservations | Read-only | Blood only (own hospital) | Platelet only (own hospital) | None |
| Transfers | Read-only | Blood only (own hospital) | Platelet only (own hospital) | None |
| Expiry Monitoring | Read-only | Blood only (own hospital) | Platelet only (own hospital) | None |
| Wastage Recording | Read-only | Blood only (own hospital) | Platelet only (own hospital) | None |
| Notifications | Configure | Send (blood) | Send (platelet) | Receive |
| Dashboards | Institution-wide | Blood-specific (own hospital) | Platelet-specific (own hospital) | Personal |
| Reports | Institution-wide | Blood-specific (own hospital) | Platelet-specific (own hospital) | Personal |
| Audit Logs | Full | Hospital-level | Hospital-level | None |

### Detailed Permission Matrix

#### Institution and Hospital Management

| Action | Central Admin | Blood Bank Staff | Platelet Staff | Donor |
|--------|---------------|------------------|----------------|-------|
| View institution details | ✅ | ❌ | ❌ | ❌ |
| Edit institution details | ✅ | ❌ | ❌ | ❌ |
| View hospital list | ✅ | ❌ | ❌ | ❌ |
| Add hospital | ✅ | ❌ | ❌ | ❌ |
| Edit hospital details | ✅ | ❌ | ❌ | ❌ |
| Deactivate hospital | ✅ | ❌ | ❌ | ❌ |
| View hospital details (own) | ✅ | ✅ | ✅ | ❌ |
| View hospital details (other) | ✅ | ❌ | ❌ | ❌ |

#### User and Role Management

| Action | Central Admin | Blood Bank Staff | Platelet Staff | Donor |
|--------|---------------|------------------|----------------|-------|
| View user list | ✅ | ❌ | ❌ | ❌ |
| Add user | ✅ | ❌ | ❌ | ❌ |
| Edit user details | ✅ | ❌ | ❌ | Self only |
| Deactivate user | ✅ | ❌ | ❌ | ❌ |
| Assign role to user | ✅ | ❌ | ❌ | ❌ |
| Assign hospital to user | ✅ | ❌ | ❌ | ❌ |
| View role list | ✅ | ❌ | ❌ | ❌ |
| Add role | ✅ | ❌ | ❌ | ❌ |
| Edit role | ✅ | ❌ | ❌ | ❌ |
| View permission list | ✅ | ❌ | ❌ | ❌ |
| Assign permission to role | ✅ | ❌ | ❌ | ❌ |

#### Blood Inventory Management

| Action | Central Admin | Blood Bank Staff | Platelet Staff | Donor |
|--------|---------------|------------------|----------------|-------|
| View blood inventory (own hospital) | ✅ | ✅ | ❌ | ❌ |
| View blood inventory (other hospital) | ✅ | ❌ | ❌ | ❌ |
| Add blood unit | ❌ | ✅ | ❌ | ❌ |
| Edit blood unit | ❌ | ✅ | ❌ | ❌ |
| Update blood unit status | ❌ | ✅ | ❌ | ❌ |
| Delete blood unit | ❌ | ✅ | ❌ | ❌ |
| Search blood inventory | ✅ | ✅ | ❌ | ❌ |

#### Blood Request Management

| Action | Central Admin | Blood Bank Staff | Platelet Staff | Donor |
|--------|---------------|------------------|----------------|-------|
| View blood requests (own hospital) | ✅ | ✅ | ❌ | ❌ |
| View blood requests (other hospital) | ✅ | ❌ | ❌ | ❌ |
| Create blood request | ❌ | ✅ | ❌ | ❌ |
| Edit blood request | ❌ | ✅ | ❌ | ❌ |
| Approve blood request | ❌ | ✅ | ❌ | ❌ |
| Reject blood request | ❌ | ✅ | ❌ | ❌ |
| Cancel blood request | ❌ | ✅ | ❌ | ❌ |

#### Blood Intelligence

| Action | Central Admin | Blood Bank Staff | Platelet Staff | Donor |
|--------|---------------|------------------|----------------|-------|
| View demand forecasts | ✅ | ✅ | ❌ | ❌ |
| View shortage-risk assessments | ✅ | ✅ | ❌ | ❌ |
| View trend analysis | ✅ | ✅ | ❌ | ❌ |
| Configure ML parameters | ✅ | ❌ | ❌ | ❌ |

#### Donor Management

| Action | Central Admin | Blood Bank Staff | Platelet Staff | Donor |
|--------|---------------|------------------|----------------|-------|
| View donor list (own hospital) | ✅ | ✅ | ❌ | ❌ |
| View donor list (other hospital) | ✅ | ❌ | ❌ | ❌ |
| View donor details (own hospital) | ✅ | ✅ | ❌ | Self only |
| Add donor (hospital-assisted) | ❌ | ✅ | ❌ | Self-register |
| Edit donor details | ❌ | Verify only | ❌ | Self only |
| Verify donor | Approve only | Verify | ❌ | ❌ |
| Approve donor | ✅ | ❌ | ❌ | ❌ |
| Reject donor | ✅ | ✅ | ❌ | ❌ |
| Deactivate donor | ✅ | ✅ | ❌ | ❌ |

#### Donor Portal

| Action | Central Admin | Blood Bank Staff | Platelet Staff | Donor |
|--------|---------------|------------------|----------------|-------|
| Access donor portal | ❌ | ❌ | ❌ | ✅ |
| View own profile | ❌ | ❌ | ❌ | ✅ |
| Edit own profile | ❌ | ❌ | ❌ | ✅ |
| View own donation history | ❌ | ❌ | ❌ | ✅ |
| View own verification status | ❌ | ❌ | ❌ | ✅ |
| View received requests | ❌ | ❌ | ❌ | ✅ |
| Respond to requests | ❌ | ❌ | ❌ | ✅ |
| Manage appointments | ❌ | ❌ | ❌ | ✅ |
| Manage preferences | ❌ | ❌ | ❌ | ✅ |

#### Donor Mobilization

| Action | Central Admin | Blood Bank Staff | Platelet Staff | Donor |
|--------|---------------|------------------|----------------|-------|
| View mobilization campaigns | ✅ | ✅ | ❌ | ❌ |
| Create mobilization campaign | ❌ | ✅ | ❌ | ❌ |
| Identify donors for mobilization | ❌ | ✅ | ❌ | ❌ |
| Send mobilization notifications | ❌ | ✅ | ❌ | ❌ |
| View donor responses | ✅ | ✅ | ❌ | ❌ |
| Receive mobilization requests | ❌ | ❌ | ❌ | ✅ |
| Respond to mobilization requests | ❌ | ❌ | ❌ | ✅ |

#### Platelet Inventory Management

| Action | Central Admin | Blood Bank Staff | Platelet Staff | Donor |
|--------|---------------|------------------|----------------|-------|
| View platelet inventory (own hospital) | ✅ | ❌ | ✅ | ❌ |
| View platelet inventory (other hospital) | ✅ | ❌ | ❌ | ❌ |
| Add platelet product | ❌ | ❌ | ✅ | ❌ |
| Edit platelet product | ❌ | ❌ | ✅ | ❌ |
| Update platelet status | ❌ | ❌ | ✅ | ❌ |
| Delete platelet product | ❌ | ❌ | ✅ | ❌ |
| Search platelet inventory | ✅ | ❌ | ✅ | ❌ |

#### Platelet Request Management

| Action | Central Admin | Blood Bank Staff | Platelet Staff | Donor |
|--------|---------------|------------------|----------------|-------|
| View platelet requests (own hospital) | ✅ | ❌ | ✅ | ❌ |
| View platelet requests (other hospital) | ✅ | ❌ | ❌ | ❌ |
| Create platelet request | ❌ | ❌ | ✅ | ❌ |
| Edit platelet request | ❌ | ❌ | ✅ | ❌ |
| Approve platelet request | ❌ | ❌ | ✅ | ❌ |
| Reject platelet request | ❌ | ❌ | ✅ | ❌ |
| Cancel platelet request | ❌ | ❌ | ✅ | ❌ |

#### Inter-Hospital Coordination

| Action | Central Admin | Blood Bank Staff | Platelet Staff | Donor |
|--------|---------------|------------------|----------------|-------|
| View coordination requests (institution) | ✅ | ❌ | ❌ | ❌ |
| View coordination requests (own hospital) | ✅ | ✅ (blood) | ✅ (platelet) | ❌ |
| Create blood coordination request | ❌ | ✅ | ❌ | ❌ |
| Create platelet coordination request | ❌ | ❌ | ✅ | ❌ |
| Accept coordination request | Approve | ✅ | ✅ | ❌ |
| Reject coordination request | Approve | ✅ | ✅ | ❌ |
| View transfer status | ✅ | ✅ (blood) | ✅ (platelet) | ❌ |

#### Reservations and Transfers

| Action | Central Admin | Blood Bank Staff | Platelet Staff | Donor |
|--------|---------------|------------------|----------------|-------|
| View reservations (own hospital) | ✅ | ✅ (blood) | ✅ (platelet) | ❌ |
| Create blood reservation | ❌ | ✅ | ❌ | ❌ |
| Create platelet reservation | ❌ | ❌ | ✅ | ❌ |
| Cancel reservation | ❌ | ✅ | ✅ | ❌ |
| View transfer status | ✅ | ✅ (blood) | ✅ (platelet) | ❌ |
| Record dispatch | ❌ | ✅ (blood) | ✅ (platelet) | ❌ |
| Record receipt | ❌ | ✅ (blood) | ✅ (platelet) | ❌ |

#### Expiry and Wastage

| Action | Central Admin | Blood Bank Staff | Platelet Staff | Donor |
|--------|---------------|------------------|----------------|-------|
| View expiry alerts (own hospital) | ✅ | ✅ (blood) | ✅ (platelet) | ❌ |
| View wastage records (own hospital) | ✅ | ✅ (blood) | ✅ (platelet) | ❌ |
| Record blood wastage | ❌ | ✅ | ❌ | ❌ |
| Record platelet wastage | ❌ | ❌ | ✅ | ❌ |
| Authorize disposal | ❌ | ✅ (blood) | ✅ (platelet) | ❌ |

#### Notifications

| Action | Central Admin | Blood Bank Staff | Platelet Staff | Donor |
|--------|---------------|------------------|----------------|-------|
| Configure notification rules | ✅ | ❌ | ❌ | ❌ |
| Send blood notifications | ❌ | ✅ | ❌ | ❌ |
| Send platelet notifications | ❌ | ❌ | ✅ | ❌ |
| Receive notifications | ✅ | ✅ | ✅ | ✅ |
| Manage notification preferences | ❌ | ❌ | ❌ | ✅ |

#### Dashboards and Analytics

| Action | Central Admin | Blood Bank Staff | Platelet Staff | Donor |
|--------|---------------|------------------|----------------|-------|
| View institution-wide dashboard | ✅ | ❌ | ❌ | ❌ |
| View blood dashboard (own hospital) | ✅ | ✅ | ❌ | ❌ |
| View platelet dashboard (own hospital) | ✅ | ❌ | ✅ | ❌ |
| View personal dashboard | ❌ | ❌ | ❌ | ✅ |
| View institution-wide analytics | ✅ | ❌ | ❌ | ❌ |
| View blood analytics (own hospital) | ✅ | ✅ | ❌ | ❌ |
| View platelet analytics (own hospital) | ✅ | ❌ | ✅ | ❌ |
| View personal analytics | ❌ | ❌ | ❌ | ✅ |

#### Reports

| Action | Central Admin | Blood Bank Staff | Platelet Staff | Donor |
|--------|---------------|------------------|----------------|-------|
| Generate institution-wide reports | ✅ | ❌ | ❌ | ❌ |
| Generate blood reports (own hospital) | ✅ | ✅ | ❌ | ❌ |
| Generate platelet reports (own hospital) | ✅ | ❌ | ✅ | ❌ |
| Generate personal reports | ❌ | ❌ | ❌ | ✅ |
| Export reports | ✅ | ✅ | ✅ | ✅ |

#### Audit Logs

| Action | Central Admin | Blood Bank Staff | Platelet Staff | Donor |
|--------|---------------|------------------|----------------|-------|
| View institution-wide audit logs | ✅ | ❌ | ❌ | ❌ |
| View hospital-level audit logs (own) | ✅ | ✅ | ✅ | ❌ |
| View hospital-level audit logs (other) | ✅ | ❌ | ❌ | ❌ |
| Export audit logs | ✅ | ✅ | ✅ | ❌ |

---

## Access Control Principles

### Authentication vs Authorization

- **Authentication**: Verifies who the user is (login with username/password)
- **Authorization**: Determines what the user can access (roles and permissions)

### Role-Based Access Control (RBAC)

- Users are assigned one or more roles
- Roles are assigned permissions
- Users inherit permissions from their roles
- Access is granted based on role membership

### Permission-Based Authorization

- Fine-grained permissions for specific actions
- Permissions can be assigned to roles
- Access is granted based on permission possession

### Hospital-Level Data Isolation

- Blood Bank Staff and Platelet Staff can only access their assigned hospital's data
- Cross-hospital data access is restricted to Central Admin
- All queries must include hospital filter for staff roles

### Institution-Level Access

- Central Admin has institution-wide access
- Institution-level reports and analytics are available to Central Admin
- Hospital staff have limited institution-level access (reports only)

### Least Privilege

- Users receive minimum necessary access to perform their duties
- No unnecessary permissions are granted
- Access is reviewed and revoked when no longer needed

---

## Security Considerations

### Password Security
- Passwords must be hashed using BCrypt
- Plain text passwords must never be stored
- Password complexity requirements must be enforced

### Session Management
- JWT tokens for authentication
- Token expiration must be configured
- Refresh token mechanism (future consideration)

### Audit Trail
- All important operations must be logged
- Audit logs must capture user, role, hospital, action, entity, and timestamp
- Audit logs must be protected from unauthorized modification

### Data Protection
- Sensitive donor information must be protected
- Healthcare-related information must be protected
- Role-based access control limits data exposure
- Hospital-level isolation prevents cross-hospital data leakage

---

## Related Documentation

- [Project Specification](PROJECT_SPECIFICATION.md)
- [Architecture](ARCHITECTURE.md)
- [Module Roadmap](MODULE_ROADMAP.md)
- [Core Workflows](CORE_WORKFLOWS.md)
- [Security and Access Rules](SECURITY_AND_ACCESS_RULES.md)
- [Development Rules](DEVELOPMENT_RULES.md)
