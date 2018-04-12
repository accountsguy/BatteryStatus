package accountsguy.net.batterystatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    int BatteryLevel = 0;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if(BatteryLevel == 0){
                BatteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                textView.setText(String.valueOf(BatteryLevel)+"%");
            }

            if(BatteryLevel != intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)){
                BatteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                textView.setText(String.valueOf(BatteryLevel)+"%");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this, "Wait Battery Level Message Loading", Toast
                .LENGTH_SHORT).show();

        textView = (TextView) findViewById(R.id.batterylevel);

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        this.registerReceiver(broadcastReceiver, ifilter);
    }
}
