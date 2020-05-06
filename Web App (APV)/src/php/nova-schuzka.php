<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->get('/new-meeting', function (Request $request, Response $response, $args) {

    $tplVars['nMeeting'] = [
        'misto' => '',
        'cas' => '',
        'popisSchuzky' => '',
        'delkaSchuzky' => '',
        'osoba' => ''
    ];

    try {
        $stmt1 = $this->db->query('SELECT * FROM location');
        $tplVars['location'] = $stmt1->fetchAll();


        $stmt2 = $this->db->query('SELECT id_person, first_name, last_name FROM person ORDER BY last_name, first_name');
        $tplVars['osoby'] = $stmt2->fetchAll();
    } catch (PDOException $e) {
        $this->logger->error($e->getMessage());
        exit($e->getMessage());
    }

    return $this->view->render($response, 'new-meeting.latte', $tplVars);
})->setName('addSchuzka');



$app->post('/new-meeting', function (Request $request, Response $response, $args) {
    $IdOsoby = $request->getQueryParam('id');
    $data = $request->getParsedBody();

    try {
        
        if (!empty($data['misto']) &&
                !empty($data['cas']) &&
                !empty($data['osoba'])) {
            $this->db->beginTransaction();

            $popisSchuzky = empty($data['popisSchuzky']) ? '' : $data['popisSchuzky'];
            $delkaSchuzky = empty($data['delkaSchuzky']) ? null : $data['delkaSchuzky'];

            $stmt = $this->db->prepare('INSERT INTO meeting (start, description, duration, id_location) '
                    . 'VALUES (:cas, :popisSchuzky, :delkaSchuzky, :idLocation)');

            $stmt->bindValue(':idLocation', $data['misto']);
            $stmt->bindValue(':cas', $data['cas']);
            $stmt->bindValue(':popisSchuzky', $popisSchuzky);
            $stmt->bindValue(':delkaSchuzky', $delkaSchuzky);
            $stmt->execute();

            $IdSchuzky = $this->db->lastInsertId("meeting_id_meeting_seq"); //dostaju adresu poslednei novoj schuzky
            
            $stmt1 = $this->db->prepare('INSERT INTO person_meeting (id_person, id_meeting) VALUES (:idOsoby, :idSchuzky)');

            $stmt1->bindValue(':idOsoby', $data['osoba']);
            $stmt1->bindValue(':idSchuzky', $IdSchuzky);
            $stmt1->execute();
            
            $stmt2 = $this->db->prepare('INSERT INTO person_meeting (id_person, id_meeting) VALUES (:idOsoby, :idSchuzky)');

            $stmt2->bindValue(':idOsoby', $IdOsoby);
            $stmt2->bindValue(':idSchuzky', $IdSchuzky);
            $stmt2->execute();
            

            $this->db->commit();
            return $response->withHeader('Location', '.');
        } else {
            $tplVars['error'] = 'Zadejte povinne udaje';
        }
    } catch (PDOException $ex) {
        $this->db->rollback();
        $this->logger->error($ex->getMessage());
        exit($ex->getMessage());
    }

    $tplVars['nMeeting'] = $data;
    return $this->view->render($response, 'new-meeting.latte', $tplVars);
});

