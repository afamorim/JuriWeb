package br.com.vortice.ijuri.webjava.processo.action;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.JobDetail;
import org.quartz.jobs.ee.ejb.EJBInvokerJob;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.servlet.FrameworkServlet;

public class AndamentoScheduleAC extends FrameworkServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		JobDetail jd = new JobDetail("Test Quartz","My Test Job",EJBInvokerJob.class);
		
		CronTrigger cronTrigger = new CronTrigger("Test Quartz");
	}

	@Override
	protected void doService(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
	}

}
