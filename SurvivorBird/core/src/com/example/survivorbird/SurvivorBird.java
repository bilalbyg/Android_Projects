package com.example.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SurvivorBird extends ApplicationAdapter {

	SpriteBatch batch;
	Texture background,bird,bee,bee2,bee3;
	float birdX,birdY;
	int gameState = 0;
	float velocity = 0;
	float gravity = 0.3f;
	int numberofEnemies = 4;
	float[] enemyX = new float[numberofEnemies];
	float distance = 0;
	float enemyVelocity = 2;

	@Override
	public void create () { // ilk açıldığında
		batch = new SpriteBatch();
		background = new Texture("bg.png");
		bird = new Texture("bird.png");
		bee = new Texture("bee.png");
		bee2 = new Texture("bee.png");
		bee3 = new Texture("bee.png");
		birdX = Gdx.graphics.getWidth() / 4;
		birdY = Gdx.graphics.getHeight() / 3;
		distance = Gdx.graphics.getWidth()/2;
		for(int i=0; i < numberofEnemies;i++)
		{
			enemyX[i] = Gdx.graphics.getWidth() - bee.getWidth()/2 + i * distance;
		}

	}

	@Override
	public void render () { // sürekli devam eden
		batch.begin();
		batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		if(gameState == 1)
		{
			if(Gdx.input.justTouched())
			{
				velocity = -8;
			}

			for(int i = 0;i < numberofEnemies;i++)
			{
				if(enemyX[i] < Gdx.graphics.getWidth()/15)
				{
					enemyX[i] = enemyX[i] + distance;

				}else{
					enemyX[i] = enemyX[i] - enemyVelocity;

				}

				batch.draw(bee,enemyX[i],50,Gdx.graphics.getWidth() / 15,Gdx.graphics.getHeight() / 10);
				batch.draw(bee2,enemyX[i],350,Gdx.graphics.getWidth() / 15,Gdx.graphics.getHeight() / 10);
				batch.draw(bee3,enemyX[i],650,Gdx.graphics.getWidth() / 15,Gdx.graphics.getHeight() / 10);
			}

			if(birdY > 0 || velocity < 0)
			{
				velocity = velocity + gravity;
				birdY = birdY - velocity;
			}

		}
		else
			{
				if(Gdx.input.justTouched())
				{
					gameState = 1;
				}
			}

		batch.draw(bird,birdX,birdY,Gdx.graphics.getWidth() / 15,Gdx.graphics.getHeight() / 10);
		batch.end();
	}
	
	@Override
	public void dispose () {

	}
}
