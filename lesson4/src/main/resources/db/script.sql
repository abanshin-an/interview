-- # Задача про кинотеатр.
--    У фильма, который идет в кинотеатре, есть:
--    * название,
--    * длительность (пусть будет 60, 90 или 120 минут),
--    * цена билета (в разное время и дни может быть разной),
--    * время начала сеанса (один фильм может быть показан несколько раз в разное время и с разной ценой билета).
--    * Есть информация о купленных билетах (номер билета, на какой сеанс).

-- # Задания:
--    1. Составить грамотную нормализованную схему хранения этих данных в БД. Внести в нее 4–5 фильмов, расписание на один день и несколько проданных билетов.
SET search_path TO interview;
CREATE TABLE IF NOT EXISTS tb_film
(
    id            VARCHAR(36) PRIMARY KEY,
    film_name     VARCHAR,
    film_duration INTERVAL
);
CREATE TABLE IF NOT EXISTS tb_session
(
    id            VARCHAR(36) PRIMARY KEY,
    film_id       VARCHAR(36),
    session_start TIME,
    ticket_price  NUMERIC(15, 2),
    CONSTRAINT fk_session_film FOREIGN KEY (film_id) REFERENCES tb_film (id)
);
CREATE TABLE IF NOT EXISTS tb_ticket
(
    id         VARCHAR(36) PRIMARY KEY,
    session_id VARCHAR(36),
    CONSTRAINT fk_ticket_session FOREIGN KEY (session_id) REFERENCES tb_session (id)
);

INSERT INTO tb_film
(id, film_name, film_duration)
VALUES ('40ddf02e-496d-4551-be45-3f8806c2644a', 'Кавказская пленница', '01:22:00'),
       ('c51c2f42-6c95-4233-8980-cc47b9488cba', 'Старики-разбойники', '01:27:00'),
       ('d0b7e72c-9cc9-4fe3-b48e-45d06373945c', 'Джентльмены удачи', '01:40:00'),
       ('e8275f79-27a1-4c18-8949-3456ec94ae27', 'Полосатый рейс', '01:22:00'),
       ('41464a13-9acb-4cef-8205-496cfc67933f', 'Золотой теленок', '02:43:00'),
       ('3c8e6f97-e8b0-4e0f-9c83-fd1a5aa86770', 'Берегись автомобиля', '01:34:00');

INSERT INTO tb_session
(id, film_id, session_start, ticket_price)
VALUES ('9109f528-7fb7-4b7b-91a2-d2d183311076', 'e8275f79-27a1-4c18-8949-3456ec94ae27', '09:00', 150.00),
       ('b54ebb4f-f439-4230-abd8-b85fd1bf896a', '40ddf02e-496d-4551-be45-3f8806c2644a', '11:00', 200.00),
       ('21565420-c44b-461d-89a7-59cc5ad046d3', 'c51c2f42-6c95-4233-8980-cc47b9488cba', '13:00', 250.00),
       ('8f1a693d-b9c9-4290-a028-354deb28234f', 'd0b7e72c-9cc9-4fe3-b48e-45d06373945c', '15:00', 300.00),
       ('2017ac2d-f0ed-4697-9d0d-6c719a5d54a1', 'e8275f79-27a1-4c18-8949-3456ec94ae27', '16:00', 350.00),
       ('54bc7850-26c3-4bd3-9e0e-f4860ae902c5', '41464a13-9acb-4cef-8205-496cfc67933f', '18:00', 400.00),
       ('3067a7c4-91da-40a9-b307-3c94ca3c4c98', '3c8e6f97-e8b0-4e0f-9c83-fd1a5aa86770', '21:00', 500.00);

