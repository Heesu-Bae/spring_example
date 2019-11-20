package com.example.demo.board.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.board.domain.BoardVO;
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
	private int boardInsertProc(HttpServletRequest request) throws Exception {
		
		BoardVO board = (BoardVO) request.getParameterMap();
		
		return mBoardService.boardInsertService(board);
	}
	
	@RequestMapping("/update/{bno}") // 게시글 수정폼 호츌
	private String boardUpdateForm(@PathVariable int bno, Model model) throws Exception {
		
		model.addAttribute("detail", mBoardService.boardDetailService(bno));
		
		return "update";
	}
	
	@RequestMapping("/updateProc")
	private int boardUpdateProc(HttpServletRequest request) throws Exception {
		
		BoardVO board = (BoardVO) request.getParameterMap();
		
		return mBoardService.boardUpdateService(board);
	}
	
	@RequestMapping("/delete/{bno}")
	private String boardDelete(@PathVariable int bno) throws Exception {
		
		mBoardService.boardDeleteService(bno);
		
		return "redirect:/list";
	}
}
