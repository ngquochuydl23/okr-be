-- Đặt các tùy chọn phiên SQL Server chuẩn (nên có ở đầu script)
SET QUOTED_IDENTIFIER ON;
GO
SET ANSI_NULLS ON;
GO

--------------------------------------------------------------------------------
-- DROP TABLES (Đã có sẵn, giữ nguyên)
--------------------------------------------------------------------------------

DROP TABLE IF EXISTS [okr-be].dbo.permission;
GO
CREATE TABLE [okr-be].dbo.permission (
    description varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    name varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    CONSTRAINT PK_Permission PRIMARY KEY (name)
);
GO

DROP TABLE IF EXISTS [okr-be].dbo.[role];
GO
CREATE TABLE [okr-be].dbo.[role] (
    description varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    role_name varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    CONSTRAINT PK_Role PRIMARY KEY (role_name)
);
GO

DROP TABLE IF EXISTS [okr-be].dbo.role_permission;
GO
CREATE TABLE [okr-be].dbo.role_permission (
    permission_id varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    role_id varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    CONSTRAINT PK_RolePermission PRIMARY KEY (permission_id,role_id)
); -- Thêm dấu chấm phẩy sau CREATE TABLE
GO

DROP TABLE IF EXISTS  [okr-be].dbo.check_in;
GO
CREATE TABLE [okr-be].dbo.check_in (
    is_deleted bit NOT NULL,
    created_at datetimeoffset(6) NULL,
    last_updated_at datetimeoffset(6) NULL,
    id uniqueidentifier NOT NULL,
    objective_id uniqueidentifier NULL,
    reporter_id uniqueidentifier NULL,
    CONSTRAINT PK_CheckIn PRIMARY KEY (id)
);
GO

DROP TABLE IF EXISTS  [okr-be].dbo.check_in_detail;
GO
CREATE TABLE [okr-be].dbo.check_in_detail (
    [change] float NULL,
    confidence_level_enum smallint NULL,
    is_deleted bit NOT NULL,
    obtained_value float NULL,
    progress float NULL,
    created_at datetimeoffset(6) NULL,
    last_updated_at datetimeoffset(6) NULL,
    check_in_id uniqueidentifier NULL,
    id uniqueidentifier NOT NULL,
    key_result_id uniqueidentifier NULL,
    CONSTRAINT PK_CheckInDetail PRIMARY KEY (id)
);
GO
ALTER TABLE [okr-be].dbo.check_in_detail WITH NOCHECK ADD CONSTRAINT CK_CheckInDetail_ConfidenceLevel CHECK (([confidence_level_enum]>=(0) AND [confidence_level_enum]<=(2)));
GO -- Thêm GO sau ALTER TABLE

DROP TABLE IF EXISTS  [okr-be].dbo.[cycle];
GO
CREATE TABLE [okr-be].dbo.[cycle] (
    is_deleted bit NOT NULL,
    created_at datetimeoffset(6) NULL,
    last_updated_at datetimeoffset(6) NULL,
    id uniqueidentifier NOT NULL,
    workspace_id uniqueidentifier NULL,
    description nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    name nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    CONSTRAINT PK_Cycle PRIMARY KEY (id)
);
GO

DROP TABLE IF EXISTS [okr-be].dbo.key_result;
GO
CREATE TABLE [okr-be].dbo.key_result (
    [change] float NULL,
    is_decimal bit NULL,
    is_deleted bit NOT NULL,
    key_result_order int NULL,
    obtained_value float NOT NULL,
    progress float NULL,
    start_value float NULL,
    target float NOT NULL,
    target_value float NULL,
    created_at datetimeoffset(6) NULL,
    last_updated_at datetimeoffset(6) NULL,
    id uniqueidentifier NOT NULL,
    measure_unit_id uniqueidentifier NULL,
    objective_id uniqueidentifier NULL,
    name nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    CONSTRAINT PK_KeyResult PRIMARY KEY (id)
);
GO -- Thêm GO sau CREATE TABLE

