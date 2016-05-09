package gudwns999.com.tripmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * Created by user on 2016-05-09.
 */
public class MoreInfo extends Activity {
    private WebView webView;
    Button back_Btn;
    String local_url[]={"https://www.google.co.kr/maps/place/Kookmin+University/@37.6108691,126.9951006,17z",
            "https://www.google.co.kr/maps/place/Gwanghwamun+Gate/@37.5760285,126.9681619,15z",
            "https://www.google.co.kr/maps/place/하늘공원/@37.5680649,126.8827343,17z",
            "https://www.google.co.kr/maps/place/성수대교/@37.5370514,127.0327464,17z",
            "https://www.google.co.kr/maps/place/Changdeokgung/@37.5794351,126.9888539,17z,",
            "https://www.google.co.kr/maps/place/석촌호수/@37.507977,127.0919266,15z"};
    @Override
    public void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.moreinfo_layout);
        back_Btn = (Button)findViewById(R.id.back_Btn);
        back_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent getIntent = getIntent();
        final String num = getIntent.getExtras().getString("LOCAL_URL");
        int i = Integer.parseInt(num);
        webView = (WebView)findViewById(R.id.webView_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(local_url[i-1]);
        webView.setWebViewClient(new WishWebViewClient());
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private  class WishWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }
}
