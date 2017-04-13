package com.lxw.skindemo;

import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description... //TODO
 *
 * @author lsw
 * @version 1.0, 2017/4/13
 * @see //TODO
 * @since JDK 1.8
 */

public class SkinFactory implements LayoutInflaterFactory {
    private static final String[] prefixList = new String[]{
            "android.widget.",
            "android.view.",
            "android.webkit."
    };

    private Map<View, SkinItem> map = new HashMap<>();

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        Log.d("TAG", "onCreateView() returned: " + name);
        View view = null;

        if (name.indexOf(".") == -1) {
            //系统控件
            for (String pre : prefixList) {
                view = createView(pre + name, context, attrs);
                if (view != null) {
                    Log.d("TAG", "onCreateView() returned: " + pre + name);
                    break;
                }
            }
        } else {
            view = createView(name, context, attrs);
        }
        if(view !=null){
            parseSkinView(view,context,attrs);
        }

        return view;
    }

    private void parseSkinView(View view, Context context, AttributeSet attrs) {
        List<SkinInterface> skinInterfaceList=new ArrayList<>();
        for(int i=0;i<attrs.getAttributeCount();i++){
            //属性名字
            String attrName =attrs.getAttributeName(i);

            //属性值
            String attrValue=attrs.getAttributeValue(i);
            Log.d("TAG", "parseSkinView() returned: " +attrValue );
            int id=-1;
            String entryName=null;
            String typeName=null;
            SkinInterface skinInterface=null;
            if("background".equals(attrName)){
                id=Integer.parseInt(attrValue.substring(1));
                entryName=context.getResources().getResourceEntryName(id);
                typeName=context.getResources().getResourceTypeName(id);
                skinInterface=new BackgroundSkin(attrName,id,entryName,typeName);
            }else if("textColor".equals(attrName)){
                id=Integer.parseInt(attrValue.substring(1));
                entryName=context.getResources().getResourceEntryName(id);
                typeName=context.getResources().getResourceTypeName(id);
                skinInterface=new TextSkin(attrName,id,entryName,typeName);
            }

            if(skinInterface!=null){
                skinInterfaceList.add(skinInterface);
            }
            SkinItem skinItem=new SkinItem(skinInterfaceList,view);
            map.put(view,skinItem);
            skinItem.apply();
        }
    }


    private View createView(String name, Context context, AttributeSet attrs) {
        try {
            Class clazz = context.getClassLoader().loadClass(name);
            Constructor<? extends View> constructor = clazz.getConstructor(
                    new Class[]{Context.class, AttributeSet.class});
            constructor.setAccessible(true);
            return constructor.newInstance(context, attrs);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    //给外面调用换肤
    public void update(){
        for(View view:map.keySet()){
            if(view ==null){
                continue;
            }
            map.get(view).apply();
        }
    }

    class SkinItem {
        public List<SkinInterface> attrList;
        public View view;

        public SkinItem(List<SkinInterface> attrList, View view) {
            this.attrList = attrList;
            this.view = view;
        }

        public void apply() {
            for (SkinInterface skinInterface : attrList) {
                skinInterface.apply(view);
            }
        }
    }
}
