package org.android.jplas.projectsuperman;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

public class facebook extends AppCompatActivity {
    WebView wv;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
        wv = (WebView)findViewById(R.id.wv);
        pb = (ProgressBar)findViewById(R.id.pb);
        wv.setWebViewClient(new WebViewClient());
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("https://facebook.com");
    }
    public class myWebclient extends WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            pb.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode==KeyEvent.KEYCODE_BACK) && wv.canGoBack()){
            wv.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
