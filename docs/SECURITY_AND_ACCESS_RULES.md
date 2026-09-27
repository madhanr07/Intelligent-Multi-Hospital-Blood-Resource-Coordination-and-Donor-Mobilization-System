# HemoNexus - Security and Access Rules

## Overview

This document describes the security and access rules for HemoNexus, including authentication vs authorization, RBAC, permissions, hospital-level isolation, institution-level access, least privilege, password security, secret management, environment variables, and sensitive data handling.

**Development Status:** Module 0 - Project Specification & Development Rules (PLANNED)

**IMPORTANT:** This document provides security principles and rules. Actual security implementation (authentication, authorization, RBAC) will be implemented in Module 3.

---

## Authentication vs Authorization

### Authentication

Authentication is the process of verifying who a user is:

- **Purpose**: Verify user identity
- **Mechanism**: Username/password, JWT tokens
- **Outcome**: User is authenticated or not
- **Scope**: Global (user is authenticated across the system)

### Authorization

Authorization is the process of determining what a user can access:

- **Purpose**: Determine user permissions
- **Mechanism**: Roles, permissions, hospital assignment
- **Outcome**: User is authorized or not for specific resource/action
- **Scope**: Contextual (depends on resource, hospital, institution)

### Relationship

```
Authentication (Who are you?)
    ↓
Authorization (What can you do?)
    ↓
Access Granted/Denied
```

### Implementation

- **Authentication**: Implemented in Module 3 using Spring Security and JWT
- **Authorization**: Implemented in Module 3 using RBAC and permission-based access control

---

## Role-Based Access Control (RBAC)

### RBAC Model

HemoNexus uses Role-Based Access Control (RBAC) with the following model:

```
User
  ↓ (has many)
Role
  ↓ (has many)
Permission
```

### User-Role Assignment

- Each user is assigned one or more roles
- Roles define the user's functional area
- Role assignment is performed by Central Admin
- Role assignment can be modified by authorized users

### Role-Permission Assignment

- Each role is assigned specific permissions
- Permissions define what actions a role can perform
- Permission assignment is performed by Central Admin
- Permission assignment can be modified by authorized users

### Permission Inheritance

- Users inherit permissions from their assigned roles
- Multiple roles accumulate permissions
- Deny permissions override allow permissions (if implemented)

### Primary Roles

HemoNexus has four primary roles:

1. **CENTRAL_ADMIN**: Institution-level administration
2. **BLOOD_BANK_STAFF**: Blood inventory and blood intelligence operations
3. **PLATELET_STAFF**: Platelet lifecycle and platelet operations
4. **DONOR**: Donor self-service

### Role Hierarchy

There is no strict role hierarchy. Instead:

- Central Admin has institution-wide access
- Blood Bank Staff has hospital-level blood operations access
- Platelet Staff has hospital-level platelet operations access
- Donor has personal donor portal access

---

## Permissions

### Permission Structure

Permissions follow a hierarchical structure:

```
Resource
  ↓
Action
  ↓
Scope
```

### Permission Naming Convention

Use the following naming convention:

```
{resource}:{action}:{scope}
```

**Examples:**
- `hospital:read:institution` - Read hospitals at institution level
- `hospital:read:hospital` - Read own hospital
- `blood-unit:create:hospital` - Create blood units in own hospital
- `donor:verify:hospital` - Verify donors in own hospital

### Permission Categories

#### Institution Management Permissions
- `institution:read:institution`
- `institution:update:institution`
- `hospital:read:institution`
- `hospital:create:institution`
- `hospital:update:institution`
- `hospital:delete:institution`

#### User Management Permissions
- `user:read:institution`
- `user:create:institution`
- `user:update:institution`
- `user:delete:institution`
- `user:assign-role:institution`
- `user:assign-hospital:institution`

#### Role Management Permissions
- `role:read:institution`
- `role:create:institution`
- `role:update:institution`
- `role:delete:institution`
- `permission:read:institution`
- `permission:assign:institution`

#### Blood Inventory Permissions
- `blood-unit:read:hospital`
- `blood-unit:create:hospital`
- `blood-unit:update:hospital`
- `blood-unit:delete:hospital`

#### Blood Request Permissions
- `blood-request:read:hospital`
- `blood-request:create:hospital`
- `blood-request:update:hospital`
- `blood-request:approve:hospital`
- `blood-request:reject:hospital`

