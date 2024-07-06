<?php

namespace Services;

use GuzzleHttp\Client;
use GuzzleHttp\Exception\GuzzleException;

class GameService {

    /**
     * @throws GuzzleException
     */
    function getAllGames() {

        $client = new Client([
            'base_uri' => 'http://localhost:8080/api/'
        ]);

        $res = $client->request("GET", "game/all?limit=0");
        return json_decode($res->getBody()->getContents());

    }
}
