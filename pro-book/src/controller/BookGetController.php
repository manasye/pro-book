<?php
class BookGetController implements ControllerInterface {
  public static function control(Request $request) {
    $db = new MarufDB;
    $username = $db->getUsername($_COOKIE['token']);
    $options = array( 
    'cache_wsdl'=>WSDL_CACHE_NONE 
    ); 
    $soapClient = new SoapClient("http://{$_ENV['BANK_HOST']}/ws/book?wsdl", $options); 
    $book = $soapClient->searchDetail($request->id);
    $recommendation = $soapClient->getBookRecommendation(explode("/", $book->category));
    // $recommendation = $soapClient->getBookRecommendation(['Other']);
    $reviews = $db->getReviews($book->id);
    $rating = $db->getRatings($book->id);
    $template = new Template('src/view/book.php');
    return $template->render($username, $book, $reviews, $rating, $recommendation);
  }
}