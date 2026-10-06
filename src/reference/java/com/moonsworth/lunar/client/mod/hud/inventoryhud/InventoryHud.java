package com.moonsworth.lunar.client.mod.hud.inventoryhud;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.overlay.HudColorOverride;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.mod.player.inventorymod.InventoryMods;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Arrays;
import java.util.List;

public class InventoryHud extends AbstractFeature {
   private static final ResourceLocationBridge inventoryTexture = ResourceLocationBridge.create("textures/gui/container/inventory.png");
   private final ToggleOption mcBackground = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("mcBackground").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showEmpty = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showEmpty").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption background = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("background").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption grid = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("grid").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption gridColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "gridColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(Integer.MIN_VALUE))
      .method31();
   private final ToggleOption backgroundColor = (ToggleOption)OptionFactory.method7("backgroundColor").method31();
   private final ColorOption color = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "color"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ItemStackBridge[] items = new ItemStackBridge[27];
   private boolean empty = true;

   public InventoryHud(InventoryMods inventorymod1) {
      super(false);
      this.method6(ModTraits.field16, ChildModBinding.method3(inventorymod1));
      this.method6(ModTraits.field1, new InventoryHud.Data());
      this.method14(EventTick.class, this::updateItems);
   }

   public String getId() {
      return "INVENTORY_HUD";
   }

   public void registerOptions(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.showEmpty});
      lightingextension231.method7(
         this.background,
         arg1x -> {
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.mcBackground});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.grid, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.gridColor}))
               .method3(this.mcBackground::get);
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.backgroundColor, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.color}));
         }
      );
      lightingextension231.method9(new ClientOption[]{((MixinCore9Extension)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field1)).method9()});
   }

   private void updateItems() {
      if (this.mc.bridge$getPlayer() != null && this.mc.bridge$getPlayer().bridge$getInventory() != null) {
         List list1 = this.mc.bridge$getPlayer().bridge$getInventory().bridge$getMainInventory();
         this.empty = true;

         for (int index2 = 9; index2 < list1.size(); index2++) {
            ItemStackBridge bridgeextension_43 = (ItemStackBridge)list1.get(index2);
            if (bridgeextension_43 != null && (bridgeextension_43.bridge$isEmpty() || bridgeextension_43.bridge$getStackSize() < 1)) {
               bridgeextension_43 = null;
            }

            this.items[index2 - 9] = bridgeextension_43;
            if (bridgeextension_43 != null) {
               this.empty = false;
            }
         }
      } else {
         Arrays.fill(this.items, null);
         this.empty = true;
      }
   }

   private class Data extends HudElementBase {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT);
      }

      public void render(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         MixinHelper_4 mixinhelper_45 = highlightimpl1.method2();
         mixinhelper_45.push();
         mixinhelper_45.method38(Math.round(value2), Math.round(value3), 0.0F);
         if ((Boolean)InventoryHud.this.background.get()) {
            float value6 = this.getWidth();
            float value7 = this.getHeight();
            if ((Boolean)InventoryHud.this.mcBackground.get()) {
               this.registerOptions(mixinhelper_45, value6, value7);
            } else {
               int number8 = InventoryHud.this.backgroundColor.get() ? InventoryHud.this.color.method14(0.0F) : Integer.MIN_VALUE;
               LcuiScreen.method94(mixinhelper_45, 0.0F, 0.0F, value6, value7, number8);
               if ((Boolean)InventoryHud.this.grid.get()) {
                  int number9 = InventoryHud.this.gridColor.method14(0.0F);
                  float value10 = 317.5F;
                  LcuiScreen.method68(mixinhelper_45, 2.0F, value10, 36.0F, number9);
                  LcuiScreen.method68(mixinhelper_45, 2.0F, value10, value7 - 36.0F, number9);

                  for (int index11 = 1; index11 < 9; index11++) {
                     LcuiScreen.method87(mixinhelper_45, index11 * 35.5F, 2.0F, value7 - 2.0F, number9);
                  }
               }
            }
         }

         if (InventoryHud.this.empty) {
            mixinhelper_45.pop();
         } else {
            for (int index15 = 0; index15 < InventoryHud.this.items.length; index15++) {
               ItemStackBridge bridgeextension_416 = InventoryHud.this.items[index15];
               if (bridgeextension_416 != null && !bridgeextension_416.bridge$isEmpty() && bridgeextension_416.bridge$getStackSize() >= 1) {
                  mixinhelper_45.push();

                  try {
                     int number17 = index15 % 9;
                     int number18 = index15 / 9;
                     mixinhelper_45.scale(2.0F, 2.0F, 1.0F);
                     mixinhelper_45.method38(1.0F + number17 * 17.75F, 0.5F + number18 * 18.0F, 0.0F);
                     mixinhelper_45.method44(arg0 -> {
                        arg0.method29().method22();
                        Bridge.method14().method2();
                     });
                     mixinhelper_45.method36(bridgeextension_416, 0, 0, true, Ref.method3());
                     mixinhelper_45.method44(arg0 -> {
                        Bridge.method14().method3();
                        arg0.method29().method23();
                        arg0.method29().method15();
                     });
                  } finally {
                     mixinhelper_45.pop();
                  }
               }
            }

            mixinhelper_45.pop();
         }
      }

      private void registerOptions(MixinHelper_4 mixinhelper_41, float value2, float value3) {
         OverlayMod overlaymod4 = Ref.method4().method40().method84();
         if (!overlaymod4.isContainerTintEnabled()) {
            this.render(mixinhelper_41, value2, value3);
         } else {
            HudColorOverride.method1(overlaymod4.getContainerTint());

            try {
               this.render(mixinhelper_41, value2, value3);
            } finally {
               HudColorOverride.method2();
            }
         }
      }

      private void render(MixinHelper_4 mixinhelper_41, float value2, float value3) {
         int number4 = InventoryHud.this.backgroundColor.get() ? InventoryHud.this.color.method14(0.0F) : -570425345;
         if (Ref.MC_VERSION < 30) {
            number4 = HudColorOverride.method5(number4);
         }

         mixinhelper_41.push();
         mixinhelper_41.method40(value2 / 162.0F, value3 / 54.0F);
         mixinhelper_41.method25(InventoryHud.inventoryTexture, 0.0F, 0.0F, 7.0F, 83.0F, 162.0F, 54.0F, 256.0F, 256.0F, number4);
         mixinhelper_41.pop();
      }

      public boolean method31() {
         return false;
      }

      public boolean shouldRender(boolean flag1) {
         boolean flag2 = flag1 || (Boolean)InventoryHud.this.showEmpty.get() || !InventoryHud.this.empty;
         if (Ref.MC_VERSION >= 36) {
            GuiScreenBridge bridge5extension63 = InventoryHud.this.mc.bridge$getCurrentScreen();
            if (bridge5extension63 != null && !(bridge5extension63 instanceof Bridge5Extension62)) {
               flag2 = false;
            }
         }

         if (!flag2) {
            this.shouldRender(0.0F, 0.0F);
            return false;
         } else {
            this.shouldRender(320.0F, 107.0F);
            return true;
         }
      }

      public void registerOptions(RootSettingsBuilder lightingextension231) {
      }
   }
}
