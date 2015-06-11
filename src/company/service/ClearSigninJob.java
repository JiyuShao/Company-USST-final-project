package company.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;  
import org.quartz.JobExecutionException;

import company.model.Employee;
import company.model.Signin;
import company.model.util.ManagedEmployeeBean;
import company.model.util.ManagedSigninBean;

public class ClearSigninJob implements Job{
	public ClearSigninJob() {  
        
    }  
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		System.out.println("Hello quzrtz  "+
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(new Date()));
		
		
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		DateFormat df = DateFormat.getDateTimeInstance();
		Signin signin=null;
		List<Employee> employees = ManagedEmployeeBean.getAllEmployees();
		for(Integer i=0;i<employees.size();i++){
			Integer id = employees.get(i).getEmployeeId();
			try{
				signin = ManagedSigninBean.getByIdDate(id, date,"YES");
				try{
					signin = ManagedSigninBean.getByIdDate(id, date,"NO");
				}catch(Exception e){
					Employee employee = ManagedEmployeeBean.getById(id);
					Signin signin1 = new Signin();
					signin1.setDate(date);
					signin1.setTime(df.format(new Date()));
					signin1.setStatus("NO");
					signin1.setEmployee(employee);
					ManagedSigninBean.createNewSignin(id, signin1);
					System.out.println("Employee "+signin1.getEmployee().getName()+" Sign Out Succeed!!");
				}
			}catch(Exception e){
			}
		}
		
	}
}
