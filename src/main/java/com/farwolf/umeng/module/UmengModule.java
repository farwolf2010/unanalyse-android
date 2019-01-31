package com.farwolf.umeng.module;

import android.content.Context;
import android.text.TextUtils;

import com.farwolf.weex.annotation.WeexModule;
import com.farwolf.weex.base.WXModuleBase;
import com.taobao.weex.annotation.JSMethod;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

/**
 * Umeng 埋点 Module
 * Created by liuyuanxiao on 2018/7/18.
 */
@WeexModule(name = "umanalyse")
public class UmengModule extends WXModuleBase {
    @JSMethod
    public void initUM(String umengAppKey) {
        Context context = mWXSDKInstance.getContext();
        if (!TextUtils.isEmpty(umengAppKey)) {
            UMConfigure.init(context, umengAppKey, "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
            MobclickAgent.setDebugMode(true);
            MobclickAgent.openActivityDurationTrack(false);
//            MobclickAgent.setCatchUncaughtExceptions(!Config.debug(getContext()));
            MobclickAgent.setScenarioType(context, MobclickAgent.EScenarioType.E_UM_NORMAL);

        }
    }

    @JSMethod(uiThread = true)
    public void beginPage(String name) {
        UmengUtils.umengOnActivityResume(mWXSDKInstance.getContext(), name);
    }

    @JSMethod(uiThread = true)
    public void endPage(String name) {
        UmengUtils.umengOnActivityPause(mWXSDKInstance.getContext(), name);
    }

    @JSMethod(uiThread = true)
    public void event(String name) {
        UmengUtils.umengClick(mWXSDKInstance.getContext(), name);
    }

    @JSMethod(uiThread = true)
    public void beginEvent(String name) {
        UmengUtils.umengOnPageResume(mWXSDKInstance.getContext(), name);
    }

    @JSMethod(uiThread = true)
    public void endEvent(String name) {
        UmengUtils.umengOnPagePause(mWXSDKInstance.getContext(), name);
    }


}
