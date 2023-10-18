package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import edu.pnu.domain.MemberVO;

public class MemberDao {
	
	Connection con;

			
	public MemberDao() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/misson2","sa","abcd");
		}
		catch (Exception e) {
			System.out.println("데이터베이스 접속 중 오류");
			System.out.println(e.getMessage());
		}
	}
	
	public int deleteMember(int id){
		
		String str = "delete from member where id = ? ";
		
		try {
			PreparedStatement psmt = con.prepareStatement(str);
			
			psmt.setInt(1, id);
			
			int rs = psmt.executeUpdate();			
			
			return rs;
		}
		catch (Exception e) {
			System.out.println("삭제하는데 실패 했습니다");
			System.out.println(e.getMessage());
		}
		
		return -1;
	}

	public int updateMember(MemberVO memberVO) {
		
		String str = "update member set pass = ? , name = ? where id = ?";
		
		try {
			PreparedStatement psmt = con.prepareStatement(str);
			
			psmt.setString(1, memberVO.getPass());
			psmt.setString(2, memberVO.getName());
			psmt.setInt(3, memberVO.getId());
			
			int rs = psmt.executeUpdate();			
			
			return rs;
		}
		catch (Exception e) {
			System.out.println("업데이트 오류 입니다");
			System.out.println(e.getMessage());
		}
		
		return -1;
		
	}
	
	
	public MemberVO getMember(int id) throws SQLException {
		String str = "select * from member where id = ? ";
		
		try {
			PreparedStatement psmt = con.prepareStatement(str);
			
			psmt.setInt(1, id);
			
			ResultSet rs = psmt.executeQuery();
			
			MemberVO m = new MemberVO();
			
			if(rs.next()) {
			    m.setId(rs.getInt(1));
			    m.setPass(rs.getString(2));
			    m.setName(rs.getString(3));
			    m.setRegidate(rs.getDate(4));
			}
		    
		    return m;
		}
		catch (Exception e) {
			System.out.println("아이디를 찾는 중 오류입니다");
			System.err.println(e.getMessage());
		}
	    
		return null;	
		
}
	
	
	public List<MemberVO> getMembers() throws SQLException{
		List<MemberVO> list = new ArrayList<>();
		
		String str = "select * from member";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(str);
		
		while(rs.next()) {
			MemberVO memberVO = new MemberVO();
			memberVO.setId(rs.getInt(1));
			memberVO.setPass(rs.getString(2));
			memberVO.setName(rs.getString(3));
			memberVO.setRegidate(rs.getDate(4));
			list.add(memberVO);
		}
		
		rs.close();
		st.close();
		con.close();
		
		if(list == null) return null;
		else return list;
	}
	
	
	public int addMember(MemberVO memberVO) throws SQLException {	
		int result = 0;
		String str = "insert into member ( pass , name ) values ( ? , ? ) ";
		
		try {
			PreparedStatement psmt = con.prepareStatement(str);
			psmt.setString(1, memberVO.getPass());
			psmt.setString(2, memberVO.getName());

		    result = psmt.executeUpdate();
		    
		    psmt.close();
		    System.out.println("아이디 추가에 성공했습니다");
		    return result; 
		}
		catch (Exception e) {
			System.out.println("아이디 추가에 실패했습니다");
			System.out.println(e.getMessage());
		}
		return -1; 
		
	}
	
//	getMembers();
//	getMember(int id);
//  addMember(MemberVO memberVO);
//	updateMember(MemberVO membervo);
//	deleteMember(int id);
	
	
}
