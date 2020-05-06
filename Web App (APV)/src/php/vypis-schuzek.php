<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->get('/vypis-schuzek', function (Request $request, Response $response, $args) {
    try {
        $request->getQueryParams();
        
        $stmt5 = $this->db->query('SELECT * FROM meeting
            JOIN location USING(id_location) 
            ORDER BY city, start');

        $tplVars['meeting'] = $stmt5->execute();
        $meeting = $stmt5->fetchAll();
        $tplVars['meeting'] = $meeting;

        return $this->view->render($response, 'vypis-schuzek.latte', $tplVars);
    } catch (PDOException $e) {
        $this->logger->error($e->getMessage());
        exit($e->getMessage());
    }
    return $this->view->render($response, 'vypis-schuzek.latte', ['person' => $person]);
})->setName('vypisSchuzek');
