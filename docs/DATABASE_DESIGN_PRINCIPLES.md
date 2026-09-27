# HemoNexus - Database Design Principles

## Overview

This document describes the database design principles for HemoNexus, including relational design principles, normalization, entity ownership, hospital/institution relationships, primary keys, foreign keys, indexes, constraints, status fields, audit fields, candidate domain entities, and rules for avoiding redundant tables.

**Development Status:** Module 0 - Project Specification & Development Rules (PLANNED)

**IMPORTANT:** This document provides design principles and candidate domain entities. Do NOT create SQL tables or entity classes in Module 0. The complete database design will be formally reviewed and implemented in Module 2.

---

## Relational Design Principles

### Normalization

The database must follow normalized relational design principles:

- **First Normal Form (1NF)**: All attributes must be atomic (no repeating groups or multi-valued attributes)
- **Second Normal Form (2NF)**: All non-key attributes must be fully dependent on the entire primary key (no partial dependencies)
- **Third Normal Form (3NF)**: All non-key attributes must be directly dependent on the primary key (no transitive dependencies)
- **Boyce-Codd Normal Form (BCNF)**: Every determinant must be a candidate key

### Benefits of Normalization

- Eliminates data redundancy
- Ensures data consistency
- Simplifies data maintenance
- Improves data integrity
- Reduces update anomalies

### Denormalization Considerations

Denormalization may be considered only for:
- Performance optimization (after normalization)
- Read-heavy workloads with proven performance issues
- Reporting requirements (with documented justification)

Any denormalization must be:
- Documented with rationale
- Approved during database design review
- Implemented with data consistency mechanisms

---

## Primary Keys

### Primary Key Guidelines

- Every table must have a primary key
- Primary keys should be:
  - Unique: No duplicate values
  - Immutable: Values should not change
  - Minimal: Smallest number of columns necessary
  - Simple: Prefer single-column keys over composite keys

### Primary Key Strategies

- **Surrogate Keys**: Use auto-incrementing integer or UUID for most tables
- **Natural Keys**: Use for lookup tables where a natural identifier exists (e.g., role names)
- **Composite Keys**: Use only when necessary for many-to-many relationships

### Primary Key Naming Convention

- Use `id` for single-column surrogate keys
- Use descriptive names for natural keys (e.g., `role_name`)
- Use composite key names for junction tables (e.g., `user_id`, `role_id`)

---

## Foreign Keys

### Foreign Key Guidelines

- All relationships must be enforced through foreign keys
- Foreign keys must reference existing primary keys
- Foreign key constraints must be defined to ensure referential integrity

### Referential Integrity

- **ON DELETE**: Specify behavior when referenced record is deleted
  - `RESTRICT`: Prevent deletion (default, safest)
  - `CASCADE`: Delete dependent records (use carefully)
  - `SET NULL`: Set foreign key to NULL (if nullable)
  - `SET DEFAULT`: Set foreign key to default value (if defined)

- **ON UPDATE**: Specify behavior when referenced primary key is updated
  - `CASCADE`: Update dependent records (if using natural keys)
  - `RESTRICT`: Prevent update (default for surrogate keys)

### Foreign Key Naming Convention

- Use singular form of referenced table name + `_id`
- Example: `hospital_id` references `hospital.id`

---

## Unique Constraints

### Unique Constraint Guidelines

- Unique constraints should be defined for:
  - Business identifiers (e.g., donor registration number)
  - Natural keys (e.g., user email)
  - Combinations that must be unique (e.g., hospital + blood type + date)

### Unique Constraint vs. Primary Key

- Primary key: One per table, identifies each record
- Unique constraint: Multiple per table, enforces business rules

---

## Indexes

### Index Guidelines

- Indexes should be created for:
  - All foreign keys (for join performance)
  - Frequently queried columns (for search performance)
  - Columns used in WHERE clauses
  - Columns used in ORDER BY clauses
  - Columns used in JOIN conditions

### Index Types

- **B-Tree Index**: Default for most use cases
- **Unique Index**: For unique constraints
- **Composite Index**: For multi-column queries
- **Full-Text Index**: For text search (future consideration)

### Index Trade-offs

- Indexes improve read performance
- Indexes slow down write operations (INSERT, UPDATE, DELETE)
- Indexes consume storage space
- Indexes must be maintained

### Index Strategy

- Start with essential indexes (foreign keys, primary keys)
- Add indexes based on query patterns
- Monitor index usage
- Remove unused indexes

---

## Constraints

