package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class Nameplate2Impl_2 extends Nameplate2 {
   private String field1;
   private String key;
   private Nameplate2Impl$Type field2;
   private Object value;

   @Override
   public void method1(ByteBufLoader var1) {
      this.field1 = var1.readString();
      this.key = var1.readString();
      this.field2 = var1.method9(Nameplate2Impl$Type.class);
      switch (this.field2) {
         case STRING:
            this.value = var1.readString();
            break;
         case INTEGER:
            this.value = var1.readVarInt();
            break;
         case FLOAT:
            this.value = var1.readFloat();
            break;
         case DOUBLE:
            this.value = var1.readDouble();
            break;
         case LONG:
            this.value = var1.readLong();
            break;
         case BOOLEAN:
            this.value = var1.readBoolean();
      }
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method1(this.field1);
      var1.method1(this.key);
      var1.method10(this.field2);
      switch (this.field2) {
         case STRING:
            var1.method1((String)this.value);
            break;
         case INTEGER:
            var1.method11((Integer)this.value);
            break;
         case FLOAT:
            var1.writeFloat((Float)this.value);
            break;
         case DOUBLE:
            var1.writeDouble((Double)this.value);
            break;
         case LONG:
            var1.writeLong((Long)this.value);
            break;
         case BOOLEAN:
            var1.writeBoolean((Boolean)this.value);
      }
   }

   public static Nameplate2Impl$Type method3(Object object) {
      if (object instanceof String) {
         return Nameplate2Impl$Type.STRING;
      } else if (object instanceof Integer) {
         return Nameplate2Impl$Type.INTEGER;
      } else if (object instanceof Float) {
         return Nameplate2Impl$Type.FLOAT;
      } else if (object instanceof Double) {
         return Nameplate2Impl$Type.DOUBLE;
      } else if (object instanceof Long) {
         return Nameplate2Impl$Type.LONG;
      } else if (object instanceof Boolean) {
         return Nameplate2Impl$Type.BOOLEAN;
      } else {
         throw new IllegalArgumentException("Unsupported value type: " + object.getClass());
      }
   }

   @Override
   public void method3(Nameplate4 var1) {
      ModsSettings var2 = ThreadModuleDump63.method4().method40();
      if (var2.method11(this.field1) instanceof AbstractFeature var4) {
         var4.method33().set(this.key, this.value);
      }
   }

   @Generated
   public Nameplate2Impl_2(String var1, String var2, Nameplate2Impl$Type type, Object var4) {
      this.field1 = var1;
      this.key = var2;
      this.field2 = type;
      this.value = var4;
   }

   @Generated
   public Nameplate2Impl_2() {
   }

   @Generated
   public String method5() {
      return this.field1;
   }

   @Generated
   public String getKey() {
      return this.key;
   }

   @Generated
   public Nameplate2Impl$Type method6() {
      return this.field2;
   }

   @Generated
   public Object getValue() {
      return this.value;
   }
}
