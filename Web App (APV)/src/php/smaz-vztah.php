<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->post('/delete-relation', function (Request $request, Response $response, $args) {
    $data = $request->getParsedBody();
    if (empty($data['id'])) {
        exit('Neni zadano ID vztahu');
    }
    $id=$data['idPer'];
    try {
        $stmt = $this->db->prepare("DELETE FROM relation WHERE id_relation = :id");
        $stmt->bindValue(':id', $data['id']);
        $stmt->execute();
        return $response->withHeader("Location", "./details-person?id=$id");
    } catch (Exception $ex) {
        $this->logger->error($ex->getMessage());
        exit($ex->getMessage());
    }
})->setName('deleteRel');