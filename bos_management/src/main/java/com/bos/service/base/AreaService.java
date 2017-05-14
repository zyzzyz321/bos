package com.bos.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.bos.domain.base.Area;

public interface AreaService {

	void saveBatch(List<Area> areas);

	Page<Area> findPageData(Specification<Area> specification, Pageable pageable);

	

}
