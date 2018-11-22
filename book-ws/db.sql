CREATE TABLE book (
    id INT NOT NULL,
    price INT NOT NULL,
    PRIMARY KEY (id)
)

INSERT INTO book VALUES (1, 1000);
INSERT INTO book VALUES (2, 400);
INSERT INTO book VALUES (3, 600);

CREATE TABLE sold (
    id INT NOT NULL,
    category VARCHAR(255) NOT NULL,
    count INT NOT NULL,
    PRIMARY KEY (id)
)

INSERT INTO sold VALUES (1, "Comedy", 100);
INSERT INTO sold VALUES (2, "Romance", 40);
INSERT INTO sold VALUES (3, "Thriller", 60);
