# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


# 指定代码的压缩级别
-optimizationpasses 5
 # 是否使用大小写混合
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
# 混淆时是否做预校验
-dontpreverify
# 混淆时是否记录日志
-verbose
-dontskipnonpubliclibraryclassmembers
# 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
#不缩减代码
# -dontshrink
# 忽略Warnings
-ignorewarnings

-keepattributes InnerClasses
-dontoptimize
-keepattributes EnclosingMethod

-dontoptimize
-keepattributes JavascriptInterface,Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,LocalVariable*Table,*Annotation*,Synthetic,EnclosingMethod
-repackageclasses ''
-dontoptimize
-allowaccessmodification
-printmapping map.txt

#R.java
-keepclassmembers class **.R$* {
    public static <fields>;
}
-keep class **.R$* {*;}

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

-keep public class * extends javax.annotation.processing.AbstractProcessor

#Annotation
-keep class * extends java.lang.annotation.Annotation { *; }
-keepattributes *Annotation*
-keep @interface *

#Android&Java
-dontwarn android.**
-dontwarn com.android.**
-dontwarn java.**
-dontwarn javax.**
-keep class com.android.** {*;}
-keep class android.** {*;}
-keep class android.support.v7.** {*;}
-keep public class javax.** { *; }
-keep public class java.** { *; }

# or more generally:
#-keep public class * implements com.bumptech.glide.module.GlideModule

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclassmembers class * implements android.os.Parcelable {
    static ** CREATOR;
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

# As described in tools/proguard/examples/android.pro - ignore all warnings.
#如果有其它包有warning，在报出warning的包加入下面类似的-dontwarn 报名


#app中的##################################################
-dontwarn com.zleme.app.presentation.bean.**
-keep class com.zleme.app.presentation.bean.**{*;}
##########################################################

##############高德相关混淆文件
#3D 地图
-dontwarn com.autonavi.amap.**
-dontwarn com.amap.api.**
-dontwarn com.aps.**
-keep class com.amap.api.maps.**{*;}
-keep class com.autonavi.amap.mapcore.*{*;}
#2D地图
-keep class com.amap.api.maps2d.**{*;}
-keep class com.amap.api.mapcore2d.**{*;}
#Location2
-keep   class com.amap.api.location.**{*;}
-keep   class com.aps.**{*;}
#Service
-keep class com.amap.api.services.**{*;}
#导航
-keep class com.amap.api.navi.**{*;}
-keep class com.autonavi.**{*;}
##############高德相关混淆文件 over

#百度地图
-keep class com.baidu.** { *; }
-dontwarn com.baidu.**
-keep class vi.com.gdi.bgl.android.**{*;}

#video player
-keep class tv.danmaku.ijk.** { *; }
-dontwarn tv.danmaku.ijk.**

#讯飞语音
-keep class com.iflytek.voiceads.**{*;}
-keep class com.iflytek.**{*;}
-dontwarn com.iflytek.**

#信鸽
-keep class com.tencent.android.tpush.**  {* ;}
-dontwarn com.tencent.android.tpush.**
-keep class com.tencent.mid.**  {* ;}

#bugly
-keep public class com.tencent.bugly.**{*;}

#ShareSdk
-dontwarn cn.sharesdk.**
-dontwarn com.sina.**
-keep class android.net.http.SslError
-keep class android.webkit.**{*;}
-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class m.framework.**{*;}

-keep enum com.facebook.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public interface com.facebook.**
-keep public interface com.tencent.**

-keep class com.facebook.**
-keep public class com.tencent.** {*;}

-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
-dontwarn com.tencent.mm.sdk.**
-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}

#umeng
-keep class com.umeng.socialize.sensor.**
-keep class com.umeng.socialize.** {*;}
-dontwarn com.umeng.socialize.**
-keep class com.umeng.scrshot.**

#retrofit RxJava
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-dontwarn sun.misc.**
-keep class sun.misc.Unsafe { *; }
-keepattributes Signature
-keepattributes Exceptions

#glide 图片加载
-dontwarn android.support.v8.**
-keep public class android.support.v8.**
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-dontwarn com.bumptech.glide.load.resource.bitmap.VideoDecoder
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule
-dontwarn jp.co.cyberagent.**

