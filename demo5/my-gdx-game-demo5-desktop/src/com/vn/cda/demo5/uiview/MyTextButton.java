package com.vn.cda.demo5.uiview;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MyTextButton extends TextButton{
	//Biến xác định button thuộc loại nào
	private int driection;
		
	//Kích thước Button
	private static int WIDTH=125;
	private static int HEIGHT=75;
	
	public MyTextButton(int driection,String text, Skin skin) {
		super(text, skin);
		// TODO Auto-generated constructor stub
		this.driection=driection;
		this.setSize(WIDTH, HEIGHT);
	}
	
	
	public int getDirection(){
		return this.driection;
	}
}
