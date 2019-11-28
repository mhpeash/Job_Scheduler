package pp.mohibul.prioritymasjid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static int JOB_ID = 101;
    private JobScheduler jobScheduler;
    private JobInfo jobInfo;
    public static Context context;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        ComponentName componentName = new ComponentName(this, MyJobscheduler.class);
        JobInfo.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            builder = new JobInfo.Builder(JOB_ID, componentName);
            builder.setPeriodic(5000);
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
            builder.setPersisted(true);

            jobInfo = builder.build();
            jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        }

        SchedeleJob();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void SchedeleJob() {
        jobScheduler.schedule(jobInfo);
        Toast.makeText(this, "Job scheduled", Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void ClearschedeleJob(View view) {
        jobScheduler.cancel(JOB_ID);
        Toast.makeText(this, "Job schedule canceled", Toast.LENGTH_SHORT).show();

    }

}