-- Đảm bảo có dấu chấm phẩy sau lệnh CREATE INDEX
CREATE UNIQUE NONCLUSTERED INDEX UK_KeyResult_MeasureUnitId ON [okr-be].dbo.key_result (  measure_unit_id ASC  )
     WHERE  ([measure_unit_id] IS NOT NULL)
     WITH (  PAD_INDEX = OFF ,FILLFACTOR = 100  ,SORT_IN_TEMPDB = OFF , IGNORE_DUP_KEY = OFF , STATISTICS_NORECOMPUTE = OFF , ONLINE = OFF , ALLOW_ROW_LOCKS = ON , ALLOW_PAGE_LOCKS = ON  )
     ON [PRIMARY];
GO -- Thêm GO sau CREATE INDEX

DROP TABLE IF EXISTS  [okr-be].dbo.key_result_comment;
GO
CREATE TABLE [okr-be].dbo.key_result_comment (
    is_deleted bit NOT NULL,
    created_at datetimeoffset(6) NULL,
    last_updated_at datetimeoffset(6) NULL,
    id uniqueidentifier NOT NULL,
    key_result_id uniqueidentifier NULL,
    user_id uniqueidentifier NULL,
    comment nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    CONSTRAINT PK_KeyResultComment PRIMARY KEY (id)
);
GO

DROP TABLE IF EXISTS  [okr-be].dbo.key_result_supporter;
GO
CREATE TABLE [okr-be].dbo.key_result_supporter (
    key_result_id uniqueidentifier NOT NULL,
    user_id uniqueidentifier NOT NULL,
    CONSTRAINT PK_KeyResultSupporter PRIMARY KEY (key_result_id,user_id)
);
GO

DROP TABLE IF EXISTS  [okr-be].dbo.muser;
GO
CREATE TABLE [okr-be].dbo.muser (
    is_deleted bit NOT NULL,
    created_at datetimeoffset(6) NULL,
    last_updated_at datetimeoffset(6) NULL,
    current_workspace_id uniqueidentifier NULL,
    id uniqueidentifier NOT NULL,
    avatar varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    bio nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    email nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    full_name nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    role_name varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    CONSTRAINT PK_User PRIMARY KEY (id),
    CONSTRAINT UK_User_Email UNIQUE (email)
);
GO -- Thêm GO sau CREATE TABLE

-- Đảm bảo có dấu chấm phẩy sau lệnh CREATE INDEX
CREATE UNIQUE NONCLUSTERED INDEX UK_User_CurrentWorkspaceId ON [okr-be].dbo.muser (  current_workspace_id ASC  )  
     WHERE  ([current_workspace_id] IS NOT NULL)
     WITH (  PAD_INDEX = OFF ,FILLFACTOR = 100  ,SORT_IN_TEMPDB = OFF , IGNORE_DUP_KEY = OFF , STATISTICS_NORECOMPUTE = OFF , ONLINE = OFF , ALLOW_ROW_LOCKS = ON , ALLOW_PAGE_LOCKS = ON  )
     ON [PRIMARY ];
GO
CREATE UNIQUE NONCLUSTERED INDEX UK_User_RoleName ON [okr-be].dbo.muser (  role_name ASC  )  
     WHERE  ([role_name] IS NOT NULL)
     WITH (  PAD_INDEX = OFF ,FILLFACTOR = 100  ,SORT_IN_TEMPDB = OFF , IGNORE_DUP_KEY = OFF , STATISTICS_NORECOMPUTE = OFF , ONLINE = OFF , ALLOW_ROW_LOCKS = ON , ALLOW_PAGE_LOCKS = ON  )
     ON [PRIMARY ];
GO

