# Database

## Current Database

**Database Name:** `hemonexus`

**Host:** `localhost`

**Port:** `3306`

## Status

**Module 2 COMPLETED** - The complete database schema has been implemented and is ready for use.

### Database Schema Summary

- **Total Tables:** 32 (30 application tables + 1 Flyway schema history table)
- **Migration Tool:** Flyway
- **Migration Version:** V8
- **JPA Entities:** 30 entities created
- **Repository Interfaces:** 26 repositories created

### Tables Created

#### Institution & Hospital Management (2 tables)
- `institution` - Institution-level data
- `hospital` - Hospital-level data with institution ownership

#### Access Control (5 tables)
- `role` - User roles
- `permission` - System permissions
- `role_permission` - Role-permission mapping
- `user` - User accounts
- `user_role` - User-role mapping

#### Donor Management (6 tables)
- `donor` - Donor profiles
- `donor_document` - Donor verification documents
- `donor_verification` - Donor verification records
- `donation` - Blood donation records
- `appointment` - Donation appointments
- `donor_response` - Donor response to requests

#### Blood Inventory (5 tables)
- `blood_unit` - Individual blood units
- `blood_request` - Blood requests
- `blood_request_item` - Blood request line items
- `blood_reservation` - Blood unit reservations
- `blood_usage_record` - Blood usage tracking

#### Platelet Inventory (4 tables)
- `platelet_product` - Individual platelet products
- `platelet_request` - Platelet requests
- `platelet_request_item` - Platelet request line items
- `platelet_reservation` - Platelet product reservations

#### Inter-Hospital Coordination (2 tables)
- `resource_transfer` - Resource transfer requests
- `resource_transfer_item` - Transfer line items

#### Blood Intelligence (3 tables)
- `blood_demand_record` - Historical demand data
- `prediction` - ML predictions
- `risk_assessment` - Risk assessment records

#### Notifications (2 tables)
- `notification` - User notifications
- `notification_preference` - User notification preferences

#### Audit & Compliance (2 tables)
- `wastage_record` - Resource wastage tracking
- `audit_log` - System audit trail

### Schema Design Principles

- **Normalization:** 3NF compliant design
- **Foreign Keys:** All relationships properly defined with ON DELETE/UPDATE rules
- **Indexes:** Strategic indexes on frequently queried columns
- **Audit Fields:** `created_at` and `updated_at` on all relevant tables
- **Status Fields:** Status columns for workflow tracking
- **Data Isolation:** Hospital-level data isolation with institution ownership

### Migration Scripts

Flyway migration scripts are located in:
`backend/src/main/resources/db/migration/`

- `V1__create_institution_and_hospital_tables.sql`
- `V2__create_access_control_tables.sql`
- `V3__create_donor_tables.sql`
- `V4__create_blood_tables.sql`
- `V5__create_platelet_tables.sql`
- `V6__create_transfer_tables.sql`
- `V7__create_intelligence_tables.sql`
- `V8__create_notification_and_audit_tables.sql`

### JPA Entities

All entities are organized by domain in `backend/src/main/java/com/project/bloodsystem/entity/`:
- `entity.institution` - Institution, Hospital
- `entity.access` - Role, Permission, RolePermission, User, UserRole
- `entity.donor` - Donor, DonorDocument, DonorVerification, Donation, Appointment, DonorResponse
- `entity.blood` - BloodUnit, BloodRequest, BloodRequestItem, BloodReservation, BloodUsageRecord
- `entity.platelet` - PlateletProduct, PlateletRequest, PlateletRequestItem, PlateletReservation
- `entity.transfer` - ResourceTransfer, ResourceTransferItem
- `entity.intelligence` - BloodDemandRecord, Prediction, RiskAssessment
- `entity.notification` - Notification, NotificationPreference
- `entity.audit` - WastageRecord, AuditLog

### Repository Interfaces

Spring Data JPA repositories are in `backend/src/main/java/com/project/bloodsystem/repository/`:
- 26 repository interfaces extending JpaRepository
- Custom query methods for common operations
- Domain-specific finders for each entity type

## Connection Configuration

Database connection is configured through environment variables:
- `DB_HOST` (default: localhost)
- `DB_PORT` (default: 3306)
- `DB_NAME` (default: hemonexus)
- `DB_USERNAME` (default: root)
- `DB_PASSWORD` (must be provided via environment variable)

See `.env.example` for the expected environment variable format.

## Running Migrations

Migrations run automatically on Spring Boot startup when `spring.flyway.enabled=true`.

To manually run migrations:
```bash
cd backend
mvn spring-boot:run
```

## Important Notes

- **DDL Auto:** Set to `none` - Flyway manages all schema changes
- **No Fake Data:** No seed data is inserted - tables are empty
- **Security:** Database credentials are never hardcoded; always use environment variables
- **Version Control:** All schema changes must be done via Flyway migrations
