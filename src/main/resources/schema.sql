DROP TABLE IF EXISTS ACL_P_OBJECT;

CREATE TABLE ACL_P_OBJECT
(
ID              INT AUTO_INCREMENT PRIMARY KEY,
P_OBJECT_ID     INT,
TEAM_ID         INT,
TEAM_NAME       VARCHAR,
AC_ROLE_NAME    VARCHAR
);

DROP TABLE IF EXISTS ACL_SECTION_PERMISSIONS;

CREATE TABLE ACL_SECTION_PERMISSIONS
(
ID              INT PRIMARY KEY auto_increment,
AC_ROLE_NAME    VARCHAR,
INFO_SECTION    VARCHAR,
PERMISSIONS      INT
);

