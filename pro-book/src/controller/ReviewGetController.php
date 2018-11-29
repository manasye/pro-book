<?php
class ReviewGetController implements ControllerInterface {
  public static function control(Request $request) {
    $options = array( 
      'cache_wsdl'=>WSDL_CACHE_NONE 
    ); 

    $db = new MarufDB();
    $soapClient = new SoapClient("http://{$_ENV['BOOK_HOST']}/ws/book?wsdl", $options); 

    $token = $_COOKIE['token'];
    $order_id = $request->id;
    $book_id = $db->getBookIdByOrderId($order_id);
    $book = $soapClient->searchDetail($book_id);
    $user_id = $db->getUserId($token);
    $username = $db->getUsername($token);
    $template = new Template('src/view/review.php');
    return $template->render($username, $book, $user_id, $order_id);
  }
}
