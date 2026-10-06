package com.moonsworth.lunar.client.mod.skyblock.kuudra;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension5_2;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.minecraft.EntityEquipmentSlotBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.mixin.KuudraPearlWaypoint;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.mixin.KuudraWaypoints;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.event.entity.EventEntityRemove;
import com.moonsworth.lunar.client.event.entity.EventEntityMovement;
import com.moonsworth.lunar.client.event.entity.EventEntitySpawn;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.mod.skyblock.kuudra.SkyblockKuudra;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.util.math.NumberUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import net.kyori.adventure.text.Component;
import org.joml.Vector3i;

public class SkyblockKuudraWaypoints extends AbstractFeature {
   private static final String field8 = "ewogICJ0aW1lc3RhbXAiIDogMTcyMDAyOTIzMDk5OSwKICAicHJvZmlsZUlkIiA6ICJkM2Y5MjEyMjY3YzM0YzEwYWNjOWZkNGI5MDFkYjI0ZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJkYXl3ZSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9mZDcyZGViMWFiMDAzM2I0MmIwYTEyZWZjZjQ4M2YwZmJhMjZkYzUxZGVkMzkxOWViYWRiNzBmOTY1N2ExZjYxIgogICAgfQogIH0KfQ==";
   private final HashMap<BridgeExtension, SkyblockKuudraWaypoints.Type> field9 = new HashMap<>();
   private KuudraPearlWaypoint field10;

   public SkyblockKuudraWaypoints(SkyblockKuudra skyblockkuudra1, ToggleOption lightingextension4432) {
      super(true);
      this.method14(ModTraits.field16, ChildModBinding.method4(false, skyblockkuudra1));
      this.method14(ModTraits.field6, ModEnabledState.method7(lightingextension4432));
      this.handle(EventEntitySpawn.class, this::method2);
      this.handle(EventEntityMovement.class, this::method3);
      this.handle(EventEntityRemove.class, this::method4);
      this.handle(EventTick.class, this::method5);
      this.handle(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent.class, arg1x -> {
         this.method6(arg1x);
         this.method7(arg1x);
         this.method8(arg1x);
      });
      this.handle(EventWorldChange.class, this::method9);
   }

   public String getId() {
      return "SKYBLOCK_KUUDRA_WAYPOINTS";
   }

   protected void method1(boolean flag1) {
   }

   private void method2(EventEntitySpawn highlightimpl6_21) {
      if (IslandUtils.getIsland() == SkyblockIsland.KUUDRA) {
         SkyblockKuudraWaypoints.Type type2 = this.method12(highlightimpl6_21.field1);
         if (type2 == null) {
            this.field9.remove(highlightimpl6_21.field1);
         } else {
            this.field9.put(highlightimpl6_21.field1, type2);
         }
      }
   }

   private void method3(EventEntityMovement highlightimpl211) {
      if (IslandUtils.getIsland() == SkyblockIsland.KUUDRA) {
         BridgeExtension bridgeextension2 = highlightimpl211.method1();
         if (bridgeextension2 instanceof Bridge5Extension5_2 && !this.field9.containsKey(bridgeextension2)) {
            SkyblockKuudraWaypoints.Type type3 = this.method12(bridgeextension2);
            if (type3 == null) {
               this.field9.remove(bridgeextension2);
            } else {
               this.field9.put(bridgeextension2, type3);
            }
         }
      }
   }

   private void method4(EventEntityRemove highlightimpl121) {
      if (IslandUtils.getIsland() == SkyblockIsland.KUUDRA) {
         this.field9.remove(highlightimpl121.method1());
      }
   }

   private void method5(EventTick highlightimpl21) {
      SkyblockKuudra skyblockkuudra2 = (SkyblockKuudra)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      if ((Boolean)skyblockkuudra2.method27().get()) {
         if (IslandUtils.getIsland() == SkyblockIsland.KUUDRA) {
            Bridge5Extension_5 bridge5extension_53 = Ref.method7();
            if (bridge5extension_53 != null) {
               HashMap map4 = this.method15();
               if (map4 != null) {
                  for (KuudraPearlWaypoint holograms6 : map4.values()) {
                     Vector3i vector3i7 = holograms6.method1();
                     double value8 = bridge5extension_53.bridge$getPosX() - vector3i7.x();
                     double value10 = bridge5extension_53.bridge$getPosY() - vector3i7.y();
                     double value12 = bridge5extension_53.bridge$getPosZ() - vector3i7.z();
                     double value14 = value8 * value8 + value10 * value10 + value12 * value12;
                     if (value14 <= 25.0) {
                        this.field10 = holograms6;
                        return;
                     }
                  }

                  this.field10 = null;
               }
            }
         }
      }
   }

