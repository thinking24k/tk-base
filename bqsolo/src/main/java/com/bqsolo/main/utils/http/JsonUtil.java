package com.bqsolo.main.utils.http;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.bqsolo.main.utils.StringUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
/**
 * @ClassName: JsonUtil
 * @Description: TODO
 * @author xuefeng.gao
 * @date 2014-10-29
 * 
 */
public class JsonUtil {
	protected static final Logger logger = Logger.getLogger(HttpUtil.class);
    private static ObjectMapper mapper = new ObjectMapper();
    static{
    	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES ,false);
    	mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
    	mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    	mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    
    }
    
    /** 
    * @Title: convertList2Json 
    * @Description: 将List对象转换为Json字符串(支持泛型)
    * @param objects
    * @param clazz
    * @throws IOException  
    * @return String   
    */ 
    public static <T> String convertList2Json(List<T> objects, Class clazz) throws IOException {
     //   mapper = new ObjectMapper().setVisibility(arg0, arg1)
     //   ObjectWriter typedWriter = mapper.writerWithType(mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    	mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        return mapper.writeValueAsString(objects);
    }
    

    /** 
    * @Title: convertObject2Json 
    * @Description: 将Object对象转换为Json字符串
    * @param object
    * @param clazz
    * @return
    * @throws IOException  
    */ 
    public static String convertObject2Json(Object object,Class clazz) throws IOException {
      //  mapper = new ObjectMapper().setVisibility(JsonMethod, JsonAutoDetect.Visibility.ANY);
        return mapper.writeValueAsString(object);
    }    
    

    /** 
    * @Title: convertJson2List 
    * @Description: 将Json字符串 转换为List泛型对象
    * @param jsonStr json字符串
    * @param collectionClass List泛型
    * @param elementClasses 元素类型
    * @return 泛型对象
    * @throws Exception  
    */ 
    public static <T> T convertJson2List(String jsonStr, Class<?> collectionClass, Class<?>... elementClasses) throws Exception {
    	if(StringUtil.isEmpty(jsonStr)){
    		return null;
    	}   	
           JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
           return mapper.readValue(jsonStr, javaType);
    } 

    
    /** 
    * @Title: convertJson2Object 
    * @Description: 将Json字符串 转换为Object对象（不支持集合对象）
    * @param jsonStr
    * @param targetClazz
    * @return 普通对象
    * @throws IOException  
    */ 
    public static <T> T convertJson2Object(String jsonStr, Class<T> targetClazz) throws IOException {
    	if(StringUtil.isEmpty(jsonStr)){
    		return null;
    	}
        return mapper.readValue(jsonStr,targetClazz);
    } 
    

    /**
    * <p>Title: convertJson2Object</p>
    * <p>Description: 将JSON字符串转换成 MessageDTO</p>
    * @param jsonStr
    * @param targetClazz MessageDTO.class
    * @param contentClazz  content 类 对象
    * @return
    * @throws IOException
    */
    public static <T, K> T convertJson2Object(String jsonStr, Class<T> targetClazz, Class<K> contentClazz) throws IOException {
    	if(StringUtil.isEmpty(jsonStr)){
    		return null;
    	}
    	JavaType javaType = mapper.getTypeFactory().constructParametricType(targetClazz, contentClazz);
        return mapper.readValue(jsonStr,javaType);
    }     
    
    
    
    
    /** 
    * @Title: convertJson2Object 
    * @Description: 将Json字符串 转换为Object对象（不支持集合对象）
    * @param jsonStr
    * @param targetClazz
    * @return 普通对象
    * @throws IOException  
    */ 
    public static <T> T convertJson2Object(String jsonStr,  TypeReference typeReference) throws IOException {
    	if(StringUtil.isEmpty(jsonStr)){
    		return null;
    	}
        return mapper.readValue(jsonStr,typeReference);
    }

    private static JavaType getJavaType(Type type, Class<?> clazz) {
        return (clazz != null) ?
               // TypeFactory.type(type, TypeFactory.type(clazz)):TypeFactory.type(type);
                TypeFactory.defaultInstance().constructType(type, clazz):TypeFactory.defaultInstance().constructType(type);
    }

    public static Object readFromStream(InputStream inputStream, Class<?> clazz)
            throws IOException {
        JavaType javaType = getJavaType(clazz, null);
        return mapper.readValue(inputStream, javaType);
    }	
		
	public static String string2Json(String s) {
		StringBuilder sb = new StringBuilder(s.length() + 20);
		sb.append('\"');
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '\"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '/':
				sb.append("\\/");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			default:
				sb.append(c);
			}
		}
		sb.append('\"');

		return sb.toString();
	}

	public static String number2Json(Number number) {
		return number.toString();
	}

	public static String boolean2Json(Boolean bool) {
		return bool.toString();
	}
	
	public static String array2Json(Object[] array) { 
	    if (array.length==0) 
	        return "[]"; 
	    StringBuilder sb = new StringBuilder(array.length << 4); 
	    sb.append('['); 
	    for (Object o : array) { 
	        sb.append(toJson(o)); 
	        sb.append(','); 
	    } 
	    sb.setCharAt(sb.length()-1, ']'); 
	    return sb.toString(); 
	 }
	
	public static String map2Json(Map<String, Object> map) { 
	    if (map.isEmpty()) 
	        return "{}"; 
	    StringBuilder sb = new StringBuilder(map.size() << 4); 
	    sb.append('{'); 
	    Set<String> keys = map.keySet(); 
	    for (String key : keys) { 
	        Object value = map.get(key); 
	        sb.append('\"'); 
	        sb.append(key); 
	        sb.append('\"'); 
	        sb.append(':'); 
	        sb.append(toJson(value)); 
	        sb.append(','); 
	    } 
	    sb.setCharAt(sb.length() - 1, '}'); 
	    return sb.toString(); 
	 }
	
	@SuppressWarnings("unchecked")
	public static String toJson(Object o) { 
	    if (o==null) 
	        return "null"; 
	    if (o instanceof String) 
	        return string2Json((String)o); 
	    if (o instanceof Boolean) 
	        return boolean2Json((Boolean)o); 
	    if (o instanceof Number) 
	        return number2Json((Number)o); 
	    if (o instanceof Map) 
	        return map2Json((Map<String, Object>)o); 
	    if (o instanceof Object[]) 
	        return array2Json((Object[])o); 
	    throw new RuntimeException("Unsupported type: " + o.getClass().getName()); 
	 }

	//新添加的两个方法
    /**
     * 根据json串和节点名返回节点
     * 
     * @param json
     * @param nodeName
     * @return
     */
    public static JsonNode getNode(String json, String nodeName) {
        JsonNode node = null;
        try {
            node = mapper.readTree(json);
            return node.get(nodeName);
        } catch (JsonProcessingException e) {
        	logger.error("Json Error:"+e.getMessage());
        } catch (IOException e) {
        	logger.error("Json Error:"+e.getMessage());
        }
        return node;
    }
 
    /**
     * JsonNode转换为Java泛型对象，可以是各种类型，此方法最为强大。用法看测试用例。
     * 
     * @param <T>
     * @param node JsonNode
     * @param tr TypeReference,例如: new TypeReference< List<FamousUser> >(){}
     * @return List对象列表
     */
    public static <T> T jsonNode2GenericObject(JsonNode node, TypeReference<T> tr) {
        if (node == null || "".equals(node)) {
            return null;
        } else {
            try {
                return (T) mapper.readValue(node.traverse(), tr);
            } catch (Exception e) {
            	logger.error("Json Error:"+e.getMessage());
            }
        }
        return null;
    }
 

}
