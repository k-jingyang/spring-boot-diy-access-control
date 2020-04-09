INSERT INTO ACL_P_OBJECT (P_OBJECT_ID, TEAM_ID, TEAM_NAME, AC_ROLE_NAME) VALUES
('1', '1', 'ROCKET', 'Owner'),
('1', '2', 'MAGMA', 'Viewer'),
('1', '3', 'AQUA', 'Viewer'),
('2', '1', 'ROCKET', 'Owner'),
('3', '3', 'AQUA', 'Collaborator');

INSERT INTO ACL_SECTION_PERMISSIONS(AC_ROLE_NAME, INFO_SECTION, PERMISSIONS) VALUES
('Owner', 'SECTION_1', '7'),
('Viewer', 'SECTION_1', '4'),
('Owner', 'SECTION_2', '7'),
('Collaborator', 'SECTION_2', '5');

