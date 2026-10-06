package com.moonsworth.webosr.input;

public enum Modifiers {
   CAPS_LOCK(1),
   SHIFT(2),
   CONTROL(4),
   ALT(8),
   SUPER(16),
   LEFT_MOUSE_BUTTON(32),
   RIGHT_MOUSE_BUTTON(64),
   MIDDLE_MOUSE_BUTTON(128),
   NUM_LOCK(256),
   KEY_PAD(512),
   LEFT(1024),
   RIGHT(2048),
   REPEAT(4096),
   SYSTEM_KEY(8192);

   private final int mask;

   Modifiers(int value) {
      this.mask = value;
   }

   public static int toMask(Modifiers... items0) {
      int number1 = 0;

      for (Modifiers modifiers5 : items0) {
         number1 |= modifiers5.getMask();
      }

      return number1;
   }

   public int getMask() {
      return this.mask;
   }
}
