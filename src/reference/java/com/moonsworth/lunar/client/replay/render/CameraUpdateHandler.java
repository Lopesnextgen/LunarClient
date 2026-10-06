package com.moonsworth.lunar.client.replay.render;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.replay.timeline.KeyframeProperty;
import com.moonsworth.lunar.client.replay.timeline.RewindIterator;
import com.moonsworth.lunar.client.replay.timeline.Track;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.timeline.PropertyGroup;
import com.moonsworth.lunar.client.replay.render.RewindCameraController;
import com.moonsworth.lunar.client.replay.render.RewindhandlersImpl;
import com.moonsworth.lunar.client.replay.render.CameraRewindHandler;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.replay.render.CameraFollowMode;
import com.moonsworth.lunar.client.replay.render.CameraMode;
import com.moonsworth.lunar.client.event.CancellableEvent;
import com.moonsworth.lunar.client.event.input.EventMouseMove;
import com.moonsworth.lunar.client.event.render.EventPreRenderPlayer;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRewindFrame;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderTick.EventRenderTickStart;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemColor;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderPlayer;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseScroll;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseWheel;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ButtonOption;
import com.moonsworth.lunar.client.config.option.MultiNumberOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.EnumOption.Data;
import com.moonsworth.lunar.client.driver.DriverFieldType;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandler;
import com.moonsworth.lunar.client.util.math.LineSegment;
import com.moonsworth.lunar.client.util.collection.ValueHolder;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.game.EntityLookup;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import lombok.Generated;
import org.apache.commons.lang3.Range;
import org.joml.Matrix3d;
import org.joml.Vector3d;

