package com.moonsworth.lunar.client.account;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge3Extension_7;
import com.moonsworth.lunar.bridge.Bridge3_4;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.Bridge8Extension34;
import com.moonsworth.lunar.bridge.Bridge8Handler2;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BridgeType2_5;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.Bridge_58;
import com.moonsworth.lunar.bridge.RenderTypeResolver;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.Horsestats;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.account.OwnedBadge;
import com.moonsworth.lunar.client.Gui2Handler2;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.cosmetics.Outfit;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.KeyCombo;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.render.texture.Util2Handler;
import com.moonsworth.lunar.client.util.alert.Alert5;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.cosmetics.OutfitManager;

public class BadgeManager extends com.moonsworth.lunar.client.framework.ItemMapHandler<Integer, Gui2Handler2> implements Extension, EventRegistrar {
   private static final ResourceLocationBridge field2 = ResourceLocationBridge.create("lunar", "badges.json");
   public static final AtomicBoolean field3 = new AtomicBoolean(false);
   private static final ModifierKeybindOption field4 = (ModifierKeybindOption)((Data)((Data)((Data)OptionFactory.method18("reloadBadges")
               .HIIIOHRRROCICIOIORRRIRCRCHHIII(KeyCombo.method2(KeyCode.KEY_EQUALS)))
            .OCIRRCIOIORIIRCOORRIROOROOHCOI(false))
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private static final ModifierKeybindOption field5 = (ModifierKeybindOption)((Data)((Data)((Data)OptionFactory.method18("reloadTextures")
               .HIIIOHRRROCICIOIORRRIRCRCHHIII(KeyCombo.method2(KeyCode.KEY_SEMICOLON)))
            .OCIRRCIOIORIIRCOORRIROOROOHCOI(false))
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final List<OwnedBadge> field6 = new ArrayList<>();
   private final GuiIterator field7 = new GuiIterator();

   public BadgeManager() {
      field4.method3(() -> {
         if (ThreadModuleDump63.method4().method31().method29()) {
            this.method2().clear();
            this.method4();
            Client.method109().method69().method3("Badges reloaded.");
         }
      });
      field5.method3(() -> {
         if (ThreadModuleDump63.method4().method31().method29()) {
            Bridge8Handler2 var1 = ThreadModuleDump63.method3().bridge$getTextureManager();
            this.method2().forEach((var1x, var2) -> var1.bridge$deleteTexture(var2.method3()));
            field3.set(false);
            Client.method109().method69().method3("Badge textures reloaded.");
         }
      });
      this.handle(EventClientTick.class, var1 -> {
         if (ThreadModuleDump63.method3().bridge$areResourcesLoaded()) {
            if (!field3.get()) {
               for (Gui2Handler2 var3 : this.method2().values()) {
                  if (var3.method6()) {
                     try {
                        ThreadModuleDump63.method3().bridge$getTextureManager().method3(var3.method3(), new Util2Handler(var3.method3(), BridgeType2_5.FULL));
                     } catch (Exception var5) {
                        Inventorymod2.method5(var5, "BadgeManager");
                     }
                  }
               }

               field3.set(true);
            }
         }
      });
   }

   @Override
   protected Map<Integer, Gui2Handler2> method3() {
      return new HashMap<>();
   }

   public void register() {
      Client.field2.submit(this::method4);
   }

   private void method4() {
      try {
         for (JsonElement var3 : ThreadModuleDump63.method40(field2).getAsJsonArray()) {
            if (var3.isJsonObject()) {
               JsonObject var4 = var3.getAsJsonObject();

               try {
                  int var5 = var4.get("id").getAsInt();
                  String var6 = var4.get("name").getAsString();
                  String var7 = var4.get("description").getAsString();
                  String var8 = var4.get("resource").getAsString();
                  String[] var9 = var8.split(":", 2);
                  String var10 = var9.length > 1 ? var9[0] : "lunar";
                  String var11 = var9.length > 1 ? var9[1] : var9[0];
                  ResourceLocationBridge var12 = ResourceLocationBridge.create(var10, var11);
                  String var13 = var4.get("releasedAt").getAsString();
                  Instant var14 = Instant.parse(var13);
                  boolean var15 = var4.get("animated").getAsBoolean();
                  ResourceLocationBridge var16 = var15 ? ResourceLocationBridge.create(var10, var11.replace(".webp", ".gif")) : var12;
                  Gui2Handler2 var17 = new Gui2Handler2(var5, var6, var7, var12, var16, var14, var15);
                  this.method2().put(var5, var17);
               } catch (Exception var18) {
                  Slayer.getAnimation("Failed to parse badge: " + var4, new Object[0]);
                  var18.printStackTrace();
               }
            }
         }
      } catch (Exception var19) {
         Inventorymod2.method5(var19, "BadgeManager");
      }

      Slayer.method4("Badges", "Loaded %d badges", new Object[]{this.method2().size()});
   }

   public void method3(List<OwnedBadge> var1) {
      this.field6.clear();
      this.field6.addAll(var1);
      this.field7.method3("ownedBadges", this.field6.stream().map(OwnedBadge::provide).collect(JsonArray::new, JsonArray::add, JsonArray::addAll));
   }

   public void method4(int var1) {
      this.method5((Gui2Handler2)this.method2().get(var1));
   }

   public void method5(Gui2Handler2 var1) {
      OutfitManager var2 = ThreadModuleDump63.method4().method55();
      if (var2.method17() != null) {
         Outfit var3 = var2.method17().method5();
         if (var3 != null && var1 != var3.method9()) {
            var3.method13(var1);
            var3.method3();
            Horsestats var4 = ThreadModuleDump63.method3().bridge$getSession();
            if (var4 != null) {
               ThreadModuleDump63.method4()
                  .method53()
                  .method63()
                  .computeIfPresent(var4.bridge$getProfile().getId(), (var1x, var2x) -> var2x.method4(var3.method9()));
            }
         }
      }
   }

   public GuiIterator getProvider() {
      return this.field7;
   }

   public Gui2Handler2 getAnimation(UUID var1) {
      OutfitManager var2 = ThreadModuleDump63.method4().method55();
      Outfit var3 = var2.method4(var1);
      return var3 != null ? var3.method9() : null;
   }

   public static void method8(MixinHelper_4 var0, Gui2Handler2 var1, float var2, float var3, float var4, float var5, int var6) {
      method10(var0, var1, var2, var3, var4, var5, var6, null);
   }

   public static void method9(
      AbstractRenderContext var0, Gui2Handler2 var1, float var2, float var3, float var4, float var5, int var6, @Nullable RenderTypeResolver var7
   ) {
      if (var1 != null) {
         Bridge8Extension3 var8 = ThreadModuleDump63.method3().bridge$getTextureManager().bridge$getTexture(var1.method3());
         if (var8 instanceof Bridge8Extension34) {
            Bridge3_4 var9 = ((Bridge8Extension34)var8).method3();
            if (var9 instanceof Alert5) {
               if (var9 instanceof Bridge3Extension_7 var10) {
                  var10.method3(true);
                  if (!var10.method4()) {
                     return;
                  }
               }

               Bridge_58 var12 = ((Alert5)var9).getAnimation();
               if (var12 != null) {
                  var12.updateAnimation();
                  if (var9 instanceof Util2Handler var11) {
                     var11.method12();
                  }
               }
            }
         }

         if (var7 != null) {
            LcuiScreen.method41(var7.get(var1.method3()), var0, var2, var3, (float)LcuiScreen.z, 0.0F, 0.0F, var4, var5, var4, var5, var6);
         } else {
            LcuiScreen.method29(var0, var1.method3(), var2, var3, var4, var5, var6);
         }
      }
   }

   public static void method10(MixinHelper_4 var0, Gui2Handler2 var1, float var2, float var3, float var4, float var5, int var6, @Nullable RenderTypeResolver var7) {
      if (var1 != null) {
         Bridge8Extension3 var8 = ThreadModuleDump63.method3().bridge$getTextureManager().bridge$getTexture(var1.method3());
         if (var8 instanceof Bridge8Extension34) {
            Bridge3_4 var9 = ((Bridge8Extension34)var8).method3();
            if (var9 instanceof Alert5) {
               if (var9 instanceof Bridge3Extension_7 var10) {
                  var10.method3(true);
                  if (!var10.method4()) {
                     return;
                  }
               }

               Bridge_58 var12 = ((Alert5)var9).getAnimation();
               if (var12 != null) {
                  var12.updateAnimation();
                  if (var9 instanceof Util2Handler var11) {
                     var11.method12();
                  }
               }
            }
         }

         if (var7 != null) {
            LcuiScreen.method42(
               var7.get(var1.method3()), var1.method3(), var0, var2, var3, (float)LcuiScreen.z, 0.0F, 0.0F, var4, var5, var4, var5, var6
            );
         } else {
            LcuiScreen.method31(var0, var1.method3(), var2, var3, var4, var5, var6);
         }
      }
   }
}
