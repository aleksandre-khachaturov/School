<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->get('/new-person', function (Request $request, Response $response, $args) {
    $tplVars['person'] = [
        'jm' => '',
        'pr' => '',
        'pz' => '',
        'dn' => '',
        'vy' => '',
        'po' => '',
        'conTy' => '',
        'con' => '',
        'mesto' => '',
        'ulice' => '',
        'dom' => '',
        'psc' => ''
    ];

    ///////////////////////////////////////////////get for contact
    try {
        $stmt = $this->db->query('SELECT * FROM contact_type');
        $tplVars['contactType'] = $stmt->fetchAll();
        $stmt->execute();
    } catch (PDOException $e) {
        $this->logger->error($e->getMessage());
        exit($e->getMessage());
    }
    //////////////////////////////////////////////////////////////////

    return $this->view->render($response, 'new-person.latte', $tplVars);
})->setName('add');

$app->post('/new-person', function (Request $request, Response $response, $args) {
    $data = $request->getParsedBody();
    try {
        if (!empty($data['jm']) && !empty($data['pr']) && !empty($data['pz'])) {
            $this->db->beginTransaction();

            $ContID = null;
            $IdOsoby = null;


            if (!empty($data['mesto']) || !empty($data['ulice']) || !empty($data['dom']) || !empty($data['psc'])) {
                $mesto = empty($data['mesto']) ? null : $data['mesto'];
                $ulice = empty($data['ulice']) ? null : $data['ulice'];
                $dom = empty($data['dom']) ? null : $data['dom'];
                $psc = empty($data['psc']) ? null : $data['psc'];

                $stmt1 = $this->db->prepare('INSERT INTO location (city, street_name, street_number, zip) VALUES (:mesto, :ulice, :dom, :psc)');

                $stmt1->bindValue(':mesto', $mesto);
                $stmt1->bindValue(':ulice', $ulice);
                $stmt1->bindValue(':dom', $dom);
                $stmt1->bindValue(':psc', $psc);
                $stmt1->execute();

                $AddressID = $this->db->lastInsertId("location_id_location_seq"); //poluchaju posledne vlozenooju ID adresy
            }

            if (!empty($data['vy']) || !empty($data['dn']) || !empty($data['po']) || !empty($data['psc'])) {
                $vy = empty($data['vy']) ? null : $data['vy'];
                $dn = empty($data['dn']) ? null : $data['dn'];
                $po = empty($data['po']) ? null : $data['po'];

                $stmt = $this->db->prepare('INSERT INTO person (height, birth_day, gender, first_name, last_name, nickname) '
                        . 'VALUES (:vy, :dn, :po, :jm, :pr, :pz)');

                $stmt->bindValue(':jm', $data['jm']);
                $stmt->bindValue(':pr', $data['pr']);
                $stmt->bindValue(':pz', $data['pz']);
                $stmt->bindValue(':vy', $vy);
                $stmt->bindValue(':dn', $dn);
                $stmt->bindValue(':po', $po);
                $stmt->execute();

                $IdOsoby = $this->db->lastInsertId("person_id_person_seq"); //dostaju adresu poslednei novoj osoby
            }

            if (!empty($AddressID)) {
                $stmt = $this->db->prepare('UPDATE person SET id_location = :idLoc'
                        . 'WHERE id_person = :id');

                $stmt->bindValue(':idLoc', $AddressID);
                $stmt->bindValue(':id', $IdOsoby);
                $stmt->execute();
            }

            if (!empty($data['conTy']) && !empty($data['con'])) {

                $stmt1 = $this->db->prepare('INSERT INTO contact (id_contact_type, id_person, contact) '
                        . 'VALUES (:conTy, :idOs,  :con)');
                $stmt1->bindValue(':conTy', $data['conTy']);
                $stmt1->bindValue(':idOs', $IdOsoby);
                $stmt1->bindValue(':con', $data['con']);
                $stmt1->execute();

                $ContID = $this->db->lastInsertId("contact_id_contact_seq"); //get posledne vlozenooju ID adresy 
            }


            $stmtAdr = $this->db->prepare('UPDATE person SET id_location = :aid WHERE id_person = :id_osoby'); //v tablicvu person SETuju ID oleaction (persona<>adresa)

            $stmtAdr->bindValue(':id_osoby', $IdOsoby);
            $stmtAdr->bindValue(':aid', $AddressID);
            $stmtAdr->execute();

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
    $tplVars['person'] = $data;
    return $this->view->render($response, 'new-person.latte', $tplVars);
});
