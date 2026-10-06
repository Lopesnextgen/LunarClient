package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click;
import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui_2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Fishing2Iterator;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.mixin.Rewindhandlers;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.mixin.RewindhandlersImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.mixin.RewindhandlersImpl2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore.mixin.Gui2Extension;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore.mixin.Gui2Extension2;
import com.moonsworth.lunar.client.highlight.HighlightImpl;
import com.moonsworth.lunar.client.event.input.EventMouseDelta;
import com.moonsworth.lunar.client.event.render.EventPlayerPreRender;
import com.moonsworth.lunar.client.event.mixin.fishing.EventReplayFrame;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderTickPhase.EventRenderTickBegin;
import com.moonsworth.lunar.client.event.mixin.highlight.ItemColorRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.PlayerRenderEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseScrollLegacy;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseWheelLegacy;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ButtonOption;
import com.moonsworth.lunar.client.config.option.MultiNumberOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.EnumOption.Data;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers3;
import com.moonsworth.lunar.client.util.math.Fishing;
import com.moonsworth.lunar.client.util.ThreadModuleDump6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump73;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import lombok.Generated;
import org.apache.commons.lang3.Range;
import org.joml.Matrix3d;
import org.joml.Vector3d;

public class RewindHandlers3Updater extends RewindHandlers3 {
   private Rewindhandlers field9;
   private final ClientOption<String> field10 = this.method21();
   private final EnumOption<Gui2Extension2> field11 = (EnumOption<Gui2Extension2>)OptionFactory.method10("cameraMode", Gui2Extension2.POV)
      .method31();
   private final EnumOption<Gui2Extension> field12 = (EnumOption<Gui2Extension>)((Data)OptionFactory.method10("follow", Gui2Extension.POSITION)
         .method17(() -> this.field11.get() != Gui2Extension2.FOLLOW))
      .method31();
   private final MultiNumberOption<Double> field13 = (MultiNumberOption<Double>)((com.moonsworth.lunar.client.config.option.MultiNumberOption.Data)((com.moonsworth.lunar.client.config.option.MultiNumberOption.Data)OptionFactory.method22(
               "positionRotationFreecam", new Double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0}
            )
            .method8(Codec.DOUBLE.listOf()))
         .method17(() -> this.field11.get() != Gui2Extension2.FREE_CAMERA))
      .method31();
   private final MultiNumberOption<Double> field14 = (MultiNumberOption<Double>)((com.moonsworth.lunar.client.config.option.MultiNumberOption.Data)((com.moonsworth.lunar.client.config.option.MultiNumberOption.Data)OptionFactory.method22(
               "positionRotationFollow", new Double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0}
            )
            .method8(Codec.DOUBLE.listOf()))
         .method17(() -> this.field11.get() != Gui2Extension2.FOLLOW))
      .method31();
   private final ToggleOption field15 = (ToggleOption)OptionFactory.method7("cameraShake").method31();
   private final FloatOption field16 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "intensity"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.0F, 10.0F))
      .method31();
   private final FloatOption field17 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "speed"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.0F, 10.0F))
      .method31();
   private final ToggleOption field18 = (ToggleOption)OptionFactory.method7("cameraFov").method31();
   private final FloatOption field19 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "fov"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(70.0F))
         .method8(1.0F, 130.0F))
      .method31();
   private final ToggleOption field20 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("hideHud")
            .method17(() -> ((Gui2Extension2)this.field11.get()).isFixedToPlayer()))
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field21 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("forceHideHud")
         .method17(() -> !((Gui2Extension2)this.field11.get()).isFixedToPlayer()))
      .method31();
   private final ButtonOption field22 = (ButtonOption)((com.moonsworth.lunar.client.config.option.ButtonOption.Data)OptionFactory.method14(
            "teleportToTarget"
         )
         .method4(() -> {
            if (this.field9 instanceof RewindhandlersImpl var1x) {
               var1x.method6();
            }
         })
         .method17(() -> ((Gui2Extension2)this.field11.get()).isFixedToPlayer()))
      .method31();
   private final Rewindhandlers field23 = new RewindhandlersImpl(this);
   private final Rewindhandlers field24 = new RewindhandlersImpl2(this);
   private Gui2Extension2 field25 = null;

   public RewindHandlers3Updater(ThreadModuleDump6<Nameplate4> var1) {
      super(var1);
      this.handle(EventRenderTickBegin.class, this::method2);
      this.handle(EventMouseWheelLegacy.class, this::method9);
      this.handle(EventMouseScrollLegacy.class, this::method10);
      this.handle(EventMouseDelta.class, this::method11);
      this.handle(EventReplayFrame.class, this::method3);
      this.handle(PlayerRenderEvent.class, this::method4);
      if (ThreadModuleDump63.MC_VERSION == 1) {
         this.handle(ItemColorRenderEvent.class, this::method4);
      }

      this.handle(EventPlayerPreRender.class, this::method5);
      this.handle(com.moonsworth.lunar.client.event.mixin.highlight.FovModifierEvent.Data.class, this::method6);
      this.handle(HudRenderLegacyEvent.class, this::method7);
      this.update();
   }

   public void update() {
      if (this.field25 != this.method15()) {
         this.field9 = this.method1(this.method15());
         this.field25 = this.method15();
         this.field9.method1(0.0F);
      }
   }

   private Rewindhandlers method1(Gui2Extension2 var1) {
      if (var1.isFixedToPlayer()) {
         return this.field24;
      }

      Gui2Extension2 var2 = this.field25;
      ThreadModuleDump63.method3().bridge$schedule(() -> {
         if (this.method16().isDefault() || var2 == Gui2Extension2.FOLLOW && !((Nameplate4)this.field8.get()).method6().method44()) {
            ((RewindhandlersImpl)this.field23).method6();
         }

         ((Nameplate4)this.field8.get()).method6().method57().method17();
         ThreadModuleDump63.method3().bridge$getLevelRenderer().bridge$reloadChunks();
      });
      return this.field23;
   }

   private void method2(EventRenderTickBegin var1) {
      this.field9.method1(var1.method1());
   }

   private void method3(EventReplayFrame var1) {
      this.field9.method4(var1);
   }

   private void method4(HighlightImpl var1) {
      if (!this.method15().isFirstPerson() || ThreadModuleDump63.method3().bridge$getRenderViewEntity() != ThreadModuleDump63.method7()) {
         var1.cancel();
      }
   }

   private void method5(EventPlayerPreRender var1) {
      if (var1.method3().bridge$isSelf() && ThreadModuleDump63.method3().bridge$getSpectatedEntity() != null) {
         var1.cancel();
      }
   }

   private void method6(com.moonsworth.lunar.client.event.mixin.highlight.FovModifierEvent.Data var1) {
      if ((Boolean)this.field18.get()) {
         var1.method5((Float)this.field19.get());
      }
   }

   private void method7(HudRenderLegacyEvent var1) {
      RewindHandlers var2 = ((Nameplate4)this.field8.get()).method6();
      if (!var2.method40().method31()) {
         Highlight_3 var3 = ((Nameplate4)this.field8.get()).method4();
         if (var3 != null && (var3.isPaused() || !var2.method44()) && !var2.method57().method25()) {
            Bridge2_43 var4 = ThreadModuleDump63.method13();
            if (var4 != null) {
               Vector3d var5 = new Vector3d(var4.bridge$renderPosX(), var4.bridge$renderPosY(), var4.bridge$renderPosZ());
               LinkedHashSet var6 = new LinkedHashSet();
               Set var7 = new LinkedHashSet();
               float var8 = 1.54F;
               if (ThreadModuleDump63.method3().bridge$getRenderViewEntity() != null) {
                  var8 = ThreadModuleDump63.method3().bridge$getRenderViewEntity().bridge$getEyeHeight();
               }

               for (Gui_2 var10 : var3.method11()) {
                  Entry var11 = var10.method1(var3.method15());
                  if (var11 != null) {
                     Fishing2Iterator var12 = ((RewindIterator)var11.getValue()).method18().get("camera");
                     if (var12 != null) {
                        Fishing2Loader var13 = var12.method12().get(this.method16().getId());
                        Fishing2Loader var14 = var12.method12().get(this.field11.getId());
                        Fishing2Loader var15 = var12.method12().get(this.field12.getId());
                        BridgeExtension var16 = this.method17();
                        List var17 = List.of(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
                        if (var16 != null) {
                           double var18 = var16.bridge$getPreviousRotationYaw()
                              + (var16.bridge$getRotationYaw() - var16.bridge$getPreviousRotationYaw()) * var1.method5();
                           var17 = List.of(
                              var16.method3() + (var16.bridge$getPosX() - var16.method3()) * var1.method5(),
                              var16.method4() + (var16.bridge$getPosY() - var16.method4()) * var1.method5(),
                              var16.method5() + (var16.bridge$getPosZ() - var16.method5()) * var1.method5(),
                              var16 instanceof BridgeExtension2_5 var20
                                 ? (double)var20.bridge$getPrevRotationYawHead()
                                    + (var20.bridge$getRotationYawHead() - var20.bridge$getPrevRotationYawHead()) * var1.method5()
                                 : var18,
                              var16.bridge$getPreviousRotationPitch()
                                 + (var16.bridge$getRotationPitch() - var16.bridge$getPreviousRotationPitch()) * var1.method5(),
                              (Double)((List)this.method16().get()).get(5),
                              var18
                           );
                        }

                        if (!((Gui2Extension2)this.field11.get()).isFixedToPlayer()) {
                           var6 = new LinkedHashSet();
                           int var32 = var3.method13().getFps();
                           int var19 = 30 * var32;
                           int var33 = Math.max(var3.method15() - var19, (Integer)((Range)var11.getKey()).getMinimum());
                           int var21 = Math.min(var3.method15() + var19, (Integer)((Range)var11.getKey()).getMaximum());
                           int var22 = Integer.MIN_VALUE;
                           List var23 = null;

                           for (int var24 = var33; var24 <= var21; var24++) {
                              int var25 = var24 - (Integer)((Range)var11.getKey()).getMinimum();
                              List var26 = (List)var13.getAt(var25);
                              Gui2Extension var27 = null;
                              if (var13.method27().containsKey(var24 - (Integer)((Range)var11.getKey()).getMinimum())) {
                                 if (var24 == var3.method15()) {
                                    var23 = null;
                                    continue;
                                 }

                                 if (var14.getAt(var25) == Gui2Extension2.FOLLOW) {
                                    var27 = (Gui2Extension)var15.getAt(var25);
                                 }

                                 var6.addAll(this.method8(var3, var26, var17, var27, var8, var5));
                              }

                              if (var23 != null && var24 > var22 + var32 / 10) {
                                 Vector3d var28 = new Vector3d((Double)var23.get(0), (Double)var23.get(1) + var8, (Double)var23.get(2)).sub(var5);
                                 Vector3d var29 = new Vector3d((Double)var26.get(0), (Double)var26.get(1) + var8, (Double)var26.get(2)).sub(var5);
                                 Vector3d var30 = new Vector3d(0.0, 0.0, 0.0);
                                 if (var14.getAt(var25) == Gui2Extension2.FOLLOW) {
                                    var30 = new Vector3d((Double)var17.get(0), (Double)var17.get(1), (Double)var17.get(2)).sub(var5);
                                 }

                                 var6.add(new Fishing(var28.add(var30), var29.add(var30)));
                                 var22 = var24;
                                 var23 = var26;
                              }

                              if (var23 == null) {
                                 var23 = var26;
                              }
                           }

                           int var34 = var3.method15() - (Integer)((Range)var11.getKey()).getMinimum();
                           List var35 = (List)var13.getAt(var34);
                           Gui2Extension var36 = null;
                           if (var14.getAt(var34) == Gui2Extension2.FOLLOW) {
                              var36 = (Gui2Extension)var15.getAt(var34);
                           }

                           Vector3d var37 = new Vector3d((Double)var35.get(0), (Double)var35.get(1), (Double)var35.get(2));
                           Vector3d var38 = new Vector3d(
                              (Double)((List)this.method16().get()).get(0),
                              (Double)((List)this.method16().get()).get(1),
                              (Double)((List)this.method16().get()).get(2)
                           );
                           if (var37.distance(var38) > 0.5) {
                              var7 = this.method8(var3, var35, var17, var36, var8, var5);
                           }
                        } else if (var16 != null && !var2.method44()) {
                           var7 = this.method8(var3, var17, var17, null, var16.bridge$getEyeHeight(), var5);
                        }
                     }
                  }
               }

               AbstractRenderContext var31 = var1.method3();
               var31.push();
               if (!var6.isEmpty()) {
                  Click.drawFishingLines(var31, var6, -2364929);
               }

               if (!var7.isEmpty()) {
                  Click.drawFishingLines(var31, var7, -2171325);
               }

               var31.pop();
            }
         }
      }
   }

   private Set<Fishing> method8(Highlight_3 var1, List<Double> var2, List<Double> var3, Gui2Extension var4, float var5, Vector3d var6) {
      LinkedHashSet var7 = new LinkedHashSet();
      double var8 = (Double)var2.get(0);
      double var10 = (Double)var2.get(1) + var5;
      double var12 = (Double)var2.get(2);
      double var14 = (Double)var2.get(3) + 180.0;
      double var16 = (Double)var2.get(4);
      double var18 = (Double)var2.get(5);
      if (var4 != null) {
         if (var4 == Gui2Extension.POSITION) {
            var8 += var3.get(0);
            var10 += var3.get(1);
            var12 += var3.get(2);
         } else {
            boolean var20 = var4 == Gui2Extension.POSITION;
            double var21 = Math.toRadians((Double)var3.get(var20 ? 3 : 6));
            double var23 = Math.toRadians((Double)var3.get(4));
            double var25 = var8;
            double var27 = var10 * Math.cos(var23) - var12 * Math.sin(var23);
            double var29 = var10 * Math.sin(var23) + var12 * Math.cos(var23);
            double var31 = var25 * Math.cos(var21) - var29 * Math.sin(var21);
            double var33 = var27;
            double var35 = var25 * Math.sin(var21) + var29 * Math.cos(var21);
            var8 = (Double)var3.get(0) + var31;
            var10 = (Double)var3.get(1) + var33;
            var12 = (Double)var3.get(2) + var35;
            if (var20) {
               var14 += var3.get(6);
               var16 += var3.get(4);
            } else {
               var14 += var3.get(3);
            }
         }
      }

      double var74 = Math.toRadians(
         this.field18.get() ? ((Float)this.field19.get()).floatValue() : ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getFov()
      );
      double var22 = (double)var1.method13().getWidth() / var1.method13().getHeight();
      double var24 = 0.1;
      double var26 = 0.5;
      Vector3d var28 = new Vector3d(var8, var10, var12);
      double var75 = Math.toRadians(var14);
      double var76 = Math.toRadians(var16);
      double var77 = Math.toRadians(var18);
      double var78 = Math.cos(var76);
      double var37 = Math.sin(var76);
      double var39 = Math.sin(var75);
      double var41 = Math.cos(var75);
      Vector3d var43 = new Vector3d(var39 * var78, -var37, -var41 * var78).normalize();
      Vector3d var44 = new Vector3d(0.0, 1.0, 0.0);
      Vector3d var45 = new Vector3d(var43).cross(var44).normalize();
      Vector3d var46 = new Vector3d(var45).cross(var43).normalize();
      Vector3d var47 = new Vector3d(var46).mul(new Matrix3d().rotation(var77, var43));
      var45 = new Vector3d(var43).cross(var47).normalize();
      Vector3d var48 = new Vector3d(var28).fma(var24, var43).sub(var6);
      Vector3d var49 = new Vector3d(var28).fma(var26, var43).sub(var6);
      double var50 = 2.0 * Math.tan(var74 / 2.0) * var24;
      double var52 = var50 * var22;
      double var54 = 2.0 * Math.tan(var74 / 2.0) * var26;
      double var56 = var54 * var22;
      double var58 = var50 * 0.5;
      double var60 = var52 * 0.5;
      double var62 = var54 * 0.5;
      double var64 = var56 * 0.5;
      Vector3d var66 = new Vector3d(var48).fma(var58, var47).fma(-var60, var45);
      Vector3d var67 = new Vector3d(var48).fma(var58, var47).fma(var60, var45);
      Vector3d var68 = new Vector3d(var48).fma(-var58, var47).fma(-var60, var45);
      Vector3d var69 = new Vector3d(var48).fma(-var58, var47).fma(var60, var45);
      Vector3d var70 = new Vector3d(var49).fma(var62, var47).fma(-var64, var45);
      Vector3d var71 = new Vector3d(var49).fma(var62, var47).fma(var64, var45);
      Vector3d var72 = new Vector3d(var49).fma(-var62, var47).fma(-var64, var45);
      Vector3d var73 = new Vector3d(var49).fma(-var62, var47).fma(var64, var45);
      var7.add(new Fishing(var66, var67));
      var7.add(new Fishing(var67, var69));
      var7.add(new Fishing(var69, var68));
      var7.add(new Fishing(var68, var66));
      var7.add(new Fishing(var70, var71));
      var7.add(new Fishing(var71, var73));
      var7.add(new Fishing(var73, var72));
      var7.add(new Fishing(var72, var70));
      var7.add(new Fishing(var66, var70));
      var7.add(new Fishing(var67, var71));
      var7.add(new Fishing(var68, var72));
      var7.add(new Fishing(var69, var73));
      return var7;
   }

   private void method9(EventMouseWheelLegacy var1) {
      var1.cancel();
   }

   private void method10(EventMouseScrollLegacy var1) {
      if (((Nameplate4)this.field8.get()).method6().method62()) {
         this.field9.method2(var1);
      }
   }

   private void method11(EventMouseDelta var1) {
      var1.cancel();
      this.field9.method3(var1);
   }

   public float[] method14() {
      Highlight_3 var1 = ((Nameplate4)this.field8.get()).method6().method40().method37();
      if (var1 != null && (Boolean)this.field15.get()) {
         float var2 = (Float)this.field16.get();
         float var3 = (Float)this.field17.get();
         double var4 = var1.method13().getFps();
         float var6 = (float)Math.sin(var1.method15() / var4 * 1.5 * var3) * var2;
         float var7 = (float)Math.cos(var1.method15() / var4 * 1.2 * var3) * var2;
         return new float[]{var6, var7};
      } else {
         return new float[]{0.0F, 0.0F};
      }
   }

   public Gui2Extension2 method15() {
      return !((Nameplate4)this.field8.get()).method6().method44() ? Gui2Extension2.FREE_CAMERA : (Gui2Extension2)this.field11.get();
   }

   public MultiNumberOption<Double> method16() {
      return this.field11.get() == Gui2Extension2.FOLLOW ? this.field14 : this.field13;
   }

   public BridgeExtension method17() {
      try {
         Object var1 = ThreadModuleDump63.method7();
         if (!this.field10.isDefault()) {
            String var2 = (String)this.field10.get();
            if (var2.contains("-")) {
               UUID var3 = UUID.fromString((String)this.field10.get());
               var1 = (BridgeExtension)ThreadModuleDump63.method8().bridge$getPlayerByUniqueId(var3).orElseThrow();
            } else {
               var1 = (BridgeExtension)ThreadModuleDump63.method8().bridge$getEntityById(Integer.parseInt(var2)).orElseThrow();
            }
         }

         return (BridgeExtension)var1;
      } catch (Exception var4) {
         return ThreadModuleDump63.method7();
      }
   }

   public boolean method19() {
      return !this.field10.isDefault();
   }

   private ClientOption<String> method21() {
      return ((com.moonsworth.lunar.client.config.option.OptionFactory.SimpleOptionBuilder)((com.moonsworth.lunar.client.config.option.OptionFactory.SimpleOptionBuilder)((com.moonsworth.lunar.client.config.option.OptionFactory.SimpleOptionBuilder)OptionFactory.method42(
                     "target", Codec.STRING
                  )
                  .method2(""))
               .method16(DriverFieldTypeLegacy.PLAYERS))
            .method11(
               var1 -> {
                  JsonArray var2 = new JsonArray();
                  if (ThreadModuleDump63.method8() != null) {
                     for (Bridge6_10 var4 : ThreadModuleDump63.method8().bridge$getPlayerEntities()) {
                        JsonObject var5 = new JsonObject();
                        var5.addProperty(
                           "name", AdventureTextBridge.stripColor(var4.bridge$getName()).isBlank() ? var4.bridge$getUniqueID().toString() : var4.bridge$getName()
                        );
                        var5.addProperty("uuid", var4.bridge$getUniqueID().toString());
                        var2.add(var5);
                     }
                  }

                  if (!((String)this.field10.get()).isEmpty() && !((String)this.field10.get()).contains("-")) {
                     String var7 = null;

                     try {
                        var7 = ThreadModuleDump73.getEntityTypeName(Integer.parseInt((String)this.field10.get()));
                     } catch (Exception var6) {
                        Slayer.method4("Rewind", "Failed to get entity name for ID: %s %s", new Object[]{this.field10.get(), var6.getMessage()});
                     }

                     if (var7 == null) {
                        var7 = "Entity";
                     }

                     String var9 = (String)this.field10.get();
                     if (var9.length() > 3) {
                        var9 = var9.substring(var9.length() - 3);
                     }

                     JsonObject var10 = new JsonObject();
                     var10.addProperty("name", var7 + " (" + var9 + ")");
                     var10.addProperty("uuid", (String)this.field10.get());
                     var10.addProperty("noAvatar", true);
                     var2.add(var10);
                  }

                  var1.add("players", var2);
                  if (ThreadModuleDump63.method7() != null) {
                     JsonObject var8 = new JsonObject();
                     var8.addProperty(
                        "name",
                        AdventureTextBridge.stripColor(ThreadModuleDump63.method7().bridge$getName()).isBlank()
                           ? ThreadModuleDump63.method7().bridge$getUniqueID().toString()
                           : ThreadModuleDump63.method7().bridge$getName()
                     );
                     var8.addProperty("uuid", ThreadModuleDump63.method7().bridge$getUniqueID().toString());
                     var1.add("player", var8);
                  }
               }
            ))
         .method31();
   }

   @Generated
   public ClientOption<String> method22() {
      return this.field10;
   }

   @Generated
   public EnumOption<Gui2Extension2> method23() {
      return this.field11;
   }

   @Generated
   public EnumOption<Gui2Extension> method24() {
      return this.field12;
   }

   @Generated
   public MultiNumberOption<Double> method25() {
      return this.field13;
   }

   @Generated
   public MultiNumberOption<Double> method26() {
      return this.field14;
   }

   @Generated
   public ToggleOption method27() {
      return this.field15;
   }

   @Generated
   public FloatOption method28() {
      return this.field16;
   }

   @Generated
   public FloatOption method29() {
      return this.field17;
   }

   @Generated
   public ToggleOption method30() {
      return this.field18;
   }

   @Generated
   public FloatOption method34() {
      return this.field19;
   }

   @Generated
   public ToggleOption method35() {
      return this.field20;
   }

   @Generated
   public ToggleOption method36() {
      return this.field21;
   }

   @Generated
   public ButtonOption method37() {
      return this.field22;
   }
}
