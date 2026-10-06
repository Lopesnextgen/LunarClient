package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class ComparableImpl implements Comparable<ComparableImpl> {
   private final String field1;
   private final Component field2;
   private final int field3;
   private final long field4;

   public int method1(@NotNull ComparableImpl comparableimpl1) {
      int number2 = Integer.compare(this.field3, comparableimpl1.field3);
      return number2 != 0 ? number2 : this.field1.compareTo(comparableimpl1.field1);
   }

   public static ComparableImpl.Data method2() {
      return new ComparableImpl.Data();
   }

   @Generated
   public String method3() {
      return this.field1;
   }

   @Generated
   public Component getComponent() {
      return this.field2;
   }

   @Generated
   public int getPriority() {
      return this.field3;
   }

   @Generated
   public long method4() {
      return this.field4;
   }

   @Generated
   protected ComparableImpl(String text1, Component component2, int number3, long number4) {
      this.field1 = text1;
      this.field2 = component2;
      this.field3 = number3;
      this.field4 = number4;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof ComparableImpl comparableimpl2)) {
         return false;
      } else {
         if (!comparableimpl2.canEqual(this)) {
            return false;
         }

         String text3 = this.method3();
         String text4 = comparableimpl2.method3();
         return text3 == null ? text4 == null : text3.equals(text4);
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof ComparableImpl;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      byte number2 = 1;
      String text3 = this.method3();
      return number2 * 59 + (text3 == null ? 43 : text3.hashCode());
   }

   public static class Data {
      private String channel;
      private Component component;
      private long duration = 1000L;
      private int priority = ComparableImpl.Type.NORMAL.getPriority();

      public Data() {
      }

      public ComparableImpl.Data method1(String text1) {
         this.channel = text1;
         return this;
      }

      public ComparableImpl.Data method2(Component component1) {
         this.component = component1;
         return this;
      }

      public ComparableImpl.Data method3(long number1) {
         this.duration = number1;
         return this;
      }

      public ComparableImpl.Data method4(ComparableImpl.Type type1) {
         this.priority = type1.getPriority();
         return this;
      }

      public ComparableImpl.Data method5(int number1) {
         this.priority = number1;
         return this;
      }

      public ComparableImpl method6() {
         return new ComparableImpl(this.channel, this.component, this.priority, Ref.method3().bridge$getSystemTime() + this.duration);
      }
   }

   public enum Type {
      LOW(-10),
      NORMAL(0),
      HIGH(10),
      CRITICAL(20);

      private final int priority;

      @Generated
      public int getPriority() {
         return this.priority;
      }

      @Generated
      Type(int number3) {
         this.priority = number3;
      }
   }
}
