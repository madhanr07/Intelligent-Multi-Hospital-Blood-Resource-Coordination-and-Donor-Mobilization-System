# HemoNexus - API Design Guidelines

## Overview

This document describes the API design guidelines for HemoNexus, including REST principles, endpoint naming conventions, HTTP methods, status codes, DTO principles, validation, error response principles, pagination, and authorization expectations.

**Development Status:** Module 0 - Project Specification & Development Rules (PLANNED)

**IMPORTANT:** This document provides design guidelines. Do NOT implement actual APIs in Module 0. APIs will be implemented in later modules according to the module roadmap.

---

## REST Principles

### Resource-Oriented Design

APIs should be resource-oriented, following REST principles:

- Resources are identified by URIs
- Resources are manipulated using standard HTTP methods
- Resources have representations (JSON)
- Stateless interactions
- Uniform interface

### Resource Naming

- Use nouns to represent resources
- Use plural form for collections
- Use kebab-case for multi-word resource names
- Be consistent in naming

**Examples:**
- `/api/hospitals` (collection of hospitals)
- `/api/hospitals/{id}` (specific hospital)
- `/api/blood-units` (collection of blood units)
- `/api/blood-requests` (collection of blood requests)

### Resource Hierarchy

Reflect resource relationships in URI hierarchy:

```
/api/hospitals/{hospitalId}/blood-units
/api/hospitals/{hospitalId}/blood-requests
/api/blood-requests/{requestId}/items
```

---

## Endpoint Naming Conventions

### Base URL

All API endpoints should be prefixed with `/api`:

```
https://hemonexus.example.com/api/...
```

### Versioning

Include API version in the base URL:

```
/api/v1/hospitals
/api/v1/blood-units
```

### Resource Endpoints

Use the following patterns:

| Pattern | Description | Example |
|---------|-------------|---------|
| `/api/v1/{resource}` | List all resources | `/api/v1/hospitals` |
| `/api/v1/{resource}/{id}` | Get specific resource | `/api/v1/hospitals/123` |
| `/api/v1/{resource}` | Create new resource (POST) | `/api/v1/hospitals` |
| `/api/v1/{resource}/{id}` | Update resource (PUT/PATCH) | `/api/v1/hospitals/123` |
| `/api/v1/{resource}/{id}` | Delete resource (DELETE) | `/api/v1/hospitals/123` |

### Nested Resource Endpoints

Use the following patterns for nested resources:

| Pattern | Description | Example |
|---------|-------------|---------|
| `/api/v1/{parent}/{parentId}/{child}` | List child resources | `/api/v1/hospitals/123/blood-units` |
| `/api/v1/{parent}/{parentId}/{child}/{id}` | Get specific child resource | `/api/v1/hospitals/123/blood-units/456` |
| `/api/v1/{parent}/{parentId}/{child}` | Create child resource (POST) | `/api/v1/hospitals/123/blood-units` |

### Action Endpoints

For non-CRUD operations, use verbs as sub-resources:

```
/api/v1/blood-requests/{id}/approve
/api/v1/blood-requests/{id}/reject
/api/v1/donors/{id}/verify
/api/v1/transfers/{id}/dispatch
```

### Search and Filter Endpoints

Use query parameters for search and filtering:

```
/api/v1/blood-units?bloodType=A&rhFactor=positive
/api/v1/donors?status=verified&bloodType=O
```

---

## HTTP Methods

### Standard HTTP Methods

Use standard HTTP methods with their intended semantics:

| Method | Description | Idempotent | Safe |
|--------|-------------|------------|------|
| GET | Retrieve resource | Yes | Yes |
| POST | Create resource | No | No |
| PUT | Replace resource | Yes | No |
| PATCH | Partial update | No | No |
| DELETE | Delete resource | Yes | No |

### GET

- Retrieve resource or collection
- Should not modify server state
- Can be cached
- Should be idempotent

**Examples:**
```
GET /api/v1/hospitals
GET /api/v1/hospitals/123
GET /api/v1/hospitals/123/blood-units
```

### POST

- Create new resource
- Not idempotent
- Returns created resource with location header

**Examples:**
```
POST /api/v1/hospitals
POST /api/v1/blood-requests
POST /api/v1/donors
```

### PUT

- Replace entire resource
- Idempotent
- Returns updated resource

