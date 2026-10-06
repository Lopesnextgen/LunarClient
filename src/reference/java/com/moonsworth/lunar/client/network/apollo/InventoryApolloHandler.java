package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.Message;
import com.lunarclient.apollo.inventory.v1.DisplayInventoryButtonsMessage;
import com.lunarclient.apollo.inventory.v1.InventoryButton;
import com.lunarclient.apollo.inventory.v1.InventoryType;
import com.lunarclient.apollo.inventory.v1.RemoveInventoryButtonMessage;
import com.lunarclient.apollo.inventory.v1.ResetInventoryButtonsMessage;
import com.lunarclient.apollo.inventory.v1.UpdateInventoryButtonMessage;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_27;
import com.moonsworth.lunar.bridge.Bridge3_18;
import com.moonsworth.lunar.bridge.Bridge4_15;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.Bridge_57;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.event.input.MarkerInputEvent;
import com.moonsworth.lunar.client.event.render.ContainerSlotRenderEvent;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.event.input.MouseInputTypeLegacy;
import com.moonsworth.lunar.client.mod.misc.debug.ApolloDebugMod;
import com.moonsworth.lunar.client.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.rewindhandlers.Rewindhandlers2;
import com.moonsworth.lunar.client.rewindhandlers.Rewindhandlers3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump68;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.util.Slayer;

public class InventoryApolloHandler extends ApolloModuleHandler {
   private static final int field4 = 176;
   private static final int field5 = 195;
   public static final int field6 = 92;
   public static final int field7 = 166;
   private static final int field8 = 4;
   private static final int field9 = 25;
   private static final int field10 = 100;
   private static final InventoryApolloHandler.Type[] field11 = InventoryApolloHandler.Type.values();
   private final Map<String, ApolloButtonRenderer> field12 = new LinkedHashMap<>();
   private final Map<String, ApolloButtonRenderer> field13 = new LinkedHashMap<>();
   private boolean field14;

   public InventoryApolloHandler() {
      super("inventory", "Inventory");
      this.handle(ContainerSlotRenderEvent.ContainerSlotAfterItemsEvent.class, this::method4);
      this.handle(MarkerInputEvent.class, this::method5);
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(
         DisplayInventoryButtonsMessage.class, RemoveInventoryButtonMessage.class, ResetInventoryButtonsMessage.class, UpdateInventoryButtonMessage.class
      );
   }

   @Override
   protected void onDisable() {
      this.field12.clear();
      this.field13.clear();
      this.field14 = false;
   }

   @Override
   public void method3(HighlightImpl_3 var1) {
      if (this.isEnabled()) {
         var1.unpack(DisplayInventoryButtonsMessage.class).ifPresent(this::method3);
         var1.unpack(RemoveInventoryButtonMessage.class).ifPresent(var1x -> {
            this.field12.remove(var1x.getId());
            this.field13.remove(var1x.getId());
         });
         var1.unpack(ResetInventoryButtonsMessage.class).ifPresent(var1x -> {
            this.field12.clear();
            this.field13.clear();
         });
         var1.unpack(UpdateInventoryButtonMessage.class).ifPresent(var1x -> {
            ApolloButtonRenderer var2 = this.field12.get(var1x.getId());
            if (var2 == null) {
               var2 = this.field13.get(var1x.getId());
            }

            if (var2 != null) {
               var2.method2(var1x.getUpdate());
            }
         });
      }
   }

   private void method3(DisplayInventoryButtonsMessage var1) {
      int var2 = 0;
      int var3 = 0;

      for (InventoryButton var5 : var1.getInventoryButtonsList()) {
         InventoryType var6 = var5.getInventoryType();
         if (var6 != InventoryType.INVENTORY_TYPE_UNSPECIFIED && var6 != InventoryType.INVENTORY_TYPE_PLAYER) {
            var2++;
         } else if (!var5.hasButton()) {
            var2++;
         } else {
            Map var7 = switch (var5.getBox()) {
               case INVENTORY_BUTTON_BOX_LEFT -> this.field12;
               case INVENTORY_BUTTON_BOX_RIGHT -> this.field13;
               default -> null;
            };
            if (var7 == null) {
               var2++;
            } else {
               String var8 = var5.getButton().getId();
               int var9 = var7.size() - (var7.containsKey(var8) ? 1 : 0);
               if (var9 < 25 && var3 < 100) {
                  var3++;
                  ApolloButtonRenderer var10 = ApolloButtonRenderer.method1(var5.getButton(), 92.0F, 166.0F);
                  if (var10 == null) {
                     var2++;
                  } else {
                     this.field12.remove(var10.getId());
                     this.field13.remove(var10.getId());
                     var7.put(var10.getId(), var10);
                  }
               } else {
                  var2++;
               }
            }
         }
      }

      if (var2 > 0) {
         Slayer.method4("Apollo", "Dropped " + var2 + " invalid, unsupported or over-cap inventory buttons");
      }
   }

