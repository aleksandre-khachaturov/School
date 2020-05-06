<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->get('/userList', function (Request $request, Response $response, $args) {


    try {
        $stmt = $this->db->query("SELECT * FROM account");

        $tplVars['users'] = $stmt->execute();
        $users = $stmt->fetchAll();
        $tplVars['users'] = $users;
    } catch (Exception $e) {
        die("DB chyba" . $e->getMessage());
    }

    return $this->view->render($response, 'userList.latte', $tplVars);
})->setName('userList');
