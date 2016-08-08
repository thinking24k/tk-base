package com.tk.base.builder.util;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.tk.base.builder.entity.ColumnEntity;
import com.tk.base.builder.entity.TableEntity;

/**
* @ClassName: PdmReadUtil 
* @Description: pdm文件读取工具类，主要用于解析pdm文件
* @company 
* @author yixiang.deng
* @Email 553067271@qq.com
* @date 2015年6月30日 
*
 */
public class PdmReadUtil {
	private static Logger logger=Logger.getLogger(PdmReadUtil.class);
	
	private static final String TABLEXMLPATH = "/Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table";
	/**
	 * 
	 * @Title: getDoc 
	 * @Description: 获取Document对象
	 * @param filePath
	 * @return
	 */
	public static Document getDoc(String filePath){
		  File f = new File(filePath);  
	        SAXReader sr = new SAXReader();  
	        Document doc = null;  
	        try {  
	            doc = sr.read(f);  
	        } catch (DocumentException e) {  
	            e.printStackTrace();  
	        }
	        return doc;
	}
	/**
	 * 
	 * @Title: getDoc 
	 * @Description: 通过inputStream
	 * @param inputStream
	 * @return Document
	 */
	public static Document getDoc(InputStream inputStream){
	        SAXReader sr = new SAXReader();  
	        Document doc = null;  
	        try {  
	            doc = sr.read(inputStream);  
	        } catch (DocumentException e) {  
	            e.printStackTrace();  
	        }
	        return doc;
	}
	/**
	 * 
	 * @Title: parsePDM_VO 
	 * @Description: 解析pdm文件，对应实体
	 * @param doc 文档对象
	 * @return List<TableEntity>
	 */
	public static List<TableEntity> parsePDM_VO(Document doc) {  
        List<TableEntity> voS = new ArrayList<TableEntity>();  
        TableEntity vo = null;  
        if(StringUtil.isEmpty(doc)){
        	return null;
        }
        @SuppressWarnings("unchecked")
		Iterator<Element> itr= doc.selectNodes(TABLEXMLPATH).iterator();
        while (itr.hasNext()) {  
            vo = new TableEntity();  
            Element e_table = (Element) itr.next();  
            vo.setTableName(e_table.elementTextTrim("Code"));
            vo.setAnnotations(e_table.elementTextTrim("Comment"));  
            vo.setColumns( getCols(e_table)); 
            voS.add(vo);  
        }  
        return voS;
    }
	//获取所有列
	private static List<ColumnEntity> getCols(Element e_table) {
        List<ColumnEntity> cols =  new ArrayList<ColumnEntity>();  
		@SuppressWarnings("unchecked")
		Iterator<Element> itr1 = e_table.element("Columns").elements("Column").iterator();  
		while (itr1.hasNext()) {  
		    cols.add(mapperCol(e_table, itr1));  
		}
		return cols;
	}
	//映射列属性值
	private static ColumnEntity mapperCol(Element e_table, Iterator<Element> itr1) {
		ColumnEntity col=null;
		try {  
			col = new ColumnEntity();  
		    Element e_col = (Element) itr1.next();  
		    String pkID = e_col.attributeValue("Id");  
		    //col.setDefaultValue(e_col.elementTextTrim("DefaultValue"));  
		    col.setColumnName(e_col.elementTextTrim("Code"));  
		    col.setColumnAnnotations(e_col.elementTextTrim("Comment"));
		    if(e_col.elementTextTrim("DataType").indexOf("(") >0){  
		        col.setColumnType(e_col.elementTextTrim("DataType").substring(0, e_col.elementTextTrim("DataType").indexOf("(")));  
		    }else {  
		        col.setColumnType(e_col.elementTextTrim("DataType"));  
		    }  
		    //col.setCode(e_col.elementTextTrim("Code"));  
		    col.setDatasize(e_col.elementTextTrim("Length") == null ? null : Integer.parseInt(e_col.elementTextTrim("Length")));  
		    if(e_table.element("Keys")!=null){  
		        String keys_key_id = e_table.element("Keys").element("Key").attributeValue("Id");  
		        String keys_column_ref = e_table.element("Keys").element("Key").element("Key.Columns")  
		                .element("Column").attributeValue("Ref");  
		        String keys_primarykey_ref_id = e_table.element("PrimaryKey").element("Key").attributeValue("Ref");  
		            if (keys_primarykey_ref_id.equals(keys_key_id) && keys_column_ref.equals(pkID)) {  
		                col.setIsPrimaryKey(ColumnEntity.YES);  
		                //vo.setPkField(col.getCode());  
		            }  
		    }  
		} catch (Exception ex) {  
		   logger.error(ex);
		    ex.printStackTrace();  
		}
		return col;
	} 

}
