package com.mygdx.game.android;

import android.content.Context;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class ShareTest implements ApplicationListener {
	private Context context;
	private SpriteBatch spriteBatch;
	private Sprite sprite;
	private Texture texture;
	private int left;
	private int top;
	private int deltaL;
	private int deltaT;
	private Stage stage;

	public ShareTest(Context context) {
		this.context = context;
	}
	
	public void create() {
		spriteBatch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("badlogic.jpg")) ;
		sprite = new Sprite(texture);
		deltaL = deltaT = 11;
		sprite.setPosition(left, top);
		
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		Gdx.input.setInputProcessor(stage);
		Texture upTexture = new Texture(Gdx.files.internal("share.png"));
		SpriteDrawable upDraw = new SpriteDrawable(new Sprite(upTexture));
		ImageButton up = new ImageButton(upDraw);
		up.setPosition(Gdx.graphics.getWidth() - 160, 40);
		up.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				goShare();
			}
		});
		stage.addActor(up);
	}
	
	public void pause() {
		
	}
	
	public void resume() {
		
	}
	
	public void dispose() {
		stage.dispose();
	}
	
	public void resize(int width, int height) {
		
	}

	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		float c = left / 1920f;
		Gdx.gl.glClearColor(c, 0.5f, 0.5f, 0.5f);
		
		spriteBatch.begin();
		sprite.draw(spriteBatch);
		spriteBatch.end();
		
		stage.act();
		stage.draw();
		
		changeLT();
	}
	
	private void changeLT() {
		left += deltaL;
		if (left >= Gdx.graphics.getWidth() - 256) {
			deltaL = -11;
		}
		if (left <= 0) {
			deltaL = 11;
		}
		top += deltaT;
		if (top >= Gdx.graphics.getHeight() - 256) {
			deltaT = -11;
		}
		if (top <= 0) {
			deltaT = 11;
		}
		sprite.setPosition(left, top);
	}
	
	// all logics about sharesdk are here
	// using sharesdk on libgdx just like what you can do in a normal android java app
	private void goShare() {
		OnekeyShare oks = new OnekeyShare();
		oks.setText("I can use ShareSDK on libgdx!");
		oks.setDialogMode();
		oks.show(context);
	}

}
