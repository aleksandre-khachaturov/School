<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->get('/details-schuzek', function (Request $request, Response $response, $args) {
    $IdSchuzky = $request->getQueryParam('id');

    try {
        $request->getQueryParams();
        $stmt = $this->db->prepare('SELECT * FROM person_meeting
            LEFT JOIN person
            USING(id_person) WHERE id_meeting = :id');
        $stmt->bindValue(':id', $IdSchuzky);
        $tplVars['person'] = $stmt->execute();
        $person = $stmt->fetchAll();
        $tplVars['person'] = $person;


        $stmt5 = $this->db->prepare('SELECT * FROM person_meeting JOIN meeting USING(id_meeting) 
            JOIN location USING(id_location) JOIN person USING(id_person) WHERE id_meeting = :id
            ORDER BY city, start');

        $stmt5->bindValue(':id', $IdSchuzky);
        $tplVars['meeting'] = $stmt5->execute();
        $meeting = $stmt5->fetchAll();
        $tplVars['meeting'] = $meeting;

        return $this->view->render($response, 'detail-meeting.latte', $tplVars);
    } catch (PDOException $e) {
        $this->logger->error($e->getMessage());
        exit($e->getMessage());
    }
    return $this->view->render($response, 'detail-meeting.latte', $tplVars);
})->setName('oSchuzce');
