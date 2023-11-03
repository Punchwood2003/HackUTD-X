# HackUTD-X

## Flutter
This section will explain how to download and install Flutter on your machine, as well as any additional steps that may be required to get Flutter up and running.

### Downloading Flutter
If you have never downloaded Flutter before, follow the steps below to download and install Flutter on your machine:
1. Download the Flutter SDK from the following link: https://flutter.dev/docs/get-started/install
2. Extract the zip file to a location of your choice
3. Add the flutter/bin directory to your `PATH` environment variable
   1. This can be done by running the following command in the terminal while in the root directory of where you extracted the Flutter SDK:
    ```bash
    export PATH="$PATH:`pwd`/flutter/bin"
    ```
    2. Alternatively, you can add the following line to your `~/.bashrc` file:
     ```bash
    export PATH="$PATH:[PATH_TO_FLUTTER_SDK]/flutter/bin"
    ```
    3. Alternatively, if you are running on Windows and you don't want to use the CLI to update your `PATH` environment variable, you can follow the [instructions here](https://flutter.dev/docs/get-started/install/windows#update-your-path).
4. Run the following command to verify that Flutter is installed properly:
```bash
flutter doctor
```
5. Run the following command to install the required dependencies:
```bash
flutter pub get
```

### Upgrading Flutter
If you already have Flutter installed and want to upgrade to the latest version, run the following command:
```bash
flutter upgrade
```
This will upgrade Flutter to the latest version (`v3.13.9`) and also upgrade all the dependencies.

### Installing or Upgrading Anroid Studio
If you have never downloaded Android Studio before, or are wanting to update it, follow the steps below to download and install Android Studio on your machine:
1. Download Android Studio from the following link: https://developer.android.com/studio
2. Run the installer and follow the instructions to install Android Studio
3. Once Android Studio is installed, run `flutter doctor` to verify that Flutter recognizes Android Studio as an IDE.

### Additional Required Steps
Please continue to follow the instructions listed in the [Flutter documentation](https://flutter.dev/docs/get-started/install) to ensure that you have all the required dependencies installed for your specific operating system. This includes installing the Android SDK, setting up an Android emulator, and setting up Operating System specific additions.