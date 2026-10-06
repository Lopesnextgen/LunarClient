package com.moonsworth.lunar.client.event.input;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.event.input.KeyInputTypeLegacy;
import lombok.Generated;
import com.moonsworth.lunar.client.highlight.HighlightImpl;

public class KeyInputEvent extends HighlightImpl {
   private final KeyCode field1;
   private final char character;
   private final int keyCode;
   private final int field2;
   private final KeyInputTypeLegacy field3;

   @Generated
   public KeyInputEvent(KeyCode keyCode2, char character2, int value, int value2, KeyInputTypeLegacy highlightType2) {
      this.field1 = keyCode2;
      this.character = character2;
      this.keyCode = value;
      this.field2 = value2;
      this.field3 = highlightType2;
   }

   @Generated
   public KeyCode method1() {
      return this.field1;
   }

   @Generated
   public char getCharacter() {
      return this.character;
   }

   @Generated
   public int getKeyCode() {
      return this.keyCode;
   }

   @Generated
   public int getModifiers() {
      return this.field2;
   }

   @Generated
   public KeyInputTypeLegacy method4() {
      return this.field3;
   }
}
