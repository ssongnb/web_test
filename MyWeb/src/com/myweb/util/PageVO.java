package com.myweb.util;

public class PageVO {

	//화면에 그려질 버튼들의 개수를 계산하는 클래스
	private int startPage;	//시작번호
	private int endPage;	//끝번호
	private int total;		//전체 게시글
	
	private int pageNum;	//현재조회하는 페이지
	private boolean next;	//다음, 이전 버튼 활성화 여부
	private boolean prev;	//이전 버튼 활성화 여부
	private Criteria cri;	//페이지 기준
	
	//생성자를 기본생성자로 생성하지 못하고, total과 Criteria를 받아서 계산 처리하도록 선언
	
	public PageVO(int total, Criteria cri) {
		//전달되는 매개변수에서 초기값을 저장
		this.total = total;	//전달 매개변수
		this.cri = cri;		//초기값
		this.pageNum = cri.getPageNum();
		
		//끝페이지 계산
		//조회하는 페이지 번호가 1~10 -> 10
		//조회하는 페이지 번호가 11~20 -> 20
		//Math.ceil() 올림함수
		//계산식 = (int)Math.ceil(조회하는 페이지번호/ (double)10 ) *10
		this.endPage = (int)Math.ceil(cri.getPageNum()/(double)10)*10;
		
		//시작페이지 계산
		//끝페이지번호 +(- 한번에보여질 페이지번호 +1)
		this.startPage = endPage -9; //한번에 보여질 페이지번호 -> 하단페이지  endPage + (-cri.getCount()+1); 는 번호가지정안되어 있어서 오류날수 있음
		
		//페이지 번호가 적을때 나타내는 진짜 끝번호
		int realEnd = (int)Math.ceil(this.total/((double)10));
		//total은 전체 게시물, ex) 117 / 10 ->11.7 ->12
		
		//마지막 페이지세팅에서 보여줘야할 번호
		if(this.endPage > realEnd) {
			this.endPage = realEnd;
		}
		
		//이전페이지 버튼 활성화 여부
		//startPage -> 1, 11, 21, 31,,,
		//startPage가 1보다크면 true 
		this.prev = this.startPage > 1;
		
		//다음페이지 버튼활성화 여부
		this.next = realEnd > this.endPage;
		
	}

	
	//getter, setter
	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	
	
}
