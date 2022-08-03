package com.spring.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDao;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.PageVo;

@Service
public class boardServiceImpl implements boardService{
	
	@Autowired
	BoardDao boardDao;
	
	@Override
	public String selectTest() throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectTest();
	}
	
	@Override
	public List<BoardVo> selectBoardList(PageVo pageVo) throws Exception {
		// TODO Auto-generated method stub
		
		return boardDao.selectBoardList(pageVo);
	}
	
	@Override
	public int selectBoardCnt() throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectBoardCnt();
	}
	
	@Override
	public BoardVo selectBoard(String boardType, int boardNum) throws Exception {
		// TODO Auto-generated method stub
		BoardVo boardVo = new BoardVo();
		
		boardVo.setBoardType(boardType);
		boardVo.setBoardNum(boardNum);
		
		return boardDao.selectBoard(boardVo);
	}
	
	@Override
	public int boardInsert(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		int[] results= new int[boardVo.getBoardVoList().size()];
		int result=1;
		
		for(int i=0; i<boardVo.getBoardVoList().size();i++) {
			results[i]=boardDao.boardInsert(boardVo.getBoardVoList().get(i));
		}
		return result;
	}

	@Override
	public int updateBoard(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.updateBoard(boardVo);
	}

	@Override
	public int deleteBoard(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.deleteBoard(boardVo);
	}

	@Override
	public List<BoardVo> insertBoardList(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.insertBoardList(boardVo);
	}
	
}