   private void method4(ContainerSlotRenderEvent.ContainerSlotAfterItemsEvent var1) {
      Bridge5Extension_3 var2 = this.method6(var1.method3());
      if (var2 != null) {
         double var3 = var1.method1().HHHCHORHIHRCOHIOICICICHCRRICCI();
         double var5 = var1.method1().IHRCCHHROHIRCOOOHRRIHOORRHIOHO();
         ApolloDebugMod var7 = ThreadModuleDump63.method4().method40().method80();
         boolean var8 = var7 != null && var7.isEnabled();
         boolean var9 = var8 && var7.method14();
         if (!this.field12.isEmpty() || !this.field13.isEmpty()) {
            ApolloButtonRenderer var10 = null;

            for (InventoryApolloHandler.Type var14 : field11) {
               if (!this.method7(var2, var14)) {
                  float var15 = this.method8(var2, var14);
                  float var16 = var2.bridge$getGuiTop();
                  if (var9) {
                     this.method10(var1.method5(), var15, var16);
                  }

                  for (ApolloButtonRenderer var18 : this.method11(var14).values()) {
                     boolean var19 = var18.method12(var15, var16, var3, var5);
                     if (var19) {
                        var10 = var18;
                     }

                     var18.method4(var1.method5(), var15, var16, var19);
                  }
               }
            }

            for (InventoryApolloHandler.Type var23 : field11) {
               if (!this.method7(var2, var23)) {
                  float var24 = this.method8(var2, var23);
                  float var25 = var2.bridge$getGuiTop();

                  for (ApolloButtonRenderer var27 : this.method11(var23).values()) {
                     var27.method5(var1.method5(), var24, var25);
                  }
               }
            }

            if (var10 != null) {
               var10.method11(var1.method5(), (int)var3, (int)var5);
            }

            var1.method4().method5(BridgeExtension2_11::method48);
         }
      }
   }

