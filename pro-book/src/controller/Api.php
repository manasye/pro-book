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
  
  public static function validateCardNumber(string $cardNumber) {
    $data = json_encode(array('cardNumber' => $cardNumber));
    $curl = curl_init();
    $url = "http://{$_ENV['BANK_HOST']}/production/validate";
    curl_setopt_array($curl, array(
      CURLOPT_URL => $url,
      CURLOPT_RETURNTRANSFER => 1,
      CURLOPT_POST => 1,
      CURLOPT_POSTFIELDS => $data,
      CURLOPT_HTTPHEADER => array(                                                                          
        'Content-Type: application/json',                                                                                
        'Content-Length: ' . strlen($data))                                                                       
      )
    );
    $exec = curl_exec($curl);
    $result = json_decode($exec);
    curl_close($curl);
    return $result;
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
