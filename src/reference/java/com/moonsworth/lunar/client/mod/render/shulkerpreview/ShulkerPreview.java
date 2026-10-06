package com.moonsworth.lunar.client.mod.render.shulkerpreview;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3Extension3;
import com.moonsworth.lunar.bridge.RenderItemBridge;
import com.moonsworth.lunar.bridge.ItemBlockBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.GlBlendFactor;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.DataComponentTypes;
import com.moonsworth.lunar.bridge.ContainerItemsComponentBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.event.player.EventInventoryUpdate;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.config.Config;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.Generated;

public class ShulkerPreview extends AbstractFeature {
   private static final ResourceLocationBridge field8 = ResourceLocationBridge.create("minecraft", "textures/gui/container/shulker_box.png");
   private final ToggleOption field9 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("alwaysPreview")
      .method31();
   private final ModifierKeybindOption field10 = (ModifierKeybindOption)com.moonsworth.lunar.client.config.option.OptionFactory.method18("previewKey")
      .method5(KeyCode.KEY_TAB)
      .method11()
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("coloredShulkerPreview")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final Map<ItemStackBridge, ItemStackBridge[]> field12 = new HashMap<>();

   public ShulkerPreview() {
      super(true);
      this.method2(ModTraits.field18, arg0 -> arg0.method5(new Config[]{Config.field1, Config.field2}));
      this.method2(com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre.class, this::method1, 200);
      this.handle(EventInventoryUpdate.class, arg1 -> this.field12.clear());
   }

   public String getId() {
      return "SHULKER_PREVIEW";
   }

   private void method1(com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre data21) {
      Optional optional2 = data21.method1();
      if (optional2.isPresent()
         && ((ItemStackBridge)optional2.get()).bridge$getItem() instanceof ItemBlockBridge bridge6extension83
         && bridge6extension83.bridge$getBlockFromItem().isPresent()
         && bridge6extension83.bridge$getBlockFromItem().get() instanceof Bridge3Extension3 bridge3extension34
         && ((Boolean)this.field9.get() || this.field10.isKeyDown())) {
         ItemStackBridge bridgeextension_410 = (ItemStackBridge)optional2.get();
         ItemStackBridge[] items6;
         if (this.field12.containsKey(bridgeextension_410)) {
            items6 = this.field12.get(bridgeextension_410);
         } else {
            ContainerItemsComponentBridge mixinhelper4_47 = (ContainerItemsComponentBridge)bridgeextension_410.bridge$getDataComponent(DataComponentTypes.field53);
            if (mixinhelper4_47 == null) {
               items6 = new ItemStackBridge[0];
            } else {
               List list8 = mixinhelper4_47.bridge$items();
               items6 = list8.toArray(new ItemStackBridge[0]);
            }

            this.field12.put(bridgeextension_410, items6);
         }

         this.method2(data21.method2(), data21.getX() + 8, data21.getY() - 8, bridge3extension34, (ItemStackBridge)optional2.get());
         this.method3(data21.method2(), items6, data21.getX() + 8, data21.getY() - 8);
         data21.setCancelled(true);
      }
   }

   private void method2(MixinHelper_4 mixinhelper_41, int number2, int number3, Bridge3Extension3 bridge3extension34, ItemStackBridge bridgeextension_45) {
      mixinhelper_41.method44(arg0 -> arg0.method29().method19());
      int number6 = -1;
      if ((Boolean)this.field11.get()) {
         number6 = bridge3extension34.bridge$getColor();
         if (number6 == -16777216) {
            number6 = -14342875;
         }
      }

      LcuiScreen.z = 400.0;
      LcuiScreen.method37(mixinhelper_41, field8, number2, number3, 256.0F, 256.0F, 0.0F, 0.0F, 256.0F, 74.0F, number6);
      LcuiScreen.method37(mixinhelper_41, field8, number2, number3 + 74, 256.0F, 256.0F, 0.0F, 160.0F, 256.0F, 166.0F, number6);
      LcuiScreen.z = 0.0;
      mixinhelper_41.push();
      mixinhelper_41.method44(arg0 -> arg0.method29().method18());
      mixinhelper_41.method38(0.0F, 0.0F, 400.0F);
      mixinhelper_41.method18(Ref.method10(), bridgeextension_45.bridge$getDisplayName(), number2 + 8, number3 + 6, -1, true);
      mixinhelper_41.pop();
   }

   private void method3(MixinHelper_4 mixinhelper_41, ItemStackBridge[] items2, int number3, int number4) {
      RenderItemBridge bridge5_195 = this.mc.bridge$getRenderItem();
      mixinhelper_41.method44(arg0 -> {
         arg0.method29().method22();
         Bridge.method14().method2();
      });
      mixinhelper_41.push();
      mixinhelper_41.method38(0.0F, 0.0F, 400.0F);
      float value6 = bridge5_195.bridge$getZLevel();
      bridge5_195.bridge$setZLevel(400.0F);
      mixinhelper_41.method44(arg0 -> arg0.method29().method33());

      for (int index7 = 0; index7 < items2.length; index7++) {
         ItemStackBridge bridgeextension_48 = items2[index7];
         if (bridgeextension_48 != null) {
            int number9 = number3 + 8 + index7 % 9 * 18;
            int number10 = number4 + 20 + index7 / 9 * 18 - 2;
            mixinhelper_41.method36(bridgeextension_48, number9, number10, true, Ref.method3());
         }
      }

      bridge5_195.bridge$setZLevel(value6);
      mixinhelper_41.method44(arg0 -> {
         Bridge.method14().method3();
         AbstractRenderContext bridgeextension_91x = arg0.method29();
         bridgeextension_91x.method23();
         bridgeextension_91x.method15();
         bridgeextension_91x.method2(GlBlendFactor.GL_SRC_ALPHA, GlBlendFactor.GL_ONE_MINUS_SRC_ALPHA);
         bridgeextension_91x.method33();
      });
      mixinhelper_41.pop();
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> {
         arg1x.method9(new ClientOption[]{this.field9});
         arg1x.method9(new ClientOption[]{this.field10}).method3(this.field9::get);
         arg1x.method9(new ClientOption[]{this.field11});
      });
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field4}).method11(this);
   }

   @Generated
   public ToggleOption method13() {
      return this.field9;
   }

   @Generated
   public ModifierKeybindOption method14() {
      return this.field10;
   }
}
