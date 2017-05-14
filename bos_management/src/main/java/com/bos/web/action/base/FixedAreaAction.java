package com.bos.web.action.base;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.bos.domain.base.FixedArea;
import com.bos.service.base.FixedAreaService;
import com.bos.web.action.common.BaseAction;

@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototye")
public class FixedAreaAction extends BaseAction<FixedArea> {

	@Autowired
	private FixedAreaService fixedAreaService; 
	
	@Action(value="fixedarea_save",results={@Result(name="success",type="redirect",location="./pages/base/fixedarea.html")})
	public String save(){
		
		
		return SUCCESS;
	}
	
}
