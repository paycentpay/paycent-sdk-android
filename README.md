# paycent-sdk-androidstudio
mobile payment sdk for Android (Android Studio)


##	Android Studio: integrate AAR package

Android SDK adopt the aar library format of android. SDK need run on Android SDK version > 5.0（API > 21）.
Please follow the steps to import SDK into Android Studio：
1)	Import aar file.
2)	Make sure step 1 works. In settings.gradle, make sure the SDK library is already set. 
<pre> include ':app', ':paysdklib-release' </pre>

3.	Add the sdk library as dependency in the build.gradle of the app. 

<pre> dependencies {
    compile project(":paysdklib-release")
}
</pre>
Add following libraries in the dependencies section：(version may need be changed according to the android studio version setting)

* compile 'com.android.support:appcompat-v7:25.3.1'
* compile 'com.android.support.constraint:constraint-layout:1.0.2'
* compile 'com.android.support:design:25.3.1'
* compile 'com.android.support:recyclerview-v7:25.3.1'
* compile 'com.android.support:cardview-v7:25.3.1'
* compile 'com.android.support:support-v4:25.3.1'

Add following control to avoid error when android studio try to crunch the pictures resources. 

<pre>
 aaptOptions{
	cruncherEnabled = false
	useNewCruncher = false
}
</pre>
