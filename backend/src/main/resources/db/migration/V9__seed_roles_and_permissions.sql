-- V9__seed_roles_and_permissions.sql
-- Seed roles, permissions, and role-permission assignments for Module 3 Authentication & Authorization

-- Insert Roles
INSERT INTO role (name, description, status) VALUES
('CENTRAL_ADMIN', 'Institution-level administration and oversight', 'ACTIVE'),
('BLOOD_BANK_STAFF', 'Blood inventory, intelligence, and donor operations', 'ACTIVE'),
('PLATELET_STAFF', 'Platelet lifecycle and operations', 'ACTIVE'),
('DONOR', 'Donor portal access', 'ACTIVE');

-- Insert Permissions (74 total)

-- Institution Management (6)
INSERT INTO permission (name, description, resource, action) VALUES
('institution:read:institution', 'Read institution information', 'institution', 'read'),
('institution:update:institution', 'Update institution information', 'institution', 'update'),
('hospital:read:institution', 'Read hospital information at institution level', 'hospital', 'read'),
('hospital:create:institution', 'Create hospitals at institution level', 'hospital', 'create'),
('hospital:update:institution', 'Update hospital information at institution level', 'hospital', 'update'),
('hospital:delete:institution', 'Delete hospitals at institution level', 'hospital', 'delete');

-- User Management (6)
INSERT INTO permission (name, description, resource, action) VALUES
('user:read:institution', 'Read user information at institution level', 'user', 'read'),
('user:create:institution', 'Create users at institution level', 'user', 'create'),
('user:update:institution', 'Update user information at institution level', 'user', 'update'),
('user:delete:institution', 'Delete users at institution level', 'user', 'delete'),
('user:assign-role:institution', 'Assign roles to users at institution level', 'user', 'assign-role'),
('user:assign-hospital:institution', 'Assign hospitals to users at institution level', 'user', 'assign-hospital');

-- Role Management (6)
INSERT INTO permission (name, description, resource, action) VALUES
('role:read:institution', 'Read role information at institution level', 'role', 'read'),
('role:create:institution', 'Create roles at institution level', 'role', 'create'),
('role:update:institution', 'Update role information at institution level', 'role', 'update'),
('role:delete:institution', 'Delete roles at institution level', 'role', 'delete'),
('permission:read:institution', 'Read permission information at institution level', 'permission', 'read'),
('permission:assign:institution', 'Assign permissions to roles at institution level', 'permission', 'assign');

-- Blood Inventory (4)
INSERT INTO permission (name, description, resource, action) VALUES
('blood-unit:read:hospital', 'Read blood unit information at hospital level', 'blood-unit', 'read'),
('blood-unit:create:hospital', 'Create blood units at hospital level', 'blood-unit', 'create'),
('blood-unit:update:hospital', 'Update blood unit information at hospital level', 'blood-unit', 'update'),
('blood-unit:delete:hospital', 'Delete blood units at hospital level', 'blood-unit', 'delete');

-- Blood Requests (5)
INSERT INTO permission (name, description, resource, action) VALUES
('blood-request:read:hospital', 'Read blood request information at hospital level', 'blood-request', 'read'),
('blood-request:create:hospital', 'Create blood requests at hospital level', 'blood-request', 'create'),
('blood-request:update:hospital', 'Update blood request information at hospital level', 'blood-request', 'update'),
('blood-request:approve:hospital', 'Approve blood requests at hospital level', 'blood-request', 'approve'),
('blood-request:reject:hospital', 'Reject blood requests at hospital level', 'blood-request', 'reject');

-- Blood Intelligence (3)
INSERT INTO permission (name, description, resource, action) VALUES
('blood-intelligence:read:hospital', 'Read blood intelligence information at hospital level', 'blood-intelligence', 'read'),
('blood-intelligence:view-forecast:hospital', 'View blood demand forecasts at hospital level', 'blood-intelligence', 'view-forecast'),
('blood-intelligence:view-risk:hospital', 'View shortage risk information at hospital level', 'blood-intelligence', 'view-risk');

