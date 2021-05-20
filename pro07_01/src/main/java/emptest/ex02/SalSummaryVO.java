package emptest.ex02;

public class SalSummaryVO {
	private String department_name;
	private int salsum;
	private int salavg;
	private int workers;
	
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public int getSalsum() {
		return salsum;
	}
	public void setSalsum(int salsum) {
		this.salsum = salsum;
	}
	public double getSalavg() {
		return salavg;
	}
	public void setSalavg(int salavg) {
		this.salavg = salavg;
	}
	
	public int getWorkers() {
		return workers;
	}
	public void setWorkers(int workers) {
		this.workers = workers;
	}

}
