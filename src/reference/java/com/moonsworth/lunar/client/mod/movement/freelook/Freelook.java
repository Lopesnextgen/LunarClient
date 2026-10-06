package com.moonsworth.lunar.client.mod.movement.freelook;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.ui.EaseAnimation;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.freelook.FreelookMode;
import com.moonsworth.lunar.client.event.render.EventRenderScreenOverlay.EventScreenInitPost;
import com.moonsworth.lunar.client.event.input.EventMouseMove;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventPerspectiveChange;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption.Data;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class Freelook extends AbstractFeature {
   private final SimpleKeybindOption field8 = (SimpleKeybindOption)((Data)((Data)((Data)OptionFactory.method17("freelook")
               .method2(KeyCode.KEY_LMENU))
            .method18(this))
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final EnumOption<FreelookMode> field9 = (EnumOption<FreelookMode>)OptionFactory.method10("mode", FreelookMode.THIRD)
      .method31();
   private final ToggleOption field10 = (ToggleOption)OptionFactory.method7("invertYaw").method31();
   private final ToggleOption field11 = (ToggleOption)OptionFactory.method7("invertPitch").method31();
   private final ToggleOption field12 = (ToggleOption)OptionFactory.method7("toggleKeyFreelook").method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("smoothCamera").method4(true))
      .method31();
   private float rotationYaw;
   private float rotationPitch;
   private boolean active;
   private static final EaseAnimation field14 = new EaseAnimation(650L);
   private int field15;

   public Freelook() {
      super(true);
      this.handle(EventScreenInitPost.class, this::method1);
      this.handle(EventPerspectiveChange.class, this::method3);
      this.handle(EventMouseMove.class, this::method2);
   }

   public String getId() {
      return "FREELOOK";
   }

   private void method1(EventScreenInitPost data51) {
      if (this.active) {
         this.stop();
      }
   }

   private void start() {
      if (Ref.method7() != null) {
         if (!Ref.method4().method40().method85().method17(arg0 -> !arg0.method45().method15().isFixedToPlayer())) {
            this.active = true;
            this.rotationYaw = (float)Ref.method7().bridge$getRotationYaw();
            this.rotationPitch = (float)Ref.method7().bridge$getRotationPitch();
            this.field15 = this.mc.bridge$getGameSettings().bridge$getThirdPersonView();
            if (Ref.method4().method45().method9(this.mc.bridge$getPlayer()) && Ref.method4().method45().field9) {
               this.field15 = 0;
            }

            this.mc.bridge$getGameSettings().bridge$setThirdPersonView(((FreelookMode)this.field9.get()).getPerspective());
            if ((Boolean)this.field13.get()) {
               field14.start();
            }
         }
      }
   }

   private void stop() {
      this.mc.bridge$getGameSettings().bridge$setThirdPersonView(this.field15);
      this.active = false;
      this.mc.bridge$getLevelRenderer().bridge$setNeedsFullRenderChunkUpdate(true);
      field14.stop();
   }

   private void method2(EventMouseMove highlightimpl141) {
      if (this.active) {
         if (Ref.method4().method40().method85().method17(arg0 -> !arg0.method45().method15().isFixedToPlayer())) {
            return;
         }

         this.rotationYaw = this.rotationYaw + highlightimpl141.method1() * (this.field10.get() ? -1 : 1) / 8.0F;
         if (Bridge.getMinecraftVersion().method19()) {
            this.rotationPitch = this.rotationPitch + highlightimpl141.method2() * (this.field11.get() ? -1 : 1) / 8.0F;
         } else {
            this.rotationPitch = this.rotationPitch + highlightimpl141.method2() * (this.field11.get() ? 1 : -1) / 8.0F;
         }

         if (Math.abs(this.rotationPitch) > 90.0F) {
            this.rotationPitch = this.rotationPitch > 0.0F ? 90.0F : -90.0F;
         }

         this.mc.bridge$getLevelRenderer().bridge$setNeedsFullRenderChunkUpdate(true);
         this.rotationYaw = this.method4(this.rotationYaw);
         highlightimpl141.cancel();
      }
   }

   private void method3(EventPerspectiveChange highlightimpl51) {
      if (this.active) {
         highlightimpl51.setCancelled(true);
      }
   }

   private float method4(float value1) {
      float value2 = value1;
      if (value2 < 0.0F) {
         while (value2 < 0.0F) {
            value2 += 360.0F;
         }
      } else if (value2 > 360.0F) {
         while (value2 > 360.0F) {
            value2 -= 360.0F;
         }
      }

      return value2;
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field9, this.field11, this.field10, this.field12, this.field13, this.field8});
      this.field8.method3(() -> {
         if (!this.active) {
            this.start();
         } else {
            this.field8.method11(0L);
         }
      }).method2(arg1x -> {
         if (!(Boolean)this.field12.get() || !arg1x) {
            this.stop();
         }
      });
   }

   protected ModDetails method20() {
      return ModDetails.method7()
         .method1(new ModCategory[]{ModCategory.field6})
         .method2(new String[]{"perspective", "360", "altlook"})
         .method11(this);
   }

   @Generated
   public SimpleKeybindOption method13() {
      return this.field8;
   }

   @Generated
   public EnumOption<FreelookMode> method14() {
      return this.field9;
   }

   @Generated
   public ToggleOption method15() {
      return this.field13;
   }

   @Generated
   public float getRotationYaw() {
      return this.rotationYaw;
   }

   @Generated
   public float getRotationPitch() {
      return this.rotationPitch;
   }

   @Generated
   public boolean isActive() {
      return this.active;
   }

   @Generated
   public static EaseAnimation method16() {
      return field14;
   }
}
