package login.capmaignmonitor;

//import login.capmaignmonitor;
import org.testng.annotations.Test;

public class CampaignMonitorLogin {
	
	@Test(priority=1)
	public void testLogin() throws Exception {
		Login login = new Login();
		login.LoginToCM("test", "ui.test.login");
	}

}