   private void method5(MarkerInputEvent var1) {
      if (var1.method4() == MouseInputTypeLegacy.CLICK || var1.method4() == MouseInputTypeLegacy.RELEASE) {
         if (var1.method4() == MouseInputTypeLegacy.RELEASE) {
            if (this.field14) {
               this.field14 = false;
               var1.setCancelled(true);
            }
         } else {
            this.field14 = false;
            Bridge5Extension_3 var2 = this.method6(var1.method1());
            if (var2 != null && (!this.field12.isEmpty() || !this.field13.isEmpty())) {
               double var3 = var1.method2().HHHCHORHIHRCOHIOICICICHCRRICCI();
               double var5 = var1.method2().IHRCCHHROHIRCOOOHRRIHOORRHIOHO();

               for (InventoryApolloHandler.Type var10 : field11) {
                  if (!this.method7(var2, var10)) {
                     float var11 = this.method8(var2, var10);
                     float var12 = var2.bridge$getGuiTop();
                     ArrayList var13 = new ArrayList<>(this.method11(var10).values());

                     for (int var14 = var13.size() - 1; var14 >= 0; var14--) {
                        ApolloButtonRenderer var15 = (ApolloButtonRenderer)var13.get(var14);
                        if (var15.method12(var11, var12, var3, var5)) {
                           var1.setCancelled(true);
                           this.field14 = true;
                           if (var1.method3() != 0) {
                              return;
                           }

                           if (var15.getRunCommand() != null) {
                              LcuiScreen.method15();
                              Rewindhandlers2.method3(this.getId(), var15.getRunCommand());
                           } else if (var15.getOpenUrl() != null) {
                              LcuiScreen.method15();
                              Rewindhandlers2.method4(this.getId(), var15.getOpenUrl());
                           } else if (var15.getClientAction() != null) {
                              LcuiScreen.method15();
                              Rewindhandlers2.method5(this.getId(), var15.getClientAction());
                           }

                           return;
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Nullable
   private Bridge5Extension_3 method6(Bridge5Extension6 var1) {
      if (!ThreadModuleDump63.method4().method41().method6().method61().get()) {
         return null;
      }

      boolean var2 = var1 instanceof Bridge4_15 || var1 instanceof Bridge2_27;
      return var2 && var1 instanceof Bridge5Extension_3 var3 ? var3 : null;
   }

   private boolean method7(Bridge5Extension_3 var1, InventoryApolloHandler.Type var2) {
      return var1 instanceof Bridge4_15 var3 && var3.bridge$isRecipeBookVisible()
         ? var2 == InventoryApolloHandler.Type.LEFT || var3.bridge$isWidthTooNarrow()
         : false;
   }

   private float method8(Bridge5Extension_3 var1, InventoryApolloHandler.Type var2) {
      int var3 = var1.bridge$getGuiLeft();
      boolean var4 = var1 instanceof Bridge2_27;
      return var2 == InventoryApolloHandler.Type.LEFT ? var3 - 4 - 92 : var3 + (var4 ? 195 : 176) + 4;
   }

   public int method9(Bridge5Extension_3 var1) {
      InventoryApolloHandler.Type var2 = ThreadModuleDump63.MC_VERSION >= 10 ? InventoryApolloHandler.Type.RIGHT : InventoryApolloHandler.Type.LEFT;
      return this.isEnabled()
            && !this.method11(var2).isEmpty()
            && ThreadModuleDump63.method4().method41().method6().method61().get()
            && !this.method7(var1, var2)
         ? 100
         : 0;
   }

   private void method10(MixinHelper_4 var1, float var2, float var3) {
      int var4 = Math.round(var2);
      int var5 = Math.round(var3);
      int var6 = var4 + 92;
      int var7 = var5 + 166;
      int var8 = -43691;
      var1.method1(var4, var5, var6, var5 + 1, var8);
      var1.method1(var4, var7 - 1, var6, var7, var8);
      var1.method1(var4, var5 + 1, var4 + 1, var7 - 1, var8);
      var1.method1(var6 - 1, var5 + 1, var6, var7 - 1, var8);
   }

   private Map<String, ApolloButtonRenderer> method11(InventoryApolloHandler.Type var1) {
      return var1 == InventoryApolloHandler.Type.LEFT ? this.field12 : this.field13;
   }

   public boolean method12(Bridge3_18 var1) {
      Bridge_57 var2 = this.method15(var1);
      if (var2 == null) {
         return false;
      } else if (Rewindhandlers.method3(var2, "hide_slot_highlight", "hideSlotHighlight")) {
         ApolloModuleManager var3 = ThreadModuleDump63.method4().method84();
         var3.method15("inventory", "HideSlotHighlight");
         return true;
      } else {
         return false;
      }
   }

   public boolean method13(Bridge3_18 var1) {
      Bridge_57 var2 = this.method15(var1);
      if (var2 == null) {
         return false;
      } else if (Rewindhandlers.method3(var2, "hide_item_tooltip", "hideItemTooltip")) {
         ApolloModuleManager var3 = ThreadModuleDump63.method4().method84();
         var3.method15("inventory", "HideItemTooltip");
         return true;
      } else {
         return false;
      }
   }

   public boolean method14(Bridge3_18 var1) {
      Bridge_57 var2 = this.method15(var1);
      if (var2 == null) {
         return false;
      }

      ApolloModuleManager var3 = ThreadModuleDump63.method4().method84();
      AtomicBoolean var4 = new AtomicBoolean(false);
      if (var2.bridge$getBoolean("unclickable")) {
         var4.set(true);
         var3.method15("inventory", "Unclickable");
      }

      String var5 = Rewindhandlers3.method7(Rewindhandlers.method2(var2, "copy_to_clipboard", "copyToClipboard"));
      if (!var5.isEmpty()) {
         ThreadModuleDump68.setClipboardString(var5);
         ThreadModuleDump63.method4().method69().method2("Copied to clipboard!", var5);
         var3.method15("inventory", "CopyToClipboard");
      }

      Bridge5_12 var6 = ThreadModuleDump63.method3();
      String var7 = Rewindhandlers.method2(var2, "open_url", "openUrl");
      if (Rewindhandlers2.method1(this.getId(), var7)) {
         var4.set(true);
      }

      String var8 = Rewindhandlers.method2(var2, "suggest_command", "suggestCommand");
      if (!var8.isEmpty()) {
         ThreadModuleDump63.method7().bridge$closeScreen();
         var6.bridge$displayScreen(Bridge.method8().method88(var8));
         var4.set(true);
         var3.method15("inventory", "SuggestCommand");
      }

      String var9 = Rewindhandlers.method2(var2, "run_command", "runCommand");
      if (Rewindhandlers2.method2(this.getId(), var9)) {
         var4.set(true);
      }

      return var4.get();
   }

   private Bridge_57 method15(Bridge3_18 var1) {
      return var1 == null ? null : Rewindhandlers.method1(var1.bridge$getItemStack());
   }

   private enum Type {
      LEFT,
      RIGHT;
   }
}
