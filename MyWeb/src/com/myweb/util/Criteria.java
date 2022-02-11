package com.myweb.util;

public class Criteria {
	//mysql, mariadb : select*from board by num desc limit ?,?;
	//oracle : select* from(select rownum as rnum , num, writer from board 
	//			WHERE ?(끝나는값) >= rownum order by num desc) where rnum >= ?(시작값);

	private int pageNum;	//페이지번호
	private int count;		//몇개의 값을 보여줄지 결정
	
	public Criteria() {
		//최초 게시판에 진입할 때  기본값 1번 페이지, 10개 데이터 세팅!!
		this.pageNum = 1;
		this.count = 10;
	}
	
	public Criteria(int pageNum, int count) {
		//전달받은 매개변수를 이용한 페이지값 출력
		super();
		this.pageNum = pageNum;
		this.count = count;
	}
	
	//mysql, miriadb limit x, count 구문의 x값을 구하는 메서드...limit0부터 시작
	public int getPageStart() {
		//return ((pageNum-1)*count)   -> mysql, mariadb인경우
		return ((pageNum -1 )*count) +1;	//rowdata는1부터 시작해서 		
	}
	
	
	//getter, setter 설정

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getCount() {
		return count;
	}
	
	public int getCount_oracle() {	// 위쿼리에 따라서는 첫번째 ? 값이 증가하기 때문에
		return (pageNum * count);
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	

	
	
}
