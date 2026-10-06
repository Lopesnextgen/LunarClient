package com.moonsworth.lunar.client.cosmetics.emote;

import com.moonsworth.lunar.client.cosmetics.emote.EmoteGift;
import lombok.Generated;

public class EmoteDefinition {
   final String field1;
   final String field2;
   EmoteGift field3;

   public EmoteDefinition(String text1, String text) {
      this.field1 = text1;
      this.field2 = text;
   }

   public static EmoteDefinition method1(String text0, String text1) {
      return new EmoteDefinition(text0, text1);
   }

   public static EmoteDefinition method2(String text0) {
      return method1(text0, "");
   }

   @Generated
   public String getId() {
      return this.field1;
   }

   @Generated
   public String method3() {
      return this.field2;
   }

   @Generated
   public EmoteGift method4() {
      return this.field3;
   }

   @Generated
   public void method5(EmoteGift fov2_41) {
      this.field3 = fov2_41;
   }
}
