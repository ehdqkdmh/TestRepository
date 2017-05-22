package com.mycompany.myapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class Exam09FormController {
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping(value="/form/exam01", method=RequestMethod.GET)
	public String joinForm() {
		return "form/exam01";
	}
	
	@RequestMapping(value="/form/exam01", method=RequestMethod.POST)
	public String join(
					String mid, String mname, String mpassword, 
					@RequestParam(defaultValue="0") int mage, 
					String[] mskill, String mbirth) {
		System.out.println("mid: " + mid);
		System.out.println("mname: " + mname);
		System.out.println("mpassword: " + mpassword);
		System.out.println("mage: " + mage);
		System.out.println("mskill: " + Arrays.toString(mskill));
		System.out.println("mbirth: " + mbirth);		
		return "form/exam01";
	}
	
	@RequestMapping(value="/form/exam02", method=RequestMethod.GET)
	public String joinForm2() {
		return "form/exam02";
	}
	
	@RequestMapping(value="/form/exam02", method=RequestMethod.POST)
	public String join2(
					String mid, String mname, String mpassword, 
					@RequestParam(defaultValue="0") int mage, 
					String[] mskill, String mbirth,
					MultipartFile attach) throws Exception {
		
		//파일의 정보 얻기
		String fileName = attach.getOriginalFilename();
		String contentType = attach.getContentType();
		long fileSize = attach.getSize();
		
		//파일을 서버 하드디스크에 저장
		String realPath = servletContext.getRealPath("/WEB-INF/upload/" + fileName);
		File file = new File(realPath);
		attach.transferTo(file);
		
		System.out.println("fileName: " + fileName);
		System.out.println("contentType: " + contentType);
		System.out.println("fileSize: " + fileSize);
		
		return "form/exam02";
	}	
	
	@RequestMapping("/file/exam03")
	public void download(HttpServletResponse response, @RequestHeader("User-Agent") String userAgent) throws Exception {
		//응답 HTTP 헤더행을 추가
		//1) 파일의 이름(옵션)
		String fileName = "사막.jpg";
		String encodingFileName;
		if(userAgent.contains("MSIE") || userAgent.contains("Trident") || userAgent.contains("Edge")) {
			encodingFileName = URLEncoder.encode(fileName, "UTF-8");
		} else {
			encodingFileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		}
		response.addHeader("Content-Disposition", "attachment; filename=\"" + encodingFileName + "\"");
		//2) 파일의 종류(필수)
		response.addHeader("Content-Type", "image/jpeg");
		//3) 파일의 크기(옵션)
		File file = new File(servletContext.getRealPath("/WEB-INF/upload/사막.jpg"));
		long fileSize = file.length();
		response.addHeader("Content-Length", String.valueOf(fileSize));
		
		//응답 HTTP 본문에 파일 데이터를 출력
		OutputStream os = response.getOutputStream();
		FileInputStream fis = new FileInputStream(file);
		FileCopyUtils.copy(fis, os);
		os.flush();
		fis.close();
		os.close();
	}
}
