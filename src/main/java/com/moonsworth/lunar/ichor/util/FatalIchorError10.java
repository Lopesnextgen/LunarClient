package com.moonsworth.lunar.ichor.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class FatalIchorError10 {
   private final List<FatalIchorError10.Data> field1;

   public FatalIchorError10(org.objectweb.asm.ClassWriter var1) {
      try {
         Field var2 = org.objectweb.asm.ClassWriter.class.getDeclaredField("symbolTable");
         var2.setAccessible(true);
         Object var3 = var2.get(var1);
         Field var4 = var3.getClass().getDeclaredField("entries");
         var4.setAccessible(true);
         Object[] var5 = (Object[])var4.get(var3);
         this.field1 = new ArrayList<>(var5.length);

         for (Object var9 : var5) {
            if (var9 != null) {
               this.field1.add(new FatalIchorError10.Data(var9));
            }
         }
      } catch (ReflectiveOperationException var10) {
         throw new RuntimeException(var10);
      }
   }

   public List<FatalIchorError10.Data> method1() {
      return this.field1;
   }

   public static final class Data {
      private final Object field1;
      private int index;
      private int tag;
      private String owner;
      private String name;
      private String value;
      private long field2;

      public Data(Object var1) {
         this.field1 = var1;

         try {
            Class var2 = var1.getClass().getSuperclass();

            for (Field var6 : FatalIchorError10.Data.class.getDeclaredFields()) {
               if (!var6.getName().equals("asmEntry")) {
                  Field var7 = var2.getDeclaredField(var6.getName());
                  var7.setAccessible(true);
                  var6.setAccessible(true);
                  var6.set(this, var7.get(var1));
               }
            }
         } catch (ReflectiveOperationException var8) {
            throw new RuntimeException(var8);
         }
      }

      public int getIndex() {
         return this.index;
      }

      public int getTag() {
         return this.tag;
      }

      public String getOwner() {
         return this.owner;
      }

      public String getName() {
         return this.name;
      }

      public String getValue() {
         return this.value;
      }

      public long method1() {
         return this.field2;
      }

      @Override
      public String toString() {
         return "EntryWrapper{index="
            + this.index
            + ", tag="
            + this.tag
            + ", owner='"
            + this.owner
            + "', name='"
            + this.name
            + "', value='"
            + this.value
            + "', data="
            + this.field2
            + "}";
      }
   }
}
