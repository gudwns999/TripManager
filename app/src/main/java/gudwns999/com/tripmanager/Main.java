package gudwns999.com.tripmanager;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by user on 2016-05-09.
 */
public class Main extends TabActivity implements TabHost.OnTabChangeListener {
    TextView IDMain_Txt,LocalMain_Txt;
    Button updateMain_Btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        //login에서부터 가져온 변수
        Intent getIntent = getIntent();
        final String user_ID = getIntent.getExtras().getString("USERID");
        final String local_Num = getIntent.getExtras().getString("LOCALNUM");
        //객체참조
        IDMain_Txt = (TextView)findViewById(R.id.IDMain_Txt);
        LocalMain_Txt = (TextView)findViewById(R.id.LocalMain_Txt);
        updateMain_Btn = (Button)findViewById(R.id.updateMain_Btn);

        IDMain_Txt.setText(user_ID);
        LocalMain_Txt.setText(local_Num);
        updateMain_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //수정 화면으로 넘어감.
                Intent intent = new Intent(Main.this, Radio.class);
                intent.putExtra("USERID",user_ID);
                startActivity(intent);
                finish();
            }
        });
        Intent intent;
        //탭뷰 선언.
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        TabHost.TabSpec spec1;
        //탭뷰 선택시 해당 class로 이동.
        intent = new Intent().setClass(Main.this, List.class);
        intent.putExtra("USERID",user_ID);
        intent.putExtra("LOCALNUM",local_Num);

        spec1 = tabHost.newTabSpec("Location").setIndicator("선호 지역").setContent(intent);
        //탭을 TabHost에 추가한다.
        tabHost.addTab(spec1);
    }
    //탭 뷰 눌렀을 시
    @Override
    public void onTabChanged(String tabId){
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}