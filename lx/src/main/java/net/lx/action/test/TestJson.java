package net.lx.action.test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TestJson {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("abc");
		//定义JSON字符串
		String jsonStr = "{\"id\": 2," + 
		             	 " \"title\": \"json title\", " + 
		                 "\"config\": {" +
		                     "\"width\": 34," +
		                     "\"height\": 35," +
		                 "}, \"data\": [" +
		                     "\"JAVA\", \"JavaScript\", \"PHP\"" +
		                 "]}";
		         
		         //转换成为JSONObject对象
		         JSONObject jsonObj = new JSONObject(jsonStr);
		         
		         //从JSONObject对象中获取数据
		         JavaBean bean = new JavaBean();
		         
		         //根据属性名称获取int型数据;
		         bean.setId(jsonObj.getInt("id"));     
		         
		         //根据属性名获取String数据;
		         bean.setTitle(jsonObj.getString("title")); 
		         
		         //根据属性名获取JSONObject类
		         JSONObject config = jsonObj.getJSONObject("config");
		         bean.setWidth(config.getInt("width"));
		         bean.setHeight(config.getInt("height"));
		         
		         //根据属性名获取JSONArray数组
		         JSONArray data = jsonObj.getJSONArray("data");
		         for(int index = 0, length = data.length(); index < length; index++) {
		             //这里在org.json.JSONArray对象中居然没有找到toArray方法，求各位网友给出解决办法啊！
		 //            bean.setLanguages(String[]);
		         }    
	}
	
}

class JavaBean{
	     private int id ;
	     private String title;
	     private int width;
	     private int height;
	     private String[] languages;
	     
	         //这里省略了设置器和访问器
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public int getWidth(){
		return width;
	}
	public void setWidth(int width){
		this.width = width;
	}
	public int getHeight(){
		return height;
	}
	public void setHeight(int height){
		this.height = height;
	}
	public String[] getLanguages(){
		return languages;
	}
	public void setLanguages(String[] languages){
		this.languages = languages;
	}
}