import 'package:flutter/services.dart';

class Toast {
  Toast(String message) {
    _showToast(message);
  }

  static const platform = const MethodChannel('flutter.toast.message.channel');

  Future<Null> _showToast(String message) async {
    await platform.invokeMethod('toast', {'message': message});
  }
}
