package ru.geekbrains.stargame.screen.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.engine.Base2DScreen;
import ru.geekbrains.engine.Sprite2DTexture;
import ru.geekbrains.engine.math.Rect;
import ru.geekbrains.engine.math.Rnd;
import ru.geekbrains.stargame.Explosion;
import ru.geekbrains.stargame.pools.BulletPool;
import ru.geekbrains.stargame.pools.ExplosionPool;
import ru.geekbrains.stargame.screen.Background;
import ru.geekbrains.stargame.screen.star.TrackingStar;

/**
 * Игровой экран
 */
public class GameScreen extends Base2DScreen {

    private static final int STAR_COUNT = 56; // число звёзд
    private static final float STAR_HEIGHT = 0.01f; // высота звезды

    private final BulletPool bulletPool = new BulletPool();
    private ExplosionPool explosionPool;

    private Sprite2DTexture textureBackground;
    private Background background;
    private TextureAtlas atlas;
    private MainShip mainShip;
    private TrackingStar[] trackingStars;

    private Sound soundExplosion;
    private Sound soundBullet;
    private Music gameMusic;

    /**
     * Конструктор
     *
     * @param game // объект Game
     */
    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        this.textureBackground = new Sprite2DTexture("textures/bg.png");
        this.atlas = new TextureAtlas("textures/mainAtlas.tpack");
        this.background = new Background(new TextureRegion(this.textureBackground));
        soundBullet = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        this.mainShip = new MainShip(atlas, bulletPool,soundBullet);
        TextureRegion regionStar = atlas.findRegion("star");
        trackingStars = new TrackingStar[STAR_COUNT];
        for (int i = 0; i < trackingStars.length; i++) {
            trackingStars[i] = new TrackingStar(regionStar, Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.3f, -0.1f), STAR_HEIGHT, mainShip.getV());
        }

        soundExplosion = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
        this.explosionPool = new ExplosionPool(atlas, soundExplosion);

        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        gameMusic.play();
    }

    @Override
    protected void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (int i = 0; i < trackingStars.length; i++) {
            trackingStars[i].resize(worldBounds);
        }
        mainShip.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        update(delta);
        checkCollisions();
        deleteAllDestroyed();
        draw();
    }

    /**
     * Метод обновление информации в объектах
     * @param delta дельта
     */
    public void update(float delta) {
        for (int i = 0; i < trackingStars.length; i++) {
            trackingStars[i].update(delta);
        }
        bulletPool.updateActiveSprites(delta);
        explosionPool.updateActiveSprites(delta);
        mainShip.update(delta);
    }

    /**
     * Проверка коллизий (попала пуля в корабль, и т.д.)
     */
    public void checkCollisions() {

    }

    /**
     * Удаление объектов, помеченных на удаление (уничтоженные корабли, и т.д.)
     */
    public void deleteAllDestroyed() {
        bulletPool.freeAllDestroyedActiveObjects();
        explosionPool.freeAllDestroyedActiveObjects();
    }

    /**
     * Метод отрисовки
     */
    public void draw() {
        Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 0.7f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (int i = 0; i < trackingStars.length; i++) {
            trackingStars[i].draw(batch);
        }
        mainShip.draw(batch);
        bulletPool.drawActiveObjects(batch);
        explosionPool.drawActiveObjects(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        textureBackground.dispose();
        atlas.dispose();
        bulletPool.dispose();
        explosionPool.dispose();
        soundExplosion.dispose();
        soundBullet.dispose();
        gameMusic.dispose();
        super.dispose();
    }

    @Override
    protected void touchDown(Vector2 touch, int pointer) {
        mainShip.touchDown(touch, pointer);

        Explosion explosion = explosionPool.obtain();
        explosion.set(0.1f, touch);

        soundExplosion.play(0.5f);
    }

    @Override
    protected void touchUp(Vector2 touch, int pointer) {
        mainShip.touchUp(touch, pointer);
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return false;
    }
}
