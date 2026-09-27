# HemoNexus - Development Rules

## Overview

This document describes the development rules for HemoNexus, including module-by-module development process, AI coding rules, code quality expectations, naming conventions, Git branch strategy, commit strategy, testing expectations, no-scope-creep rule, change-control rules, and dependency management principles.

**Development Status:** Module 0 - Project Specification & Development Rules (PLANNED)

---

## Module-by-Module Development Process

### AI Development Methodology

All future modules must follow this process:

#### STEP 1 — ANALYZE
Inspect the existing repository and dependencies:
- Review current codebase state
- Review existing documentation
- Review completed modules
- Identify dependencies on previous modules
- Understand module scope and boundaries

#### STEP 2 — DESIGN
Produce the technical design before implementation when requested:
- Review module specification from MODULE_ROADMAP.md
- Design database schema (if applicable)
- Design API endpoints (if applicable)
- Design frontend components (if applicable)
- Design integration points
- Document design decisions

#### STEP 3 — BACKEND
Implement approved backend scope:
- Create/update database entities (if applicable)
- Create/update repositories (if applicable)
- Create/update services (if applicable)
- Create/update controllers (if applicable)
- Implement business logic
- Implement validation
- Implement error handling

#### STEP 4 — FRONTEND
Implement approved frontend scope:
- Create/update React components
- Implement API service calls
- Implement form validation
- Implement error handling
- Implement loading states
- Implement protected routes
- Implement role-aware navigation

#### STEP 5 — INTEGRATE
Integrate without breaking completed modules:
- Integrate frontend with backend APIs
- Test integration points
- Ensure no breaking changes to previous modules
- Update documentation if needed

#### STEP 6 — TEST
Run appropriate tests:
- Run unit tests
- Run integration tests
- Run manual testing for new features
- Test edge cases
- Test error scenarios

#### STEP 7 — FIX
Fix issues related to the current module:
- Fix bugs identified during testing
- Fix integration issues
- Fix performance issues
- Fix security issues
- Re-test after fixes

#### STEP 8 — REVIEW
Report module completion:
- Files changed
- Features implemented
- Tests performed
- Known issues
- Next-module dependencies

### Module Completion Criteria

A module is considered complete when:

- All approved features are implemented
- All tests pass
- No breaking changes to previous modules
- Documentation is updated (if applicable)
- Known issues are documented
- Next-module dependencies are identified

---

## AI Coding Rules

### Code Generation Rules

- Follow existing code style and patterns
- Follow naming conventions
- Write clean, readable code
- Add appropriate comments
- Don't generate unnecessary code
- Don't generate redundant code
- Don't generate code outside module scope

### Code Quality Standards

- Write modular, reusable code
- Follow SOLID principles
- Use appropriate design patterns
- Avoid code duplication
- Keep functions focused and small
- Keep classes focused and small

### Error Handling

- Handle errors gracefully
- Provide meaningful error messages
- Log errors appropriately
- Don't swallow exceptions
- Don't expose sensitive information in errors

### Code Review Simulation

Before considering code complete:

- Review code for security vulnerabilities
- Review code for performance issues
- Review code for edge cases
- Review code for error handling
- Review code for consistency with existing codebase

### Documentation in Code

- Add Javadoc comments for public methods
- Add inline comments for complex logic
- Document non-obvious decisions
- Keep comments up to date
- Don't comment obvious code

---

## Code Quality Expectations

### Backend Code Quality

#### Java/Spring Boot

- Follow Java coding conventions
- Use meaningful variable and method names
- Use appropriate access modifiers
- Use dependency injection
- Use Spring annotations appropriately
- Implement proper exception handling
- Use validation annotations
- Use DTOs for API requests/responses
- Separate concerns (controllers, services, repositories)

#### Service Layer

- Business logic in service layer
- Transaction management with @Transactional
- Service methods should be focused
- Service methods should be testable
- Avoid business logic in controllers

#### Repository Layer

- Use Spring Data JPA repositories
- Define custom query methods when needed
- Use @Query for complex queries
- Consider N+1 query problem
- Use appropriate fetch strategies

#### Controller Layer

- Controllers should be thin
- Delegate to service layer
- Use appropriate HTTP methods
- Return appropriate HTTP status codes
- Validate requests
- Handle exceptions

### Frontend Code Quality

#### React/JavaScript

- Follow React best practices
- Use functional components with hooks
- Use meaningful component names
- Use appropriate state management
- Avoid prop drilling (use context when needed)
- Use appropriate lifecycle methods or effects
- Handle loading and error states
- Implement form validation

#### Component Design

