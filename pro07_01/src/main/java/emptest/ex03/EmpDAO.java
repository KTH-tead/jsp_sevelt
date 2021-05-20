package emptest.ex03;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmpDAO {
	private Connection conn;
	private PreparedStatement stmt;
	private DataSource dataFactory;
	
	public EmpDAO() {
		Context ctx;
		try {
			ctx = new InitialContext();
			Context env = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) env.lookup("jdbc/oraclehr");
		} catch (NamingException e) {
			e.printStackTrace();
		}
				
	}
	
	public List<EmpVO> listEmps(){

		List<EmpVO> list = new ArrayList<EmpVO>();
		
		
		try {
			conn = dataFactory.getConnection();
			
			String query = 
					  "SELECT e.*, d.department_name "
					+ "FROM hr.employees2 e INNER JOIN hr.departments2 d "
					+ 		"ON(e.department_id=d.department_id) "
					+ "ORDER BY e.employee_id";
			
			stmt = conn.prepareStatement(query);
			
			System.out.println("PrepareStatement :" + query);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int employee_id = rs.getInt("employee_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String email = rs.getString("email");
				String phone_number = rs.getString("email");
				Date hire_date = rs.getDate("hire_date");;
				String job_id = rs.getString("email");
				int salary = rs.getInt("salary");
				double commission_pct = rs.getDouble("commission_pct");
				int manager_id = rs.getInt("manager_id");
				String department_name = rs.getString("department_name");;
				
				EmpVO vo = new EmpVO();
				
				vo.setEmployee_id(employee_id);
				vo.setFirst_name(first_name);
				vo.setLast_name(last_name);
				vo.setEmail(email);
				vo.setPhone_number(phone_number);
				vo.setHire_date(hire_date);
				vo.setJob_id(job_id);
				vo.setSalary(salary);
				vo.setCommission_pct(commission_pct);
				vo.setManager_id(manager_id);
				vo.setDepartment_name(department_name);
				
				list.add(vo);

			}
			
			rs.close();
			stmt.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	

	
	public void delEmp(String employee_id) {
		try {
			conn = dataFactory.getConnection();
			String query2 = "DELETE FROM hr.employees2 WHERE employee_id=?";
			stmt = conn.prepareStatement(query2);
			stmt.setString(1, employee_id);
			stmt.executeUpdate();
			System.out.println("prepareStatement :"+query2);
			
			stmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