public class CameraUpdateHandler extends RewindHandler {
   private RewindCameraController field9;
   private final ClientOption<String> field10 = this.method21();
   private final EnumOption<CameraMode> field11 = (EnumOption<CameraMode>)OptionFactory.method10("cameraMode", CameraMode.POV)
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final EnumOption<CameraFollowMode> field12 = (EnumOption<CameraFollowMode>)((Data)OptionFactory.method10("follow", CameraFollowMode.POSITION)
         .ORICHRORRORHORHOIHCRHOORCRRHOI(() -> this.field11.get() != CameraMode.FOLLOW))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final MultiNumberOption<Double> field13 = (MultiNumberOption<Double>)((com.moonsworth.lunar.client.config.option.MultiNumberOption.Data)((com.moonsworth.lunar.client.config.option.MultiNumberOption.Data)OptionFactory.method22(
               "positionRotationFreecam", new Double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0}
            )
            .method8(Codec.DOUBLE.listOf()))
         .ORICHRORRORHORHOIHCRHOORCRRHOI(() -> this.field11.get() != CameraMode.FREE_CAMERA))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final MultiNumberOption<Double> field14 = (MultiNumberOption<Double>)((com.moonsworth.lunar.client.config.option.MultiNumberOption.Data)((com.moonsworth.lunar.client.config.option.MultiNumberOption.Data)OptionFactory.method22(
               "positionRotationFollow", new Double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0}
            )
            .method8(Codec.DOUBLE.listOf()))
         .ORICHRORRORHORHOIHCRHOORCRRHOI(() -> this.field11.get() != CameraMode.FOLLOW))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field15 = (ToggleOption)OptionFactory.method7("cameraShake").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final FloatOption field16 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "intensity"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.0F, 10.0F))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final FloatOption field17 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "speed"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.0F, 10.0F))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field18 = (ToggleOption)OptionFactory.method7("cameraFov").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final FloatOption field19 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "fov"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(70.0F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(1.0F, 130.0F))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field20 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("hideHud")
            .ORICHRORRORHORHOIHCRHOORCRRHOI(() -> ((CameraMode)this.field11.get()).isFixedToPlayer()))
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field21 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("forceHideHud")
         .ORICHRORRORHORHOIHCRHOORCRRHOI(() -> !((CameraMode)this.field11.get()).isFixedToPlayer()))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ButtonOption field22 = (ButtonOption)((com.moonsworth.lunar.client.config.option.ButtonOption.Data)OptionFactory.method14(
            "teleportToTarget"
         )
         .method4(() -> {
            if (this.field9 instanceof RewindhandlersImpl rewindhandlersimpl1x) {
               rewindhandlersimpl1x.method6();
            }
         })
         .ORICHRORRORHORHOIHCRHOORCRRHOI(() -> ((CameraMode)this.field11.get()).isFixedToPlayer()))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final RewindCameraController field23 = new RewindhandlersImpl(this);
   private final RewindCameraController field24 = new CameraRewindHandler(this);
   private CameraMode field25 = null;

   public CameraUpdateHandler(ValueHolder<ReplayContext> threadmoduledump61) {
      super(threadmoduledump61);
      this.handle(EventRenderTickStart.class, this::method2);
      this.handle(EventMouseWheel.class, this::method9);
      this.handle(EventMouseScroll.class, this::method10);
      this.handle(EventMouseMove.class, this::method11);
      this.handle(EventRewindFrame.class, this::method3);
      this.handle(EventRenderPlayer.class, this::method4);
      if (Ref.MC_VERSION == 1) {
         this.handle(EventRenderItemColor.class, this::method4);
      }

      this.handle(EventPreRenderPlayer.class, this::method5);
      this.handle(com.moonsworth.lunar.client.event.mixin.highlight.EventFovModifier.FovInput.class, this::method6);
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

   private RewindCameraController method1(CameraMode gui2extension21) {
      if (gui2extension21.isFixedToPlayer()) {
         return this.field24;
      }

      CameraMode gui2extension22 = this.field25;
      Ref.method3().bridge$schedule(() -> {
         if (this.method16().isDefault() || gui2extension22 == CameraMode.FOLLOW && !((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method6().method44()) {
            ((RewindhandlersImpl)this.field23).method6();
         }

         ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method6().method57().method17();
         Ref.method3().bridge$getLevelRenderer().bridge$reloadChunks();
      });
      return this.field23;
   }

   private void method2(EventRenderTickStart data51) {
      this.field9.method1(data51.RRROHRIIHCCIICIROOHHHOHHOOHCRR());
   }

   private void method3(EventRewindFrame highlightimpl111) {
      this.field9.method4(highlightimpl111);
   }

   private void method4(CancellableEvent highlightimpl1) {
      if (!this.method15().isFirstPerson() || Ref.method3().bridge$getRenderViewEntity() != Ref.method7()) {
         highlightimpl1.cancel();
      }
   }

   private void method5(EventPreRenderPlayer highlightimpl91) {
      if (highlightimpl91.method3().bridge$isSelf() && Ref.method3().bridge$getSpectatedEntity() != null) {
         highlightimpl91.cancel();
      }
   }

   private void method6(com.moonsworth.lunar.client.event.mixin.highlight.EventFovModifier.FovInput data1) {
      if ((Boolean)this.field18.get()) {
         data1.ICOOHRIORIOOIIRRIHHOOIOHHCORIR((Float)this.field19.get());
      }
   }

   private void method7(HudRenderLegacyEvent highlightimpl21) {
      RewindHandlers rewindhandlers2 = ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method6();
      if (!rewindhandlers2.method40().method31()) {
         ReplayTimeline highlight_33 = ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method4();
         if (highlight_33 != null && (highlight_33.isPaused() || !rewindhandlers2.method44()) && !rewindhandlers2.method57().method25()) {
            EntityRenderDispatcherBridge bridge2_434 = Ref.method13();
            if (bridge2_434 != null) {
               Vector3d vector3d5 = new Vector3d(bridge2_434.bridge$renderPosX(), bridge2_434.bridge$renderPosY(), bridge2_434.bridge$renderPosZ());
               LinkedHashSet set6 = new LinkedHashSet();
               Set set7 = new LinkedHashSet();
               float value8 = 1.54F;
               if (Ref.method3().bridge$getRenderViewEntity() != null) {
                  value8 = Ref.method3().bridge$getRenderViewEntity().bridge$getEyeHeight();
               }

               for (Track gui_210 : highlight_33.method11()) {
                  Entry entry11 = gui_210.method1(highlight_33.method15());
                  if (entry11 != null) {
                     PropertyGroup fishing2iterator12 = ((RewindIterator)entry11.getValue()).method18().get("camera");
                     if (fishing2iterator12 != null) {
                        KeyframeProperty fishing2loader13 = fishing2iterator12.method12().get(this.method16().getId());
                        KeyframeProperty fishing2loader14 = fishing2iterator12.method12().get(this.field11.getId());
                        KeyframeProperty fishing2loader15 = fishing2iterator12.method12().get(this.field12.getId());
                        BridgeExtension bridgeextension16 = this.method17();
                        List list17 = List.of(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
                        if (bridgeextension16 != null) {
                           double value18 = bridgeextension16.bridge$getPreviousRotationYaw()
                              + (bridgeextension16.bridge$getRotationYaw() - bridgeextension16.bridge$getPreviousRotationYaw()) * highlightimpl21.method5();
                           list17 = List.of(
                              bridgeextension16.method3() + (bridgeextension16.bridge$getPosX() - bridgeextension16.method3()) * highlightimpl21.method5(),
                              bridgeextension16.method4() + (bridgeextension16.bridge$getPosY() - bridgeextension16.method4()) * highlightimpl21.method5(),
                              bridgeextension16.method5() + (bridgeextension16.bridge$getPosZ() - bridgeextension16.method5()) * highlightimpl21.method5(),
                              bridgeextension16 instanceof EntityLivingBridge bridgeextension2_520
                                 ? (double)bridgeextension2_520.bridge$getPrevRotationYawHead()
                                    + (bridgeextension2_520.bridge$getRotationYawHead() - bridgeextension2_520.bridge$getPrevRotationYawHead()) * highlightimpl21.method5()
                                 : value18,
                              bridgeextension16.bridge$getPreviousRotationPitch()
                                 + (bridgeextension16.bridge$getRotationPitch() - bridgeextension16.bridge$getPreviousRotationPitch()) * highlightimpl21.method5(),
                              (Double)((List)this.method16().get()).get(5),
                              value18
                           );
                        }

                        if (!((CameraMode)this.field11.get()).isFixedToPlayer()) {
                           set6 = new LinkedHashSet();
                           int number32 = highlight_33.method13().getFps();
                           int number19 = 30 * number32;
                           int number33 = Math.max(highlight_33.method15() - number19, (Integer)((Range)entry11.getKey()).getMinimum());
                           int number21 = Math.min(highlight_33.method15() + number19, (Integer)((Range)entry11.getKey()).getMaximum());
                           int number22 = Integer.MIN_VALUE;
                           List list23 = null;

                           for (int index24 = number33; index24 <= number21; index24++) {
                              int number25 = index24 - (Integer)((Range)entry11.getKey()).getMinimum();
                              List list26 = (List)fishing2loader13.getAt(number25);
                              CameraFollowMode gui2extension27 = null;
                              if (fishing2loader13.method27().containsKey(index24 - (Integer)((Range)entry11.getKey()).getMinimum())) {
                                 if (index24 == highlight_33.method15()) {
                                    list23 = null;
                                    continue;
                                 }

                                 if (fishing2loader14.getAt(number25) == CameraMode.FOLLOW) {
                                    gui2extension27 = (CameraFollowMode)fishing2loader15.getAt(number25);
                                 }

                                 set6.addAll(this.method8(highlight_33, list26, list17, gui2extension27, value8, vector3d5));
                              }

                              if (list23 != null && index24 > number22 + number32 / 10) {
                                 Vector3d vector3d28 = new Vector3d((Double)list23.get(0), (Double)list23.get(1) + value8, (Double)list23.get(2)).sub(vector3d5);
                                 Vector3d vector3d29 = new Vector3d((Double)list26.get(0), (Double)list26.get(1) + value8, (Double)list26.get(2)).sub(vector3d5);
                                 Vector3d vector3d30 = new Vector3d(0.0, 0.0, 0.0);
                                 if (fishing2loader14.getAt(number25) == CameraMode.FOLLOW) {
                                    vector3d30 = new Vector3d((Double)list17.get(0), (Double)list17.get(1), (Double)list17.get(2)).sub(vector3d5);
                                 }

                                 set6.add(new LineSegment(vector3d28.add(vector3d30), vector3d29.add(vector3d30)));
                                 number22 = index24;
                                 list23 = list26;
                              }

                              if (list23 == null) {
                                 list23 = list26;
                              }
                           }

                           int number34 = highlight_33.method15() - (Integer)((Range)entry11.getKey()).getMinimum();
                           List list35 = (List)fishing2loader13.getAt(number34);
                           CameraFollowMode gui2extension36 = null;
                           if (fishing2loader14.getAt(number34) == CameraMode.FOLLOW) {
                              gui2extension36 = (CameraFollowMode)fishing2loader15.getAt(number34);
                           }

                           Vector3d vector3d37 = new Vector3d((Double)list35.get(0), (Double)list35.get(1), (Double)list35.get(2));
                           Vector3d vector3d38 = new Vector3d(
                              (Double)((List)this.method16().get()).get(0),
                              (Double)((List)this.method16().get()).get(1),
                              (Double)((List)this.method16().get()).get(2)
                           );
                           if (vector3d37.distance(vector3d38) > 0.5) {
                              set7 = this.method8(highlight_33, list35, list17, gui2extension36, value8, vector3d5);
                           }
                        } else if (bridgeextension16 != null && !rewindhandlers2.method44()) {
                           set7 = this.method8(highlight_33, list17, list17, null, bridgeextension16.bridge$getEyeHeight(), vector3d5);
                        }
                     }
                  }
               }

               AbstractRenderContext bridgeextension_931 = highlightimpl21.method3();
               bridgeextension_931.push();
               if (!set6.isEmpty()) {
                  WorldRenderUtils.renderLinesJoml(bridgeextension_931, set6, -2364929);
               }

               if (!set7.isEmpty()) {
                  WorldRenderUtils.renderLinesJoml(bridgeextension_931, set7, -2171325);
               }

               bridgeextension_931.pop();
            }
         }
      }
   }

   private Set<LineSegment> method8(ReplayTimeline highlight_31, List<Double> list2, List<Double> list3, CameraFollowMode gui2extension4, float value5, Vector3d vector3d6) {
      LinkedHashSet set7 = new LinkedHashSet();
      double value8 = (Double)list2.get(0);
      double value10 = (Double)list2.get(1) + value5;
      double value12 = (Double)list2.get(2);
      double value14 = (Double)list2.get(3) + 180.0;
      double value16 = (Double)list2.get(4);
      double value18 = (Double)list2.get(5);
      if (gui2extension4 != null) {
         if (gui2extension4 == CameraFollowMode.POSITION) {
            value8 += list3.get(0);
            value10 += list3.get(1);
            value12 += list3.get(2);
         } else {
            boolean flag20 = gui2extension4 == CameraFollowMode.POS_ROT_HEAD;
            double value21 = Math.toRadians((Double)list3.get(flag20 ? 3 : 6));
            double value23 = Math.toRadians((Double)list3.get(4));
            double value25 = value8;
            double value27 = value10 * Math.cos(value23) - value12 * Math.sin(value23);
            double value29 = value10 * Math.sin(value23) + value12 * Math.cos(value23);
            double value31 = value25 * Math.cos(value21) - value29 * Math.sin(value21);
            double value33 = value27;
            double value35 = value25 * Math.sin(value21) + value29 * Math.cos(value21);
            value8 = (Double)list3.get(0) + value31;
            value10 = (Double)list3.get(1) + value33;
            value12 = (Double)list3.get(2) + value35;
            if (flag20) {
               value14 += list3.get(6);
               value16 += list3.get(4);
            } else {
               value14 += list3.get(3);
            }
         }
      }

      double value74 = Math.toRadians(
         this.field18.get() ? ((Float)this.field19.get()).floatValue() : Ref.method3().bridge$getGameSettings().bridge$getFov()
      );
      double value22 = (double)highlight_31.method13().getWidth() / highlight_31.method13().getHeight();
      double value24 = 0.1;
      double value26 = 0.5;
      Vector3d vector3d28 = new Vector3d(value8, value10, value12);
      double value75 = Math.toRadians(value14);
      double value76 = Math.toRadians(value16);
      double value77 = Math.toRadians(value18);
      double value78 = Math.cos(value76);
      double value37 = Math.sin(value76);
      double value39 = Math.sin(value75);
      double value41 = Math.cos(value75);
      Vector3d vector3d43 = new Vector3d(value39 * value78, -value37, -value41 * value78).normalize();
      Vector3d vector3d44 = new Vector3d(0.0, 1.0, 0.0);
      Vector3d vector3d45 = new Vector3d(vector3d43).cross(vector3d44).normalize();
      Vector3d vector3d46 = new Vector3d(vector3d45).cross(vector3d43).normalize();
      Vector3d vector3d47 = new Vector3d(vector3d46).mul(new Matrix3d().rotation(value77, vector3d43));
      vector3d45 = new Vector3d(vector3d43).cross(vector3d47).normalize();
      Vector3d vector3d48 = new Vector3d(vector3d28).fma(value24, vector3d43).sub(vector3d6);
      Vector3d vector3d49 = new Vector3d(vector3d28).fma(value26, vector3d43).sub(vector3d6);
      double value50 = 2.0 * Math.tan(value74 / 2.0) * value24;
      double value52 = value50 * value22;
      double value54 = 2.0 * Math.tan(value74 / 2.0) * value26;
      double value56 = value54 * value22;
      double value58 = value50 * 0.5;
      double value60 = value52 * 0.5;
      double value62 = value54 * 0.5;
      double value64 = value56 * 0.5;
      Vector3d vector3d66 = new Vector3d(vector3d48).fma(value58, vector3d47).fma(-value60, vector3d45);
      Vector3d vector3d67 = new Vector3d(vector3d48).fma(value58, vector3d47).fma(value60, vector3d45);
      Vector3d vector3d68 = new Vector3d(vector3d48).fma(-value58, vector3d47).fma(-value60, vector3d45);
      Vector3d vector3d69 = new Vector3d(vector3d48).fma(-value58, vector3d47).fma(value60, vector3d45);
      Vector3d vector3d70 = new Vector3d(vector3d49).fma(value62, vector3d47).fma(-value64, vector3d45);
      Vector3d vector3d71 = new Vector3d(vector3d49).fma(value62, vector3d47).fma(value64, vector3d45);
      Vector3d vector3d72 = new Vector3d(vector3d49).fma(-value62, vector3d47).fma(-value64, vector3d45);
      Vector3d vector3d73 = new Vector3d(vector3d49).fma(-value62, vector3d47).fma(value64, vector3d45);
      set7.add(new LineSegment(vector3d66, vector3d67));
      set7.add(new LineSegment(vector3d67, vector3d69));
      set7.add(new LineSegment(vector3d69, vector3d68));
      set7.add(new LineSegment(vector3d68, vector3d66));
      set7.add(new LineSegment(vector3d70, vector3d71));
      set7.add(new LineSegment(vector3d71, vector3d73));
      set7.add(new LineSegment(vector3d73, vector3d72));
      set7.add(new LineSegment(vector3d72, vector3d70));
      set7.add(new LineSegment(vector3d66, vector3d70));
      set7.add(new LineSegment(vector3d67, vector3d71));
      set7.add(new LineSegment(vector3d68, vector3d72));
      set7.add(new LineSegment(vector3d69, vector3d73));
      return set7;
   }

   private void method9(EventMouseWheel highlightimpl61) {
      highlightimpl61.cancel();
   }

   private void method10(EventMouseScroll highlightimpl41) {
      if (((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method6().method62()) {
         this.field9.method2(highlightimpl41);
      }
   }

   private void method11(EventMouseMove highlightimpl141) {
      highlightimpl141.cancel();
      this.field9.method3(highlightimpl141);
   }

   public float[] method14() {
      ReplayTimeline highlight_31 = ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method6().method40().method37();
      if (highlight_31 != null && (Boolean)this.field15.get()) {
         float value2 = (Float)this.field16.get();
         float value3 = (Float)this.field17.get();
         double value4 = highlight_31.method13().getFps();
         float value6 = (float)Math.sin(highlight_31.method15() / value4 * 1.5 * value3) * value2;
         float value7 = (float)Math.cos(highlight_31.method15() / value4 * 1.2 * value3) * value2;
         return new float[]{value6, value7};
      } else {
         return new float[]{0.0F, 0.0F};
      }
   }

   public CameraMode method15() {
      return !((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method6().method44() ? CameraMode.FREE_CAMERA : (CameraMode)this.field11.get();
   }

   public MultiNumberOption<Double> method16() {
      return this.field11.get() == CameraMode.FOLLOW ? this.field14 : this.field13;
   }

   public BridgeExtension method17() {
      try {
         Object obj1 = Ref.method7();
         if (!this.field10.isDefault()) {
            String text2 = (String)this.field10.get();
            if (text2.contains("-")) {
               UUID uuid3 = UUID.fromString((String)this.field10.get());
               obj1 = (BridgeExtension)Ref.method8().bridge$getPlayerByUniqueId(uuid3).orElseThrow();
            } else {
               obj1 = (BridgeExtension)Ref.method8().bridge$getEntityById(Integer.parseInt(text2)).orElseThrow();
            }
         }

         return (BridgeExtension)obj1;
      } catch (Exception exception4) {
         return Ref.method7();
      }
   }

   public boolean method19() {
      return !this.field10.isDefault();
   }

   private ClientOption<String> method21() {
      return ((com.moonsworth.lunar.client.config.option.OptionFactory.SimpleOptionBuilder)((com.moonsworth.lunar.client.config.option.OptionFactory.SimpleOptionBuilder)((com.moonsworth.lunar.client.config.option.OptionFactory.SimpleOptionBuilder)OptionFactory.method42(
                     "target", Codec.STRING
                  )
                  .HIIIOHRRROCICIOIORRRIRCRCHHIII(""))
               .HORHROIOIOICIRHIOCOICHHHIHCIIO(DriverFieldType.PLAYERS))
            .IICIROOOIIIHCHICHIRRRRIRHHHHOR(
               arg1 -> {
                  JsonArray array2 = new JsonArray();
                  if (Ref.method8() != null) {
                     for (Bridge6_10 bridge6_104 : Ref.method8().bridge$getPlayerEntities()) {
                        JsonObject json5 = new JsonObject();
                        json5.addProperty(
                           "name", TextBridge.stripColor(bridge6_104.bridge$getName()).isBlank() ? bridge6_104.bridge$getUniqueID().toString() : bridge6_104.bridge$getName()
                        );
                        json5.addProperty("uuid", bridge6_104.bridge$getUniqueID().toString());
                        array2.add(json5);
                     }
                  }

                  if (!((String)this.field10.get()).isEmpty() && !((String)this.field10.get()).contains("-")) {
                     String text7 = null;

                     try {
                        text7 = EntityLookup.method2(Integer.parseInt((String)this.field10.get()));
                     } catch (Exception exception6) {
                        LunarLogger.method4("Rewind", "Failed to get entity name for ID: %s %s", new Object[]{this.field10.get(), exception6.getMessage()});
                     }

                     if (text7 == null) {
                        text7 = "Entity";
                     }

                     String text9 = (String)this.field10.get();
                     if (text9.length() > 3) {
                        text9 = text9.substring(text9.length() - 3);
                     }

                     JsonObject json10 = new JsonObject();
                     json10.addProperty("name", text7 + " (" + text9 + ")");
                     json10.addProperty("uuid", (String)this.field10.get());
                     json10.addProperty("noAvatar", true);
                     array2.add(json10);
                  }

                  arg1.add("players", array2);
                  if (Ref.method7() != null) {
                     JsonObject json8 = new JsonObject();
                     json8.addProperty(
                        "name",
                        TextBridge.stripColor(Ref.method7().bridge$getName()).isBlank()
                           ? Ref.method7().bridge$getUniqueID().toString()
                           : Ref.method7().bridge$getName()
                     );
                     json8.addProperty("uuid", Ref.method7().bridge$getUniqueID().toString());
                     arg1.add("player", json8);
                  }
               }
            ))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   }

   @Generated
   public ClientOption<String> method22() {
      return this.field10;
   }

   @Generated
   public EnumOption<CameraMode> method23() {
      return this.field11;
   }

   @Generated
   public EnumOption<CameraFollowMode> method24() {
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
