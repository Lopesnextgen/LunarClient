package com.moonsworth.lunar.client.cosmetics.emote;

import com.moonsworth.lunar.client.cosmetics.emote.EmoteGiftInfo;
import com.moonsworth.lunar.client.util.text.DateUtils;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

public class EmoteGift {
   private final int field1;
   private final long field2;
   private final Instant field3;
   private final List<Integer> field4;
   private final EmoteGiftInfo field5;

   public EmoteGift(int value, long value2, Instant instant4, List<Integer> list, EmoteGiftInfo emoteGiftInfo) {
      this.field1 = value;
      this.field2 = value2;
      this.field3 = instant4;
      this.field4 = list;
      this.field5 = emoteGiftInfo;
   }

   @Override
   public boolean equals(Object object) {
      if (this == object) {
         return true;
      } else if (object != null && this.getClass() == object.getClass()) {
         EmoteGift fov2_42 = (EmoteGift)object;
         return this.field1 == fov2_42.field1;
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.field1);
   }

   public Instant method1() {
      return this.field3;
   }

   public EmoteGiftInfo method2() {
      return this.field5;
   }

   public String method3() {
      LocalDateTime localdatetime1 = LocalDateTime.ofInstant(this.field3, ZoneId.systemDefault());
      return localdatetime1.format(DateUtils.field1);
   }

   public List<Integer> method4() {
      return this.field4;
   }

   public long method5() {
      return this.field2;
   }

   public int id() {
      return this.field1;
   }

   public List<Integer> method6() {
      return this.field4;
   }
}
