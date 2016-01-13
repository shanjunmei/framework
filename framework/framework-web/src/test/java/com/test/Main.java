package com.test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.hzins.test.bean.Bean;

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
 * @date 2015年11月4日 下午2:13:08
 * @version
 */
public final class Main {

    public static void main(String[] args) {
	Bean source = null;
	List<Bean> sources = new ArrayList<Bean>();

	long t = System.currentTimeMillis();
	for (int i = 0; i < 1000000; i++) {
	    source = new Bean();
	    source.setId("id-" + i);
	    source.setName("name-" + i);
	    sources.add(source);
	}

	t = System.currentTimeMillis() - t;
	System.out.println("create take :" + t + " ms");

	List<Bean> targets = new ArrayList<Bean>();
	
	List<Bean> targets1 = new ArrayList<Bean>();

	List<Bean> targets2 = new ArrayList<Bean>();
	
	List<Bean> targets3 = new ArrayList<Bean>();
	
	copys(sources, targets);

	copysByReflection(sources, targets1);
	
	copysBySelfReflection(sources, targets3);
	
	copysBySerialization(sources, targets3);
    }
    private static void copysBySelfReflection(List<Bean> sources, List<Bean> targets) {
 	long t = System.currentTimeMillis();
 	Bean targetCopy;
 	for (Bean bean : sources) {
 	    targetCopy = new Bean();
 	   copyBySelfReflection(bean, targetCopy);
 	    targets.add(targetCopy);
 	}
 	t = System.currentTimeMillis() - t;
 	System.out.println("copysBySelfReflection take :" + t + " ms");
     }

    private static void copysByReflection(List<Bean> sources, List<Bean> targets) {
	long t = System.currentTimeMillis();
	Bean targetCopy;
	for (Bean bean : sources) {
	    targetCopy = new Bean();
	    copyByReflection(bean, targetCopy);
	    targets.add(targetCopy);
	}
	t = System.currentTimeMillis() - t;
	System.out.println("copysByReflection take :" + t + " ms");
    }

    private static void copys(List<Bean> sources, List<Bean> targets) {
	long t = System.currentTimeMillis();
	Bean targetCopy;
	for (Bean bean : sources) {
	    targetCopy = new Bean();
	    copy(bean, targetCopy);
	    targets.add(targetCopy);
	}
	t = System.currentTimeMillis() - t;
	System.out.println("copys take :" + t + " ms");
    }
    
    private static void copysBySerialization(List<Bean> sources, List<Bean> targets) {
   	long t = System.currentTimeMillis();
   	Bean targetCopy;
   	for (Bean bean : sources) {
   	    targetCopy =copyBySerialization(bean);
   	    targets.add(targetCopy);
   	}
   	t = System.currentTimeMillis() - t;
   	System.out.println("copysBySerialization take :" + t + " ms");
       }

    public static void copy(Bean source, Bean target) {
	target.setId(source.getId());
	target.setName(source.getName());
    }

    public static void copyByReflection(Bean source, Bean target) {
	BeanUtils.copyProperties(source, target,Bean.class);
    }
    
    public static Bean copyBySerialization(Bean source) {
	ByteArrayOutputStream out=new ByteArrayOutputStream();
	ObjectOutputStream oos;
	try {
	    oos = new ObjectOutputStream(out);
	    oos.writeObject(source);
	    
	    ByteArrayInputStream in=new ByteArrayInputStream(out.toByteArray());
	    ObjectInputStream ois=new ObjectInputStream(in);
	    return (Bean)ois.readObject();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    throw new RuntimeException(e);
	}
	
	
       }

    public static void copyBySelfReflection(Bean source, Bean target) {
	Field[] sourceFields = source.getClass().getDeclaredFields();

	Field[] targetFields = target.getClass().getDeclaredFields();
	for (Field field : targetFields) {
	    field.setAccessible(true);
	    
	    for (Field srcField : sourceFields) {
		srcField.setAccessible(true);
		if(field.getName().equals(srcField.getName())){
		   
		    try {
			Object value=srcField.get(source);
			field.set(target, value);
			break;
		    } catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    } catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		}
	    }
	}

    }

}
