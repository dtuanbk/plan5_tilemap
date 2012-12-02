package com.vn.cda.demo5.uiview;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MyButton extends Button{
	
	//Biến xác định button thuộc loại nào
	private int driection;
	
	//Kích thước Button
	private static int WIDTH=125;
	private static int HEIGHT=75;
	
	
	
	public MyButton(int driection,Skin skin) {
		super(skin);
		// TODO Auto-generated constructor stub
		this.driection=driection;
	}
	
	public int getDirection(){
		return this.driection;
	}
	 
}
