package com.leo.google;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;


/**
 * 源代码统计量
 * 
 * @createTime: 2012-8-1 下午01:25:56
 * @changesSum:
 * 
 */
public class CountSrcLine {
	
	private static final String FILE_TYPE = "java";
	
	private long rows = 0;
	
	private StringBuffer sbBuffer = new StringBuffer();

	/**
	 * 根据文件计算代码行数
	 * @param file
	 * @return
	 * @throws IOException
	 * @return long
	 * 2012-8-1 下午01:43:38
	 */
	public long staticRowsByFile(File file) throws IOException {
		if (file.isDirectory()) { // 非文�?			throw new IOException("is not file:" + file);
		} else if (!file.getName().endsWith("." + FILE_TYPE)) { // 非java文件
			return 0;
		}
		
		long rows = 0;
		
		FileInputStream fis = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		
		String str = null;
		while ((str = br.readLine()) != null) {
			str = str.trim();
			if (str.length() > 1 && !str.startsWith("/") && !str.startsWith("*")) {
				rows ++;
			}
		}
		
		return rows;
	}
	

	public long staticRowsByDirectory(File dirFile) throws IOException {
		if (!dirFile.isDirectory()) {
			throw new IOException("is not Directory:" + dirFile);
		}
		
		File[] files = dirFile.listFiles();
		for (File childFile : files) {
			if (childFile.isDirectory()) {
				staticRowsByDirectory(childFile);
			} else {
				rows += staticRowsByFile(childFile);
			}
		}
		return rows;
	}
	

	public String getSrcByFile(File file) throws IOException {
		if (file.isDirectory()) { // 	throw new IOException("is not file:" + file);
		} else if (!file.getName().endsWith("." + FILE_TYPE)) { // 非java文件
			return "";
		}
		
		StringBuffer sbBuffer = new StringBuffer();
		
		FileInputStream fis = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		
		String str = null;
		while ((str = br.readLine()) != null) {
			if (str.trim().length() > 0) {
				sbBuffer.append(str);
				sbBuffer.append("\r\n");
			}
		}
		
		return sbBuffer.toString();
	}
	

	public String getSrcByDirectory(File dirFile) throws IOException {
		if (!dirFile.isDirectory()) {
			throw new IOException("is not Directory:" + dirFile);
		}
		
		File[] files = dirFile.listFiles();
		for (File childFile : files) {
			if (childFile.isDirectory()) {
				getSrcByDirectory(childFile);
			} else {
				sbBuffer.append(getSrcByFile(childFile));
			}
		}
		return sbBuffer.toString();
	}
	
	public static void main(String[] args) throws IOException {

		File file = new File("D:\\workspace_eclipse\\workspace0822\\FaceTest\\src");
		CountSrcLine jsu = new CountSrcLine();
		System.out.println(jsu.staticRowsByDirectory(file));
		
		OutputStream os = new FileOutputStream(new File("d:/crm.java"));
		os.write(jsu.getSrcByDirectory(file).getBytes());
	}
}