### Data Integrity Constraints

- **NOT NULL**: Column must have a value
- **DEFAULT**: Default value if none provided
- **CHECK**: Validate column values against expression
- **UNIQUE**: Ensure column values are unique
- **FOREIGN KEY**: Ensure referential integrity

### Business Rule Constraints

- Use CHECK constraints for:
  - Value ranges (e.g., quantity > 0)
  - Status transitions (e.g., status in ('pending', 'approved', 'rejected'))
  - Date validations (e.g., expiry_date > collection_date)

---

## Status Fields

### Status Field Guidelines

- Status fields should use meaningful, human-readable values
- Status fields should be consistent across related entities
- Status transitions should be documented
- Status fields should be indexed

### Common Status Patterns

- **Request Status**: `pending`, `approved`, `rejected`, `fulfilled`, `cancelled`
- **Verification Status**: `pending`, `approved`, `needs_review`, `rejected`
- **Inventory Status**: `available`, `reserved`, `issued`, `expired`, `unavailable`
- **Transfer Status**: `pending`, `accepted`, `rejected`, `dispatched`, `in_transit`, `received`, `completed`

### Status Field Implementation

- Use VARCHAR or ENUM for status fields
- Document all valid status values
- Document valid status transitions
- Consider using a lookup table for status values (if complex)

---

## Audit Fields

### Standard Audit Fields

Every table should include the following audit fields where applicable:

- **created_at**: Timestamp when record was created
- **created_by**: User ID who created the record
- **updated_at**: Timestamp when record was last updated
- **updated_by**: User ID who last updated the record
- **deleted_at**: Timestamp when record was soft-deleted (optional)
- **deleted_by**: User ID who soft-deleted the record (optional)

### Audit Field Guidelines

- Use TIMESTAMP or DATETIME for timestamp fields
- Use foreign keys for user references
- Set default values for created_at (CURRENT_TIMESTAMP)
- Update updated_at automatically (ON UPDATE CURRENT_TIMESTAMP)
- Consider soft deletes instead of hard deletes for important data

### Soft Delete Strategy

- Add `deleted_at` and `deleted_by` fields
- Set `deleted_at` to NULL for active records
- Set `deleted_at` to timestamp for deleted records
- Filter out soft-deleted records in queries (WHERE deleted_at IS NULL)
- Hard delete only for non-sensitive data or after retention period

---

## Entity Ownership

### Institution Ownership

Entities that belong to the institution:

- `Institution`: Represents the institution itself
- `Role`: Institution-wide roles
- `Permission`: Institution-wide permissions
- `RolePermission`: Institution-wide role-permission mappings

### Hospital Ownership

Entities that belong to a specific hospital:

- `Hospital`: Belongs to institution
- `User`: Assigned to a hospital (except Central Admin)
- `BloodUnit`: Belongs to a hospital
- `PlateletProduct`: Belongs to a hospital
- `BloodRequest`: Created by a hospital
- `PlateletRequest`: Created by a hospital
- `BloodReservation`: Belongs to a hospital
- `PlateletReservation`: Belongs to a hospital
- `ResourceTransfer`: Involves two hospitals (sending and receiving)

### Donor Ownership

Entities related to donors:

- `Donor`: Belongs to institution (not specific hospital)
- `DonorDocument`: Related to donor
- `DonorVerification`: Related to donor
- `Donation`: Related to donor and hospital
- `Appointment`: Related to donor and hospital
- `DonorResponse`: Related to donor and request

### Cross-Hospital Entities

Entities that involve multiple hospitals:

- `ResourceTransfer`: Involves sending hospital and receiving hospital
- `ResourceTransferItem`: Items in a transfer

---

## Hospital and Institution Relationships

### Institution-Hospital Relationship

```
Institution (1) ----< (N) Hospital
```

- One institution has many hospitals
- Each hospital belongs to one institution
- Hospital table has `institution_id` foreign key

### Hospital-User Relationship

```
Hospital (1) ----< (N) User
```

- One hospital has many users
- Each user belongs to one hospital (except Central Admin)
- User table has `hospital_id` foreign key (nullable for Central Admin)

### Hospital-BloodUnit Relationship

```
Hospital (1) ----< (N) BloodUnit
```

- One hospital has many blood units
- Each blood unit belongs to one hospital
- BloodUnit table has `hospital_id` foreign key

### Hospital-PlateletProduct Relationship

```
Hospital (1) ----< (N) PlateletProduct
```

