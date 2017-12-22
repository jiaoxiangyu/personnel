package cn.jxy.personnel.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import cn.jxy.personnel.entity.User;

public class ExcelUtil {
	
		/**
		 * 对外提供读取excel 的方法（根据文件名后缀名判断是2003还是2007excel文件）
		 */
		public static List<User> readExcel(String fileName,InputStream is) throws IOException {
		   
		    String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
		            .substring(fileName.lastIndexOf(".") + 1);
		    if ("xls".equals(extension)) {
		        return read2003Excel(is);
		    } else if ("xlsx".equals(extension)) {
		        return read2007Excel(is);
		    } else {
		        throw new IOException("不支持的文件类型");
		    }
		}
	
		/**
	     * 创建2003版本的Excel文件
	     */
	    public static void creat2003Excel(List<User> list,String path) throws Exception {
	        HSSFWorkbook workbook = new HSSFWorkbook();// 创建 一个excel文档对象

	        HSSFSheet sheet = workbook.createSheet();// 创建一个工作薄对象

	        // 添加表头行
	        HSSFRow hssfRow = sheet.createRow(0);
	        // 设置单元格格式居中
	        HSSFCellStyle cellStyle = workbook.createCellStyle();
	        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        sheet.setColumnWidth(3, 6000);//设置第四列列宽

	        // 添加表头内容
	        HSSFCell headCell = hssfRow.createCell(0);
	        headCell.setCellValue("姓名");
	        headCell.setCellStyle(cellStyle);

	        headCell = hssfRow.createCell(1);
	        headCell.setCellValue("性别");
	        headCell.setCellStyle(cellStyle);

	        headCell = hssfRow.createCell(2);
	        headCell.setCellValue("年龄");
	        headCell.setCellStyle(cellStyle);

	        headCell = hssfRow.createCell(3);
	        headCell.setCellValue("民族");
	        headCell.setCellStyle(cellStyle);
	        
	        headCell = hssfRow.createCell(4);
	        headCell.setCellValue("地址");
	        headCell.setCellStyle(cellStyle);
	        
	        headCell = hssfRow.createCell(5);
	        headCell.setCellValue("学历");
	        headCell.setCellStyle(cellStyle);
	        
	        headCell = hssfRow.createCell(6);
	        headCell.setCellValue("政治面貌");
	        headCell.setCellStyle(cellStyle);
	        
	        headCell = hssfRow.createCell(7);
	        headCell.setCellValue("行业");
	        headCell.setCellStyle(cellStyle);
	        
	        headCell = hssfRow.createCell(8);
	        headCell.setCellValue("手机号");
	        headCell.setCellStyle(cellStyle);
	        
	        headCell = hssfRow.createCell(9);
	        headCell.setCellValue("邮箱");
	        headCell.setCellStyle(cellStyle);
	        
	        headCell = hssfRow.createCell(10);
	        headCell.setCellValue("身份");
	        headCell.setCellStyle(cellStyle);
	        
	     
	        // 添加数据内容
	        for (int i = 0; i < list.size(); i++) {
	            hssfRow = sheet.createRow((int) i + 1);
	            User user = list.get(i);

	            // 创建单元格，并设置值
	            HSSFCell cell = hssfRow.createCell(0);
	            cell.setCellValue(user.getUsername());
	            cell.setCellStyle(cellStyle);

	            cell = hssfRow.createCell(1);
	            cell.setCellValue(user.getSex());
	            cell.setCellStyle(cellStyle);

	            cell = hssfRow.createCell(2);
	            cell.setCellValue(user.getAge());
	            cell.setCellStyle(cellStyle);
	            
	            cell = hssfRow.createCell(3);
	            cell.setCellValue(user.getNation());
	            cell.setCellStyle(cellStyle);
	            
	            cell = hssfRow.createCell(4);
	            cell.setCellValue(user.getAddress());
	            cell.setCellStyle(cellStyle);

	            cell = hssfRow.createCell(5);
	            cell.setCellValue(user.getEducation());
	            cell.setCellStyle(cellStyle);

	            cell = hssfRow.createCell(6);
	            cell.setCellValue(user.getPoliticalStatus());
	            cell.setCellStyle(cellStyle);

	            cell = hssfRow.createCell(7);
	            cell.setCellValue(user.getIndustry());
	            cell.setCellStyle(cellStyle);

	            cell = hssfRow.createCell(8);
	            cell.setCellValue(user.getPhone());
	            cell.setCellStyle(cellStyle);

	            cell = hssfRow.createCell(9);
	            cell.setCellValue(user.getEmail());
	            cell.setCellStyle(cellStyle);

	            cell = hssfRow.createCell(10);
	            if(user.getType()==0) {
	            	cell.setCellValue("管理员");
	            }else {
	            	cell.setCellValue("普通用户");
	            }
	            cell.setCellStyle(cellStyle);
	            
	           /* HSSFCellStyle dateStyle = workbook.createCellStyle();
	            dateStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 设置单元格格式居中
	            dateStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));//设置时间格式
	            cell = hssfRow.createCell(3);
	            cell.setCellValue(student.getDate());
	            cell.setCellStyle(dateStyle);*/
	        }

	        // 保存Excel文件
	        try {
	            OutputStream outputStream = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\"+path+".xls");
	            workbook.write(outputStream);
	            outputStream.close();
	            System.out.println("创建成功 office 2003 excel");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	    }

	    /**
	     * 创建2007版Excel文件
	     *
	     * @throws FileNotFoundException
	     * @throws IOException
	     */
	    public static void creat2007Excel(List<User> list) throws Exception {
	        // HSSFWorkbook workbook = new HSSFWorkbook();// 创建 一个excel文档对象
	        XSSFWorkbook workbook = new XSSFWorkbook();
	        XSSFSheet sheet = workbook.createSheet();// 创建一个工作薄对象

	        // 添加表头行
	        XSSFRow xssfRow = sheet.createRow(0);
	        // 设置单元格格式居中
	        XSSFCellStyle cellStyle = workbook.createCellStyle();
	        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	        sheet.setColumnWidth(3, 6000);//设置第四列列宽
	        
	        
	        // 添加表头内容
	        XSSFCell headCell = xssfRow.createCell(0);
	        headCell.setCellValue("姓名");
	        headCell.setCellStyle(cellStyle);

	        headCell = xssfRow.createCell(1);
	        headCell.setCellValue("性别");
	        headCell.setCellStyle(cellStyle);

	        headCell = xssfRow.createCell(2);
	        headCell.setCellValue("年龄");
	        headCell.setCellStyle(cellStyle);

	        headCell = xssfRow.createCell(3);
	        headCell.setCellValue("民族");
	        headCell.setCellStyle(cellStyle);
	        
	        headCell = xssfRow.createCell(4);
	        headCell.setCellValue("地址");
	        headCell.setCellStyle(cellStyle);
	        
	        headCell = xssfRow.createCell(5);
	        headCell.setCellValue("学历");
	        headCell.setCellStyle(cellStyle);
	        
	        headCell = xssfRow.createCell(6);
	        headCell.setCellValue("政治面貌");
	        headCell.setCellStyle(cellStyle);
	        
	        headCell = xssfRow.createCell(7);
	        headCell.setCellValue("行业");
	        headCell.setCellStyle(cellStyle);
	        
	        headCell = xssfRow.createCell(8);
	        headCell.setCellValue("手机号");
	        headCell.setCellStyle(cellStyle);
	        
	        headCell = xssfRow.createCell(9);
	        headCell.setCellValue("邮箱");
	        headCell.setCellStyle(cellStyle);
	        
	        headCell = xssfRow.createCell(10);
	        headCell.setCellValue("身份");
	        headCell.setCellStyle(cellStyle);

	        // 添加数据内容
	        for (int i = 0; i < list.size(); i++) {
	            xssfRow = sheet.createRow((int) i + 1);
	            User user = list.get(i);

	         // 创建单元格，并设置值
	            XSSFCell cell = xssfRow.createCell(0);
	            cell.setCellValue(user.getUsername());
	            cell.setCellStyle(cellStyle);

	            cell = xssfRow.createCell(1);
	            cell.setCellValue(user.getSex());
	            cell.setCellStyle(cellStyle);

	            cell = xssfRow.createCell(2);
	            cell.setCellValue(user.getAge());
	            cell.setCellStyle(cellStyle);
	            
	            cell = xssfRow.createCell(3);
	            cell.setCellValue(user.getNation());
	            cell.setCellStyle(cellStyle);
	            
	            cell = xssfRow.createCell(4);
	            cell.setCellValue(user.getAddress());
	            cell.setCellStyle(cellStyle);

	            cell = xssfRow.createCell(5);
	            cell.setCellValue(user.getEducation());
	            cell.setCellStyle(cellStyle);

	            cell = xssfRow.createCell(6);
	            cell.setCellValue(user.getPoliticalStatus());
	            cell.setCellStyle(cellStyle);

	            cell = xssfRow.createCell(7);
	            cell.setCellValue(user.getIndustry());
	            cell.setCellStyle(cellStyle);

	            cell = xssfRow.createCell(8);
	            cell.setCellValue(user.getPhone());
	            cell.setCellStyle(cellStyle);

	            cell = xssfRow.createCell(9);
	            cell.setCellValue(user.getEmail());
	            cell.setCellStyle(cellStyle);

	            cell = xssfRow.createCell(10);
	            if(user.getType()==0) {
	            	cell.setCellValue("管理员");
	            }else {
	            	cell.setCellValue("普通用户");
	            }
	            cell.setCellStyle(cellStyle);
	        }

	        // 保存Excel文件
	        try {
	            OutputStream outputStream = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\person.xlsx");
	            workbook.write(outputStream);
	            outputStream.close();
	            System.out.println("创建成功 office 2007 excel");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	    }

	    

	    /**
	     * 读取 office 2003 excel
	     *
	     * @throws IOException
	     * @throws FileNotFoundException
	     */
	    private static List<User> read2003Excel(InputStream inputStream)
	            throws IOException {
	        List<User> list = new ArrayList<>();
	        HSSFWorkbook workbook = null;

	        try {
	            // 读取Excel文件
	           
	            workbook = new HSSFWorkbook(inputStream);
	            inputStream.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        // 循环工作表
	        if (workbook != null) {
	            for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
	                HSSFSheet hssfSheet = workbook.getSheetAt(numSheet);
	                if (hssfSheet == null) {
	                    continue;
	                }
	                // 循环行
	                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
	                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
	                    if (hssfRow == null) {
	                        continue;
	                    }

	                    // 将单元格中的内容存入集合
	                    User user = new User();

	                    HSSFCell cell = hssfRow.getCell(0);
	                    if (cell == null) {
	                        continue;
	                    }
	                    user.setUsername(cell.getStringCellValue());
	                    cell = hssfRow.getCell(1);
	                    
	                    if (cell == null) {
	                        continue;
	                    }
	                    user.setSex(cell.getStringCellValue());
	                    cell = hssfRow.getCell(2);
	                    
	                    if (cell == null) {
	                        continue;
	                    }
	                    user.setAge((int) cell.getNumericCellValue());
	                    cell = hssfRow.getCell(3);

	                    if (cell == null) {
	                        continue;
	                    }
	                    user.setNation(cell.getStringCellValue());
	                    cell = hssfRow.getCell(4);

	                    if (cell == null) {
	                        continue;
	                    }
	                    user.setAddress(cell.getStringCellValue());
	                    cell = hssfRow.getCell(5);

	                    if (cell == null) {
	                        continue;
	                    }
	                    user.setEducation(cell.getStringCellValue());
	                    cell = hssfRow.getCell(6);

	                    if (cell == null) {
	                        continue;
	                    }
	                    user.setPoliticalStatus(cell.getStringCellValue());
	                    cell = hssfRow.getCell(7);

	                    if (cell == null) {
	                        continue;
	                    }
	                    user.setIndustry(cell.getStringCellValue());
	                    cell = hssfRow.getCell(8);

	                    if (cell == null) {
	                        continue;
	                    }
	                    user.setPhone(cell.getStringCellValue());
	                    cell = hssfRow.getCell(9);

	                    if (cell == null) {
	                        continue;
	                    }
	                    user.setEmail(cell.getStringCellValue());
	                    cell = hssfRow.getCell(10);
	                    
	                    if (cell == null) {
	                        continue;
	                    }
	                    if(cell.getStringCellValue().equals("管理员")) {
	                    	user.setType(0);
	                    }else {
	                    	user.setType(1);
	                    }
	                   
	                    cell = hssfRow.getCell(11);


	                    list.add(user);
	                }
	            }
	        }
	        return list;
	    }

	    /**
	     * 读取Office 2007 excel
	     */

	    private static List<User> read2007Excel(InputStream inputStream)
	            throws IOException {
	        List<User> list = new ArrayList<>();
	        XSSFWorkbook workbook = null;

	        try {
	            // 读取Excel文件
	            workbook = new XSSFWorkbook(inputStream);
	            inputStream.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        // 循环工作表
	        if (workbook != null) {
	        	 for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
	                 XSSFSheet hssfSheet = workbook.getSheetAt(numSheet);
	                 if (hssfSheet == null) {
	                     continue;
	                 }
	                 // 循环行
	                 for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
	                     XSSFRow hssfRow = hssfSheet.getRow(rowNum);
	                     if (hssfRow == null) {
	                         continue;
	                     }

	                     // 将单元格中的内容存入集合
	                     User user = new User();

	                     XSSFCell cell = hssfRow.getCell(0);
	                     if (cell == null) {
	                         continue;
	                     }
	                     user.setUsername(cell.getStringCellValue());
	                     cell = hssfRow.getCell(1);
	                     
	                     if (cell == null) {
	                         continue;
	                     }
	                     user.setSex(cell.getStringCellValue());
	                     cell = hssfRow.getCell(2);
	                     
	                     if (cell == null) {
	                         continue;
	                     }
	                     user.setAge((int) cell.getNumericCellValue());
	                     cell = hssfRow.getCell(3);

	                     if (cell == null) {
	                         continue;
	                     }
	                     user.setNation(cell.getStringCellValue());
	                     cell = hssfRow.getCell(4);

	                     if (cell == null) {
	                         continue;
	                     }
	                     user.setAddress(cell.getStringCellValue());
	                     cell = hssfRow.getCell(5);

	                     if (cell == null) {
	                         continue;
	                     }
	                     user.setEducation(cell.getStringCellValue());
	                     cell = hssfRow.getCell(6);

	                     if (cell == null) {
	                         continue;
	                     }
	                     user.setPoliticalStatus(cell.getStringCellValue());
	                     cell = hssfRow.getCell(7);

	                     if (cell == null) {
	                         continue;
	                     }
	                     user.setIndustry(cell.getStringCellValue());
	                     cell = hssfRow.getCell(8);

	                     if (cell == null) {
	                         continue;
	                     }
	                     user.setPhone(cell.getStringCellValue());
	                     cell = hssfRow.getCell(9);

	                     if (cell == null) {
	                         continue;
	                     }
	                     user.setEmail(cell.getStringCellValue());
	                     cell = hssfRow.getCell(10);
	                     
	                     if (cell == null) {
	                         continue;
	                     }
	                     if(cell.getStringCellValue().equals("管理员")) {
	                     	user.setType(0);
	                     }else {
	                     	user.setType(1);
	                     }
	                    
	                     cell = hssfRow.getCell(11);


	                     list.add(user);
	                 }
	        	 }
	           
	        }
	        return list;
	    }

}