   private void method6(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent highlightimpl21) {
      if (IslandUtils.getIsland() == SkyblockIsland.KUUDRA) {
         if (!this.field9.isEmpty()) {
            AbstractRenderContext bridgeextension_92 = highlightimpl21.method3();

            for (Entry entry4 : this.field9.entrySet()) {
               BridgeExtension bridgeextension5 = (BridgeExtension)entry4.getKey();
               SkyblockKuudraWaypoints.Type type6 = (SkyblockKuudraWaypoints.Type)entry4.getValue();
               if (this.method11(type6)) {
                  SkyblockKuudra skyblockkuudra7 = (SkyblockKuudra)((ChildModBinding)this.method7(ModTraits.field16)).method1();
                  switch (type6) {
                     case SUPPLY_PICKUP:
                        WorldRenderUtils.drawBeaconBeam(bridgeextension_92, bridgeextension5.bridge$getPosX() - 1.9, 256.0, bridgeextension5.bridge$getPosZ() + 3.25, skyblockkuudra7.method30().method14(0.0F));
                        break;
                     case SUPPLY_PLACE:
                        this.method10(bridgeextension_92, bridgeextension5, skyblockkuudra7.method30().method14(0.0F));
                        break;
                     case BUILD:
                        String text8 = TextBridge.getTextContent(bridgeextension5.bridge$getCustomName());
                        String text9 = text8.replaceAll("\\D", "");
                        if (!text9.isEmpty()) {
                           double value10 = NumberUtils.method2(text8.replaceAll("\\D", "")) / 100.0;
                           Color color12 = new Color(16711680);
                           Color color13 = new Color(65280);
                           int number14 = ColorUtils.method22(this.method13(color12, color13, value10), 191);
                           this.method10(bridgeextension_92, bridgeextension5, number14);
                        }
                        break;
                     case FUEL_CELL:
                        WorldRenderUtils.drawBeaconBeam(bridgeextension_92, bridgeextension5.bridge$getPosX() - 1.9, 256.0, bridgeextension5.bridge$getPosZ() + 3.25, skyblockkuudra7.method35().method14(0.0F));
                  }
               }
            }
         }
      }
   }

   private void method7(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent highlightimpl21) {
      SkyblockKuudra skyblockkuudra2 = (SkyblockKuudra)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      if ((Boolean)skyblockkuudra2.method27().get()) {
         if (IslandUtils.getIsland() == SkyblockIsland.KUUDRA) {
            HashMap map3 = this.method15();
            if (map3 != null) {
               AbstractRenderContext bridgeextension_94 = highlightimpl21.method3();

               for (KuudraPearlWaypoint holograms6 : map3.values()) {
                  WorldRenderUtils.drawBoxAtCoordinate(bridgeextension_94, holograms6.method1(), skyblockkuudra2.method36().method14(0.0F));
               }

               if (this.field10 != null) {
                  for (Vector3i vector3i9 : this.field10.method2()) {
                     WorldRenderUtils.drawBoxAtCoordinate(bridgeextension_94, vector3i9, skyblockkuudra2.method36().method14(0.0F));
                  }

                  if ((Boolean)skyblockkuudra2.method28().get()) {
                     for (Vector3i vector3i10 : this.field10.method3()) {
                        WorldRenderUtils.drawBoxAtCoordinate(bridgeextension_94, vector3i10, skyblockkuudra2.method37().method14(0.0F));
                     }
                  }
               }
            }
         }
      }
   }

   private void method8(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent highlightimpl21) {
      SkyblockKuudra skyblockkuudra2 = (SkyblockKuudra)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      if ((Boolean)skyblockkuudra2.method29().get()) {
         if (IslandUtils.getIsland() == SkyblockIsland.KUUDRA) {
            List list3 = this.method16();
            if (list3 != null) {
               AbstractRenderContext bridgeextension_94 = highlightimpl21.method3();

               for (Vector3i vector3i6 : list3) {
                  WorldRenderUtils.drawBoxAtCoordinate(bridgeextension_94, vector3i6, skyblockkuudra2.method38().method14(0.0F));
               }
            }
         }
      }
   }

