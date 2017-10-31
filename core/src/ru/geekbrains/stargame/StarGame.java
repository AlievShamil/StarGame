package ru.geekbrains.stargame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class StarGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture img;
    private float height;
    private float width;
    private boolean bool;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        height = img.getHeight();
        width = img.getWidth();
    }

    @Override
    public void render() {
        setBackgroundColor();
        batch.begin();
        batch.draw(img, (Gdx.graphics.getWidth() - width) / 2,
                (Gdx.graphics.getHeight() - height) / 2);
        batch.end();
    }

    private void setBackgroundColor() {
        float r = MathUtils.random(0f, 1f);
        float g = MathUtils.random(0f, 1f);
        float b = MathUtils.random(0f, 1f);

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && bool) {
            Gdx.gl.glClearColor(r, g, b, 1);
            System.out.println(r+" "+g+" "+b);
            bool = false;
        }
        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && !bool) {
            Gdx.gl.glClearColor(r, g, b, 1);
            bool = true;
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
