/**
 * @author ssaleh
 *
 * Created date 26 Jul 2017
 */
package net.sh4m.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.InputSource;

public interface FileService {

	/**
	 * @param id
	 */
	void updateWIPPathStatusAsync(Long id);

	/**
	 * @param folderName 
	 * @param uploadFile
	 * @return
	 */
	Map<String, String> templateBasicInfoUploadFile(String folderName, MultipartFile uploadFile);

	/**
	 * @param fullPathFileNameMaster
	 * @param fullPathFileNameSlave
	 * @return
	 */
	boolean copyFileToAnotherDirectory(String FromFullFilePath, String ToFolder);

	/**
	 * @param xml
	 * @param fullPath
	 * @return
	 */
	boolean writeToDirectory(String xml, String fullPath);

	List<String> getAllFilesInDir(String fullTempFolderNameUnzip);

	/**
	 * @param fullFilePath
	 * @return
	 */
	InputSource readFileIS(String fullFilePath);

}
