package com.moonsworth.lunar.bridge.itemcounter;

import java.util.Arrays;
import java.util.Comparator;
import javax.annotation.Nullable;

public enum ItemcounterType {
   PEACEFUL(0, "peaceful"),
   EASY(1, "easy"),
   NORMAL(2, "normal"),
   HARD(3, "hard");

   public static ItemcounterType[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(ItemcounterType::getId)).toArray(ItemcounterType[]::new);
   public int id;
   public String key;

   ItemcounterType(int value, String var4) {
      this.id = value;
      this.key = var4;
   }

   public int getId() {
      return this.id;
   }

   public static ItemcounterType byId(int var0) {
      return BY_ID[var0 % BY_ID.length];
   }

   @Nullable
   public static ItemcounterType byName(String var0) {
      for (ItemcounterType var4 : values()) {
         if (var4.key.equals(var0)) {
            return var4;
         }
      }

      return null;
   }

   public String getKey() {
      return this.key;
   }
}
