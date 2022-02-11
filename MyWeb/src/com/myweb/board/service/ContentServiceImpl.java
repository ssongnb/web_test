package com.myweb.board.service;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;
import com.myweb.board.model.BoardVO;

public class ContentServiceImpl implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//System.out.println(request.getParameter("num")); //유효값 확인
		/*
		 * 	1. dao에 getContent(num) 매서드를 생성하고, 호출
		 * 	2. getContent()메서드에서는 num을 가지고, 게시글에 대한 정보를 vo객체에 담는 코드를 작성
		 * 	3. 메서드 리턴타입은 BoardVO
		 * 	4. 화면전송을 위한 리턴값은 vo라는 이름으로 강제 저장처리한다. 
		 */
		
		String num = request.getParameter("num");
		
		//dao
		BoardDAO dao = BoardDAO.getInstance();
		
		
		//(2).쿠키값 생성 : 서버의 요청시 자동으로 request에 담겨서 전달됨
		//해당쿠키값이 있는지 확인
		 Cookie[] arr = request.getCookies();	//생성된 쿠키값을 얻기...
		 
		 //쿠키검사
		 boolean bool = true;
		 for(Cookie c : arr) {
			 if(c.getName().contentEquals("hitNum"+num)) {	//num은 글번호/게시물번호 - 쿠키이름 게시번호의 쿠키인지 확인
				 			//contentEquals 문자열 자체를 비교할 떄 사용
				 bool = false;	//false면 클릭한적이 있다.
				 break;			//증가시키지 않음
				 
			 }
		 }
		
		 //조회수 업데이트
		 if(bool) {	//bool이 true면 클릭한 적이 없다
			 dao.upHit(num);	//hit값을 +1해서 업데이트하는 메서드
			 
		 }
		  
		 BoardVO vo = dao.getContent(num);	//결과값을 받아오는 메서드 반환값이 BoardVO
			
			request.setAttribute("vo", vo);	//request에 강제저장
			
			//중복 증가를 방지하기 위한 쿠키 생성(1);
			Cookie hitcoo = new Cookie("hitNum"+num,num);	//num게시글 번호
					hitcoo.setMaxAge(60);
			response.addCookie(hitcoo);
			
			
	}
	

}
