/**   
* @Title: JsonTimestampDeSerializer.java 
* @Package com.calabar.util 
* @Description: TODO
* @author xuefeng.gao
* @date 2014-11-24 
* @version V1.0   
*/ 


package com.xxwl.tk.framework.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/** 
 * @ClassName: JsonTimestampDeSerializer 
 * @Description: TODO
 * @company 
 * @author x
 * @Email x
 * @date x
 *  
 */
public class JsonDateDeserializer  extends JsonDeserializer<Date> {  

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext context)
			throws IOException, JsonProcessingException {
		Date date = null;  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
        try {
        	date = sdf.parse(jp.getText());
        } catch (Exception e) {  
            e.printStackTrace();
        }  
        return date;  
	}  
} 
