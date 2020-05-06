<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->post('/delete-contact', function (Request $request, Response $response, $args) {
    $data = $request->getParsedBody();
    if (empty($data['id'])) {
        exit('Neni zadano ID kontaktu');
    }
    $id=$data['idPer'];
    try {
        $stmt = $this->db->prepare("DELETE FROM contact WHERE id_contact = :id");
        $stmt->bindValue(':id', $data['id']);
        $stmt->execute();
        return $response->withHeader("Location", "./details-person?id=$id");
    } catch (Exception $ex) {
        $this->logger->error($ex->getMessage());
        exit($ex->getMessage());
    }
})->setName('deleteCon');