#### Blood Intelligence Permissions
- `blood-intelligence:read:hospital`
- `blood-intelligence:view-forecast:hospital`
- `blood-intelligence:view-risk:hospital`

#### Donor Management Permissions
- `donor:read:hospital`
- `donor:read:institution` (Central Admin)
- `donor:create:hospital`
- `donor:update:hospital`
- `donor:verify:hospital`
- `donor:approve:institution` (Central Admin)

#### Donor Portal Permissions
- `donor-portal:read:personal`
- `donor-portal:update:personal`
- `donor-portal:respond:personal`

#### Platelet Inventory Permissions
- `platelet-product:read:hospital`
- `platelet-product:create:hospital`
- `platelet-product:update:hospital`
- `platelet-product:delete:hospital`

#### Platelet Request Permissions
- `platelet-request:read:hospital`
- `platelet-request:create:hospital`
- `platelet-request:update:hospital`
- `platelet-request:approve:hospital`
- `platelet-request:reject:hospital`

#### Inter-Hospital Coordination Permissions
- `coordination:read:institution` (Central Admin)
- `coordination:create:hospital`
- `coordination:respond:hospital`
- `coordination:approve:institution` (Central Admin)

#### Transfer Permissions
- `transfer:read:hospital`
- `transfer:create:hospital`
- `transfer:dispatch:hospital`
- `transfer:receive:hospital`

#### Expiry and Wastage Permissions
- `expiry:read:hospital`
- `wastage:read:hospital`
- `wastage:create:hospital`
- `wastage:authorize:hospital`

#### Notification Permissions
- `notification:configure:institution` (Central Admin)
- `notification:send:hospital`
- `notification:receive:personal`

#### Dashboard Permissions
- `dashboard:read:institution` (Central Admin)
- `dashboard:read:hospital`
- `dashboard:read:personal` (Donor)

#### Report Permissions
- `report:read:institution` (Central Admin)
- `report:read:hospital`
- `report:read:personal` (Donor)
- `report:generate:institution` (Central Admin)
- `report:generate:hospital`

#### Audit Log Permissions
- `audit-log:read:institution` (Central Admin)
- `audit-log:read:hospital`
- `audit-log:export:institution` (Central Admin)

### Permission Enforcement

- Permissions are enforced at the controller level
- Permissions are enforced at the service level for critical operations
- Permissions are checked before database operations
- Permission violations result in 403 Forbidden response

---

## Hospital-Level Data Isolation

### Hospital Assignment

- Each user (except Central Admin) is assigned to a specific hospital
- Hospital assignment is stored in the user record
- Hospital assignment can only be changed by authorized users

### Data Filtering

- All data queries must include hospital filter for staff roles
- Blood Bank Staff can only access their assigned hospital's data
- Platelet Staff can only access their assigned hospital's data
- Central Admin can access all hospitals' data

### Query Filtering

Example query filtering:

```java
// Blood Bank Staff query
SELECT * FROM blood_unit WHERE hospital_id = :userHospitalId

// Central Admin query
SELECT * FROM blood_unit WHERE hospital_id IN (:accessibleHospitalIds)
```

### Cross-Hospital Access Prevention

- Staff roles cannot access other hospitals' data
- API endpoints must enforce hospital-level filtering
- Frontend must not provide cross-hospital navigation for staff
- Audit logs must track cross-hospital access attempts

### Hospital Context

- Hospital context is extracted from user's authentication token
- Hospital context is passed to all service methods
- Hospital context is used in all database queries
- Hospital context is logged in audit records

---

## Institution-Level Access

### Institution Context

- Institution context is derived from hospital assignment
- Central Admin has institution-level context
- Staff roles have hospital-level context within institution

### Institution-Wide Operations

Central Admin can perform institution-wide operations:

- View all hospitals
- Manage all hospitals
- View all users across hospitals
- View institution-wide reports
- View institution-wide analytics
- Approve institution-wide decisions

### Institution-Level Reports

- Institution-wide inventory reports
- Institution-wide usage reports
- Institution-wide transfer reports
- Institution-wide donor reports
- Institution-wide shortage-risk reports

### Institution-Level Analytics

- Institution-wide dashboards
- Institution-wide trend analysis
- Institution-wide performance metrics
- Cross-hospital comparisons

---

## Least Privilege

### Principle

Users receive the minimum necessary access to perform their duties:

- No unnecessary permissions are granted
- Access is scoped to required resources only
- Access is scoped to required actions only
- Access is scoped to required time period (if applicable)

