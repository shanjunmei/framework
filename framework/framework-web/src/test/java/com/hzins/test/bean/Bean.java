package com.hzins.test.bean;
import java.io.Serializable;

/**
 * 
 * <p>
 * 
 *
 *
 * </p>
 * 
 * @author hz15101769
 * @date 2015年11月4日 下午2:12:27
 * @version
 */

public class Bean implements Serializable {

    /**
     * <p>
     * 
     *
     *
     * </p>
     *  
     * @author	hz15101769 
     * @date	2015年11月25日 下午5:38:21
     * @version     
     */ 
    //private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Override
    public String toString() {
	return "Bean [id=" + id + ", name=" + name + "]";
    }

    
}
