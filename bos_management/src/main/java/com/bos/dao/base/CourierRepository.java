package com.bos.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bos.domain.base.Courier;

public interface CourierRepository extends JpaRepository<Courier, Integer>,JpaSpecificationExecutor<Courier> {

	@Query(value="update Courier set deltag='1' where id=?")
	@Modifying
	public	void updateDelTag(Integer id);

	@Query(value="update Courier set deltag='0' where id=?")
	@Modifying
	public	void updateResTag(Integer id);

	
	
}
