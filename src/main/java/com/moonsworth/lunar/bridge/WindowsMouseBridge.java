package com.moonsworth.lunar.bridge;

public interface WindowsMouseBridge {
   boolean bridge$isGrabbed();

   void bridge$handleMouseMoved(int number1, int number2, long number3);

   void bridge$handleMouseMovedRelative(int number1, int number2, long number3);
}
