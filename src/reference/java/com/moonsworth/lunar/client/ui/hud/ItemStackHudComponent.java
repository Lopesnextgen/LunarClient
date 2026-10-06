package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.function.Supplier;

public class ItemStackHudComponent implements HudComponent {
   private final HudComponentValue<Double> field1 = new HudComponentValue<>(1.0, false, true);
   private final HudComponentValue<Boolean> field2 = new HudComponentValue<>(false, false, true);
   private final HudComponentValue<Boolean> field3 = new HudComponentValue<>(false, false, true);
   private final HudComponentValue<Double> field4 = new HudComponentValue<>(0.5, false, true);
   private final HudComponentValue<ItemStackBridge> field5 = new HudComponentValue<>(null, true, true);
   private final TextHudComponent field6 = new TextHudComponent().method15(() -> this.field4.get() * this.field1.get());
   private boolean field7 = false;

   public ItemStackHudComponent() {
      this((ItemStackBridge)null);
   }

   public ItemStackHudComponent(ItemBridge bridge6_41) {
      this(Bridge.method8().method38(bridge6_41));
   }

   public ItemStackHudComponent(Bridge3_23 bridge3_231) {
      this(Bridge.method8().method39(bridge3_231));
   }

   public ItemStackHudComponent(ItemStackBridge bridgeextension_41) {
      this.field5.method1(bridgeextension_41);
   }

   public ItemStackHudComponent method1(ItemStackBridge bridgeextension_41) {
      this.field5.method1(bridgeextension_41);
      return this;
   }

   public ItemStackHudComponent method2(Supplier<ItemStackBridge> supplier1) {
      this.field5.method2(supplier1);
      return this;
   }

   public ItemStackHudComponent method3(ItemBridge bridge6_41) {
      this.field5.method1(Bridge.method8().method38(bridge6_41));
      return this;
   }

   public ItemStackHudComponent method4(Bridge3_23 bridge3_231) {
      this.field5.method1(Bridge.method8().method39(bridge3_231));
      return this;
   }

   public ItemStackHudComponent method5(String text1) {
      this.field7 = text1 != null;
      if (this.field7) {
         this.field6.method1(text1);
      }

      return this;
   }

   public ItemStackHudComponent method6(double value1) {
      this.field1.method1(value1);
      return this;
   }

   public ItemStackHudComponent method7(Supplier<Double> supplier1) {
      this.field1.method2(supplier1);
      return this;
   }

   public ItemStackHudComponent method8(double value1) {
      this.field4.method1(value1);
      return this;
   }

   public ItemStackHudComponent method9(boolean flag1) {
      this.field2.method1(flag1);
      return this;
   }

   public ItemStackHudComponent method10(boolean flag1) {
      this.field3.method1(flag1);
      return this;
   }

   public ItemStackHudComponent method11(Supplier<Boolean> supplier1) {
      this.field3.method2(supplier1);
      return this;
   }

   @Override
   public void clearCache() {
      this.field6.clearCache();
      this.field1.clearCache();
      this.field2.clearCache();
      this.field3.clearCache();
      this.field4.clearCache();
      this.field5.clearCache();
   }

   @Override
   public float getWidth() {
      return (float)(8.0 * this.field1.get());
   }

   @Override
   public float getHeight() {
      return (float)(8.0 * this.field1.get());
   }

   @Override
   public void method1(float value1, float value2, HudRenderContext mixincore43) {
      ItemStackBridge bridgeextension_44 = this.field5.get();
      if (bridgeextension_44 != null && !bridgeextension_44.bridge$isEmpty()) {
         float value5 = this.field1.get().floatValue();
         mixincore43.method6().push();
         mixincore43.method6().method38(value1, value2, 0.0F);
         mixincore43.method6().scale(value5 / 2.0F, value5 / 2.0F, 1.0F);
         mixincore43.method6().method38(8.0F, 8.0F, 0.0F);
         if (this.field2.get()) {
            if (Ref.MC_VERSION >= 6) {
               mixincore43.method6().scale(1.2F, 1.2F, 1.0F);
            } else {
               mixincore43.method6().scale(1.3F, 1.3F, 1.0F);
            }
         }

         Bridge.method14().method2();
         mixincore43.method6().method34(bridgeextension_44, -8, -8, Ref.method3());
         Bridge.method14().method3();
         mixincore43.method6().pop();
         if (this.field3.get()) {
            if (!this.field7) {
               this.field6.method1(bridgeextension_44.bridge$getStackSize() != 1 ? String.valueOf(bridgeextension_44.bridge$getStackSize()) : "");
            }

            this.field6.method1(value1 + this.getWidth() - this.field6.getWidth() + 1.0F, value2 + this.getHeight() - this.field6.getHeight() + 0.5F, mixincore43);
         }
      }
   }
}