- One hospital has many platelet products
- Each platelet product belongs to one hospital
- PlateletProduct table has `hospital_id` foreign key

### Resource Transfer Relationships

```
Hospital (1) ----< (N) ResourceTransfer (as sending hospital)
Hospital (1) ----< (N) ResourceTransfer (as receiving hospital)
```

- ResourceTransfer has `sending_hospital_id` foreign key
- ResourceTransfer has `receiving_hospital_id` foreign key

---

## Candidate Domain Entities

The following are candidate domain entities for the HemoNexus database. These will be formally reviewed and implemented in Module 2.

### Institution and Hospital Management

- `Institution`: Institution configuration and management
- `Hospital`: Hospital registration and management

### User and Access Control

- `User`: User accounts
- `Role`: User roles
- `Permission`: Permissions
- `RolePermission`: Role-permission mappings

### Donor Management

- `Donor`: Donor profiles
- `DonorDocument`: Donor documents
- `DonorVerification`: Donor verification records
- `Donation`: Donation records
- `Appointment`: Donation appointments
- `DonorResponse`: Donor responses to mobilization requests

### Blood Inventory

- `BloodUnit`: Individual blood units
- `BloodRequest`: Blood requests
- `BloodRequestItem`: Items in blood requests
- `BloodReservation`: Blood unit reservations
- `BloodUsageRecord`: Blood usage records

### Platelet Inventory

- `PlateletProduct`: Individual platelet products
- `PlateletRequest`: Platelet requests
- `PlateletRequestItem`: Items in platelet requests
- `PlateletReservation`: Platelet product reservations

### Inter-Hospital Coordination

- `ResourceTransfer`: Resource transfers between hospitals
- `ResourceTransferItem`: Items in resource transfers

### Blood Intelligence

- `BloodDemandRecord`: Historical blood demand records
- `Prediction`: ML predictions
- `RiskAssessment`: Shortage-risk assessments

### Notifications

- `Notification`: Notification records
- `NotificationPreference`: User notification preferences

### Audit and Compliance

- `WastageRecord`: Wastage and disposal records
- `AuditLog`: Audit log entries

---

## Rules for Avoiding Redundant Tables

### Inventory Representation

**Rule:** If individual `BloodUnit` records represent physical blood inventory, do not automatically create another redundant physical inventory table without a documented reason.

**Rationale:**
- Individual `BloodUnit` records already represent physical inventory
- Aggregated inventory views can be derived from `BloodUnit` records
- Redundant inventory tables introduce synchronization complexity
- Redundant tables can lead to data inconsistencies

**When to Create Aggregated Tables:**
- Only if performance requirements justify denormalization
- Only if query complexity cannot be managed through views
- Must be documented with performance justification
- Must include data consistency mechanisms

### Platelet Inventory

**Rule:** If individual `PlateletProduct` records represent physical platelet inventory, do not automatically create another redundant physical inventory table.

**Rationale:**
- Individual `PlateletProduct` records already represent physical inventory
- Aggregated inventory views can be derived from `PlateletProduct` records
- Same principles as blood inventory apply

### Demand Records

**Rule:** If `BloodDemandRecord` captures historical demand, do not create separate summary tables without documented justification.

**Rationale:**
- Historical records can be aggregated for analysis
- Summary tables introduce redundancy
- ML models can work with raw historical data

### Transfer Tracking

**Rule:** If `ResourceTransfer` and `ResourceTransferItem` capture transfer details, do not create separate transfer summary tables without justification.

**Rationale:**
- Transfer details are already captured
- Summary views can be derived
- Redundant tables introduce complexity

---

## Data Type Guidelines

### Common Data Types

- **Integer IDs**: `BIGINT` or `INT` (auto-increment)
- **Foreign Keys**: Same type as referenced primary key
- **Strings**: `VARCHAR` with appropriate length
- **Text**: `TEXT` for long strings
- **Dates**: `DATE` for dates without time
- **Timestamps**: `TIMESTAMP` or `DATETIME` for dates with time
- **Decimals**: `DECIMAL` for monetary values
- **Booleans**: `BOOLEAN` or `TINYINT(1)`

### String Lengths

- Use appropriate VARCHAR lengths:
  - Short identifiers: `VARCHAR(50)`
  - Names: `VARCHAR(100)`
  - Descriptions: `VARCHAR(255)` or `TEXT`
  - Long text: `TEXT`

### Decimal Precision

