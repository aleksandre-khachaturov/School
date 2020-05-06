<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;



$app->get('/login', function (Request $request, Response $response, $args) {
    $this->view->render($response, 'login.latte');
})->setName('login');




$app->post('/login', function (Request $request, Response $response, $args) {
    $data = $request->getParsedBody();
    print_r($data);
    //overeni osoby [login] [password]
    try {
        $stmt = $this->db->prepare('SELECT * FROM account WHERE login = :l');
        $stmt->bindValue(':l', $data['login']);
        $stmt->execute();
        $acc = $stmt->fetch();

        if ($acc) {

            if (password_verify($data['pass'], $acc['password'])) {
                $_SESSION['user'] = $acc;
                return $response->withHeader('Location', $this->router->pathFor('index'));
            }
        }

        return $response->withHeader('Location', $this->router->pathFor('login'));
    } catch (Exception $ex) {

        exit($ex->getMessage());
        $this->logger->error($ex->getMessage());
    }
});


