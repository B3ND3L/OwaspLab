<?php

namespace Renderer;

use Leaf\UI;
use Leaf\UI\Component;

class MainRender extends Component
{
  public $key = 'MainRender';

  public function render()
  {
    return UI::view('views/main.html');
  }
}
