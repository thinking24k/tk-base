package ${basePackage}.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.bqsolo.framework.page.Criteria;
import com.bqsolo.framework.page.PageBean;
import com.bqsolo.main.dao.${tableEntity.className}Dao;
import com.bqsolo.main.entity.${entityName};
import com.bqsolo.main.service.${tableEntity.className}Service;
/** 
* @ClassName: ${tableEntity.className}Service 
* @Description: 本类是由代码生成器自动生成${entityName}逻辑层(Service)
* @company 
* @author ${builderAuthor}
* @Email ${builderEmail}
* @date ${nowDate}
*  
*/
@Service
public class ${tableEntity.className}ServiceImpl implements ${tableEntity.className}Service {
	
	private static Logger logger = Logger.getLogger(${tableEntity.className}ServiceImpl.class);
	
	@Resource
	private ${tableEntity.className}Dao ${lowercaseClassName}Dao;

	@Override
	public long getCount(Criteria<${tableEntity.className}Entity> criteria) {
		if(null == criteria){
			return 0L;
		}
		return ${lowercaseClassName}Dao.getCount(criteria);
	}

	@Override
	public List<${tableEntity.className}Entity> queryForList(Criteria<${tableEntity.className}Entity> criteria) {
		if(null == criteria){
			return null;
		}
		return ${lowercaseClassName}Dao.queryForList(criteria);
	}

	@Override
	public List<${tableEntity.className}Entity> queryForPageList(Criteria<${tableEntity.className}Entity> criteria) {
		if(null == criteria){
			return null;
		}
		return ${lowercaseClassName}Dao.queryForPageList(criteria);
	}

	@Override
	public ${tableEntity.className}Entity getById(Integer pk) {
		if(null == pk){
			return null;
		}
		return ${lowercaseClassName}Dao.getById(pk);
	}

	@Override
	public List<${tableEntity.className}Entity> getByIds(List<Integer> pks) {
		if(null == pks || pks.isEmpty()){
			return null;
		}
		return ${lowercaseClassName}Dao.getByIds(pks);
	}

	@Override
	public Integer doAdd(${tableEntity.className}Entity ${lowercaseClassName}) {
		if(null == ${lowercaseClassName}){
			return null;
		}
		return ${lowercaseClassName}Dao.doAdd(${lowercaseClassName});
	}

	@Override
	public Integer doAddBatch(List<${tableEntity.className}Entity> list) {
		if(null == list || list.isEmpty()){
			return null;
		}
		return ${lowercaseClassName}Dao.doAddBatch(list);
	}

	@Override
	public Integer doUpdate(${tableEntity.className}Entity ${lowercaseClassName}) {
		if(null == ${lowercaseClassName}){
			return null;
		}
		return ${lowercaseClassName}Dao.doUpdate(${lowercaseClassName});
	}

	@Override
	public Integer doDelete(Integer pk) {
		if(null == pk){
			return null;
		}
		return ${lowercaseClassName}Dao.doDelete(pk);
	}

	@Override
	public Integer doDeletes(List<Integer> pks) {
		if(null == pks || pks.isEmpty()){
			return null;
		}
		return ${lowercaseClassName}Dao.doDeletes(pks);
	}

	@Override
	public Integer doRemove(${tableEntity.className}Entity ${lowercaseClassName}) {
		if(null == ${lowercaseClassName}){
			return null;
		}
		return ${lowercaseClassName}Dao.doRemove(${lowercaseClassName});
	}

	@Override
	public PageBean<${tableEntity.className}Entity> pageQuery(Criteria<${tableEntity.className}Entity> criteria) {
		if(null == criteria){
			return null;
		}
		//获取总数
		long rowCount = ${lowercaseClassName}Dao.getCount(criteria);
		if(0 == rowCount ){
			return criteria.getPageBean();
		}
		criteria.getPageBean().setRowCount(rowCount);
		//获取列表
		List<${tableEntity.className}Entity> list = ${lowercaseClassName}Dao.queryForPageList(criteria);
		criteria.getPageBean().setData(list);
		return criteria.getPageBean();
	}
	
}
