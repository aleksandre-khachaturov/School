<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->get('/', function (Request $request, Response $response, $args) {

    try {
        $params = $request->getQueryParams();
        if (!empty($params['filtr'])) {
            //filtruju
            $stmt = $this->db->prepare('SELECT * FROM person LEFT JOIN location USING (id_location) LEFT JOIN (SELECT id_person, COUNT(*) AS pocet FROM person_meeting GROUP BY id_person) AS ps USING (id_person) LEFT JOIN (SELECT id_person, COUNT(*) AS pocet_kontaktu FROM contact GROUP BY id_person) AS pk USING (id_person) WHERE first_name ILIKE :f OR last_name ILIKE :f OR nickname ILIKE :f ORDER BY last_name');

            $stmt->bindValue(':f', '%' . $params['filtr'] . '%');

            $stmt->execute();
        } else {
            $tplVars['zprava'] = 'Není nic zadáno';

            //nefiltruju
            $stmt = $this->db->query('SELECT * FROM person LEFT JOIN location USING (id_location) LEFT JOIN (SELECT id_person, COUNT(*) AS pocet FROM person_meeting GROUP BY id_person) AS ps USING (id_person) LEFT JOIN (SELECT id_person, COUNT(*) AS pocet_kontaktu FROM contact GROUP BY id_person) AS pk USING (id_person) ORDER BY last_name');
        }
        $osoby = $stmt->fetchAll();
        // Render index view
        return $this->view->render($response, 'index.latte', ['osoby' => $osoby]);
    } catch (PDOException $ex) {
        $this->logger->error($ex->getMessage());
        exit($ex->getMessage());
    }
})->setName('index');