- Components should be focused and reusable
- Separate presentation from logic
- Use appropriate component composition
- Use props for data flow
- Use callbacks for events
- Keep components small

#### API Integration

- Use service layer for API calls
- Handle loading states
- Handle error states
- Implement retry logic (if needed)
- Cache responses when appropriate

#### Styling

- Use consistent styling approach
- Use responsive design
- Follow accessibility guidelines
- Use semantic HTML
- Use appropriate color contrast

### Database Code Quality

#### Entity Design

- Follow JPA best practices
- Use appropriate relationships
- Use appropriate cascade types
- Implement equals() and hashCode() for entities
- Use appropriate fetch strategies
- Consider lazy vs eager loading

#### Query Design

- Use efficient queries
- Avoid N+1 query problem
- Use indexes appropriately
- Use pagination for large result sets
- Consider query performance

#### Migration Design

- Use incremental migrations
- Make migrations reversible
- Test migrations in development
- Don't modify existing migrations

---

## Naming Conventions

### Backend Naming Conventions

#### Java Classes

- Use PascalCase for class names
- Use descriptive names
- Use nouns for classes
- Use verbs for methods

**Examples:**
```java
HospitalService
BloodUnitController
DonorRepository
```

#### Java Methods

- Use camelCase for method names
- Use verbs for methods
- Be descriptive

**Examples:**
```java
getHospitalById
createBloodRequest
verifyDonor
```

#### Java Variables

- Use camelCase for variable names
- Use descriptive names
- Avoid abbreviations (unless common)

**Examples:**
```java
String hospitalName;
int bloodUnitCount;
```

#### Database Tables

- Use snake_case for table names
- Use plural form
- Be descriptive

**Examples:**
```sql
hospitals
blood_units
donor_verifications
```

#### Database Columns

- Use snake_case for column names
- Use descriptive names
- Use foreign key naming: `{table}_id`

**Examples:**
```sql
hospital_id
created_at
blood_type
```

### Frontend Naming Conventions

#### React Components

- Use PascalCase for component names
- Use descriptive names
- Use nouns for components

**Examples:**
```javascript
HospitalList
BloodRequestForm
DonorDashboard
```

#### React Functions/Hooks

- Use camelCase for function names
- Use descriptive names
- Use verbs for actions

**Examples:**
```javascript
const fetchHospitals = () => { ... }
const handleSubmit = () => { ... }
```

#### React Variables

- Use camelCase for variable names
- Use descriptive names

**Examples:**
```javascript
const hospitalList = [];
const isLoading = false;
```

#### CSS Classes

- Use kebab-case for CSS class names
- Use descriptive names
- Follow BEM methodology (optional)

**Examples:**
```css
.hospital-list
.hospital-list__item
.hospital-list__item--active
```

### File Naming Conventions

#### Backend Files

- Java files: PascalCase, match class name
- Test files: `{ClassName}Test.java`
- Configuration files: descriptive names

**Examples:**
```
HospitalService.java
HospitalServiceTest.java
SecurityConfig.java
```

#### Frontend Files

- Component files: PascalCase, match component name
- Service files: camelCase with `.service` suffix
- Hook files: camelCase with `.hook` suffix

**Examples:**
```
HospitalList.jsx
hospitalService.js
useAuth.js
```

---

## Git Branch Strategy

### Branch Structure

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

- **main must remain stable**: Only merge completed, tested modules to main
- **One branch per implementation module**: Create a new branch for each module
- **Meaningful commits**: Write clear, descriptive commit messages
- **No secrets committed**: Never commit passwords, API keys, or sensitive data
- **No .env committed**: .env files must be in .gitignore
- **No force push**: Avoid force pushing to shared branches
- **No destructive Git operations**: Don't reset or delete branches without approval
- **Do not automatically commit**: Developer reviews changes before commit
- **Do not automatically push**: Developer reviews changes before push

### Branch Creation

Create a new branch for each module:

```bash
git checkout main
git pull origin main
git checkout -b module-{number}-{name}
```

**Example:**
```bash
git checkout -b module-3-auth-rbac
```

### Branch Merging

Merge module branch to main only after completion:

```bash
git checkout main
git merge module-3-auth-rbac
git push origin main
```

### Branch Deletion

Delete module branch after successful merge:

```bash
git branch -d module-3-auth-rbac
git push origin --delete module-3-auth-rbac
```

---

## Commit Strategy

### Commit Message Format

Use conventional commit format:

```
<type>(<scope>): <subject>

<body>

<footer>
```

### Commit Types

- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `style`: Code style changes (formatting, etc.)
- `refactor`: Code refactoring
- `test`: Test changes
- `chore`: Build process or auxiliary tool changes

