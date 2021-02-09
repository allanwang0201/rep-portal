package au.com.jaycar.search;

import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;  
import java.util.Collections;  
import java.util.Comparator;  
import java.util.List;  
import java.util.Map;  
import java.util.Map.Entry;  
import java.util.TreeMap;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;

import au.com.jaycar.mdm.entity.ProductsWithBLOBs;  
  
public class IO_Word {  
      public static List<String>Io_word(String str)throws Exception{  
        File file = new File(str);  
        int n = 0;//文章中单词总数  
        TreeMap<Object, Integer> myTreeMap = new TreeMap<Object, Integer>();//存放键值对  
        Object word = null;//文章中的单词  
        Object num = null;//出现的次数  
        FileInputStream fis = new FileInputStream(file);  
        try{  
          InputStreamReader isr = new InputStreamReader(fis);  
          try{  
             BufferedReader br = new BufferedReader(isr);  
             try{  
               List<String> all = new ArrayList<String>();  
               String temp = br.readLine();  
               while (temp !=null){  
                 all.add(temp);  
                 temp = br.readLine();  
               }  
               //System.out.println("all="+all.size());  
              // System.out.println(all.get(0));  
               Pattern expression = Pattern.compile("[a-zA-Z]+");//定义正则表达式匹配单词  
               String string1 = all.toString().toLowerCase();//转换成小写  
               Matcher matcher = expression.matcher(string1);//定义string1的匹配器  
               while(matcher.find()){  
                 word = matcher.group();//得到一个单词—树映射的键  
                 //System.out.println("word="+word);  
                 n++;  
                 if(myTreeMap.containsKey(word)){  
                   num = myTreeMap.get(word);//得到单词出现的次数  
                   Integer count = (Integer)num;  
                   myTreeMap.put(word, new Integer(count.intValue()+1));  
                 }else {  
                   myTreeMap.put(word, new Integer(1));//否则单词第一次出现，添加到映射中  
                 }  
               }  
               System.out.println("统计分析如下：");  
               System.out.println("txt文章中单词总数"+ n +"个");  
               /*Iterator<Object> iter = myTreeMap.keySet().iterator();//得到树映射键集合的迭代器 
               while(iter.hasNext()){ 
                 key = iter.next(); 
                 System.out.println(((String)key+"-"+myTreeMap.get(key))); 
               }*/  
               List<Map.Entry<Object, Integer>> list = new ArrayList<Map.Entry<Object,Integer>>(myTreeMap.entrySet());  
               System.out.println("list="+list.size());  
               Collections.sort(list,new Comparator<Map.Entry<Object, Integer>>(){  
  
                public int compare(Map.Entry<Object, Integer>zj,  Map.Entry<Object, Integer> zz) {  
                  return (zz.getValue() - zj.getValue());  
                }  
               });
               
       		
       		String filename = "D:/mdmproducts/stats.txt";//文件路径+名称+文件类型
       		
       		File stats = new File(filename);//文件路径(包括文件名称)
               FileOutputStream fos  = null;
               PrintWriter pw = null;
               if(!stats.exists()){
                   try {
                	   stats.createNewFile();
          
       				StringBuffer buffer = new StringBuffer();
       		            
       		        fos = new FileOutputStream(stats);
       		        pw = new PrintWriter(fos);     
       		      for (Entry<Object, Integer> entry : list) {  
       		    	buffer.append(entry.getKey() + "-" + entry.getValue() + "\n" );  
                    }  
       		        pw.write(buffer.toString().toCharArray());
       		        pw.flush();

       			} catch (IOException e) {
       				// TODO Auto-generated catch block
       				e.printStackTrace();
       			}
                   finally {
                   if (pw != null) {
                       pw.close();
                   }
                   if (fos != null) {
                       try {
       					fos.close();
       				} catch (IOException e) {
       					// TODO Auto-generated catch block
       					e.printStackTrace();
       				}
                   }
               }
               
               }

         
               return all;  
             }finally{  
               br.close();  
             }  
          }finally{  
            isr.close();  
          }  
        }finally{  
          fis.close();  
        }  
      }  
      public static void main(String[] args) {  
          try {  
              IO_Word.Io_word("D:/mdmproducts/AllinOne.txt");  
            } catch (Exception e) {  
              e.printStackTrace();  
            }  
    }  
    }  
   