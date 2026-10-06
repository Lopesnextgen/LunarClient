package com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight;

import com.lunarclient.SafeMap;
import com.lunarclient.generated.skyblockprofileresponse.profile.Member;
import com.lunarclient.generated.skyblockprofileresponse.profile.member.Inventory;
import com.lunarclient.generated.skyblockprofileresponse.profile.member.inventory.BackpackContent;
import com.lunarclient.generated.skyblockprofileresponse.profile.member.inventory.BackpackIcon;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.NBTTagListBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.CompoundTagBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.GuiRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.ProfileIdListener;
import com.moonsworth.lunar.client.framework.feature.mod.impl.click.storageoverlay.Storageoverlay;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkyblockProfileEvents;
import com.moonsworth.lunar.client.framework.listener.PersistentValuesListener;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.util.math.NumberUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StorageOverlayListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final PersistentValuesListener field7 = (PersistentValuesListener)this.method3(PersistentValuesListener.class);
   private final com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockProfileCache field8 = (com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockProfileCache)this.method3(
      com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockProfileCache.class
   );
   private final ProfileIdListener field9 = (ProfileIdListener)this.method3(ProfileIdListener.class);
   private boolean field10;

   public StorageOverlayListener() {
      if (Ref.MC_VERSION >= 33) {
         this.handle(EventTick.class, this::method1);
         this.handle(SkyblockProfileEvents.SkyblockProfileLoadEvent.class, arg1 -> this.method10());
      }
   }

   protected void onEnable() {
      if (Ref.MC_VERSION >= 33) {
         this.method10();
      }
   }

   @VersionGate(min = 33)
   private void method1(EventTick highlightimpl21) {
      GuiScreenBridge bridge5extension62 = Ref.method3().bridge$getCurrentScreen();
      if (bridge5extension62 instanceof GuiContainerBridge bridge5extension_33) {
         if (this.method4(bridge5extension62) || this.method5(bridge5extension62)) {
            for (Storageoverlay storageoverlay10 : this.method6()) {
               if (storageoverlay10 != null) {
                  storageoverlay10.method4();
                  this.field10 = true;
               }
            }
         }

         if (this.method3(bridge5extension62)) {
            for (int index8 = 0; index8 < 9; index8++) {
               ItemStackBridge bridgeextension_411 = ((SlotBridge)bridge5extension_33.bridge$inventorySlots().get(9 + index8)).bridge$getItemStack();
               if (!bridgeextension_411.bridge$isEmpty()) {
                  String text6 = ChatFormatting.getTextWithoutFormattingCodes(bridgeextension_411.bridge$getDisplayName());
                  if (text6.equals("Locked Page")) {
                     this.method6().set(index8, null);
                  } else if (this.method6().get(index8) == null) {
                     this.method6().set(index8, new Storageoverlay(index8 + 1, true));
                     GuiRenderer.field1.method49("StorageOverlay", "menu-true-" + (index8 + 1));
                  } else if (this.method6().get(index8).getMenuIndex() != index8 + 1) {
                     this.method6().set(index8, null);
                  }
               }
            }

            for (int index9 = 0; index9 < 18; index9++) {
               ItemStackBridge bridgeextension_412 = ((SlotBridge)bridge5extension_33.bridge$inventorySlots().get(27 + index9)).bridge$getItemStack();
               if (!bridgeextension_412.bridge$isEmpty()) {
                  String text13 = ChatFormatting.getTextWithoutFormattingCodes(bridgeextension_412.bridge$getDisplayName());
                  if (!text13.startsWith("Locked ") && !text13.startsWith("Empty ")) {
                     if (this.method6().get(index9 + 9) == null) {
                        this.method6().set(index9 + 9, new Storageoverlay(index9 + 1, false));
                        GuiRenderer.field1.method49("StorageOverlay", "menu-false-" + (index9 + 1));
                     } else if (this.method6().get(index9 + 9).getMenuIndex() != index9 + 1) {
                        this.method6().set(index9 + 9, null);
                     }
                  } else {
                     this.method6().set(index9 + 9, null);
                  }
               }
            }
         }
      } else {
         if (this.field10) {
            for (Storageoverlay storageoverlay5 : this.method6()) {
               if (storageoverlay5 != null) {
                  storageoverlay5.method2(this.field7);
               }
            }
         }
      }
   }

   private String method2(GuiScreenBridge bridge5extension61) {
      return bridge5extension61 instanceof GuiContainerBridge bridge5extension_32 ? TextBridge.getTextContent(TextBridge.asAdventure(bridge5extension_32.bridge$title())) : null;
   }

   private boolean method3(GuiScreenBridge bridge5extension61) {
      return "Storage".equals(this.method2(bridge5extension61));
   }

   private boolean method4(GuiScreenBridge bridge5extension61) {
      String text2 = this.method2(bridge5extension61);
      return text2 != null && text2.contains(" Backpack ") && text2.contains("(Slot #");
   }

   private boolean method5(GuiScreenBridge bridge5extension61) {
      String text2 = this.method2(bridge5extension61);
      return text2 != null && text2.startsWith("Ender Chest (");
   }

   @VersionGate(min = 33)
   public List<Storageoverlay> method6() {
      return this.method7(this.field9.method5());
   }

   @VersionGate(min = 33)
   public List<Storageoverlay> method7(String text1) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 == null) {
         return Collections.emptyList();
      }

      List list3 = this.method8(text1);
      if (list3 == null || list3.isEmpty()) {
         list3 = new ArrayList();

         for (int index4 = 0; index4 < 9; index4++) {
            list3.add(null);
         }

         for (int index5 = 0; index5 < 18; index5++) {
            list3.add(null);
         }

         this.method9(list3, text1);
      }

      return list3;
   }

   private List<Storageoverlay> method8(String text1) {
      return this.field7.method3(text1).storageContents.storageInventoriesModern;
   }

   private void method9(List<Storageoverlay> list1, String text2) {
      this.field7.method3(text2).storageContents.storageInventoriesModern = list1;
   }

   @VersionGate(min = 33)
   private void method10() {
      Inventory inventory1 = Optional.ofNullable(this.field8.method9()).<Inventory>map(Member::inventory).orElse(null);
      if (inventory1 != null) {
         String text2 = (String)inventory1.enderChestContents().data().orElse(null);
         if (text2 != null) {
            List list3 = SkyblockItemUtil.method28(text2);
            if (list3 != null) {
               int number4 = (int)Math.ceil(list3.size() / 45.0);

               for (int index5 = 0; index5 < number4; index5++) {
                  ArrayList list6 = new ArrayList();

                  for (int index7 = 0; index7 < Math.min(45, list3.size() - index5 * 45); index7++) {
                     list6.add((ItemStackBridge)list3.get(index7 + index5 * 45));
                  }

                  Storageoverlay storageoverlay17 = this.method6().get(index5);
                  if (storageoverlay17 == null) {
                     storageoverlay17 = new Storageoverlay(index5 + 1, true);
                     this.method6().set(index5, storageoverlay17);
                  }

                  storageoverlay17.setName("Ender Chest (" + (index5 + 1) + "/" + number4 + ")");
                  storageoverlay17.method8(list6);
               }
            }
         }

         SafeMap safemap13 = inventory1.backpackContents();
         SafeMap safemap14 = inventory1.backpackIcons();
         if (safemap13 != null && safemap14 != null) {
            for (String text16 : safemap13.keySet()) {
               CompoundTagBridge bridge_5718 = SkyblockItemUtil.method26((String)((BackpackIcon)safemap14.get(text16)).data().orElse(null));
               List list8 = SkyblockItemUtil.method28((String)((BackpackContent)safemap13.get(text16)).data().orElse(null));
               if (list8 != null) {
                  String text9 = "Backpack (Slot #" + text16 + "1)";
                  if (bridge_5718 != null && bridge_5718.bridge$contains("i", 9)) {
                     NBTTagListBridge bridge3_610 = bridge_5718.bridge$getList("i", 10);
                     if (bridge3_610.bridge$size() == 1) {
                        CompoundTagBridge bridge_5711 = bridge3_610.bridge$getCompoundAt(0);
                        if (bridge_5711.bridge$contains("tag", 10) && bridge_5711.bridge$getCompoundTag("tag").bridge$contains("display", 10)) {
                           String text12 = ChatFormatting.getTextWithoutFormattingCodes(
                              bridge_5711.bridge$getCompoundTag("tag").bridge$getCompoundTag("display").bridge$getString("Name")
                           );
                           text9 = text12 + " (Slot #" + text16 + "1)";
                        }
                     }
                  }

                  int index19 = NumberUtils.method4(text16, -1);
                  if (index19 != -1) {
                     Storageoverlay storageoverlay20 = this.method6().get(index19 + 9);
                     if (storageoverlay20 == null) {
                        storageoverlay20 = new Storageoverlay(index19 + 1, false);
                        this.method6().set(index19 + 9, storageoverlay20);
                     }

                     storageoverlay20.setName(text9);
                     storageoverlay20.method8(list8);
                  }
               }
            }
         }
      }
   }

   @com.moonsworth.lunar.ichor.util.KeepName
   public static class Data {
      private List<Storageoverlay> storageInventoriesModern = null;

      public Data() {
      }
   }
}