INSERT INTO tb_ticket
(id, session_id)
VALUES ('00f36061-b6ad-4c70-99a7-88a5e95b2bc8', '9109f528-7fb7-4b7b-91a2-d2d183311076'),
       ('1d0f441b-46d2-42cf-ba4f-406f9aefb8fa', '9109f528-7fb7-4b7b-91a2-d2d183311076'),
       ('bc071067-fff0-4698-b581-4591e2d3487d', '9109f528-7fb7-4b7b-91a2-d2d183311076'),
       ('726dbe91-a36a-495d-b423-8cbbd0c34ffe', '9109f528-7fb7-4b7b-91a2-d2d183311076'),
       ('64ac4189-cc51-40d7-9bb0-1bcf2b34e2eb', 'b54ebb4f-f439-4230-abd8-b85fd1bf896a'),
       ('dabbb00e-2757-4724-9839-8c9cee8d632a', 'b54ebb4f-f439-4230-abd8-b85fd1bf896a'),
       ('dd590006-4e7f-4cf1-8858-e05a4e4d3f2b', 'b54ebb4f-f439-4230-abd8-b85fd1bf896a'),
       ('fda94459-76e1-4891-9d7c-6642db865b8e', 'b54ebb4f-f439-4230-abd8-b85fd1bf896a'),
       ('948d04f3-0f5b-45de-bd3c-39d02e7c0e48', '21565420-c44b-461d-89a7-59cc5ad046d3'),
       ('44be8d7c-b849-422f-990a-e4b213adb522', '21565420-c44b-461d-89a7-59cc5ad046d3'),
       ('2680f06d-4c53-4c64-88a3-16c68f13a603', '21565420-c44b-461d-89a7-59cc5ad046d3'),
       ('5832f929-ee57-43da-a11a-ca251583fb89', '8f1a693d-b9c9-4290-a028-354deb28234f'),
       ('087b8b14-cfbb-44dc-a9b4-d6834845398e', '8f1a693d-b9c9-4290-a028-354deb28234f'),
       ('16e2d3e6-1d54-4546-a606-441a03acdca7', '8f1a693d-b9c9-4290-a028-354deb28234f'),
       ('bde4ebb7-2890-4df5-a94a-679ef6a2e072', '8f1a693d-b9c9-4290-a028-354deb28234f'),
       ('1e3429cf-695a-4843-aab8-fa47c978fd0a', '8f1a693d-b9c9-4290-a028-354deb28234f'),
       ('1f533f32-06e3-4f57-aca3-ea1cc81cfa41', '8f1a693d-b9c9-4290-a028-354deb28234f'),
       ('af48c8b9-3f5a-4020-ba3a-b460e9a3fae0', '2017ac2d-f0ed-4697-9d0d-6c719a5d54a1'),
       ('5320234d-9701-491b-babd-9083914382e3', '2017ac2d-f0ed-4697-9d0d-6c719a5d54a1'),
       ('e0787b89-77e5-4e60-bbad-e61cbf989830', '2017ac2d-f0ed-4697-9d0d-6c719a5d54a1'),
       ('827cc518-3df5-4819-8826-c1aa8ce11b2b', '2017ac2d-f0ed-4697-9d0d-6c719a5d54a1'),
       ('391822aa-6cd6-4ffa-a57c-3180a4365040', '54bc7850-26c3-4bd3-9e0e-f4860ae902c5'),
       ('1c4a64a9-f99c-4113-93db-e558a41bef52', '3067a7c4-91da-40a9-b307-3c94ca3c4c98'),
       ('93d91acd-6f9b-4b5d-bfd0-3dd2eb130613', '3067a7c4-91da-40a9-b307-3c94ca3c4c98'),
       ('b9b88b93-3a11-4593-8512-e3d48b5d0682', '3067a7c4-91da-40a9-b307-3c94ca3c4c98');

CREATE OR REPLACE VIEW v_session(id, next_id, film_name, start_time, end_time, film_duration) AS
SELECT ts.id,
       (SELECT ts1.id
        FROM tb_session ts1
        where ts1.session_start > ts.session_start
        ORDER BY session_start
        LIMIT 1) AS prev_id,
       film_name,
       session_start,
       session_start + tf.film_duration,
       film_duration
