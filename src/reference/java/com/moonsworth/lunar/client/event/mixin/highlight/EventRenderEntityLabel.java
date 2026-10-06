package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.MultiBufferSourceBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.client.util.concurrent.SupplierExtension;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Collections;
import java.util.List;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@VersionGate(min = 16)
public abstract class EventRenderEntityLabel extends com.moonsworth.lunar.client.event.CancellableEvent {
   private static int field1 = 0;
   @NotNull
   private final BridgeExtension field2;
   @Nullable
   private Object[] values;

   public static synchronized int method1() {
      return field1++;
   }

   private EventRenderEntityLabel(@NotNull BridgeExtension bridgeextension1, @Nullable Object[] items2) {
      this.field2 = bridgeextension1;
      this.values = items2;
   }

   @Nullable
   public EntityPlayerBridge method2() {
      return method3(this.method8());
   }

   @Nullable
   public static EntityPlayerBridge method3(BridgeExtension bridgeextension0) {
      while (!(bridgeextension0 instanceof EntityPlayerBridge)) {
         bridgeextension0 = bridgeextension0.bridge$getVehicle();
         if (bridgeextension0 instanceof EntityPlayerBridge bridgeextension2221) {
            return bridgeextension2221;
         }

         if (bridgeextension0 == null) {
            break;
         }
      }

      return null;
   }

   public boolean method4() {
      BridgeExtension bridgeextension1 = this.method8();
      BridgeExtension bridgeextension2 = bridgeextension1.bridge$getVehicle();

      while (bridgeextension2 != null && !(bridgeextension2 instanceof EntityPlayerBridge)) {
         bridgeextension2 = bridgeextension2.bridge$getVehicle();
      }

      if (bridgeextension2 == null) {
         return true;
      }

      BridgeExtension bridgeextension3 = method5(bridgeextension2, null);
      return bridgeextension3 == null || bridgeextension3 == bridgeextension1;
   }

   @Nullable
   private static BridgeExtension method5(@NotNull BridgeExtension bridgeextension0, @Nullable BridgeExtension bridgeextension1) {
      List list2 = bridgeextension0.bridge$getPassengers();

      for (int index3 = 0; index3 < list2.size(); index3++) {
         BridgeExtension bridgeextension4 = (BridgeExtension)list2.get(index3);
         if (bridgeextension4.bridge$isTextDisplay()) {
            if (bridgeextension1 == null || bridgeextension4.bridge$getPosY() > bridgeextension1.bridge$getPosY()) {
               bridgeextension1 = bridgeextension4;
            }

            bridgeextension1 = method5(bridgeextension4, bridgeextension1);
         }
      }

      return bridgeextension1;
   }

   public void method6(int index1, @Nullable Object obj2) {
      if (index1 >= 0 && index1 < field1) {
         Object[] items3 = this.values;
         if (items3 == null) {
            items3 = new Object[field1];
         }

         items3[index1] = obj2;
         this.values = items3;
      } else {
         throw new IllegalStateException("Invalid set valueId " + index1 + " (" + field1 + ")");
      }
   }

   public <T> T method7(int index1, T value2) {
      Object[] items3 = this.values;
      if (items3 == null) {
         return (T)value2;
      } else if (index1 >= 0 && index1 < items3.length) {
         Object obj4 = items3[index1];
         return (T)(obj4 == null ? value2 : obj4);
      } else {
         throw new IllegalStateException("Invalid get valueId " + index1 + " (" + items3.length + ")");
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

   public class EventRenderEntityLabelValues {
      private final EntityPlayerBridge field1;
      private final Object[] field2;
      private final Bridge5_16 field3;
      private final MultiBufferSourceBridge field4;
      private final int field5;

      public EventRenderEntityLabelValues(EntityPlayerBridge bridgeextension2221, Object[] items2, Bridge5_16 bridge5_163, MultiBufferSourceBridge bridge174, int value) {
         this.field1 = bridgeextension2221;
         this.field2 = items2;
         this.field3 = bridge5_163;
         this.field4 = bridge174;
         this.field5 = value;
      }

      public <T> T method1(int index1, T value2) {
         Object[] items3 = this.field2;
         if (items3 == null) {
            return (T)value2;
         } else if (index1 >= 0 && index1 < items3.length) {
            Object obj4 = items3[index1];
            return (T)(obj4 == null ? value2 : obj4);
         } else {
            throw new IllegalStateException("Invalid get valueId " + index1 + " (" + items3.length + ")");
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

      public MultiBufferSourceBridge method4() {
         return this.field4;
      }

      public int method5() {
         return this.field5;
      }
   }

   @VersionGate(min = 16)
   public static class EventRenderEntityLabelLines extends EventRenderEntityLabel {
      private final SupplierExtension<List<Component>> field3;
      private String[] field4;
      private boolean field5;

      public EventRenderEntityLabelLines(@NotNull BridgeExtension bridgeextension1, @NotNull SupplierExtension<List<Component>> supplierextension2) {
         super(bridgeextension1, null);
         this.field3 = supplierextension2;
      }

      public int method3() {
         return ((List)this.field3.get()).size();
      }

      @NotNull
      public Component method2(int index1) {
         return (Component)((List)this.field3.get()).get(index1);
      }

      public void method3(int index1, @NotNull Component component2) {
         ((List)this.field3.get()).set(index1, component2);
         this.field5 = true;
         if (this.field4 != null) {
            this.field4[index1] = null;
         }
      }

      @NotNull
      public String method4(int index1) {
         if (this.field4 == null) {
            this.field4 = new String[((List)this.field3.get()).size()];
         }

         if (this.field4[index1] == null) {
            this.field4[index1] = TextBridge.getTextContent(this.method2(index1));
         }

         return this.field4[index1];
      }

      public void method5(@NotNull Component component1) {
         ((List)this.field3.get()).add(0, component1);
         this.field5 = true;
         this.field4 = null;
      }

      @NotNull
      public List<Component> getLines() {
         return Collections.unmodifiableList((List<? extends Component>)this.field3.get());
      }

      @Generated
      public boolean isModified() {
         return this.field5;
      }
   }
}
