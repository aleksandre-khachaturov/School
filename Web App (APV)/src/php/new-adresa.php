<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->get('/new-adresa', function (Request $request, Response $response, $args) {
    $tplVars['person'] = [
        'mesto' => '',
        'ulice' => '',
        'dom' => '',
        'psc' => '',
        'stat' => '',
        'long' => '',
        'lat' => '',
        'descr' => ''
    ];

    return $this->view->render($response, 'new-address.latte', $tplVars);
})->setName('addAdresu');

$app->post('/new-adresa', function (Request $request, Response $response, $args) {
    $IdOsoby = $request->getQueryParam('id');

    $data = $request->getParsedBody();
    try {
        if (!empty($data['mesto']) && !empty($data['ulice']) && !empty($data['dom'])) {
            $stat = empty($data['stat']) ? null : $data['stat'];
            $psc = empty($data['psc']) ? null : $data['psc'];
            $lat = empty($data['lat']) ? null : $data['lat'];
            $long = empty($data['long']) ? null : $data['long'];

            $stmt1 = $this->db->prepare('INSERT INTO location (city, street_name, street_number, zip, country, name, latitude, longitude) '
                    . 'VALUES (:mesto, :ulice, :dom, :psc, :stat, :descr, :lat, :lon)');

            $stmt1->bindValue(':mesto', $data['mesto']);
            $stmt1->bindValue(':ulice', $data['ulice']);
            $stmt1->bindValue(':dom', $data['dom']);
            $stmt1->bindValue(':psc', $psc);
            $stmt1->bindValue(':stat', $stat);
            $stmt1->bindValue(':descr', $data['descr']);
            $stmt1->bindValue(':lat', $lat);
            $stmt1->bindValue(':lon', $long);
            $stmt1->execute();

            $AddressID = $this->db->lastInsertId("location_id_location_seq");


            $stmt = $this->db->prepare('UPDATE person SET id_location = :idLoc '
                    . 'WHERE id_person = :id');

            $stmt->bindValue(':idLoc', $AddressID);
            $stmt->bindValue(':id', $IdOsoby);
            $stmt->execute();
        }

        return $response->withHeader('Location', '.');
    } catch (PDOException $ex) {
        $this->logger->error($ex->getMessage());
        exit($ex->getMessage());
    }
    $tplVars['person'] = $data;
    return $this->view->render($response, 'new-address.latte', $tplVars);
});
