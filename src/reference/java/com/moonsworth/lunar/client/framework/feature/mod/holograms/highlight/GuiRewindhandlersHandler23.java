package com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight;

import com.lunarclient.SafeMap;
import com.lunarclient.generated.skyblockprofileresponse.profile.Member;
import com.lunarclient.generated.skyblockprofileresponse.profile.member.Inventory;
import com.lunarclient.generated.skyblockprofileresponse.profile.member.inventory.BackpackContent;
import com.lunarclient.generated.skyblockprofileresponse.profile.member.inventory.BackpackIcon;
import com.moonsworth.lunar.bridge.Bridge3_18;
import com.moonsworth.lunar.bridge.Bridge3_6;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.Bridge_57;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.MixinHelper;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui3;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler212;
import com.moonsworth.lunar.client.framework.feature.mod.impl.click.storageoverlay.Storageoverlay;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler26;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.util.ThreadModuleDump40;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class GuiRewindhandlersHandler23 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final GuiRewindhandlersHandler26 field7 = (GuiRewindhandlersHandler26)this.method3(GuiRewindhandlersHandler26.class);
   private final com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler22 field8 = (com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler22)this.method3(
      com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler22.class
   );
   private final GuiRewindhandlersHandler212 field9 = (GuiRewindhandlersHandler212)this.method3(GuiRewindhandlersHandler212.class);
   private boolean field10;

   public GuiRewindhandlersHandler23() {
      if (ThreadModuleDump63.MC_VERSION >= 33) {
         this.handle(EventClientTick.class, this::method1);
         this.handle(Rewindhandlers.Data14.class, var1 -> this.method10());
      }
   }

   protected void onEnable() {
      if (ThreadModuleDump63.MC_VERSION >= 33) {
         this.method10();
      }
   }

   @Annotation2(min = 33)
   private void method1(EventClientTick var1) {
      Bridge5Extension6 var2 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
      if (var2 instanceof Bridge5Extension_3 var3) {
         if (this.method4(var2) || this.method5(var2)) {
            for (Storageoverlay var10 : this.method6()) {
               if (var10 != null) {
                  var10.method4();
                  this.field10 = true;
               }
            }
         }

         if (this.method3(var2)) {
            for (int var8 = 0; var8 < 9; var8++) {
               ItemStackBridge var11 = ((Bridge3_18)var3.bridge$inventorySlots().get(9 + var8)).bridge$getItemStack();
               if (!var11.bridge$isEmpty()) {
                  String var6 = AdventureChatFormatting.getTextWithoutFormattingCodes(var11.bridge$getDisplayName());
                  if (var6.equals("Locked Page")) {
                     this.method6().set(var8, null);
                  } else if (this.method6().get(var8) == null) {
                     this.method6().set(var8, new Storageoverlay(var8 + 1, true));
                     MixinHelper.field1.method49("StorageOverlay", "menu-true-" + (var8 + 1));
                  } else if (this.method6().get(var8).getMenuIndex() != var8 + 1) {
                     this.method6().set(var8, null);
                  }
               }
            }

            for (int var9 = 0; var9 < 18; var9++) {
               ItemStackBridge var12 = ((Bridge3_18)var3.bridge$inventorySlots().get(27 + var9)).bridge$getItemStack();
               if (!var12.bridge$isEmpty()) {
                  String var13 = AdventureChatFormatting.getTextWithoutFormattingCodes(var12.bridge$getDisplayName());
                  if (!var13.startsWith("Locked ") && !var13.startsWith("Empty ")) {
                     if (this.method6().get(var9 + 9) == null) {
                        this.method6().set(var9 + 9, new Storageoverlay(var9 + 1, false));
                        MixinHelper.field1.method49("StorageOverlay", "menu-false-" + (var9 + 1));
                     } else if (this.method6().get(var9 + 9).getMenuIndex() != var9 + 1) {
                        this.method6().set(var9 + 9, null);
                     }
                  } else {
                     this.method6().set(var9 + 9, null);
                  }
               }
            }
         }
      } else {
         if (this.field10) {
            for (Storageoverlay var5 : this.method6()) {
               if (var5 != null) {
                  var5.method2(this.field7);
               }
            }
         }
      }
   }

   private String method2(Bridge5Extension6 var1) {
      return var1 instanceof Bridge5Extension_3 var2 ? AdventureTextBridge.getTextContent(AdventureTextBridge.asAdventure(var2.bridge$title())) : null;
   }

   private boolean method3(Bridge5Extension6 var1) {
      return "Storage".equals(this.method2(var1));
   }

   private boolean method4(Bridge5Extension6 var1) {
      String var2 = this.method2(var1);
      return var2 != null && var2.contains(" Backpack ") && var2.contains("(Slot #");
   }

   private boolean method5(Bridge5Extension6 var1) {
      String var2 = this.method2(var1);
      return var2 != null && var2.startsWith("Ender Chest (");
   }

   @Annotation2(min = 33)
   public List<Storageoverlay> method6() {
      return this.method7(this.field9.method5());
   }

   @Annotation2(min = 33)
   public List<Storageoverlay> method7(String var1) {
      Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
      if (var2 == null) {
         return Collections.emptyList();
      }

      List var3 = this.method8(var1);
      if (var3 == null || var3.isEmpty()) {
         var3 = new ArrayList();

         for (int var4 = 0; var4 < 9; var4++) {
            var3.add(null);
         }

         for (int var5 = 0; var5 < 18; var5++) {
            var3.add(null);
         }

         this.method9(var3, var1);
      }

      return var3;
   }

   private List<Storageoverlay> method8(String var1) {
      return this.field7.method3(var1).storageContents.storageInventoriesModern;
   }

   private void method9(List<Storageoverlay> var1, String var2) {
      this.field7.method3(var2).storageContents.storageInventoriesModern = var1;
   }

   @Annotation2(min = 33)
   private void method10() {
      Inventory var1 = Optional.ofNullable(this.field8.method9()).<Inventory>map(Member::inventory).orElse(null);
      if (var1 != null) {
         String var2 = (String)var1.enderChestContents().data().orElse(null);
         if (var2 != null) {
            List var3 = Gui3.method28(var2);
            if (var3 != null) {
               int var4 = (int)Math.ceil(var3.size() / 45.0);

               for (int var5 = 0; var5 < var4; var5++) {
                  ArrayList var6 = new ArrayList();

                  for (int var7 = 0; var7 < Math.min(45, var3.size() - var5 * 45); var7++) {
                     var6.add((ItemStackBridge)var3.get(var7 + var5 * 45));
                  }

                  Storageoverlay var17 = this.method6().get(var5);
                  if (var17 == null) {
                     var17 = new Storageoverlay(var5 + 1, true);
                     this.method6().set(var5, var17);
                  }

                  var17.setName("Ender Chest (" + (var5 + 1) + "/" + var4 + ")");
                  var17.method8(var6);
               }
            }
         }

         SafeMap var13 = var1.backpackContents();
         SafeMap var14 = var1.backpackIcons();
         if (var13 != null && var14 != null) {
            for (String var16 : var13.keySet()) {
               Bridge_57 var18 = Gui3.method26((String)((BackpackIcon)var14.get(var16)).data().orElse(null));
               List var8 = Gui3.method28((String)((BackpackContent)var13.get(var16)).data().orElse(null));
               if (var8 != null) {
                  String var9 = "Backpack (Slot #" + var16 + "1)";
                  if (var18 != null && var18.bridge$contains("i", 9)) {
                     Bridge3_6 var10 = var18.bridge$getList("i", 10);
                     if (var10.bridge$size() == 1) {
                        Bridge_57 var11 = var10.bridge$getCompoundAt(0);
                        if (var11.bridge$contains("tag", 10) && var11.bridge$getCompoundTag("tag").bridge$contains("display", 10)) {
                           String var12 = AdventureChatFormatting.getTextWithoutFormattingCodes(
                              var11.bridge$getCompoundTag("tag").bridge$getCompoundTag("display").bridge$getString("Name")
                           );
                           var9 = var12 + " (Slot #" + var16 + "1)";
                        }
                     }
                  }

                  int var19 = ThreadModuleDump40.method4(var16, -1);
                  if (var19 != -1) {
                     Storageoverlay var20 = this.method6().get(var19 + 9);
                     if (var20 == null) {
                        var20 = new Storageoverlay(var19 + 1, false);
                        this.method6().set(var19 + 9, var20);
                     }

                     var20.setName(var9);
                     var20.method8(var8);
                  }
               }
            }
         }
      }
   }

   @com.moonsworth.lunar.ichor.util.Annotation2
   public static class Data {
      private List<Storageoverlay> storageInventoriesModern = null;
   }
}
