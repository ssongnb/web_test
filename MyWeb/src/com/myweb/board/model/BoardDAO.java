package com.myweb.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.*;
import javax.sql.DataSource;

import com.myweb.util.Criteria;
import com.myweb.util.JdbcUtil;

public class BoardDAO {
	//1. 스스로 객체를 멤버변수로 선언하고 1개 제한 - 싱글톤
	private static BoardDAO instance = new BoardDAO();
	
	//2. 외부에서 객체를 생성 할 수 없도록 private설정
	private BoardDAO() {
		//커넥션풀을 꺼내는 작업
		try {

			InitialContext ctx = new InitialContext(); //Context.xml값에 저장된 설정을 저장하여 불러냄
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
			
		} catch (Exception e) {
			System.out.println("커넥션 풀링 에러 발생");
			e.printStackTrace();
		}
	}
	
	
	//3. 외부에서 객체를 요구할 때에 getter메서드만 써서 반환
	public static BoardDAO getInstance() {
		return instance;
	}
	
	//--------------------중복되는 코드를 멤버 변수로 선언
	private DataSource ds; 
	//어디에 쓰던 찾아서 사용하는거라 오류x
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	//------------------------- 메서드 구현
	
	//게시글 등록 메서드
	public void regist(String writer,String title,String content) {
		//반환값은 하고 싶으면 해도 됨
		
			String sql = "INSERT INTO board (writer, title, content) VALUES(?,?,?)";
			
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, writer);
					pstmt.setString(2, title);
					pstmt.setString(3, content);
				
				pstmt.executeUpdate();	
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(conn, pstmt, rs);
			}
		
	}
	//페이징작업을 하는 게시물 목록 처리 메서드
	public ArrayList<BoardVO> getList(Criteria cri){
		ArrayList<BoardVO> list = new ArrayList<>();
			
			String sql = "SELECT*FROM (SELECT rownum AS rnum, B.* FROM board B WHERE rownum <= ? "
					+ "ORDER BY num DESC) WHERE ? <= rnum";
		
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1,cri.getCount_oracle());	//몇개의 데이터 조회(끝)
					pstmt.setInt(2, cri.getPageStart());	//시작번호
				//검증
					System.out.println("끝 : "+cri.getCount_oracle());
					System.out.println("시작: "+cri.getPageStart());
					
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int num = rs.getInt("num");
					String writer = rs.getString("writer");
					String title = rs.getString("title");
					String content = rs.getString("content");
					Timestamp regdate = rs.getTimestamp("regdate");
					int hit  = rs.getInt("hit");

					BoardVO vo = new BoardVO(num, writer, title, content, regdate, hit);
					
					//생성된 vo를 리스트에 추가
					list.add(vo);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(conn, pstmt, rs);
			}
			
		return list;
	}
	
	//총게시물 수를 반환하는 메서드
	public int getTotal() {
		int result = 0;
			String sql = "SELECT count(*) AS total FROM board";
			
			try {
				conn = ds.getConnection();	
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					result = rs.getInt("total");
				}
				
				System.out.println("총 게시물 갯수 : "+result);
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(conn, pstmt, rs);
			}
		
		return result;
	}
	
	
	/*
	//게시물 목록 조회 메서드
	public ArrayList<BoardVO> getList(){
		ArrayList<BoardVO> list = new ArrayList<>();
		
			String sql = "SELECT*FROM board ORDER BY num DESC";	//게시글 번호 애림차순
		
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					//rs.getString, rs.getInt(), rs.getTimeStamp()
					//데이터를 vo에 담고, 이 데이터를 List에 저장할 코드를 작성
					
					 * BoardVO vo = new BoardVO();
					 * vo.setNum(rs.getInt("num"));
					 * vo.setWriter(rs.getString("writer"));
					 * vo.setTitle(rs.getString("title"));
					 * vo.setContent(rs.getString("content"));
					 * vo.setRegdate(rs.getTimeStemp("regdate"));
					 * vo.setHit(rs.getInt("hit"));
					 
				int num = rs.getInt("num");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp regdate = rs.getTimestamp("regdate");
				int hit  = rs.getInt("hit");

				BoardVO vo = new BoardVO(num, writer, title, content, regdate, hit);
				
				//생성된 vo를 리스트에 추가
				list.add(vo);
				
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(conn, pstmt, rs);
			}
			
		return list;
	}
	
	*/
	
	//num을 이용한 게시글 넘기는 getContent()
	public BoardVO getContent(String num) {
		BoardVO vo = new BoardVO();
		
			String sql = "SELECT*FROM board WHERE num=?";
		
			try {
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, num);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						vo.setNum(rs.getInt("num"));
						vo.setWriter(rs.getString("writer"));
						vo.setTitle(rs.getString("title"));
						vo.setContent(rs.getString("content"));
						vo.setRegdate(rs.getTimestamp("regdate"));
						
						vo.setHit(rs.getInt("hit"));					
					}
					
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(conn, pstmt, rs);
			}
		return vo;
	}
	
	//update()
	public void update(String num, String title, String content) {
		
		String sql = "UPDATE board SET title=?, content=? WHERE num=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, content);
				pstmt.setString(3, num);
					//자동으로 형변환을 하기때문에 string을 써도 되긴하지만 원래 setInt, Integer.parsetInt(num)을 사용
				pstmt.executeUpdate();
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	}
	
	
	//DELETE()
	public void delete(String num) {
		
		String sql = "DELETE FROM board WHERE num=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(num));
			
				pstmt.executeUpdate();	
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	}
	
	//조회수 증가
	public void upHit(String num) {
		
		String sql = "UPDATE board SET hit = hit+1 WHERE num=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(num));
			pstmt.executeUpdate();	
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	}
}
