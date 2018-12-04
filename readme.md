<img src="pro-book/docs/logo.png">

Pro-Book
&middot;
[![GitLab license](https://img.shields.io/github/license/Day8/re-frame.svg)](LICENSE)
=====

> A book is both a usually portable physical object and the body of immaterial representations or intellectual object whose material signs—written or drawn lines or other two-dimensional media—the physical object contains or houses. Pro-Book is an online book store that allows user to buy books and give reviews to their purchased books.

## Table Of Contents

-   [Introduction](#introduction)
-   [Installation](#installation)
-   [Run Pro-Book](#run-pro-book)
-   [Features](#features)
-   [Implemented Libraries](#implemented-libraries)
-   [API Documentation](#api-Documentation)
-   [Authors](#authors)
-   [Words From Authors](#words-from-authors)
-   [References](#references)

## Introduction

This is an implementation of a web-based online book store using **PHP**.

## Requirements

To run Pro-Book, we need to install certain libraries and packages

1. [PHP 7](http://php.net/manual/en/install.php)
2. [PHP-curl](https://www.digitalocean.com/community/questions/curl-is-not-installed-in-your-php-installation)
3. [PHP PDO Extension](http://php.net/manual/en/pdo.installation.php)
4. [Node.js](https://nodejs.org/en/download/package-manager/)
5. [tmux](https://github.com/tmux/tmux/wiki)
6. [MySQL](https://dev.mysql.com/doc/mysql-installation-excerpt/5.7/en/)
7. [Maven](https://maven.apache.org/install.html)
8. [Tomcat](http://www.ntu.edu.sg/home/ehchua/programming/howto/tomcat_howto.html)

## Installation

In order to run this web on your local server, you need to run it on **PHP 7.1** and install:

#### For MacOS users

Install Homebrew

```
$ /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```

1. **PHP 7.1**

MacOS

```
$ brew install php71
```

Ubuntu

```
$ apt-get install php7.1
```

Windows: Use installer tools

2. **PHP cURL**

MacOS: PHP cURL comes with default PHP

Ubuntu

```
$ sudo apt-get install php7.0-curl
```

Windows, go [here](https://www.codeooze.com/coding/php-curl-on-windows/)

3. **PDO Extension**

MacOS: PHP cURL comes with default PHP
Ubuntu

```
$ apt-get install php7.1-pdo-mysql
```

Windows, go [here](http://php.net/manual/en/pdo.installation.php)

4. **Node.js**

MacOS

```sh-session
$ brew install node
```

Ubuntu

```sh-session
$ curl -sL https://deb.nodesource.com/setup_11.x | sudo -E bash -
$ sudo apt-get install -y nodejs
```

Windows: Use windows installer

5. **MySQL**

MacOS

```
$ brew install mysql
```

Ubuntu

```
$ apt-get install mysql
$ apt-get install mysql-server
```

6. **Maven**, go [here](https://www.baeldung.com/install-maven-on-windows-linux-mac)

Ubuntu

```
$ sudo apt install maven
```

7. **Tomcat**

MacOS

```
$ brew install tomcat
```

Ubuntu, or you can go [here](https://linuxize.com/post/how-to-install-tomcat-8-5-on-ubuntu-18.04/)

## Pre-Running

Before we run the Pro-Book, go to tmux and we have to setup every `.env` file and database instances.

To initialize the `.env` file,

```
$ tmux
$ cp bank-ws/env.sample bank-ws/.env                                # Modify .env file with your own setting
$ cp pro-book/ethes.sample pro-book/.ethes                          # Modify .ethes file with your own setting
$ cp book-ws/src/main/envs/env.sample book-ws/src/main/envs/.env    # Modify .env file with your own setting
```

To initialize the database,

```sh-session
$ chmod +x setup.sh
$ ./setup.sh
```

Build the book-ws servlet and make sure `CATALINA_HOME` is pointing to tomcat correctly in `book-ws/src/main/envs`

```sh-session
$ cd book-ws
$ mvn clean compile war:war
```

## Run Pro-Book

Run this command on your terminal

```
$ chmod +x run.sh
$ ./run.sh
```

Pro-Book will be serving in `localhost:5000`, book-ws will be serving in `localhost:8080`, bank-ws will be serving in `localhost:9000`

## Features

-   **Browse** : You can search any books that you want and view its detailed information that contains title, author, synopsis, cover, ratings, and list of reviews.
-   **Order** : When you are on the book's detail page, you can order the book by choosing the amount of copies you want and click on the Order button.
-   **History** : You can see your history of orders.
-   **Review** : On your history page, you can click the **review** button in order to give a review about your purchased books. You can also give a rating and comment about the book.
-   **View Profile** : You can view your profile details on the profile page.
-   **Edit Profile** : On the profile page, you can click the **edit** button and change some personal information about you. You are allowed to change your profile picture, name, address, and phone number.

## API Documentation

#### Bank Service

**_User Validation_**

Check whether the given card number is registered in the bank.
Request:

```bash
GET /production/validate
POST /production/validate
{
    "cardNumber": card number of a user [string]
}
```

Response:

```
{
    "success": whether the user is valid [boolean],
    "message": response message [string],
    "account": {
        "id": user id [string],
        "name": user name [string],
        "cardNumber": user card number [string],
        "balance": user bank balance [number]
    } [if success == true]
}
```

**_Generate QR Code Secret_**

Generate QR code for a merchant. Uses authentication by checking the card number and merchant secret.
Request:

```
POST /production/validate
{
    cardNumber: card number of a user [string]
    merchantSecret: merchant secret [string]
}
```

Response:

```
{
    "success": whether qr code successfully generated [boolean],
    "message": response message [string],
    "qrCode": image [binary] [if success == true]
}
```

**_Make Transaction_**

Make transaction and checks whether the user's balance is enough to make a transaction with certain amount. Will transfer the transaction amount from user to merchant.
Request:

```bash
POST /production/validate
{
    cardNumber: card number of a user [string]
    merchantSecret: merchant secret [string]
    amount: amount of transaction in IDR [number]
}
```

Response:

```
{
    "success": whether transaction succeed [boolean],
    "message": response message [string],
}
```

#### Book Service

Book service is a service to get book details, get book recommendations, and make book transactions. Book service utilize Google Book service to fetch book info. Book service is implemented using JAX-WS. You can use your preferred implementation of soap client. In this documentation, soapClient in PHP will be used for demonstration purposes. To initialize the soap client in PHP, insert code below.

```php
$soapClient = new SoapClient(<BOOK_API_WSDL_URL>);
```

**_Search Book By Title_**

Search a book based on the title of the book.
Command

```php
$soapClient->searchTitle(<SEARCH_TERM>);
```

Response:

```
{
    "bookList": [
        {
            "id": book id [string],
            "title": book title [string],
            "author": book author [string],
            "category": book category [string],
            "description": book description [string],
            "imageUrl": book image url [string],
            "price": book price [number]
        },
        ...
    ]
}
```

**_Search Book By Book Id_**

Seach a book based on the id of the book.
Command

```php
$soapClient->searchDetail(<BOOK_ID>);
```

Response:

```
{
    "id": book id [string],
    "title": book title [string],
    "author": book author [string],
    "category": book category [string],
    "description": book description [string],
    "imageUrl": book image url [string],
    "price": book price [number]
}
```

**_Get Book Recommendation By Categories_**

Generate book recommendations based on the number of sold items in the same categories. If no book in the categories that have been bought, book service will return one random book in the same categories from the Google Book API.

Command

```php
$soapClient->getBookRecommendation(<CATEGORY_LIST>);
```

Response:

```
{
    "bookList": [
        {
            "id": book id [string],
            "title": book title [string],
            "author": book author [string],
            "category": book category [string],
            "description": book description [string],
            "imageUrl": book image url [string],
            "price": book price [number]
        },
        ...
    ]
}
```

**_Buy Book_**

Buy book with bank authentication based by communicating with bank service.

Command

```php
$soapClient->buyBook(<CARD_NUMBER>, <TOKEN>, <BOOK_ID>, <BOOK_QUANTITY>);
```

Response:

```
{
    "success": whether the transcation succeed [boolean],
    "message": response message [string]
}
```

## Explanations

### Database Schema

#### Probook

**_ActiveTokens_**

| Field                | Type         | Description           |
| -------------------- | ------------ | --------------------- |
| user_id              | INT(11)      | User ID (Primary Key) |
| token                | VARCHAR(300) | User Token            |
| user_agent           | VARCHAR(255) | User Agent            |
| ip_address           | VARCHAR(20)  | IP Address            |
| expiration_timestamp | BIGINT(20)   | Expiration Timestamp  |

**_Orders_**

| Field           | Type         | Description       |
| --------------- | ------------ | ----------------- |
| id              | INT(11)      | Order ID          |
| user_id         | INT(11)      | User ID           |
| is_review       | TINYINT(1)   | Is order reviewed |
| book_id         | VARCHAR(255) | Book ID           |
| amount          | INT(11)      | Amount            |
| order_timestamp | BIGINT(20)   | Order Timestamp   |

**_Ratings_**

| Field  | Type         | Description                          |
| ------ | ------------ | ------------------------------------ |
| id     | VARCHAR(255) | Book ID                              |
| rating | FLOAT        | Book Average Rating                  |
| vote   | INT(11)      | Number of User Ratings for This Book |

**_Reviews_**

| Field    | Type         | Description       |
| -------- | ------------ | ----------------- |
| id       | INT(11)      | Rating ID         |
| rating   | FLOAT        | Review Rating     |
| coment   | VARCHAR(500) | Review Comment    |
| book_id  | VARCHAR(20)  | Book ID           |
| username | VARCHAR(300) | Reviewer Username |
| user_id  | INT(11)      | Reviewer ID       |

**_Users_**

| Field       | Type         | Description              |
| ----------- | ------------ | ------------------------ |
| id          | INT(11)      | User ID                  |
| name        | VARCHAR(255) | User Name                |
| username    | VARCHAR(255) | User Username            |
| email       | VARCHAR(255) | User Email Address       |
| password    | VARCHAR(255) | User Password            |
| address     | VARCHAR(255) | User Address             |
| phonenumber | VARCHAR(255) | User Phone Number        |
| cardnumber  | VARCHAR(255) | User Card Number         |
| imageurl    | VARCHAR(255) | User Profile Picture URL |

#### Bank Service

**_customers_**

| Field      | Type   | Description          |
| ---------- | ------ | -------------------- |
| id         | INT    | Customer ID          |
| name       | STRING | Customer Name        |
| cardNumber | STRING | Customer Card Number |
| balance    | BIGINT | Customer Balance     |
| secret     | STRING | Customer Secret      |

**_merchants_**

| Field        | Type   | Description     |
| ------------ | ------ | --------------- |
| id           | INT    | Merchant ID     |
| ownerAccount | INT    | Owner User ID   |
| merchantName | STRING | Merchant Name   |
| secret       | STRING | Merchant Secret |

**_transactions_**

| Field           | Type   | Description        |
| --------------- | ------ | ------------------ |
| id              | INT    | Transaction ID     |
| senderAccount   | INT    | Sender User ID     |
| receiverAccount | INT    | Receiver User ID   |
| amount          | BIGINT | Transaction Amount |
| transactionTime | DATE   | Transaction Time   |

#### Book Service

**_book_**

| Field    | Type         | Description     |
| -------- | ------------ | --------------- |
| id       | VARCHAR(255) | Book ID         |
| price    | INT          | Book Price      |
| category | VARCHAR(255) | Book Category   |
| sold     | INT          | Book Sold Count |

### Token Creation And Expiry Mechanism

In this project, whenever user log in to the web, the app will generate the token and automatically calculates the expiry time and stores the IP Address, HTTP Client, and the expiry time of the token to web's database.
When user accesses the web, it will validate the token, IP Address, HTTP Client, and the expiry time of the token. If the token validates, the user will be redirected to /browse page, else client token will be deleted and the user will be redirected to /login page.

### Probook Architecture vs Monolith Architecture

Since this project uses microservice architecture, there must be a tradeoff in this architecture compared to the traditional monolith architecture. Here's the pros and cons of our architecture compared against monolith architecture.

**_Pros:_**

-   Microservice is decoupled so service isolation is easier to be done.
-   Microservice is easier to understand since there's less dependencies compared to Monolith.
-   Microservice allows parallel development.

**_Cons:_**

-   There's more operational overhead compared Monolith.
-   It's possible to have worse performance than monolith since monolith's shared memory is much faster than inter-process communication (IPC).

## Authors

1. Abram Perdanaputra - 13516083 - https://github.com/abrampers
2. Ahmad Izzan - 13516116 - https://github.com/ahmadizzan
3. Manasye Bukit - 13516122 - https://github.com/manasye