### Commit Examples

```
feat(auth): implement JWT authentication

Add JWT token generation and validation.
Implement authentication filter.
Add login endpoint.

Closes #123
```

```
fix(blood): resolve inventory count issue

Fix incorrect blood unit count calculation.
Add unit test for inventory count.

Closes #456
```

```
docs(readme): update installation instructions

Add MySQL setup steps.
Update environment variable documentation.
```

### Commit Frequency

- Commit frequently with logical groupings
- Commit after completing a feature or fix
- Commit after resolving an issue
- Don't commit broken code
- Don't commit incomplete features

### Commit Before Push

- Review changes before committing
- Ensure tests pass before committing
- Ensure no secrets are included
- Ensure .env files are not included

---

## Testing Expectations

### Unit Testing

- Write unit tests for business logic
- Write unit tests for services
- Write unit tests for utilities
- Mock external dependencies
- Test happy path and error cases
- Aim for high code coverage

### Integration Testing

- Write integration tests for API endpoints
- Write integration tests for database operations
- Test integration between layers
- Test with actual database (test database)
- Test authentication and authorization

### Frontend Testing

- Write component tests (Jest, React Testing Library)
- Test component rendering
- Test user interactions
- Test form validation
- Test error states

### Manual Testing

- Perform manual testing for new features
- Test user workflows
- Test edge cases
- Test error scenarios
- Test with different user roles

### Test Coverage

- Aim for high test coverage
- Focus on critical paths
- Focus on business logic
- Don't test trivial code
- Don't test third-party libraries

### Test Data

- Use test data fixtures
- Use test data factories
- Clean up test data after tests
- Use transactions for database tests
- Rollback transactions after tests

---

## No-Scope-Creep Rule

### Scope Definition

- Each module has a clearly defined scope (see MODULE_ROADMAP.md)
- Scope is defined before implementation begins
- Scope must be approved before implementation

### Scope Creep Prevention

- Do not add features outside module scope
- Do not add features not in project specification
- Do not add features not in module roadmap
- If new feature is needed, document for future module
- If scope change is necessary, get approval first

### Scope Change Process

1. Identify need for scope change
2. Document scope change request
3. Get approval from developer
4. Update module documentation
5. Implement approved scope change

### Scope Boundaries

- Module 0: Documentation only (no code)
- Module 1: Project foundation (no business logic)
- Module 2: Database only (no business logic)
- Module 3: Authentication only (no other features)
- Each subsequent module: Only features defined in module roadmap

---

## Change-Control Rules

### Change Types

- **Feature Addition**: Adding new functionality
- **Bug Fix**: Fixing a bug
- **Refactoring**: Improving code structure without changing behavior
- **Configuration Change**: Changing configuration
- **Documentation Change**: Updating documentation

### Change Approval

- Feature additions must be in module scope
- Bug fixes can be made without approval if critical
- Refactoring must not change behavior
- Configuration changes must be documented
- Documentation changes should be kept in sync

### Change Impact Analysis

Before making changes:

- Identify affected components
- Identify breaking changes
- Identify dependencies
- Identify test impact
- Identify documentation impact

### Change Documentation

- Document significant changes
- Update relevant documentation
- Update API documentation if APIs change
- Update database documentation if schema changes
- Update README if deployment changes

### Change Testing

- Test changes thoroughly
- Run regression tests
- Test integration points
- Test with different user roles
- Test edge cases

---

## Dependency Management Principles

### Backend Dependencies (Maven)

- Use stable, well-maintained dependencies
- Keep dependencies up to date
- Document dependency versions in pom.xml
- Review dependency updates for breaking changes
- Test dependency updates before deployment

### Frontend Dependencies (npm)

- Use stable, well-maintained dependencies
- Keep dependencies up to date
- Document dependency versions in package.json
- Review dependency updates for breaking changes
- Test dependency updates before deployment
- Use package-lock.json for consistent builds

### ML Service Dependencies (Python)

- Use stable, well-maintained dependencies
- Keep dependencies up to date
- Document dependency versions in requirements.txt
- Review dependency updates for breaking changes
- Test dependency updates before deployment
- Use virtual environment for isolation

### Dependency Security

- Review dependencies for security vulnerabilities
- Use dependency scanning tools
- Update vulnerable dependencies promptly
- Monitor security advisories
- Document security updates

### Dependency Licensing

- Review dependency licenses
- Ensure compatibility with project license
- Document license information
- Avoid dependencies with restrictive licenses

---

## Code Review Guidelines

### Self-Review Checklist

Before considering code complete:

