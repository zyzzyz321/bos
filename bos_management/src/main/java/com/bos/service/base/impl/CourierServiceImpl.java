package com.bos.service.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bos.dao.base.CourierRepository;
import com.bos.domain.base.Courier;
import com.bos.service.base.CourierService;
@Service
@Transactional
public class CourierServiceImpl implements CourierService {

	@Autowired
	private CourierRepository courierRepository;
	@Override
	public void save(Courier c) {
		// TODO Auto-generated method stub
		courierRepository.save(c);
	}
	@Override
	public Page<Courier> findPageData(Specification<Courier> spe,  Pageable pageable) {
		// TODO Auto-generated method stub
		
		return courierRepository.findAll(spe,pageable);
	}
	@Override
	public void delBatch(String[] idArray) {
		// TODO Auto-generated method stub
		for (String string : idArray) {
			Integer id= Integer.parseInt(string);
			courierRepository.updateDelTag(id);
		}
	}
	@Override
	public void restoreBatch(String[] idArray) {
		// TODO Auto-generated method stub
		for (String string : idArray) {
			Integer id= Integer.parseInt(string);
			courierRepository.updateResTag(id);
		}
	}

}