- Use DECIMAL for monetary values:
  - Amounts: `DECIMAL(10, 2)` (10 digits, 2 decimal places)
  - Percentages: `DECIMAL(5, 2)` (5 digits, 2 decimal places)

---

## Naming Conventions

### Table Names

- Use plural form (e.g., `users`, `hospitals`)
- Use snake_case (e.g., `blood_units`, `platelet_products`)
- Be descriptive and meaningful

### Column Names

- Use snake_case (e.g., `created_at`, `hospital_id`)
- Be descriptive and meaningful
- Use consistent naming across tables

### Foreign Key Names

- Use singular form of referenced table name + `_id`
- Example: `hospital_id` references `hospital.id`

### Index Names

- Use descriptive names:
  - `idx_table_name_column_name` for single-column indexes
  - `idx_table_name_column1_column2` for composite indexes
  - `uk_table_name_column_name` for unique indexes

### Constraint Names

- Use descriptive names:
  - `fk_table_name_column_name` for foreign keys
  - `uk_table_name_column_name` for unique constraints
  - `chk_table_name_column_name` for check constraints

---

## Database Migration Strategy

### Migration Tool

- Use Flyway or Liquibase for database migrations
- Version control all migration scripts
- Apply migrations in order
- Never modify existing migration scripts (create new ones)

### Migration Scripts

- Each migration script should be:
  - Idempotent (can be run multiple times safely)
  - Reversible (include rollback script)
  - Independent (does not depend on other migrations)
  - Tested in development before production

### Migration Naming

- Use descriptive names with version numbers:
  - Flyway: `V1__create_users_table.sql`
  - Liquibase: `001-create-users-table.xml`

---

## Data Consistency

### Transaction Management

- Use transactions for multi-step operations
- Ensure ACID properties:
  - Atomicity: All or nothing
  - Consistency: Data remains consistent
  - Isolation: Transactions don't interfere
  - Durability: Committed changes persist

### Optimistic vs. Pessimistic Locking

- **Optimistic Locking**: Use version column for concurrent updates
- **Pessimistic Locking**: Use SELECT FOR UPDATE for critical operations

### Data Validation

- Validate data at application level
- Validate data at database level (constraints)
- Validate data at API level (DTO validation)

---

## Security Considerations

### Sensitive Data

- Identify sensitive data fields (e.g., donor medical information)
- Consider encryption for sensitive data (future consideration)
- Restrict access to sensitive data through RBAC
- Audit access to sensitive data

### SQL Injection Prevention

- Use parameterized queries (prepared statements)
- Never concatenate user input into SQL queries
- Use ORM (JPA/Hibernate) which handles parameterization

### Least Privilege Database Access

- Application database user should have minimum required privileges
- Separate read-only and read-write users if needed
- No direct database access for end users

---

## Performance Considerations

### Query Optimization

- Use EXPLAIN to analyze query performance
- Add indexes based on query patterns
- Avoid SELECT * (specify required columns)
- Use appropriate JOIN types

### Partitioning (Future Consideration)

- Consider table partitioning for large tables
- Partition by date range for time-series data
- Partition by hospital for hospital-isolated data

### Caching (Future Consideration)

- Consider application-level caching (Redis)
- Consider database query cache
- Cache frequently accessed data

---

## Backup and Recovery

### Backup Strategy

- Regular database backups
- Incremental backups for large databases
- Off-site backup storage
- Backup encryption

### Recovery Strategy

- Document recovery procedures
- Test recovery procedures regularly
- Point-in-time recovery capability
- Disaster recovery plan

---

## Important Notes

### Module 2 Implementation

- The complete database design will be formally reviewed in Module 2
- SQL tables will be created in Module 2
- JPA entity classes will be created in Module 2
- Repository interfaces will be created in Module 2
- Migration scripts will be created in Module 2

### Current Scope

- This document provides design principles and candidate entities
- No SQL tables are created in Module 0
- No entity classes are created in Module 0
- No migration scripts are created in Module 0

### Future Refinement

- Database design may be refined during Module 2
- Additional entities may be identified
- Relationships may be adjusted
- Indexes may be added based on query patterns

---

## Related Documentation

- [Project Specification](PROJECT_SPECIFICATION.md)
- [Architecture](ARCHITECTURE.md)
- [Module Roadmap](MODULE_ROADMAP.md)
- [User Roles and Permissions](USER_ROLES_AND_PERMISSIONS.md)
- [API Design Guidelines](API_DESIGN_GUIDELINES.md)
- [Security and Access Rules](SECURITY_AND_ACCESS_RULES.md)
