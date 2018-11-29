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

  public static function getSecretImage(string $cardNumber) {
    $data = json_encode(array('cardNumber' => $cardNumber, 'merchantSecret' => "JokowiMaruf2019"));
    $curl = curl_init();
    $url = "http://{$_ENV['BANK_HOST']}/production/secret";
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

  // public static function order(Request $request) {
  //   $db = new MarufDB();
  //   $bookId = $request->bookid;
  //   $userId = $db->getUserId($_COOKIE['token']);
  //   $amount = $request->amount;
  //   return array(
  //     'orderNumber' => $db->orderBook($bookId, $userId, $amount, time())
  //   );
  // }

  public static function buyBook($token, $bookId, $amount) {
    $db = new MarufDB();
    $user = $db->getUser($_COOKIE['token']);
    $cardNumber = $user['cardnumber'];
    $orderId = $db->orderBook($bookId, $user['id'], $amount, time());

    $options = array(
      'cache_wsdl'=>WSDL_CACHE_NONE
    );
    
    $soapClient = new SoapClient("http://localhost:9999/ws/book?wsdl", $options);
    $res = $soapClient->buyBook($cardNumber, $token, $bookId, $amount);
    if ($res->success) {
      return $res;
    } else {
      $db->deleteOrder($orderId);
      return array(
        "success" => false,
        "message" => "Failed to buy book"
      );
    }
  }

  public static function googleLogin($id, $name, $username, $image, $email) {
    $db = new MarufDB();
    $emailExist = $db->checkEmailExist($email);

    if ($emailExist == 1) {

      // If email exist, login with that user

      $user_id = $db->getUserIdFromEmail($email);
      $user_agent = $_SERVER['HTTP_USER_AGENT'];
      $ip_address = $_SERVER['REMOTE_ADDR'];
      $JKWToken = new JKWToken();
      $token = $JKWToken->generateJKWToken();
      if ($db->addToken($user_id, $token, $user_agent, $ip_address) == 1) {
        setcookie("token", $token, time() + (int)$_ENV['COOKIE_EXPIRED_TIME'], '/');
        return array('success' => true);
      } else {
        return array('success' => false, 'message' => 'Token is not matched');
      }

    } else {

      // If not exist, register with that email, and login with it

      $result = $db->addProfile($name, $username, $email, 'Not assigned', 'Not assigned', 
      'Not assigned', 'Not assigned');

      if ($result == 1) {
        $user_id = $db->getUserIdFromEmail($email);
        $user_agent = $_SERVER['HTTP_USER_AGENT'];
        $ip_address = $_SERVER['REMOTE_ADDR'];
        $JKWToken = new JKWToken();
        $token = $JKWToken->generateJKWToken();
        if ($db->addToken($user_id, $token, $user_agent, $ip_address) == 1) {
          setcookie("token", $token, time() + (int)$_ENV['COOKIE_EXPIRED_TIME'], '/');
          return array('success' => true);
        } else {
          return array('success' => false, 'message' => 'Token is not matched');
        }
      } else {
        return array("success" => false, 'message' =>"An error has occured");
      }

    }

    return "An error has occured";
  }

}