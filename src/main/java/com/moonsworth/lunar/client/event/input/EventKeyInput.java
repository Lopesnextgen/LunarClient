package com.moonsworth.lunar.client.event.input;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.event.input.KeyInputType;
import lombok.Generated;
import com.moonsworth.lunar.client.event.CancellableEvent;

public class EventKeyInput extends CancellableEvent {
   private final KeyCode field1;
   private final char character;
   private final int keyCode;
   private final int field2;
   private final KeyInputType field3;

   @Generated
   public EventKeyInput(KeyCode bridgetype_81, char character2, int value, int value2, KeyInputType keyInputType) {
      this.field1 = bridgetype_81;
      this.character = character2;
      this.keyCode = value;
      this.field2 = value2;
      this.field3 = keyInputType;
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
   public KeyInputType method4() {
      return this.field3;
   }
}
