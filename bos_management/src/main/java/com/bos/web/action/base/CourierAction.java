package com.bos.web.action.base;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.bos.domain.base.Courier;
import com.bos.domain.base.Standard;
import com.bos.service.base.CourierService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class CourierAction extends ActionSupport implements ModelDriven<Courier> {

	
	private Courier courier= new Courier();
	
	@Autowired
	private CourierService courierService;
	
	@Override
	public Courier getModel() {
		// TODO Auto-generated method stub
		return courier;
	}
	
	@Action(value="courier_save" ,results={@Result(name="success",
			location="./pages/base/courier.html",type="redirect")})
	public String save(){
		courierService.save(courier);
		return SUCCESS;
	}
	
	
	
	private int page;
	private int rows;

	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
	@Action(value="courier_pageQuery",
			results={@Result(name="success",type="json")}
			)
	public String pageQuery(){
		Pageable pageable= new PageRequest(page-1, rows);
		Specification<Courier> specification = new Specification<Courier>() {
			@Override
			public Predicate toPredicate(Root<Courier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto -generated method stub
				List<Predicate> list =new ArrayList<Predicate>();
				if( StringUtils.isNotBlank(courier.getCourierNum())){
					Predicate p1 = cb.equal(
					root.get("courierNum").as(String.class),
					courier.getCourierNum());
					list.add(p1);
				}
				if (StringUtils.isNotBlank(courier.getCompany())) {
					Predicate p2 = cb.like(
							root.get("company").as(String.class),
							"%" + courier.getCompany() + "%");
					list.add(p2);
				}
				if (StringUtils.isNotBlank(courier.getType())) {
					Predicate p3 = cb.equal(root.get("type").as(String.class),
							courier.getType());
					list.add(p3);
				}
				//多表查询
				Join<Courier,Standard> standardJoin = root.join("standard",JoinType.INNER);
				if (courier.getStandard() != null
						&& StringUtils.isNotBlank(courier.getStandard()
								.getName())) {
					Predicate p4 = cb.like(
							standardJoin.get("name").as(String.class), "%"
									+ courier.getStandard().getName() + "%");
					list.add(p4);
				}
				return cb.and(list.toArray(new Predicate[0]));
			}
		};
		Page<Courier> pageData= courierService.findPageData(specification,pageable);
		Map<String,Object> result=new HashMap<>();
		result.put("total", pageData.getTotalElements());
		result.put("rows", pageData.getContent());
		ActionContext.getContext().	getValueStack().push(result);	
		return SUCCESS;
	}
	
	/**
	 * 批量作废
	 * */
	private String ids;

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	@Action(value="courier_delBatch" ,
			results={@Result(name="success",type="redirect",location="./pages/base/courier.html")})
	public String delBatch(){
		String[] idArray = ids.split(",");
		courierService.delBatch(idArray);
		
		
		return SUCCESS;
	}
	@Action(value="courier_resBatch" ,
			results={@Result(name="success",type="redirect",location="./pages/base/courier.html")})
	public String resBatch(){
		String[] idArray = ids.split(",");
		courierService.restoreBatch(idArray);
		return SUCCESS;
	}
	
	
	
	

}
