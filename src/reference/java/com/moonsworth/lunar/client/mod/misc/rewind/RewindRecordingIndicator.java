package com.moonsworth.lunar.client.mod.misc.rewind;

import com.moonsworth.lunar.bridge.GuiContainerCreativeBridge;
import com.moonsworth.lunar.bridge.GuiRecipeBookBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.GuiMainMenuBridge;
import com.moonsworth.lunar.bridge.GuiIngameMenuBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.GuiMultiplayerBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.ui.widget.TextShadowWidget;
import com.moonsworth.lunar.client.ui.hud.HudEditorScreen;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.ui.hud.HudConditionSet;
import com.moonsworth.lunar.client.replay.recording.RecorderState;
import com.moonsworth.lunar.client.event.input.EventMarkerInput;
import com.moonsworth.lunar.client.event.input.MouseInputType;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindMod;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;
import com.moonsworth.lunar.client.util.text.TimeFormatting;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.ui.MousePosition;
import java.util.List;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class RewindRecordingIndicator extends AbstractFeature {
   public static final ResourceLocationBridge field8 = ResourceLocationBridge.create("lunar", "icons/rewind/circle.png");
   public static final ResourceLocationBridge field9 = ResourceLocationBridge.create("lunar", "icons/rewind/circle-pause.png");
   public static final ResourceLocationBridge field10 = ResourceLocationBridge.create("lunar", "icons/rewind/marker.png");
   public static final ResourceLocationBridge field11 = ResourceLocationBridge.create("lunar", "icons/rewind/pause.png");
   public static final ResourceLocationBridge field12 = ResourceLocationBridge.create("lunar", "icons/rewind/play.png");
   public static final ResourceLocationBridge field13 = ResourceLocationBridge.create("lunar", "icons/rewind/record.png");
   public static final ResourceLocationBridge field14 = ResourceLocationBridge.create("lunar", "icons/rewind/shadow.png");
   public static final ResourceLocationBridge field15 = ResourceLocationBridge.create("lunar", "icons/rewind/stop.png");
   private final ToggleOption field16 = (ToggleOption)OptionFactory.method7("showOnlyOnPauseScreen").method31();
   private final ToggleOption field17 = (ToggleOption)OptionFactory.method7("showShadowRecording").method31();
   private final TextShadowWidget field18 = new TextShadowWidget(null, "record", field13);
   private final TextShadowWidget field19 = new TextShadowWidget(null, "", field14);
   private final TextShadowWidget field20 = new TextShadowWidget(null, "", field15);
   private final TextShadowWidget field21 = new TextShadowWidget(null, "", field11);
   private final TextShadowWidget field22 = new TextShadowWidget(null, "", field10);
   private boolean canInteract;
   private boolean field23;
   private boolean field24;

   public RewindRecordingIndicator(RewindMod rewind1) {
      super(true);
      this.method2(ModTraits.field16, ChildModBinding.method3(rewind1));
      this.method2(ModTraits.field1, this.method14());
      this.handle(EventMarkerInput.class, arg1x -> {
         if (FeatureFlag.REWIND.isEnabled()) {
            if (this.field23 && this.canInteract) {
               if (arg1x.method3() == 0 && arg1x.method4() == MouseInputType.CLICK) {
                  MixinCore9Extension mixincore9extension2 = (MixinCore9Extension)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field1);
                  Data2 data23 = (Data2)arg1x.method2().method5().IHCORIOHOHHOIORHCCOOIIIHOCROOI(mixincore9extension2.getScale());

                  for (GuiWidget calculator2handler5 : this.method13()) {
                     if (calculator2handler5.method1(data23)) {
                        calculator2handler5.method6(data23, arg1x.method3());
                        arg1x.setCancelled(true);
                        return;
                     }
                  }
               }
            }
         }
      });
   }

   private List<TextShadowWidget> method13() {
      RewindMod rewind1 = (RewindMod)((ChildModBinding)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field16)).method1();
      RewindRecorder rewindhandlers52 = rewind1.method34();
      if (rewindhandlers52 != null && !rewindhandlers52.method22()) {
         return List.of(this.field20, this.field21, this.field22);
      } else {
         return rewindhandlers52 == null ? List.of(this.field18) : List.of(this.field18, this.field19);
      }
   }

   public boolean method2(GuiScreenBridge bridge5extension61, RewindRecorder rewindhandlers52) {
      boolean flag3 = bridge5extension61 instanceof GuiIngameMenuBridge
         || bridge5extension61 instanceof GuiMainMenuBridge
         || bridge5extension61 instanceof GuiMultiplayerBridge && Ref.method8() == null
         || DriverViewportLegacy.method50().method63().method21() && Ref.method8() == null;
      if (!(Boolean)this.field16.get() && rewindhandlers52 != null) {
         flag3 = flag3 || bridge5extension61 instanceof Bridge5Extension612 || bridge5extension61 instanceof GuiRecipeBookBridge || bridge5extension61 instanceof GuiContainerBridge || bridge5extension61 instanceof GuiContainerCreativeBridge;
      }

      return flag3;
   }

   private MixinCore9Extension method14() {
      return new TypedHudRenderer<String>(0.0F, 0.0F, HudAnchor.TOP_RIGHT) {
         public HudConditionSet method5() {
            return HudConditionSet.method5().method1(false).method3(false).method8();
         }

         public HudSize method15() {
            return HudSize.method1(10, 18, 22, 50, 56, Integer.MAX_VALUE);
         }

         public boolean method4(boolean flag1) {
            return (flag1 || !(Boolean)RewindRecordingIndicator.this.field16.get()) && super.method4(flag1);
         }

         @Nullable
         public String method5(boolean flag1) {
            if (!FeatureFlag.REWIND.isEnabled()) {
               return null;
            }

            if (!flag1 && (Boolean)RewindRecordingIndicator.this.field16.get()) {
               return null;
            }

            RewindMod rewind2 = (RewindMod)((ChildModBinding)RewindRecordingIndicator.this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field16)).method1();
            if (rewind2.method35() != null) {
               return null;
            }

            RewindRecorder rewindhandlers53 = rewind2.method34();
            GuiScreenBridge bridge5extension64 = Ref.method3().bridge$getCurrentScreen();
            RewindRecordingIndicator.this.canInteract = RewindRecordingIndicator.this.method2(bridge5extension64, rewindhandlers53);
            RewindRecordingIndicator.this.field23 = Ref.method3().bridge$getCurrentScreen() != null
               && (RewindRecordingIndicator.this.canInteract || flag1);
            RewindRecordingIndicator.this.field24 = false;
            TranslationManager foghandler285 = Client.method109().method67();
            String text6;
            if (rewindhandlers53 != null) {
               RewindRecordingIndicator.this.field24 = true;
               if (rewindhandlers53.method22()) {
                  if (!(Boolean)RewindRecordingIndicator.this.field17.get() && !RewindRecordingIndicator.this.field23) {
                     return null;
                  }

                  text6 = RewindRecordingIndicator.this.field17.get() ? foghandler285.method2("rewind", "shadow", new Object[0]) : "RewindMod";
                  RewindRecordingIndicator.this.field24 = (Boolean)RewindRecordingIndicator.this.field17.get();
               } else {
                  text6 = TimeFormatting.method2(rewindhandlers53.method16().method4());
               }
            } else {
               if (!RewindRecordingIndicator.this.field23) {
                  return null;
               }

               text6 = "RewindMod";
            }

            return text6;
         }

         protected float method5(boolean flag1, String text2, float value3) {
            float value4 = super.method14(flag1, text2, value3);
            boolean flag5 = !(Boolean)this.HIOICORHOCCRCOIHCRIIROIOIOIRIC.get();
            if (RewindRecordingIndicator.this.field24) {
               if (flag5) {
                  value4 += 4.0F;
               }

               value4 += 16.0F;
            }

            if (RewindRecordingIndicator.this.field23) {
               if (flag5) {
                  value4 += 4.0F;
               }

               for (TextShadowWidget calculator2impl67 : RewindRecordingIndicator.this.method13()) {
                  value4 += calculator2impl67.getWidth() + 5.0F;
               }
            }

            return value4;
         }

         public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
            if (!RewindRecordingIndicator.this.canInteract || flag4) {
               super.method3(highlightimpl1, value2, value3, flag4);
            }
         }

         protected float method19(float value1, float value2, float value3, boolean flag4) {
            boolean flag5 = !(Boolean)this.HIOICORHOCCRCOIHCRIIROIOIOIRIC.get();
            return value1 + (flag4 ? 4.0F : 0.0F) + (RewindRecordingIndicator.this.field24 ? (flag5 ? 18 : 14) : 0);
         }

         protected void method18(MixinHelper_4 mixinhelper_41, float value2, float value3, boolean flag4, boolean flag5) {
            if (!flag5) {
               if (flag4 || !(Boolean)RewindRecordingIndicator.this.field16.get()) {
                  RewindMod rewind6 = (RewindMod)((ChildModBinding)RewindRecordingIndicator.this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field16)).method1();
                  RewindRecorder rewindhandlers57 = rewind6.method34();
                  float value8 = value3 + this.getHeight() / 2.0F;
                  if (rewindhandlers57 != null) {
                     float value9 = 0.1F + (float)Math.sin(System.currentTimeMillis() % 2500L / 2500.0 * Math.PI * 2.0) * 0.1F;
                     int number10 = rewindhandlers57.method22() ? -7060035 : -969160;
                     number10 = ColorUtils.method22(number10, (int)(204.0F * (1.0F - value9)));
                     if (rewindhandlers57.method17() == RecorderState.PAUSED) {
                        mixinhelper_41.method24(RewindRecordingIndicator.field9, (int)(value2 + 2.5F), (int)(value8 - 6.0F), 12, 12, -2130706433);
                     } else if (!rewindhandlers57.method22() || (Boolean)RewindRecordingIndicator.this.field17.get()) {
                        mixinhelper_41.method24(RewindRecordingIndicator.field8, (int)(value2 + 2.5F), (int)(value8 - 6.0F), 12, 12, number10);
                     }
                  } else if (!RewindRecordingIndicator.this.field23) {
                     mixinhelper_41.method24(RewindRecordingIndicator.field8, (int)(value2 + 2.5F), (int)(value8 - 6.0F), 12, 12, -789498312);
                  }

                  if (RewindRecordingIndicator.this.field23) {
                     if (rewindhandlers57 != null) {
                        RewindRecordingIndicator.this.field21
                           .RCIOICOHRIOIIRRRROCRHCIICRROHO(rewindhandlers57.method17() == RecorderState.PAUSED ? "resume" : "pause", new Object[0]);
                        RewindRecordingIndicator.this.field21
                           .method4(
                              rewindhandlers57.method17() == RecorderState.PAUSED
                                 ? RewindRecordingIndicator.field12
                                 : RewindRecordingIndicator.field11
                           );
                     }

                     Data2 data213 = (Data2)MousePosition.method1().method5().IHCORIOHOHHOIORHCCOOIIIHOCROOI(this.getScale());
                     float value15 = value2 + this.getWidth();

                     for (TextShadowWidget calculator2impl612 : RewindRecordingIndicator.this.method13()) {
                        value15 = RewindRecordingIndicator.this.method4(calculator2impl612, value15, value8 + 0.5F);
                        calculator2impl612.setTextShadow((Boolean)this.ORHRIHRICHICRCOCIIRIOICOIICHOI.get());
                        RewindRecordingIndicator.this.method5(calculator2impl612, mixinhelper_41, data213);
                     }
                  }
               }
            }
         }
      };
   }

   private float method4(GuiWidget calculator2handler1, float value2, float value3) {
      calculator2handler1.method2(value2 - calculator2handler1.getWidth() - 5.0F, value3 - 8.0F, calculator2handler1.getWidth(), 16.0F);
      return calculator2handler1.getX();
   }

   private void method5(TextShadowWidget calculator2impl61, MixinHelper_4 mixinhelper_42, Data2 data23) {
      calculator2impl61.method3(mixinhelper_42, data23, this.canInteract && calculator2impl61.method3(data23));
   }

   public String getId() {
      return "REWIND_RECORDING_INDICATOR_CHILD";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field16, this.field17});
      this.method7(this.field18, "newRecording", () -> {
         RewindMod rewind1x = (RewindMod)((ChildModBinding)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field16)).method1();
         rewind1x.method23().HIIIORIICIRRHOCRIHROOIHCHOHCOO().forEach(Runnable::run);
      });
      this.method7(this.field19, "saveShadow", () -> {
         RewindMod rewind1x = (RewindMod)((ChildModBinding)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field16)).method1();
         rewind1x.method28().HIIIORIICIRRHOCRIHROOIHCHOHCOO().forEach(Runnable::run);
      });
      this.method7(this.field20, "stop", () -> {
         RewindMod rewind1x = (RewindMod)((ChildModBinding)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field16)).method1();
         rewind1x.method23().HIIIORIICIRRHOCRIHROOIHCHOHCOO().forEach(Runnable::run);
      });
      this.method7(this.field21, "pause", () -> {
         RewindMod rewind1x = (RewindMod)((ChildModBinding)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field16)).method1();
         rewind1x.method24().HIIIORIICIRRHOCRIHROOIHCHOHCOO().forEach(Runnable::run);
      });
      this.method7(this.field22, "addMarker", () -> {
         RewindMod rewind1x = (RewindMod)((ChildModBinding)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field16)).method1();
         rewind1x.method25().HIIIORIICIRRHOCRIHROOIHCHOHCOO().forEach(Runnable::run);
      });
      this.field18.method3(new AnimatedValue(-1057933768, -969160));
   }

   private void method7(GuiWidget calculator2handler1, String text2, Runnable runnable3) {
      calculator2handler1.method25((arg1x, arg2x) -> {
         if (Ref.method11() != HudEditorScreen.class) {
            com.moonsworth.lunar.client.ui.LcuiScreen.method15();
         }

         runnable3.run();
         return true;
      });
      calculator2handler1.method17(text2, new Object[0]);
      calculator2handler1.method34(false);
   }

   @Generated
   public ToggleOption method15() {
      return this.field16;
   }
}
