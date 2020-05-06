<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->get('/details-person', function (Request $request, Response $response, $args) {
    $IdOsoby = $request->getQueryParam('id');

    try {
        $request->getQueryParams();
        $stmt = $this->db->prepare('SELECT * FROM person '
                . 'LEFT JOIN location USING(id_location) '
                . 'WHERE id_person = :id');
        $stmt->bindValue(':id', $IdOsoby);
        $tplVars['person'] = $stmt->execute();
        $person = $stmt->fetchAll();
        $tplVars['person'] = $person;


        $stmt3 = $this->db->prepare('SELECT id_contact, name, id_contact_type, id_person, contact '
                . 'FROM contact_type RIGHT JOIN contact '
                . 'USING(id_contact_type)'
                . 'WHERE id_person = :id');
        $stmt3->bindValue(':id', $IdOsoby);
        $tplVars['contact'] = $stmt3->execute();
        $contact = $stmt3->fetchAll();
        $tplVars['contact'] = $contact;


        $stmt4 = $this->db->prepare("SELECT id_person1, id_relation, os2.first_name AS first_name2, os2.last_name AS last_name2, name, description FROM relation AS re JOIN relation_type AS ty ON re.id_relation_type = ty.id_relation_type JOIN person AS os2 ON re.id_person2 = os2.id_person WHERE id_person1 = :id ORDER BY id_relation DESC");
        $stmt4->bindValue(':id', $IdOsoby);
        $tplVars['relation'] = $stmt4->execute();
        $relation = $stmt4->fetchAll();
        $tplVars['relation'] = $relation;

        $stmt5 = $this->db->prepare('SELECT * FROM person_meeting JOIN meeting USING(id_meeting) 
            JOIN location USING(id_location) JOIN person USING(id_person) WHERE id_person = :id
            ORDER BY city, start');

        $stmt5->bindValue(':id', $IdOsoby);
        $tplVars['meeting'] = $stmt5->execute();
        $meeting = $stmt5->fetchAll();
        $tplVars['meeting'] = $meeting;

        return $this->view->render($response, 'details-person.latte', $tplVars);
    } catch (PDOException $e) {
        $this->logger->error($e->getMessage());
        exit($e->getMessage());
    }
    return $this->view->render($response, 'details-person.latte', ['person' => $person]);
})->setName('oOsobe');
