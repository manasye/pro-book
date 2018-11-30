<img src="pro-book/docs/logo.png">

Pro-Book
&middot;
[![GitLab license](https://img.shields.io/github/license/Day8/re-frame.svg)](LICENSE)
=====
> A book is both a usually portable physical object and the body of immaterial representations or intellectual object whose material signs—written or drawn lines or other two-dimensional media—the physical object contains or houses. Pro-Book is an online book store that allows user to buy books and give reviews to their purchased books.

## Table Of Contents
- [Introduction](#introduction)
- [Installation](#installation)
- [Run Pro-Book](#run-pro-book)
- [Features](#features)
- [Implemented Libraries](#implemented-libraries)
- [API Documentation](#api-Documentation)
- [Authors](#authors)
- [Words From Authors](#words-from-authors)
- [References](#references)

## Introduction
This is an implementation of a web-based online book store using **PHP**.

## Requirements
To run Pro-Book, we need to install certain libraries and packages
1. PHP
2. PHP-curl
3. npm
4. tmux
5. MySQL
6. perjava2an


## Installation
In order to run this web on your local server, you need to run it on **PHP 7.1** and install:

1. PHP 7.1
```
$ apt-get install php7.1
```
2. PDO Extension
```
$ apt-get install php7.1-pdo-mysql
```
3. mysql
```
$ apt-get install mysql
$ apt-get install mysql-server
```

## Pre-Running
Before we run the Pro-Book, we have to setup every `.env` file and database instances.

To initialize the `.env` file,
```
$ cp bank-ws/env.sample bank-ws/.env          # Modify .env file with your own setting
$ cp pro-book/ethes.sample pro-book/.ethes  # Modify .ethes file with your own setting
$ cp book-ws/env.sample book-ws/.env  # Modify .env file with your own setting
```

To initialize the database,
```
$ chmod +x setup.sh
$ ./setup.sh
```

## Run Pro-Book
Run this command on your terminal
```
$ chmod +x run.sh
$ ./run.sh
```
Pro-Book will be serving in `localhost:5000`, book-ws will be serving in `localhost:8080`, bank-ws will be serving in `localhost:9000`

## Features
- **Browse** : You can search any books that you want and view its detailed information that contains title, author, synopsis, cover, ratings, and list of reviews.
- **Order** : When you are on the book's detail page, you can order the book by choosing the amount of copies you want and click on the Order button.
- **History** : You can see your history of orders.
- **Review** : On your history page, you can click the **review** button in order to give a review about your purchased books. You can also give a rating and comment about the book.
- **View Profile** : You can view your profile details on the profile page.
- **Edit Profile** : On the profile page, you can click the **edit** button and change some personal information about you. You are allowed to change your profile picture, name, address, and phone number.

## API Documentation
#### Bank Service
***User Validation***
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

***Generate QR Code Secret***
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

***Make Transaction***
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
Book service is a service to get book details, get book recommendations, and make book transactions. Book service utilize Google Book service to fetch book info. Book service is implemented using JAX-WS. You can use your preferred implementation of soap client. In this documentation, soapClient in PHP will be used for demonstration purposes.  To initialize the soap client in PHP, insert code below.
```php
$soapClient = new SoapClient(<BOOK_API_WSDL_URL>);
```

***Search Book By Title***
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

***Search Book By Book Id***
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

***Get Book Recommendation By Categories***
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

***Buy Book***
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

### Authors
1. Abram Perdanaputra - 13516083 - https://github.com/abrampers
2. Ahmad Izzan - 13516116 - https://github.com/ahmadizzan
3. Manasye Bukit - 13516122 - https://github.com/manasye

## Words from Authors
Thanks to our lovely lecturer Mr. Dr.Techn. Muhammad Zuhri Catur Candra ST,MT for his amazing project about [Tugas 1 IF3110 Pengembangan Aplikasi Berbasis Web](http://gitlab.informatika.org/IF3110-2018/tugasbesar1_2018).
> *"You already know how hard it is to build a software right? Do you still want to use pirated softwares?"*
> *- Dr.Techn. Muhammad Zuhri Catur Candra ST,MT*

## References
* [Pro-Book v1](https://github.com/abrampers/PRO-Book)
* [Tugas 1 IF3110 Pengembangan Aplikasi Berbasis Web](http://gitlab.informatika.org/IF3110-2018/tugasbesar1_2018)
* [Flaticon](https://flaticon.com)
