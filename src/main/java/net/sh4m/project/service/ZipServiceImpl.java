package net.sh4m.project.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import net.sh4m.project.util.TimeWatch;

@Service
public class ZipServiceImpl implements ZipService {
	private static final Logger logger = Logger.getLogger(ZipServiceImpl.class);
	@Override
	public List<String> getAllZipEntries(String zipPath) {
		TimeWatch watch = TimeWatch.start();
		List<String> allListFile = new ArrayList<String>();
		logger.info("Zip Path :" + zipPath);
		try {
			ZipFile zipFile = new ZipFile(zipPath);
			Enumeration<? extends ZipEntry> entries = zipFile.entries();

		    while(entries.hasMoreElements()){
		        ZipEntry entry = entries.nextElement();
		        allListFile.add(entry.getName());
		    }
		    return allListFile;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			logger.info("Elapsed Time " + watch.toMinuteSeconds());
		}
		
	}
	@Override
	public boolean extractToFolder(String zipFile, String fullTempFolderNameUnzip) {
		TimeWatch watch = TimeWatch.start();
		File dir = new File(fullTempFolderNameUnzip);
		if(!dir.exists()) {
		    dir.mkdirs();
		}else {
		    try {
                FileUtils.deleteDirectory(dir);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
		    dir.mkdirs();
		}
		
		
		byte[] buffer = new byte[1024];
		FileOutputStream fos = null;
		ZipInputStream zis = null;
		try {
			zis = new ZipInputStream(new FileInputStream(zipFile));
			ZipEntry ze = zis.getNextEntry();
			while (ze != null) {
				String fileName = ze.getName();
                if (ze.isDirectory()) {
                    ze = zis.getNextEntry();
                    continue;
                }
                fileName = new File(fileName).getName();
                File newFile = new File(fullTempFolderNameUnzip + File.separator + fileName);
                logger.info("file unzip : " + newFile.getAbsoluteFile());

                //create all non exists folders
                //else you will hit FileNotFoundException for compressed folder
                new File(newFile.getParent()).mkdirs();

                fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                ze = zis.getNextEntry();
               
			}
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			 try {
				fos.close();
				zis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("Elapsed Time " + watch.toMinuteSeconds());
		}
       
        

		return false;
	}

}
