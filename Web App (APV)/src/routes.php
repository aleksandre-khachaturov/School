<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

$app->get('/', function(Request $request, Response $response) {
    return $response->withHeader('Location', $this->router->pathFor('login'));
});
$app->group('/auth', function() use($app) {

    include("php/filtr.php");
    include("php/pridatOsobu.php");
    include("php/new-person.php");
    include("php/odstranOsobu.php");
    include("php/novy-kontakt.php");
    include("php/details-person.php");
    include("php/details-meeting.php");
    include("php/uprava-osoby.php");
    include("php/smaz-osobu.php");
    include("php/smaz-kontakt.php");
    include("php/smaz-vztah.php");
    include("php/smaz-schuzku.php");
    include("php/smaz-account.php");
    include("php/uprava-kontaktu.php");
    include("php/uprava-vztahu.php");
    include("php/uprava-vypisSch.php");
    include("php/uprava-schuzky.php");  
    include("php/vypis-schuzek.php");  
    
    include("php/nova-schuzka.php");
    include("php/novy-vztah.php");

    include("php/userList.php");



// STRANKOVANI

    $app->get('/osoby[/{page:[0-9]+}]', function(Request $request, Response $response, $args) {
        $pageLimit = 10;
        try {
            $page = !empty($args['page']) ? $args['page'] : 0;
            //select persons with limit and offset
            $stmt = $this->db->prepare('
            SELECT * FROM person 
            ORDER BY last_name, first_name
            LIMIT :pl OFFSET :of
        ');
            $stmt->bindValue(':pl', $pageLimit);
            $stmt->bindValue(':of', $page * $pageLimit);
            $stmt->execute();
            //calculate how much rows is in the database
            $stmtCnt = $this->db->query('SELECT COUNT(*) AS cnt FROM person');
            $pageInfo = $stmtCnt->fetch();
            //copy values to template
            $tplVars['pCount'] = ceil($pageInfo['cnt'] / $pageLimit);
            $tplVars['pLimit'] = $pageLimit;
            $tplVars['pCurr'] = $page;
            $tplVars['osoby'] = $stmt->fetchAll();
            return $this->view->render($response, 'index.latte', $tplVars);
        } catch (PDOException $e) {
            $this->logger->error($e->getMessage());
            exit($e->getMessage());
        }
    })->setName('osoby');



    $app->get('/logout', function(Request $request, Response $response, $next) {
        session_destroy();
        return $response->withHeader('Location', $this->router->pathFor('login'));
    })->setName('logout');
})->add(function(Request $request, Response $response, $next) {
//middleware   
    if (!empty($_SESSION['user'])) {
        return $next($request, $response);
    } else {
        return $response->withHeader('Location', $this->router->pathFor('login'));
    }
});



include("php/reg.php");
include("php/login.php");
