package com.campaignmonitor.login.setup;

//import login.capmaignmonitor;
import org.testng.annotations.Test;

import com.campaignmonitor.login.setup.Login;

public class CampaignMonitorLogin {
	
	@Test(priority=1,groups={"orders","sanity","regression"})
	public void testLogin() throws Exception {
		Login login = new Login();
		login.LoginToCM("test", "ui.test.login");
	}

}
