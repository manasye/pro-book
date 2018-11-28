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
$ cp bankws/env.sample bankws/.env          # Modify .env file with your own setting
$ cp pro-book/ethes.sample pro-book/.ethes  # Modify .ethes file with your own setting
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
Pro-Book will be serving in `localhost:5000`, book-ws will be serving in `localhost:9999`, bank-ws will be serving in `localhost:9000`

## Features
- **Browse** : You can search any books that you want and view its detailed information that contains title, author, synopsis, cover, ratings, and list of reviews.
- **Order** : When you are on the book's detail page, you can order the book by choosing the amount of copies you want and click on the Order button.
- **History** : You can see your history of orders.
- **Review** : On your history page, you can click the **review** button in order to give a review about your purchased books. You can also give a rating and comment about the book.
- **View Profile** : You can view your profile details on the profile page.
- **Edit Profile** : On the profile page, you can click the **edit** button and change some personal information about you. You are allowed to change your profile picture, name, address, and phone number.

## API Documentation
huyuhuyuhuyuuyu TODO DIISI GAN

## Authors
1. Abram Perdanaputra - 13516083 - https://github.com/abrampers
2. Ahmad Izzan - 13516116 - https://github.com/ahmadizzan
3. Manasye Bukit - 13516119 - https://github.com/manasye

## Words from Authors
Thanks to our lovely lecturer Mr. Dr.Techn. Muhammad Zuhri Catur Candra ST,MT for his amazing project about [Tugas 1 IF3110 Pengembangan Aplikasi Berbasis Web](http://gitlab.informatika.org/IF3110-2018/tugasbesar1_2018).
> *"You already know how hard it is to build a software right? Do you still want to use pirated softwares?"*
> *- Dr.Techn. Muhammad Zuhri Catur Candra ST,MT*

## References
* [Pro-Book v1](https://github.com/abrampers/PRO-Book)
* [Tugas 1 IF3110 Pengembangan Aplikasi Berbasis Web](http://gitlab.informatika.org/IF3110-2018/tugasbesar1_2018)
* [Flaticon](https://flaticon.com)
