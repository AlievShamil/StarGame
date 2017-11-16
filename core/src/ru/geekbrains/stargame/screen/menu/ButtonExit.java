package ru.geekbrains.stargame.screen.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.engine.math.Rect;
import ru.geekbrains.engine.ui.ActionListener;
import ru.geekbrains.engine.ui.ScaleTouchUpButton;

/**
 * Created by Alexey on 11.11.2017.
 */

public class ButtonExit extends ScaleTouchUpButton {
    /**
     * Конструктор
     *
     * @param atlas     атлас
     * @param listener   слушатель событий
     * @param pressScale на сколько уменьшить кнопку при нажатии
     */
    public ButtonExit(TextureAtlas atlas, ActionListener listener, float pressScale) {
        super(atlas.findRegion("btExit"), listener, pressScale);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setRight(worldBounds.getRight() );
    }
}
