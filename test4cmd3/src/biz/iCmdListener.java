package biz;

import android.os.Bundle;

public interface iCmdListener {
	public void onPreExecute();

//	public void onProgressUpdate(int progress);

	public void onPostExecute(Bundle resultBundle);

}
