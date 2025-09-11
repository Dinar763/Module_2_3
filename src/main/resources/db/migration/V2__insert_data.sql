DELETE FROM post_label;
DELETE FROM post;
DELETE FROM label;
DELETE FROM writer;

INSERT INTO label (name) VALUES
    ('Важное'),
    ('Технологии'),
    ('Искусство'),
    ('Наука'),
    ('История');

INSERT INTO writer (firstname, lastname) VALUES
    ('Иван', 'Иванов'),
    ('Петр', 'Петров'),
    ('Мария', 'Сидорова'),
    ('Алексей', 'Смирнов'),
    ('Елена', 'Кузнецова');

INSERT INTO post (content, status, writer_id) VALUES
    ('Первая статья о технологиях', 'ACTIVE', 1),
    ('Искусство в современном мире', 'ACTIVE', 2),
    ('Научные открытия 2023 года', 'UNDER_REVIEW', 3),
    ('Исторический анализ событий', 'ACTIVE', 4),
    ('Личные размышления о жизни', 'DELETED', 5),
    ('Новые тенденции в дизайне', 'ACTIVE', 1),
    ('Программирование на Java', 'ACTIVE', 2);

INSERT INTO post_label (post_id, label_id) VALUES
    (1, 2),
    (1, 1),
    (2, 3),
    (3, 4),
    (3, 1),
    (4, 5),
    (5, 1),
    (6, 3),
    (7, 2),
    (7, 1);