### Implementation

- Default deny: All access is denied unless explicitly allowed
- Role-based: Permissions are assigned to roles, not individual users
- Justification: Permission assignments must be justified
- Review: Permissions are reviewed periodically

### Permission Review

- Regular permission audits
- Remove unused permissions
- Remove permissions for inactive users
- Update permissions when roles change

### Temporary Access

- Temporary access can be granted for specific tasks
- Temporary access must have expiration
- Temporary access must be approved
- Temporary access must be audited

---

## Password Security

### Password Storage

- Passwords must never be stored in plain text
- Passwords must be hashed using BCrypt
- BCrypt automatically handles salting
- Use appropriate BCrypt strength factor (e.g., 10-12)

### Password Requirements

Enforce strong password requirements:

- Minimum length: 8 characters
- Maximum length: 128 characters
- At least one uppercase letter
- At least one lowercase letter
- At least one digit
- At least one special character
- No common passwords
- No personal information (name, email, etc.)

### Password Hashing Example

```java
String rawPassword = "userPassword123!";
String hashedPassword = passwordEncoder.encode(rawPassword);
```

### Password Verification

```java
boolean matches = passwordEncoder.matches(rawPassword, hashedPassword);
```

### Password Change

- Users must change password on first login (optional)
- Users must change password after password reset
- Users can change password through profile settings
- Old password must be verified before change

### Password Reset

- Password reset through email token
- Reset token must expire (e.g., 1 hour)
- Reset token must be single-use
- Reset must be logged

### Password History

- Prevent password reuse (optional)
- Store password history (hashed)
- Check new password against history

---

## Secret Management

### Secrets to Manage

The following secrets must be managed securely:

- Database credentials (username, password)
- JWT signing secret
- Email service credentials (username, password, API keys)
- SMS service credentials (API keys)
- Third-party API keys
- Encryption keys (if used)

### Environment Variables

- All secrets must be stored in environment variables
- Environment variables must never be committed to Git
- `.env` files must never be committed to Git
- Use `.env.example` as template for required variables

### .env Files

- `.env` files contain actual secrets
- `.env` files must be in `.gitignore`
- `.env` files must not be shared
- `.env` files must have appropriate file permissions

### .env.example Files

- `.env.example` files contain variable names and example values
- `.env.example` files can be committed to Git
- `.env.example` files must not contain actual secrets
- Use placeholder values (e.g., `your-secret-here`)

### Environment Variable Access

```java
@Value("${database.password}")
private String databasePassword;

@Value("${jwt.secret}")
private String jwtSecret;
```

### Secret Rotation

- Secrets should be rotated periodically
- Secret rotation must be planned
- Secret rotation must be tested
- Secret rotation must be documented

---

## Sensitive Data Handling

### Sensitive Data Types

The following data types are considered sensitive:

- Donor personal information (name, address, contact)
- Donor medical information
- User credentials (passwords)
- Hospital operational data
- Transfer details
- Audit logs

### Data Protection

- Sensitive data must be protected using RBAC
- Sensitive data must be protected using least privilege
- Sensitive data access must be audited
- Sensitive data must be encrypted at rest (future consideration)
- Sensitive data must be encrypted in transit (HTTPS)

### Data Masking

- Mask sensitive data in logs
- Mask sensitive data in error messages
- Mask sensitive data in UI (partial display)
- Example: Display partial phone numbers or email addresses

### Data Retention

- Define data retention policies
- Archive old data
- Delete expired data
- Audit data deletion

### Data Export

- Data export must be authorized
- Data export must be logged
- Data export must be filtered by permissions
- Data export must include audit trail

---

## Secure Authentication

### JWT Token Structure

Use JWT (JSON Web Tokens) for authentication:

```json
{
  "sub": "user123",
  "roles": ["BLOOD_BANK_STAFF"],
  "hospitalId": "hospital456",
  "institutionId": "institution789",
  "iat": 1609459200,
  "exp": 1609545600
}
```

### JWT Token Claims

- `sub`: User ID
- `roles`: User roles
- `hospitalId`: Assigned hospital ID (nullable for Central Admin)
- `institutionId`: Institution ID
- `iat`: Issued at timestamp
- `exp`: Expiration timestamp

### JWT Token Security

- Use strong signing secret (at least 256 bits)
- Use appropriate algorithm (HS256 or RS256)
- Set reasonable expiration time (e.g., 1 hour)
- Implement refresh token mechanism (future consideration)
- Validate token on every request

