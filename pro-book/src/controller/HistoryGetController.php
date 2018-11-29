<?php
class HistoryGetController implements ControllerInterface {
  private $results;
  public static function control(Request $request) {
    $template = new Template('src/view/history.php');
    $db = new MarufDB();

    $user = $db->getUser($_COOKIE['token']);
    $user_id = $user['id'];
    $orders = $db->getHistory($user_id);

    $options = array( 
    'cache_wsdl'=>WSDL_CACHE_NONE 
    ); 
    
    $results = array();
    foreach($orders as $key => $order) {
      $soapClient = new SoapClient("http://localhost:9999/ws/book?wsdl", $options); 
      $book = $soapClient->searchDetail($order['book_id']);

      $results[] = array(
        "order_id" => $order['order_id'],
        "user_id" => $order['user_id'],
        "is_review" => $order['is_review'],
        "book_id" => $order['book_id'],
        "amount" => $order['amount'],
        "order_timestamp" => $order['order_timestamp'],
        "title" => $book->title,
        "image_url" => $book->imageUrl
      );
    }

    return $template->render($user['username'], $results);
  }
}
