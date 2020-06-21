INSERT INTO USERS (USERNAME, PASSWORD, ROLE) VALUES
('rocket_grunt',        '{bcrypt}$2a$10$AQGbP79Bm2Gfv8Lt.M.6pOzIN7u4r67WrKRgWPkedZGCZhyVQ6Y6W', 'USER'),
('magma_grunt',         '{bcrypt}$2a$10$AQGbP79Bm2Gfv8Lt.M.6pOzIN7u4r67WrKRgWPkedZGCZhyVQ6Y6W', 'USER'),
('magma_galactic_grunt','{bcrypt}$2a$10$AQGbP79Bm2Gfv8Lt.M.6pOzIN7u4r67WrKRgWPkedZGCZhyVQ6Y6W', 'USER'),
('galactic_grunt',      '{bcrypt}$2a$10$AQGbP79Bm2Gfv8Lt.M.6pOzIN7u4r67WrKRgWPkedZGCZhyVQ6Y6W', 'USER');

INSERT INTO USER_TEAM (USERNAME, TEAM_NAME) VALUES
('rocket_grunt', 'ROCKET'),
('magma_grunt', 'MAGMA'),
('magma_galactic_grunt', 'MAGMA'),
('magma_galactic_grunt', 'GALACTIC'),
('galactic_grunt', 'GALACTIC');

INSERT INTO POKEMON_ACL (POKEMON_ID, TEAM_NAME, AC_ROLE_NAME) VALUES
('1', 'ROCKET', 'Owner'),
('1', 'GALACTIC', 'Collaborator'),
('1', 'MAGMA', 'Viewer');

