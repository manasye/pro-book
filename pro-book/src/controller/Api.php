<?php
class Api {
  public static function validateUsername(string $username) {
    $db = new MarufDB();
    return array('valid' => (bool) $db->validateUsername($username));
  }

  public static function validateEmail(string $email) {
    $db = new MarufDB();
    return array('valid' => (bool) $db->validateEmail($email));
  }

  public static function getBooksFromTitle(string $title) {
    $options = array( 
    'cache_wsdl'=>WSDL_CACHE_NONE 
    ); 

    $soapClient = new SoapClient("http://localhost:9999/ws/book?wsdl", $options); 

    return $soapClient->searchTitle($title);
  }

  public static function order(Request $request) {
    $db = new MarufDB();
    $bookId = $request->bookId;
    $userId = $db->getUserId($_COOKIE['token']);
    $quantity = $request->quantity;
    return array(
      'orderNumber' => $db->orderBook($bookId, $userId, $quantity, time())
    );
  }
}
