USE [okr-be];
GO

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Role' AND schema_id = SCHEMA_ID('dbo'))
BEGIN
    CREATE TABLE dbo.MRole (
        RoleName NVARCHAR(50) PRIMARY KEY,
        Description NVARCHAR(255) NULL
    );
END
GO

INSERT INTO dbo.Role ([role_name], [description])
VALUES
    ('SYSTEM_ADMIN', 'Full system access: manage all workspaces, users, permissions, and system configurations'),
    ('WORKSPACE_ADMIN', 'Workspace administrator: create and manage workspaces, members, OKR cycles, and workspace-level permissions'),
    ('TEAM_LEADER', 'Team leader: manage the team, create team OKRs, assign tasks, and monitor member progress'),
    ('TEAM_MEMBER', 'Team member: update personal OKRs, perform check-ins, and carry out assigned tasks'),
    ('WORKSPACE_MEMBER', 'Workspace member without team assignment: can view workspace OKRs and their own personal OKRs');
GO