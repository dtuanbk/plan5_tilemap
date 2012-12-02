package com.vn.cda.demo5.uiview;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MySelectBox extends SelectBox{

	//Hằng số chứa nội dung tương ứng với 2 lựa ch�?n
	private static String[]items=new String[]{"Di chuyển vừa","Di chuyển nhanh"};
	
	
	
	//Contructor với listener bên ngoài truy�?n vào
	public MySelectBox(Skin skin) {
		super(items, skin);
	}
	
}
