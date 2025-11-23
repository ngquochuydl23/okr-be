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
    ('SUPER_ADMIN', 'Full system access'),
    ('WORKSPACE_ADMIN', 'Admin of workspace, can manage members'),
    ('WORKSPACE_MEMBER', 'Regular workspace member with limited permissions');
GO