### JWT Token Storage

- Store JWT token in HTTP-only cookie (recommended)
- Or store JWT token in localStorage (with XSS protection)
- Never store JWT token in URL
- Never expose JWT token in logs

### Session Management

- Implement session timeout
- Implement concurrent session limit (optional)
- Implement session revocation (future consideration)
- Log session events

---

## API Security

### Protected Endpoints

- All API endpoints must be protected (except public endpoints)
- Authentication must be required for protected endpoints
- Authorization must be checked for protected endpoints
- Return 401 Unauthorized for missing authentication
- Return 403 Forbidden for insufficient authorization

### Input Validation

- Validate all input data
- Sanitize input to prevent injection attacks
- Use parameterized queries
- Validate file uploads (size, type, content)

### Output Encoding

- Encode output to prevent XSS
- Sanitize data before returning
- Use Content Security Policy (CSP)
- Use XSS protection headers

### Rate Limiting

- Implement rate limiting to prevent abuse
- Limit requests per user
- Limit requests per IP
- Return 429 Too Many Requests when limit exceeded

### CORS Configuration

- Configure CORS for cross-origin requests
- Allow specific origins (not `*` for production)
- Allow necessary HTTP methods
- Allow necessary headers

---

## Frontend Security

### Protected Routes

- Protect frontend routes based on user role
- Redirect unauthorized users to login
- Redirect authorized users to appropriate dashboard
- Check permissions before displaying UI elements

### Token Management

- Store token securely (HTTP-only cookie recommended)
- Include token in API requests
- Handle token expiration
- Implement token refresh (future consideration)

### XSS Prevention

- Escape user input in UI
- Use React's built-in XSS protection
- Validate and sanitize user input
- Use Content Security Policy

### CSRF Prevention

- Implement CSRF tokens for state-changing operations
- Validate CSRF tokens on server
- Use SameSite cookie attribute
- Use HTTP-only cookies

---

## Audit Logging

### Audit Events

Log the following security-relevant events:

- Login attempts (success and failure)
- Logout
- Password changes
- Role changes
- Permission changes
- Hospital assignment changes
- Failed authorization attempts
- Cross-hospital access attempts
- Data export
- Configuration changes

### Audit Log Content

Audit logs must include:

- User ID
- User role
- Hospital ID
- Action performed
- Resource affected
- Record identifier
- Timestamp
- IP address
- User agent
- Result (success/failure)
- Relevant details

### Audit Log Protection

- Audit logs must be protected from unauthorized access
- Audit logs must be protected from modification
- Audit logs must be retained for appropriate period
- Audit logs must be backed up

---

## Security Best Practices

### Defense in Depth

- Implement multiple layers of security
- Don't rely on single security measure
- Secure network, application, and data layers
- Monitor and detect security incidents

### Security by Design

- Consider security from the start
- Perform threat modeling
- Implement security controls
- Test security controls

### Security Testing

- Perform security testing regularly
- Use static analysis tools
- Use dynamic analysis tools
- Perform penetration testing
- Fix security vulnerabilities promptly

### Security Monitoring

- Monitor security events
- Set up alerts for suspicious activity
- Review security logs regularly
- Respond to security incidents promptly

### Security Updates

- Keep dependencies up to date
- Apply security patches promptly
- Monitor security advisories
- Test security updates before deployment

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

## Important Notes

### Module 3 Implementation

- Authentication will be implemented in Module 3
- RBAC will be implemented in Module 3
- Authorization will be implemented in Module 3
- Spring Security will be used for security implementation
- JWT will be used for token-based authentication

### Current Scope

- This document provides security principles and rules
- No authentication implementation in Module 0
- No authorization implementation in Module 0
- No RBAC implementation in Module 0
- No security configuration in Module 0

### Future Refinement

- Security design may be refined during Module 3
- Additional security controls may be added
- Security requirements may evolve
- Security best practices may be updated

---

## Related Documentation

- [Project Specification](PROJECT_SPECIFICATION.md)
- [Architecture](ARCHITECTURE.md)
- [Module Roadmap](MODULE_ROADMAP.md)
- [User Roles and Permissions](USER_ROLES_AND_PERMISSIONS.md)
- [API Design Guidelines](API_DESIGN_GUIDELINES.md)
- [Development Rules](DEVELOPMENT_RULES.md)
