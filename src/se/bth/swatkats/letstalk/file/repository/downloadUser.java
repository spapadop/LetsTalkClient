package se.bth.swatkats.letstalk.file.repository;
/**
 * The user class to observe file download progress 
 * 
 * @author Jyoti
 *
 */ 
import java.util.Observer;
import java.util.Observable;
import se.bth.swatkats.letstalk.connection.GuiHandler;
public class downloadUser implements Observer {
	private int downloaded;
	private filedownload file;
	public downloadUser(filedownload file){
	      this.file = file;
	   }
@Override
	   public void update(Observable obs, Object obj){
		if (obs == file)
		{
		   this.downloaded=(int) file.getValue();
                   GuiHandler.getInstance().getGui().updateDownloadProgress(downloaded);
	   }
}
}
