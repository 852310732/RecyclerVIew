package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Person;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.Utils.MyWifiManager;
import com.example.myapplication.Utils.testAdapter;
import com.example.myapplication.bean.WiFiBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<WiFiBean> wifilist;
    private List<ScanResult> names;
    private testAdapter testAdapterr;
    private RecyclerView recyclerView;
    WifiManager wifiManager;



    List<String> mPermissionList = new ArrayList<>();
    private final int mRequestCode = 1;//权限请求码
    String[] permissions = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_WIFI_STATE,
        Manifest.permission.CHANGE_WIFI_MULTICAST_STATE,
        Manifest.permission.CHANGE_WIFI_STATE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_Permission();
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        recyclerView = findViewById(R.id.hahaha);

        inta();

        Button ad = findViewById(R.id.mo);
        Button dd = findViewById(R.id.open);
        dd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wifiManager.setWifiEnabled(true);

            }
        });
        ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyWifiManager.startScanWifi(wifiManager);
                names = MyWifiManager.getWifiList(wifiManager);
                for (int i = 0; names.size() > i; i++) {
                    WiFiBean wiFiBean = new WiFiBean();
                    wiFiBean.setName(names.get(i).SSID);
                    wiFiBean.setRiss(names.get(i).level);
                    wiFiBean.setLockkk(MyWifiManager.getEncrypt(wifiManager, names.get(i)));
                    wifilist.add(wiFiBean);
                    testAdapterr.notifyDataSetChanged();
                }
            }
        });
    }
    private void inta(){
        wifilist = new ArrayList<>();
        names = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(
                MainActivity.this, DividerItemDecoration.VERTICAL));  //分割线
        testAdapterr = new testAdapter(wifilist);
        recyclerView.setAdapter(testAdapterr);

        /***************************************/
        testAdapterr.setmOnItemClickListerer(new testAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "位置" + wifilist.get(position), Toast.LENGTH_LONG).show();
            }
        });
        testAdapterr.setmQQClickListerer(new testAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "QQ" + wifilist.get(position), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void init_Permission() {
        mPermissionList.clear();//清空已经允许的没有通过的权限
        //逐个判断是否还有未通过的权限
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) !=
                    PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);//添加还未授予的权限到mPermissionList中
            }
        }
        //申请权限
        if (mPermissionList.size() > 0) {//有权限没有通过，需要申请

            ActivityCompat.requestPermissions(this, permissions, mRequestCode);
        } else {
        }
    }
}



