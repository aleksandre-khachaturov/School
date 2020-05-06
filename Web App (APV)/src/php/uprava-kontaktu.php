<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->get('/edit-contact', function (Request $request, Response $response, $args) {
    $idPer = $request->getQueryParam('idPer'); //get ID osoby
    $id = $request->getQueryParam('id'); //get ID kontaktu
    if (empty($id)) {
        exit('Nezadano ID kontaktu');
    }
    if (empty($idPer)) {
        exit('Nezadano ID osoby');
    }
    try {
        $stmt0 = $this->db->query('SELECT * FROM contact_type');
        $tplVars['contactType'] = $stmt0->fetchAll();
        $stmt0->execute();
    } catch (PDOException $e) {
        $this->logger->error($e->getMessage());
        exit($e->getMessage());
    }

    try {
      
        $stmt1 = $this->db->prepare('SELECT * FROM contact '
                . 'JOIN contact_type USING(id_contact_type)'
                . 'WHERE id_contact = :id');
        $stmt1->bindValue(':id', $id);
        $stmt1->execute();
        $tplVars['kontakt'] = $stmt1->fetch();

        return $this->view->render(
                        $response, 'edit-contact.latte', $tplVars
        );
    } catch (PDOException $ex) {
        $this->logger->error($ex->getMessage());
        exit($ex->getMessage());
    }
})->setName('editCon');



$app->post('/edit-contact', function (Request $request, Response $response, $args) {
    $id = $request->getQueryParam('id');
    $idPer = $request->getQueryParam('idPer');
    if (empty($id)) {
        exit('Nezadano ID kontaktu');
    }
    if (empty($idPer)) {
        exit('Nezadano ID osoby');
    }

    $data = $request->getParsedBody();

    try {

        $stmt = $this->db->prepare('UPDATE contact SET contact = :con, id_contact_type = :conTy WHERE id_contact = :id_kont');

        $stmt->bindValue(':con', $data['con']);
        $stmt->bindValue(':conTy', $data['conTy']);
        $stmt->bindValue(':id_kont', $id);
        $stmt->execute();


        return $response->withHeader('Location', "./details-person?id=$idPer");
    } catch (PDOException $ex) {
        $this->logger->error($ex->getMessage());
        exit($ex->getMessage());
    }

    $tplVars['kontakt'] = $data;
    return $this->view->render($response, 'edit-contact.latte', $tplVars);
});
