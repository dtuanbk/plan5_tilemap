package com.vn.cda.demo5;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.tiled.TileAtlas;
import com.badlogic.gdx.graphics.g2d.tiled.TileMapRenderer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLayer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLoader;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;
import com.badlogic.gdx.graphics.g2d.tiled.TiledObject;
import com.badlogic.gdx.graphics.g2d.tiled.TiledObjectGroup;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.vn.cda.demo5.object.FoodImage;
import com.vn.cda.demo5.object.Pacman;
import com.vn.cda.demo5.uiview.MyTextButton;
import com.vn.cda.demo5.utils.BaseScreen;
import com.vn.cda.demo5.utils.Driections;

public class MyGdxGame implements ApplicationListener {
	
	
	Skin skin;
	Stage stage;
	Label labelScreenTimes;
	SpriteBatch batch;
	TextureAtlas atlas;
	private ClickListener buttonClickListener;
	// Screen second counter (1 second tick)
	private float startTime = System.nanoTime();
	private float secondsTime = 0;
	private boolean ispass=true;
	
	
	TiledMap tiledMap;
	TileAtlas tileAtlas;
	TileMapRenderer tileMapRenderer;

	
	TiledLayer nopassLayer;

	OrthographicCamera cam;
	
	Group GFoods = new Group();
	
	String path="data/map/";
	String mapname="demo5";
	
	TextureRegion food2;
	Texture texture;
	/*
	 * Pacman
	 */
	Pacman pacman;
	
	private boolean moveable=true;
	
	
	
	@Override
	public void create() {		
		/*
		 * Food2
		 */
		texture = new Texture(Gdx.files.internal("data/food2.png"));
		food2 = new TextureRegion(texture, 32, 32);
		/*
		 * Map
		 */
		FileHandle mapHandle=Gdx.files.internal(path+mapname+".tmx");
		FileHandle pathHandle=Gdx.files.internal(path);
		tiledMap=TiledLoader.createMap(mapHandle);
		tileAtlas=new TileAtlas(tiledMap, pathHandle);
		tileMapRenderer=new TileMapRenderer(tiledMap, tileAtlas, 10, 10);
		
//		tiledMap.layers.remove(1); 
		
		//Duyệt tất cả các GroupLayer (ở ví dụ tạo map trên - ta chỉ có một).
		for (int i = 0; i < tiledMap.objectGroups.size(); i++)
		{
		    TiledObjectGroup group =  tiledMap.objectGroups.get(i);
		    //Nếu GroupLayer đó có tên là "Food" thì ta bắt đầu duyệt để nạp foods
		    if ("Food".equals(group.name))
		    {
		        //Duyệt tất cả các objects bên trong GroupLayer có tên "Food"
		        for (int j = 0; j < group.objects.size(); j++ )
		        {
		            TiledObject object = group.objects.get(j);
		            //Nếu object này có Type = "Food" (đã được đặt khi vẽ map)
		            if ("Food".equals(object.type))
		            {
		                //nap đối tượng food vào Group GFoods.
		            	Gdx.app.log("Object:", "x="+object.x+"; y="+object.y);
		                GFoods.addActor(new FoodImage(food2, object.x, object.y));
		                
		            }
		        }
		    }
		}
		
		
		batch=new SpriteBatch();
		stage=new Stage(BaseScreen.VIEWPORT_WIDTH, BaseScreen.VIEWPORT_HEIGHT, false);
		Gdx.input.setInputProcessor(stage);
		atlas=new TextureAtlas(Gdx.files.internal("data/uiskin.atlas"));
		skin=new Skin(Gdx.files.internal("data/uiskin.json"));
		skin.getAtlas().getTextures().iterator().next().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		labelScreenTimes=new Label("Times: ", skin,"right");
		
		
		//Tao listener cho cac button
		createButtonListener();
		
		MyTextButton bt_left = new MyTextButton(Driections.LEFT, "Trái",skin);
		bt_left.addListener(buttonClickListener);
		MyTextButton bt_right = new MyTextButton(Driections.RIGHT,"Phải", skin);
		bt_right.addListener(buttonClickListener);
		MyTextButton bt_up = new MyTextButton(Driections.UP,"Trên",skin);
		bt_up.addListener(buttonClickListener);
		MyTextButton bt_down = new MyTextButton(Driections.DOWN,"Dưới",skin);
    	bt_down.addListener(buttonClickListener);
		
    	Table buttons = new Table();
    	
    	//cot trai (mot nut)
    	buttons.add(bt_left).width(bt_left.getWidth()).height(bt_up.getHeight()).pad(10);
    	
    	//cot giua (hai nut tren duoi)
    	Table mButtons = new Table();
    	mButtons.add(bt_up).width(bt_up.getWidth()).height(bt_up.getHeight()).pad(10);
    	mButtons.row();
    	mButtons.add(bt_down).width(bt_down.getWidth()).height(bt_down.getHeight()).pad(10);
    	buttons.add(mButtons);
    	
    	//cot phai (mot nut)
    	buttons.add(bt_right).width(bt_down.getWidth()).height(bt_up.getHeight()).pad(10);
    	//------------------------------------------------------------------------------------------
    	
    	
    	Table table = new Table();
    	table.setFillParent(true);
    	table.bottom().right();
    	
    	table.add(buttons).expand().bottom().left();
    	table.add(labelScreenTimes).width(200).top().left().padTop(10).padLeft(10);
    	stage.addActor(table);
    	
    	
    	stage.addActor(GFoods);
    	
    	pacman=new Pacman(384,32);
    	pacman.setStand(true);
    	nopassLayer=tiledMap.layers.get(1);
    	
	}

	@Override
	public void dispose() {
		if(stage!=null) stage.dispose();
		if(skin!=null) skin.dispose();
	}

	@Override
	public void render() {		
		
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		cam = (OrthographicCamera) stage.getCamera (); 
		cam.update();
	    tileMapRenderer.render (cam); 
		
		stage.act (Gdx.graphics.getDeltaTime ()); 
		
        stage.draw (); 
        if (System.nanoTime() - startTime >= 1000000000) {
			secondsTime++;
			startTime = System.nanoTime();
		}
        labelScreenTimes.setText("Times:"+secondsTime);
        
        //Pacman
        pacman.Walking(Gdx.graphics.getDeltaTime(), batch);
        //Test di chuyen len tren
		int row=(480-pacman.getPositionY())/32-1;
		Gdx.app.log("row", "="+row);
		int col=(800- pacman.getPositionX())/32-1;
		Gdx.app.log("col", "="+col);
		String proper = tiledMap.getTileProperty(nopassLayer.tiles[12][12], "nopass");
		if("true".equals(proper)){
			ispass=false;
		}
		if(!ispass){
			pacman.setStand(true);
		}
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(BaseScreen.VIEWPORT_WIDTH, BaseScreen.VIEWPORT_HEIGHT, true);
	}
	
	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}
	
	
	public void  createButtonListener(){
		buttonClickListener=new ClickListener(){

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				MyTextButton bt=(MyTextButton)event.getListenerActor();
				Gdx.app.log("Button", ""+bt.getDirection());
				
					pacman.setDirection(bt.getDirection());
				if(ispass){
					pacman.setStand(false);
				}
				return super.touchDown(event, x, y, pointer, button);
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				MyTextButton bt=(MyTextButton)event.getListenerActor();
				Gdx.app.log("Button", ""+bt.getDirection());
				pacman.setStand(true);
				super.touchUp(event, x, y, pointer, button);
			}
			
		};
		
		
	}
}
