package com.myweb.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;

public class DeleteServiceImpl implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//게시판 삭제
		String num = request.getParameter("num");
		
		//dao
		BoardDAO dao = BoardDAO.getInstance();
		dao.delete(num);
		
		
	}

}
