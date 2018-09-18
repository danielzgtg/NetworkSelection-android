Network Selection Alternative for AOSP
=============
An app to manually select Android network when the system Settings app doesn't work.

## Features
Set manual cell provider to any string.
Set cell provider back to automatic.

## Why?
Phone Services crashes on some ROMs when using the Settings app to do this.
A separate app was a fast way to work around this while the ROMs are being fixed.

## Requirements
- Android Studio
- Gradle 4.10.1
- Phone on Android Pie
- Signing keys
- adb

## Building
1. Open the cloned git repo with Android Studio
2. Configure Gradle
3. Run: gradle build && gradle clean
4. Build using Build > Generate Signed Apk

## Installation
1. Find the signed apk and "adb install" it
2. Reinstall the app as a system app
3. Reboot

## Usage
1. Scan and try to set the network using the ROM's Settings app
2. Search logcat for a bunch of "operatorNumeric: XXXXXX+X"
3. In the app, type one of the numbers into "PLMNID (operator)", and tap "SET MANUAL"
4. Repeat step 3 until you get the cell provider you want

## Revert
1. In the app, tap "UNSET AND RETURN TO AUTOMATIC"
2. Reboot to recovery, then wipe cache, dalvik, and system, and reflash your ROM
3. Uninstall the app normally if it persists after reboot

## Warning
This app uses system Telephony APIs. It has not been tested with all ROMs and phones. By using this app, you risk hardware damage, data loss, illegally connecting to the wrong network, and more.

## License
Creative Commons 0 (Public Domain)

