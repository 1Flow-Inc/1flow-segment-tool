
# 1Flow Mobile Plugin Destination

1Flow is a leading in-app user survey and messaging platform for Mobile app and SaaS businesses.

Using 1Flow, you can reach users _in-the-moment_ while they are interacting with your website or application, to collect highly contextual user insights that help you improve your product offering and customer experience

This destination is maintained by 1Flow. For any issues with the destination, [contact Support team](mailto:support@1flow.app).

## Getting started

#### If gradle version is 6.5 or lower, include the below repository in your project's build.gradle file:
```
allprojects{
	repositories{
        google()
        jcenter()
        maven{url 'https://jitpack.io'} 
    }
}
```

#### If gradle version is higher than 6.5, add the below code in settings.gradle
```
    dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven{url 'https://jitpack.io'}
    }
}
```

### Add dependency in your app's build.gradle file:
```
compileSdkVersion 34
....
defaultConfig {
		      ....
		      minSdkVersion 21
    }
dependencies {
		....
    implementation 'com.segment.analytics.android:analytics:4.11.3'
    implementation 'com.github.1Flow-Inc:segment-1flow-android:2023.09.26'
}
```

### Initialize Segment and Add 1Fow Destination
```java
Analytics analytics = new Analytics.Builder(context, "YOUR_WRITE_KEY_HERE")
    .use(OneFlowIntegration.FACTORY)
    ...
   .build();
    ...
    Analytics.setSingletonInstance(analytics);
```
## Supported methods

### Identify
An example call would look like:

```java
Analytics.with(context).identify("peter@example.com", new Traits().putName("Peter Gibbons"), null);
```
When you call identify method of segment, it will be equivalent to `logUser` of 1Flow. `userId` will be `userID` and `traits` will be `userDetails`.

### Track
An example call would look like:

```java
Analytics.with(context).track("ButtonClicked");
```
Any value passed in `name`, will be eventName and if you have passed any event property, then it will be event `parameters`.

### Screen

Send Screenview events to record which mobile app screens users have viewed. For example:

```java
Analytics.with(context).screen(null, "Home");
```

Segment sends Screen calls to 1Flow as a `screen_[name]` event (or `screen_view` if a screen name isn't provided).