**Examples:**
```
PUT /api/v1/hospitals/123
PUT /api/v1/donors/456
```

### PATCH

- Partial update of resource
- Not idempotent
- Returns updated resource

**Examples:**
```
PATCH /api/v1/hospitals/123
PATCH /api/v1/blood-units/789
```

### DELETE

- Delete resource
- Idempotent
- Returns 204 No Content on success

**Examples:**
```
DELETE /api/v1/hospitals/123
DELETE /api/v1/blood-units/456
```

---

## HTTP Status Codes

### Success Codes

| Code | Description | Usage |
|------|-------------|-------|
| 200 OK | Request succeeded | GET, PUT, PATCH |
| 201 Created | Resource created | POST |
| 204 No Content | Success, no content returned | DELETE |

### Redirection Codes

| Code | Description | Usage |
|------|-------------|-------|
| 304 Not Modified | Resource not modified | Conditional GET |

### Client Error Codes

| Code | Description | Usage |
|------|-------------|-------|
| 400 Bad Request | Invalid request | Validation errors |
| 401 Unauthorized | Authentication required | Missing or invalid token |
| 403 Forbidden | Authorization failed | Insufficient permissions |
| 404 Not Found | Resource not found | Invalid resource ID |
| 409 Conflict | Resource conflict | Duplicate resource |
| 422 Unprocessable Entity | Semantic errors | Business rule violations |
| 429 Too Many Requests | Rate limit exceeded | Rate limiting |

### Server Error Codes

| Code | Description | Usage |
|------|-------------|-------|
| 500 Internal Server Error | Server error | Unexpected errors |
| 503 Service Unavailable | Service unavailable | Maintenance |

### Status Code Usage Guidelines

- Use appropriate status codes for each scenario
- Include error details in response body for 4xx errors
- Log server errors (5xx) for debugging
- Return 404 for non-existent resources
- Return 401 for missing authentication
- Return 403 for insufficient permissions
- Return 409 for duplicate resources
- Return 422 for business rule violations

---

## DTO Principles

### Data Transfer Objects

DTOs (Data Transfer Objects) are used to transfer data between layers:

- Separate from entity classes (JPA entities)
- Contain only data needed for the operation
- No business logic
- Validation annotations
- Can be nested for complex structures

### Request DTOs

DTOs for incoming requests:

- Contain fields for creating or updating resources
- Include validation annotations
- Exclude sensitive fields (e.g., passwords in update DTOs)
- Use appropriate data types

**Example:**
```java
public class CreateHospitalRequest {
    @NotBlank
    private String name;
    
    @NotBlank
    private String address;
    
    @Pattern(regexp = "^[A-Z]{2}\\d{10}$")
    private String licenseNumber;
    
    // Getters and setters
}
```

### Response DTOs

DTOs for outgoing responses:

- Contain fields to be returned to client
- Exclude sensitive fields (e.g., passwords)
- Include computed fields if needed
- Can be nested for related resources

**Example:**
```java
public class HospitalResponse {
    private Long id;
    private String name;
    private String address;
    private String licenseNumber;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and setters
}
```

### DTO Naming Conventions

- Request DTOs: `{Operation}{Resource}Request`
  - `CreateHospitalRequest`
  - `UpdateHospitalRequest`
  - `BloodRequestItemRequest`

- Response DTOs: `{Resource}Response`
  - `HospitalResponse`
  - `BloodUnitResponse`
  - `DonorResponse`

---

## Validation

### Request Validation

Validate all incoming requests:

- Use validation annotations on DTOs
- Validate required fields
- Validate field formats (email, phone, etc.)
- Validate field ranges (dates, numbers)
- Validate business rules

### Validation Annotations

Use standard validation annotations:

```java
@NotNull
@NotBlank
@NotEmpty
@Size(min = 1, max = 100)
@Email
@Pattern(regexp = "...")
@Min(1)
@Max(100)
@Past
@Future
@Positive
@NegativeOrZero
```

### Custom Validation

Create custom validators for complex validation:

```java
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BloodTypeValidator.class)
public @interface ValidBloodType {
    String message() default "Invalid blood type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
```

### Validation Error Response

Return validation errors with field-level details:

```json
{
  "timestamp": "2024-01-01T12:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "errors": [
    {
      "field": "name",
      "message": "Name is required"
    },
    {
      "field": "email",
      "message": "Invalid email format"
    }
  ]
}
```