DROP TABLE IF EXISTS  [okr-be].dbo.objective;
GO
CREATE TABLE [okr-be].dbo.objective (
    is_deleted bit NOT NULL,
    progress float NOT NULL,
    created_at datetimeoffset(6) NULL,
    last_updated_at datetimeoffset(6) NULL,
    creator_id uniqueidentifier NULL,
    cycle_id uniqueidentifier NULL,
    id uniqueidentifier NOT NULL,
    parent_objective_id uniqueidentifier NULL,
    responsible_user_id uniqueidentifier NULL,
    team_id uniqueidentifier NULL,
    workspace_id uniqueidentifier NULL,
    description nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    name nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    CONSTRAINT PK_Objective PRIMARY KEY (id)
);
GO -- Thêm GO sau CREATE TABLE

-- Đảm bảo có dấu chấm phẩy sau lệnh CREATE INDEX
CREATE UNIQUE NONCLUSTERED INDEX UK_Objective_CreatorId ON [okr-be].dbo.objective (  creator_id ASC  )  
     WHERE  ([creator_id] IS NOT NULL)
     WITH (  PAD_INDEX = OFF ,FILLFACTOR = 100  ,SORT_IN_TEMPDB = OFF , IGNORE_DUP_KEY = OFF , STATISTICS_NORECOMPUTE = OFF , ONLINE = OFF , ALLOW_ROW_LOCKS = ON , ALLOW_PAGE_LOCKS = ON  )
     ON [PRIMARY ];
GO
CREATE UNIQUE NONCLUSTERED INDEX UK_Objective_ParentObjectiveId ON [okr-be].dbo.objective (  parent_objective_id ASC  )  
     WHERE  ([parent_objective_id] IS NOT NULL)
     WITH (  PAD_INDEX = OFF ,FILLFACTOR = 100  ,SORT_IN_TEMPDB = OFF , IGNORE_DUP_KEY = OFF , STATISTICS_NORECOMPUTE = OFF , ONLINE = OFF , ALLOW_ROW_LOCKS = ON , ALLOW_PAGE_LOCKS = ON  )
     ON [PRIMARY ];
GO
CREATE UNIQUE NONCLUSTERED INDEX UK_Objective_CycleId ON [okr-be].dbo.objective (  cycle_id ASC  )  
     WHERE  ([cycle_id] IS NOT NULL)
     WITH (  PAD_INDEX = OFF ,FILLFACTOR = 100  ,SORT_IN_TEMPDB = OFF , IGNORE_DUP_KEY = OFF , STATISTICS_NORECOMPUTE = OFF , ONLINE = OFF , ALLOW_ROW_LOCKS = ON , ALLOW_PAGE_LOCKS = ON  )
     ON [PRIMARY ];
GO
CREATE UNIQUE NONCLUSTERED INDEX UK_Objective_ResponsibleUserId ON [okr-be].dbo.objective (  responsible_user_id ASC  )  
     WHERE  ([responsible_user_id] IS NOT NULL)
     WITH (  PAD_INDEX = OFF ,FILLFACTOR = 100  ,SORT_IN_TEMPDB = OFF , IGNORE_DUP_KEY = OFF , STATISTICS_NORECOMPUTE = OFF , ONLINE = OFF , ALLOW_ROW_LOCKS = ON , ALLOW_PAGE_LOCKS = ON  )
     ON [PRIMARY ];
GO

DROP TABLE IF EXISTS  [okr-be].dbo.team;
GO
CREATE TABLE [okr-be].dbo.team (
    is_deleted bit NOT NULL,
    created_at datetimeoffset(6) NULL,
    last_updated_at datetimeoffset(6) NULL,
    id uniqueidentifier NOT NULL,
    parent_team_id uniqueidentifier NULL,
    workspace_id uniqueidentifier NULL,
    name nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    CONSTRAINT PK_Team PRIMARY KEY (id)
);
GO

DROP TABLE IF EXISTS  [okr-be].dbo.team_user;
GO
CREATE TABLE [okr-be].dbo.team_user (
    is_deleted bit NOT NULL,
    created_at datetimeoffset(6) NULL,
    last_updated_at datetimeoffset(6) NULL,
    id uniqueidentifier NOT NULL,
    team_id uniqueidentifier NOT NULL,
    user_id uniqueidentifier NOT NULL,
    CONSTRAINT PK_TeamUser PRIMARY KEY (id)
);
GO

