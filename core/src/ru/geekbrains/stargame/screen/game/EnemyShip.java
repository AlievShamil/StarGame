package ru.geekbrains.stargame.screen.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.geekbrains.engine.math.Rect;
import ru.geekbrains.engine.math.Rnd;
import ru.geekbrains.stargame.Bullet;
import ru.geekbrains.stargame.Ship;
import ru.geekbrains.stargame.pools.BulletPool;

/**
 * Created by DevCom on 17.11.2017.
 */

public class EnemyShip extends Ship {


    private static final List<String> enemy;

    static {
        enemy =new ArrayList<String>();
        enemy.add("enemy0");
        enemy.add("enemy1");
        enemy.add("enemy2");

    }

    // вектор скорости, который отслеживает звезда
    private final Vector2 trackingV;
    // вектор суммы скоростей
    private final Vector2 sumV = new Vector2();
    private final Vector2 v = new Vector2();

    private Sound bulletSound;

    public EnemyShip(TextureAtlas atlas, float vx, float vy, float height, Vector2 trackingV, BulletPool bulletPool, Sound bulletSound) {
        super(atlas.findRegion(getEnemyShipRegion()),1,2,2);
//        super(atlas.findRegion("enemy2"),1,2,2);
        v.set(vx, vy);
        this.trackingV = trackingV;
        setHeightProportion(height);
        this.bulletRegion = atlas.findRegion("bulletEnemy");
        this.bulletHeight = 0.01f;
        this.bulletV.set(0, 0.5f);
        this.bulletDamage = 1;
        this.bulletPool = bulletPool;
        this.reloadInterval = 0.9f;
        this.bulletSound = bulletSound;
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        float posX = Rnd.nextFloat(this.worldBounds.getLeft(), this.worldBounds.getRight());
        float posY = Rnd.nextFloat(this.worldBounds.getBottom(), this.worldBounds.getTop());
        pos.set(posX, posY);
    }

    @Override
    public void update(float deltaTime) {
        sumV.setZero().mulAdd(trackingV, 0.2f).rotate(180).add(v);
        pos.mulAdd(sumV,deltaTime);
        reloadTimer += deltaTime;
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            shoot(bulletSound);
        }
        if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());}
        if (getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
        }

        checkAndHandleBounds();
    }

    private void checkAndHandleBounds() {
        if (getRight() < worldBounds.getLeft()) setLeft(worldBounds.getRight());
        if (getLeft() > worldBounds.getRight()) setRight(worldBounds.getLeft());
        if (getTop() < worldBounds.getBottom()) setBottom(worldBounds.getTop());
        if (getBottom() > worldBounds.getTop()) setTop(worldBounds.getBottom());
    }

    public static String getEnemyShipRegion() {
        System.out.println(enemy.get((int) Rnd.nextFloat(0,enemy.size())));
        return enemy.get((int) Rnd.nextFloat(0,enemy.size()));
    }
}
