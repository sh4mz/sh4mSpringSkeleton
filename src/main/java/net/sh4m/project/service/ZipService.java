package net.sh4m.project.service;

import java.util.List;

public interface ZipService {

	List<String> getAllZipEntries(String zipPath);

	boolean extractToFolder(String zipFile, String fullTempFolderNameUnzip);

}