- [ ] Code follows naming conventions
- [ ] Code follows existing patterns
- [ ] Code is readable and maintainable
- [ ] Code has appropriate comments
- [ ] Error handling is implemented
- [ ] Security best practices are followed
- [ ] Performance is considered
- [ ] Tests are written
- [ ] Tests pass
- [ ] Documentation is updated

### Review Criteria

- Correctness: Does the code work as intended?
- Clarity: Is the code easy to understand?
- Maintainability: Is the code easy to maintain?
- Performance: Is the code performant?
- Security: Is the code secure?
- Testing: Are tests adequate?
- Documentation: Is documentation adequate?

---

## Documentation Rules

### Code Documentation

- Add Javadoc comments for public APIs
- Add inline comments for complex logic
- Keep comments up to date
- Don't comment obvious code
- Document non-obvious decisions

### API Documentation

- Document all API endpoints
- Document request/response formats
- Document authentication requirements
- Document error responses
- Provide examples

### README Updates

- Update README for significant changes
- Update installation instructions if needed
- Update configuration instructions if needed
- Update deployment instructions if needed

### Module Documentation

- Update module documentation after completion
- Document known issues
- Document next-module dependencies
- Document any deviations from plan

---

## Debugging Guidelines

### Debugging Process

1. Reproduce the issue
2. Identify the root cause
3. Fix the root cause (not symptoms)
4. Add tests to prevent regression
5. Verify the fix
6. Document the fix

### Logging

- Use appropriate log levels (DEBUG, INFO, WARN, ERROR)
- Log meaningful information
- Don't log sensitive information
- Log errors with context
- Use structured logging (future consideration)

### Error Messages

- Provide meaningful error messages
- Include relevant context in error messages
- Don't expose sensitive information
- Use consistent error message format
- Localize error messages (future consideration)

---

## Performance Guidelines

### Backend Performance

- Use database indexes appropriately
- Optimize queries
- Use pagination for large result sets
- Cache frequently accessed data (future consideration)
- Use lazy loading appropriately
- Avoid N+1 query problem

### Frontend Performance

- Use code splitting (future consideration)
- Lazy load components (future consideration)
- Optimize images
- Use appropriate caching
- Minimize bundle size
- Use virtual scrolling for long lists (future consideration)

### Database Performance

- Use appropriate indexes
- Optimize queries
- Use connection pooling
- Use appropriate data types
- Partition large tables (future consideration)

---

## Security Guidelines

### Code Security

- Validate all input
- Sanitize output
- Use parameterized queries
- Don't hardcode secrets
- Use secure algorithms
- Follow security best practices

### Dependency Security

- Review dependencies for vulnerabilities
- Update vulnerable dependencies
- Use dependency scanning tools
- Monitor security advisories

### Data Security

- Encrypt sensitive data at rest (future consideration)
- Encrypt data in transit (HTTPS)
- Implement access control
- Audit data access
- Follow least privilege principle

---

## Deployment Guidelines

### Pre-Deployment Checklist

- [ ] All tests pass
- [ ] No known critical bugs
- [ ] Documentation is updated
- [ ] Configuration is set
- [ ] Environment variables are set
- [ ] Database migrations are tested
- [ ] Backup is created
- [ ] Rollback plan is prepared

### Deployment Process

1. Create deployment branch
2. Run final tests
3. Create backup
4. Deploy to staging
5. Test in staging
6. Deploy to production
7. Verify production
8. Monitor for issues

### Post-Deployment

- Monitor application health
- Monitor error logs
- Monitor performance
- Address issues promptly
- Document deployment

---

## Important Notes

### Module 0 Scope

- Module 0 is documentation and architecture specification only
- No application code is created in Module 0
- No database schema is created in Module 0
- No APIs are implemented in Module 0
- No authentication is implemented in Module 0
- No ML implementation in Module 0

### Future Modules

- Each module will follow this development process
- Each module will be developed on a dedicated branch
- Each module will be tested before merging to main
- Each module will be documented before completion

### Continuous Improvement

- Development rules may be refined based on experience
- Best practices may be updated
- New tools may be adopted
- Process improvements may be implemented

---

## Related Documentation

- [Project Specification](PROJECT_SPECIFICATION.md)
- [Architecture](ARCHITECTURE.md)
- [Module Roadmap](MODULE_ROADMAP.md)
- [User Roles and Permissions](USER_ROLES_AND_PERMISSIONS.md)
- [Core Workflows](CORE_WORKFLOWS.md)
- [Database Design Principles](DATABASE_DESIGN_PRINCIPLES.md)
- [API Design Guidelines](API_DESIGN_GUIDELINES.md)
- [Security and Access Rules](SECURITY_AND_ACCESS_RULES.md)