-- Donor Management (6)
INSERT INTO permission (name, description, resource, action) VALUES
('donor:read:hospital', 'Read donor information at hospital level', 'donor', 'read'),
('donor:read:institution', 'Read donor information at institution level', 'donor', 'read'),
('donor:create:hospital', 'Create donor records at hospital level', 'donor', 'create'),
('donor:update:hospital', 'Update donor information at hospital level', 'donor', 'update'),
('donor:verify:hospital', 'Verify donor information at hospital level', 'donor', 'verify'),
('donor:approve:institution', 'Approve donor records at institution level', 'donor', 'approve');

-- Donor Portal (3)
INSERT INTO permission (name, description, resource, action) VALUES
('donor-portal:read:personal', 'Read personal donor portal information', 'donor-portal', 'read'),
('donor-portal:update:personal', 'Update personal donor portal information', 'donor-portal', 'update'),
('donor-portal:respond:personal', 'Respond to requests in donor portal', 'donor-portal', 'respond');

-- Platelet Inventory (4)
INSERT INTO permission (name, description, resource, action) VALUES
('platelet-product:read:hospital', 'Read platelet product information at hospital level', 'platelet-product', 'read'),
('platelet-product:create:hospital', 'Create platelet products at hospital level', 'platelet-product', 'create'),
('platelet-product:update:hospital', 'Update platelet product information at hospital level', 'platelet-product', 'update'),
('platelet-product:delete:hospital', 'Delete platelet products at hospital level', 'platelet-product', 'delete');

-- Platelet Requests (5)
INSERT INTO permission (name, description, resource, action) VALUES
('platelet-request:read:hospital', 'Read platelet request information at hospital level', 'platelet-request', 'read'),
('platelet-request:create:hospital', 'Create platelet requests at hospital level', 'platelet-request', 'create'),
('platelet-request:update:hospital', 'Update platelet request information at hospital level', 'platelet-request', 'update'),
('platelet-request:approve:hospital', 'Approve platelet requests at hospital level', 'platelet-request', 'approve'),
('platelet-request:reject:hospital', 'Reject platelet requests at hospital level', 'platelet-request', 'reject');

-- Inter-Hospital Coordination (4)
INSERT INTO permission (name, description, resource, action) VALUES
('coordination:read:institution', 'Read coordination information at institution level', 'coordination', 'read'),
('coordination:create:hospital', 'Create coordination requests at hospital level', 'coordination', 'create'),
('coordination:respond:hospital', 'Respond to coordination requests at hospital level', 'coordination', 'respond'),
('coordination:approve:institution', 'Approve coordination requests at institution level', 'coordination', 'approve');

-- Transfers (4)
INSERT INTO permission (name, description, resource, action) VALUES
('transfer:read:hospital', 'Read transfer information at hospital level', 'transfer', 'read'),
('transfer:create:hospital', 'Create transfers at hospital level', 'transfer', 'create'),
('transfer:dispatch:hospital', 'Dispatch transfers at hospital level', 'transfer', 'dispatch'),
('transfer:receive:hospital', 'Receive transfers at hospital level', 'transfer', 'receive');

-- Expiry (1)
INSERT INTO permission (name, description, resource, action) VALUES
('expiry:read:hospital', 'Read expiry information at hospital level', 'expiry', 'read');

-- Wastage (3)
INSERT INTO permission (name, description, resource, action) VALUES
('wastage:read:hospital', 'Read wastage information at hospital level', 'wastage', 'read'),
('wastage:create:hospital', 'Create wastage records at hospital level', 'wastage', 'create'),
('wastage:authorize:hospital', 'Authorize wastage at hospital level', 'wastage', 'authorize');

-- Notifications (3)
INSERT INTO permission (name, description, resource, action) VALUES
('notification:configure:institution', 'Configure notification settings at institution level', 'notification', 'configure'),
('notification:send:hospital', 'Send notifications at hospital level', 'notification', 'send'),
('notification:receive:personal', 'Receive personal notifications', 'notification', 'receive');

