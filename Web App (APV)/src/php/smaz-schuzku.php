<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->post('/delete-meeting', function (Request $request, Response $response, $args) {
    $data = $request->getParsedBody();
    if (empty($data['id'])) {
        exit('Neni zadano ID schuzky');
    }
    $id=$data['idPer'];
    try {
        $stmt = $this->db->prepare("DELETE FROM person_meeting WHERE id_meeting = :id");
        $stmt->bindValue(':id', $data['id']);
        $stmt->execute();
        return $response->withHeader("Location", "./details-person?id=$id");
    } catch (Exception $ex) {
        $this->logger->error($ex->getMessage());
        exit($ex->getMessage());
    }
})->setName('deleteMet');