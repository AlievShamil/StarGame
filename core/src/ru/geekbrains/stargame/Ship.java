package ru.geekbrains.stargame;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.engine.math.Rect;
import ru.geekbrains.engine.sprite.Sprite;
import ru.geekbrains.stargame.pools.BulletPool;

/**
 * Базовый класс для всех кораблей
 */
public class Ship extends Sprite {

    protected final Vector2 v = new Vector2(); // скорость корабля
    protected Rect worldBounds; // граница мира

    protected BulletPool bulletPool; // пулл пуль
    protected TextureRegion bulletRegion; // текстура пули

    protected final Vector2 bulletV = new Vector2(); // скорость пули
    protected float bulletHeight; // высота пули
    protected int bulletDamage; // урон

    protected float reloadInterval; // время перезарядки
    protected float reloadTimer; // таймер для стрельбы

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }

    /**
     * Выстрел
     */
    protected void shoot(Sound sound) {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, bulletHeight, worldBounds, bulletDamage, sound);
    }
}
