package com.example.biometriclogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFFont;
//import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.ss.formula.functions.T;
//import org.apache.poi.ss.usermodel.BorderStyle;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.FillPatternType;
//import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
//import java.util.Timer;
//import java.util.TimerTask;

public class Student_present extends AppCompatActivity {
    public static CountDownTimer timer;
    Boolean flag = false;
    public static TextView tv_list;
    TextView tv_date;
    CardView tv_header;
    ImageButton delete;
    ArrayList<ModelClass> arrayList;
    //     private File directory = getFilesDir(); //or getExternalFilesDir(null); for external storage
    private File filePath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Attendance.xls");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_present);
        getSupportActionBar().hide();


        tv_date = findViewById(R.id.tv_data);
        tv_list = findViewById(R.id.tv_list);
        tv_header = findViewById(R.id.cv_heade);
        LoadData();
        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        if(name!=null)
        saveData(name,email);
//        if (name != null && email != null) {
//            saveData(name, email);
////            Intent i = new Intent(this, ScanQRKotlin.class);
////            startActivity(i);
////            finish();
//        }

        delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getApplicationContext().getSharedPreferences("DATA", 0).edit().clear().commit();
            }
        });
//            Toast.makeText(this,String.valueOf(ssid),Toast.LENGTH_LONG).show();
        }





    private void saveData(String name, String email) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        arrayList.add(new ModelClass(name,email));
        String json = gson.toJson(arrayList);
        editor.putString("student_data", json);
        editor.apply();
        tv_list.setText("\n");
        LoadData();
    }

    private void LoadData() {

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("student_data", null);
        Type type = new TypeToken<ArrayList<ModelClass>>() {
        }.getType();
        arrayList = gson.fromJson(json, type);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        } else {
            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            tv_date.setText("Roll No    Name         Date : "+date+"\n");

            for (int i = 0; i < arrayList.size(); i++) {
                String currentString = arrayList.get(i).id;
                String[] separated = currentString.split("@");
                tv_list.append(separated[0]+ "      " + arrayList.get(i).name + "\n");
            }
        }
    }

    public void buttonCreateExcel(View view) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("student_data", null);
        Type type = new TypeToken<ArrayList<ModelClass>>() {
        }.getType();
        arrayList = gson.fromJson(json, type);


        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        HSSFSheet hssfSheet = hssfWorkbook.createSheet(date.toString());
        hssfSheet.setColumnWidth(0,25*256);
        HSSFRow hssfRow = hssfSheet.createRow(0);
        HSSFCell hssfCell = hssfRow.createCell(0);
        hssfCell.setCellValue("Date : "+date);



        for (int i = 0; i < arrayList.size(); i++) {
             hssfRow = hssfSheet.createRow(i+1);
             hssfCell = hssfRow.createCell(0);
            String currentString = arrayList.get(i).id;
            String[] separated = currentString.split("@");
            hssfCell.setCellValue(arrayList.get(i).name+"-"+separated[0]);
        }

        try {

            if (!filePath.exists()) {

                filePath.createNewFile();
            }
            Toast.makeText(this,filePath.getPath().toString(),Toast.LENGTH_LONG).show();
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            hssfWorkbook.write(fileOutputStream);
            if (fileOutputStream!=null){
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        } catch (Exception e) {
           e.printStackTrace();
           Toast.makeText(this,filePath.getPath(),Toast.LENGTH_LONG).show();

        }

    }
}
