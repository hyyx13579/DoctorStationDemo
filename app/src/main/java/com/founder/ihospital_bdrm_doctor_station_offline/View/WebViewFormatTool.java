package com.founder.ihospital_bdrm_doctor_station_offline.View;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * webview 加载器
 * Created by Tony on 16/6/30.
 */
public class WebViewFormatTool {
    private WebView webView;
    public WebViewFormatTool(WebView webview){
        this.webView = webview;
    }

    /**
     * 通过url加载网页
     * @param url
     */
    public static void setWebView(WebView webview,String url){

        WebSettings s = webview.getSettings();

        //重新设置websettings，使网页里的内容可点击
        s.setBuiltInZoomControls(true);
        s.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        s.setUseWideViewPort(true);
        s.setLoadWithOverviewMode(true);
        s.setSavePassword(true);
        s.setSaveFormData(true);
        s.setJavaScriptEnabled(true);
        // enable navigator.geolocation
        s.setGeolocationEnabled(true);
        s.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");
        // enable Web Storage: localStorage, sessionStorage
        s.setDomStorageEnabled(true);
        webview.requestFocus();
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        //重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
        webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webview.loadUrl(url);

    }

    /**
     * 通过baseurl 和 data 加载网页，并格式化，适应屏幕
     * @param data
     * @param baseurl
     */
    public static void setWebViewFromData(WebView webView,String data,String baseurl){
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
//        webView.getSettings().setBlockNetworkImage(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); // 设置缓存模式
        // 开启 DOM storage API 功能
        webView.getSettings().setDomStorageEnabled(true);
        // 开启 database storage API 功能
        webView.getSettings().setDatabaseEnabled(true);
//        webView.loadUrl(url);

        webView.setWebViewClient(
                new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        view.loadUrl("javascript:function myFunction(){" +
                                "var width=document.body.clientWidth - 20;" +
                                "var img= document.getElementsByTagName('img');" +
                                "for(var i = 0; i < img.length;i++){" +
                                "img[i].style.width = width+'px';" +
                                "img[i].style.height = 'auto';" +
                                "}" +
                                "}");
                        view.loadUrl("javascript:myFunction()");
                        super.onPageFinished(view, url);
                    }

                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        super.onPageStarted(view, url, favicon);
                    }

                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        return super.shouldOverrideUrlLoading(view, url);
                    }
                });

        webView.loadDataWithBaseURL(baseurl, data, "text/html", "UTF - 8", null);
    }





}
