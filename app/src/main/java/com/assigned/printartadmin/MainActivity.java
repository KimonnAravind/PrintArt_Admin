package com.assigned.printartadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private DatabaseReference TestRef;
    private String savecurrentdate, savecurrenttime,productrandomkey;
    private Button Test,Test1;
    private String series="00";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TestRef= FirebaseDatabase.getInstance().getReference().child("ProductCategory");
        Test = (Button)findViewById(R.id.button);
        Test1 = (Button)findViewById(R.id.button1);

        Test.setOnClickListener(this);
        Test1.setOnClickListener(this);

    }

    private void clickedEvent(String series, String productrandomkey)
    {
        HashMap<String ,Object>upload = new HashMap<>();
        upload.put("ProductName","two");
        upload.put("ProductPrice","one");
        upload.put("ProductDescription","one");
        TestRef.child(series).child("About").child(productrandomkey).updateChildren(upload)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this, "Yeahh", Toast.LENGTH_SHORT).show();
                    }
                    }});
    }
    private void randomnumber()
    {
            Calendar date = Calendar.getInstance();
            SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
            savecurrentdate = currentDate.format(date.getTime());

            SimpleDateFormat currentTime = new SimpleDateFormat(" HH:mm:ss a");
            savecurrenttime = currentTime.format(date.getTime());

            productrandomkey=savecurrentdate + savecurrenttime;

    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.button :
            {
                series="01";
                randomnumber();
                clickedEvent(series,productrandomkey);
                break;
            }
            case R.id.button1:
            {
                Toast.makeText(this, "Toast", Toast.LENGTH_SHORT).show();
                break;
            }

        }

    }
}
