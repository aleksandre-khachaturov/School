<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->get('/edit-relation', function (Request $request, Response $response, $args) {
    $idPer = $request->getQueryParam('idPer'); //get ID osoby
    $id = $request->getQueryParam('id'); //get ID vztahu
    if (empty($id)) {
        exit('Nezadano ID vztahu');
    }
    if (empty($idPer)) {
        exit('Nezadano ID osoby');
    }
    try {
        $stmt0 = $this->db->query('SELECT * FROM relation_type');
        $tplVars['relationType'] = $stmt0->fetchAll();
        $stmt0->execute();
    } catch (PDOException $e) {
        $this->logger->error($e->getMessage());
        exit($e->getMessage());
    }

    try {
      
        $stmt1 = $this->db->prepare('SELECT * FROM relation '
                . 'JOIN relation_type USING(id_relation_type)'
                . 'WHERE id_relation = :id');
        $stmt1->bindValue(':id', $id);
        $stmt1->execute();
        $tplVars['vztah'] = $stmt1->fetch();

        return $this->view->render(
                        $response, 'edit-relation.latte', $tplVars
        );
    } catch (PDOException $ex) {
        $this->logger->error($ex->getMessage());
        exit($ex->getMessage());
    }
})->setName('editRel');



$app->post('/edit-relation', function (Request $request, Response $response, $args) {
    $id = $request->getQueryParam('id');
    $idPer = $request->getQueryParam('idPer');
    if (empty($id)) {
        exit('Nezadano ID vztahu');
    }
    if (empty($idPer)) {
        exit('Nezadano ID osoby');
    }

    $data = $request->getParsedBody();

    try {

        $stmt = $this->db->prepare('UPDATE relation SET description = :descr, id_relation_type = :relTy WHERE id_relation = :id_rel');

        $stmt->bindValue(':descr', $data['descr']);
        $stmt->bindValue(':relTy', $data['relTy']);
        $stmt->bindValue(':id_rel', $id);
        $stmt->execute();


        return $response->withHeader('Location', "./details-person?id=$idPer");
    } catch (PDOException $ex) {
        $this->logger->error($ex->getMessage());
        exit($ex->getMessage());
    }

    $tplVars['vztah'] = $data;
    return $this->view->render($response, 'edit-relation.latte', $tplVars);
});
