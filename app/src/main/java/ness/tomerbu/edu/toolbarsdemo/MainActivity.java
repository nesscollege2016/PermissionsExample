package ness.tomerbu.edu.toolbarsdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String[] permissions,
             int[] grantResults) {
        if (requestCode == REQUEST_CALL){
            if (permissions[0] == Manifest.permission.CALL_PHONE){
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    call(null);
                }
            }
        }
    }

    public void call(View v) {

        Uri phoneUri = Uri.parse("tel://0507123012");
        Intent callIntent = new Intent(Intent.ACTION_CALL, phoneUri);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            //    ActivityCompat#requestPermissions

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            // here to request the missing permissions
            //
            //
            // and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(callIntent);
    }
}
