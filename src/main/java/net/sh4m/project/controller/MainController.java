/**
 * @author ssaleh
 *
 * Created date May 24, 2017
 */
package net.sh4m.project.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sh4m.project.RestURLConstant;
import net.sh4m.project.response.dto.BasicResponseDTO;
import net.sh4m.project.util.CommonFileUtil;

@Controller
public class MainController {
	
	
	
	@RequestMapping(value = "/testabc",
            method = RequestMethod.GET, produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Map<String, String> test()
    {
        
		Map<String, String> resp = new HashMap<String, String>() ;
		
		
		resp.put("Response", "success");
		return resp ;
    }
	
	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		
		return "index";
	}
	
	@RequestMapping(value = "/files",
            method = RequestMethod.GET, produces = {
                    MediaType.APPLICATION_OCTET_STREAM_VALUE})
    @ResponseBody
    public FileSystemResource getFile(@RequestParam(value="fileName") String fileName,HttpServletResponse  response) {
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		//String path = "\\\\pengtornado01\\TORNADO_TESTING\\0011651A\\060 Images\\062 Assembly Images\\";
		String path = "//Users//ssaleh//Documents//temp//upload//";
		return new FileSystemResource(CommonFileUtil.getFileFor(path,fileName)); 
    }
	
	@RequestMapping(value = "/sec/md5",
            method = RequestMethod.GET, produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public BasicResponseDTO md5Encrypt(@RequestParam(value="val") String value){
		BasicResponseDTO resp = new BasicResponseDTO();
		String myHash = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(value.getBytes());
			byte[] digest = md.digest();
		    myHash = DatatypeConverter
		      .printHexBinary(digest);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.setMessage(myHash);
		return resp;
	}
	
	
	
	@RequestMapping(value = "/testInterpreterXML",
			method = RequestMethod.GET, produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Map<String, Object> testInterpreterXML()
	{
		
		Map<String, Object> resp = new HashMap<String, Object>() ;
		return resp ;
	}
	
	@RequestMapping(value = "/downloadFile",
            method = RequestMethod.GET, produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public void downloadFile(HttpServletResponse response)
    {
        
		String fileContent = "testing 1 2 3";
		//ByteArrayInputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());

		response.setContentType("text/html");
		response.addHeader("Content-Disposition", "attachment; filename=Invoice.txt");

		ServletOutputStream stream;
		try {
			stream = response.getOutputStream();
			stream.write(fileContent.getBytes());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
	
	
	
	@RequestMapping(value = "/downloadFileZip",
            method = RequestMethod.GET, produces = {"application/zip"})
    @ResponseBody
    public void downloadFileZip(HttpServletResponse response)
    {
        
		String filePath = "/Users/ssaleh/Documents/Tornado/sample/POA-00024326_Restricted_Version_5.zip";
	    InputStream inputStream;
		try {
			response.setContentType("application/zip");
			response.addHeader("Content-Disposition", "attachment; filename=my.zip");
			inputStream = new FileInputStream(new File(filePath));
			//InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
			IOUtils.copy(inputStream, response.getOutputStream());
		    
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    

    }
	
	@RequestMapping(value = "/pub/getPdf",
            method = RequestMethod.GET)
    @ResponseBody
    public void getPdf(HttpServletResponse response)
    {
        
		String filePath = "/Users/ssaleh/Documents/DB/design.pdf";
		File file = new File(filePath);
	    InputStream inputStream;
		try {
			
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=design.pdf");
			inputStream = new FileInputStream(file);
			
			//InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
			IOUtils.copy(inputStream, response.getOutputStream());
		    
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    

    }
	
	@RequestMapping(value = "/pub/viewartwork",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView viewArtwork(HttpServletResponse response)
    {
		ModelAndView modelAndView = new ModelAndView("reportAAWViewArtwork");
	    
		return modelAndView;

    }
	
	@RequestMapping(value="/pub/getpdf1", method=RequestMethod.GET)
	public ResponseEntity<byte[]> getPDF1() {

		
		
		try {
			Path path = Paths.get("/Users/ssaleh/Documents/DB/design.pdf");
			byte[] data = Files.readAllBytes(path);
			HttpHeaders headers = new HttpHeaders();

		    headers.setContentType(MediaType.parseMediaType("application/pdf"));
		    String filename = "pdf1.pdf";

		    headers.add("content-disposition", "inline;filename=" + filename);

		    //headers.setContentDispositionFormData(filename, filename);
		    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		    ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);
		    return response;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	    
	}
}
