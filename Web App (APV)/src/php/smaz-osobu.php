<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->post('/delete-person', function (Request $request, Response $response, $args) {
    $data = $request->getParsedBody();
    if (empty($data['id'])) {
        exit('Neni zadano ID osoby');
    }
    try {
        $stmt = $this->db->prepare("DELETE FROM person WHERE id_person = :id");
        $stmt->bindValue(':id', $data['id']);
        $stmt->execute();
        return $response->withHeader('Location', $this->router->pathFor('index'));
    } catch (Exception $ex) {
        $this->logger->error($ex->getMessage());
        exit($ex->getMessage());
    }
})->setName('delete');
