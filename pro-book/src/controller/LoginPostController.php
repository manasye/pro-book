<?php
class LoginPostController implements ControllerInterface {
  public static function control(Request $request) {
    $db = new MarufDB();
    $user_id = $db->checkLogin($request->username, $request->password);
    $user_agent = $_SERVER['HTTP_USER_AGENT'];
    $ip_address = $_SERVER['REMOTE_ADDR'];
    if($user_id != -1) {
      $JKWToken = new JKWToken();
      $token = $JKWToken->generateJKWToken();
      if ($db->addToken($user_id, $token, $user_agent, $ip_address) == 1) {
        setcookie("token", $token, time() + (int)$_ENV['COOKIE_EXPIRED_TIME'], '/');
        header("Location: /");
        exit();
      } else {
        return '<h1>Failed</h1>';
      }
    } else {
      $template = new Template('src/view/login.php');
      return $template->render(True, False);
    }
  }
}
