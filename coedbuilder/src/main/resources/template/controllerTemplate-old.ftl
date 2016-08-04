package ${basePackage}.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scrt.mi.base.common.attribute.MessageAttribute;
import com.scrt.mi.base.common.exception.BusinessException;
import ${basePackage}.domain.${entityName};
import ${basePackage}.domain.criteria.${tableEntity.className}Criteria;
import ${basePackage}.service.impl.${tableEntity.className}Service;
import com.scrt.mi.framework.domain.criteria.PageBean;
import com.scrt.mi.framework.domain.MessageDTO;
import com.scrt.mi.framework.web.controller.BaseController;

/** 
* @ClassName: ${tableEntity.className}Controller 
* @Description: 本类是由代码生成器自动生成${entityName}逻辑(Controller)层
* @company 
* @author ${builderAuthor}
* @Email ${builderEmail}
* @date ${nowDate}
*  
*/ 
@Controller
@RequestMapping(value ="<#if null != ${platform}>/${platform}<#if><#if null != ${moduleName}>/${moduleName}<#if>/${simpleEntityName}.cmd",method = RequestMethod.POST)
public class ${tableEntity.className}Controller extends BaseController {
	//日志
	private static Logger logger = Logger.getLogger(${tableEntity.className}Controller.class);

	@Resource
	private ${tableEntity.className}Service ${lowercaseClassName}Service;

	/** 
	* @Title: doAdd 
	* @Description: 新增操作
	* @param ${entityParName}  Post提交
	* @param request
	* @param response
	* @throws BusinessException  
	*/ 
	@RequestMapping("/doadd")
	public @ResponseBody MessageDTO doAdd(@RequestBody ${entityName} ${entityParName},HttpServletRequest request,HttpServletResponse response) throws BusinessException{
		// 1.服务器校验
		if(!doAddValidation(${entityParName},request,response))
		{ 
			return this.responseData(false, "", response,
					MessageAttribute.COMMON_ERROR_EMPTY_OBJ);
		}
		//2.业务层调用
		${entityParName} = ${lowercaseClassName}Service.doAdd(${entityParName});
		//3.返回JSON数据
		return this.responseData(true, ${entityParName}, response, MessageAttribute.COMMON_INSERT_SUC);
	}
	
	
	/**
	 * @Title: 更新操作
	 * @Description: 更新${entityName} 表单对象
	 * @param ${entityParName}
	 *            表单DTO对象
	 * @param response
	 * @throws BusinessException
	 */
	@RequestMapping("/doupdate")
	public @ResponseBody MessageDTO doUpdate(@RequestBody ${entityName} ${entityParName},HttpServletRequest request,HttpServletResponse response) throws BusinessException{	
		// 1.服务器校验
		if (!doUpdateValidation(${entityParName}, response)) {
			return this.responseData(false, "", response,
					MessageAttribute.COMMON_ERROR_EMPTY_OBJ);
		}
		//2.验证
		/*${entityName} ${entityParName}Temp = ${lowercaseClassName}Service.doSelectById(${entityParName}.getId());
		if(${entityParName}Temp==null){
			return this.responseData(true, ${entityParName}Temp, response,
					MessageAttribute.COMMON_UPDATE_FAIL);			
		}*/
		// 3.校验成功，进行业务处理
		${entityParName} = ${lowercaseClassName}Service.doUpdate(${entityParName});
		return this.responseData(true, ${entityParName}, response,
				MessageAttribute.COMMON_UPDATE_SUC);
	}	

	
	/**
	 * @Title: 物理删除对象操作
	 * @Description: 针对后台管理平台，统一用该方法名接受本操作.
	 * <p> 1.针对其他APP. 如无特殊情况，尽量用此唯一入口接受本操作.
	 * <p> 2.底层支持 批量删除和 单个删除. 即支持Long/String id. 或者List id.
	 * 不需要更改本方法任何代码. 底层自动识别.
	 * @param Object id 主键.
	 * @param response
	 * @throws BusinessException
	 */
	@RequestMapping("/dodelbyid/{id}")
	public @ResponseBody MessageDTO doDeleteById(@PathVariable("id") Object id, HttpServletResponse response) {
		return deleteById(id,response);
	}		
	
	/**
	 * @Title: 物理删除对象操作
	 * @Description: 针对后台管理平台，统一用该方法名接受本操作.
	 * <p> 1.针对其他APP. 如无特殊情况，尽量用此唯一入口接受本操作.
	 * <p> 2.底层支持 批量删除和 单个删除. 即支持Long/String id. 或者List id.
	 * 不需要更改本方法任何代码. 底层自动识别.
	 * @param Object id 主键.
	 * @param response
	 * @throws BusinessException
	 */
	@RequestMapping("/dodel")
	public @ResponseBody MessageDTO doDelete(@RequestBody Object id, HttpServletResponse response) {
		return deleteById(id,response);
	}	

	
	private MessageDTO deleteById(Object id, HttpServletResponse response){
		// 1.服务器校验
		// 2.校验成功，进行业务处理
		${lowercaseClassName}Service.doDelete(id);
		return this.responseData(true, "", response, MessageAttribute.COMMON_DELETE_SUC);
	}	
	
