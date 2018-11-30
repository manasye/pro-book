<?php

$options = array(
  'cache_wsdl'=>WSDL_CACHE_NONE
);

$soapClient = new SoapClient("http://localhost:8080/ws/book?wsdl", $options);

// https://www.googleapis.com/books/v1/volumes/sIENKoFHwdgC
var_dump($soapClient->searchDetail('sIENKoFHwdgC'));

// var_dump($soapClient->getBookRecommendation('Crafts & Hobbies / Quilts & Quilting'));

// var_dump($soapClient->getBookRecommendation('KckVORnmg40C'));

// $categories = array('Children Book', 'Crafts & Hobbies / Quilts & Quilting');
// $parameters = array('4111111111111114', '123', '0ENFDwAAQBAJ', 2);

// var_dump($soapClient->getBookRecommendation($categories));

// var_dump($soapClient->buyBook('4111111111111111', '123', '0ENFDwAAQBAJ', 1));
