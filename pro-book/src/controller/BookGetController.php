<?php
class BookGetController implements ControllerInterface {
  public static function control(Request $request) {
    $db = new MarufDB;
    $user = $db->getUser($_COOKIE['token']);
    $username = $user['username'];
    $userImg = $user['imageurl'];
    $options = array(
    'cache_wsdl'=>WSDL_CACHE_NONE
    );
    $soapClient = new SoapClient("http://{$_ENV['BOOK_HOST']}/ws/book?wsdl", $options);
    $book = $soapClient->searchDetail($request->id);
    $recommendation = $soapClient->getBookRecommendation(explode("/", $book->category));
    $reviews = $db->getReviews($book->id);
    $rating = $db->getRatings($book->id);
    $template = new Template('src/view/book.php');
    return $template->render($username, $userImg, $book, $reviews, $rating, $recommendation);
  }
}