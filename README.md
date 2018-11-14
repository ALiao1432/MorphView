[![](https://jitpack.io/v/ALiao1432/MorphView.svg)](https://jitpack.io/#ALiao1432/MorphView)

# Demo
![](https://lh3.googleusercontent.com/y14E7d6OyNh6BXN-5YtQMPb_OvNUC4aW1cwyCCJpzmDEraS3H4qIsFlOqXqVrE_l-UOY6mfgvVQ=w246-h437-no)

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
  implementation 'com.github.ALiao1432:MorphView:v1.0.1'
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
# A demo project
https://github.com/ALiao1432/NumberMorph