#jackson
-dontwarn org.codehaus.jackson.**
-dontwarn javax.ws.rs.**
-keep class org.codehaus.jackson.**{*;}

#retrolambda
-dontwarn org.codehaus.**
-dontwarn java.lang.invoke.**
-keep class java.lang.invoke.** {*;}
-keep class org.codehaus.**

# universal-image-loader 混淆
-dontwarn com.nostra13.universalimageloader.**
-keep class com.nostra13.universalimageloader.** { *; }

#json
#-dontwarn javax.xml.bind.**

#butterknife
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }
-keepnames class * { @butterknife.InjectView *;}

#rxbus
-keep class com.hwangjr.rxbus.** {*;}

#rxjava
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#greenDao
-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
    public static final java.lang.String *;
}
-keep class **$Properties

#qiniu
-dontwarn com.qiniu.android.**
-keep class com.qiniu.android.** { *; }


#alibaba
-keep class com.alibaba.** {*;}
-keep class com.alibaba.sdk.** {*;}
-dontwarn com.alibaba.sdk.**
-keep class com.taobao.** {*;}
-keep class com.alibaba.** {*;}
-keep class com.alipay.** {*;}
-dontwarn com.taobao.**
-dontwarn com.alibaba.**
-dontwarn com.alipay.**
-keep class com.ut.** {*;}
-dontwarn com.ut.**
-keep class com.ta.** {*;}
-dontwarn com.ta.**
-keep class com.alibaba.sdk.android.feedback.** {*;}

#
-keep class oauth.signpost.** {*;}
-dontwarn oauth.signpost.**
-keep class oauth.signpost.commonshttp.** {*;}
-dontwarn oauth.signpost.commonshttp.**

-keep class com.unionpay.**{*;}
-dontwarn com.unionpay.**

-keep class com.wbtech.ums.**{*;}
-keep class com.ut.mini.**{*;}

#gson
-keep class sun.misc.Unsafe { *; }

# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in H:\app\android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-optimizationpasses 5    # 指定代码的压缩级别
-dontusemixedcaseclassnames   # 是否使用大小写混合
-dontskipnonpubliclibraryclasses
-dontpreverify  # 混淆时是否做预校验
-verbose   # 混淆时是否记录日志
-dontskipnonpubliclibraryclassmembers
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*  # 混淆时所采用的算法
# -dontshrink #不缩减代码

-dontoptimize
-keepattributes JavascriptInterface,Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,LocalVariable*Table,*Annotation*,Synthetic,EnclosingMethod
-repackageclasses ''
-allowaccessmodification
-printmapping map.txt

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

-keep public class * extends javax.annotation.processing.AbstractProcessor

# Annotation
-keep class * extends java.lang.annotation.Annotation { *; }
-keepattributes *Annotation*
-keep @interface *

# Android&Java
-dontwarn android.**
-dontwarn com.android.**
-dontwarn java.**
-dontwarn javax.**
-dontwarn java.lang.invoke.*

-keep class com.android.** {*;}
-keep class android.** {*;}
-keep public class javax.** { *; }
-keep public class java.** { *; }

# or more generally:
# -keep public class * implements com.bumptech.glide.module.GlideModule

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclassmembers class * implements android.os.Parcelable {
    static ** CREATOR;
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keep class **$Properties

# bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

# butterknife
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }
-keepnames class * { @butterknife.InjectView *;}

#greenDao
-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
    public static final java.lang.String *;
}

# glide 图片加载
-dontwarn android.support.v8.**
-keep public class android.support.v8.**
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
-dontwarn jp.co.cyberagent.**

# perform
-dontwarn com.sina.**
-keep class android.net.http.SslError
-keep class android.webkit.**{*;}
-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}
-keep class com.sina.**{*;}

# retrofit RxJava
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-dontwarn sun.misc.**
-keep class sun.misc.Unsafe { *; }
-keepattributes Signature
-keepattributes Exceptions

# rxjava
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#rxbus
#-keep class com.xloong.app.xiaoqi.utils.rxbus.**
-keepnames class * { @com.xloong.app.xiaoqi.utils.rxbus.RxBusReact *;}
-keepclassmembers class * { @com.xloong.app.xiaoqi.utils.rxbus.RxBusReact <methods>;}

