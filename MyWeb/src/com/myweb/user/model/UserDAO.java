package com.myweb.user.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.*;
import javax.sql.DataSource;

import com.myweb.util.JdbcUtil;

public class UserDAO {

	//DB연동 클래스
	
	private DataSource ds;	//데이터 소스 객체 생성
	private Context ct; //javax.naming.*
	
	// 1. 스스로의 객체를 멤버객체로 선언 1개로 제한
	private static UserDAO instance = new UserDAO();
	
	//2. 외부에서 객체를 생성 할 수 없게 생성자 PRIVATE처리
	private UserDAO() {
		//생성자가 한번 동작할 때의 다음 내용 처리
		
		try{
			ct = new InitialContext(); 	//이니셜 컨텍스트 생성
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/oracle");  	//이니셜 컨택스트로부터 찾음 java:comp/env/사용하는db이름, env환경설정
			
			
		}catch(Exception e) {
			System.out.println("드라이버호출시 에러 발쌩");
			e.printStackTrace();
		}
		
	}
	
	//3. 외부에서 객체를 요구할 때 getter메서드만 써서 반환
	public static UserDAO getInstance() {
		return instance;
	}
	
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//--------------------ㅡ method를 선언하여 기능을 구현 -------------------
	
	//ID확인 메서드
	
	public int IdConfirm(String id) {
		int result = 0;
		
			String sql = "SELECT*FROM users WHERE id=?";
			
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,id);
				
				//sql실행
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					//return 1;
					result = 1;
				}else {
					//return 0;
					result = 0;
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					JdbcUtil.close(conn, pstmt, rs);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		
		return result;
	}
	
	public int join(UserVO vo) {
		int result = 0;
		
			String sql = "insert into users(id, pw, name, email, address) values(?,?,?,?,?)";
		
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,vo.getId());
					pstmt.setString(2,vo.getPw());
					pstmt.setString(3,vo.getName());
					pstmt.setString(4,vo.getEmail());
					pstmt.setString(5,vo.getAddress());
					
				result = pstmt.executeUpdate();
					
			}catch(SQLException sqle) {
				System.out.println("sql구문 오류 or db연동실패"+sqle);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					JdbcUtil.close(conn, pstmt, rs);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		return result;
	}
	
	//login메서드
	public int login(String id, String pw) {
		int result =0;
		
			String sql = "SELECT*FROM users WHERE id=? AND pw=?";
			
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, id);
					pstmt.setString(2, pw);
				rs = pstmt.executeQuery();
				
				if(rs.next()) result = 1;
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					JdbcUtil.close(conn, pstmt, rs);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		
		return result;
	}

	//회원정보전달 메소드 getUserInfo(id)
	public UserVO getUserInfo(String id) {
		UserVO vo = null;
		
			String sql = "SELECT*FROM users WHERE id=?";
		
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					String pw = rs.getString("pw");
					String name = rs.getString("name");
					String email = rs.getString("email");
					String address = rs.getString("address");
					Timestamp regdate = rs.getTimestamp("regdate");
					
					vo = new UserVO(id,pw,name,email,address,regdate);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					JdbcUtil.close(conn, pstmt, rs);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
		return vo;
	}
	//pw맞는지 확인하는 pwcheck메소드
	public int pwcheck(String id) {
		int result = 0;
		
			String sql = "SELECT pw FROM users WHERE id=?";
		
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "id");
				rs = pstmt.executeQuery();
				
				if(rs.next()) result = 1;
				
			} catch (Exception e) {
				e.printStackTrace();	
			}finally {
				JdbcUtil.close(conn, pstmt, rs);
			}
		
		return result;
	}
	
	//changePassword() 메소드 만들기
	public int changePassword(String id, String pw, String new_pw) {
		int result =0;
		
			String sql = "SELECT pw FROM users WHERE id=?";
			String sql2 = "UPDATE users SET pw=? WHERE id=? AND pw=?";
			
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					String user_pw = rs.getString("pw");
					if(user_pw.equals(pw)) {
						pstmt = conn.prepareStatement(sql2);
							pstmt.setString(1, new_pw);
							pstmt.setString(2, id);
							pstmt.setString(3, pw);
						result = pstmt.executeUpdate();	
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(conn, pstmt, rs);
			}
		
		return result;
	}
	
	//update()메서드
	public int update(UserVO vo) {
		int result = 0;
		
		String sql = "UPDATE users SET name=?,email=?,address=? WHERE id=?";
		
		try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, vo.getName());
					pstmt.setString(2, vo.getEmail());
					pstmt.setString(3, vo.getAddress());
					pstmt.setString(4, vo.getId());
					
				result = pstmt.executeUpdate();							
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return result;
	}
	
	//delete()
	public int delete(String id) {
		int result = 0;
		
			String sql = "DELETE FROM users WHERE id=?";
			
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, id);
				
					result = pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(conn, pstmt, rs);
			}
		
		return result;
	}
	
}
