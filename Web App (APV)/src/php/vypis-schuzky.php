<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->get('/vypis-schuzky', function (Request $request, Response $response, $args) {
    
    try {         
        $stmt5 = $this->db->prepare('SELECT * FROM person_meeting JOIN meeting USING(id_meeting) 
            JOIN location USING(id_location)
            WHERE id_meeting = :id
            ORDER BY city, start');

        $stmt5->bindValue(':id', $IdOsoby);
        $tplVars['meeting'] = $stmt5->execute();
        $meeting = $stmt5->fetchAll();
        $tplVars['meeting'] = $meeting;
    } catch (Exception $e) {
        die("Chyba v DB" . $e->getMessage());
    }


    return $this->view->render($response, 'vypis-schuzky.latte', $tplVars);
})->setName('vypisSchuzky');