---

## Error Response Principles

### Consistent Error Response Format

Use consistent error response format across all endpoints:

```json
{
  "timestamp": "2024-01-01T12:00:00Z",
  "status": 404,
  "error": "Not Found",
  "message": "Hospital not found with id: 123",
  "path": "/api/v1/hospitals/123"
}
```

### Error Response Fields

- `timestamp`: ISO 8601 timestamp
- `status`: HTTP status code
- `error`: Error type (e.g., "Not Found", "Bad Request")
- `message`: Human-readable error message
- `path`: Request path (optional)
- `errors`: Field-level validation errors (optional)

### Error Message Guidelines

- Use clear, human-readable messages
- Include relevant details (e.g., resource ID)
- Avoid exposing sensitive information
- Use consistent language
- Support internationalization (future consideration)

### Error Logging

- Log all errors with sufficient context
- Include user ID, role, hospital in logs
- Include request parameters in logs (sanitized)
- Include stack trace for server errors
- Monitor error rates

---

## Pagination

### Pagination Parameters

Use standard pagination parameters:

- `page`: Page number (0-indexed or 1-indexed, document choice)
- `size`: Number of items per page
- `sort`: Sort field and direction (e.g., `name,asc`)

**Example:**
```
GET /api/v1/hospitals?page=0&size=20&sort=name,asc
```

### Pagination Response

Include pagination metadata in response:

```json
{
  "content": [
    // Resource items
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "totalPages": 5,
    "totalElements": 100,
    "first": true,
    "last": false
  }
}
```

### Default Pagination

- Set reasonable default page size (e.g., 20)
- Set maximum page size (e.g., 100)
- Document pagination parameters
- Support sorting on common fields

### Pagination Best Practices

- Use pagination for large collections
- Avoid returning all records without pagination
- Use cursor-based pagination for large datasets (future consideration)
- Support filtering to reduce result set

---

## Filtering and Sorting

### Filtering Parameters

Use query parameters for filtering:

```
GET /api/v1/blood-units?bloodType=A&rhFactor=positive&status=available
```

### Sorting Parameters

Use `sort` parameter for sorting:

```
GET /api/v1/hospitals?sort=name,asc
GET /api/v1/hospitals?sort=name,desc&sort=createdAt,asc
```

### Filtering Best Practices

- Support filtering on common fields
- Use consistent parameter names
- Support multiple filter values (e.g., `status=available,reserved`)
- Document available filters
- Validate filter values

---

## Authorization Expectations

### Authentication Required

All API endpoints (except public endpoints) require authentication:

- Include JWT token in `Authorization` header
- Format: `Authorization: Bearer {token}`
- Validate token on each request
- Extract user information from token

### Role-Based Authorization

Enforce role-based access control:

- Check user role before processing request
- Return 403 Forbidden if insufficient permissions
- Document required roles for each endpoint

### Hospital-Level Authorization

Enforce hospital-level data isolation:

- Filter data by user's hospital
- Prevent access to other hospitals' data
- Central Admin can access all hospitals
- Return 403 Forbidden for cross-hospital access attempts

### Permission-Based Authorization

Enforce permission-based authorization:

- Check specific permissions for operations
- Return 403 Forbidden if permission missing
- Document required permissions for each endpoint

### Authorization Headers

