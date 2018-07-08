package com.example.ssmbu.gankki.annotation;

import android.support.annotation.IntDef;

public class AnnotationGank {
    public static final int ALL=0;
    public static final int ANDROID=1;
    public static final int IOS=2;
    public static final int APP=3;
    public static final int FRONT_END=4;
    public static final int BLIND=5;
    public static final int EXPANDING_RESOURCE=6;
    public static final int RELAXED_VEDIO=7;
    public static final int FEAST_FOR_EYES=8;

    @IntDef({ALL,ANDROID,IOS,APP,FRONT_END,BLIND,EXPANDING_RESOURCE,RELAXED_VEDIO,FEAST_FOR_EYES})
    public  @interface Gank{}

}
