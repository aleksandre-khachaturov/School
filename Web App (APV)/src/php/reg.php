<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->get('/reg', function (Request $request, Response $response, $args) {
    $tplVars['user'] = [
        'login' => '',
        'heslo' => '',
        'heslo2' => ''
    ];
    return $this->view->render($response, 'reg.latte', $tplVars);
})->setName('reg');


$app->post('/reg', function (Request $request, Response $response, $args) {
    $data = $request->getParsedBody();
    try {
        //overuje spravnost hesla
        if ($data['heslo2'] == $data['heslo']) {
            // hesuje heslo
            $hesovaneHeslo = password_hash($data['heslo'], PASSWORD_DEFAULT);

            $stmt = $this->db->prepare('INSERT INTO account(login, password) VALUES(:login,:heslo)');
            $stmt->bindValue(':login', $data['login']);
            $stmt->bindValue(':heslo', $hesovaneHeslo);
            $stmt->execute();
            $acc = $stmt->fetch();
        }

        if ($acc) {
            if (password_verify($data['pass'], $acc['password'])) {
                // overeno
                $_SESSION['user'] = $acc;
                return $response->withHeader('Location', $this->router->pathFor('index'));
            }
        }
        return $response->withHeader('Location', $this->router->pathFor('login'));
    } catch (PDOException $e) {
        $this->logger->error($e->getMessage());
        exit($e->getMessage());
    }

    $tplVars['user'] = $data;
});
