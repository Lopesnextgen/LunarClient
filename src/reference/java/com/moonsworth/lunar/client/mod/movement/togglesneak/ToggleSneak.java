package com.moonsworth.lunar.client.mod.movement.togglesneak;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.PlayerCapabilitiesBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.GameOptionsBridge;
import com.moonsworth.lunar.bridge.KeyBindingBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.event.screen.EventScreenUpdate;
import com.moonsworth.lunar.client.event.player.EventPlayerLivingUpdate;
import com.moonsworth.lunar.client.event.input.InputAction;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventKeybind;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseButton;
import com.moonsworth.lunar.client.network.server.ServerBrandWatcher;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.config.option.IntegerOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.movement.togglesneak.ToggleSneakHud;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.config.Config;
import java.util.List;
import lombok.Generated;

public class ToggleSneak extends AbstractFeature {
   private final GameOptionsBridge field8;
   protected final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("toggleSprint").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption field10 = (ToggleOption)OptionFactory.method7("sprintKeybindOverride").method31();
   protected final SimpleKeybindOption field11 = (SimpleKeybindOption)OptionFactory.method17("keybindSprint").method12().method31();
   protected final ToggleOption field12 = (ToggleOption)OptionFactory.method7("toggleSneak").method31();
   protected final ToggleOption field13 = (ToggleOption)OptionFactory.method7("sneakKeybindOverride").method31();
   protected final SimpleKeybindOption field14 = (SimpleKeybindOption)OptionFactory.method17("keybindSneak").method12().method31();
   protected final ToggleOption field15 = (ToggleOption)OptionFactory.method7("toggleSneakContainer").method31();
   protected final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("flyBoost").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final IntegerOption field17 = (IntegerOption)((Data)((Data)OptionFactory.method4("flyBoostAmount").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(4))
         .method7(2, 8))
      .method31();
   protected final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("doubleTap").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private boolean field19;
   private boolean field20;
   private Float field21;
   private boolean field22;
   private boolean sneak;
   private boolean flying;
   private long field23;
   private long field24;
   private boolean field25;

   public ToggleSneak() {
      super(true);
      this.field8 = Bridge.method9().bridge$getGameSettings();
      this.handle(EventKeybind.class, this::method9);
      this.handle(EventScreenUpdate.class, this::method4);
      this.handle(EventPlayerLivingUpdate.class, this::method3);
      this.handle(EventMouseButton.class, this::method8);
      if (Bridge.getMinecraftVersion() == Config.field6) {
         this.handle(EventTick.class, this::method2);
      }
   }

   public String getId() {
      return "TOGGLE_SNEAK";
   }

   protected List<Framework7Extension> method9() {
      return List.of(new ToggleSneakHud(this));
   }

   private void method2(EventTick highlightimpl21) {
      if (!this.field19) {
         this.method11(this.field8.bridge$keyBindSprint(), this.field22);
         this.field19 = true;
      }

      if (!this.field20) {
         this.method11(this.field8.bridge$keyBindSneak(), this.sneak);
         this.field20 = true;
      }
   }

   private void method3(EventPlayerLivingUpdate highlightimpl9_21) {
      Bridge5Extension_5 bridge5extension_52 = this.mc.bridge$getPlayer();
      PlayerCapabilitiesBridge bridge3_323 = bridge5extension_52.bridge$getPlayerCapabilities();
      if (bridge3_323.bridge$isFlying()) {
         this.flying = true;
      } else if (this.flying) {
         if (this.field22) {
            this.field8.bridge$keyBindSprint().bridge$setKeyBindPressed(this.field22);
         }

         this.flying = false;
      }

      boolean flag4 = bridge3_323.bridge$isCreativeMode() || this.mc.bridge$getPlayerController().bridge$isSpectator();
      boolean flag5 = Bridge.method18().method1(this.mc.bridge$getGameSettings().bridge$keyBindSprint().bridge$getKey());
      boolean flag6 = bridge3_323.bridge$isFlying();
      if ((Boolean)this.field16.get() && flag4 && flag6 && flag5) {
         if (this.field21 == null) {
            this.field21 = bridge3_323.bridge$getFlySpeed();
         }

         bridge3_323.bridge$setFlySpeed(0.05F * ((Integer)this.field17.get()).intValue());
         if (bridge5extension_52.bridge$getMovementInput().bridge$isSneaking()) {
            bridge5extension_52.bridge$setMotionY(bridge5extension_52.bridge$getMotionY() - 0.15 * ((Integer)this.field17.get()).intValue());
         }

         if (bridge5extension_52.bridge$getMovementInput().bridge$isJumping()) {
            bridge5extension_52.bridge$setMotionY(bridge5extension_52.bridge$getMotionY() + 0.15 * ((Integer)this.field17.get()).intValue());
         }
      }

      if (this.field21 != null && (!flag5 || !flag6)) {
         bridge3_323.bridge$setFlySpeed(this.field21);
         this.field21 = null;
      }
   }

   private void method4(EventScreenUpdate highlightimpl161) {
      if ((Boolean)this.field9.get()) {
         this.field8.bridge$keyBindSprint().bridge$setKeyBindPressed(this.field22);
      }

      if ((Boolean)this.field12.get() && this.method13()) {
         this.field8.bridge$keyBindSneak().bridge$setKeyBindPressed(this.sneak);
      }

      if (highlightimpl161.method1() != null && !(highlightimpl161.method1() instanceof Bridge5Extension62) && this.field8.bridge$keyBindSneak().bridge$isKeyDown() && !this.method15()
         )
       {
         this.field8.bridge$keyBindSneak().bridge$setKeyBindPressed(false);
      }
   }

