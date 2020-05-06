<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->get('/vypis-vztah', function (Request $request, Response $response, $args) {


    try {
        $stmt = $this->db->query("SELECT id_relation, os1.first_name AS first_name1, os1.last_name AS last_name1, os2.first_name AS first_name2, os2.last_name AS last_name2, name, description FROM relation AS re JOIN relation_type AS ty ON re.id_relation_type = ty.id_relation_type JOIN person AS os1 ON re.id_person1 = os1.id_person JOIN person AS os2 ON re.id_person2 = os2.id_person ORDER BY id_relation DESC");

//        $stmt1->bindValue(':id', $data['osoba']);
        $tplVars['relation'] = $stmt->execute();
        $relation = $stmt->fetchAll();
        $tplVars['relation'] = $relation;
    } catch (Exception $e) {
        die("DB chyba" . $e->getMessage());
    }


    return $this->view->render($response, 'vypis-vztah.latte', $tplVars);
})->setName('vypisVztah');
