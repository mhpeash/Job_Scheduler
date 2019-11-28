package pp.mohibul.prioritymasjid;

import android.os.AsyncTask;

public class JobExecuter extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... voids) {
        return "service runing";
    }

}