   private boolean method13() {
      return this.sneak != this.field8.bridge$keyBindSneak().bridge$isKeyDown();
   }

   private boolean method14() {
      return this.field22 != this.field8.bridge$keyBindSprint().bridge$isKeyDown();
   }

   private boolean method15() {
      return (Boolean)this.field15.get() && !ServerBrandWatcher.method8(KeystrokesType.HYPIXEL);
   }

   private void method8(EventMouseButton highlightimpl31) {
      if (highlightimpl31.method2() != -1) {
         KeyCode bridgetype_82 = KeyCode.valueOf("KEY_MOUSE" + (highlightimpl31.method2() + 1));
         if (this.method10(highlightimpl31.method4(), bridgetype_82)) {
            highlightimpl31.cancel();
         }
      }
   }

   private void method9(EventKeybind highlightimpl1) {
      if (this.method10(highlightimpl1.method11(), highlightimpl1.method10())) {
         highlightimpl1.cancel();
      }
   }

   private boolean method10(InputAction highlighttype1, KeyCode bridgetype_82) {
      if (Ref.method8() == null || Ref.method4().method40().method64().method13()) {
         return false;
      }

      if (Ref.method11() != null) {
         this.field23 = 0L;
         this.field24 = 0L;
         this.field25 = false;
         return this.sneak && this.method15() && bridgetype_82 == this.method17();
      }

      if (highlighttype1 == InputAction.DOWN) {
         if (bridgetype_82 == this.method17() && (Boolean)this.field12.get()) {
            this.field23 = System.currentTimeMillis();
         }

         if (bridgetype_82 == this.method16() && (Boolean)this.field9.get()) {
            this.field24 = System.currentTimeMillis();
            this.field25 = true;
         }

         return false;
      } else {
         if (bridgetype_82 == this.method16() && (Boolean)this.field9.get()) {
            this.field25 = false;
         }

         if (!Ref.method7().bridge$getPlayerCapabilities().bridge$isFlying()
            && bridgetype_82 == this.method17()
            && (Boolean)this.field12.get()
            && System.currentTimeMillis() - this.field23 <= 200L) {
            this.sneak = !this.sneak;
            if (Bridge.getMinecraftVersion() == Config.field6) {
               this.field20 = false;
            } else {
               this.method11(this.field8.bridge$keyBindSneak(), this.sneak);
            }

            return true;
         } else if (!this.mc.bridge$getPlayer().bridge$getPlayerCapabilities().bridge$isFlying()
            && bridgetype_82 == this.method16()
            && (Boolean)this.field9.get()
            && System.currentTimeMillis() - this.field24 <= 400L) {
            this.field22 = !this.field22;
            if (Bridge.getMinecraftVersion() == Config.field6) {
               this.field19 = false;
            } else {
               this.method11(this.field8.bridge$keyBindSprint(), this.field22);
            }

            return true;
         } else {
            return false;
         }
      }
   }

   private void method11(KeyBindingBridge mixinhelper_151, boolean flag2) {
      mixinhelper_151.bridge$setKeyBindState(false);
      mixinhelper_151.bridge$setKeyBindPressed(flag2);
   }

   private KeyCode method16() {
      return this.field10.get() ? this.field11.method8() : this.field8.bridge$keyBindSprint().bridge$getKey();
   }

   private KeyCode method17() {
      return this.field13.get() ? this.field14.method8() : this.field8.bridge$keyBindSneak().bridge$getKey();
   }

   public void method19() {
      this.field22 = (Boolean)this.field9.get();
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field9,
               arg1xx -> arg1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.field10, arg1xxx -> arg1xxx.method9(new ClientOption[]{this.field11})
               )
            );
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field12, arg1xx -> {
               arg1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field13, arg1xxx -> arg1xxx.method9(new ClientOption[]{this.field14}));
               arg1xx.method9(new ClientOption[]{this.field15});
            });
            arg1x.method9(new ClientOption[]{this.field18});
         }
      );
      lightingextension231.method1("flyBoostOptions", arg1x -> arg1x.method9(new ClientOption[]{this.field16, this.field17}));
   }

   protected ModDetails method20() {
      return ModDetails.method7()
         .method1(new ModCategory[]{ModCategory.field4})
         .method2(new String[]{"Toggle Sprint", "ToggleSprint"})
         .method11(this);
   }

   @Generated
   public GameOptionsBridge method21() {
      return this.field8;
   }

   @Generated
   public ToggleOption method22() {
      return this.field9;
   }

   @Generated
   public ToggleOption method23() {
      return this.field10;
   }

   @Generated
   public SimpleKeybindOption method24() {
      return this.field11;
   }

   @Generated
   public ToggleOption method25() {
      return this.field12;
   }

   @Generated
   public ToggleOption method26() {
      return this.field13;
   }

   @Generated
   public SimpleKeybindOption method27() {
      return this.field14;
   }

   @Generated
   public ToggleOption method28() {
      return this.field15;
   }

   @Generated
   public ToggleOption method29() {
      return this.field16;
   }

   @Generated
   public IntegerOption method30() {
      return this.field17;
   }

   @Generated
   public ToggleOption method34() {
      return this.field18;
   }

   @Generated
   public boolean method35() {
      return this.field19;
   }

   @Generated
   public boolean method36() {
      return this.field20;
   }

   @Generated
   public Float method37() {
      return this.field21;
   }

   @Generated
   public boolean method38() {
      return this.field22;
   }

   @Generated
   public boolean isSneak() {
      return this.sneak;
   }

   @Generated
   public boolean isFlying() {
      return this.flying;
   }

   @Generated
   public long method39() {
      return this.field23;
   }

   @Generated
   public long method40() {
      return this.field24;
   }

   @Generated
   public boolean method41() {
      return this.field25;
   }
}
