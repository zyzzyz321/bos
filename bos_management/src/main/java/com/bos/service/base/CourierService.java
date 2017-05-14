package com.bos.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.bos.domain.base.Courier;

public interface CourierService {

	void save(Courier c);
	Page<Courier> findPageData(Specification<Courier> spe,Pageable pageable);
	void delBatch(String[] idArray);
	void restoreBatch(String[] idArray);
} 
