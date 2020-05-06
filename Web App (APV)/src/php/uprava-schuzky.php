<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->get('/edit-meeting', function (Request $request, Response $response, $args) {
    $idPer = $request->getQueryParam('idPer'); //get ID osoby
    $id = $request->getQueryParam('id'); //get ID schuzkau
    if (empty($id)) {
        exit('Nezadano ID schuzkau');
    }
    if (empty($idPer)) {
        exit('Nezadano ID osoby');
    }
    try {
        $stmt1 = $this->db->query('SELECT * FROM location');
        $tplVars['location'] = $stmt1->fetchAll();
      
        $stmt2 = $this->db->prepare('SELECT * FROM meeting RIGHT JOIN person_meeting USING (id_meeting) WHERE id_meeting = :id');
        $stmt2->bindValue(':id', $id);
        $stmt2->execute();
        $tplVars['schuzka'] = $stmt2->fetch();

        return $this->view->render(
                        $response, 'edit-meeting.latte', $tplVars
        );
    } catch (PDOException $ex) {
        $this->logger->error($ex->getMessage());
        exit($ex->getMessage());
    }
})->setName('editMet');



$app->post('/edit-meeting', function (Request $request, Response $response, $args) {
    $id = $request->getQueryParam('id');
    $idPer = $request->getQueryParam('idPer');
    if (empty($id)) {
        exit('Nezadano ID schuzky');
    }
    if (empty($idPer)) {
        exit('Nezadano ID osoby');
    }

    $data = $request->getParsedBody();

    try {

        $stmt = $this->db->prepare('UPDATE meeting SET start = :start, description = :descr, id_location = :idLoc, duration=:dur 
                WHERE id_meeting = :id');
        $stmt->bindValue(':start', $data['start']);
        $stmt->bindValue(':descr', $data['descr']);
        $stmt->bindValue(':idLoc', $data['idLoc']);
        $stmt->bindValue(':dur', $data['dur']);
        $stmt->bindValue(':id', $id);
        $stmt->execute();


        return $response->withHeader('Location', "./details-person?id=$idPer");
    } catch (PDOException $ex) {
        $this->logger->error($ex->getMessage());
        exit($ex->getMessage());
    }

    $tplVars['schuzka'] = $data;
    return $this->view->render($response, 'edit-meeting.latte', $tplVars);
});
