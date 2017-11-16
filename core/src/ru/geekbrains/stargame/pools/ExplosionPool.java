package ru.geekbrains.stargame.pools;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.engine.pool.SpritesPool;
import ru.geekbrains.stargame.Explosion;

/**
 * Created by DevCom on 15.11.2017.
 */

public class ExplosionPool extends SpritesPool<Explosion> {

    private final TextureRegion explosionRegion;
    private Sound sound;

    public ExplosionPool(TextureAtlas atlas, Sound sound) {
        explosionRegion = atlas.findRegion("explosion");
        this.sound = sound;
    }

    @Override
    protected Explosion newObject() {
        return new Explosion(explosionRegion, 9, 9, 74, sound);
    }
}
