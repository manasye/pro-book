<?php

$options = array(
  'cache_wsdl'=>WSDL_CACHE_NONE
);

$soapClient = new SoapClient("http://localhost:9999/ws/book?wsdl", $options);

$bookDetail = $soapClient->searchDetail('iJrS9blx6fIC');

var_dump($bookDetail);
