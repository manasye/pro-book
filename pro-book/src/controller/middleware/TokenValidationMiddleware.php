<?php
class TokenValidationMiddleware implements MiddlewareInterface {
  public function run(Request $request) {
    $token = $_COOKIE['token'];
    $db = new MarufDB();
    $user_agent = $_SERVER['HTTP_USER_AGENT'];
    $ip_address = $_SERVER['REMOTE_ADDR'];
    if ($db->checkToken($token, $user_agent, $ip_address) == 0) {
      setcookie('token', '', time() - 3600);
    }
    return True;
  }
}
