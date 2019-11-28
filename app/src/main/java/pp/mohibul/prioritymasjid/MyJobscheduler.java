package pp.mohibul.prioritymasjid;

import android.annotation.SuppressLint;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.location.Location;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;


import static pp.mohibul.prioritymasjid.MainActivity.context;


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobscheduler extends JobService {

    private JobExecuter jobExecuter;

    @SuppressLint("StaticFieldLeak")
    @Override
    public boolean onStartJob(final JobParameters jobParameters) {

        jobExecuter = new JobExecuter() {
            @Override
            protected void onPostExecute(String s) {
                Location();
                Toast.makeText(MyJobscheduler.this, s, Toast.LENGTH_SHORT).show();
                jobFinished(jobParameters, false);
            }
        };
        jobExecuter.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {

        jobExecuter.cancel(true);
        return false;
    }


    private String Location() {

        final String[] getLatitude = {""};
        final String[] getLongitude = {""};
        GetLocation.LocationResult locationResult = new GetLocation.LocationResult() {
            @Override
            public void gotLocation(Location location) {
                //Got the location!
                getLatitude[0] = String.valueOf(location.getLatitude());
                getLongitude[0] = String.valueOf(location.getLongitude());
                Log.d("Latitude", getLatitude[0]);
                Log.d("longitude", getLongitude[0]);
            }
        };
        GetLocation myLocation = new GetLocation();
        myLocation.getLocation(context, locationResult);
        return "Latitude=" + getLatitude[0] + "\n" + "longitude=" + getLongitude[0];
    }
}
