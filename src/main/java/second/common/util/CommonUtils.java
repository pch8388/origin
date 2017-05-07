package second.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import second.account.dto.AccountDTO;

public class CommonUtils {

	public static String getRandomString(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static Map<String,String> accountSum(List<AccountDTO> list) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		
		for(int i=0; i<list.size(); i++){
			if(list.get(i).getClassification().equals("식비")){
				if(map.get("식비")!=null){
					map.put(list.get(i).getClassification(), sumAccount(list, i, "식비", map));
				}else{
					map.put(list.get(i).getClassification(), sumAccount(list, i));
				}
			}else if(list.get(i).getClassification().equals("주거비")){
				if(map.get("주거비")!=null){
					map.put(list.get(i).getClassification(), sumAccount(list, i, "주거비", map));
				}else{
					map.put(list.get(i).getClassification(), sumAccount(list, i));
				}
			}else if(list.get(i).getClassification().equals("통신비")){
				if(map.get("통신비")!=null){
					map.put(list.get(i).getClassification(), sumAccount(list, i, "통신비", map));
				}else{
					map.put(list.get(i).getClassification(), sumAccount(list, i));
				}
			}else if(list.get(i).getClassification().equals("생활용품")){
				if(map.get("생활용품")!=null){
					map.put(list.get(i).getClassification(), sumAccount(list, i, "생활용품", map));
				}else{
					map.put(list.get(i).getClassification(), sumAccount(list, i));
				}
			}else if(list.get(i).getClassification().equals("의복/미용")){
				if(map.get("의복/미용")!=null){
					map.put(list.get(i).getClassification(), sumAccount(list, i, "의복/미용", map));
				}else{
					map.put(list.get(i).getClassification(), sumAccount(list, i));
				}
			}else if(list.get(i).getClassification().equals("건강/문화")){
				if(map.get("건강/문화")!=null){
					map.put(list.get(i).getClassification(), sumAccount(list, i, "건강/문화", map));
				}else{
					map.put(list.get(i).getClassification(), sumAccount(list, i));
				}
			}else if(list.get(i).getClassification().equals("교육/육아")){
				if(map.get("교육/육아")!=null){
					map.put(list.get(i).getClassification(), sumAccount(list, i, "교육/육아", map));
				}else{
					map.put(list.get(i).getClassification(), sumAccount(list, i));
				}
			}else if(list.get(i).getClassification().equals("교통/차량")){
				if(map.get("교통/차량")!=null){
					map.put(list.get(i).getClassification(), sumAccount(list, i, "교통/차량", map));
				}else{
					map.put(list.get(i).getClassification(), sumAccount(list, i));
				}
			}else if(list.get(i).getClassification().equals("경조사/회비")){
				if(map.get("경조사/회비")!=null){
					map.put(list.get(i).getClassification(), sumAccount(list, i, "경조사/회비", map));
				}else{
					map.put(list.get(i).getClassification(), sumAccount(list, i));
				}
			}else if(list.get(i).getClassification().equals("세금/이자")){
				if(map.get("세금/이자")!=null){
					map.put(list.get(i).getClassification(), sumAccount(list, i, "세금/이자", map));
				}else{
					map.put(list.get(i).getClassification(), sumAccount(list, i));
				}
			}else if(list.get(i).getClassification().equals("용돈/기타")){
				if(map.get("용돈/기타")!=null){
					map.put(list.get(i).getClassification(), sumAccount(list, i, "용돈/기타", map));
				}else{
					map.put(list.get(i).getClassification(), sumAccount(list, i));
				}
			}
		}
		
		return map;
	}
	
	private static String sumAccount(List<AccountDTO> list,int i){
		return String.valueOf(Integer.parseInt(list.get(i).getCash())+Integer.parseInt(list.get(i).getCard()));
	}
	private static String sumAccount(List<AccountDTO> list,int i,String classification,Map<String,String> map){
		return String.valueOf(Integer.parseInt(map.get(classification)+list.get(i).getCash())+Integer.parseInt(list.get(i).getCard()));
	}
}
