<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->post('/delete-personMeet', function (Request $request, Response $response, $args) {
    $data = $request->getParsedBody();
    if (empty($data['id'])) {
        exit('Neni zadano ID schuzky');
    }
    if (empty($data['idPer'])) {
        exit('Neni zadano ID osoby');
    }
    $id=$data['id'];
    try {
        $stmt = $this->db->prepare("DELETE FROM person_meeting WHERE id_meeting = :id AND id_person = :idPer");
        $stmt->bindValue(':id', $data['id']);
        $stmt->bindValue(':idPer', $data['idPer']);
        
        $stmt->execute();
        
        return $response->withHeader("Location", "./details-schuzek?id=$id");
    } catch (Exception $ex) {
        $this->logger->error($ex->getMessage());
        exit($ex->getMessage());
    }
})->setName('deletePerson');