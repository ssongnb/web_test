package com.myweb.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.SendResult;

import com.myweb.board.service.ContentServiceImpl;
import com.myweb.board.service.DeleteServiceImpl;
import com.myweb.board.service.GetListServiceImpl;
import com.myweb.board.service.IBoardService;
import com.myweb.board.service.RegisterServicelmpl;
import com.myweb.board.service.UpdateServiceImpl;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	//.Board로 끝나는 요청은 이 컨트롤러로 들어오도록 처리
	//1. get, post 요청을 하나의 메서드에 연결 -> doAction()으로
	//2. context path를 제거 / board/list.board 요청으로 들어오면 board_list화면으로 이동
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//컨트로러로 들어온 명령을 구분해서 처리하는 구간
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();	//요청주소(http[s]://를 뺀 주소)
		String path = request.getContextPath();	//context 주소를 얻어옴.
		String command = uri.substring(path.length()); 	// /board/list.board로 만드는 과정.
		
		System.out.println(command);
		
		IBoardService service = null;
		
		//command에 따른 동작 구현.....
		
		if(command.equals("/board/list.board")) {
			//게시글 목록을 가지고 화면으로 이동
			service = new GetListServiceImpl();
			service.execute(request, response);
			
			//response.sendRedirect("board_list.jsp");
			RequestDispatcher dp = request.getRequestDispatcher("board_list.jsp");
			dp.forward(request, response);
			
		}else if(command.equals("/board/write.board")) {
			//글작성 페이지(view)로 이동
			response.sendRedirect("board_write.jsp");
		
			
		}else if(command.equals("/board/register.board")) {
			//글등록  board_write.jsp
			service = new RegisterServicelmpl();
			service.execute(request, response);
			
			response.sendRedirect("list.board");
		
		}else if(command.equals("/board/content.board")) {
			//글보기(상세보기)
			service = new ContentServiceImpl();
			service.execute(request, response);
			
			//forwarding
			RequestDispatcher dp = request.getRequestDispatcher("board_content.jsp");
			dp.forward(request, response);
			
		}else if(command.equals("/board/modify.board")) {
			//글내용 수정 뷰를 제공(수정화면 요청)
			service = new ContentServiceImpl();
			service.execute(request, response);
			
			//forwarding
			RequestDispatcher dp = request.getRequestDispatcher("board_modify.jsp");
			dp.forward(request, response);
			
		}else if(command.equals("/board/update.board")) {
			//수정하기
			service = new UpdateServiceImpl();
			service.execute(request, response);
			
			String num = request.getParameter("num");
			response.sendRedirect("content.board?num="+num);	// 다시 컨드롤러로
		}else if(command.equals("/board/delete.board")) {
			//삭제하기
			service = new DeleteServiceImpl();
			service.execute(request, response);
			
			response.sendRedirect("list.board");
		}
	
	
	
	}
	
}