# 百度地图
-keep class com.baidu.** { *; }
-keep class vi.com.gdi.bgl.android.**{*;}
-dontwarn com.baidu.**

# 阿里
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }
-keep class com.taobao.** {*;}
-keep class com.alibaba.** {*;}
-keep class com.alipay.** {*;}
-dontwarn com.taobao.**
-dontwarn com.alibaba.**
-dontwarn com.alipay.**
-keep class com.ut.** {*;}
-dontwarn com.ut.**
-keep class com.ta.** {*;}
-dontwarn com.ta.**
-keep class com.alibaba.sdk.android.feedback.** {*;}

# 讯飞广告
-keep class com.iflytek.voiceads.**
-dontwarn com.iflytek.voiceads.**

-keep class com.tencent.mm.**
-dontwarn com.alipay.sdk.**
-dontwarn com.baidu.location.**
-dontwarn com.iflytek.voiceads.**
-dontwarn com.umeng.**
-dontwarn com.tencent.mm.**

# 百度移动统计
-keep class com.baidu.bottom.** { *; }
-keep class com.baidu.kirin.** { *; }
-keep class com.baidu.mobstat.** { *; }


# 神策
-dontwarn com.sensorsdata.analytics.android.**
-keep class com.sensorsdata.analytics.android.** {
*;
}
-keep class com.sensorsdata.analytics.android.** { *; }
-keep class **.R$* {
    <fields>;
}
-keepnames class * implements android.view.View$OnClickListener
-keep public class * extends android.content.ContentProvider
-keepnames class * extends android.view.View

-keep class * extends android.app.Fragment {
 public void setUserVisibleHint(boolean);
 public void onHiddenChanged(boolean);
 public void onResume();
 public void onPause();
}
-keep class android.support.v4.app.Fragment {
 public void setUserVisibleHint(boolean);
 public void onHiddenChanged(boolean);
 public void onResume();
 public void onPause();
}
-keep class * extends android.support.v4.app.Fragment {
 public void setUserVisibleHint(boolean);
 public void onHiddenChanged(boolean);
 public void onResume();
 public void onPause();
}

# DataBinding
#-dontwarn android.databinding.**
#-keep class android.databinding.** { *; }
#-keep class 您项目的包名.databinding.** {
#    <fields>;
#    <methods>;
#}

# umeng
-dontwarn com.taobao.**
-dontwarn anet.channel.**
-dontwarn anetwork.channel.**
-dontwarn org.android.**
-dontwarn org.apache.thrift.**
-dontwarn com.xiaomi.**
-dontwarn com.huawei.**
-dontwarn com.meizu.**

-keepattributes *Annotation*

-keep class com.taobao.** {*;}
-keep class org.android.** {*;}
-keep class anet.channel.** {*;}
-keep class com.umeng.** {*;}
-keep class com.xiaomi.** {*;}
-keep class com.huawei.** {*;}
-keep class com.meizu.** {*;}
-keep class org.apache.thrift.** {*;}

-keep class com.alibaba.sdk.android.**{*;}
-keep class com.ut.**{*;}
-keep class com.ta.**{*;}

-keep public class **.R$*{
   public static final int *;
}

# 阿里云视频
-keep class com.alivc.player.**{*;}
-keep class com.aliyun.clientinforeport.**{*;}
-keep class com.aliyun.vodplayer.**{*;}
-keep class com.aliyun.com.aliyun.vodplayerview.**{*;}
-dontwarn com.alivc.player.**

# 网易云信
-dontwarn com.netease.**
-keep class com.netease.** {*;}
#如果你使用全文检索插件，需要加入
-dontwarn org.apache.lucene.**
-keep class org.apache.lucene.** {*;}


# vpn编译需要
# This dnsjava class uses old Sun API
-dontnote org.xbill.DNS.spi.DNSJavaNameServiceDescriptor
-dontwarn org.xbill.DNS.spi.DNSJavaNameServiceDescriptor
# See https://stackoverflow.com/questions/5701126, happens in dnsjava
-optimizations !code/allocation/variable
-dontwarn kotlinx.atomicfu.AtomicFU

-keep class me.whaless.app.data.entity.** {*;}
-keep class me.whaless.app.domain.model.** {*;}
-keep class me.whaless.app.presentation.bean.** {*;}
