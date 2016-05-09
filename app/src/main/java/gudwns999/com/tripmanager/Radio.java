package gudwns999.com.tripmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016-05-09.
 */
public class Radio extends Activity {
    //즐겨찾기 php
    HttpPost httppost;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;

    RadioButton radio_01,radio_02,radio_03,radio_04,radio_05,radio_06;
    Button radio_Btn,reset_Btn;

    int a=0,b=0,c=0,d=0,e=0,f=0;
    String location;    //location 정보
    //값 받아오기
   String user_ID;
    @Override
    protected void onCreate(Bundle savedInstancedState) {
        super.onCreate(savedInstancedState);
        setContentView(R.layout.radio_layout);
        //객체 참조
        radio_Btn = (Button)findViewById(R.id.radio_Btn);
        reset_Btn = (Button)findViewById(R.id.reset_Btn);
        radio_01 = (RadioButton)findViewById(R.id.optioin01);
        radio_02 = (RadioButton)findViewById(R.id.optioin02);
        radio_03 = (RadioButton)findViewById(R.id.optioin03);
        radio_04 = (RadioButton)findViewById(R.id.optioin04);
        radio_05 = (RadioButton)findViewById(R.id.optioin05);
        radio_06 = (RadioButton)findViewById(R.id.optioin06);
        Intent getIntent = getIntent();
        user_ID = getIntent.getExtras().getString("USERID");
        //리셋
        reset_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio_01.setChecked(false);
                radio_02.setChecked(false);
                radio_03.setChecked(false);
                radio_04.setChecked(false);
                radio_05.setChecked(false);
                radio_06.setChecked(false);
                a=0;b=0;c=0;d=0;e=0;f=0;
            }
        });
        //서버로 전송
        radio_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location = ""+a+b+c+d+e+f;
                Toast.makeText(Radio.this,location,Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send();
                    }
                }).start();
            }
        });
        //라디오버튼 눌렀을 시 이벤트
        radio_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=1;
            }
        });
        radio_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b = 1;
            }
        });
        radio_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = 1;
            }
        });
        radio_04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 1;
            }
        });
        radio_05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e = 1;
            }
        });
        radio_06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f = 1;
            }
        });
    }
    void send(){
        try{
            httpclient=new DefaultHttpClient();
            httppost= new HttpPost("http://52.9.33.19/PHP/updateLocation.php"); // make sure the url is correct. Test
            //add your data
            nameValuePairs = new ArrayList<NameValuePair>(2);
            // Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar,
            nameValuePairs.add(new BasicNameValuePair("user_choice",location.trim()));  // $Edittext_value = $_POST['Edittext_value'];
            nameValuePairs.add(new BasicNameValuePair("user_id",user_ID.trim()));  // $Edittext_value = $_POST['Edittext_value'];
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            //Execute HTTP Post Request
            response=httpclient.execute(httppost);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();

            runOnUiThread(new Runnable() {
                public void run() {
                    Intent intent = new Intent(Radio.this, Main.class);
                    intent.putExtra("USERID",user_ID);
                    intent.putExtra("LOCALNUM",location);
                    startActivity(intent);
                    finish();
                }
            });
        }catch(Exception e){
            System.out.println("Exception : " + e.getMessage());
        }
    }
}
