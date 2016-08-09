package com.xxwl.tk.main.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxwl.tk.framework.attribute.MessageAttribute;
import com.xxwl.tk.framework.web.conttroller.BaseController;
import com.xxwl.tk.framework.domain.MessageDTO;
import com.xxwl.tk.framework.page.Criteria;
import com.xxwl.tk.framework.page.PageBean;
import com.xxwl.tk.main.entity.PicEntity;
import com.xxwl.tk.main.service.PicService;

/** 
* @ClassName: PicController 
* @Description: 本类是由代码生成器自动生成PicEntity逻辑(Controller)层
* @company 
* @author yixiang.deng
* @Email 553067271@qq.com
* @date 2016年08月09日
*  
*/ 
@Controller
@RequestMapping("/main/pic")
public class PicController extends BaseController {
	//日志
	private static Logger logger = Logger.getLogger(PicController.class);

	@Resource
	private PicService picService;

	/** 
	* @Title: doAdd 
	* @Description: 新增操作
	* @param picEntity  Post提交
	* @param request
	* @param response
	* @throws BusinessException  
	*/ 
	@RequestMapping(value="/doadd",method = RequestMethod.POST)
	public @ResponseBody MessageDTO doAdd(PicEntity picEntity,HttpServletRequest request,HttpServletResponse response) {
		// 1.服务器校验
		if(!doNullValidation(picEntity)){ 
			return this.responseData(false, null,MessageAttribute.COMMON_ERROR_VAL_EMPTY_OBJ);
		}
		//2.业务层调用
		Integer doAdd = picService.doAdd(picEntity);
		//3.返回JSON数据
		return this.responseData(true, doAdd,  MessageAttribute.COMMON_SELECT_VAL_SUC);
	}
	
	
	/**
	 * @Title: 更新操作
	 * @Description: 更新PicEntity 表单对象
	 * @param picEntity
	 *            表单DTO对象
	 * @param response
	 * @throws BusinessException
	 */
	@RequestMapping(value="/doupdate",method = RequestMethod.POST)
	public @ResponseBody MessageDTO doUpdate(PicEntity picEntity,HttpServletRequest request,HttpServletResponse response) {	
		// 1.服务器校验
		if(!doNullValidation(picEntity)){ 
			return this.responseData(false, null,MessageAttribute.COMMON_ERROR_VAL_EMPTY_OBJ);
		}
		//2.验证
		
		// 3.校验成功，进行业务处理
		Integer doUpdate = picService.doUpdate(picEntity);
		return this.responseData(true, doUpdate, MessageAttribute.COMMON_UPDATE_VAL_SUC);
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
	@RequestMapping(value="/dodel",method = RequestMethod.POST)
	public @ResponseBody MessageDTO doDelete(Integer id) {
		return deleteById(id);
	}	

	
	private MessageDTO deleteById(Integer id){
		// 1.服务器校验
		if(!doNullValidation(id)){ 
			return this.responseData(false, null,MessageAttribute.COMMON_ERROR_VAL_EMPTY_OBJ);
		}
		// 2.校验成功，进行业务处理
		Integer doDelete = picService.doDelete(id);
		return this.responseData(true, doDelete,  MessageAttribute.COMMON_DELETE_VAL_SUC);
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
	@RequestMapping(value="/doremove",method = RequestMethod.POST)
	public @ResponseBody MessageDTO doRemove(Integer id) {
		return removeById(id);
	}

	private MessageDTO removeById(Integer id){
		// 1.服务器校验
		if(null == id){
			return this.responseData(false, null,  MessageAttribute.COMMON_ERROR_VAL_EMPTY_OBJ);
		}
		// 2.校验成功，进行业务处理
		Integer doRemove = picService.doRemove(id);
		return this.responseData(true, doRemove, MessageAttribute.COMMON_REMOVE_VAL_SUC);	
	}
	
	/**
	 * @Title: 查找对象操作
	 * @Description: 
	 * @param Object id 主键.
	 * @param response
	 * @throws BusinessException
	 */
	@RequestMapping(value="/doselect",method = RequestMethod.POST)
	public @ResponseBody MessageDTO doSelect(Integer id) {
		return selectById(id);
	}
	
	/**
	 * @Title: 查找对象操作[Rest方式]
	 * @Description: 
	 * @param Object id 主键.
	 * @param response
	 * @throws BusinessException
	 */
	@RequestMapping("/doselectbyid/{id}")
	public @ResponseBody MessageDTO doSelectById(@PathVariable("id") Integer id) {
		return selectById(id);
	}	
	
	private MessageDTO selectById(Integer id) {
		// 1.服务器校验
		if(!doNullValidation(id)){ 
			return this.responseData(false, null,MessageAttribute.COMMON_ERROR_VAL_EMPTY_OBJ);
		}
		// 2.校验成功，进行业务处理
		PicEntity picEntity = picService.getById(id);
		return this.responseData(true, picEntity,MessageAttribute.COMMON_SELECT_VAL_SUC);
	}	
	
	
	/**
	 * @Title: 分页操作
	 * @Description: 分页，只返回集合
	 * @param Object id 主键.
	 * @param response
	 * @throws BusinessException
	 */
	@RequestMapping(value="/pagequery",method = RequestMethod.POST)
	public @ResponseBody MessageDTO pageQuery(Criteria<PicEntity>  picCriteria){
		// 1.服务器校验
		if(!doNullValidation(picCriteria)){ 
			return this.responseData(false, null,MessageAttribute.COMMON_ERROR_VAL_EMPTY_OBJ);
		}
		PageBean<PicEntity> pageQuery = picService.pageQuery(picCriteria);
		return this.responseData(true, pageQuery,MessageAttribute.COMMON_SELECT_VAL_SUC);		

	}	
	
	/**
	 * @Title: 查询集合操作
	 * @Description: 不分页
	 * @param Object id 主键.
	 * @param response
	 * @throws BusinessException
	 */
	@RequestMapping(value="/queryforlist",method = RequestMethod.POST)
	public @ResponseBody MessageDTO queryForList(Criteria<PicEntity>  picCriteria) {
		// 1.服务器校验
		if(!doNullValidation(picCriteria)){ 
			return this.responseData(false, null,MessageAttribute.COMMON_ERROR_VAL_EMPTY_OBJ);
		}
		//查询条件
		List<PicEntity> picEntityList = picService.queryForList(picCriteria);
		return this.responseData(true, picEntityList,MessageAttribute.COMMON_SELECT_VAL_SUC);				
	}		

    
	
	
	private boolean doUpdateValidation(PicEntity picEntity) {
		boolean status = true;
		// 1.空对象校验
		if (picEntity == null) {
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
	protected <T> boolean doAddValidation(PicEntity picEntity ) {
		boolean status = true;
		// 1.空对象校验
		if (picEntity == null) {
			status = false;
			
		}
		return status;
	}
	
}
