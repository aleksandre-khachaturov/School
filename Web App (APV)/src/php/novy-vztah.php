<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->get('/new-vztah', function (Request $request, Response $response, $args) {

    $tplVars['nVztah'] = [
        "per2" => "",
        "descr" => "",
        "vzt" => ""
    ];

    try {
        $stmt = $this->db->query("SELECT id_person, first_name, last_name FROM person ORDER BY last_name");
        $tplVars['person'] = $stmt->fetchAll();

        $stmt2 = $this->db->query("SELECT * FROM relation_type");
        $tplVars['relation'] = $stmt2->fetchAll();
    } catch (PDOException $e) {
        $this->logger->error($e->getMessage());
        exit($e->getMessage());
    }

    return $this->view->render($response, 'new-vztah.latte', $tplVars);
})->setName('addVztah');



$app->post('/new-vztah', function (Request $request, Response $response, $args) {
    $IdOsoby = $request->getQueryParam('id');
    $data = $request->getParsedBody();

    try {

        if  (!empty($data['per2']) &&
                !empty($data['vzt'])) {
            

            $stmt1 = $this->db->prepare('INSERT INTO relation (id_person1, id_person2, description, id_relation_type) '
                    . 'VALUES (:per1, :per2, :descr, :vzt)');

            $stmt1->bindValue(':per1', $IdOsoby);
            $stmt1->bindValue(':per2', $data['per2']);
            $stmt1->bindValue(':descr', $data['descr']);
            $stmt1->bindValue(':vzt', $data['vzt']);
            $stmt1->execute();

            return $response->withHeader('Location', "./details-person?id=$IdOsoby");
        } else {
            $tplVars['zprava'] = 'Nejsou zadane svechny nutne udaje';
        }
    } catch (PDOException $ex) {
        $this->logger->error($ex->getMessage());
        exit($ex->getMessage());
    }

    $tplVars['nVztah'] = $data;
    return $this->view->render($response, 'new-vztah.latte', $tplVars);
});

