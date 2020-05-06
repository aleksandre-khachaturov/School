<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->post('/delete-account', function (Request $request, Response $response, $args) {
    $data = $request->getParsedBody();
    if (empty($data['id'])) {
        exit('Neni zadano ID accountu');
    }
    try {
        $stmt = $this->db->prepare("DELETE FROM account WHERE id_account = :id");
        $stmt->bindValue(':id', $data['id']);
        $stmt->execute();
        return $response->withHeader('Location', '.');
    } catch (Exception $ex) {
        $this->logger->error($ex->getMessage());
        exit($ex->getMessage());
    }
})->setName('deleteAcc');
