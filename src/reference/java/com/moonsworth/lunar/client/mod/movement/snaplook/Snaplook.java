package com.moonsworth.lunar.client.mod.movement.snaplook;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.snaplook.SnaplookPerspective;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption.Data;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import com.moonsworth.lunar.client.mod.movement.freelook.Freelook;

public class Snaplook extends AbstractFeature {
   private final SimpleKeybindOption field8 = (SimpleKeybindOption)((Data)((Data)((Data)OptionFactory.method17("thirdPersonKey")
               .method2(KeyCode.KEY_LMENU))
            .method18(this))
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final SimpleKeybindOption field9 = (SimpleKeybindOption)((Data)OptionFactory.method17("forwardPersonKey").CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private int field10;
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("smoothCamera").method4(true))
      .method31();
   private final ToggleOption field12 = (ToggleOption)OptionFactory.method7("snaplookToggleMode").method31();
   private boolean active;

   public Snaplook() {
      super(false);
   }

   public String getId() {
      return "SNAPLOOK";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8, this.field9, this.field11, this.field12});
      this.field8.method3(() -> this.method2(SnaplookPerspective.THIRD)).method2(arg1x -> {
         if (!(Boolean)this.field12.get() && !this.field9.isKeyDown()) {
            this.active = false;
            this.mc.bridge$getGameSettings().bridge$setThirdPersonView(this.field10);
            this.mc.bridge$getLevelRenderer().bridge$setNeedsFullRenderChunkUpdate(true);
            Freelook.method16().stop();
         }
      });
      this.field9.method3(() -> this.method2(SnaplookPerspective.FORWARD)).method2(arg1x -> {
         if (!(Boolean)this.field12.get() && !this.field8.isKeyDown()) {
            this.active = false;
            this.mc.bridge$getGameSettings().bridge$setThirdPersonView(this.field10);
            this.mc.bridge$getLevelRenderer().bridge$setNeedsFullRenderChunkUpdate(true);
            Freelook.method16().stop();
         }
      });
   }

   private void method2(SnaplookPerspective gui2extension1) {
      if (!Ref.method4().method40().method31().isEnabled()
         || Ref.method4().method40().method31().method13().method8() != this.field8.method8()
            && Ref.method4().method40().method31().method13().method8() != this.field9.method8()) {
         if (!Ref.method4().method40().method85().method17(arg0 -> !arg0.method45().method15().isFixedToPlayer())) {
            if ((Boolean)this.field12.get()) {
               if (this.active) {
                  int number2 = this.mc.bridge$getGameSettings().bridge$getThirdPersonView();
                  if (number2 != gui2extension1.getPerspective()) {
                     this.mc.bridge$getGameSettings().bridge$setThirdPersonView(gui2extension1.getPerspective());
                     this.mc.bridge$getLevelRenderer().bridge$setNeedsFullRenderChunkUpdate(true);
                  } else {
                     this.active = false;
                     this.mc.bridge$getGameSettings().bridge$setThirdPersonView(this.field10);
                     this.mc.bridge$getLevelRenderer().bridge$setNeedsFullRenderChunkUpdate(true);
                     Freelook.method16().stop();
                  }
               } else {
                  this.active = true;
                  this.field10 = this.mc.bridge$getGameSettings().bridge$getThirdPersonView();
                  this.mc.bridge$getGameSettings().bridge$setThirdPersonView(gui2extension1.getPerspective());
                  this.mc.bridge$getLevelRenderer().bridge$setNeedsFullRenderChunkUpdate(true);
                  if ((Boolean)this.field11.get()) {
                     Freelook.method16().start();
                  }
               }
            } else {
               if (!this.active) {
                  this.field10 = this.mc.bridge$getGameSettings().bridge$getThirdPersonView();
                  this.active = true;
               }

               this.mc.bridge$getGameSettings().bridge$setThirdPersonView(gui2extension1.getPerspective());
               this.mc.bridge$getLevelRenderer().bridge$setNeedsFullRenderChunkUpdate(true);
               if ((Boolean)this.field11.get()) {
                  Freelook.method16().start();
               }
            }
         }
      }
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field6}).method11(this);
   }

   @Generated
   public int method13() {
      return this.field10;
   }

   @Generated
   public ToggleOption method14() {
      return this.field11;
   }
}
