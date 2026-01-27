package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.Board;
import com.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/insertForm")
	public String boardInsertForm(Model model) {
		return "board/insertForm";
	}
	
	@PostMapping("/insert")
	public String postMethodName(Board board, Model model) {
		log.info("insert board =" +board.toString());
		try {
			int count = boardService.insertBoard(board);
			if(count > 0) {
				return "board/success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "board/failed";
	}
	
	@GetMapping("/boardList")
	public String boardList(Model model) {
		log.info("boardList");
		try {
			List<Board> boardList = boardService.boardList();
			model.addAttribute("boardList", boardList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "board/boardList";
	}
	
	@GetMapping("/detail")
	public String boardDetail(@RequestParam("no") int no, Model model) { // 파라미터를 직접 명시!
	    log.info("조회 요청 번호(no): " + no);
	    try {
	        Board board = boardService.selectByNo(no);
	        if(board == null) {
	            return "board/failed";
	        }
	        model.addAttribute("board", board);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return "board/detail";
	}
	
}












