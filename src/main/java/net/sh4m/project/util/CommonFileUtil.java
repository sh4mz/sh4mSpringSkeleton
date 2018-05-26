/**
 * @author ssaleh
 *
 * Created date May 31, 2017
 */
package net.sh4m.project.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class CommonFileUtil {

	public static List<String> getAllDir(String path){
		List<String> list = new ArrayList<String>();
		DirectoryStream<Path> dirStream = null;
		
		try {
			dirStream = Files.newDirectoryStream(Paths.get(path));
			for (Path entry : dirStream) {
	            if (Files.isDirectory(entry)) {
	                //System.out.println(entry.getFileName().toString());
	                list.add(entry.getFileName().toString());
	            }
	        }
			dirStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dirStream != null){
				try {
					dirStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public static List<String> getAllFiles(String path){
		List<String> list = new ArrayList<String>();
		DirectoryStream<Path> dirStream = null;
		try {
			dirStream = Files.newDirectoryStream(Paths.get(path));
			for (Path entry : dirStream) {
	            if (!Files.isDirectory(entry)) {
	                //System.out.println(entry.getFileName().toString());
	                list.add(entry.getFileName().toString());
	            }
	            
	        }
			dirStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dirStream != null){
				try {
					dirStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public static List<String> getAll(String path){
		List<String> list = new ArrayList<String>();
		DirectoryStream<Path> dirStream = null;
		try {
			dirStream = Files.newDirectoryStream(Paths.get(path));
			for (Path entry : dirStream) {
	                //System.out.println(entry.getFileName().toString());
	                list.add(entry.getFileName().toString());
	        }
			dirStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dirStream != null){
				try {
					dirStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	public static void main(String[] arg){
		String path = "\\\\pengtornado01\\TORNADO_TESTING\\0011651A";
		CommonFileUtil.getAllDir(path);
		//CommonFileUtil.getAllFiles(path);
		//CommonFileUtil.getAll(path);
	}

	/**
	 * @param fileName
	 * @return
	 */
	public static File getFileFor(String path,String fileName) {
		File file = new File(path + fileName);
        if (!file.exists()){
            return null;
        }
        return file;
		
	}
}
