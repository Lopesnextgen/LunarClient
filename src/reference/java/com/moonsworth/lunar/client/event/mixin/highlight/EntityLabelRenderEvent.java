package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.BatchingBufferSourceBridge;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.client.util.concurrent.SupplierExtension;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Collections;
import java.util.List;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Annotation2(min = 16)
public abstract class EntityLabelRenderEvent extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private static int field1 = 0;
   @NotNull
   private final BridgeExtension field2;
   @Nullable
   private Object[] values;

   public static synchronized int method1() {
      return field1++;
   }

   private EntityLabelRenderEvent(@NotNull BridgeExtension var1, @Nullable Object[] var2) {
      this.field2 = var1;
      this.values = var2;
   }

   @Nullable
   public EntityPlayerBridge method2() {
      return method3(this.method8());
   }

   @Nullable
   public static EntityPlayerBridge method3(BridgeExtension var0) {
      while (!(var0 instanceof EntityPlayerBridge)) {
         var0 = var0.bridge$getVehicle();
         if (var0 instanceof EntityPlayerBridge var1) {
            return var1;
         }

         if (var0 == null) {
            break;
         }
      }

      return null;
   }

   public boolean method4() {
      BridgeExtension var1 = this.method8();
      BridgeExtension var2 = var1.bridge$getVehicle();

      while (var2 != null && !(var2 instanceof EntityPlayerBridge)) {
         var2 = var2.bridge$getVehicle();
      }

      if (var2 == null) {
         return true;
      }

      BridgeExtension var3 = method5(var2, null);
      return var3 == null || var3 == var1;
   }

   @Nullable
   private static BridgeExtension method5(@NotNull BridgeExtension var0, @Nullable BridgeExtension var1) {
      List var2 = var0.bridge$getPassengers();

      for (int var3 = 0; var3 < var2.size(); var3++) {
         BridgeExtension var4 = (BridgeExtension)var2.get(var3);
         if (var4.bridge$isTextDisplay()) {
            if (var1 == null || var4.bridge$getPosY() > var1.bridge$getPosY()) {
               var1 = var4;
            }

            var1 = method5(var4, var1);
         }
      }

      return var1;
   }

   public void method6(int var1, @Nullable Object var2) {
      if (var1 >= 0 && var1 < field1) {
         Object[] var3 = this.values;
         if (var3 == null) {
            var3 = new Object[field1];
         }

         var3[var1] = var2;
         this.values = var3;
      } else {
         throw new IllegalStateException("Invalid set valueId " + var1 + " (" + field1 + ")");
      }
   }

   public <T> T method7(int var1, T var2) {
      Object[] var3 = this.values;
      if (var3 == null) {
         return (T)var2;
      } else if (var1 >= 0 && var1 < var3.length) {
         Object var4 = var3[var1];
         return (T)(var4 == null ? var2 : var4);
      } else {
         throw new IllegalStateException("Invalid get valueId " + var1 + " (" + var3.length + ")");
      }
   }

   @NotNull
   @Generated
   public BridgeExtension method8() {
      return this.field2;
   }

   @Nullable
   @Generated
   public Object[] getValues() {
      return this.values;
   }

   public class EntityLabelValuesEvent {
      private final EntityPlayerBridge field1;
      private final Object[] field2;
      private final Bridge5_16 field3;
      private final BatchingBufferSourceBridge field4;
      private final int field5;

      public EntityLabelValuesEvent(EntityPlayerBridge var1, Object[] var2, Bridge5_16 var3, BatchingBufferSourceBridge var4, int value) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
         this.field5 = value;
      }

      public <T> T method1(int var1, T var2) {
         Object[] var3 = this.field2;
         if (var3 == null) {
            return (T)var2;
         } else if (var1 >= 0 && var1 < var3.length) {
            Object var4 = var3[var1];
            return (T)(var4 == null ? var2 : var4);
         } else {
            throw new IllegalStateException("Invalid get valueId " + var1 + " (" + var3.length + ")");
         }
      }

      public EntityPlayerBridge method2() {
         return this.field1;
      }

      public Object[] values() {
         return this.field2;
      }

      public Bridge5_16 method3() {
         return this.field3;
      }

      public BatchingBufferSourceBridge method4() {
         return this.field4;
      }

      public int method5() {
         return this.field5;
      }
   }

   @Annotation2(min = 16)
   public static class EntityLabelLinesEvent extends EntityLabelRenderEvent {
      private final SupplierExtension<List<Component>> field3;
      private String[] field4;
      private boolean field5;

      public EntityLabelLinesEvent(@NotNull BridgeExtension var1, @NotNull SupplierExtension<List<Component>> var2) {
         super(var1, null);
         this.field3 = var2;
      }

      public int method3() {
         return this.field3.get().size();
      }

      @NotNull
      public Component method2(int var1) {
         return this.field3.get().get(var1);
      }

      public void method3(int var1, @NotNull Component var2) {
         this.field3.get().set(var1, var2);
         this.field5 = true;
         if (this.field4 != null) {
            this.field4[var1] = null;
         }
      }

      @NotNull
      public String method4(int var1) {
         if (this.field4 == null) {
            this.field4 = new String[this.field3.get().size()];
         }

         if (this.field4[var1] == null) {
            this.field4[var1] = AdventureTextBridge.getTextContent(this.method2(var1));
         }

         return this.field4[var1];
      }

      public void method5(@NotNull Component var1) {
         this.field3.get().add(0, var1);
         this.field5 = true;
         this.field4 = null;
      }

      @NotNull
      public List<Component> getLines() {
         return Collections.unmodifiableList(this.field3.get());
      }

      @Generated
      public boolean isModified() {
         return this.field5;
      }
   }
}
