package ru.geekbrains.stargame;

import com.badlogic.gdx.Game;

import ru.geekbrains.stargame.screen.menu.MenuScreen;

/**
 * Created by DevCom on 08.11.2017.
 */

public class Star2DGame extends Game {
    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }
}
