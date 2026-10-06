package com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin;

import java.util.HashMap;
import lombok.Generated;
import org.joml.Vector3i;

public class ChocolateEggLocations {
   private HashMap<String, HashMap<String, ChocolateEggLocations.Data>> field1;

   @Generated
   public ChocolateEggLocations(HashMap<String, HashMap<String, ChocolateEggLocations.Data>> map) {
      this.field1 = map;
   }

   @Generated
   public HashMap<String, HashMap<String, ChocolateEggLocations.Data>> method1() {
      return this.field1;
   }

   public static class Data {
      private Vector3i field1;
      private String message;

      public String getMessage() {
         return " Egg " + this.message + "!";
      }

      @Generated
      public Data(Vector3i vector3i1, String text) {
         this.field1 = vector3i1;
         this.message = text;
      }

      @Generated
      public Vector3i method1() {
         return this.field1;
      }
   }
}
