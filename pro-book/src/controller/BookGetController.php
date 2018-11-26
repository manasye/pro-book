<?php
class BookGetController implements ControllerInterface {
  public static function control(Request $request) {
    $db = new MarufDB;
    $username = $db->getUsername($_COOKIE['token']);
    $options = array( 
    'cache_wsdl'=>WSDL_CACHE_NONE 
    ); 
    $soapClient = new SoapClient("http://localhost:9999/ws/book?wsdl", $options); 
    $book = $soapClient->searchDetail($request->id);
    $recommendation = $soapClient->getBookRecommendation(explode("/", $book->category));
    $reviews = $db->getReviews($book->id);
    $template = new Template('src/view/book.php');
    return $template->render($username, $book, $reviews, $recommendation);
  }
}