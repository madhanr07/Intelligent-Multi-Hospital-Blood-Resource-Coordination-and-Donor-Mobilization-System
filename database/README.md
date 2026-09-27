# Database

## Current Database

**Database Name:** `hemonexus`

**Host:** `localhost`

**Port:** `3306`

## Status

The database is currently empty. No business tables have been created yet.

## Module 1 Foundation

In Module 1, only the database connection foundation is established. The backend Spring Boot application is configured to connect to this database using environment variables.

## Future Implementation

The complete database schema will be implemented in **Module 2 — Complete Database & Data Model**.

Module 2 will include:
- Complete database schema design
- All business tables (users, roles, permissions, hospitals, donors, blood units, platelet products, etc.)
- JPA entity classes
- Repository interfaces
- Database migration scripts

## Connection Configuration

Database connection is configured through environment variables:
- `DB_HOST` (default: localhost)
- `DB_PORT` (default: 3306)
- `DB_NAME` (default: hemonexus)
- `DB_USERNAME` (default: root)
- `DB_PASSWORD` (must be provided via environment variable)

See `.env.example` for the expected environment variable format.
