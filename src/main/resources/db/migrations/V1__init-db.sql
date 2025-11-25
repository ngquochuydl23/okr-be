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
    CONSTRAINT PK__permissi__72E12F1A5AF8D253 PRIMARY KEY (name)
);
GO

DROP TABLE IF EXISTS [okr-be].dbo.[role];
GO
CREATE TABLE [okr-be].dbo.[role] (
    description varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    role_name varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    CONSTRAINT PK__role__783254B0A27E31DA PRIMARY KEY (role_name)
);
GO

DROP TABLE IF EXISTS [okr-be].dbo.role_permission;
GO
CREATE TABLE [okr-be].dbo.role_permission (
    permission_id varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    role_id varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    CONSTRAINT PK__role_per__32538CA6502D0B33 PRIMARY KEY (permission_id,role_id)
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
    CONSTRAINT PK__check_in__3213E83FFC573B5C PRIMARY KEY (id)
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
    CONSTRAINT PK__check_in__3213E83F6A5FBA37 PRIMARY KEY (id)
);
GO
ALTER TABLE [okr-be].dbo.check_in_detail WITH NOCHECK ADD CONSTRAINT CK__check_in___confi__7D98A078 CHECK (([confidence_level_enum]>=(0) AND [confidence_level_enum]<=(2)));
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
    CONSTRAINT PK__cycle__3213E83FBC2E1423 PRIMARY KEY (id)
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
    CONSTRAINT PK__key_resu__3213E83FC0673081 PRIMARY KEY (id)
);
GO -- Thêm GO sau CREATE TABLE

-- Đảm bảo có dấu chấm phẩy sau lệnh CREATE INDEX
CREATE UNIQUE NONCLUSTERED INDEX UKt157bc3rl8r8hfo90xh19rsm7 ON [okr-be].dbo.key_result (  measure_unit_id ASC  )
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
    CONSTRAINT PK__key_resu__3213E83FBB45F195 PRIMARY KEY (id)
);
GO

DROP TABLE IF EXISTS  [okr-be].dbo.key_result_supporter;
GO
CREATE TABLE [okr-be].dbo.key_result_supporter (
    key_result_id uniqueidentifier NOT NULL,
    user_id uniqueidentifier NOT NULL,
    CONSTRAINT PK__key_resu__7D180384A30D0F81 PRIMARY KEY (key_result_id,user_id)
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
    CONSTRAINT PK__muser__3213E83F76942F86 PRIMARY KEY (id),
    CONSTRAINT UK7kuv609oustl7r8kpdgnktbfp UNIQUE (email)
);
GO -- Thêm GO sau CREATE TABLE

-- Đảm bảo có dấu chấm phẩy sau lệnh CREATE INDEX
CREATE UNIQUE NONCLUSTERED INDEX UK8k1gk6l4ebj21t7flbauilhtk ON [okr-be].dbo.muser (  current_workspace_id ASC  )  
     WHERE  ([current_workspace_id] IS NOT NULL)
     WITH (  PAD_INDEX = OFF ,FILLFACTOR = 100  ,SORT_IN_TEMPDB = OFF , IGNORE_DUP_KEY = OFF , STATISTICS_NORECOMPUTE = OFF , ONLINE = OFF , ALLOW_ROW_LOCKS = ON , ALLOW_PAGE_LOCKS = ON  )
     ON [PRIMARY ];
GO
CREATE UNIQUE NONCLUSTERED INDEX UK9mi1kj2ry98rfbgi3e67aaxdb ON [okr-be].dbo.muser (  role_name ASC  )  
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
    CONSTRAINT PK__objectiv__3213E83F4A2A4466 PRIMARY KEY (id)
);
GO -- Thêm GO sau CREATE TABLE

-- Đảm bảo có dấu chấm phẩy sau lệnh CREATE INDEX
CREATE UNIQUE NONCLUSTERED INDEX UK1fhjdi440us56rfjuda24vie6 ON [okr-be].dbo.objective (  creator_id ASC  )  
     WHERE  ([creator_id] IS NOT NULL)
     WITH (  PAD_INDEX = OFF ,FILLFACTOR = 100  ,SORT_IN_TEMPDB = OFF , IGNORE_DUP_KEY = OFF , STATISTICS_NORECOMPUTE = OFF , ONLINE = OFF , ALLOW_ROW_LOCKS = ON , ALLOW_PAGE_LOCKS = ON  )
     ON [PRIMARY ];
