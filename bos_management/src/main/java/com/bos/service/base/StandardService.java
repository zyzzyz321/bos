package com.bos.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bos.domain.base.Standard;

/**
 * 收派标准管理 
 * @author itcast
 * 
 */
public interface StandardService {
	public void save(Standard standard);

	// 分页查询 
	public Page<Standard> findPageData(Pageable pageable);

	public List<Standard> findAll();

}
