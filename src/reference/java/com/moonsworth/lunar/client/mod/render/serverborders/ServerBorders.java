package com.moonsworth.lunar.client.mod.render.serverborders;

import com.moonsworth.lunar.bridge.CameraBridge;
import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.WorldBorderExtensionBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.replay.render.WorldRenderHandler;
import com.moonsworth.lunar.client.render.particle.ClampUtils;
import com.moonsworth.lunar.client.event.entity.EventEntityCollisionBoxes;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindMod;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import java.util.Map;

public class ServerBorders extends AbstractFeature {
   public ServerBorders() {
      super(true);
      if (Ref.MC_VERSION <= 5) {
         this.handle(HudRenderLegacyEvent.class, this::renderBorders);
         this.handle(EventEntityCollisionBoxes.class, this::addBorderCollisionBoxes);
      }
   }

   public String getId() {
      return "SERVER_BORDERS";
   }

   protected void method1(boolean flag1) {
   }

   protected ModDetails method20() {
      return null;
   }

   private void renderBorders(HudRenderLegacyEvent highlightimpl21) {
      this.renderWorldBorders(highlightimpl21.method3(), Ref.method7(), highlightimpl21.method4(), highlightimpl21.method5());
   }

   private void addBorderCollisionBoxes(EventEntityCollisionBoxes highlightimpl71) {
      Map map2 = Ref.method4().method63().method2();
      if (!map2.isEmpty()) {
         BridgeExtension bridgeextension3 = highlightimpl71.method1();
         if (bridgeextension3 != null && bridgeextension3 == Ref.method7()) {
            double value4 = bridgeextension3.bridge$getMotionX();
            double value6 = bridgeextension3.bridge$getMotionY();
            double value8 = bridgeextension3.bridge$getMotionZ();
            if (value4 != 0.0 || value8 != 0.0) {
               double value10 = bridgeextension3.bridge$getPosX();
               double value12 = bridgeextension3.bridge$getPosZ();
               AxisAlignedBBBridge horsestats1214 = bridgeextension3.bridge$getBoundingBox();
               AxisAlignedBBBridge horsestats1215;
               if (Ref.MC_VERSION <= 0) {
                  horsestats1215 = horsestats1214.bridge$expand(Math.abs(value4), Math.abs(value6), Math.abs(value8));
               } else {
                  horsestats1215 = horsestats1214.bridge$union(horsestats1214.bridge$offset(value4, value6, value8));
               }

               for (WorldBorderExtensionBridge itemcounter4extension17 : map2.values()) {
                  if (itemcounter4extension17.shouldRender()) {
                     boolean flag18 = itemcounter4extension17.contains(value10, value12);
                     boolean flag19 = itemcounter4extension17.contains(value10 + value4, value12 + value8);
                     if ((itemcounter4extension17.isCancelEntry() || flag18 || flag19) && (itemcounter4extension17.isCancelExit() || !flag18 || !flag19)) {
                        for (AxisAlignedBBBridge horsestats1221 : itemcounter4extension17.method8()) {
                           if (horsestats1221.bridge$intersectsWith(horsestats1215)) {
                              highlightimpl71.method3().add(horsestats1221);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
   }

   private void renderWorldBorders(AbstractRenderContext bridgeextension_91, BridgeExtension bridgeextension2, CameraBridge bridge2_193, float value4) {
      RewindMod rewind5 = Ref.method4().method40().method85();
      if (rewind5.method19()) {
         WorldRenderHandler rewindhandlers3impl66 = rewind5.method35().method52();
         if ((Boolean)rewindhandlers3impl66.method14().get() && !(Boolean)rewindhandlers3impl66.method25().get()) {
            return;
         }
      }

      double value51 = this.mc.bridge$getGameSettings().bridge$getRenderDistance() * 16;
      boolean flag8 = Ref.MC_VERSION >= 6;
      DrawBufferBridge bridge2_329 = bridgeextension_91.method10(LunarRenderTypes.field50);

      for (WorldBorderExtensionBridge itemcounter4extension11 : Client.method109().method63().method2().values()) {
         if (itemcounter4extension11.shouldRender()) {
            double value12 = itemcounter4extension11.OOICIIIHOCOHHHHIHRRHOIOHIRCOII();
            double value14 = itemcounter4extension11.IOCCOCICCHCRHCCIHHCRRIRHORCCCR();
            double value16 = itemcounter4extension11.RHHIOOHRICOHORORROIIRHHICOORIO();
            double value18 = itemcounter4extension11.RICHICOHRRICRIOOCRCHHRRRIRIIIO();
            double value20 = flag8 ? bridge2_193.bridge$getPosX() : bridgeextension2.bridge$getPosX();
            double value22 = flag8 ? bridge2_193.bridge$getPosY() : bridgeextension2.bridge$getPosY();
            double value24 = flag8 ? bridge2_193.bridge$getPosZ() : bridgeextension2.bridge$getPosZ();
            WorldBridgeExtension itemcounter6extension26 = Ref.method8();
            if (value20 <= value14 + value51 && value20 >= value12 - value51 && value24 <= value18 + value51 && value24 >= value16 - value51) {
               double value27 = flag8 ? itemcounter4extension11.method12(bridge2_193.bridge$getPosX(), bridge2_193.bridge$getPosZ()) : itemcounter4extension11.method1(bridgeextension2);
               int number29 = itemcounter6extension26 == null ? 0 : itemcounter6extension26.bridge$getMinBuildHeight();
               int number30 = itemcounter6extension26 == null ? 256 : itemcounter6extension26.bridge$getMaxBuildHeight();
               double value31 = 1.0 - value27 / value51;
               value31 = Math.pow(value31, 4.0);
               double value33 = flag8 ? value20 : bridgeextension2.bridge$lastTickX() + (bridgeextension2.bridge$getPosX() - bridgeextension2.bridge$lastTickX()) * value4;
               double value35 = flag8 ? value22 : bridgeextension2.bridge$lastTickY() + (bridgeextension2.bridge$getPosY() - bridgeextension2.bridge$lastTickY()) * value4;
               double value37 = flag8 ? value24 : bridgeextension2.bridge$lastTickZ() + (bridgeextension2.bridge$getPosZ() - bridgeextension2.bridge$lastTickZ()) * value4;
               bridgeextension_91.push();
               bridgeextension_91.translate(-value33, -value35, -value37);
               bridgeextension_91.method26(-3.0F, -3.0F);
               bridgeextension_91.method24();
               float value39 = (float)(Ref.method3().bridge$getSystemTime() % 3000L) / 3000.0F;
               bridge2_329.method1();
               double value40 = Math.max(MathUtils.method9(value37 - value51), value16);
               double value42 = Math.min(MathUtils.method8(value37 + value51), value18);
               int number44 = itemcounter4extension11.getColor() & 16777215 | (int)(ClampUtils.clamp(value31, 0.0, 1.0) * 255.0) << 24;
               if (value33 > value14 - value51) {
                  float value45 = 0.0F;

                  for (double value46 = value40; value46 < value42; value45 += 0.5F) {
                     double value48 = Math.min(1.0, value42 - value46);
                     float value50 = (float)value48 * 0.5F;
                     bridge2_329.method2(value14, number30, value46).method10(value39 + value45, value39 + 0.0F).method9(number44).method16();
                     bridge2_329.method2(value14, number30, value46 + value48).method10(value39 + value50 + value45, value39 + 0.0F).method9(number44).method16();
                     bridge2_329.method2(value14, number29, value46 + value48).method10(value39 + value50 + value45, value39 + 128.0F).method9(number44).method16();
                     bridge2_329.method2(value14, number29, value46).method10(value39 + value45, value39 + 128.0F).method9(number44).method16();
                     value46++;
                  }
               }

               if (value33 < value12 + value51) {
                  float value55 = 0.0F;

                  for (double value58 = value40; value58 < value42; value55 += 0.5F) {
                     double value61 = Math.min(1.0, value42 - value58);
                     float value64 = (float)value61 * 0.5F;
                     bridge2_329.method2(value12, number30, value58).method10(value39 + value55, value39 + 0.0F).method9(number44).method16();
                     bridge2_329.method2(value12, number30, value58 + value61).method10(value39 + value64 + value55, value39 + 0.0F).method9(number44).method16();
                     bridge2_329.method2(value12, number29, value58 + value61).method10(value39 + value64 + value55, value39 + 128.0F).method9(number44).method16();
                     bridge2_329.method2(value12, number29, value58).method10(value39 + value55, value39 + 128.0F).method9(number44).method16();
                     value58++;
                  }
               }

               value40 = Math.max(MathUtils.method9(value33 - value51), value12);
               value42 = Math.min(MathUtils.method9(value33 + value51), value14);
               if (value37 > value18 - value51) {
                  float value56 = 0.0F;

                  for (double value59 = value40; value59 < value42; value56 += 0.5F) {
                     double value62 = Math.min(1.0, value42 - value59);
                     float value65 = (float)value62 * 0.5F;
                     bridge2_329.method2(value59, number30, value18).method10(value39 + value56, value39 + 0.0F).method9(number44).method16();
                     bridge2_329.method2(value59 + value62, number30, value18).method10(value39 + value65 + value56, value39 + 0.0F).method9(number44).method16();
                     bridge2_329.method2(value59 + value62, number29, value18).method10(value39 + value65 + value56, value39 + 128.0F).method9(number44).method16();
                     bridge2_329.method2(value59, number29, value18).method10(value39 + value56, value39 + 128.0F).method9(number44).method16();
                     value59++;
                  }
               }

               if (value37 < value16 + value51) {
                  float value57 = 0.0F;

                  for (double value60 = value40; value60 < value42; value57 += 0.5F) {
                     double value63 = Math.min(1.0, value42 - value60);
                     float value66 = (float)value63 * 0.5F;
                     bridge2_329.method2(value60, number30, value16).method10(value39 + value57, value39 + 0.0F).method9(number44).method16();
                     bridge2_329.method2(value60 + value63, number30, value16).method10(value39 + value66 + value57, value39 + 0.0F).method9(number44).method16();
                     bridge2_329.method2(value60 + value63, number29, value16).method10(value39 + value66 + value57, value39 + 128.0F).method9(number44).method16();
                     bridge2_329.method2(value60, number29, value16).method10(value39 + value57, value39 + 128.0F).method9(number44).method16();
                     value60++;
                  }
               }

               bridge2_329.method17(BufferMode.BATCHED);
               bridgeextension_91.pop();
               bridgeextension_91.method26(0.0F, 0.0F);
               bridgeextension_91.method25();
            }
         }
      }
   }
}