FROM tb_session AS ts
         JOIN tb_film tf ON tf.id = ts.film_id
ORDER BY 3;

SELECT *
FROM v_session
ORDER BY start_time;

--    2. Сделать запросы, считающие и выводящие в понятном виде:
--       * ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени.
--          - выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;
--       * перерывы 30 минут и более между фильмами
--          — выводить по уменьшению длительности перерыва. Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;
--      * список фильмов,
--          - для каждого — с указанием общего числа посетителей за все время,
--          - среднего числа зрителей за сеанс и
--          - общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли).
--          - внизу таблицы должна быть
--             +  строчка «итого», содержащая данные по всем фильмам сразу;
--             +  число посетителей и кассовые сборы, сгруппированные по времени начала фильма:
--         - с 9 до 15,
--         - с 15 до 18,
--         - с 18 до 21,
--         - с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).

-- ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени.
SELECT 'Ошибки в расписании ' title,
       s1.film_name,
       s1.start_time,
       s1.end_time::TIME,
       s2.film_name,
       s2.start_time,
       (s1.end_time - s2.start_time)::TIME overlap
FROM v_session s1
         JOIN v_session s2 ON (s1.next_id = s2.id)
WHERE s2.start_time < s1.end_time
ORDER BY s1.start_time;

-- перерывы 30 минут и более между фильмами
SELECT 'Интервал > 30 мин' title,
       s1.film_name,
       s1.start_time,
       s1.end_time::TIME,
       s2.film_name,
       s2.start_time,
       (s2.start_time - s1.end_time)::TIME gap
FROM v_session s1
         JOIN v_session s2 ON (s1.next_id = s2.id)
WHERE s2.start_time >= s1.end_time + INTERVAL '30 minutes'
ORDER BY 7 desc;

--      * список фильмов,
--          - для каждого — с указанием общего числа посетителей за все время,
--          - среднего числа зрителей за сеанс и
--          - общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли).
--          - внизу таблицы должна быть
--           - строчка «итого», содержащая данные по всем фильмам сразу;
WITH f_1 AS (SELECT tf.film_name, COUNT(tt.*), COUNT(tt.*) / COUNT(DISTINCT ts.*) average, SUM(ts.ticket_price)
             FROM tb_film AS tf
                      JOIN tb_session ts on tf.id = ts.film_id
                      JOIN tb_ticket tt on ts.id = tt.session_id
             GROUP BY film_name
             ORDER BY 4 DESC),
     f_2 AS (SELECT 'Итого', COUNT(tt.*), COUNT(tt.*) / count(DISTINCT ts.*), SUM(ts.ticket_price)
             FROM tb_film AS tf
                      JOIN tb_session ts on tf.id = ts.film_id
                      JOIN tb_ticket tt on ts.id = tt.session_id),
     consolidated AS (SELECT * FROM f_1 UNION ALL SELECT * FROM f_2)
SELECT *
FROM consolidated;

--       * число посетителей и кассовые сборы, сгруппированные по времени начала фильма:
--         - с 9 до 15,
--         - с 15 до 18,
--         - с 18 до 21,
--         - с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).
SELECT CASE
           WHEN session_start BETWEEN '09:00' AND '14:59' THEN '09:00-15:00'
           WHEN session_start BETWEEN '15:00' AND '17:59' THEN '15:00-18:00'
           WHEN session_start BETWEEN '18:00' AND '20:59' THEN '18:00-21:00'
           WHEN session_start BETWEEN '21:00' AND '23:59' THEN '21:00-00:00'
           END,
       COUNT(tt.*),
       SUM(ts.ticket_price)
FROM tb_film AS tf
         JOIN tb_session ts ON tf.id = ts.film_id
         JOIN tb_ticket tt ON ts.id = tt.session_id
GROUP BY 1
ORDER BY 1;