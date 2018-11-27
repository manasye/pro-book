<?php

$options = array(
  'cache_wsdl'=>WSDL_CACHE_NONE
);

$soapClient = new SoapClient("http://localhost:9999/ws/book?wsdl", $options);

// var_dump($bookDetail = $soapClient->searchDetail('iJrS9blx6fIC');

// var_dump($soapClient->getBookRecommendation('Crafts & Hobbies / Quilts & Quilting'));

// var_dump($soapClient->getBookRecommendation('KckVORnmg40C'));

// $categories = array('Children Book', 'Crafts & Hobbies / Quilts & Quilting');
$categories = array('Others');

var_dump($soapClient->getBookRecommendation($categories));
