[![](https://jitpack.io/v/ALiao1432/MorphView.svg)](https://jitpack.io/#ALiao1432/MorphView)  
# Demo gif link
[demo](https://photos.app.goo.gl/cGcWomKHApmh4Wd1A)

# Add library
1. Add it in your root build.gradle at the end of repositories
```gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
2. add the dependency
```gradle
dependencies {
  implementation 'com.github.ALiao1432:MorphView:1.0.11'
}
```

# How to use
1. add it to your layout
```xml
<study.ian.morphviewlib.MorphView
  android:id="@+id/morphView"
  android:layout_width="wrap_content"
  android:layout_height="wrap_content" />
```
2. find view
```java
MorphView morphView = findViewById(R.id.morphView);
```
3. initial
```java
// here R.drawable.vd_0 is the vector asset
morphView.setCurrentId(R.drawable.vd_0);
```
4. other optional setting
```java
// optional setting
morphView.setSize(200, 200);
morphView.setPaintColor("#00897b");
morphView.setPaintWidth(20);
```
5. finally perform the animation to another vector asset whenever you want
```java
morphView.performAnimation(R.drawable.vd_1);
```
# Demo projects
https://github.com/ALiao1432/Counter  
https://github.com/ALiao1432/NumberMorph
