package com.moonsworth.lunar.client.mod.movement.zoom;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.KeyBindingBridge;
import com.moonsworth.lunar.bridge.optifine.OptifineBridge;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.event.screen.EventScreenOpen;
import com.moonsworth.lunar.client.event.render.EventRenderScreenOverlay.EventScreenInitPost;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseWheel;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption.Data;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import lombok.Generated;

public class Zoom extends AbstractFeature {
   private float field8 = 1.0F;
   private float field9;
   private float field10;
   private boolean field11;
   private boolean field12;
   private boolean field13;
   private final SimpleKeybindOption field14 = ((SimpleKeybindOption)((Data)((Data)OptionFactory.method17("zoomKeybind")
               .method2(KeyCode.KEY_C))
            .method18(this))
         .method31())
      .method1((KeyBindingBridge)Ref.method3().bridge$getGameSettings().bridge$getZoomKey().orElse(null));
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("smoothCamera").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("smoothZoom").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field17 = (ToggleOption)OptionFactory.method7("variableZoom").method31();
   private final FloatOption field18 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "zoomScrollSpeed"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.25F, 5.0F))
      .method31();
   private final ToggleOption field19 = (ToggleOption)OptionFactory.method7("toggleKeyZoom").method31();
   private final IntegerOption field20 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "zoomDivisor"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(4))
         .method7(2, 10))
      .method31();
   private final FloatOption field21 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "cameraSensitivity"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.1F, 2.0F))
      .method31();
   private boolean active;

   public Zoom() {
      super(true);
      this.handle(EventMouseWheel.class, arg1 -> {
         if (this.field11 && (Boolean)this.field17.get()) {
            if (Ref.method11() == null || Ref.method4().method40().method85().method19()) {
               float value2 = arg1.method1() < 0.0 ? -1.0F : 1.0F;
               value2 *= this.field18.get();
               this.field8 = (float)MathUtils.method2(this.field8 + value2 * 0.5, 1.4 / ((Integer)this.field20.get()).intValue(), 10.0);
               this.mc.bridge$getLevelRenderer().bridge$setNeedsFullRenderChunkUpdate(true);
               arg1.setCancelled(true);
            }
         }
      });
      this.handle(EventTick.class, arg1 -> {
         if ((Boolean)this.field16.get()) {
            float value2 = 1.0F;
            double value3 = 1.0 / this.method13();
            if (this.field11) {
               value2 = (float)value3;
            }

            this.field10 = this.field9;
            this.field9 = this.field9 + (value2 - this.field9) * 0.75F;
         }
      });
      this.handle(EventScreenInitPost.class, arg1 -> this.active = false);
      this.handle(EventScreenOpen.class, arg1 -> {
         if (this.field11 && !this.active) {
            this.field9 = 1.0F;
         }
      });
      this.handle(
         com.moonsworth.lunar.client.event.mixin.highlight.EventFovModifier.FovInput.class,
         arg1 -> this.mc.bridge$getGameSettings().bridge$getZoomKey().ifPresent(arg0 -> arg0.bridge$setKeyBindState(false))
      );
      this.handle(
         com.moonsworth.lunar.client.event.mixin.highlight.EventFovModifier.EventFovModifierPost.class,
         arg1 -> {
            if (Ref.method11() == null || Ref.method4().method40().method85().method19()) {
               boolean flag2 = Bridge.method5().isPresent() && ((OptifineBridge)Bridge.method5().get()).getConfig().isZooming();
               if (this.active) {
                  if (!this.field11) {
                     this.field11 = true;
                     this.field13 = false;
                     if (!flag2) {
                        this.field12 = this.mc.bridge$getGameSettings().bridge$getSmoothCamera();
                     }

                     this.mc.bridge$getLevelRenderer().bridge$setNeedsFullRenderChunkUpdate(true);
                  }

                  if ((Boolean)this.field15.get() && !flag2) {
                     this.mc.bridge$getGameSettings().bridge$setSmoothCamera(true);
                  }

                  float value3 = arg1.IOIIOIOHRHCRHRCHOHHIOCCICCIIOH();
                  if (Bridge.method5().isPresent() && ((OptifineBridge)Bridge.method5().get()).getConfig().isZooming()) {
                     value3 *= 4.0F;
                  }

                  if ((Boolean)this.field16.get()) {
                     value3 *= MathUtils.lerp(this.field10, this.field9, arg1.OICCCIHIRHICIOOHHRHOOIHHRROORC());
                  } else {
                     value3 /= this.method13();
                  }

                  arg1.method5(value3);
               } else {
                  if (this.field11) {
                     this.field11 = false;
                     if (!flag2) {
                        this.mc.bridge$getGameSettings().bridge$setSmoothCamera(this.field12);
                     }

                     this.mc.bridge$getLevelRenderer().bridge$setNeedsFullRenderChunkUpdate(true);
                     this.field8 = 1.0F;
                  }

                  if ((Boolean)this.field16.get()) {
                     if (this.field9 != 1.0F) {
                        arg1.method5(
                           arg1.IOIIOIOHRHCRHRCHOHHIOCCICCIIOH() * MathUtils.lerp(this.field10, this.field9, arg1.OICCCIHIRHICIOOHHRHOOIHHRROORC())
                        );
                        this.mc.bridge$getLevelRenderer().bridge$setNeedsFullRenderChunkUpdate(true);
                     } else if (!this.field13) {
                        this.mc.bridge$getLevelRenderer().bridge$setNeedsFullRenderChunkUpdate(true);
                        this.field13 = true;
                     }
                  }
               }
            }
         }
      );
   }

   public String getId() {
      return "ZOOM";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(
         new ClientOption[]{this.field14, this.field19, this.field15, this.field16, this.field17, this.field18, this.field20, this.field21}
      );
      this.field14.method3(() -> {
         if (!this.active) {
            this.active = true;
         } else {
            this.field14.method11(0L);
         }
      }).method2(arg1x -> {
         if (!(Boolean)this.field19.get() || !arg1x) {
            this.active = false;
         }
      });
   }

   private float method13() {
      return ((Integer)this.field20.get()).intValue() * (this.field17.get() ? this.field8 : 1.0F);
   }

   public float method3(float value1) {
      float value2 = 1.0F;
      if (this.active) {
         if ((Boolean)this.field16.get()) {
            value2 = MathUtils.lerp(this.field10, this.field9, value1);
         } else {
            value2 /= this.method13();
         }
      }

      if (Bridge.method5().isPresent() && ((OptifineBridge)Bridge.method5().get()).getConfig().isZooming()) {
         value2 *= 4.0F;
      }

      return value2;
   }

   public float method14() {
      return (Float)this.field21.get();
   }

   @Generated
   public boolean isActive() {
      return this.active;
   }

   @Generated
   public void setActive(boolean flag1) {
      this.active = flag1;
   }
}
