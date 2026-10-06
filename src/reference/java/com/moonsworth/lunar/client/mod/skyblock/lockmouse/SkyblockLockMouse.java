package com.moonsworth.lunar.client.mod.skyblock.lockmouse;

import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.EquippedItemListener;
import com.moonsworth.lunar.client.event.input.EventMouseMove;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventKeybind;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.DoubleOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.Nullable;

public class SkyblockLockMouse extends AbstractFeature {
   EquippedItemListener field8 = (EquippedItemListener)this.method63(EquippedItemListener.class);
   private final ModifierKeybindOption field9 = (ModifierKeybindOption)OptionFactory.method18("lockMouseKeybind").method31();
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("lockMouseHud").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("lockMouseInGarden").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("lockMouseOnBarnTp").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field13 = (ToggleOption)OptionFactory.method7("skyblockAutoMouseLock").method31();
   private final ModifierKeybindOption field14 = (ModifierKeybindOption)OptionFactory.method18("slowMouseKeybind").method31();
   private final DoubleOption field15 = (DoubleOption)((com.moonsworth.lunar.client.config.option.DoubleOption.Data)((com.moonsworth.lunar.client.config.option.DoubleOption.Data)OptionFactory.method1(
               "lockMouseSensitivity"
            )
            .OIRHOOIICOCIOOHICRRRICORIHHIHC(0.05))
         .method8(0.01, 0.1))
      .method31();
   private boolean field16;
   private boolean field17;

   public SkyblockLockMouse(Skyblock skyblock1) {
      super(true);
      this.method3(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method3(ModTraits.field1, new SkyblockLockMouse.Data());
      this.method3(ModTraits.field17, ModCategories.method2(SettingsPage.FARMING));
      this.method51(this::onDisable);
      this.handle(EventMouseMove.class, this::method1);
      this.handle(EventKeybind.class, this::method2);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method4);
      this.handle(EventWorldChange.class, this::method5);
      this.handle(com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.EventEquippedItemChange.class, this::method3);
      this.field10.CICORRHIOIIOORRRICCORIOIOCIHII(arg1x -> {
         if (arg1x) {
            ((MixinCore9Extension)this.method7(ModTraits.field1)).method16(15.0F, 80.0F);
         } else {
            ((MixinCore9Extension)this.method7(ModTraits.field1)).method16(0.0F, 0.0F);
         }
      });
   }

   private void method1(EventMouseMove highlightimpl141) {
      if (this.field17) {
         highlightimpl141.cancel();
      } else if (this.field16) {
         double value2 = (Double)this.field15.get() / 10.0;
         highlightimpl141.method3((float)(highlightimpl141.method1() * value2));
         highlightimpl141.method4((float)(highlightimpl141.method2() * value2));
      }
   }

   private void method2(EventKeybind highlightimpl1) {
      if (!(Boolean)this.field11.get() || IslandUtils.getIsland() == SkyblockIsland.GARDEN) {
         NotificationManager fogimpl2 = Ref.method4().method69();
         if (highlightimpl1.method3(this.field9)) {
            this.field17 = !this.field17;
            this.field16 = false;
            if (this.field17) {
               fogimpl2.method3(NotificationManager.method15("lockedMouseMovement", new Object[0]));
            } else {
               fogimpl2.method3(NotificationManager.method15("unlockedMouseMovement", new Object[0]));
            }
         } else if (highlightimpl1.method3(this.field14)) {
            this.field16 = !this.field16;
            this.field17 = false;
            if (this.field16) {
               fogimpl2.method3(NotificationManager.method15("slowedMouseMovement", new Object[0]));
            } else {
               fogimpl2.method3(NotificationManager.method15("unslowedMouseMovement", new Object[0]));
            }
         }
      }
   }

   private void method3(com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.EventEquippedItemChange highlightimpl1) {
      if ((Boolean)this.field13.get()) {
         if (!(Boolean)this.field11.get() || IslandUtils.getIsland() == SkyblockIsland.GARDEN) {
            this.field17 = this.field8.method6();
         }
      }
   }

   private void method4(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      if ((Boolean)this.field12.get()) {
         if (IslandUtils.getIsland() == SkyblockIsland.GARDEN) {
            String text2 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
            if (text2.equals("Teleported you to The Barn!")) {
               this.field17 = false;
               this.field16 = false;
            }
         }
      }
   }

   private void onDisable() {
      this.field17 = false;
      this.field16 = false;
   }

   private void method5(EventWorldChange data31) {
      this.field17 = false;
      this.field16 = false;
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_LOCK_MOUSE";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> arg1x.method9(
            new ClientOption[]{this.field9, this.field10, this.field11, this.field12, this.field13, this.field14, this.field15}
         )
      );
   }

   private class Data extends TypedHudRenderer<TextComponent> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.BOTTOM_LEFT);
      }

      public HudSize method15() {
         return HudSize.method1(10, 15, 30, 20, 80, 200);
      }

      @Nullable
      public TextComponent method2(boolean flag1) {
         if (SkyblockLockMouse.this.field17) {
            return Component.text(SkyblockLockMouse.this.method1("mouseLocked", new Object[0]));
         } else if (SkyblockLockMouse.this.field16) {
            return Component.text(SkyblockLockMouse.this.method1("mouseSlowed", new Object[0]));
         } else {
            return flag1 ? Component.text(SkyblockLockMouse.this.method1("mouseLocked", new Object[0])) : Component.empty();
         }
      }

      public boolean method4(boolean flag1) {
         return !SkyblockLockMouse.this.field10.get() ? false : super.method4(flag1);
      }
   }
}
