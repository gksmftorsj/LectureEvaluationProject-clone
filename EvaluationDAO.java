package evaluation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DatabaseUtil;

public class EvaluationDAO {

		// 강의평가 정보를 기록할 수 있게 해주는 글쓰기 함수
		public int write(EvaluationDTO evaluationDTO) {
			String SQL = "INSERT INTO EVALUATION VALUES(EVALUATION_ID_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = DatabaseUtil.getConnection();
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, evaluationDTO.getUserID());
				pstmt.setString(2, evaluationDTO.getLectureName());
				pstmt.setString(3, evaluationDTO.getProfessorName());
				pstmt.setInt(4, evaluationDTO.getLectureYear());
				pstmt.setString(5, evaluationDTO.getSemsterDivide());
				pstmt.setString(6, evaluationDTO.getLectureDivide());
				pstmt.setString(7, evaluationDTO.getEvaluationTtitle());
				pstmt.setString(8, evaluationDTO.getEvaluationContent());
				pstmt.setString(9, evaluationDTO.getTotalScore());
				pstmt.setString(10, evaluationDTO.getCreditScore());
				pstmt.setString(11, evaluationDTO.getComfortableScore());
				pstmt.setString(12, evaluationDTO.getLectureScore());
				return pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
				try {if(pstmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
				try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			}
			return -1; // 데이터베이스 오류
		}
		
		public ArrayList<EvaluationDTO> getList(String lectureDivide, String searchType, String search, int pageNumber) {
			if(lectureDivide.equals("전체")) {
				lectureDivide = "";
			}
			ArrayList<EvaluationDTO> evaluationList = null;
			String SQL = "";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				if(searchType.equals("최신순")) {
					SQL = "SELECT * FROM EVALUATION WHERE lectureDivide LIKE ? AND CONCAT(lectureName, professorName, evaluationTitle, evaluationContent) LIKE " + "? ORDER BY evaluationID DESC LIMIT" + pageNumber * 5 + ", " + pageNumber * 5 + 6;
				} else if(searchType.equals("추천순")) {
					// 6개까지 존재한다는 것은 다음페이지가 존재한다는 뜻
					SQL = "SELECT * FROM EVALUATION WHERE lectureDivide LIKE ? AND CONCAT(lectureName, professorName, evaluationTitle, evaluationContent) LIKE " + "? ORDER BY likeCount DESC LIMIT" + pageNumber * 5 + ", " + pageNumber * 5 + 6;
				}
				conn = DatabaseUtil.getConnection();
				pstmt = conn.prepareStatement(SQL);
				// %lecture% => lecture 값이 포함되는지
				// 전체는 공백으로 치환해서 항상 포함하도록 만들어주고 나머지 전공, 교양, 기타는 동일한 결과만 출력되도록 
				pstmt.setString(1, "%" + lectureDivide + "%");
				pstmt.setString(2, "%" + search + "%");
				rs = pstmt.executeQuery();
				evaluationList = new ArrayList<EvaluationDTO>();
				while(rs.next()) {
					// 특정한 결과가 나올 때마다 결과를 이용해서 초기화해줌
					EvaluationDTO evaluation = new EvaluationDTO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getString(10),
						rs.getString(11),
						rs.getString(12),
						rs.getString(13),
						rs.getInt(14)
					);
					evaluationList.add(evaluation);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
				try {if(pstmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
				try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			}
			return evaluationList;
		}
}
