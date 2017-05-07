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
	
	public static Map<String,Object> accountSum(List<AccountDTO> list) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		
		for(int i=0; i<list.size(); i++){
			if(list.get(i).getClassification().equals("식비")){
				if(map.containsKey("식비")){
					map.put(list.get(i).getClassification(), sumAccount(list, i, "식비", map));
				}else{
					map.put(list.get(i).getClassification(), sumAccount(list, i));
				}
			}else if(list.get(i).getClassification().equals("주거비")){
				if(map.containsKey("주거비")){
					map.put(list.get(i).getClassification(), sumAccount(list, i, "주거비", map));
				}else{
					map.put(list.get(i).getClassification(), sumAccount(list, i));
				}
			}else if(list.get(i).getClassification().equals("통신비")){
				if(map.containsKey("통신비")){
					map.put(list.get(i).getClassification(), sumAccount(list, i, "통신비", map));
				}else{
					map.put(list.get(i).getClassification(), sumAccount(list, i));
				}
			}else if(list.get(i).getClassification().equals("생활용품")){
				if(map.containsKey("생활용품")){
					map.put(list.get(i).getClassification(), sumAccount(list, i, "생활용품", map));
				}else{
					map.put(list.get(i).getClassification(), sumAccount(list, i));
				}
			}else if(list.get(i).getClassification().equals("의복/미용")){
				if(map.containsKey("의복/미용")){
					map.put(list.get(i).getClassification(), sumAccount(list, i, "의복/미용", map));
				}else{
					map.put(list.get(i).getClassification(), sumAccount(list, i));
				}
			}else if(list.get(i).getClassification().equals("건강/문화")){
				if(map.containsKey("건강/문화")){
					map.put(list.get(i).getClassification(), sumAccount(list, i, "건강/문화", map));
				}else{
					map.put(list.get(i).getClassification(), sumAccount(list, i));
				}
			}else if(list.get(i).getClassification().equals("교육/육아")){
				if(map.containsKey("교육/육아")){
					map.put(list.get(i).getClassification(), sumAccount(list, i, "교육/육아", map));
				}else{
					map.put(list.get(i).getClassification(), sumAccount(list, i));
				}
			}else if(list.get(i).getClassification().equals("교통/차량")){
				if(map.containsKey("교통/차량")){
					map.put(list.get(i).getClassification(), sumAccount(list, i, "교통/차량", map));
				}else{
					map.put(list.get(i).getClassification(), sumAccount(list, i));
				}
			}else if(list.get(i).getClassification().equals("경조사/회비")){
				if(map.containsKey("경조사/회비")){
					map.put(list.get(i).getClassification(), sumAccount(list, i, "경조사/회비", map));
				}else{
					map.put(list.get(i).getClassification(), sumAccount(list, i));
				}
			}else if(list.get(i).getClassification().equals("세금/이자")){
				if(map.containsKey("세금/이자")){
					map.put(list.get(i).getClassification(), sumAccount(list, i, "세금/이자", map));
				}else{
					map.put(list.get(i).getClassification(), sumAccount(list, i));
				}
			}else if(list.get(i).getClassification().equals("용돈/기타")){
				if(map.containsKey("용돈/기타")){
					map.put(list.get(i).getClassification(), sumAccount(list, i, "용돈/기타", map));
				}else{
					map.put(list.get(i).getClassification(), sumAccount(list, i));
				}
			}
		}
		
		return map;
	}
	
	private static int sumAccount(List<AccountDTO> list,int i){
		return Integer.parseInt(list.get(i).getCash())+Integer.parseInt(list.get(i).getCard());
	}
	private static int sumAccount(List<AccountDTO> list,int i,String classification,Map<String,Object> map){
		return Integer.parseInt(String.valueOf(map.get(classification)))+Integer.parseInt(list.get(i).getCash())+Integer.parseInt(list.get(i).getCard());
	}
}
