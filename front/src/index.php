<?php

require __DIR__ . '/vendor/autoload.php';

use Leaf\UI;
use Renderer\MainRender;

$app = new Leaf\App;

$app->match('GET', '/', function () {
    $render = new MainRender();
    $render->setGames();
    return UI::render($render);
});

$app->run();
