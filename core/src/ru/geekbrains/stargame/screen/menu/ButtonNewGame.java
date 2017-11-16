package ru.geekbrains.stargame.screen.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.engine.math.Rect;
import ru.geekbrains.engine.ui.ActionListener;
import ru.geekbrains.engine.ui.ScaleTouchUpButton;

/**
 * Кнопка "Новая игра"
 */
public class ButtonNewGame extends ScaleTouchUpButton {

    /**
     * Конструктор
     *
     * @param atlas     атлас
     * @param listener   слушатель событий
     * @param pressScale на сколько уменьшить кнопку при нажатии
     */
    public ButtonNewGame(TextureAtlas atlas, ActionListener listener, float pressScale) {
        super(atlas.findRegion("btPlay"), listener, pressScale);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setLeft(worldBounds.getLeft());
    }
}
