package ${basePackage}.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import com.bqsolo.framework.json.JsonDateDeserializer;
import com.bqsolo.framework.json.JsonDateSerializer;
import com.bqsolo.framework.domain.BaseEntity;
/** 
* @ClassName: ${entityName} 
* @Description: 本类是由代码生成器自动生成${entityName}实体对象(Entity)
* @company 
* @author ${builderAuthor}
* @Email ${builderEmail}
* @date ${nowDate}
*  
*/ 
@Entity(name = "${tableEntity.className}Entity")
@Table(name = "${tableEntity.tableName}")
public class ${entityName} extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  ${serialVersionUID?c}L;


	<#list tableEntity.columns as col>
		<#if col.columnName !='cuid' && col.columnName !='cdate' && col.columnName !='changeuid' && col.columnName !='changedate' && col.columnName !='isvoid' >
	//${col.columnAnnotations?default('-')}
			<#if col.isPrimaryKey?exists && 1 == col.isPrimaryKey >
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "${col.columnName}", nullable = false)	
	private ${col.classAttrType} ${col.classAttr};
			</#if>
			<#if col.isPrimaryKey?exists && col.isPrimaryKey != 1 >
	@Column(name = "${col.columnName}")
				<#if col.classAttrType =='java.util.Data' >
	@Temporal(TemporalType.TIMESTAMP)	
	@JsonSerialize(using=JsonDateSerializer.class,include=Inclusion.NON_NULL)
	@JsonDeserialize(using=JsonDateDeserializer.class)
				</#if>
	private ${col.classAttrType} ${col.classAttr};
			</#if>
		</#if>		
	</#list>
	
	<#list tableEntity.columns as col>
		<#if col.columnName !='cuid' && col.columnName !='cdate' && col.columnName !='changeuid' && col.columnName !='changedate' && col.columnName !='isvoid' >
	/**${col.columnAnnotations?default('-')}*/
	public ${col.classAttrType} get${col.endStr}() {
		return ${col.classAttr};
	}
	/**${col.columnAnnotations?default('-')}*/
	public void set${col.endStr}(${col.classAttrType} ${col.classAttr}) {
		this.${col.classAttr} = ${col.classAttr};
	}
		</#if>
	</#list>

}
