DROP TABLE IF EXISTS Payment;

CREATE TABLE Payment (
          id INT AUTO_INCREMENT  PRIMARY KEY,
          id_dweller INT NOT NULL,
          date DATETIME NOT NULL,
          total DOUBLE NOT NULL,
          type VARCHAR(30) NOT NULL
);

INSERT INTO Payment (id_dweller, date, total, type) VALUES
(1, NOW(), 10, 'MONEY'),
(1, NOW(), 100, 'DEBIT_CARD'),
(2, NOW(), 40, 'CREDITOOLS'),
(3, NOW(), 21, 'CREDIT_CARD'),
(4, NOW(), 78, 'CREDIT_CARD');

DROP TABLE IF EXISTS Reward;

CREATE TABLE Reward (
         id INT AUTO_INCREMENT  PRIMARY KEY,
         id_dweller INT NOT NULL,
         date DATETIME NOT NULL,
         value DOUBLE NOT NULL
);

INSERT INTO Reward (id_dweller, date, value) VALUES
(1, NOW(), 10),
(1, NOW(), 7),
(2, NOW(), 74),
(3, NOW(), 100),
(4, NOW(), 100);
