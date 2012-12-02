package com.vn.cda.demo5.uiview;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MyLabel extends Label{


	//Khai báo các style tương ứng với các hướng di chuyển
	private static final String[] STYLES=new String[]{"up","down","left","right"};
	
	//Khai báo các label tương ứng với các hướng di chuyển
	private static final String[] LABELS=new String[]{"�?i lên","�?i xuống","Sang trái","Sang phải"};
	
	public MyLabel(CharSequence text, Skin skin, String styleName) {
		super(text, skin, styleName);
		// TODO Auto-generated constructor stub
	}
	
	//Khởi tạo label với trạng thái cho trước
	public static MyLabel create(Skin skin,int direction){
		return new MyLabel(LABELS[direction],skin,STYLES[direction]);
	}

	
}
