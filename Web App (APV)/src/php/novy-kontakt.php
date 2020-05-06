<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->get('/new-contact', function (Request $request, Response $response, $args) {

    $tplVars['nContact'] = [
        'conTy' => '',
        'con' => ''
    ];

    try {
        $stmt = $this->db->query('SELECT * FROM contact_type');
        $tplVars['contactType'] = $stmt->fetchAll();
        $stmt->execute();
    } catch (PDOException $e) {
        $this->logger->error($e->getMessage());
        exit($e->getMessage());
    }

    return $this->view->render($response, 'new-contact.latte', $tplVars);
})->setName('addKontakt');

$app->post('/new-contact', function (Request $request, Response $response, $args) {
    $IdOsoby = $request->getQueryParam('id');
    if (empty($IdOsoby)) {
            exit("Chybí parametr ID");
        }
    $data = $request->getParsedBody();

    try {

        $stmt1 = $this->db->prepare('INSERT INTO contact (id_contact_type, id_person, contact) '
                . 'VALUES (:conTy, :idOs,  :con)');
        $stmt1->bindValue(':conTy', $data['conTy']);
        $stmt1->bindValue(':idOs', $IdOsoby);
        $stmt1->bindValue(':con', $data['con']);
        $stmt1->execute();

        return $response->withHeader("Location", "./details-person?id=$IdOsoby");
    } catch (PDOException $ex) {
        $this->logger->error($ex->getMessage());
        exit($ex->getMessage());
    }

    $tplVars['nContact'] = $data;
    return $this->view->render($response, 'new-contact.latte', $tplVars);
});

