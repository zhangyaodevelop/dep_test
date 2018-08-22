package com.forezp.eurekaclient.dao;


import com.forezp.eurekaclient.model.student;
import org.springframework.stereotype.Component;

@Component("studentMapper")
public interface studentMapper {
    student selectById(Integer id);
}