-- Dashboards (3)
INSERT INTO permission (name, description, resource, action) VALUES
('dashboard:read:institution', 'Read dashboard information at institution level', 'dashboard', 'read'),
('dashboard:read:hospital', 'Read dashboard information at hospital level', 'dashboard', 'read'),
('dashboard:read:personal', 'Read personal dashboard information', 'dashboard', 'read');

-- Reports (5)
INSERT INTO permission (name, description, resource, action) VALUES
('report:read:institution', 'Read reports at institution level', 'report', 'read'),
('report:read:hospital', 'Read reports at hospital level', 'report', 'read'),
('report:read:personal', 'Read personal reports', 'report', 'read'),
('report:generate:institution', 'Generate reports at institution level', 'report', 'generate'),
('report:generate:hospital', 'Generate reports at hospital level', 'report', 'generate');

-- Audit Logs (3)
INSERT INTO permission (name, description, resource, action) VALUES
('audit-log:read:institution', 'Read audit logs at institution level', 'audit-log', 'read'),
('audit-log:read:hospital', 'Read audit logs at hospital level', 'audit-log', 'read'),
('audit-log:export:institution', 'Export audit logs at institution level', 'audit-log', 'export');

-- Insert Role-Permission Assignments (89 total)

-- CENTRAL_ADMIN (28 permissions)
INSERT INTO role_permission (role_id, permission_id) VALUES
-- Institution Management (6)
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6),
-- User Management (6)
(1, 7), (1, 8), (1, 9), (1, 10), (1, 11), (1, 12),
-- Role Management (6)
(1, 13), (1, 14), (1, 15), (1, 16), (1, 17), (1, 18),
-- Donor Management (2)
(1, 32), (1, 36),
-- Coordination (2)
(1, 49), (1, 52),
-- Notifications (1)
(1, 61),
-- Dashboards (1)
(1, 64),
-- Reports (2)
(1, 67), (1, 70),
-- Audit Logs (2)
(1, 72), (1, 74);

-- BLOOD_BANK_STAFF (31 permissions)
INSERT INTO role_permission (role_id, permission_id) VALUES
-- Blood Inventory (4)
(2, 19), (2, 20), (2, 21), (2, 22),
-- Blood Requests (5)
(2, 23), (2, 24), (2, 25), (2, 26), (2, 27),
-- Blood Intelligence (3)
(2, 28), (2, 29), (2, 30),
-- Donor Management (4)
(2, 31), (2, 33), (2, 34), (2, 35),
-- Coordination (2)
(2, 50), (2, 51),
-- Transfers (4)
(2, 53), (2, 54), (2, 55), (2, 56),
-- Expiry (1)
(2, 57),
-- Wastage (3)
(2, 58), (2, 59), (2, 60),
-- Notifications (1)
(2, 62),
-- Dashboards (1)
(2, 65),
-- Reports (2)
(2, 68), (2, 71),
-- Audit Logs (1)
(2, 73);

-- PLATELET_STAFF (24 permissions)
INSERT INTO role_permission (role_id, permission_id) VALUES
-- Platelet Inventory (4)
(3, 40), (3, 41), (3, 42), (3, 43),
-- Platelet Requests (5)
(3, 44), (3, 45), (3, 46), (3, 47), (3, 48),
-- Coordination (2)
(3, 50), (3, 51),
-- Transfers (4)
(3, 53), (3, 54), (3, 55), (3, 56),
-- Expiry (1)
(3, 57),
-- Wastage (3)
(3, 58), (3, 59), (3, 60),
-- Notifications (1)
(3, 62),
-- Dashboards (1)
(3, 65),
-- Reports (2)
(3, 68), (3, 71),
-- Audit Logs (1)
(3, 73);

-- DONOR (6 permissions)
INSERT INTO role_permission (role_id, permission_id) VALUES
-- Donor Portal (3)
(4, 37), (4, 38), (4, 39),
-- Notifications (1)
(4, 63),
-- Dashboards (1)
(4, 66),
-- Reports (1)
(4, 69);
