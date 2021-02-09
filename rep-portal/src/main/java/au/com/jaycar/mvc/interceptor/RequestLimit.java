package au.com.jaycar.mvc.interceptor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)  
public @interface RequestLimit {  
         /** 
     * 
     * 允许访问的次数，默认值MAX_VALUE 
     */  
   int count() default 5;//Integer.MAX_VALUE;  
 
   /** 
     * 
     * 时间段，单位为毫秒，默认值10分钟 
     */  
   long time() default 600000;
   
}
   
