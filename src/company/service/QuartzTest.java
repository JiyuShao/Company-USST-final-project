package company.service;


import org.apache.log4j.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest {
	//日志对象 
	private static Logger log = Logger.getLogger(QuartzTest.class);
	public QuartzTest(){}
	
	public static void main(String args[]) throws SchedulerException{
        JobDetail jobDetail= JobBuilder.newJob(ClearSigninJob.class)
                .withIdentity("testJob_1","group_1")
                .build();
 
        Trigger trigger= TriggerBuilder
                .newTrigger()
                .withIdentity("trigger_1","group_1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(10).repeatForever()//时间间隔
                        )
                .build();
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
 
        sched.scheduleJob(jobDetail,trigger);
 
        sched.start();
 
    }

}
