package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class SettingValuePacket extends ReplayPacket {
   private String field1;
   private String key;
   private SettingValueType field2;
   private Object value;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.field1 = bytebufloader1.readString();
      this.key = bytebufloader1.readString();
      this.field2 = bytebufloader1.method9(SettingValueType.class);
      switch (this.field2) {
         case STRING:
            this.value = bytebufloader1.readString();
            break;
         case INTEGER:
            this.value = bytebufloader1.readVarInt();
            break;
         case FLOAT:
            this.value = bytebufloader1.readFloat();
            break;
         case DOUBLE:
            this.value = bytebufloader1.readDouble();
            break;
         case LONG:
            this.value = bytebufloader1.readLong();
            break;
         case BOOLEAN:
            this.value = bytebufloader1.readBoolean();
      }
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method1(this.field1);
      bytebufloader1.method1(this.key);
      bytebufloader1.method10(this.field2);
      switch (this.field2) {
         case STRING:
            bytebufloader1.method1((String)this.value);
            break;
         case INTEGER:
            bytebufloader1.method11((Integer)this.value);
            break;
         case FLOAT:
            bytebufloader1.writeFloat((Float)this.value);
            break;
         case DOUBLE:
            bytebufloader1.writeDouble((Double)this.value);
            break;
         case LONG:
            bytebufloader1.writeLong((Long)this.value);
            break;
         case BOOLEAN:
            bytebufloader1.writeBoolean((Boolean)this.value);
      }
   }

   public static SettingValueType method3(Object object) {
      if (object instanceof String) {
         return SettingValueType.STRING;
      } else if (object instanceof Integer) {
         return SettingValueType.INTEGER;
      } else if (object instanceof Float) {
         return SettingValueType.FLOAT;
      } else if (object instanceof Double) {
         return SettingValueType.DOUBLE;
      } else if (object instanceof Long) {
         return SettingValueType.LONG;
      } else if (object instanceof Boolean) {
         return SettingValueType.BOOLEAN;
      } else {
         throw new IllegalArgumentException("Unsupported value type: " + object.getClass());
      }
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      ModsSettings fogloader32 = Ref.method4().method40();
      if (fogloader32.method11(this.field1) instanceof AbstractFeature framework7extension24) {
         framework7extension24.method33().set(this.key, this.value);
      }
   }

   @Generated
   public SettingValuePacket(String text, String text2, SettingValueType nameplate2impl$type3, Object object) {
      this.field1 = text;
      this.key = text2;
      this.field2 = nameplate2impl$type3;
      this.value = object;
   }

   @Generated
   public SettingValuePacket() {
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
   public SettingValueType method6() {
      return this.field2;
   }

   @Generated
   public Object getValue() {
      return this.value;
   }
}
