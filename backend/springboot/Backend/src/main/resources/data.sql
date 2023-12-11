-- -- INSERT INTO product() VALUES (3, 'Sumsung S23', 'Electronics', 'Samsung S23 model 2023 made inn chin', 1200);
--
--
INSERT INTO product (name, type, description, price) VALUES
                                                         ('samsung s23', 'Electronics', 'Smartphone with advanced features', 499.99),
                                                         ('Airmax', 'Clothing', 'Mens casual t-shirt', 29.99),
    ('coffee maker', 'Home Appliances', 'Coffee maker with built-in grinder', 149.99),
    ('SmartWatch Pro', 'Electronics', 'Advanced smartwatch with fitness tracking', 199.99),
                                                                                      ('UltraHD TV', 'Electronics', '65-inch 4K UltraHD Smart TV', 899.99),
                                                                                      ('Leather Jacket', 'Clothing', 'Genuine leather jacket for men', 159.99),
                                                                                      ('Running Shoes', 'Footwear', 'High-performance running shoes', 79.99),
                                                                                      ('Blender Deluxe', 'Home Appliances', 'Powerful blender with multiple settings', 129.99),
                                                                                      ('Designer Sunglasses', 'Accessories', 'Premium designer sunglasses', 149.99),
                                                                                      ('Gaming Laptop', 'Electronics', 'High-end gaming laptop with RGB lighting', 1499.99),
                                                                                      ('Winter Coat', 'Clothing', 'Warm winter coat with faux fur hood', 119.99),
                                                                                      ('Espresso Machine', 'Home Appliances', 'Espresso machine with milk frother', 249.99),
                                                                                      ('Fitness Tracker', 'Electronics', 'Water-resistant fitness tracker with heart rate monitor', 79.99),
                                                                                      ('Denim Jeans', 'Clothing', 'Classic denim jeans for men', 49.99),
                                                                                      ('Wireless Earbuds', 'Electronics', 'True wireless earbuds with noise cancellation', 129.99),
                                                                                      ('Yoga Mat', 'Fitness', 'Non-slip yoga mat for home workouts', 29.99),
                                                                                      ('Duffle Bag', 'Accessories', 'Spacious duffle bag for travel and gym', 69.99),
                                                                                      ('Robot Vacuum', 'Home Appliances', 'Smart robot vacuum with mapping technology', 299.99),
                                                                                      ('Leather Wallet', 'Accessories', 'Handcrafted leather wallet with RFID protection', 59.99),
                                                                                      ('Cordless Drill', 'Tools', 'Powerful cordless drill for DIY projects', 79.99),
                                                                                      ('Graphic T-shirt', 'Clothing', 'Cool graphic-print t-shirt for casual wear', 24.99),
                                                                                      ('Portable Speaker', 'Electronics', 'Compact portable speaker with Bluetooth', 49.99),
                                                                                      ('Desk Organizer', 'Home Office', 'Multifunctional desk organizer with compartments', 34.99);

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