GO
CREATE UNIQUE NONCLUSTERED INDEX UK6375byphgk2hqtmelr2fahit2 ON [okr-be].dbo.objective (  parent_objective_id ASC  )  
     WHERE  ([parent_objective_id] IS NOT NULL)
     WITH (  PAD_INDEX = OFF ,FILLFACTOR = 100  ,SORT_IN_TEMPDB = OFF , IGNORE_DUP_KEY = OFF , STATISTICS_NORECOMPUTE = OFF , ONLINE = OFF , ALLOW_ROW_LOCKS = ON , ALLOW_PAGE_LOCKS = ON  )
     ON [PRIMARY ];
GO
CREATE UNIQUE NONCLUSTERED INDEX UK80uu3cwhchkha0q4t1n8vkdc2 ON [okr-be].dbo.objective (  cycle_id ASC  )  
     WHERE  ([cycle_id] IS NOT NULL)
     WITH (  PAD_INDEX = OFF ,FILLFACTOR = 100  ,SORT_IN_TEMPDB = OFF , IGNORE_DUP_KEY = OFF , STATISTICS_NORECOMPUTE = OFF , ONLINE = OFF , ALLOW_ROW_LOCKS = ON , ALLOW_PAGE_LOCKS = ON  )
     ON [PRIMARY ];
GO
CREATE UNIQUE NONCLUSTERED INDEX UK9rfodvtj0d0alj5vmv18pf06o ON [okr-be].dbo.objective (  responsible_user_id ASC  )  
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
    CONSTRAINT PK__team__3213E83FF9EB4610 PRIMARY KEY (id)
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
    CONSTRAINT PK__team_use__3213E83FDF736714 PRIMARY KEY (id)
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
    CONSTRAINT PK__workspac__3213E83F1E50A165 PRIMARY KEY (id)
);
GO
ALTER TABLE [okr-be].dbo.workspace WITH NOCHECK ADD CONSTRAINT CK__workspace__type__119F9925 CHECK (([type]>=(0) AND [type]<=(3)));
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
    CONSTRAINT PK__workspac__3213E83FD764A4DC PRIMARY KEY (id)
);
GO

--------------------------------------------------------------------------------
-- FOREIGN KEYS (Thêm dấu chấm phẩy và GO sau mỗi ALTER TABLE)
--------------------------------------------------------------------------------

-- [okr-be].dbo.check_in foreign keys
ALTER TABLE [okr-be].dbo.check_in ADD CONSTRAINT FKb2t2p2xag74gt95rj2ohcw25k FOREIGN KEY (objective_id) REFERENCES [okr-be].dbo.objective(id);
GO
ALTER TABLE [okr-be].dbo.check_in ADD CONSTRAINT FKostl5acigglxwggnwf3q3e3gf FOREIGN KEY (reporter_id) REFERENCES [okr-be].dbo.muser(id);
GO

-- [okr-be].dbo.check_in_detail foreign keys
ALTER TABLE [okr-be].dbo.check_in_detail ADD CONSTRAINT FK12q6lvmaoqdyctcjrlmg6gda0 FOREIGN KEY (key_result_id) REFERENCES [okr-be].dbo.key_result(id);
GO
ALTER TABLE [okr-be].dbo.check_in_detail ADD CONSTRAINT FKnjcp0uc07q5n7cfj9tdtwf33g FOREIGN KEY (check_in_id) REFERENCES [okr-be].dbo.check_in(id);
GO

-- [okr-be].dbo.[cycle] foreign keys
ALTER TABLE [okr-be].dbo.[cycle] ADD CONSTRAINT FKii5xwi3n2x37y9w092yk5dgbj FOREIGN KEY (workspace_id) REFERENCES [okr-be].dbo.workspace(id);
GO

-- [okr-be].dbo.key_result foreign keys
ALTER TABLE [okr-be].dbo.key_result ADD CONSTRAINT FK7w5sy6rljmf828riu0v329cm5 FOREIGN KEY (measure_unit_id) REFERENCES [okr-be].dbo.key_result(id);
GO
ALTER TABLE [okr-be].dbo.key_result ADD CONSTRAINT FKrvcqyntd3p3kj8i7n0kuwbmqk FOREIGN KEY (objective_id) REFERENCES [okr-be].dbo.objective(id);
GO

-- [okr-be].dbo.key_result_comment foreign keys
ALTER TABLE [okr-be].dbo.key_result_comment ADD CONSTRAINT FKo8ejjt1vtih7us9l9f8q0k32g FOREIGN KEY (key_result_id) REFERENCES [okr-be].dbo.key_result(id);
GO
ALTER TABLE [okr-be].dbo.key_result_comment ADD CONSTRAINT FKqjm34lvm184o24oet3ndnt30f FOREIGN KEY (user_id) REFERENCES [okr-be].dbo.muser(id);
GO

