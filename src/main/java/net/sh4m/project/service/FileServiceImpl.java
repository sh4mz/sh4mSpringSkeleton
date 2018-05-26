/**
 * @author ssaleh
 *
 * Created date 26 Jul 2017
 */
package net.sh4m.project.service;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.InputSource;

import com.google.common.eventbus.AsyncEventBus;

import net.sh4m.project.ErrorMessageConstant;

@Service
public class FileServiceImpl implements FileService {

	private static final Logger logger = Logger.getLogger(FileServiceImpl.class);
	
	@Autowired
	private AsyncEventBus asyncEventBus;
	
	@Value("#{propVal['TEMPLATE.BASICINFO.TEMP.DIRECTORY']}")
    private String uploadBasicInfoTempDirectory;
	
	/* (non-Javadoc)
	 * @see net.sh4m.project.service.FileService#updateWIPPathStatus(java.lang.Long)
	 */
	@Override
	public void updateWIPPathStatusAsync(Long id) {
		
		
		
	}

	/* (non-Javadoc)
	 * @see net.sh4m.project.service.FileService#templateBasicInfoUploadFile(org.springframework.web.multipart.MultipartFile[])
	 */
	@Override
	public Map<String, String> templateBasicInfoUploadFile(String folderName, MultipartFile uploadFile) {
		Map<String, String> result = new HashMap<String, String>();
		
		
		boolean statusUpload = this.uploadFileToDirectory(uploadFile, uploadBasicInfoTempDirectory  + folderName + "//");
		if (!statusUpload){
			result.put(ErrorMessageConstant.UPLOAD_FILE_KEY, ErrorMessageConstant.UPLOAD_FILE_FAIL_VAL);
		}
		
		
		return null;
	}

	/**
	 * @param eachFile
	 * @param uploadBasicInfoTempDirectory
	 * @return
	 */
	private boolean uploadFileToDirectory(MultipartFile eachFile, String uploadBasicInfoTempDirectory) {
		try {
			if(! new File(uploadBasicInfoTempDirectory).exists())
	        {
	            new File(uploadBasicInfoTempDirectory).mkdir();
	        }
		} catch (Exception e) {
			return false;
		}
		
		
		String orgName = eachFile.getOriginalFilename();
        String filePath = uploadBasicInfoTempDirectory + orgName;
        File dest = new File(filePath);
        try {
        	eachFile.transferTo(dest);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see net.sh4m.project.service.FileService#copyFileToAnotherDirectory(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean copyFileToAnotherDirectory(String FromFullFilePath, String ToFolder) {
		File source = new File(FromFullFilePath);
		if (!source.exists()){
			logger.error("Path not found. ->" + FromFullFilePath);
			return false;
		}
		
		File dest = new File(ToFolder);
		if (!dest.exists()){
			dest.mkdirs();
		}
		
		dest = new File(ToFolder+source.getName());
		
//		try {
//			Files.copy(source.toPath(), dest.toPath());
//			return true;
//		} catch (IOException e) {
//			logger.error("Copy error " + e.getMessage());
//			e.printStackTrace();
//		}
		
		return copyFile(source, dest);
	}

	private boolean copyFile(File source, File dest) {
		FileChannel sourceChannel = null;
	    FileChannel destChannel = null;
		
	    try {
	        sourceChannel = new FileInputStream(source).getChannel();
	        destChannel = new FileOutputStream(dest).getChannel();
	        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
	        
       } catch (IOException e) {
    	   logger.error("Copy error " + e.getMessage());
    	   e.printStackTrace();
    	   return false;
       } finally {
           try {
				sourceChannel.close();
				destChannel.close();
           } catch (IOException e) {
			
        	   	e.printStackTrace();
           }
          
	   }
		return true;
		
	}

	/* (non-Javadoc)
	 * @see net.sh4m.project.service.FileService#writeToDirectory(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean writeToDirectory(String xml, String fullPath) {
		try {
			logger.info("Write file : " + fullPath);
			Files.write(Paths.get(fullPath), xml.getBytes(Charset.forName("UTF-8")));
			return true;
		} catch (IOException e) {

			e.printStackTrace();
			logger.info("write failed : " + fullPath);
		}
		return false;
	}

	@Override
	public List<String> getAllFilesInDir(String fullTempFolderNameUnzip) {
		List<String> allFilesInDir = new ArrayList<String>();
		File dir = new File(fullTempFolderNameUnzip);
		if(!dir.exists()) {
			return null;
		}
		List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		for (File file : files) {
			allFilesInDir.add(file.getName());
        }
		return allFilesInDir;
	}

	/* (non-Javadoc)
	 * @see net.sh4m.project.service.FileService#readFileIS(java.lang.String)
	 */
	@Override
	public InputSource readFileIS(String fullFilePath) {
		
		try {
			File file = new File(fullFilePath);
			FileInputStream fileIS;
			fileIS = new FileInputStream(file);
			InputStream inputStream = new BufferedInputStream(fileIS);
			InputSource inputSource = new InputSource(inputStream);
			return inputSource;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
}
