CREATE TABLE book (
    id VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    category VARCHAR(255) NOT NULL,
    sold INT NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO book VALUES ("NP1wDwAAQBAJ", 411366, "Computers", 780);
INSERT INTO book VALUES ("yc2YZFRjcAQC", 30000, "Others", 410);
INSERT INTO book VALUES ("FkBPDwAAQBAJ", 274206, "Computers", 0);
INSERT INTO book VALUES ("uVVSDwAAQBAJ", 42243, "Political Science", 790);
INSERT INTO book VALUES ("-dzJDAAAQBAJ", 319926, "Computers", 780);
INSERT INTO book VALUES ("MZRRDwAAQBAJ", 365646, "Computers", 700);
INSERT INTO book VALUES ("vSLlCwAAQBAJ", 274206, "Computers", 990);
INSERT INTO book VALUES ("Ht3JDAAAQBAJ", 411366, "Computers", 320);
INSERT INTO book VALUES ("YJZGDwAAQBAJ", 411366, "Computers", 630);
INSERT INTO book VALUES ("aPWsDAAAQBAJ", 140000, "Computers", 500);
INSERT INTO book VALUES ("VE0LqD085eMC", 60000, "Children's stories", 130);
INSERT INTO book VALUES ("iQmPNDIAskUC", 130000, "Children's stories", 390);
INSERT INTO book VALUES ("0ENFDwAAQBAJ", 85000, "Juvenile Fiction", 90);
INSERT INTO book VALUES ("Aaug_RnI-xQC", 40000, "Literary Criticism", 310);
INSERT INTO book VALUES ("iO5pApw2JycC", 30000, "Literary Criticism", 130);
INSERT INTO book VALUES ("OyB4llvAoXQC", 50000, "Adventure stories", 80);
INSERT INTO book VALUES ("DKcWE3WXoj8C", 30000, "Literary Criticism", 140);
INSERT INTO book VALUES ("xVBbEzRjBZoC", 30000, "Children's stories", 540);
INSERT INTO book VALUES ("Mr48DwAAQBAJ", 75000, "Juvenile Fiction", 990);
INSERT INTO book VALUES ("WV8pZj_oNBwC", 200074, "Juvenile Nonfiction", 220);
INSERT INTO book VALUES ("4Jo9DwAAQBAJ", 54000, "Others", 0);
INSERT INTO book VALUES ("DoJQAAAAMAAJ", 40000, "Computers", 0);
INSERT INTO book VALUES ("bL7QZHtWvaUC", 110000, "Computers", 30);
INSERT INTO book VALUES ("3Ntz-UJzZN0C", 40000, "Computers", 0);
INSERT INTO book VALUES ("QXojiwovK7oC", 1183228, "Computers", 920);
INSERT INTO book VALUES ("ZoF06z4dhQ4C", 80000, "Computers", 20);
INSERT INTO book VALUES ("CAlM6nNPcsgC", 2295982, "Computers", 330);
INSERT INTO book VALUES ("ttaMIFv8bv8C", 110000, "Computers", 0);
INSERT INTO book VALUES ("li5QDAAAQBAJ", 64905, "Computers", 0);
INSERT INTO book VALUES ("YnGz2ghKF-gC", 30000, "Software engineering", 420);
INSERT INTO book VALUES ("sIENKoFHwdgC", 60000, "Others", 20);
INSERT INTO book VALUES ("7_zn6VwOnJcC", 100000, "Others", 270);
INSERT INTO book VALUES ("cdCMDgAAQBAJ", 68500, "Technology & Engineering", 50);
INSERT INTO book VALUES ("wQEjrc7Qrj8C", 80000, "Others", 0);
INSERT INTO book VALUES ("0w2Q6swN5BsC", 90000, "Others", 0);
INSERT INTO book VALUES ("aFmjOlog120C", 120000, "Others", 440);
INSERT INTO book VALUES ("xVStCQAAQBAJ", 45000, "Others", 0);
INSERT INTO book VALUES ("9aYEBwAAQBAJ", 82000, "Others", 280);
INSERT INTO book VALUES ("SEFjcIpP20wC", 50000, "Others", 630);
INSERT INTO book VALUES ("vz-LEFRT8E0C", 60000, "Catfishes", 780);


-- CREATE TABLE book (
--     id VARCHAR(255) NOT NULL,
--     price INT NOT NULL,
--     PRIMARY KEY (id)
-- );

-- INSERT INTO book VALUES ("NP1wDwAAQBAJ", 411366);
-- INSERT INTO book VALUES ("yc2YZFRjcAQC", 110000);
-- INSERT INTO book VALUES ("FkBPDwAAQBAJ", 274206);
-- INSERT INTO book VALUES ("uVVSDwAAQBAJ", 42243);
-- INSERT INTO book VALUES ("-dzJDAAAQBAJ", 319926);
-- INSERT INTO book VALUES ("MZRRDwAAQBAJ", 365646);
-- INSERT INTO book VALUES ("vSLlCwAAQBAJ", 274206);
-- INSERT INTO book VALUES ("Ht3JDAAAQBAJ", 411366);
-- INSERT INTO book VALUES ("YJZGDwAAQBAJ", 411366);
-- INSERT INTO book VALUES ("aPWsDAAAQBAJ", 100000);
-- INSERT INTO book VALUES ("VE0LqD085eMC", 40000);
-- INSERT INTO book VALUES ("iQmPNDIAskUC", 140000);
-- INSERT INTO book VALUES ("Aaug_RnI-xQC", 100000);
-- INSERT INTO book VALUES ("iO5pApw2JycC", 90000);
-- INSERT INTO book VALUES ("0ENFDwAAQBAJ", 85000);
-- INSERT INTO book VALUES ("OyB4llvAoXQC", 110000);
-- INSERT INTO book VALUES ("DKcWE3WXoj8C", 80000);
-- INSERT INTO book VALUES ("WV8pZj_oNBwC", 200074);
-- INSERT INTO book VALUES ("LiWrXUHgnL8C", 140000);
-- INSERT INTO book VALUES ("szF_pLGmJTQC", 138371);
-- INSERT INTO book VALUES ("4Jo9DwAAQBAJ", 54000);
-- INSERT INTO book VALUES ("DoJQAAAAMAAJ", 30000);
-- INSERT INTO book VALUES ("bL7QZHtWvaUC", 80000);
-- INSERT INTO book VALUES ("3Ntz-UJzZN0C", 40000);
-- INSERT INTO book VALUES ("GaRbZtWShxkC", 40000);
-- INSERT INTO book VALUES ("QXojiwovK7oC", 1183228);
-- INSERT INTO book VALUES ("ZoF06z4dhQ4C", 60000);
-- INSERT INTO book VALUES ("CAlM6nNPcsgC", 2295982);
-- INSERT INTO book VALUES ("ttaMIFv8bv8C", 50000);
-- INSERT INTO book VALUES ("li5QDAAAQBAJ", 64905);
-- INSERT INTO book VALUES ("sIENKoFHwdgC", 40000);
-- INSERT INTO book VALUES ("7_zn6VwOnJcC", 140000);
-- INSERT INTO book VALUES ("cdCMDgAAQBAJ", 68500);
-- INSERT INTO book VALUES ("wQEjrc7Qrj8C", 70000);
-- INSERT INTO book VALUES ("0w2Q6swN5BsC", 30000);
-- INSERT INTO book VALUES ("aFmjOlog120C", 100000);
-- INSERT INTO book VALUES ("il09DwAAQBAJ", 45000);
-- INSERT INTO book VALUES ("9aYEBwAAQBAJ", 82000);
-- INSERT INTO book VALUES ("SEFjcIpP20wC", 60000);
-- INSERT INTO book VALUES ("vz-LEFRT8E0C", 60000);

-- CREATE TABLE sold (
--     id VARCHAR(255) NOT NULL,
--     category VARCHAR(255) NOT NULL,
--     count INT NOT NULL,
--     PRIMARY KEY (id)
-- );

-- INSERT INTO sold VALUES ("yc2YZFRjcAQC", "Others", 740);
-- INSERT INTO sold VALUES ("FkBPDwAAQBAJ", "Computers", 930);
-- INSERT INTO sold VALUES ("uVVSDwAAQBAJ", "Political Science", 230);
-- INSERT INTO sold VALUES ("-dzJDAAAQBAJ", "Computers", 420);
-- INSERT INTO sold VALUES ("vSLlCwAAQBAJ", "Computers", 490);
-- INSERT INTO sold VALUES ("Ht3JDAAAQBAJ", "Computers", 290);
-- INSERT INTO sold VALUES ("iQmPNDIAskUC", "Children's stories", 770);
-- INSERT INTO sold VALUES ("Aaug_RnI-xQC", "Literary Criticism", 340);
-- INSERT INTO sold VALUES ("OyB4llvAoXQC", "Adventure stories", 20);
-- INSERT INTO sold VALUES ("DKcWE3WXoj8C", "Literary Criticism", 250);
-- INSERT INTO sold VALUES ("WV8pZj_oNBwC", "Juvenile Nonfiction", 70);
-- INSERT INTO sold VALUES ("szF_pLGmJTQC", "Children", 370);
-- INSERT INTO sold VALUES ("DoJQAAAAMAAJ", "Computers", 980);
-- INSERT INTO sold VALUES ("bL7QZHtWvaUC", "Computers", 890);
-- INSERT INTO sold VALUES ("3Ntz-UJzZN0C", "Computers", 630);
-- INSERT INTO sold VALUES ("GaRbZtWShxkC", "Others", 760);
-- INSERT INTO sold VALUES ("QXojiwovK7oC", "Computers", 920);
-- INSERT INTO sold VALUES ("ZoF06z4dhQ4C", "Computers", 420);
-- INSERT INTO sold VALUES ("ttaMIFv8bv8C", "Computers", 780);
-- INSERT INTO sold VALUES ("li5QDAAAQBAJ", "Computers", 900);
-- INSERT INTO sold VALUES ("sIENKoFHwdgC", "Others", 480);
-- INSERT INTO sold VALUES ("7_zn6VwOnJcC", "Others", 470);
-- INSERT INTO sold VALUES ("cdCMDgAAQBAJ", "Technology & Engineering", 120);
-- INSERT INTO sold VALUES ("wQEjrc7Qrj8C", "Others", 590);
-- INSERT INTO sold VALUES ("aFmjOlog120C", "Others", 370);
-- INSERT INTO sold VALUES ("il09DwAAQBAJ", "Others", 100);
-- INSERT INTO sold VALUES ("9aYEBwAAQBAJ", "Others", 360);
-- INSERT INTO sold VALUES ("vz-LEFRT8E0C", "Catfishes", 410);
