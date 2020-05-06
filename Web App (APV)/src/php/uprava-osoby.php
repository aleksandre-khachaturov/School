<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->get('/edit-person', function (Request $request, Response $response, $args) {
    $id = $request->getQueryParam('id'); //get ID osoby
    if (empty($id)) {
        exit('Nezadano ID osoby');
    }

    try {
        $stmt = $this->db->prepare('SELECT * FROM person WHERE id_person = :id');
        $stmt->bindValue(':id', $id);
        $stmt->execute();
        $osoba = $stmt->fetch();
        if (!empty($osoba)) {
            $tplVars['person'] = [
                'jm' => $osoba['first_name'],
                'pr' => $osoba['last_name'],
                'pz' => $osoba['nickname'],
                'dn' => $osoba['birth_day'],
                'vy' => $osoba['height'],
                'po' => $osoba['gender']
            ];

            $stmt = $this->db->prepare('SELECT * FROM location WHERE id_location = :id');
            $stmt->bindValue(':id', $osoba['id_location']);
            $stmt->execute();

            $adresa = $stmt->fetch();
            $tplVars['location'] = [
                'mesto' => $adresa['city'],
                'ulice' => $adresa['street_name'],
                'dom' => $adresa['street_number'],
                'psc' => $adresa['zip'],
            ];

            return $this->view->render(
                            $response, 'edit-person.latte', $tplVars
            );
        } else {
            exit("Osoba nenalezena");
        }
    } catch (PDOException $ex) {
        $this->logger->error($ex->getMessage());
        exit($ex->getMessage());
    }
})->setName('edit');



$app->post('/edit-person', function (Request $request, Response $response, $args) {
    $id = $request->getQueryParam('id');
    if (empty($id)) {
        exit('Nezadano ID osoby');
    }

    $data = $request->getParsedBody();

    try {
        if ((!empty($data['jm']) &&
                !empty($data['pr']) &&
                !empty($data['pz']))) {
            $vy = empty($data['vy']) ? null : $data['vy'];
            $dn = empty($data['dn']) ? null : $data['dn'];

            $stmt = $this->db->prepare('UPDATE person SET first_name = :jm, 
                                        last_name = :pr, 
                                        nickname = :pz, 
                                        birth_day = :dn, 
                                        gender = :po, 
                                        height = :vy
                                       WHERE id_person = :id');

            $stmt->bindValue(':jm', $data['jm']);
            $stmt->bindValue(':pr', $data['pr']);
            $stmt->bindValue(':pz', $data['pz']);
            $stmt->bindValue(':vy', $vy);
            $stmt->bindValue(':dn', $dn);
            $stmt->bindValue(':po', $data['po']);
            $stmt->bindValue(':id', $id);
            $stmt->execute();

            if (!empty($data['mesto']) && !empty($data['ulice']) && !empty($data['dom']) && !empty($data['psc'])) {
                $stmtAdr = $this->db->prepare('UPDATE location SET city = :mesto, street_name = :ulice, street_number = :dom, zip = :psc WHERE id_location = (SELECT id_location FROM person WHERE id_person = :id)');

                $stmtAdr->bindValue(':mesto', $data['mesto']);
                $stmtAdr->bindValue(':ulice', $data['ulice']);
                $stmtAdr->bindValue(':dom', $data['dom']);
                $stmtAdr->bindValue(':psc', $data['psc']);
                $stmtAdr->bindValue(':id', $id);
                $stmtAdr->execute();
            }

            return $response->withHeader('Location', '.');
        } else {
            $tplVars['error'] = 'Zadejte povinne udaje';
        }
    } catch (PDOException $ex) {
        $this->logger->error($ex->getMessage());
        exit($ex->getMessage());
    }

    $tplVars['person'] = $data;
    return $this->view->render($response, 'edit-person.latte', $tplVars);
})->setName('save');
