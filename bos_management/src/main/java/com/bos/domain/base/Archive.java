package com.bos.domain.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @description:档案类，记录所有的分类信息，在子档中
 * */

@Entity
@Table(name="T_ARCHIVE")
public class Archive {

	@Id
	@GeneratedValue
	@Column(name="C_ID")
	private Integer id;
}
