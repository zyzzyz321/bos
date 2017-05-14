package com.bos.service.base.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bos.dao.base.AreaRepository;
import com.bos.domain.base.Area;
import com.bos.service.base.AreaService;

@Service
@Transactional
public class AreaServiceImpl implements AreaService {

	private AreaRepository areaRepository;
	@Override
	public void saveBatch(List<Area> areas) {
		areaRepository.save(areas);
	}
	@Override
	public Page<Area> findPageData(Specification<Area> specification, Pageable pageable) {
		// TODO Auto-generated method stub
		return areaRepository.findAll(specification, pageable);
	}

	
}
