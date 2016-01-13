package com.hzins.test.bean;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import com.hzins.test.bean.Bean;
import com.hzins.test.intaface.X;
import com.lanhun.core.context.Parameter;

/**
 * Copyright (c) 2006-2015 Hzins Ltd. All Rights Reserved. 
 *  
 * This code is the confidential and proprietary information of   
 * Hzins. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Hzins,http://www.hzins.com.
 *  
 */
/**
 * <p>
 * 
 *
 *
 * </p>
 * 
 * @author hz15101769
 * @date 2015年11月4日 下午3:21:08
 * @version
 */
public class Tx implements X {

    /* (non-Javadoc)
     * @see X#createBean(java.lang.String, java.lang.String)
     */
    @Override
    public Bean createBean(String id, String name) {
	Bean bean = new Bean();
	bean.setId(id);
	bean.setName(name);
	return bean;
    }

    public static void main(String[] args) {
	Method[] methods = Tx.class.getDeclaredMethods();
	for (Method method : methods) {
	    System.out.println(method);
	    ParameterNameDiscoverer pd = new DefaultParameterNameDiscoverer();
	    String[] pnames = pd.getParameterNames(method);
	    Class<?>[] ptypes=method.getParameterTypes();
	    List<Parameter> mp=new ArrayList<Parameter>();
	    Parameter pb=null;
	    for (int i=0;i<pnames.length;i++) {
		pb=new Parameter();
		pb.setName(pnames[i]);
		pb.setType(ptypes[i]);
		mp.add(pb);
	    }
	}
	System.out.println("hello");
    }

}