DROP TABLE IF EXISTS  [okr-be].dbo.workspace;
GO
CREATE TABLE [okr-be].dbo.workspace (
    is_deleted bit NOT NULL,
    [type] smallint NOT NULL,
    created_at datetimeoffset(6) NULL,
    last_updated_at datetimeoffset(6) NULL,
    id uniqueidentifier NOT NULL,
    owner_id uniqueidentifier NULL,
    avatar varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    name nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    CONSTRAINT PK_Workspace PRIMARY KEY (id)
);
GO
ALTER TABLE [okr-be].dbo.workspace WITH NOCHECK ADD CONSTRAINT CK_Workspace_Type CHECK (([type]>=(0) AND [type]<=(3)));
GO -- Thêm GO sau ALTER TABLE

DROP TABLE IF EXISTS  [okr-be].dbo.workspace_user;
GO
CREATE TABLE [okr-be].dbo.workspace_user (
    is_deleted bit NOT NULL,
    created_at datetimeoffset(6) NULL,
    last_updated_at datetimeoffset(6) NULL,
    id uniqueidentifier NOT NULL,
    user_id uniqueidentifier NULL,
    workspace_id uniqueidentifier NULL,
    CONSTRAINT PK_WorkspaceUser PRIMARY KEY (id)
);
GO

--------------------------------------------------------------------------------
-- FOREIGN KEYS (Thêm dấu chấm phẩy và GO sau mỗi ALTER TABLE)
--------------------------------------------------------------------------------

-- [okr-be].dbo.check_in foreign keys
ALTER TABLE [okr-be].dbo.check_in ADD CONSTRAINT FK_CheckIn_Objective FOREIGN KEY (objective_id) REFERENCES [okr-be].dbo.objective(id);
GO
ALTER TABLE [okr-be].dbo.check_in ADD CONSTRAINT FK_CheckIn_Reporter FOREIGN KEY (reporter_id) REFERENCES [okr-be].dbo.muser(id);
GO

-- [okr-be].dbo.check_in_detail foreign keys
ALTER TABLE [okr-be].dbo.check_in_detail ADD CONSTRAINT FK_CheckInDetail_KeyResult FOREIGN KEY (key_result_id) REFERENCES [okr-be].dbo.key_result(id);
GO
ALTER TABLE [okr-be].dbo.check_in_detail ADD CONSTRAINT FK_CheckInDetail_CheckIn FOREIGN KEY (check_in_id) REFERENCES [okr-be].dbo.check_in(id);
GO

-- [okr-be].dbo.[cycle] foreign keys
ALTER TABLE [okr-be].dbo.[cycle] ADD CONSTRAINT FK_Cycle_Workspace FOREIGN KEY (workspace_id) REFERENCES [okr-be].dbo.workspace(id);
GO

-- [okr-be].dbo.key_result foreign keys
ALTER TABLE [okr-be].dbo.key_result ADD CONSTRAINT FK_KeyResult_MeasureUnit FOREIGN KEY (measure_unit_id) REFERENCES [okr-be].dbo.key_result(id);
GO
ALTER TABLE [okr-be].dbo.key_result ADD CONSTRAINT FK_KeyResult_Objective FOREIGN KEY (objective_id) REFERENCES [okr-be].dbo.objective(id);
GO

-- [okr-be].dbo.key_result_comment foreign keys
ALTER TABLE [okr-be].dbo.key_result_comment ADD CONSTRAINT FK_KeyResultComment_KeyResult FOREIGN KEY (key_result_id) REFERENCES [okr-be].dbo.key_result(id);
GO
ALTER TABLE [okr-be].dbo.key_result_comment ADD CONSTRAINT FK_KeyResultComment_User FOREIGN KEY (user_id) REFERENCES [okr-be].dbo.muser(id);
GO

