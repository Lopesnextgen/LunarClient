package com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin;

import java.util.HashMap;
import lombok.Generated;
import org.joml.Vector3i;

public class Fishing2 {
   private HashMap<String, HashMap<String, Fishing2.Data>> field1;

   @Generated
   public Fishing2(HashMap<String, HashMap<String, Fishing2.Data>> var1) {
      this.field1 = var1;
   }

   @Generated
   public HashMap<String, HashMap<String, Fishing2.Data>> method1() {
      return this.field1;
   }

   public static class Data {
      private Vector3i field1;
      private String message;

      public String getMessage() {
         return " Egg " + this.message + "!";
      }

      @Generated
      public Data(Vector3i var1, String text) {
         this.field1 = var1;
         this.message = text;
      }

      @Generated
      public Vector3i method1() {
         return this.field1;
      }
   }
}
