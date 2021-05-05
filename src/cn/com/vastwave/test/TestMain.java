package cn.com.vastwave.test;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import cn.com.vastwave.entity.AddressInfo;
import cn.com.vastwave.entity.AddressProperty;
import cn.com.vastwave.entity.OtherInfo;
import cn.com.vastwave.entity.UserInfo;
import cn.com.vastwave.msg.CreatJsonMsg;

public class TestMain {
	public static void main(String[] args) {
		UserInfo userInfo = new UserInfo();
		userInfo.setIdCard("5224194910012154");
		userInfo.setIdType("identify");
		
		List<AddressInfo> addressInfos=new ArrayList<AddressInfo>();
		AddressInfo ads=new AddressInfo();
		ads.setCity("110000");
		ads.setProvince("110000");
		addressInfos.add(ads);
		
		AddressProperty ap= new AddressProperty();
		ap.setArea(1000.12);
		ap.setLeval("1");
		ads.setAdrProperty(ap);
		
		userInfo.setAds(addressInfos);
		
		
		OtherInfo oInfo=new OtherInfo();
		oInfo.setSkillLevl("p4");
		userInfo.setOtherInfo(oInfo);
		
		
			CreatJsonMsg creatJsonMsg=	new CreatJsonMsg();
			creatJsonMsg.setObj(userInfo);
			JSONObject msg=new 	JSONObject();
				try {
					
					creatJsonMsg.getReqJsonMsg(msg,"message");
					
					
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (IntrospectionException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				}
			
		}
}