-- [okr-be].dbo.key_result_supporter foreign keys
ALTER TABLE [okr-be].dbo.key_result_supporter ADD CONSTRAINT FKnam90c8853yhs27lpqafppl6e FOREIGN KEY (key_result_id) REFERENCES [okr-be].dbo.key_result(id);
GO
ALTER TABLE [okr-be].dbo.key_result_supporter ADD CONSTRAINT FKpvjbqlx61ib11f81b4avd5jl9 FOREIGN KEY (user_id) REFERENCES [okr-be].dbo.muser(id);
GO

-- [okr-be].dbo.muser foreign keys
ALTER TABLE [okr-be].dbo.muser ADD CONSTRAINT FK593ewny5d83cryfjv5ru55od2 FOREIGN KEY (role_name) REFERENCES [okr-be].dbo.[role](role_name);
GO
ALTER TABLE [okr-be].dbo.muser ADD CONSTRAINT FKjjbehog0nbq0lenattv66llgh FOREIGN KEY (current_workspace_id) REFERENCES [okr-be].dbo.workspace(id);
GO

-- [okr-be].dbo.objective foreign keys
ALTER TABLE [okr-be].dbo.objective ADD CONSTRAINT FK73khxsng41plxcjxieclcq0ca FOREIGN KEY (workspace_id) REFERENCES [okr-be].dbo.workspace(id);
GO
ALTER TABLE [okr-be].dbo.objective ADD CONSTRAINT FK8h2m4kk8wt96ran9rgxn9n3to FOREIGN KEY (team_id) REFERENCES [okr-be].dbo.team(id);
GO
ALTER TABLE [okr-be].dbo.objective ADD CONSTRAINT FKb8n43snod50xhr8ssfagrrlxs FOREIGN KEY (responsible_user_id) REFERENCES [okr-be].dbo.muser(id);
GO
ALTER TABLE [okr-be].dbo.objective ADD CONSTRAINT FKeqlborwd8e9qjmpoo061bc8ac FOREIGN KEY (cycle_id) REFERENCES [okr-be].dbo.[cycle](id);
GO
ALTER TABLE [okr-be].dbo.objective ADD CONSTRAINT FKr2k6i1sno1p2n5o9keebri9de FOREIGN KEY (creator_id) REFERENCES [okr-be].dbo.muser(id);
GO
ALTER TABLE [okr-be].dbo.objective ADD CONSTRAINT FKsq3x9rkdop29lyub1to7pr9rc FOREIGN KEY (parent_objective_id) REFERENCES [okr-be].dbo.objective(id);
GO

-- [okr-be].dbo.team foreign keys
ALTER TABLE [okr-be].dbo.team ADD CONSTRAINT FK13ubj3frsnn4adnc9x2y8qp6n FOREIGN KEY (parent_team_id) REFERENCES [okr-be].dbo.team(id);
GO
ALTER TABLE [okr-be].dbo.team ADD CONSTRAINT FKhu7l1af1d1n1foj4k082dixd7 FOREIGN KEY (workspace_id) REFERENCES [okr-be].dbo.workspace(id);
GO

-- [okr-be].dbo.team_user foreign keys
ALTER TABLE [okr-be].dbo.team_user ADD CONSTRAINT FKiuwi96twuthgvhnarqj34mnjv FOREIGN KEY (team_id) REFERENCES [okr-be].dbo.team(id);
GO
ALTER TABLE [okr-be].dbo.team_user ADD CONSTRAINT FKpa4yhjpookdmxsscijs6ja6p FOREIGN KEY (user_id) REFERENCES [okr-be].dbo.muser(id);
GO

-- [okr-be].dbo.workspace foreign keys
ALTER TABLE [okr-be].dbo.workspace ADD CONSTRAINT FKjc02gdvlu1yt5isnk0ey2ydp8 FOREIGN KEY (owner_id) REFERENCES [okr-be].dbo.muser(id);
GO

-- [okr-be].dbo.workspace_user foreign keys
ALTER TABLE [okr-be].dbo.workspace_user ADD CONSTRAINT FKhkrwafax9362p5dpi70tagivs FOREIGN KEY (user_id) REFERENCES [okr-be].dbo.muser(id);
GO
ALTER TABLE [okr-be].dbo.workspace_user ADD CONSTRAINT FKjek9w7728njryydpb6r7wa1lk FOREIGN KEY (workspace_id) REFERENCES [okr-be].dbo.workspace(id);
GO -- Đảm bảo kết thúc bằng GO cuối cùng