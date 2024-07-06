CREATE TABLE IF NOT EXISTS Users (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(32) NOT NULL,
    password varchar(32) NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS Wallets (
    id int NOT NULL AUTO_INCREMENT,
    userId int NOT NULL,
    amount int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES Users(id)
);

CREATE TABLE IF NOT EXISTS Games (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(128) NOT NULL,
    price int NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Libraries (
    userId int NOT NULL,
    gameId int NOT NULL,
    PRIMARY KEY (userId, gameId),
    FOREIGN KEY (userId) REFERENCES Users(id),
    FOREIGN KEY (gameId) REFERENCES Games(id)
);

INSERT INTO Users (name, password) VALUES
    ('Admin', 'Admin2024!'),
    ('Bob', 'Bob#Secure123'),
    ('Charlie', 'Charlie!Password'),
    ('David', 'David&StrongPass'),
    ('Eve', 'Eve*SuperSecure'),
    ('Frank', 'Frank!Password123'),
    ('Grace', 'Grace@SecurePass'),
    ('Hank', 'Hank#StrongPass'),
    ('Ivy', 'Ivy!Secure2024'),
    ('Jack', 'Jack@Password123'),
    ('Karen', 'Karen&SecurePass');

INSERT INTO Wallets (userId, amount) VALUES
(1, 0),
(2, 31),
(3, 0),
(4, 154),
(5, 30),
(6, 50),
(7, 0),
(8, 250),
(9, 510),
(10, 100);

INSERT INTO Games (name, price) VALUES
   ('The Witcher 3: Wild Hunt', 60),
   ('Red Dead Redemption 2', 60),
   ('Grand Theft Auto V', 40),
   ('Fallout 4', 30),
   ('Cyberpunk 2077', 60),
   ('Assassin''s Creed Odyssey', 50),
   ('Dark Souls III', 50),
   ('Sekiro: Shadows Die Twice', 60),
   ('Resident Evil Village', 60),
   ('Monster Hunter: World', 40),
   ('Metal Gear Solid V: The Phantom Pain', 30),
   ('Far Cry 5', 50),
   ('Devil May Cry 5', 60),
   ('Nioh 2', 60),
   ('Watch Dogs 2', 40),
   ('Middle-earth: Shadow of Mordor', 30),
   ('Middle-earth: Shadow of War', 40),
   ('Dragon Age: Inquisition', 30),
   ('Mass Effect: Andromeda', 20),
   ('Horizon Zero Dawn', 40),
   ('Dishonored 2', 20),
   ('Bloodborne', 20),
   ('The Division 2', 30),
   ('Days Gone', 30),
   ('Ghost of Tsushima', 60),
   ('Control', 40),
   ('Death Stranding', 50),
   ('Borderlands 3', 50),
   ('Hitman 3', 60),
   ('Outriders', 60),
   ('The Outer Worlds', 40),
   ('Metro Exodus', 40),
   ('Dying Light', 30),
   ('Prey', 20),
   ('Mafia III', 20),
   ('Shadow Warrior 2', 20),
   ('Dead by Daylight', 20),
   ('Destiny 2', 30),
   ('Evolve', 20),
   ('Quantum Break', 20),
   ('Titanfall 2', 30),
   ('Just Cause 4', 30),
   ('Alien: Isolation', 30),
   ('Call of Duty: Black Ops III', 40),
   ('Call of Duty: Infinite Warfare', 40),
   ('Battlefield 1', 40),
   ('Battlefield V', 50),
   ('Titanfall', 20),
   ('Mad Max', 20),
   ('Wolfenstein II: The New Colossus', 40);

INSERT INTO Libraries (userId, gameId) VALUES
(2, 4),
(2, 5),
(2, 6),
(3, 7),
(3, 8),
(3, 9),
(4, 10),
(4, 11),
(4, 12),
(5, 13),
(5, 14),
(5, 15),
(6, 16),
(6, 17),
(6, 18),
(7, 19),
(7, 20),
(7, 21),
(8, 22),
(8, 23),
(8, 24),
(9, 25),
(9, 26),
(9, 27),
(10, 28),
(10, 29),
(10, 30),
(11, 1),
(11, 2),
(11, 3);