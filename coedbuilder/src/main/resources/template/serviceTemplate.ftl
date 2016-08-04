package ${basePackage}.service;

import com.bqsolo.framework.service.BaseService;
import com.bqsolo.framework.page.PageBean;
import com.bqsolo.framework.page.Criteria;
import ${basePackage}.entity.${entityName};
/** 
* @ClassName: ${tableEntity.className}Service 
* @Description: 本类是由代码生成器自动生成${entityName}逻辑层(Service)
* @company 
* @author ${builderAuthor}
* @Email ${builderEmail}
* @date ${nowDate}
*  
*/ 

public interface ${tableEntity.className}Service extends BaseService<${entityName}, Integer> {
	

	public  PageBean<${entityName}> pageQuery(Criteria<${entityName}> criteria);
	
	

}
