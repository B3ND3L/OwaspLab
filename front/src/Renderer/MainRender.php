<?php

namespace Renderer;

use GuzzleHttp\Exception\GuzzleException;
use Services\GameService;
use Leaf\UI;
use Leaf\UI\Component;

class MainRender extends Component
{
    public $key = 'MainRender';
    private $gameService;
    public $games;

    /**
     * @throws GuzzleException
     */
    function setGames() {
        $this->gameService = new GameService();
        $this->games = $this->gameService->getAllGames();
    }

    public function render()
    {
        return UI::view('views/main.html');
    }
}
