package usertable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DatabaseUtil;

public class UserDAO {
	
	// 아이디와 비밀번호를 받아서 로그인을 시도해주는 함수 결과는 정수형으로 반환
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USERTABLE WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; // 로그인 성공
				} else {
					return 0; // 비밀번호 틀림
				}
			}
			return -1; // 아이디 없음
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			try {if(pstmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
		}
		return -2; // 데이터베이스 오류
	}
	
	// user라는 객체에서 사용자의 정보 받기 => 회원가입 수행해주는 함수 결과는 정수형으로 반환
	public int join(UserDTO user) {
		// true = 1, false = 0
		String SQL = "INSERT INTO USERTABLE VALUES(?, ?, ?, ?, '0')";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserEmail());
			pstmt.setString(4, user.getUserEmailHash());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			try {if(pstmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
		}
		return -1; // 회원가입 실패
	}
	
	// 사용자의 아이디값을 받아서 사용자의 이메일값을 반환해주는 함수 결과는 문자형으로 반환
	public String getUserEmail(String userID) {
		String SQL = "SELECT userEmail FROM USERTABLE WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				// 만약 존재하는 사용자의 아이디인 경우 이메일값 반환
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			try {if(pstmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
		}
		return null;
	}

	// 사용자가 현재 이메일 인증이 되었는지 확인해주는 함수 결과는 원래 boolean값 반환인데 oracle은 boolean 값이 없으므로 true(1), false(0) 문자형으로 구분해서 반환
	public String getUserEmailChecked(String userID) {
		// true = 1, false = 0
		String SQL = "SELECT userEmailChecked FROM USERTABLE WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				// 만약 존재하는 사용자의 아이디인 경우 true값 반환
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			try {if(pstmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
		}
		// 만약 존재하지 않는 사용자의 아이디인 경우 false값 반환
		return "0";
	}
	
	// 특정한 사용자의 이메일 인증을 수행해주는 함수 결과는 원래 boolean값 반환인데 oracle은 boolean 값이 없으므로 true(1), false(0) 문자형으로 구분해서 반환
	// 이메일 인증 완료되면 0값이 1로 바뀜
	public String setUserEmailChecked(String userID) {
		// true = 1, false = 0
		String SQL = "UPDATE USERTABLE SET userEmailChecked = '1' WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.executeUpdate();
			return "1";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			try {if(pstmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
		}
		return "0"; // 데이터베이스 오류
	}
}
