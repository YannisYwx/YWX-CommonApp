package com.ywx.third;

import android.annotation.SuppressLint;
import android.app.Application;
import android.graphics.Color;
import android.view.Gravity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.Utils;
import com.hjq.toast.ToastUtils;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxAdapterView;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.thomas.okaspectj.IPointHandler;
import com.thomas.okaspectj.OkAspectjHelper;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cache.converter.SerializableDiskConverter;
import com.zhouyou.http.cache.model.CacheMode;
import com.zhouyou.http.model.HttpHeaders;
import com.zhouyou.http.model.HttpParams;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cat.ereza.customactivityoncrash.activity.DefaultErrorActivity;
import cat.ereza.customactivityoncrash.config.CaocConfig;

/**
 * @author : WX.Y
 * date : 2021/3/9 17:02
 * description :
 */
public class ThirdHelper {


    private static Application mApplication;
    private static volatile ThirdHelper instance;
    public static RefWatcher refWatcher;

    public static String BASE_URL = "https://www.vphband.com:505/api/";


    private static final class Holder {
        private static ThirdHelper INSTANCE = new ThirdHelper();
    }

    private ThirdHelper() {
    }

    public static ThirdHelper getInstance() {
        return Holder.INSTANCE;
    }

    public static ThirdHelper getInstance(Application application) {
        if (instance == null) {
            synchronized (ThirdHelper.class) {
                if (instance == null) {
                    mApplication = application;
                    instance = new ThirdHelper();
                }
            }
        }
        return instance;
    }

