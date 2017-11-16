package ru.geekbrains.stargame.pools;

import com.badlogic.gdx.audio.Sound;

import ru.geekbrains.engine.pool.SpritesPool;
import ru.geekbrains.stargame.Bullet;

/**
 * Created by DevCom on 15.11.2017.
 */

public class BulletPool extends SpritesPool<Bullet> {

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }

    @Override
    protected void debugLog() {
        System.out.println("active/free: "+ activeObjects.size()+"/"+freeObjects.size());
    }
}
