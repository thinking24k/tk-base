package com.bqsolo.framework.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.persistence.Id;

import org.apache.log4j.Logger;


/** 
* @ClassName: EntityReflectUtils 
* @Description: 实体反射解析工具
* @company 
* @author xuefeng.gao
* @Email ourjava@qq.com
* @date 2014-12-13 
*  
*/ 
public class EntityReflectUtil<T> {
	private static Logger logger = Logger.getLogger(EntityReflectUtil.class);
	/** 
	* @Title: getIdField 
	* @Description: 获取实体ID Field
	* @param clazz
	* @return  ID Field
	*/ 
	public static <T> Field getIdField(Class<T> clazz){
		Field idField = null;
		for(Field field : clazz.getDeclaredFields()) {
			if(field.isAnnotationPresent(Id.class)){
				idField = field;
				break;
			}				
		}
		return idField;
	}
	
	/** 
	* @Title: getIdField 
	* @Description: 获取实体ID Field
	* @param clazz
	* @return  ID Field
	*/ 
	public static <T> Field getField(Class<T> clazz,String fieldName){
		Field field = null;
		try {
			field = getDeclaredField(clazz,fieldName);
		} catch (SecurityException e) {
			logger.warn("Field获取错误："+clazz.getSimpleName()+"没有field "+fieldName);
		}
		return field;
	}
	
	
	
	public static <T>  Object getFieldValue(Field field,T obj){
		//抑制Java对其的检查
		field.setAccessible(true) ;
		Object fieldValue = null;
		try {
			fieldValue = field.get(obj);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			logger.error("Field获取错误：",e);
			e.printStackTrace();
		}
		return fieldValue;
	}
	public static <T>  void setFieldValue(Field field,Object fieldValue,T obj) throws IllegalArgumentException, IllegalAccessException{
		//抑制Java对其的检查
		field.setAccessible(true) ;
		field.set(obj, fieldValue);

	}
	
	/** 
	* @Title: getIdField 
	* @Description: 获取实体ID Field
	* @param clazz
	* @return  ID Field
	*/ 
	public static <T> Method getMethod(Class<T> clazz,String methodName){
		Method method = null;

		try {
			method = clazz.getDeclaredMethod(methodName);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			logger.warn("method获取错误："+clazz.getSimpleName()+"没有method "+methodName);
		}

		return method;
	}	
	
	public static <T>  Object getMethodValue(Method method,T obj){
		//抑制Java对其的检查
		method.setAccessible(true) ;
		Object fieldValue = null;
		try {
			fieldValue = method.invoke(obj);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logger.error("Method获取错误：",e);
			e.printStackTrace();
		} 
		return fieldValue;
	}	
	/**
	 * 利用递归找一个类的指定方法，如果找不到，去父亲里面找直到最上层Object对象为止。
	 * 
	 * @param clazz
	 *            目标类
	 * @param methodName
	 *            方法名
	 * @param classes
	 *            方法参数类型数组
	 * @return 方法对象
	 * @throws Exception
	 */
	public static Method getMethod(Class clazz, String methodName,
			Class... classes) throws Exception {
		Method method = null;
		try {
			method = clazz.getDeclaredMethod(methodName, classes);
		} catch (NoSuchMethodException e) {
			try {
				method = clazz.getMethod(methodName, classes);
			} catch (NoSuchMethodException ex) {
				if (clazz.getSuperclass() == null) {
					return method;
				} else {
					method = getMethod(clazz.getSuperclass(), methodName,
							classes);
				}
			}
		}
		return method;
	}

	/**
	 * 
	 * @param obj
	 *            调整方法的对象
	 * @param methodName
	 *            方法名
	 * @param classes
	 *            参数类型数组
	 * @param objects
	 *            参数数组
	 * @return 方法的返回值
	 */
	public static Object invoke(final Object obj, final String methodName,
			final Class[] classes, final Object[] objects) {
		try {
			Method method = getMethod(obj.getClass(), methodName, classes);
			method.setAccessible(true);// 调用private方法的关键一句话
			return method.invoke(obj, objects);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Object invoke(final Object obj, final String methodName,
			Class... classes) {
		return invoke(obj, methodName, classes, new Object[] {});
	}

	public static Object invoke(final Object obj, final String methodName) {
		return invoke(obj, methodName, new Class[] {}, new Object[] {});
	}
	
	/** 
     * 循环向上转型, 获取对象的 DeclaredMethod 
     * @param object : 子类对象 
     * @param methodName : 父类中的方法名 
     * @param parameterTypes : 父类中的方法参数类型 
     * @return 父类中的方法对象 
     */  
      
    public static Method getDeclaredMethod(Object object, String methodName, Class<?> ... parameterTypes){  
        Method method = null ;  
          
        for(Class<?> clazz = object.getClass() ; clazz != Object.class ; clazz = clazz.getSuperclass()) {  
            try {  
                method = clazz.getDeclaredMethod(methodName, parameterTypes) ;  
                return method ;  
            } catch (Exception e) {  
                //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。  
                //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了  
              
            }  
        }     
        return null;  
    }  	
	
    /** 
     * 循环向上转型, 获取对象的 DeclaredField 
     * @param object : 子类对象 
     * @param fieldName : 父类中的属性名 
     * @return 父类中的属性对象 
     */  
      
    public static Field getDeclaredField(Class clazz, String fieldName){  
        Field field = null ;  

          
        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {  
            try {  
                field = clazz.getDeclaredField(fieldName) ;  
                return field ;  
            } catch (Exception e) {  
                //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。  
                //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了  
                  
            }   
        }  
      
        return null;  
    }     
    
        
}
