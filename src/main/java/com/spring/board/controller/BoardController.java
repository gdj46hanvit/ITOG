package com.spring.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.HomeController;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.PageVo;
import com.spring.common.CommonUtil;

@Controller
public class BoardController {
	
	@Autowired 
	boardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	// 리스트
	@RequestMapping(value = "/board/boardList.do", method = RequestMethod.GET)
	public String boardList(Locale locale, Model model,PageVo pageVo) throws Exception{
		
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		
		int page = 1;
		int totalCnt = 0;
		
		if(pageVo.getPageNo() == 0){
			pageVo.setPageNo(page);;
		}
		
		boardList = boardService.selectBoardList(pageVo);
		totalCnt = boardService.selectBoardCnt();
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageNo", page);
		
		return "board/boardList";
	}
	// 상세보가
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardView.do", method = RequestMethod.GET)
	public String boardView(Locale locale, Model model
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum) throws Exception{
		
		BoardVo boardVo = new BoardVo();
		
		
		boardVo = boardService.selectBoard(boardType,boardNum);
		
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);
		
		return "board/boardView";
	}
	// 작성 폼
	@RequestMapping(value = "/board/boardWrite.do", method = RequestMethod.GET)
	public String boardWrite(Locale locale, Model model) throws Exception{
		
		
		return "board/boardWrite";
	}
	
	// 작성 엑션
	@RequestMapping(value = "/board/boardWriteAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardWriteAction(Locale locale, BoardVo boardVo) throws Exception{
		
		
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		int resultCnt = boardService.boardInsert(boardVo);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		
		
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
	
	//수정
	@RequestMapping(value = { "/board/{boardType}/{boardNum}/updateBoard.do" }, method = { RequestMethod.GET })
    public String updateBoard(final Locale locale, final Model model, @PathVariable("boardType") final String boardType, @PathVariable("boardNum") final int boardNum) throws Exception {
        BoardVo boardVo = new BoardVo();
        boardVo = this.boardService.selectBoard(boardType, boardNum);
        model.addAttribute("boardType", (Object)boardType);
        model.addAttribute("boardNum", (Object)boardNum);
        model.addAttribute("board", (Object)boardVo);
        return "board/updateBoard";
    }
    
    @RequestMapping(value = { "/board/updateBoardAction.do" }, method = { RequestMethod.POST })
    @ResponseBody
    public String updateBoard(final Locale locale, final BoardVo boardVo) throws Exception {
        final HashMap<String, String> result = new HashMap<String, String>();
        final CommonUtil commonUtil = new CommonUtil();
        final int resultCnt = this.boardService.updateBoard(boardVo);
        result.put("success", (resultCnt > 0) ? "Y" : "N");
        final String callbackMsg = CommonUtil.getJsonCallBackString(" ", (Object)result);
        System.out.println("callbackMsg::" + callbackMsg);
        return callbackMsg;
    }
    
    @RequestMapping(value = { "/board/deleteBoardAction.do" }, method = { RequestMethod.POST })
    @ResponseBody
    public String deleteBoardAction(final BoardVo boardVo) throws Exception {
        final HashMap<String, String> result = new HashMap<String, String>();
        final CommonUtil commonUtil = new CommonUtil();
        final int resultCnt = this.boardService.deleteBoard(boardVo);
        result.put("success", (resultCnt > 0) ? "Y" : "N");
        final String callbackMsg = CommonUtil.getJsonCallBackString(" ", (Object)result);
        return "redirect:board/boardList";
    }
}