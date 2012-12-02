package com.vn.cda.demo5.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vn.cda.demo5.utils.BaseScreen;
import com.vn.cda.demo5.utils.Driections;

public class Pacman {
	private int x;
	private int y;
	private int width;
	private int height;
	
	final static int HEIGHT = 32;
	final static int WIDTH = 32;

	final static int FAST = 300;
	final static int SLOW = 100;

	final static float F_FrameDura = 0.25f;
	final static float S_FrameDura = 0.5f;

	Animation slow_UpAnimation;
	Animation slow_DownAnimation;
	Animation slow_LeftAnimation;
	Animation slow_RightAnimation;

	Animation fast_UpAnimation;
	Animation fast_DownAnimation;
	Animation fast_LeftAnimation;
	Animation fast_RightAnimation;

	TextureRegion[] UpWalking;
	TextureRegion[] DownWalking;
	TextureRegion[] LeftWalking;
	TextureRegion[] RightWalking;
	
	Animation CurrAnimation;

	TextureRegion currentFrame;
	float stateTime = 0;

	private int speed = SLOW;
	private int direction = Driections.UP;
	private boolean isStanding = true;

	public Pacman(int x,int y) {

		this.setSize(WIDTH, HEIGHT);
		this.setPosition(x,y);
		Texture texture = new Texture(Gdx.files.internal("data/pacman2.png"));
		TextureRegion[][] regions = TextureRegion.split(texture, WIDTH, HEIGHT);
		
		UpWalking = regions[2];

		DownWalking = regions[1];

		LeftWalking = regions[3];
		
		RightWalking = regions[0];

		slow_UpAnimation = new Animation(S_FrameDura, UpWalking);
		slow_DownAnimation = new Animation(S_FrameDura, DownWalking);
		slow_LeftAnimation = new Animation(S_FrameDura, LeftWalking);
		slow_RightAnimation = new Animation(S_FrameDura, RightWalking);

		fast_UpAnimation = new Animation(F_FrameDura, UpWalking);
		fast_DownAnimation = new Animation(F_FrameDura, DownWalking);
		fast_LeftAnimation = new Animation(F_FrameDura, LeftWalking);
		fast_RightAnimation = new Animation(F_FrameDura, RightWalking);
	}

	public void setSize(int w, int h)
	{
		this.width = w;
		this.height = h;
	}
	
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setDirection(int direct) {
		this.direction = direct;
	}

	public void setStand(boolean value) {
		isStanding = value;
	}

	public void setFast() {
		speed = FAST;
	}

	public void setSlow() {
		speed = SLOW;
	}

	public void Walking(float delta, SpriteBatch batch ) {
		if (isStanding)
		{
			DrawStanding(batch);
			return;
		}

		if (direction == Driections.UP)
			WalkUp(delta, batch);
		else if (direction == Driections.DOWN)
			WalkDown(delta, batch);
		else if (direction == Driections.LEFT)
			WalkLeft(delta, batch);
		else if (direction == Driections.RIGHT)
			WalkRight(delta, batch);
	}

	public void WalkUp(float delta, SpriteBatch batch) {
		
		y = y + (int)(speed * delta);
		if (y > BaseScreen.VIEWPORT_HEIGHT) {
			stateTime = 0;
			y = 0;
		}

		if (speed == FAST)
			CurrAnimation = fast_UpAnimation;
		else
			CurrAnimation = slow_UpAnimation;
		DrawAnim(delta, batch);

	}

	public void WalkDown(float delta, SpriteBatch batch) {
		y = y - (int)(speed * delta);
		if (y < -height) {
			stateTime = 0;
			y = BaseScreen.VIEWPORT_HEIGHT;
		}

		if (speed == FAST)
			CurrAnimation = fast_DownAnimation;
		else
			CurrAnimation = slow_DownAnimation;
		DrawAnim(delta, batch);
	}

	public void WalkRight(float delta, SpriteBatch batch) {
		x = x + (int)(speed * delta);
		if (x > BaseScreen.VIEWPORT_WIDTH) {
			stateTime = 0;
			x = - width;
		}

		if (speed == FAST)
			CurrAnimation = fast_RightAnimation;
		else
			CurrAnimation = slow_RightAnimation;
		DrawAnim(delta, batch);
	}

	public void WalkLeft(float delta, SpriteBatch batch) {
		x = x - (int)(speed * delta);
		if (x < - width) {
			stateTime = 0;
			x = BaseScreen.VIEWPORT_WIDTH;
		}

		if (speed == FAST)
			CurrAnimation = fast_LeftAnimation;
		else
			CurrAnimation = slow_LeftAnimation;
		DrawAnim(delta, batch);
	}

	private void DrawAnim(float delta, SpriteBatch batch) {
		stateTime += delta;
		currentFrame = CurrAnimation.getKeyFrame(stateTime, true);
		
		batch.begin();
			batch.draw(currentFrame, x, y, width, height);
		batch.end();
		
	}
	
	private void DrawStanding(SpriteBatch batch)
	{
		if (direction == Driections.UP)
			currentFrame = UpWalking[1];
		else if (direction == Driections.DOWN)
			currentFrame = DownWalking[1];
		else if (direction == Driections.LEFT)
			currentFrame = LeftWalking[1];
		else if (direction == Driections.RIGHT)
			currentFrame = RightWalking[1];
		
		batch.begin();
		batch.draw(currentFrame, x, y, width, height);
		batch.end();
	}
	
	public int getPositionX(){
		return this.x;
	}
}
