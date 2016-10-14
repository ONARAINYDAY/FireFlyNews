# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in F:\android-studio-sdk/tools/proguard/proguard-android.txt
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
#----------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------

#-------------------------------------------基本不用动区域--------------------------------------------
#---------------------------------基本指令区----------------------------------
#optimizationpasses 表示proguard对你的代码进行迭代优化的次数，首先要明白optimization 会对代码进行各种优化，每次优化后的代码还可以再次优化，
#所以就产生了优化次数的问题，这里面的 passes 应该翻译成 ‘次数’ 而不是 ‘通道’。楼上默认写 5 ，应该是做Android的，关于Android里面为什么写 5 ，
#因为作者本来写 99 ，但是每次迭代时间都很长团队成员天天抱怨，就改成 5 了，迭代会在最后一次无法优化的时候停止，也就是虽然你写着 99 ，但是可能就优化了 几次，一般情况下迭代10次左右的时候代码已经不能再次优化了
-optimizationpasses 5

# 混淆时不使用大小写混合，混淆后的类名为小写
# windows下的同学还是加入这个选项吧(windows大小写不敏感)
-dontusemixedcaseclassnames  # 是否使用大小写混合

# 指定不去忽略非公共的库的类
# 默认跳过，有些情况下编写的代码与类库中的类在同一个包下，并且持有包中内容的引用，此时就需要加入此条声明
-dontskipnonpubliclibraryclasses # 是否混淆第三方jar

# 指定不去忽略非公共的库的类的成员
-dontskipnonpubliclibraryclassmembers #不去忽略包可见的库类的成员

# 不做预检验，preverify是proguard的四个步骤之一
# Android不需要preverify，去掉这一步可以加快混淆速度
-dontpreverify # 混淆时是否做预校验

#优化  不优化输入的类文件
-dontoptimize


# 有了verbose这句话，混淆后就会生成映射文件
# 包含有类名->混淆后类名的映射关系
# 然后使用printmapping指定映射文件的名称
-verbose # 混淆时是否记录日志

#####################记录生成的日志数据,gradle build时在本项目根目录输出################
#mapping.txt文件中找到proguard究竟给你混淆了什么,同时保留了什么
-printmapping proguardMapping.txt
#apk 包内所有 class 的内部结构
-dump class_files.txt
#未混淆的类和成员
-printseeds seeds.txt
#列出从 apk 中删除的代码
-printusage unused.txt
################混淆保护自己项目的部分代码#########################

# 指定混淆时采用的算法，后面的参数是一个过滤器
# 这个过滤器是谷歌推荐的算法，一般不改变
-optimizations !code/simplification/cast,!field/*,!class/merging/* # 混淆时所采用的算法

# 保护代码中的Annotation不被混淆
# 这在JSON实体映射时非常重要，比如fastJson
-keepattributes *Annotation*,InnerClasses #保护给定的可选属性,例如LineNumberTable, LocalVariableTable, SourceFile, Deprecated, Synthetic, Signature, and InnerClasses

# 避免混淆泛型
# 这在JSON实体映射时非常重要，比如fastJson
-keepattributes Signature #避免混淆泛型 如果混淆报错建议关掉

# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable
#----------------------------------------------------------------------------
#---------------------------------默认保留区---------------------------------
# 保留了继承自Activity、Application这些类的子类
# 因为这些子类有可能被外部调用
# 比如第一行就保证了所有Activity的子类不要被混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Appliction
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}

#忽略警告
-ignorewarning

#如果引用了v4或者v7包--->
-keep class android.support.v4.** { *; }
-keep interface android.support.v4.** { *; }
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }

#表示对android.support包下的代码不警告，因为support包中有很多代码都是在高版本中使用的，如果我们的项目指定的版本比较低在打包时就会给予警告。
#不过support包中所有的代码都在版本兼容性上做足了判断，因此不用担心代码会出问题，所以直接忽略警告就可以了。
-dontwarn android.support.**

# 保留Activity中的方法参数是view的方法，
# 从而我们在layout里面编写onClick就不会影响
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}

#//以上两种任意一种都行

#一个项目中最好把所有的实体都放到同一个包下，这样针对包混淆就行了，避免了实体混淆遗漏而造成的崩溃

# 保留所有的本地native方法不被混淆。这点在我们做ndk开发的时候很重要
-keepclasseswithmembernames class * {
    native <methods>;
}
#保持枚举 enum 类不被混淆
#-keepclassmembers enum * {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}
# 保留Parcelable序列化的类不能被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

# 保留Serializable序列化的类不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# 保留自定义控件(继承自View)不能被混淆
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}


#不混淆资源类,对R文件下的所有类及其方法，都不能被混淆
-keep class **.R$* {
 *;
}

# 对于带有回调函数的onXXEvent、**On*Listener的，不能被混淆
-keepclassmembers class * {
    void *(**On*Event);
    void *(**On*Listener);
}

#Gson  泛型注解 --->
-keepattributes EnclosingMethod
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

# 使用Gson时需要配置Gson的解析对象及变量都不混淆。不然Gson会找不到变量。

#理解完战略级思想后，我们开始第一部分补充-实体类。实体类由于涉及到与服务端的交互，各种gson的交互如此等等，
#是要保留的。将你项目中实体类都拎出来，用以下语法进行保留。
#-keep class 你的实体类所在的包.** { *; }
# 将下面替换成自己的实体类
-keep class com.zjk.fireflynews.data.**{ *; }

# ButterKnife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# LeakCanary
-keep class org.eclipse.mat.** { *; }
-keep class com.squareup.leakcanary.** { *; }

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# RxJava RxAndroid
-dontwarn sun.misc.**
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

# Okio
-dontwarn com.squareup.**
-dontwarn okio.**
-keep public class org.codehaus.* { *; }
-keep public class java.nio.* { *; }

# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keep interface retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# OkHttp3
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**

#保持第三方不被混淆
-keep class com.dou361.**{*;}
-keep class tv.danmaku.ijk.**{*;}