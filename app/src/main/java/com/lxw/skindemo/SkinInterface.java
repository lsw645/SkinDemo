package com.lxw.skindemo;

import android.view.View;

/**
 * description... //TODO
 *
 * @author lsw
 * @version 1.0, 2017/4/13
 * @see //TODO
 * @since JDK 1.8
 */

public abstract class SkinInterface {
    //属性名称 background
    public String attrName =null;
    //整个资源id @color/ss
    public int defId =0 ;
    //资源具体名称
    public String attrValueName =null;
    //资源类型 drawable color
    public String attrType;

    public SkinInterface(String attrName, int defId, String attrValueName, String attrType) {
        this.attrName = attrName;
        this.defId = defId;
        this.attrValueName = attrValueName;
        this.attrType = attrType;
    }

    public abstract  void apply(View view );
}
