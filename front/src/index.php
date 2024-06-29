<?php

require __DIR__ . '/vendor/autoload.php';

use Leaf\UI;
use Renderer\MainRender;

$app = new Leaf\App;

$app->match('GET', '/', function () {
    return UI::render(new MainRender());
});

$app->run();
