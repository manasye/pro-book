CREATE TABLE book (
    id VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO book VALUES ("VE0LqD085eMC", 10000);
INSERT INTO book VALUES ("iJrS9blx6fIC", 50000);
INSERT INTO book VALUES ("wVVv5X5QE-EC", 300000);

CREATE TABLE sold (
    id VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    count INT NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO sold VALUES ("VE0LqD085eMC", "Crafts & Hobbies / Quilts & Quilting", 100);
INSERT INTO sold VALUES ("iJrS9blx6fIC", "Other", 40);
INSERT INTO sold VALUES ("wVVv5X5QE", "Thriller", 60);
