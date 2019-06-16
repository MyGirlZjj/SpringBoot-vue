package com.boylegu.springboot_vue.dao;

import com.boylegu.springboot_vue.entities.Ypxx;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface YpxxRepository extends JpaRepository<Ypxx, Long> {

    Page<Ypxx> findAll(Pageable pageable);

    Page<Ypxx> findbyBianma(String bianma, Pageable pageable);

    Ypxx findbyPihao(String pihao);

    Ypxx findbyPinming(String pihao);

}
