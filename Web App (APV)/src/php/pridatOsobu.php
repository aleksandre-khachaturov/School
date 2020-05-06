<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->get('/add-person', function (Request $request, Response $response, $args) {

    $tplVars['nMeeting'] = [
        'osoba' => ''
    ];

    try {
        $stmt2 = $this->db->query('SELECT id_person, first_name, last_name FROM person ORDER BY last_name, first_name');
        $tplVars['osoby'] = $stmt2->fetchAll();
    } catch (PDOException $e) {
        $this->logger->error($e->getMessage());
        exit($e->getMessage());
    }

    return $this->view->render($response, 'add-person.latte', $tplVars);
})->setName('addPerson');



$app->post('/add-person', function (Request $request, Response $response, $args) {
    $IdSchuzky = $request->getQueryParam('id');
    $data = $request->getParsedBody();

    try {
        
        if (!empty($data['osoba'])) {
            $stmt1 = $this->db->prepare('INSERT INTO person_meeting (id_person, id_meeting) VALUES (:idOsoby, :idSchuzky)');

            $stmt1->bindValue(':idOsoby', $data['osoba']);
            $stmt1->bindValue(':idSchuzky', $IdSchuzky);
            $stmt1->execute();
                        
            return $response->withHeader('Location', "./details-schuzek?id=$IdSchuzkyd");
        } else {
            $tplVars['error'] = 'Zadejte povinne udaje';
        }
    } catch (PDOException $ex) {
        $this->logger->error($ex->getMessage());
        exit($ex->getMessage());
    }

    $tplVars['nMeeting'] = $data;
    return $this->view->render($response, 'add-person.latte', $tplVars);
});

