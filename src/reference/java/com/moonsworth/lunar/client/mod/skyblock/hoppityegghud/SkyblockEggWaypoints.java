package com.moonsworth.lunar.client.mod.skyblock.hoppityegghud;

import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.SkyblockCalendar;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.ChocolateEggLocations;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.ChocolateEggLocations.Data;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.mod.skyblock.hoppityegghud.SkyblockHoppityEggHud;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashMap;
import org.joml.Vector3i;

public class SkyblockEggWaypoints extends AbstractFeature {
   public SkyblockEggWaypoints(SkyblockHoppityEggHud skyblockhoppityegghud1, ToggleOption lightingextension4432) {
      super(true);
      this.method2(ModTraits.field16, ChildModBinding.method4(false, skyblockhoppityegghud1));
      this.method2(ModTraits.field6, ModEnabledState.method7(lightingextension4432));
      this.handle(HudRenderLegacyEventAlt.class, this::method2);
   }

   public String getId() {
      return "SKYBLOCK_EGG_WAYPOINTS";
   }

   protected void method1(boolean flag1) {
   }

   private void method2(HudRenderLegacyEventAlt highlightimpl41) {
      if (IslandUtils.isOnIsland()) {
         SkyblockHoppityEggHud skyblockhoppityegghud2 = (SkyblockHoppityEggHud)((ChildModBinding)this.method7(ModTraits.field16)).method1();
         if (SkyblockCalendar.isSpring() && !skyblockhoppityegghud2.method14()) {
            if (skyblockhoppityegghud2.method15() != null) {
               String text3 = skyblockhoppityegghud2.method21().method15();
               if ((Boolean)skyblockhoppityegghud2.method23().get() || text3 == null) {
                  ChocolateEggLocations fishing24 = skyblockhoppityegghud2.method15();
                  String text5 = IslandUtils.getIsland().name();
                  HashMap map6 = (HashMap)fishing24.method1().get(text5);
                  if (map6 != null) {
                     AbstractRenderContext bridgeextension_97 = highlightimpl41.method3();
                     EntityRenderDispatcherBridge bridge2_438 = Ref.method13();
                     bridgeextension_97.push();
                     bridgeextension_97.translate(-bridge2_438.bridge$renderPosX(), -bridge2_438.bridge$renderPosY(), -bridge2_438.bridge$renderPosZ());
                     DrawBufferBridge bridge2_329 = bridgeextension_97.method10(LunarRenderTypes.field52);
                     bridge2_329.method1();

                     for (String text11 : map6.keySet()) {
                        if (!text11.equals(text3)) {
                           Data data12 = (Data)map6.get(text11);
                           Vector3i vector3i13 = data12.method1();
                           if (skyblockhoppityegghud2.method7(text5, text11)) {
                              WorldRenderUtils.fillBox(bridge2_329, vector3i13.x(), vector3i13.y(), vector3i13.z(), vector3i13.x() + 1, vector3i13.y() + 1, vector3i13.z() + 1, skyblockhoppityegghud2.method27().method1(0.0F));
                           } else {
                              WorldRenderUtils.fillBox(bridge2_329, vector3i13.x(), vector3i13.y(), vector3i13.z(), vector3i13.x() + 1, vector3i13.y() + 1, vector3i13.z() + 1, skyblockhoppityegghud2.method25().method1(0.0F));
                           }
                        }
                     }

                     bridge2_329.method17(BufferMode.BATCHED);
                     BufferBuilderBridge bridge_2815 = bridgeextension_97.method11(1.0F);

                     for (String text18 : map6.keySet()) {
                        if (!text18.equals(text3)) {
                           Data data20 = (Data)map6.get(text18);
                           Vector3i vector3i14 = data20.method1();
                           if (skyblockhoppityegghud2.method7(text5, text18)) {
                              WorldRenderUtils.drawBoxOutline(
                                 bridge_2815,
                                 vector3i14.x(),
                                 vector3i14.y(),
                                 vector3i14.z(),
                                 vector3i14.x() + 1,
                                 vector3i14.y() + 1,
                                 vector3i14.z() + 1,
                                 ColorUtils.method31(skyblockhoppityegghud2.method27().method1(0.0F))
                              );
                           } else {
                              WorldRenderUtils.drawBoxOutline(
                                 bridge_2815,
                                 vector3i14.x(),
                                 vector3i14.y(),
                                 vector3i14.z(),
                                 vector3i14.x() + 1,
                                 vector3i14.y() + 1,
                                 vector3i14.z() + 1,
                                 ColorUtils.method31(skyblockhoppityegghud2.method25().method1(0.0F))
                              );
                           }
                        }
                     }

                     bridge_2815.end();

                     for (String text19 : map6.keySet()) {
                        if (!text19.equals(text3)) {
                           Data data21 = (Data)map6.get(text19);
                           Vector3i vector3i22 = data21.method1();
                           if (skyblockhoppityegghud2.method7(text5, text19)) {
                              WorldRenderUtils.drawString(
                                 bridgeextension_97,
                                 "Egg (Duplicate)",
                                 vector3i22.x() + 0.5,
                                 vector3i22.y() + 1.5,
                                 vector3i22.z() + 0.5,
                                 ColorUtils.method31(skyblockhoppityegghud2.method27().method1(0.0F)),
                                 true
                              );
                           } else {
                              WorldRenderUtils.drawString(
                                 bridgeextension_97,
                                 "Egg",
                                 vector3i22.x() + 0.5,
                                 vector3i22.y() + 1.5,
                                 vector3i22.z() + 0.5,
                                 ColorUtils.method31(skyblockhoppityegghud2.method25().method1(0.0F)),
                                 true
                              );
                           }
                        }
                     }

                     bridgeextension_97.pop();
                  }
               }
            }
         }
      }
   }
}
