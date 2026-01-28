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
		log.info("insert board =" + board.toString());
		try {
			int count = boardService.insertBoard(board);
			if (count > 0) {
				model.addAttribute("message", "%s님 회원정보가 등록되었습니다.".formatted(board.getWriter()));
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
			if (board == null) {
				model.addAttribute("message", "%d 님의 상세정보가 없습니다.".formatted(board.getNo()));
				return "board/failed";
			}
			model.addAttribute("board", board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "board/detail";
	}

	@GetMapping("/delete")
	public String boardDelete(Board board, Model model) {
		log.info("boardDelete board= " + board.toString());
		try {
			int count = boardService.deleteBoard(board);
			if (count > 0) {
				model.addAttribute("message", "%d 님의 게시글이 삭제되었습니다.".formatted(board.getNo()));
				return "board/success";
			}
			model.addAttribute("board", board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "%s 님의 게시글 삭제실패하였습니다.".formatted(board.getWriter()));
		return "board/failed";
	}

	@GetMapping("/updateForm")
	public String boardUpdateForm(Board b, Model model, @RequestParam("no") int no) {
		log.info("boardUpdateForm board= " + b.toString());
		try {
			Board board = boardService.selectByNo(no);
			if (board == null) {
				model.addAttribute("message", "%d 님의 정보가 없습니다.".formatted(board.getNo()));
				return "board/failed";
			}
			model.addAttribute("board", board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "board/updateForm";
	}

	@PostMapping("/update") // 수정할 게시글 no 확인 필요
	public String boardUpdate(Board b, Model model) { 
		log.info("boardUpdate board =" + b.toString());
		try {
			int count = boardService.updateBoard(b);
			if (count > 0) {
				model.addAttribute("message", "%s님의 게시글 수정성공".formatted(b.getWriter()));
				return "board/success";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "%s님의 게시글 수정실패".formatted(b.getWriter()));
		return "board/failed";
	}
	
	@GetMapping("/search")
	public String boardSearch(Board board, Model model) {
	    log.info("boardSearch board="+board.toString());
	    try {
	        List<Board> boardList = boardService.boardSearch(board);
	        model.addAttribute("boardList", boardList);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return "board/boardList";
	}
	
}