    public ThirdHelper initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(mApplication)) {
            return this;
        }
        refWatcher = LeakCanary.install(mApplication);
        return this;
    }

    public ThirdHelper initEasyHttp(){
        String packageName = getPackageName();
        EasyHttp.init(mApplication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("packagename",packageName);
        HttpParams params = new HttpParams();
        params.put("appId", "AppConstant.APPID");
        EasyHttp.getInstance()
                .setBaseUrl(BASE_URL)
                .debug("EasyHttp-VeepooHealth",true)

                //如果使用默认的60秒,以下三行也不需要设置
                .setReadTimeOut(60 * 1000)
                .setWriteTimeOut(60 * 100)
                .setConnectTimeout(60 * 100)

                //可以全局统一设置超时重连次数,默认为3次,那么最差的情况会请求4次(一次原始请求,三次重连请求),
                //不需要可以设置为0
                .setRetryCount(3)//网络不好自动重试3次
                //可以全局统一设置超时重试间隔时间,默认为500ms,不需要可以设置为0
                .setRetryDelay(500)//每次延时500ms重试
                //可以全局统一设置超时重试间隔叠加时间,默认为0ms不叠加
                .setRetryIncreaseDelay(500)//每次延时叠加500ms

                //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体请看CacheMode
                .setCacheMode(CacheMode.NO_CACHE)
                //可以全局统一设置缓存时间,默认永不过期
                .setCacheTime(-1)//-1表示永久缓存,单位:秒 ，Okhttp和自定义RxCache缓存都起作用
                //全局设置自定义缓存保存转换器，主要针对自定义RxCache缓存
                .setCacheDiskConverter(new SerializableDiskConverter())//默认缓存使用序列化转化
                //全局设置自定义缓存大小，默认50M
                .setCacheMaxSize(100 * 1024 * 1024)//设置缓存大小为100M
                //设置缓存版本，如果缓存有变化，修改版本后，缓存就不会被加载。特别是用于版本重大升级时缓存不能使用的情况
                .setCacheVersion(1)//缓存版本为1
                //.setHttpCache(new Cache())//设置Okhttp缓存，在缓存模式为DEFAULT才起作用

                //可以设置https的证书,以下几种方案根据需要自己设置
                .setCertificates()                                  //方法一：信任所有证书,不安全有风险
                //.setCertificates(new SafeTrustManager())            //方法二：自定义信任规则，校验服务端证书
                //配置https的域名匹配规则，不需要就不要加入，使用不当会导致https握手失败
                //.setHostnameVerifier(new SafeHostnameVerifier())
                //.addConverterFactory(GsonConverterFactory.create(gson))//本框架没有采用Retrofit的Gson转化，所以不用配置
                .addCommonHeaders(httpHeaders)//设置全局公共头
                .addCommonParams(params)//设置全局公共参数
                //.addNetworkInterceptor(new NoCacheInterceptor())//设置网络拦截器
                //.setCallFactory()//局设置Retrofit对象Factory
                //.setCookieStore()//设置cookie
                //.setOkproxy()//设置全局代理
                //.setOkconnectionPool()//设置请求连接池
                //.setCallbackExecutor()//全局设置Retrofit callbackExecutor
                //可以添加全局拦截器，不需要就不要加入，错误写法直接导致任何回调不执行
                //.addInterceptor(new GzipRequestInterceptor())//开启post数据进行gzip后发送给服务器
                //.addInterceptor(new CustomSignInterceptor())
        ;//添加参数签名拦截器


        return this;
    }

    private String getPackageName(){
        return mApplication.getPackageName() + "/" +  AppUtils.getAppVersionName();
    }

    public ThirdHelper initUM() {
//        UMConfigure.setLogEnabled(true);
//        UMConfigure.init(mApplication, UMConfigure.DEVICE_TYPE_PHONE, "");
//        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
//        PlatformConfig.setSinaWeibo(Constants.SINA_ID, Constants.SINA_KEY, "http://sns.whalecloud.com");
//        PlatformConfig.setQQZone(Constants.QQ_ID, Constants.QQ_KEY);
        return this;
    }

    public ThirdHelper initBugly(boolean enableHotfix) {
//        Beta.largeIconId = R.drawable.third_launcher_ting;
//        Beta.smallIconId = R.drawable.third_launcher_ting;
//        Beta.upgradeDialogLayoutId = R.layout.third_dialog_update;
        Beta.enableHotfix = enableHotfix;
        Beta.canNotifyUserRestart = true;
        //生产环境
        //Bugly.init(mApplication, Constants.Bugly.SPEECH_ID,false);
        //开发环境
        Bugly.init(mApplication, Constants.BUGLY_ID, true);
        return this;
    }

    public ThirdHelper initRouter() {
        if (BuildConfig.DEBUG) {
            // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(mApplication); // 尽可能早，推荐在Application中初始化
        return this;
    }

    public ThirdHelper initUtils() {
        Utils.init(mApplication);
        com.blankj.utilcode.util.ToastUtils.setMsgColor(Color.WHITE);
        com.blankj.utilcode.util.ToastUtils.setBgColor(Color.GRAY);

        ToastUtils.init(mApplication);
        ToastUtils.setView(R.layout.third_layout_toast);
        ToastUtils.setGravity(Gravity.CENTER, 0, 0);
        KeyboardUtils.clickBlankArea2HideSoftInput();
        return this;
    }

    @SuppressLint("RestrictedApi")
    public ThirdHelper initCrashView() {
        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT)
                .enabled(true)//这阻止了对崩溃的拦截,false表示阻止。用它来禁用customactivityoncrash框架
                .minTimeBetweenCrashesMs(2000)      //定义应用程序崩溃之间的最短时间，以确定我们不在崩溃循环中。比如：在规定的时间内再次崩溃，框架将不处理，让系统处理！
                .errorActivity(DefaultErrorActivity.class) //程序崩溃后显示的页面
                .apply();
        //如果没有任何配置，程序崩溃显示的是默认的设置
        CustomActivityOnCrash.install(mApplication);
        return this;
    }

    public ThirdHelper initAspectj(IPointHandler handler) {
        OkAspectjHelper.init(handler);
        return this;
    }

}