   /**
	 * @Title: 逻辑删除对象操作
	 * @Description: 针对后台管理平台，统一用该方法名接受本操作.
	 * <p>针对其他APP. 如无特殊情况，尽量用此唯一入口接受本操作.
	 * <p>逻辑删除一般应用与 订单/电子钱包等删除后 对数据展示有影响的 模块主表
	 * <p>2.底层支持 批量移除和 单个移除. 即支持Long/String id. 或者List id.
	 * 需要更改本方法任何代码. 底层自动识别.
	 * @param Object id 主键.
	 * @param response
	 * @throws BusinessException
	 */
	@RequestMapping("/doremove")
	public @ResponseBody MessageDTO doRemove(@RequestBody Object id, HttpServletResponse response) {
		return removeById(id,response);
	}
	
	/**
	 * @Title: 逻辑删除对象操作
	 * @Description: 针对后台管理平台，统一用该方法名接受本操作.
	 * <p>针对其他APP. 如无特殊情况，尽量用此唯一入口接受本操作.
	 * <p>逻辑删除一般应用与 订单/电子钱包等删除后 对数据展示有影响的 模块主表
	 * <p>2.底层支持 批量移除和 单个移除. 即支持Long/String id. 或者List id.
	 * 需要更改本方法任何代码. 底层自动识别.
	 * @param Object id 主键.
	 * @param response
	 * @throws BusinessException
	 */
	@RequestMapping("/doremovebyid/{id}")
	public @ResponseBody MessageDTO doRemoveById(@PathVariable("id") Object id, HttpServletResponse response) {
		return removeById(id,response);
	}	
	
	private MessageDTO removeById(Object id, HttpServletResponse response){
		// 1.服务器校验
		Subject subject = SecurityUtils.getSubject();
		logger.info("CURR_USER:"+subject.getSession().getAttribute("CURR_USER_INFO"));		
		// 2.校验成功，进行业务处理
		${lowercaseClassName}Service.doRemove(id);
		return this.responseData(true, "", response, MessageAttribute.COMMON_REMOVE_SUC);	
	}
	
	/**
	 * @Title: 查找对象操作
	 * @Description: 
	 * @param Object id 主键.
	 * @param response
	 * @throws BusinessException
	 */
	@RequestMapping("/doselect")
	public @ResponseBody MessageDTO doSelect(@RequestBody Object id, HttpServletResponse response) throws BusinessException {
		return selectById(id,response);
	}
	
	/**
	 * @Title: 查找对象操作[Rest方式]
	 * @Description: 
	 * @param Object id 主键.
	 * @param response
	 * @throws BusinessException
	 */
	@RequestMapping("/doselectbyid/{id}")
	public @ResponseBody MessageDTO doSelectById(@PathVariable("id") Object id, HttpServletResponse response) throws BusinessException {
		return selectById(id,response);
	}	
	
	private MessageDTO selectById(Object id,HttpServletResponse response) throws BusinessException {
		// 1.服务器校验

		// 2.校验成功，进行业务处理
		${entityName} ${entityParName} = ${lowercaseClassName}Service.doSelectById(id);
		return this.responseData(true, ${entityParName}, response,
				MessageAttribute.COMMON_SELECT_SUC);
	}	
	
	
	/**
	 * @Title: 分页操作
	 * @Description: 分页，只返回集合
	 * @param Object id 主键.
	 * @param response
	 * @throws BusinessException
	 */
	@RequestMapping("/queryforlistpage")
	public @ResponseBody MessageDTO queryForListByPage(@RequestBody ${tableEntity.className}Criteria ${entityParName}Criteria, HttpServletResponse response) throws BusinessException {

		${lowercaseClassName}Service.pageQuery(${entityParName}Criteria, ${entityParName}Criteria.getPageBean());
		return this.responseData(true, ${entityParName}Criteria.getPageBean(), response,MessageAttribute.COMMON_SELECT_SUC);		

	}	
	
	/**
	 * @Title: 查询集合操作
	 * @Description: 不分页
	 * @param Object id 主键.
	 * @param response
	 * @throws BusinessException
	 */
	@RequestMapping("/queryforlist")
	public @ResponseBody MessageDTO queryForList(@RequestBody ${tableEntity.className}Criteria ${entityParName}Criteria,HttpServletResponse response) throws BusinessException {
		//查询条件
		List<${entityName}> ${entityParName}List = ${lowercaseClassName}Service.queryForList(${entityParName}Criteria);
		return this.responseData(true, ${entityParName}List, response,
				MessageAttribute.COMMON_SELECT_SUC);				
	}		

    
	private boolean doUpdateValidation(${entityName} ${entityParName}, HttpServletResponse response) {
		boolean status = true;
		// 1.空对象校验
		if (${entityParName} == null) {
			status = false;
		}
		return status;
	}


	/**
	* <p>Title: doInsertValidation</p>
	* <p>Description: 新增操作预校验</p>
	* @param <T>
	* @param T DTO对象
	* @param response
	* @return
	*/
	protected <T> boolean doAddValidation(${entityName} ${entityParName} , HttpServletRequest request,HttpServletResponse response) {
		boolean status = true;
		// 1.空对象校验
		if (${entityParName} == null) {
			status = false;
			
		}
		return status;
	}
	
}
