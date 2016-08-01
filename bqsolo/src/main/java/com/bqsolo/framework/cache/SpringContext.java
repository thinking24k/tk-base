package com.bqsolo.framework.cache;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * 
 * @ClassName: SpringContext 
 * @Description: 获取spring容器
 * @company 
 * @author yixiang.deng
 * @Email 553067271@qq.com
 * @date 2016年8月1日 
 *
 */
public class SpringContext implements ApplicationContextAware {

    protected static ApplicationContext context;
    
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

}