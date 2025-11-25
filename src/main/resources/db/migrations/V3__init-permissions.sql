USE [okr-be];
GO

INSERT INTO dbo.Permission (name, description) VALUES
('SYSTEM_MANAGE_USERS', 'Manage all system users'),
('SYSTEM_MANAGE_WORKSPACES', 'Manage all workspaces'),
('SYSTEM_MANAGE_ROLES', 'Manage system roles'),
('SYSTEM_VIEW_AUDIT_LOGS', 'View full system audit'),

-- WORKSPACE MGMT
('WORKSPACE_VIEW', 'View workspace'),
('WORKSPACE_UPDATE', 'Update workspace info'),
('WORKSPACE_DELETE', 'Delete workspace'),
('WORKSPACE_INVITE_MEMBER', 'Invite members to workspace'),
('WORKSPACE_REMOVE_MEMBER', 'Remove members from workspace'),
('WORKSPACE_MANAGE_ROLES', 'Assign roles inside workspace'),

-- OBJECTIVE
('OBJECTIVE_CREATE', 'Create objectives'),
('OBJECTIVE_UPDATE', 'Update objectives'),
('OBJECTIVE_DELETE', 'Delete objectives'),
('OBJECTIVE_VIEW', 'View objectives'),

-- KEY RESULT
('KR_CREATE', 'Create key results'),
('KR_UPDATE', 'Update key results'),
('KR_DELETE', 'Delete key results'),
('KR_VIEW', 'View key results'),

-- CHECKIN
('CHECKIN_CREATE', 'Create check-ins'),
('CHECKIN_UPDATE', 'Update check-ins'),
('CHECKIN_DELETE', 'Delete check-ins'),
('CHECKIN_VIEW', 'View check-ins');
GO