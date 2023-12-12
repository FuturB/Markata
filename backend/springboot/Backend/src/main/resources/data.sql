-- -- INSERT INTO product() VALUES (3, 'Sumsung S23', 'Electronics', 'Samsung S23 model 2023 made inn chin', 1200);
--
--


INSERT INTO product (name, type, description, price, quantity_in_stock) VALUES
('samsung s23', 'Electronics', 'Smartphone with advanced features', 499.99, 100),
 ('Airmax', 'Clothing', 'Mens casual t-shirt', 29.99, 50),
('coffee maker', 'Home Appliances', 'Coffee maker with built-in grinder', 149.99, 20),
('Sony Xperia 5 III', 'Electronics', 'High-end Android smartphone', 799.99, 30),
('HP Envy x360', 'Electronics', 'Convertible laptop with powerful performance', 1099.99, 15),
('Summer Breeze Dress', 'Clothing', 'Light and airy summer dress', 49.99, 40),
('Classic Oxford Shirt', 'Clothing', 'Mens classic oxford shirt', 39.99, 25),
('KitchenAid Stand Mixer', 'Home Appliances', 'Powerful stand mixer for baking enthusiasts', 299.99, 10),
('Smart LED TV 55 inch', 'Electronics', '4K Ultra HD Smart TV with built-in streaming', 699.99, 20),
('Running Shoes', 'Footwear', 'Comfortable and durable running shoes', 79.99, 35),
 ('Instax Mini 11', 'Electronics', 'Instant film camera for capturing memories', 69.99, 30),
 ('Cozy Throw Blanket', 'Home Decor', 'Soft and cozy throw blanket for chilly nights', 24.99, 30),
 ('Wireless Noise-Canceling Headphones', 'Electronics', 'Immersive audio experience with noise-canceling', 149.99, 25);


-- -- data.sql
--
INSERT INTO User (id, first_name, last_name, email_id,password, year, month, date, enabled, role)
VALUES
    (1, 'Musie', 'Fanuel', 'musiefanuel@gmail.com','$2a$10$Z7nZ2p.LV5sXr7UpepW9bOHqPs6SwJPl0/aTbq9IFpnaGblgcCYeK', '1994', 'January', '15', true, 'USER'),
    (2, 'Abraham', 'Afewerki', 'abrahamafewerki@gmail.com','$2a$10$Z7nZ2p.LV5sXr7UpepW9bOHqPs6SwJPl0/aTbq9IFpnaGblgcCYeK', '1995', 'March', '22', false, 'USER'),
    (3,'Natnael', 'Zerai', 'natizerai@gmail.com', '$2a$10$Z7nZ2p.LV5sXr7UpepW9bOHqPs6SwJPl0/aTbq9IFpnaGblgcCYeK', '1995', 'May', '10', true, 'USER'),
    (4, 'Muna', 'Haile', 'munahaile@gmail.com', '$2a$10$Z7nZ2p.LV5sXr7UpepW9bOHqPs6SwJPl0/aTbq9IFpnaGblgcCYeK', '1996', 'June', '14', true, 'USER'),
    (5, 'Henos', 'Ghirmay', 'henosghirmay@gmail.com', '$2a$10$Z7nZ2p.LV5sXr7UpepW9bOHqPs6SwJPl0/aTbq9IFpnaGblgcCYeK', '1997', 'February', '25', false, 'USER');


