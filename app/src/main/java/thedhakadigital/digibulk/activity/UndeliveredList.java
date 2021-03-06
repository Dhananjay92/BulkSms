package thedhakadigital.digibulk.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import thedhakadigital.digibulk.R;
import thedhakadigital.digibulk.adapter.DeliverAdapter;
import thedhakadigital.digibulk.database.DbManager;
import thedhakadigital.digibulk.model.InfoModel;
import thedhakadigital.digibulk.sharedpref.SharedPref;

public class UndeliveredList extends AppCompatActivity {
    RecyclerView rvUndeliverd;
    ArrayList<InfoModel> deleverdInfo = new ArrayList<>();
    DbManager database;
    DeliverAdapter deliverAdapter;
    SharedPref sharedPref;
    Button btUndelivredNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_undelivered_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
        setAdapter();

    }

    private void setAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setAutoMeasureEnabled(true);
        rvUndeliverd.setLayoutManager(linearLayoutManager);
        rvUndeliverd.setAdapter(deliverAdapter);
    }

    private void initView() {
        sharedPref = new SharedPref(getApplicationContext());
        database = new DbManager(getApplicationContext());
        deliverAdapter = new DeliverAdapter(database.getRecentUndeliveredPhn());
        rvUndeliverd = (RecyclerView) findViewById(R.id.rvUnliveredlist);
        btUndelivredNumbers = (Button) findViewById(R.id.btCopyNumbers);

        initListener();
    }

    private void initListener() {
        btUndelivredNumbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<InfoModel> undeliveredNumbers = database.getRecentUndeliveredPhn();
                String numbers = "";

                for (int i = 0; i < undeliveredNumbers.size(); i++){
                   numbers  = numbers + undeliveredNumbers.get(i).getPhoneNumber() + ",";
                }

                copyText(numbers);


            }
        });
    }

    private void copyText(String text){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Numbers Copied", Toast.LENGTH_SHORT).show();
    }

}
