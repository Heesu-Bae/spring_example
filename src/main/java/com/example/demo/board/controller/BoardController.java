package com.example.demo.board.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.board.domain.BoardVO;
import com.example.demo.board.domain.FileVO;
import com.example.demo.board.service.BoardService;

@Controller
public class BoardController {
	
	@Resource(name="com.example.demo.board.service.BoardService")
	BoardService mBoardService;
	
	@RequestMapping("/list") // 게시판 리스트 화면 호출
	private String boardList(Model model) throws Exception {
		
		model.addAttribute("list", mBoardService.boardListService());
		
		return "list";
	}
	
	@RequestMapping("/detail/{bno}")
	private String boardDetail(@PathVariable int bno, Model model) throws Exception {
		
		model.addAttribute("detail", mBoardService.boardDetailService(bno));
		
		return "detail";
	}
	
	@RequestMapping("/insert") // 게시글 작성폼 호출
	private String boardInsertForm() {
		
		return "insert";
	}
	
	@RequestMapping("insertProc")
	private String boardInsertProc(HttpServletRequest request, @RequestPart MultipartFile files) throws Exception {
		
		BoardVO board = new BoardVO();
		FileVO file = new FileVO();
		
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		board.setWriter(request.getParameter("writer"));
		
		if (files.isEmpty()) {
			mBoardService.boardInsertService(board);
		}
		else {
		
			String sourceFileName = files.getOriginalFilename();
			String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName);
			File destinationFile;
			String destinationFileName;
			String fileUrl = "/Users/heesu/Documents/springspace/board/src/main/webapp/WEB-INF/uploadFiles/";
			
			do {
				destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
				destinationFile = new File(fileUrl + destinationFileName);
			} while(destinationFile.exists());
			
			destinationFile.getParentFile().mkdirs();
			files.transferTo(destinationFile);
			
			mBoardService.boardInsertService(board);
			
			file.setBno(board.getBno());
			file.setFileName(destinationFileName);
			file.setFileOriName(files.getOriginalFilename());
			file.setFileUrl(fileUrl);
			
			mBoardService.fileInsertService(file);
		}
		
		return "redirect:/list";
	}
	
	@RequestMapping("/update/{bno}") // 게시글 수정폼 호츌
	private String boardUpdateForm(@PathVariable int bno, Model model) throws Exception {
		
		model.addAttribute("detail", mBoardService.boardDetailService(bno));
		
		return "update";
	}
	
	@RequestMapping("/updateProc")
	private String boardUpdateProc(HttpServletRequest request) throws Exception {
		
		BoardVO board = new BoardVO();
		
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		board.setBno(Integer.parseInt(request.getParameter("bno")));
		
		mBoardService.boardUpdateService(board);
		
		return "redirect:/detail/" + request.getParameter("bno");
	}
	
	@RequestMapping("/delete/{bno}")
	private String boardDelete(@PathVariable int bno) throws Exception {
		
		mBoardService.boardDeleteService(bno);
		
		return "redirect:/list";
	}
}
