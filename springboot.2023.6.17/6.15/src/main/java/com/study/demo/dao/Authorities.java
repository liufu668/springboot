package com.study.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface Authorities extends JpaRepository<com.study.demo.entity.Authorities,Integer>, JpaSpecificationExecutor<com.study.demo.entity.Authorities> {
}
