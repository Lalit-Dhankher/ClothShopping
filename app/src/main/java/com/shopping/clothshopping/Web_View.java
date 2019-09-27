package com.shopping.clothshopping;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

public class Web_View extends Activity {

    private WebView webView;
    boolean isBackPressed = false;
    LinearLayout llSplash;
    ProgressBar pbSplesh;
    ImageView ivSplesh;
    String url,themeColor,icon;
    private static final int RP_ACCESS_LOCATION = 1001;
    private String mGeolocationOrigin;
    private GeolocationPermissions.Callback mGeolocationCallback;

    public class GeoWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // When user clicks a hyperlink, load in the existing WebView
            view.loadUrl(url);
            return false;
        }
    }

    public class GeoWebChromeClient extends WebChromeClient {
        @Override
        public void onGeolocationPermissionsShowPrompt(String origin,
                                                       GeolocationPermissions.Callback callback) {
            final String permission = Manifest.permission.ACCESS_FINE_LOCATION;
            // Always grant permission since the app itself requires location
            // permission and the user has therefore already granted it

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                    ContextCompat.checkSelfPermission(Web_View.this, permission) == PackageManager.PERMISSION_GRANTED) {
                // that is you already implement, but it works only
                // we're on SDK < 23 OR user has ALREADY granted permission
                callback.invoke(origin, true, false);
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(Web_View.this, permission)) {
                    // user has denied this permission before and selected [/] DON'T ASK ME AGAIN
                    // TODO Best Practice: show an AlertDialog explaining why the user could allow this permission, then ask again
                } else {
                    // store
                    mGeolocationOrigin = origin;
                    mGeolocationCallback = callback;
                    // ask the user for permissions
                    ActivityCompat.requestPermissions(Web_View.this, new String[] {permission}, RP_ACCESS_LOCATION);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case RP_ACCESS_LOCATION:
                boolean allow = false;
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // user has allowed these permissions
                    allow = true;
                }
                if (mGeolocationCallback != null) {
                    // use stored callback and origin for allowing Geolocation permission for WebView
                    mGeolocationCallback.invoke(mGeolocationOrigin, allow, false);
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web__view);


        webView =findViewById(R.id.webview);
        llSplash=findViewById(R.id.llSplash);
        ivSplesh=findViewById(R.id.ivSplash);
        pbSplesh=findViewById(R.id.pbSplash);



        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            url = mBundle.getString("url");
            themeColor=mBundle.getString("themeColor");
            icon=mBundle.getString("icon");
        }


        //chage status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(themeColor));
        }



        // Enable Javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webView.clearView();
        webView.setInitialScale(1);
        webView.setHorizontalScrollBarEnabled(false);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webView.setVerticalScrollBarEnabled(false);
        webSettings.setDisplayZoomControls(false);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        webSettings.setDisplayZoomControls(false);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);


        webView.setWebViewClient(new GeoWebViewClient());

        // Below required for geolocation
        webSettings.setJavaScriptEnabled(true);
        webSettings.setGeolocationEnabled(true);
//        webView.setWebChromeClient(new GeoWebChromeClient());
        webView.loadUrl(url);



        webView.setWebChromeClient(new GeoWebChromeClient(){
            @Override
            public void onProgressChanged(WebView view,int progress) {
                pbSplesh.setProgress(progress);
                if (progress == 100) {
                    pbSplesh.setVisibility(View.GONE);
                    llSplash.setVisibility(View.GONE);
                    webView.setVisibility(View.VISIBLE);

                } else {

                    pbSplesh.setVisibility(View.VISIBLE);
                    llSplash.setVisibility(View.VISIBLE);
                    llSplash.setBackgroundColor(Color.parseColor(themeColor));
                    Glide.with(getApplicationContext()).load(icon).centerCrop().into(ivSplesh);
                    webView.setVisibility(View.GONE);

                }

            }
        });


    }

    //   changes for android 4

    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return false;
        }
    }


    @Override
    public void onBackPressed(){

        if(webView.canGoBack()){
            webView.goBack();
        } else {
            super.onBackPressed();

            }

        }
    }

