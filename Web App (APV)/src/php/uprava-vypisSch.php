<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->get('/uprava-vypisSch', function (Request $request, Response $response, $args) {
  $id = $request->getQueryParam('id'); 
    if (empty($id)) {
        exit('Nezadano ID schuzkau');
    }
    try {
        $stmt1 = $this->db->query('SELECT * FROM location ORDER BY city');
        $tplVars['location'] = $stmt1->fetchAll();
        
        $stmt2 = $this->db->prepare('SELECT * FROM meeting LEFT JOIN location USING(id_location) WHERE id_meeting = :id');
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
    return $this->view->render($response, 'edit-meeting.latte', $tplVars);
})->setName('editMetList');


$app->post('/uprava-vypisSch', function (Request $request, Response $response, $args) {
    $id = $request->getQueryParam('id');
    if (empty($id)) {
        exit('Nezadano ID schuzky');
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


        return $response->withHeader('Location', "./vypis-schuzek");
    } catch (PDOException $ex) {
        $this->logger->error($ex->getMessage());
        exit($ex->getMessage());
    }

    $tplVars['schuzka'] = $data;
    return $this->view->render($response, 'edit-meeting.latte', $tplVars);
});

