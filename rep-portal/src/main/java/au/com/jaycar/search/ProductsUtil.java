package au.com.jaycar.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import au.com.jaycar.mdm.entity.ProductsWithBLOBs;
import au.com.jaycar.mdm.service.MdmProductsService;

@Component
public class ProductsUtil {

	
	@Autowired
	private MdmProductsService mdmProductsService;
	
	private String path = "D:\\mdmproducts\\";
	 
	
	public void WriteAllToOneFiles()
	{
		List<ProductsWithBLOBs> products = mdmProductsService.findAll();
		
		String filename = path + "AllinOne" + ".txt";//文件路径+名称+文件类型
		
		File file = new File(filename);//文件路径(包括文件名称)
        FileOutputStream fos  = null;
        PrintWriter pw = null;
        if(!file.exists()){
            try {
				file.createNewFile();
   
				StringBuffer buffer = new StringBuffer();
		            
		        fos = new FileOutputStream(file);
		        pw = new PrintWriter(fos);     
				for(ProductsWithBLOBs product :products)
				{
					String one = product.getpCopyretail();
					if(one != null && one.length() > 0 ){
						buffer.append(one);
					}
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
        
        
        
        
	}
	
	
	public void WriteAllToFiles()
	{
		List<ProductsWithBLOBs> products = mdmProductsService.findAll();
		
		for(ProductsWithBLOBs product :products)
		{
			String filenameTemp = path + product.getCode() + ".txt";//文件路径+名称+文件类型
			createFile(filenameTemp, product.getpCopyretail());
		}
	}
	
    public boolean createFile(String fileName,String filecontent){
        Boolean bool = false;

        try {
            //如果文件不存在，则创建新的文件
           
             writeFileContent(fileName, filecontent);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return bool;
    }
    
    /**
     * 向文件中写入内容
     * @param filepath 文件路径与名称
     * @param newstr  写入的内容
     * @return
     * @throws IOException
     */
    public boolean writeFileContent(String filepath,String content) throws IOException{
        Boolean bool = false;
        
        FileOutputStream fos  = null;
        PrintWriter pw = null;
        try {
            File file = new File(filepath);//文件路径(包括文件名称)
            
            if(!file.exists()){
                file.createNewFile();
            }
            
            StringBuffer buffer = new StringBuffer();
         
            buffer.append(content);
            
            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
        return bool;
    }
}
