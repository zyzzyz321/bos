package com.bos.web.action.common;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public  class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	protected T model;
	
	@Override
	public T getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	public BaseAction(){
		Type genericSuperclass = this.getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType)genericSuperclass;
		Class<T>  modelClass=(Class<T>)parameterizedType.getActualTypeArguments()[0];
		try {
			model = modelClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Failed to construct...");
		}
	
	}
	
		protected int page;
		protected int rows;
	
		public void setPage(int page) {
			this.page = page;
		}
	
		public void setRows(int rows) {
			this.rows = rows;
		}

		protected void pushPageDataToValueStack(Page<T> pageData) {
			Map<String,Object> result = new HashMap<>();
			result.put("total", pageData.getTotalElements());
			result.put("rows", pageData.getContent());
			ActionContext.getContext().getValueStack().push(result);
		}
}
