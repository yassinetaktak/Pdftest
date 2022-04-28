package org.ngback.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.ngback.service.FilesStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
	private final Path root = Paths.get("uploads");

	@Override
	public void save(MultipartFile file, MultipartFile img,String numPage,String cordX,String cordY) {
		try {
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
			Path path = Paths.get(file.getOriginalFilename());
			Files.copy(img.getInputStream(), this.root.resolve(img.getOriginalFilename()));
			Path pathImage = Paths.get(img.getOriginalFilename());
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
		try {

			// Loading an existing document
			File file1 = new File(this.root + "/" + file.getOriginalFilename());
			PDDocument doc = PDDocument.load(file1);

			// Retrieving the page
			
			PDPage page = doc.getPage(0);
			// Creating PDImageXObject object
			PDImageXObject pdImage = PDImageXObject.createFromFile(this.root + "/" + img.getOriginalFilename(), doc);

			// creating the PDPageContentStream object
			PDPageContentStream contents = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true);

			// Drawing the image in the PDF document
			contents.drawImage(pdImage,Integer.parseInt(cordX), Integer.parseInt(cordY));

			System.out.println("Image added");

			// Closing the PDPageContentStream object
			contents.close();

			// Saving the document
			doc.save(this.root + "/" + file.getOriginalFilename());

			// Closing the document
			doc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Resource load(String filename) {
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
		} catch (IOException e) {
			throw new RuntimeException("Could not load the files!");
		}
	}

}
