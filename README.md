# appiumBasics
## Basic Test Cases
[TestCases.md](/TestCases.md)

## First Steps
Install [Node](https://nodejs.org/en/download/)

Install [Appium](http://appium.io/docs/en/about-appium/getting-started/) `
npm install -g appium
`  
Install [XCode 10](https://developer.apple.com/xcode/)

Install [Android Studio](https://developer.android.com/studio/install)

Run [testng.xml](/demoCode/testng.xml)

Set `alias kill4723='lsof -n -i4TCP:4723 | grep LISTEN | awk '\''{ print  }'\'' | xargs kill' `
to kill appium server after test

## Android
Set ANDROID_HOME

create `alias emu28='./Library/Android/sdk/emulator/emulator -avd Pixel_2_API_28'` to start the emulator 


## IOS

brew install carthage

Allow network access to WebDriver


## Test Report
- Using ReportNG, we are generating reports at this output folder, [test-output](/demoCode/test-output), in [HTML](/demoCode/test-output/html/index.html) and [XML](/demoCode/test-output/xml/com.shreyansh.IOSTests_results.xml) format 