```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### Authorization Error Response

```json
{
  "timestamp": "2024-01-01T12:00:00Z",
  "status": 403,
  "error": "Forbidden",
  "message": "Insufficient permissions to access this resource",
  "path": "/api/v1/hospitals/123"
}
```

---

## Request/Response Format

### Content-Type

- Use `application/json` for request and response bodies
- Document content-type requirements
- Return 415 Unsupported Media Type for invalid content-type

### Request Headers

```
Content-Type: application/json
Authorization: Bearer {token}
```

### Response Headers

```
Content-Type: application/json
X-Request-ID: {uuid}
```

### Date Format

Use ISO 8601 format for dates:

```
2024-01-01T12:00:00Z
2024-01-01T12:00:00+05:30
```

### Null Handling

- Return `null` for missing optional fields
- Use empty arrays `[]` for empty collections
- Document which fields can be null
- Avoid null for required fields

---

## API Versioning Strategy

### Version in URL

Include version in URL path:

```
/api/v1/hospitals
/api/v2/hospitals
```

### Versioning Guidelines

- Start with v1
- Increment major version for breaking changes
- Maintain backward compatibility when possible
- Document version differences
- Deprecate old versions before removal

### Breaking Changes

Breaking changes include:
- Changing request/response structure
- Removing endpoints
- Changing required fields
- Changing authentication mechanism

Non-breaking changes:
- Adding optional fields
- Adding new endpoints
- Adding new query parameters

---

## Rate Limiting

### Rate Limiting Strategy

Implement rate limiting to prevent abuse:

- Limit requests per user per time window
- Limit requests per IP per time window
- Return 429 Too Many Requests when limit exceeded
- Include rate limit headers in response

### Rate Limit Headers

```
X-RateLimit-Limit: 1000
X-RateLimit-Remaining: 999
X-RateLimit-Reset: 1609459200
```

### Rate Limit Response

```json
{
  "timestamp": "2024-01-01T12:00:00Z",
  "status": 429,
  "error": "Too Many Requests",
  "message": "Rate limit exceeded. Try again in 60 seconds."
}
```

---

## CORS Configuration

### CORS Headers

Configure CORS for cross-origin requests:

```
Access-Control-Allow-Origin: {origin}
Access-Control-Allow-Methods: GET, POST, PUT, PATCH, DELETE
Access-Control-Allow-Headers: Content-Type, Authorization
Access-Control-Max-Age: 3600
```

### CORS Guidelines

- Allow specific origins (not `*` for production)
- Allow necessary HTTP methods
- Allow necessary headers
- Handle preflight requests (OPTIONS)
- Document CORS policy

---

## API Documentation

### OpenAPI/Swagger

Use OpenAPI/Swagger for API documentation:

- Document all endpoints
- Document request/response schemas
- Document authentication requirements
- Document error responses
- Provide example requests/responses

### Documentation Guidelines

- Keep documentation up to date
- Include usage examples
- Document all parameters
- Document all possible error codes
- Provide interactive API explorer (Swagger UI)

---

## Security Considerations

### Input Validation

- Validate all input data
- Sanitize input to prevent injection attacks
- Use parameterized queries
- Validate file uploads (size, type)

### Output Encoding

- Encode output to prevent XSS
- Sanitize data before returning
- Avoid returning sensitive information
- Mask sensitive fields (e.g., partial credit card numbers)

### HTTPS Only

- Use HTTPS in production
- Redirect HTTP to HTTPS
- Use valid SSL certificates
- Implement HSTS headers

### Security Headers

Implement security headers:

```
Strict-Transport-Security: max-age=31536000; includeSubDomains
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
X-XSS-Protection: 1; mode=block
```

---

## API Testing

### Unit Testing

- Test each endpoint independently
- Test happy path and error cases
- Mock external dependencies
- Test validation logic

### Integration Testing

- Test API with database
- Test authentication and authorization
- Test error scenarios
- Test rate limiting

### API Contract Testing

- Test API contract (request/response structure)
- Use tools like Postman, Swagger
- Automate contract tests
- Validate against OpenAPI spec

---

## Important Notes

### Module Implementation

- APIs will be implemented in later modules according to the module roadmap
- Module 3: Authentication APIs
- Module 4: Central Admin APIs
- Module 5: Blood Bank Management APIs
- Module 6: Blood Request APIs
- Module 8: Donor Registration APIs
- Module 9: Donor Portal APIs
- Module 10: Donor Mobilization APIs
- Module 11: Platelet Management APIs
- Module 12: Platelet Request APIs
- Module 13: Multi-Hospital Coordination APIs
- Module 14: Reservation and Transfer APIs
- Module 15: Expiry and Wastage APIs
- Module 16: Notification APIs
- Module 17: Dashboard APIs
- Module 18: Report APIs
- Module 19: Audit Log APIs

### Current Scope

- This document provides design guidelines
- No actual APIs are implemented in Module 0
- No controllers are created in Module 0
- No services are created in Module 0

---

## Related Documentation

- [Project Specification](PROJECT_SPECIFICATION.md)
- [Architecture](ARCHITECTURE.md)
- [Module Roadmap](MODULE_ROADMAP.md)
- [User Roles and Permissions](USER_ROLES_AND_PERMISSIONS.md)
- [Security and Access Rules](SECURITY_AND_ACCESS_RULES.md)
- [Development Rules](DEVELOPMENT_RULES.md)
