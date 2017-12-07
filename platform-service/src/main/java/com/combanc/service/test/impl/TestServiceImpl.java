package com.combanc.service.test.impl;

import com.combanc.dao.TestDao;
import com.combanc.service.test.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service(value = "testService")
public class TestServiceImpl implements TestService {

    @Resource
    private TestDao testDao;

    @Override
    public Integer resultOne() {
        return testDao.resultOne();
    }
}
