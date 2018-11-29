<?php
class ProfileGetController implements ControllerInterface {
  public static function control(Request $request) {
    $template = new Template('src/view/profile.php');
    $db = new MarufDB;
    $user = $db->getUser($_COOKIE['token']);
    if ($user['imageurl']) {
      return $template->render($user['id'], $user['name'], $user['username'], $user['email'], $user['address'], $user['phonenumber'], $user['cardnumber'], $user['imageurl']);
    }
    return $template->render($user['id'], $user['name'], $user['username'], $user['email'], $user['address'], $user['phonenumber'], $user['cardnumber'], '');
  }
}