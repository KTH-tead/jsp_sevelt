package emptest.ex02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SalSummaryDAO {
	private Connection conn;
	private PreparedStatement stmt;
	private DataSource dataFactory;
	
	public SalSummaryDAO() {
		Context ctx;
		try {
			ctx = new InitialContext();
			Context env = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) env.lookup("jdbc/oraclehr");
		} catch (NamingException e) {
			e.printStackTrace();
		}
				
	}
	
	public List<SalSummaryVO> deptSalSummary(){

		List<SalSummaryVO> salSummaryList = new ArrayList<SalSummaryVO>();
		
		
		try {
			conn = dataFactory.getConnection();
			
			String query = 
					  " SELECT d.department_name, SUM(e.salary)  salsum, "
					  +      "ROUND(AVG(e.salary), 0) salavg, COUNT(1) workers "
					+ " FROM hr.employees2 e INNER JOIN hr.departments2 d "
					+ 		" ON(e.department_id=d.department_id) "
					+ " GROUP BY d.department_name "
					+ " ORDER BY 1";
			
			stmt = conn.prepareStatement(query);

			
			System.out.println("PrepareStatement :" + query);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String department_name = rs.getString("department_name");;
				int salsum = rs.getInt("salsum");
				int salavg = rs.getInt("salavg");
				int workers = rs.getInt("workers");
			
				SalSummaryVO vo = new SalSummaryVO();
				
				vo.setDepartment_name(department_name);
				vo.setSalsum(salsum);
				vo.setSalavg(salavg);
				vo.setWorkers(workers);

				salSummaryList.add(vo);
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return salSummaryList;
	}
}