-- [okr-be].dbo.key_result_supporter foreign keys
ALTER TABLE [okr-be].dbo.key_result_supporter ADD CONSTRAINT FK_KeyResultSupporter_KeyResult FOREIGN KEY (key_result_id) REFERENCES [okr-be].dbo.key_result(id);
GO
ALTER TABLE [okr-be].dbo.key_result_supporter ADD CONSTRAINT FK_KeyResultSupporter_User FOREIGN KEY (user_id) REFERENCES [okr-be].dbo.muser(id);
GO

-- [okr-be].dbo.muser foreign keys
ALTER TABLE [okr-be].dbo.muser ADD CONSTRAINT FK_User_Role FOREIGN KEY (role_name) REFERENCES [okr-be].dbo.[role](role_name);
GO
ALTER TABLE [okr-be].dbo.muser ADD CONSTRAINT FK_User_CurrentWorkspace FOREIGN KEY (current_workspace_id) REFERENCES [okr-be].dbo.workspace(id);
GO

-- [okr-be].dbo.objective foreign keys
ALTER TABLE [okr-be].dbo.objective ADD CONSTRAINT FK_Objective_Workspace FOREIGN KEY (workspace_id) REFERENCES [okr-be].dbo.workspace(id);
GO
ALTER TABLE [okr-be].dbo.objective ADD CONSTRAINT FK_Objective_Team FOREIGN KEY (team_id) REFERENCES [okr-be].dbo.team(id);
GO
ALTER TABLE [okr-be].dbo.objective ADD CONSTRAINT FK_Objective_ResponsibleUser FOREIGN KEY (responsible_user_id) REFERENCES [okr-be].dbo.muser(id);
GO
ALTER TABLE [okr-be].dbo.objective ADD CONSTRAINT FK_Objective_Cycle FOREIGN KEY (cycle_id) REFERENCES [okr-be].dbo.[cycle](id);
GO
ALTER TABLE [okr-be].dbo.objective ADD CONSTRAINT FK_Objective_Creator FOREIGN KEY (creator_id) REFERENCES [okr-be].dbo.muser(id);
GO
ALTER TABLE [okr-be].dbo.objective ADD CONSTRAINT FK_Objective_ParentObjective FOREIGN KEY (parent_objective_id) REFERENCES [okr-be].dbo.objective(id);
GO

-- [okr-be].dbo.team foreign keys
ALTER TABLE [okr-be].dbo.team ADD CONSTRAINT FK_Team_ParentTeam FOREIGN KEY (parent_team_id) REFERENCES [okr-be].dbo.team(id);
GO
ALTER TABLE [okr-be].dbo.team ADD CONSTRAINT FK_Team_Workspace FOREIGN KEY (workspace_id) REFERENCES [okr-be].dbo.workspace(id);
GO

-- [okr-be].dbo.team_user foreign keys
ALTER TABLE [okr-be].dbo.team_user ADD CONSTRAINT FK_TeamUser_Team FOREIGN KEY (team_id) REFERENCES [okr-be].dbo.team(id);
GO
ALTER TABLE [okr-be].dbo.team_user ADD CONSTRAINT FK_TeamUser_User FOREIGN KEY (user_id) REFERENCES [okr-be].dbo.muser(id);
GO

-- [okr-be].dbo.workspace foreign keys
ALTER TABLE [okr-be].dbo.workspace ADD CONSTRAINT FK_Workspace_Owner FOREIGN KEY (owner_id) REFERENCES [okr-be].dbo.muser(id);
GO

-- [okr-be].dbo.workspace_user foreign keys
ALTER TABLE [okr-be].dbo.workspace_user ADD CONSTRAINT FK_WorkspaceUser_User FOREIGN KEY (user_id) REFERENCES [okr-be].dbo.muser(id);
GO
ALTER TABLE [okr-be].dbo.workspace_user ADD CONSTRAINT FK_WorkspaceUser_Workspace FOREIGN KEY (workspace_id) REFERENCES [okr-be].dbo.workspace(id);
GO -- Đảm bảo kết thúc bằng GO cuối cùng