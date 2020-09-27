package com.example.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

import java.util.Random;

public class SurvivorBird extends ApplicationAdapter {

	SpriteBatch batch;
	Texture background,bird,bee,bee2,bee3;
	float birdX,birdY;
	int gameState = 0;
	float velocity = 0;
	float gravity = 0.3f;
	int numberofEnemies = 4;
	float[] enemyX = new float[numberofEnemies];
	float[] enemyOffset = new float[numberofEnemies];
	float[] enemyOffset2 = new float[numberofEnemies];
	float[] enemyOffset3 = new float[numberofEnemies];
	float distance = 0;
	float enemyVelocity = 2;
	Random random;
	Circle birdCircle;
	Circle[] enemyCircle;
	Circle[] enemyCircle2;
	Circle[] enemyCircle3;
	ShapeRenderer shapeRenderer;
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
		random = new Random();
		birdCircle = new Circle();
		enemyCircle = new Circle[numberofEnemies];
		enemyCircle2 = new Circle[numberofEnemies];
		enemyCircle3 = new Circle[numberofEnemies];
		shapeRenderer = new ShapeRenderer();
		for(int i=0; i < numberofEnemies;i++)
		{
			enemyOffset[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
			enemyOffset2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
			enemyOffset3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
			enemyCircle[i] = new Circle();
			enemyCircle2[i] = new Circle();
			enemyCircle3[i] = new Circle();
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
					enemyOffset[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyOffset2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyOffset3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyX[i] = enemyX[i] + distance;

				}else{
					enemyX[i] = enemyX[i] - enemyVelocity;

				}

				batch.draw(bee,enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffset[i],Gdx.graphics.getWidth() / 15,Gdx.graphics.getHeight() / 10);
				batch.draw(bee2,enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffset2[i],Gdx.graphics.getWidth() / 15,Gdx.graphics.getHeight() / 10);
				batch.draw(bee3,enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffset3[i],Gdx.graphics.getWidth() / 15,Gdx.graphics.getHeight() / 10);

				enemyCircle[i] = new Circle(enemyX[i] + Gdx.graphics.getHeight() / 30 , enemyOffset[i] + Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth() / 30);
				enemyCircle2[i] = new Circle(enemyX[i] + Gdx.graphics.getHeight() / 30 , enemyOffset2[i] + Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth() / 30);
				enemyCircle3[i] = new Circle(enemyX[i] + Gdx.graphics.getHeight() / 30 , enemyOffset3[i] + Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth() / 30);
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

		birdCircle.set(birdX,birdY,Gdx.graphics.getWidth() / 30);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled); // içi dolu olmasını sağlar
		shapeRenderer.setColor(Color.BLACK); // renk siyah
		shapeRenderer.circle(birdCircle.x + Gdx.graphics.getWidth() / 30,birdCircle.y + Gdx.graphics.getWidth() / 20,birdCircle.radius);
		for(int i = 0; i < numberofEnemies; i++)
		{
			shapeRenderer.circle(enemyX[i] + Gdx.graphics.getHeight() / 30 , enemyOffset[i] + Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth() / 30);
			shapeRenderer.circle(enemyX[i] + Gdx.graphics.getHeight() / 30 , enemyOffset2[i] + Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth() / 30);
			shapeRenderer.circle(enemyX[i] + Gdx.graphics.getHeight() / 30 , enemyOffset3[i] + Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth() / 30);
		}
		shapeRenderer.end();
	}
	
	@Override
	public void dispose () {

	}
}
