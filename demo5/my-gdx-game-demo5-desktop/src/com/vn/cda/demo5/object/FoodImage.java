package com.vn.cda.demo5.object;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.vn.cda.demo5.utils.BaseScreen;

public class FoodImage extends Image{
	public FoodImage(TextureRegion food2, int x, int y){
		
		super(food2);
		this.setWidth(32);
		this.setHeight(32);
		this.setX(BaseScreen.VIEWPORT_WIDTH-x-32);
		this.setY(BaseScreen.VIEWPORT_HEIGHT-y-32);
	}	
	
}
