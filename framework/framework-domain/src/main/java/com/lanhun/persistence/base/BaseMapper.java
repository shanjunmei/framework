package com.lanhun.persistence.base;

public interface BaseMapper<T,PK> {
    int deleteByPrimaryKey(PK prizeid);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(PK prizeid);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}