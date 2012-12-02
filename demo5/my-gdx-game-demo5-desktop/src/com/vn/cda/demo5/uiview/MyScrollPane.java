package com.vn.cda.demo5.uiview;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class MyScrollPane extends ScrollPane{
	//Khai báo thùng chứa cái items
	private static Table container=new Table();
	
	
	public MyScrollPane(Skin skin) {
		super(container,skin);
		clear();
		this.setScrollingDisabled(true, false);
		
	}
	
	//Thêm text vào trong scroll
	public void add(MyLabel label){
		container.add(label);
		container.row();
		this.scrollTo(0, 0, 0, 0);
		this.invalidate();
		
	}
	
	public void clear(){
		container.clear();
		this.invalidate();
	}
}
