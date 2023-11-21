-- -- INSERT INTO product() VALUES (3, 'Sumsung S23', 'Electronics', 'Samsung S23 model 2023 made inn chin', 1200);
--
--
INSERT INTO product (name, type, description, price) VALUES
                                                         ('samsung s23', 'Electronics', 'Smartphone with advanced features', 499.99),
                                                         ('Airmax', 'Clothing', 'Mens casual t-shirt', 29.99),
    ('coffee maker', 'Home Appliances', 'Coffee maker with built-in grinder', 149.99);

-- -- data.sql
--
INSERT INTO User (id, first_name, last_name, email_id,password, year, month, date, enabled, role)
VALUES
    (1, 'Musie', 'Fanuel', 'musiefanuel@gmail.com','$2a$10$Z7nZ2p.LV5sXr7UpepW9bOHqPs6SwJPl0/aTbq9IFpnaGblgcCYeK', '1994', 'January', '15', true, 'USER'),
    (2, 'Abraham', 'Afewerki', 'abrahamafewerki@gmail.com','$2a$10$Z7nZ2p.LV5sXr7UpepW9bOHqPs6SwJPl0/aTbq9IFpnaGblgcCYeK', '1995', 'March', '22', false, 'USER'),
    (3,'Natnael', 'Zerai', 'natizerai@gmail.com', '$2a$10$Z7nZ2p.LV5sXr7UpepW9bOHqPs6SwJPl0/aTbq9IFpnaGblgcCYeK', '1995', 'May', '10', true, 'USER'),
    (4, 'Muna', 'Haile', 'munahaile@gmail.com', '$2a$10$Z7nZ2p.LV5sXr7UpepW9bOHqPs6SwJPl0/aTbq9IFpnaGblgcCYeK', '1996', 'June', '14', true, 'USER'),
    (5, 'Henos', 'Ghirmay', 'henosghirmay@gmail.com', '$2a$10$Z7nZ2p.LV5sXr7UpepW9bOHqPs6SwJPl0/aTbq9IFpnaGblgcCYeK', '1997', 'February', '25', false, 'USER');

--
