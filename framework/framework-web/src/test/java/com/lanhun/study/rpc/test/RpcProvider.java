/*
 * Copyright 2011 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.lanhun.study.rpc.test;

import com.lanhun.study.rpc.framework.RpcFramework;

/**
 * RpcProvider
 * 
 * @author william.liangf
 */
public class RpcProvider {

    public static void main(String[] args) throws Exception {
        RpcFramework.export(1234);
    }

}
