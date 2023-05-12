INSERT INTO authors (name, birthplace, birthday)
VALUES ('Stendhal', 'Grenoble', '1983-01-23'),
       ('Ernest Hemingway', 'Idaho', '1999-07-21'),
       ('Fyodor Dostoevsky', 'Moscow', '1821-11-01')
ON CONFLICT DO NOTHING;
INSERT INTO books (title, number_of_pages, price, date_of_publishing, author_id)
VALUES ('The Old Man and the Sea', 200, 30.50, '2002-05-05', 2),
       ('The Great Gatsby', 255, 43.75, '1990-12-10', 1),
       ('The Red and the Black', 370, 26.30, '1995-09-25', 1),
       ('The Brothers Karamazov', 420, 53.20, '1990-12-10', 3)
ON CONFLICT DO NOTHING;