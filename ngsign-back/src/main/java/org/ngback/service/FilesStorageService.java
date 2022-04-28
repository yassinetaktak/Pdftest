package org.ngback.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
	public void save(MultipartFile file, MultipartFile image,String numPage,String cordX,String cordY);

	public Resource load(String filename);

	public Stream<Path> loadAll();
}
