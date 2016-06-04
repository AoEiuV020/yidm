package cc.aoeiuv020.yidm;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.util.*;
import android.widget.*;
import android.webkit.*;
import android.webkit.WebSettings.*;

import java.util.regex.Pattern;

public class Main extends Activity
{
	private WebView mWebView=null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
		mWebView=(WebView)findViewById(R.id.webview);
		WebSettings settings = mWebView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setUseWideViewPort(true); 
		settings.setLoadWithOverviewMode(true); 
		settings.setSupportZoom(true);
		settings.setBuiltInZoomControls(true);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int mDensity = metrics.densityDpi;
		if (mDensity == 120) {
			settings.setDefaultZoom(ZoomDensity.CLOSE);
		}else if (mDensity == 160) {
			settings.setDefaultZoom(ZoomDensity.MEDIUM);
		}else if (mDensity == 240) {
			settings.setDefaultZoom(ZoomDensity.FAR);
		}
		mWebView.setWebViewClient(new YidmWebViewClient());
		mWebView.loadUrl("http://so.yidm.com/");
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {  
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {  
			mWebView.goBack();
			return true;  
		}  
		return super.onKeyDown(keyCode,event);  
	}  
}
class YidmWebViewClient extends WebViewClient
{
	@Override 
	public boolean shouldOverrideUrlLoading(WebView view, String url) {  
		if(Pattern.matches(".*/info/.*\\.html$",url))
		{
			url=url.replaceFirst(".html$","/");
			url=url.replaceFirst("/info/","/html/");
		}
		view.loadUrl(url);  
		return true;  
	}  
}
