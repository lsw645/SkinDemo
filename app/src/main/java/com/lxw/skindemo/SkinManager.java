package com.lxw.skindemo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * description... //TODO
 *
 * @author lsw
 * @version 1.0, 2017/4/13
 * @see //TODO
 * @since JDK 1.8
 */

public class SkinManager {
    private Context mContext;
    //外置Apk
    private Resources mSkinRescource;
    //外置apk的包名
    private String mSkinPackage;

    public void init(Context context) {
        this.mContext = context;
    }

    private static final SkinManager ourInstance = new SkinManager();

    public static SkinManager getInstance() {
        return ourInstance;
    }

    private SkinManager() {
    }

    public void loadSkin(String path) {
        AssetManager assetManager = null;
        PackageManager packageManager = mContext.getPackageManager();
        PackageInfo info = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        mSkinPackage = info.packageName;
        try {
            assetManager = AssetManager.class.newInstance();
            Method method = assetManager.getClass().getMethod("addAssetPath", String.class);
            method.invoke(assetManager, path);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        mSkinRescource = new Resources(assetManager, mContext.getResources().getDisplayMetrics(), mContext.getResources().getConfiguration());

    }

    public int getColor(int resId) {
        //代表本地没有文件
        if (mSkinRescource == null) {
            return resId;
        }
        String resName = mContext.getResources().getResourceName(resId);
        int realId = mContext.getResources().getIdentifier(resName, "color", mSkinPackage);
        return mSkinRescource.getColor(realId);
    }

    public Drawable getDrawable(int resId) {
        Drawable originDrawable = ContextCompat.getDrawable(mContext, resId);
        if (mSkinRescource == null) {
            return originDrawable;
        }
        String resName = mContext.getResources().getResourceName(resId);
        int realId = mContext.getResources().getIdentifier(resName, "drawable", mSkinPackage);
        originDrawable = mSkinRescource.getDrawable(realId);
        return originDrawable;
    }
}
