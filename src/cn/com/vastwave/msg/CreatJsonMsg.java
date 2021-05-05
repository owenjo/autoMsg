package cn.com.vastwave.msg;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;






/**
 * @author Owenjo
 * by entity create json message
 * 
 * 支持类似如下报文格式
 * {
	"message": {
		"ads": [
			{
				"country": "",
				"province": "110000",
				"city": "110000",
				"AddressProperty": {
					"area": 1000.12,
					"leval": "1"
				},
				"postCode": ""
			}
		],
		"idType": "identify",
		"idCard": "5224194910012154",
		"OtherInfo": {
			"face": "",
			"skillLevl": "p4",
			"hobby": ""
		}
	}
}
 * 
 * 
 * 
 * 
 */
public class CreatJsonMsg {
	
	private JSONObject jsonMsg=new JSONObject();
	
	private Object obj;
	
	/**
	 * 创建json报文 
	 * @param root :
	 * @param nodeName
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public String getReqJsonMsg(JSONObject root,String nodeName) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		Class<? extends Object> cls = obj.getClass();
		Field[] fields= cls.getDeclaredFields();//
		
		for (Field field : fields) {
			String fieldName = field.getName();
				String typeName = field.getGenericType().getTypeName();
				
				PropertyDescriptor pd = new PropertyDescriptor(fieldName, obj.getClass());
				//获得get方法
				Method readMethod = pd.getReadMethod();
				if ("java.lang.String".equals(typeName)) {
					//执行get方法
					String val = (String)readMethod.invoke(obj);
					if (val==null) {
						val="";
					}
					root.put(fieldName, val);
				}else if ("java.lang.Integer".equals(typeName) || "int".equals(typeName)) {
					//执行get方法
					Integer val = (Integer)readMethod.invoke(obj);
					if (val==null) {
						root.put(fieldName, "");
					}else {
						root.put(fieldName, val);
					}
				}else if ("java.lang.Boolean".equals(typeName) || "boolean".equals(typeName)) {
					//执行get方法
					Boolean val = (Boolean)readMethod.invoke(obj);
					if (val==null) {
						root.put(fieldName, "");
					}else {
						root.put(fieldName, val);
					}
				}else if ("java.lang.Float".equals(typeName) || "float".equals(typeName)) {
					//执行get方法
					Float val = (Float)readMethod.invoke(obj);
					if (val==null) {
						root.put(fieldName, "");
					}else {
						root.put(fieldName, val);
					}
				}else if ("java.lang.Double".equals(typeName) || "long".equals(typeName)) {
					//执行get方法
					Double val = (Double)readMethod.invoke(obj);
					if (val==null) {
						root.put(fieldName, "");
					}else {
						root.put(fieldName, val);
					}
				}else if ("java.lang.Short".equals(typeName)||"short".equals(typeName)) {
					//执行get方法
					Short val = (Short)readMethod.invoke(obj);
					if (val==null) {
						root.put(fieldName, "");
					}else {
						root.put(fieldName, val);
					}
				}else if (typeName!=null && 
						(typeName.startsWith("java.util.List") ||
						typeName.startsWith("java.util.ArrayList") || 
						typeName.startsWith("ava.util.LinkedList"))) {
					dealListType(root, fieldName,field);
				} else {//其他的实体类类型
					PropertyDescriptor instpd = new PropertyDescriptor(fieldName, obj.getClass());
					Method insMethod = instpd.getReadMethod();
					Object instObj = insMethod.invoke(obj);
					if (instObj==null) {
						Class<?> generCls = instpd.getPropertyType();
						instObj = generCls.newInstance();
					}
					JSONObject jsonObject=new JSONObject();
					String name = instObj.getClass().getName();
					name=name.substring(name.lastIndexOf(".")+1);
					dealObjMsg(jsonObject,instObj);
					root.put(name, jsonObject);
				}
		}
		jsonMsg.put(nodeName, root);
		String jstr = jsonMsg.toString();
		System.out.println(jstr);
		return jsonMsg.toString();
	}
	
	/**
	 *  当变量是一个对象时 
	 * @param jsonObject
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public String dealObjMsg(JSONObject jsonObject,Object obj) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		
		Class<? extends Object> cls = obj.getClass();
		Field[] fields= cls.getDeclaredFields();//获得所有属性
		
		for (Field field : fields) {
			String fieldName = field.getName();
				String typeName = field.getGenericType().getTypeName();
				
				PropertyDescriptor pd = new PropertyDescriptor(fieldName, obj.getClass());
				//获得get方法
				Method readMethod = pd.getReadMethod();
				if ("java.lang.String".equals(typeName)) {
					//执行get方法
					String val = (String)readMethod.invoke(obj);
					if (val==null) {
						val="";
					}
					jsonObject.put(fieldName, val);
				}else if ("java.lang.Integer".equals(typeName) || "int".equals(typeName)) {
					//执行get方法
					Integer val = (Integer)readMethod.invoke(obj);
					if (val==null) {
						jsonObject.put(fieldName, "");
					}else {
						jsonObject.put(fieldName, val);
					}
				}else if ("java.lang.Boolean".equals(typeName) || "boolean".equals(typeName)) {
					//执行get方法
					Boolean val = (Boolean)readMethod.invoke(obj);
					if (val==null) {
						jsonObject.put(fieldName, "");
					}else {
						jsonObject.put(fieldName, val);
					}
				}else if ("java.lang.Float".equals(typeName) || "float".equals(typeName)) {
					//执行get方法
					Float val = (Float)readMethod.invoke(obj);
					if (val==null) {
						jsonObject.put(fieldName, "");
					}else {
						jsonObject.put(fieldName, val);
					}
				}else if ("java.lang.Double".equals(typeName) || "long".equals(typeName)) {
					//执行get方法
					Double val = (Double)readMethod.invoke(obj);
					if (val==null) {
						jsonObject.put(fieldName, "");
					}else {
						jsonObject.put(fieldName, val);
					}
				}else if ("java.lang.Short".equals(typeName)||"short".equals(typeName)) {
					//执行get方法
					Short val = (Short)readMethod.invoke(obj);
					if (val==null) {
						jsonObject.put(fieldName, "");
					}else {
						jsonObject.put(fieldName, val);
					}
				}else if (typeName!=null && 
						(typeName.startsWith("java.util.List") ||
						typeName.startsWith("java.util.ArrayList") || 
						typeName.startsWith("ava.util.LinkedList"))) {
					dealListType(jsonObject, fieldName,field);
				} else {//其他的实体类类型
					PropertyDescriptor instpd = new PropertyDescriptor(fieldName, obj.getClass());
					Method insMethod = instpd.getReadMethod();
					Object instObj = insMethod.invoke(obj);
					if (instObj==null) {
						Class<?> generCls = instpd.getPropertyType();
						instObj = generCls.newInstance();
					}
					String name = instObj.getClass().getName();
					name=name.substring(name.lastIndexOf(".")+1);
					JSONObject json=new JSONObject();
					dealObjMsg(json,instObj);
					jsonObject.put(name, json);
				}
		}
		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}
	
	
	/**
	 * 当变量是一个list时
	 * @param fieldName
	 * @param arrayField
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException 
	 */
	public void dealListType(JSONObject json, String fieldName,Field arrayField) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		JSONObject arrayJson =new JSONObject();
		JSONArray jsonArray =new JSONArray();
		//得到当前类
		PropertyDescriptor arrpd = new PropertyDescriptor(fieldName, obj.getClass());
		Method arrMethod =arrpd.getReadMethod();
		List<Object> lists = (List)arrMethod.invoke(obj);
		// 当前集合的泛型类型
        Type genericType = arrayField.getGenericType();
         if (genericType instanceof ParameterizedType) {
             ParameterizedType pt = (ParameterizedType) genericType;
             // 得到泛型里的class类型对象
             Class<?> arrCls = (Class<?>)pt.getActualTypeArguments()[0];
			//至少组装一份
			if (lists==null) {
		
                Field[] gfields = arrCls.getDeclaredFields();
                for (Field gfield : gfields) {
                	String name = gfield.getName();
                	String typeName = gfield.getGenericType().getTypeName();
                	if ("java.lang.String".equals(typeName)) {
						arrayJson.put(name, "");
					}else if ("java.lang.Integer".equals(typeName) || "int".equals(typeName)) {
						arrayJson.put(name, "");
					}else if ("java.lang.Boolean".equals(typeName) || "boolean".equals(typeName)) {
							arrayJson.put(name, "");
					}else if ("java.lang.Float".equals(typeName) || "float".equals(typeName)) {
						arrayJson.put(name, "");
					}else if ("java.lang.Double".equals(typeName) || "long".equals(typeName)) {
						arrayJson.put(name, "");
					}else if ("java.lang.Short".equals(typeName)||"short".equals(typeName)) {
						arrayJson.put(name, "");
					}else if ("java.util.List".equals(typeName) 
						|| "java.util.ArrayList".equals(typeName)
						|| "java.util.LinkedList".equals(typeName)) {
						dealListType(arrayJson,name, gfield);
					}else {//其他的实体类类型
						 Class<? extends Object> cls = gfield.getClass();
						Object object= cls.newInstance();
						JSONObject jsonObject=new JSONObject();
						String objName = cls.getName();
						objName=objName.substring(objName.lastIndexOf(".")+1);
						dealObjMsg(jsonObject,object);
						arrayJson.put(objName, jsonObject);
					}
				}
                jsonArray.put(arrayJson);
                json.put(fieldName,jsonArray );
                return;
	        }
			for (int i = 0; i < lists.size(); i++) {
				Object arrObj = lists.get(i);
				Field[] fields = arrObj.getClass().getDeclaredFields();
				for (Field field : fields) {
					String name = field.getName();
					String typeName = field.getGenericType().getTypeName();
					
					PropertyDescriptor pd = new PropertyDescriptor(name, arrCls);
					//获得get方法
					Method readMethod = pd.getReadMethod();
					
					if ("java.lang.String".equals(typeName)) {
						//执行get方法
						String val = (String)readMethod.invoke(arrObj);
						if (val==null) val="";
						arrayJson.put(name, val);
					}else if ("java.lang.Integer".equals(typeName) || "int".equals(typeName)) {
						//执行get方法
						Integer val = (Integer)readMethod.invoke(arrObj);
						if (val==null) {
							arrayJson.put(name, "");
						}else
							arrayJson.put(name, val);
					}else if ("java.lang.Boolean".equals(typeName) || "boolean".equals(typeName)) {
						//执行get方法
						Boolean val = (Boolean)readMethod.invoke(arrObj);
						if (val==null) {
							arrayJson.put(name, "");
						}else
							arrayJson.put(name, val);
					}else if ("java.lang.Float".equals(typeName) || "float".equals(typeName)) {
						//执行get方法
						Float val = (Float)readMethod.invoke(arrObj);
						if (val==null) {
							arrayJson.put(name, "");
						}else
							arrayJson.put(name, val);
					}else if ("java.lang.Double".equals(typeName) || "long".equals(typeName)) {
						//执行get方法
						Double val = (Double)readMethod.invoke(arrObj);
						if (val==null) {
							arrayJson.put(name, "");
						}else
							arrayJson.put(name, val);
					}else if ("java.lang.Short".equals(typeName)||"short".equals(typeName)) {
						//执行get方法
						Short val = (Short)readMethod.invoke(arrObj);
						if (val==null) {
							arrayJson.put(name, "");
						}else
							arrayJson.put(name, val);
					}else if ("java.util.List".equals(typeName) 
						|| "java.util.ArrayList".equals(typeName)
						|| "java.util.LinkedList".equals(typeName)) {
						dealListType(arrayJson,name, field);
					}else {//其他的实体类类型
						PropertyDescriptor instpd = new PropertyDescriptor(name, arrCls);
						Method insMethod = instpd.getReadMethod();
						Object instObj = insMethod.invoke(arrObj);
						if (instObj==null) {
							Class<?> generCls = instpd.getPropertyType();
							instObj = generCls.newInstance();
						}
						JSONObject jsonObject=new JSONObject();
						String instName = instObj.getClass().getName();
						instName=instName.substring(instName.lastIndexOf(".")+1);
						dealObjMsg(jsonObject,instObj);
						arrayJson.put(instName, jsonObject);
					}
				}
				jsonArray.put(arrayJson);
				json.put(fieldName,jsonArray );
			}
		}
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public void setJsonMsg(JSONObject jsonMsg) {
		this.jsonMsg = jsonMsg;
	}

}
