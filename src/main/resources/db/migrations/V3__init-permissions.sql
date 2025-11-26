USE [okr-be];
GO

INSERT INTO dbo.Permission (name, description) VALUES
('SYSTEM_USERS_VIEW', 'View all users in the system'),                   -- SYSTEM_ADMIN
('SYSTEM_WORKSPACES_VIEW', 'View all workspaces in the system'),         -- SYSTEM_ADMIN
('SYSTEM_ROLES_PERMISSIONS_VIEW', 'View all roles and their permissions'), -- SYSTEM_ADMIN
('WORKSPACE_CREATE_APPROVE', 'Approve creation of a new workspace'),     -- SYSTEM_ADMIN

-- WORKSPACE Permissions
('WORKSPACE_VIEW', 'View workspace'),                                    -- WORKSPACE_ADMIN, TEAM_LEADER, TEAM_MEMBER, WORKSPACE_MEMBER
('WORKSPACE_UPDATE', 'Update workspace info'),                           -- WORKSPACE_ADMIN
('WORKSPACE_DELETE', 'Delete workspace'),                                -- WORKSPACE_ADMIN

-- WORKSPACE Member Permissions
('WORKSPACE_ADD_MEMBER', 'Add members to workspace'),                    -- WORKSPACE_ADMIN
('WORKSPACE_EDIT_MEMBER', 'Edit members to workspace'),                  -- WORKSPACE_ADMIN
('WORKSPACE_INVITE_MEMBER', 'Invite members to workspace'),              -- WORKSPACE_ADMIN
('WORKSPACE_REMOVE_MEMBER', 'Remove members from workspace'),            -- WORKSPACE_ADMIN
('WORKSPACE_LIST_MEMBER', 'List all members from workspace'),            -- WORKSPACE_ADMIN, TEAM_LEADER, TEAM_MEMBER, WORKSPACE_MEMBER

-- CYCLE Member Permissions
('CYCLE_CREATE', 'Create a new OKR cycle'),                              -- WORKSPACE_ADMIN only
('CYCLE_EDIT', 'Edit an existing OKR cycle'),                            -- WORKSPACE_ADMIN only
('CYCLE_DELETE', 'Delete an OKR cycle'),                                 -- WORKSPACE_ADMIN only
('CYCLE_VIEW', 'View OKR cycle details'),                                -- WORKSPACE_ADMIN, TEAM_LEADER, TEAM_MEMBER, WORKSPACE_MEMBER

-- Job Position Permissions
('JOB_POSITION_CREATE', 'Create a new job position'),                    -- WORKSPACE_ADMIN only
('JOB_POSITION_EDIT', 'Edit an existing job position'),                  -- WORKSPACE_ADMIN only
('JOB_POSITION_DELETE', 'Delete a job position'),                        -- WORKSPACE_ADMIN only
('JOB_POSITION_VIEW', 'View job position details'),                      -- WORKSPACE_ADMIN, TEAM_LEADER, TEAM_MEMBER, WORKSPACE_MEMBER

-- Measure Units Permissions
('MEASURE_UNITS_ADD', 'Add a new measure unit'),                         -- WORKSPACE_ADMIN only
('MEASURE_UNITS_EDIT', 'Edit an existing measure unit'),                 -- WORKSPACE_ADMIN only
('MEASURE_UNITS_DELETE', 'Delete a measure unit'),                       -- WORKSPACE_ADMIN only
('MEASURE_UNITS_VIEW', 'View all measure units in the workspace'),       -- WORKSPACE_ADMIN, TEAM_LEADER, TEAM_MEMBER, WORKSPACE_MEMBER

-- Team Permission
('TEAM_CREATE', 'Create a team'),                                        -- WORKSPACE_ADMIN
('TEAM_EDIT', 'Edit a team'),                                            -- WORKSPACE_ADMIN
('TEAM_DELETE', 'Delete a team'),                                        -- WORKSPACE_ADMIN
('TEAM_VIEW_DETAIL', 'View detailed information of a team'),             -- WORKSPACE_ADMIN, TEAM_LEADER, TEAM_MEMBER

-- Team OKR Permission
('TEAM_OKR_CREATE', 'Create a team OKR'),                                -- TEAM_LEADER
('TEAM_OKR_EDIT', 'Edit an existing team OKR'),                          -- TEAM_LEADER
('TEAM_OKR_DELETE', 'Delete a team OKR'),                                -- TEAM_LEADER
('TEAM_OKR_VIEW_DETAIL', 'View detailed information of a team OKR'),     -- TEAM_LEADER, TEAM_MEMBER

-- Team OKR CheckIn Permission
('TEAM_OKR_SUBMIT_CHECKIN', 'Submit a check-in for a team OKR'),         -- TEAM_MEMBER
('TEAM_OKR_REQUEST_CHECKIN', 'Request a check-in for a team OKR'),       -- TEAM_MEMBER
('TEAM_OKR_APPROVE_CHECKIN', 'Approve or reject team OKR check-ins'),    -- TEAM_LEADER
('TEAM_OKR_CLOSE', 'Close a team OKR after evaluation'),                 -- TEAM_LEADER

-- Workspace OKR
('WP_OKR_CREATE', 'Create a Workspace OKR'),                                -- WORKSPACE_ADMIN
('WP_OKR_EDIT', 'Edit an existing Workspace OKR'),                          -- WORKSPACE_ADMIN
('WP_OKR_DELETE', 'Delete a Workspace OKR'),                                -- WORKSPACE_ADMIN
('WP_OKR_VIEW_DETAIL', 'View detailed information of a Workspace OKR'),     -- WORKSPACE_ADMIN, TEAM_LEADER, TEAM_MEMBER, WORKSPACE_MEMBER

-- Team OKR CheckIn Permission
('WP_OKR_SUBMIT_CHECKIN', 'Submit a check-in for a Workspace OKR'),          -- TEAM_LEADER
('WP_OKR_REQUEST_CHECKIN', 'Request approval for a Workspace OKR check-in'), -- TEAM_LEADER
('WP_OKR_APPROVE_CHECKIN', 'Approve or reject Workspace OKR check-ins'),     -- WORKSPACE_ADMIN
('WP_OKR_CLOSE', 'Close a Workspace OKR after evaluation'),                  -- WORKSPACE_ADMIN

-- Personal OKR
('PERSONAL_OKR_CREATE', 'Create a personal/private OKR'),           -- WORKSPACE_ADMIN, TEAM_LEADER, TEAM_MEMBER, WORKSPACE_MEMBER
('PERSONAL_OKR_EDIT', 'Edit own personal/private OKR'),             -- WORKSPACE_ADMIN, TEAM_LEADER, TEAM_MEMBER, WORKSPACE_MEMBER
('PERSONAL_OKR_DELETE', 'Delete own personal/private OKR'),         -- WORKSPACE_ADMIN, TEAM_LEADER, TEAM_MEMBER, WORKSPACE_MEMBER
('PERSONAL_OKR_VIEW', 'View own personal/private OKR');             -- WORKSPACE_ADMIN, TEAM_LEADER, TEAM_MEMBER, WORKSPACE_MEMBER

GO