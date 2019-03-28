# Android's Toast Message in Flutter

Flutter application to show Android's Toast Message.

<img src="https://github.com/UttamPanchasara/FlutterToast/blob/master/gif/flutter_toast.gif" height="400" alt="Screenshot"/> 

## Methods and code to Show Toast message in flutter.

To show Toast Message in flutter, I'm communicating to native code using Platfrom Channels from flutter, and from native code I'm showing the Toast message.

### **Platform Channels:**
Which is provided by flutter to communicate between native code and flutter code.

#### Define Channel in Flutter

```dart

    static const platform = const MethodChannel('flutter.toast.message.channel');
```
#### Define Channel/Handler in Android

```kotlin

    MethodChannel(flutterView, CHANNEL).setMethodCallHandler { call, result ->
          
    }
```

Define platform channel as above and provide channel name,
This Channel name must be same for both platform flutter code and Android Natice Code.

### **Create Class in Flutter:**
To communicate with Android in order to show Toast message.

```dart

    class Toast {
      Toast(String message) {
        _showToast(message);
      }

      static const platform = const MethodChannel('flutter.toast.message.channel');

      Future<Null> _showToast(String message) async {
        // invoke method, provide method name and arguments.
        await platform.invokeMethod('toast', {'message': message});
      }
    }

```

### **Handle Method invocation from Flutter in Android:**

```kotlin

    class MainActivity : FlutterActivity() {
        companion object {
            const val CHANNEL = "flutter.toast.message.channel"
            const val METHOD_TOAST = "toast"
            const val KEY_MESSAGE = "message"
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            GeneratedPluginRegistrant.registerWith(this)

            // handle method invocation from flutter, and perform action
            MethodChannel(flutterView, CHANNEL).setMethodCallHandler { call, result ->
                if (call.method == METHOD_TOAST) {
                    val message = call.argument<String>(KEY_MESSAGE)
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

```

That's all we need to do to show Android Toast message in flutter.

### Usage:
To show toast messages from flutter, Simply call Toast class with your message as argument as below:

```dart

    Toast("Hello, I'm Toast from Flutter.");
```

## Questions?
 
**Ping-Me on :**  [![Twitter](https://img.shields.io/badge/Twitter-%40UTM__Panchasara-blue.svg)](https://twitter.com/UTM_Panchasara)
[![Facebook](https://img.shields.io/badge/Facebook-Uttam%20Panchasara-blue.svg)](https://www.facebook.com/UttamPanchasara94)


<a href="https://stackoverflow.com/users/5719935/uttam-panchasara">
<img src="https://stackoverflow.com/users/flair/5719935.png" width="208" height="58" alt="profile for Uttam Panchasara at Stack Overflow, Q&amp;A for professional and enthusiast programmers" title="profile for Uttam Panchasara at Stack Overflow, Q&amp;A for professional and enthusiast programmers">
</a>


# Donate
> If you found this Example helpful, consider buying me a cup of :coffee:
- Google Pay **(panchasarauttam@okaxis)**