   private void method9(EventWorldChange data31) {
      this.field10 = null;
      this.field9.clear();
   }

   private void method10(AbstractRenderContext bridgeextension_91, BridgeExtension bridgeextension2, int number3) {
      WorldRenderUtils.drawBeaconBeam(bridgeextension_91, bridgeextension2.bridge$getPosX(), 256.0, bridgeextension2.bridge$getPosZ(), number3);
   }

   private boolean method11(SkyblockKuudraWaypoints.Type type1) {
      SkyblockKuudra skyblockkuudra2 = (SkyblockKuudra)((ChildModBinding)this.method7(ModTraits.field16)).method1();

      return switch (type1) {
         case SUPPLY_PICKUP -> skyblockkuudra2.method23().get();
         case SUPPLY_PLACE -> skyblockkuudra2.method24().get();
         case BUILD -> skyblockkuudra2.method25().get();
         case FUEL_CELL -> skyblockkuudra2.method26().get();
      };
   }

   private SkyblockKuudraWaypoints.Type method12(BridgeExtension bridgeextension1) {
      if (bridgeextension1 instanceof EntityArmorStandBridge) {
         Component component3 = bridgeextension1.bridge$getCustomName();
         if (component3 == null) {
            return null;
         }

         String text4 = TextBridge.getTextContent(component3);
         if (text4.startsWith("PROGRESS: ")) {
            return SkyblockKuudraWaypoints.Type.BUILD;
         }

         if (text4.equals("BRING SUPPLY CHEST HERE")) {
            return SkyblockKuudraWaypoints.Type.SUPPLY_PLACE;
         }
      } else if (bridgeextension1 instanceof Bridge5Extension5_2 bridge5extension5_22) {
         ItemStackBridge bridgeextension_46 = bridge5extension5_22.bridge$getEquipmentInSlot(EntityEquipmentSlotBridge.MAINHAND);
         if (bridgeextension_46 == null || bridgeextension_46.bridge$isEmpty()) {
            return null;
         }

         String text7 = (String)SkyblockItemUtil.method11(bridgeextension_46).orElse(null);
         if (text7 == null) {
            return null;
         }

         SkyblockKuudra skyblockkuudra5 = (SkyblockKuudra)((ChildModBinding)this.method7(ModTraits.field16)).method1();
         if (text7.equals(skyblockkuudra5.method13())) {
            return SkyblockKuudraWaypoints.Type.SUPPLY_PICKUP;
         }

         if (text7.equals(
            "ewogICJ0aW1lc3RhbXAiIDogMTcyMDAyOTIzMDk5OSwKICAicHJvZmlsZUlkIiA6ICJkM2Y5MjEyMjY3YzM0YzEwYWNjOWZkNGI5MDFkYjI0ZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJkYXl3ZSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9mZDcyZGViMWFiMDAzM2I0MmIwYTEyZWZjZjQ4M2YwZmJhMjZkYzUxZGVkMzkxOWViYWRiNzBmOTY1N2ExZjYxIgogICAgfQogIH0KfQ=="
         )) {
            return SkyblockKuudraWaypoints.Type.FUEL_CELL;
         }
      }

      return null;
   }

   private int method13(Color color1, Color color2, double value3) {
      int number5 = color1.getRed();
      int number6 = color2.getRed();
      int number7 = this.method14(number5, number6, value3);
      int number8 = color1.getGreen();
      int number9 = color2.getGreen();
      int number10 = this.method14(number8, number9, value3);
      int number11 = color1.getBlue();
      int number12 = color2.getBlue();
      int number13 = this.method14(number11, number12, value3);
      return new Color(number7, number10, number13).getRGB();
   }

   private int method14(int number1, int number2, double value3) {
      return value3 >= 1.0 ? number2 : (value3 <= 0.0 ? number1 : (int)(number1 + (number2 - number1) * value3));
   }

   private HashMap<String, KuudraPearlWaypoint> method15() {
      KuudraWaypoints holograms21 = Ref.method4().method40().method82().method15().method19();
      return holograms21 == null ? null : holograms21.method1();
   }

   private List<Vector3i> method16() {
      KuudraWaypoints holograms21 = Ref.method4().method40().method82().method15().method19();
      return holograms21 == null ? null : holograms21.method2();
   }

   private enum Type {
      SUPPLY_PICKUP,
      SUPPLY_PLACE,
      BUILD,
      FUEL_CELL;

      Type() {
      }
   }
}
