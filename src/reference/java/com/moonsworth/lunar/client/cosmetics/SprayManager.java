package com.moonsworth.lunar.client.cosmetics;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.protobuf.Any;
import com.lunarclient.websocket.spray.v1.EquippedSpray;
import com.lunarclient.websocket.spray.v1.RemoveSprayPush;
import com.lunarclient.websocket.spray.v1.RemoveSprayRequest;
import com.lunarclient.websocket.spray.v1.UpdateEquippedSpraysRequest;
import com.lunarclient.websocket.spray.v1.UseSprayPush;
import com.lunarclient.websocket.spray.v1.UseSprayRequest;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.Bridge3Extension_7;
import com.moonsworth.lunar.bridge.Bridge3_4;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.Bridge8Handler2;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BridgeType2_7;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.SoundAttenuationType;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.bridge.horsestats.SprayHitResult;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType$Type;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType$Type2;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.bridge.horsestats.Horsestats_2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType_4;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.framework.ItemSetHandler;
import com.moonsworth.lunar.client.cosmetics.SprayPlacement;
import com.moonsworth.lunar.client.cosmetics.SprayPlacementImpl;
import com.moonsworth.lunar.client.cosmetics.SprayPlacementTracker;
import com.moonsworth.lunar.client.cosmetics.SprayEntry;
import com.moonsworth.lunar.client.cosmetics.PlayerModelPartMap;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.screen.ScreenChangeEvent;
import com.moonsworth.lunar.client.event.player.EventPlayerRemoval;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseWheelLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import com.moonsworth.lunar.client.util.alert.Alert5;
import com.moonsworth.lunar.client.util.chest.SExtension;
import com.moonsworth.lunar.client.util.chest.SImpl;
import it.unimi.dsi.fastutil.booleans.BooleanObjectPair;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3i;

public class SprayManager extends ItemSetHandler<SprayEntry> implements GuiIterator.Extension, EventRegistrar {
   private static final ResourceLocationBridge field2 = ResourceLocationBridge.create("lunar", "sprays.json");
   private static final ResourceLocationBridge[] field3 = new ResourceLocationBridge[]{
      ResourceLocationBridge.create("lunar", "sound/spray/spraySound1.ogg"),
      ResourceLocationBridge.create("lunar", "sound/spray/spraySound2.ogg"),
      ResourceLocationBridge.create("lunar", "sound/spray/spraySound3.ogg"),
      ResourceLocationBridge.create("lunar", "sound/spray/spraySound4.ogg")
   };
   private static final int field4 = 900;
   private static final int field5 = 600;
   public static final int field6 = 8;
   private final GuiIterator field7 = new GuiIterator();
   private final Int2ObjectMap<SprayEntry> field8 = new Int2ObjectOpenHashMap();
   private final Object2LongMap<SprayEntry> field9 = new Object2LongOpenHashMap();
   private final Set<EquippedSpray> field10 = new LinkedHashSet<>();
   private final Map<UUID, LinkedList<SprayPlacementTracker>> field11 = new HashMap<>();
   @Nullable
   private List<Integer> field12 = null;
   private final List<SprayEntry> field13 = new ArrayList<>();
   private int field14 = 1;
   private long field15;
   @Nullable
   private SprayPlacementImpl field16;

   public SprayManager() {
      this.handle(EventClientTick.class, this::method9);
      this.handle(EventEverySecond.class, this::method10);
      this.handle(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent.class, this::method11);
      this.handle(EventMouseWheelLegacy.class, var1 -> {
         if (this.field16 != null) {
            this.field16.method8(this.field16.method6() + (var1.method1() < 0.0 ? -2.0F : 2.0F));
            var1.setCancelled(true);
         }
      });
      this.handle(EventPlayerRemoval.class, var1 -> this.field11.remove(var1.method1().bridge$getUniqueID()));
      this.handle(ScreenChangeEvent.class, var1 -> this.field16 = null);
      this.method8();
   }

   public void method2() {
      ThreadModuleDump63.method5()
         .ifPresent(
            var1 -> var1.method91().updateEquippedSprays(null, UpdateEquippedSpraysRequest.newBuilder().addAllEquippedSprays(this.field10).build(), var0 -> {})
         );
   }

   public void method2(UUID var1, @NotNull SprayPlacementTracker var2, int var3, boolean var4) {
      LinkedList var5 = this.field11.computeIfAbsent(var1, var0 -> new LinkedList<>());

      while (var5.size() >= var3) {
         var5.removeFirst();
      }

      var5.add(var2);
      if (var4) {
         ThreadModuleDump63.method5()
            .ifPresent(
               var3x -> {
                  UseSprayRequest var4x = UseSprayRequest.newBuilder()
                     .setSprayId(var2.method1().getId())
                     .setPos(ThreadModuleDump66.method12(var2.method2()))
                     .setFacing(ThreadModuleDump66.method10(var2.method3()))
                     .setRotation(var2.getRotation())
                     .build();
                  var3x.method91().useSpray(null, var4x, var0x -> {});
                  Rewind var5x = ThreadModuleDump63.method4().method40().method85();
                  if (var5x.isRecording()) {
                     UseSprayPush var6 = UseSprayPush.newBuilder()
                        .setPlayerUuid(ThreadModuleDump66.method3(var1))
                        .setSprayId(var2.method1().getId())
                        .setPos(ThreadModuleDump66.method12(var2.method2()))
                        .setFacing(ThreadModuleDump66.method10(var2.method3()))
                        .setRotation(var2.getRotation())
                        .setMaxActiveSprays(var3)
                        .build();
                     var5x.method34().method1(Any.pack(var6));
                  }
               }
            );
      }
   }

   public void method3(UUID var1, Vector3f var2) {
      List var3 = this.field11.get(var1);
      if (var3 != null) {
         var3.removeIf(var1x -> var1x.method2().equals(var2));
      }
   }

   public void method5() {
      long var1 = System.currentTimeMillis();
      if (var1 - this.field15 > 900L) {
         this.field15 = var1;
         Bridge5Extension_5 var3 = ThreadModuleDump63.method7();
         if (var3 != null) {
            List var4 = this.field11.get(var3.bridge$getUniqueID());
            if (var4 != null && !var4.isEmpty()) {
               float var5 = ThreadModuleDump63.method3().bridge$getTimer().method1();
               ((SprayHitResult)SExtension.builder(SImpl.SPRAY)
                     .method8(var3, var3.bridge$blockInteractionRange(), var5)
                     .method14((var0, var1x) -> true)
                     .method18()
                     .method8(var4))
                  .method5(
                     var2 -> {
                        SprayPlacement var3x = (SprayPlacement)var2.method7();
                        if (var3x instanceof SprayPlacementTracker var4x) {
                           var4.remove(var4x);
                           ThreadModuleDump63.method5()
                              .ifPresent(
                                 var1xx -> var1xx.method91()
                                    .removeSpray(
                                       null, RemoveSprayRequest.newBuilder().setPos(ThreadModuleDump66.method12(var3x.method2())).build(), var0xx -> {}
                                    )
                              );
                           Rewind var5x = ThreadModuleDump63.method4().method40().method85();
                           if (var5x.isRecording()) {
                              RemoveSprayPush var6 = RemoveSprayPush.newBuilder()
                                 .setPlayerUuid(ThreadModuleDump66.method3(var3.bridge$getUniqueID()))
                                 .setPos(ThreadModuleDump66.method12(var3x.method2()))
                                 .build();
                              var5x.method34().method1(Any.pack(var6));
                           }
                        }
                     }
                  );
            }
         }
      }
   }

   public SprayEntry method5(int var1) {
      return (SprayEntry)this.field8.get(var1);
   }

   public void method6(SprayEntry var1, Vector3f var2, HorsestatsType_2 var3, Bridge5Extension_5 var4, boolean var5) {
      float var6 = method24(false, var3, var4, 0.0F);
      Matrix4f var7 = method28(var2, var3, var6);
      int var8 = this.method17(var1, var7, var3);
      BooleanObjectPair var9 = method27(var1, var7, var3, false);
      this.field16 = new SprayPlacementImpl(
         var1,
         var2,
         var3,
         var6,
         0.0F,
         var5 && var8 <= 8 && var9 != null && var9.keyBoolean(),
         var9 == null ? new Horsestats20Extension2[0] : (Horsestats20Extension2[])var9.value(),
         var8
      );
   }

   @Nullable
   public SprayPlacementImpl method7() {
      SprayPlacementImpl var1 = this.field16;
      this.field16 = null;
      return var1;
   }

   public void method8() {
      if (!this.field8.isEmpty() && ThreadModuleDump63.method3() != null) {
         Bridge8Handler2 var1 = ThreadModuleDump63.method3().bridge$getTextureManager();
         ObjectIterator var2 = this.field8.values().iterator();

         while (var2.hasNext()) {
            SprayEntry var3 = (SprayEntry)var2.next();
            var1.bridge$deleteTexture(var3.method4());
         }

         this.field8.clear();
      }

      try {
         for (JsonElement var9 : ThreadModuleDump63.method40(field2).getAsJsonArray()) {
            JsonObject var4 = var9.getAsJsonObject();
            int var5 = var4.get("id").getAsInt();
            this.field8
               .put(
                  var5,
                  new SprayEntry(
                     var5,
                     var4.get("name").getAsString(),
                     ResourceLocationBridge.create(var4.get("resource").getAsString()),
                     Integer.decode("#" + (var4.has("particleColor") ? var4.get("particleColor").getAsString() : null)),
                     ThreadModuleDump9.getFloat(var4, "width", 1.0F),
                     ThreadModuleDump9.getFloat(var4, "height", 1.0F),
                     ThreadModuleDump9.getFloat(var4, "offsetX", 0.0F),
                     ThreadModuleDump9.getFloat(var4, "offsetY", 0.0F),
                     ThreadModuleDump9.getBoolean(var4, "glowing", false),
                     ThreadModuleDump9.getBoolean(var4, "animated", false),
                     ThreadModuleDump9.getBoolean(var4, "canCover", false),
                     ThreadModuleDump9.getInt(var4, "duration", -1)
                  )
               );
         }
      } catch (Exception var6) {
         Slayer.method7("Failed to register sprays from json file");
         var6.printStackTrace();
      }
   }

   private void method9(EventClientTick var1) {
      if (!this.field11.isEmpty()) {
         Iterator var2 = this.field11.values().iterator();

         while (var2.hasNext()) {
            LinkedList var3 = (LinkedList)var2.next();
            var3.removeIf(var0 -> var0.method6() <= EventClientTick.field1);
            if (var3.isEmpty()) {
               var2.remove();
            }
         }
      }
   }

   private void method10(EventEverySecond var1) {
      if (!this.field11.isEmpty()) {
         this.field11.values().removeIf(var0 -> {
            var0.removeIf(var0x -> !method29(var0x.method4(), var0x.method1().method10(), var0x.method3()));
            return var0.isEmpty();
         });
      }
   }

   private void method11(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent var1) {
      AbstractRenderContext var2 = var1.method3();
      boolean var3 = !this.field11.isEmpty();
      if (var3) {
         for (LinkedList var5 : this.field11.values()) {
            for (SprayPlacementTracker var7 : var5) {
               this.method12(var2, var7, -1);
            }
         }
      }

      if (this.field16 != null) {
         this.field16.method1(var1.method5());
         this.method12(var2, this.field16, this.field16.isValid() ? -1996488705 : -1996519288);
      }
   }

   private void method12(AbstractRenderContext var1, SprayPlacement var2, int var3) {
      var1.push();
      Vector3f var4 = var2.method2();
      HorsestatsType_2 var5 = var2.method3();
      Bridge2_43 var6 = ThreadModuleDump63.method13();
      double var7 = 1.0 - var2.method5() * 0.001;
      double var9 = var4.x() - var7 * var5.getOffsetX() + 0.5;
      double var11 = var4.y() - var7 * var5.getOffsetY() + 0.5;
      double var13 = var4.z() - var7 * var5.getOffsetZ() + 0.5;
      var1.translate(var9 - var6.bridge$renderPosX(), var11 - var6.bridge$renderPosY(), var13 - var6.bridge$renderPosZ());
      if (var5.getAxis().isHorizontal()) {
         var1.method13(Horsestats_2.YP.rotationDegrees(180.0F - var5.toYRot()));
         var1.method13(Horsestats_2.ZP.rotationDegrees(180.0F + var2.getRotation()));
      } else {
         var1.method13(Horsestats_2.XP.rotationDegrees(-90 * var5.getDirection().offset()));
         var1.method13(Horsestats_2.YP.rotationDegrees(180.0F));
         var1.method13(Horsestats_2.ZP.rotationDegrees(var2.getRotation()));
      }

      var1.translate(-0.5, -0.5, -0.5);
      Horsestats20Extension2 var15 = Bridge.method8().method6(var9 + var5.getOffsetX(), var11 + var5.getOffsetY(), var13 + var5.getOffsetZ());
      Itemcounter6Extension var16 = ThreadModuleDump63.method8();
      int var17 = var16.bridge$getPackedLight(var15);
      if (var17 == 0) {
         for (Horsestats20Extension2 var21 : var2.method4()) {
            var17 = var16.bridge$getPackedLight(
               Bridge.method8()
                  .method4(var21.bridge$getX() + var5.getOffsetX(), var21.bridge$getY() + var5.getOffsetY(), var21.bridge$getZ() + var5.getOffsetZ())
            );
            if (var17 != 0) {
               break;
            }
         }
      }

      this.method13(var1, var2.method1(), var17, var3);
      var1.pop();
   }

   private void method13(AbstractRenderContext var1, SprayEntry var2, int var3, int var4) {
      CosmeticManager var5 = ThreadModuleDump63.method4().method53();
      Optional var6 = var5.method37(var2.method4(), null);
      if (!var6.isEmpty()) {
         Bridge3_4 var7 = ((Bridge8Extension3)var6.get()).method2();
         if (var7 != null) {
            if (var7 instanceof Bridge3Extension_7 var8) {
               var8.method3(true);
            }

            if (var7 instanceof Alert5 var15) {
               var15.method12();
            }
         }

         Bridge2_32 var16 = var1.method10(LunarRenderTypes.field59.get(var2.method4()));
         var16.method1();
         float var9 = var2.method6();
         float var10 = var2.method7();
         float var11 = var9 + var2.getWidth();
         float var12 = var10 + var2.getHeight();
         var16.method2(var9, var12, 0.0).method10(0.0F, 1.0F).method9(var4).method11(var3).method16();
         var16.method2(var11, var12, 0.0).method10(1.0F, 1.0F).method9(var4).method11(var3).method16();
         var16.method2(var11, var10, 0.0).method10(1.0F, 0.0F).method9(var4).method11(var3).method16();
         var16.method2(var9, var10, 0.0).method10(0.0F, 0.0F).method9(var4).method11(var3).method16();
         var16.method17(BufferBuildMode.IMMEDIATE);
         if (var2.method8()) {
            Optional var13 = PlayerModelPartMap.method2(var2.method4());
            if (var13.isPresent()) {
               int var14 = Bridge.method8().method92();
               var16 = var1.method10(LunarRenderTypes.field59.get((ResourceLocationBridge)var13.get()));
               var16.method1();
               var16.method2(var9, var12, 0.0).method10(0.0F, 1.0F).method9(var4).method11(var14).method16();
               var16.method2(var11, var12, 0.0).method10(1.0F, 1.0F).method9(var4).method11(var14).method16();
               var16.method2(var11, var10, 0.0).method10(1.0F, 0.5F).method9(var4).method11(var14).method16();
               var16.method2(var9, var10, 0.0).method10(0.0F, 0.5F).method9(var4).method11(var14).method16();
               var16.method17(BufferBuildMode.IMMEDIATE);
            }
         }
      }
   }

   @Nullable
   public SprayPlacementTracker method14(SprayEntry var1, Vector3f var2, HorsestatsType_2 var3, float var4) {
      return this.getProvider(var1, var2, var3, var4, true);
   }

   @Nullable
   public SprayPlacementTracker getProvider(SprayEntry var1, Vector3f var2, HorsestatsType_2 var3, float var4, boolean var5) {
      Matrix4f var6 = method28(var2, var3, var4);
      int var7 = this.method17(var1, var6, var3);
      if (var7 > 8) {
         return null;
      }

      BooleanObjectPair var8 = method27(var1, var6, var3, true);
      if (var8 == null) {
         return null;
      }

      if (var5) {
         float var9 = Math.max(var1.getHeight(), var1.getWidth()) + Math.max(var1.method6(), var1.method7());
         Bridge.method63().method2(var2, var3, var1.method5(), var9);
         int var10 = var1.hashCode() & 3;
         ThreadModuleDump63.method3()
            .bridge$getSoundHandler()
            .bridge$play(field3[var10], BridgeType2_7.PLAYERS, 1.0F, 1.0F, false, 0, SoundAttenuationType.NONE, var2.x(), var2.y(), var2.z());
      }

      int var11 = var1.getDuration() != -1 ? var1.getDuration() : 600;
      return new SprayPlacementTracker(var1, EventClientTick.field1 + var11, var2, var3, var4 % 360.0F, (Horsestats20Extension2[])var8.value(), var7);
   }

   public void method16(boolean var1, boolean var2, SprayManager.Extension var3, float var4) {
      if (!var2) {
         long var5 = System.currentTimeMillis();
         if (var5 - this.field15 <= 900L) {
            return;
         }

         this.field15 = var5;
      }

      Bridge5Extension_5 var7 = ThreadModuleDump63.method7();
      Itemcounter6 var6 = var7.bridge$getWorld();
      ((MissResult)SExtension.builder(SImpl.BLOCK_AT)
            .method8(var7, var7.bridge$blockInteractionRange(), var4)
            .method14((var1x, var2x) -> !var2x.bridge$isAir() && var2x.bridge$hasCollision(var6, var1x))
            .method18()
            .method8(var6))
         .method7(var6x -> {
            Vec3Bridge var7x = var6x.IHCORIOIOOHRCOOIOHIHHCOCIHRRHC();
            HorsestatsType_2 var8 = var6x.IOOIROORRHOCCOOIOHHRIRRICHCRIC();
            int var9 = (int)Math.floor(var7x.bridge$xCoord() - Math.abs(var8.getOffsetX()) * 0.025);
            int var10 = (int)Math.floor(var7x.bridge$yCoord() - Math.abs(var8.getOffsetY()) * 0.025);
            int var11 = (int)Math.floor(var7x.bridge$zCoord() - Math.abs(var8.getOffsetZ()) * 0.025);
            Vector3i var12 = var8.getVector().add(var9, var10, var11, new Vector3i());
            boolean var13 = true;
            if (var9 == Math.floor(var7x.bridge$xCoord()) && var10 == Math.floor(var7x.bridge$yCoord()) && var11 == Math.floor(var7x.bridge$zCoord())) {
               if (!var2) {
                  return;
               }

               var13 = false;
            }

            Vector3f var14 = method26(var1, var7x, var8);
            float var15 = method24(var1, var8, var7, var4);
            var3.accept(var6, var7, var8, var12, var14, var15, var13);
         }, () -> {
            if (var1) {
               ThreadModuleDump63.method4().method69().method7(NotificationType.WARNING, "Failed to create spray");
            }
         });
   }

   public int method17(SprayEntry var1, Matrix4f var2, HorsestatsType_2 var3) {
      if (this.field11.isEmpty()) {
         return 1;
      }

      float var4 = var1.getWidth() / 2.0F;
      float var5 = var1.getHeight() / 2.0F;
      SprayManager.Data var6 = new SprayManager.Data(var2, var4, var5, var1.method6(), var1.method7());
      int var7 = 0;

      for (List var9 : this.field11.values()) {
         for (SprayPlacementTracker var11 : var9) {
            if (var11.method3() == var3 && var11.method5() >= var7) {
               SprayEntry var12 = var11.method1();
               SprayManager.Data var13 = new SprayManager.Data(
                  method28(var11.method2(), var3, var11.getRotation()), var12.getWidth() / 2.0F, var12.getHeight() / 2.0F, var12.method6(), var12.method7()
               );
               if (method34(var6, var13)) {
                  var7 = var11.method5();
                  if (var7 >= 8) {
                     return 9;
                  }
               }
            }
         }
      }

      return var7 + 1;
   }

   @Override
   protected Set<SprayEntry> method3() {
      return new HashSet<>();
   }

   public void method19(Object2LongMap<SprayEntry> var1) {
      this.field9.clear();
      this.field9.putAll(var1);
      this.method21();
   }

   public void method20(Set<EquippedSpray> var1) {
      this.field10.clear();
      this.field10.addAll(var1);
      this.method22();
      this.method21();
   }

   public void method21() {
      ObjectSet var1 = this.method41().keySet();
      JsonArray var2 = new JsonArray(var1.size());
      CosmeticManager var3 = ThreadModuleDump63.method4().method53();
      ObjectIterator var4 = var1.iterator();

      while (var4.hasNext()) {
         SprayEntry var5 = (SprayEntry)var4.next();
         var3.method37(var5.method4(), null);
         JsonObject var6 = var5.provide().getAsJsonObject();
         var6.addProperty("active", this.method42().stream().anyMatch(var1x -> var1x.getSprayId() == var5.getId()));
         var2.add(var6);
      }

      this.field7.method3("ownedSprays", var2);
   }

   public void method22() {
      List var1 = this.method42().stream().map(var1x -> this.method5(var1x.getSprayId())).toList();
      JsonArray var2 = new JsonArray(var1.size());

      for (EquippedSpray var4 : this.method42()) {
         SprayEntry var5 = this.method5(var4.getSprayId());
         if (var5 != null) {
            JsonObject var6 = var5.provide().getAsJsonObject();
            var6.addProperty("slotId", var4.getSlotNumber());
            var2.add(var6);
         }
      }

      JsonArray var7 = new JsonArray(this.field13.size());

      for (SprayEntry var9 : this.field13) {
         var7.add(var9.provide());
      }

      this.field7.method3("equippedSprays", var2);
      this.field7.method3("freeLunarPlusSprays", var7);
   }

   public static float method23(float var0) {
      if (ThreadModuleDump63.method4().method41().method8().method40()) {
         float var1 = var0 / 90.0F;
         float var2 = Math.round(var1);
         float var3 = Math.abs(var1 - var2);
         if (var3 <= 0.05 || var3 >= 0.95) {
            return var2 * 90.0F;
         }
      }

      return var0;
   }

   public static float method24(boolean var0, HorsestatsType_2 var1, Bridge5Extension_5 var2, float var3) {
      return switch (var1) {
         case UP, DOWN -> {
            float var4 = var2.bridge$getPrevRotationYawHead() + (var2.bridge$getRotationYawHead() - var2.bridge$getPrevRotationYawHead()) * var3;
            float var5 = var1 == HorsestatsType_2.DOWN ? var4 * -1.0F : var4;
            if (var0) {
               var5 = Math.round(var5 / 90.0F) * 90;
            }

            yield var5;
         }
         default -> 0.0F;
      };
   }

   public static Vector3f method25(boolean var0, Vector3f var1, HorsestatsType_2 var2) {
      if (var0) {
         HorsestatsType$Type var4 = var2.getAxis();
         return var1.set(
            var4 != HorsestatsType$Type.X ? Math.floor(var1.x() + 0.5F) : var1.x(),
            var4 != HorsestatsType$Type.Y ? Math.floor(var1.y() + 0.5F) : var1.y(),
            var4 != HorsestatsType$Type.Z ? Math.floor(var1.z() + 0.5F) : var1.z()
         );
      }

      if (ThreadModuleDump63.method4().method41().method8().method40()) {
         HorsestatsType$Type var3 = var2.getAxis();
         if (var3 != HorsestatsType$Type.X) {
            var1.set(method36(var1.x(), 0.5F, 0.1F), var1.y(), var1.z());
         }

         if (var3 != HorsestatsType$Type.Y) {
            var1.set(var1.x(), method36(var1.y(), 0.5F, 0.1F), var1.z());
         }

         if (var3 != HorsestatsType$Type.Z) {
            var1.set(var1.x(), var1.y(), method36(var1.z(), 0.5F, 0.1F));
         }
      }

      return var1;
   }

   public static Vector3f method26(boolean var0, Vec3Bridge var1, HorsestatsType_2 var2) {
      boolean var3 = var2.getDirection() == HorsestatsType$Type2.NEGATIVE;
      Vector3f var4 = new Vector3f(
         (float)(var1.bridge$xCoord() + (var3 ? var2.getOffsetX() : 0)) + var2.getOffsetX() * 0.005F - (var2.getOffsetX() == 0 ? 0.5F : 0.0F),
         (float)(var1.bridge$yCoord() + (var3 ? var2.getOffsetY() : 0)) + var2.getOffsetY() * 0.005F - (var2.getOffsetY() == 0 ? 0.5F : 0.0F),
         (float)(var1.bridge$zCoord() + (var3 ? var2.getOffsetZ() : 0)) + var2.getOffsetZ() * 0.005F - (var2.getOffsetZ() == 0 ? 0.5F : 0.0F)
      );
      return method25(var0, var4, var2);
   }

   @Nullable
   public static BooleanObjectPair<Horsestats20Extension2[]> method27(SprayEntry var0, Matrix4f var1, HorsestatsType_2 var2, boolean var3) {
      Itemcounter6Extension var4 = ThreadModuleDump63.method8();
      if (var4 == null) {
         return null;
      }

      float var5 = var0.getWidth() / 2.0F;
      float var6 = var0.getHeight() / 2.0F;
      SprayManager.Data var7 = new SprayManager.Data(var1, var5, var6, var0.method6(), var0.method7());
      Vector3f[] var8 = var7.method1();
      Vector3f var9 = new Vector3f(Float.MAX_VALUE);
      Vector3f var10 = new Vector3f(-Float.MAX_VALUE);

      for (Vector3f var14 : var8) {
         var9.min(var14);
         var10.max(var14);
      }

      Vector3f var31 = new Vector3f(var9).add(var10).mul(0.5F);
      Vector3f var32 = new Vector3f(var10).sub(var9).mul(0.5F);
      var8 = method31(var31, var32, var7.field1, var7.field5);
      var9 = new Vector3f(Float.MAX_VALUE);
      var10 = new Vector3f(-Float.MAX_VALUE);

      for (Vector3f var16 : var8) {
         var9.min(var16);
         var10.max(var16);
      }

      Vector3f var34 = var2.getUnitVector();
      var9.sub(var34);
      var10.sub(var34);
      int var36 = (int)Math.floor(var9.x());
      int var37 = (int)Math.floor(var9.y());
      int var38 = (int)Math.floor(var9.z());
      int var17 = (int)Math.ceil(var10.x());
      int var18 = (int)Math.ceil(var10.y());
      int var19 = (int)Math.ceil(var10.z());
      ArrayList var20 = new ArrayList();
      boolean var21 = true;
      boolean var22 = var0.method10();
      Vector3f var23 = new Vector3f(0.5F, 0.5F, 0.5F);

      for (int var24 = var36; var24 < var17; var24++) {
         for (int var25 = var37; var25 < var18; var25++) {
            for (int var26 = var38; var26 < var19; var26++) {
               if (method33(var7, new Vector3f(var24 + 0.5F, var25 + 0.5F, var26 + 0.5F), var23)) {
                  Horsestats20Extension2 var27 = Bridge.method8().method4(var24, var25, var26);
                  if (method30(var22, var4, var27, var2)) {
                     var20.add(var27);
                  } else {
                     if (var3) {
                        return null;
                     }

                     var21 = false;
                  }
               }
            }
         }
      }

      return BooleanObjectPair.of(var21, var20.toArray(new Horsestats20Extension2[0]));
   }

   public static Matrix4f method28(Vector3f var0, HorsestatsType_2 var1, float var2) {
      Matrix4f var3 = new Matrix4f();
      var3.translate(var0.x() - 0.995F * var1.getOffsetX() + 0.5F, var0.y() - 0.995F * var1.getOffsetY() + 0.5F, var0.z() - 0.995F * var1.getOffsetZ() + 0.5F);
      if (var1.getAxis().isHorizontal()) {
         var3.rotate(Horsestats_2.YP.rotationDegrees(180.0F - var1.toYRot()));
         var3.rotate(Horsestats_2.ZP.rotationDegrees(180.0F + var2));
      } else {
         var3.rotate(Horsestats_2.XP.rotationDegrees(-90 * var1.getDirection().offset()));
         var3.rotate(Horsestats_2.YP.rotationDegrees(180.0F));
         var3.rotate(Horsestats_2.ZP.rotationDegrees(var2));
      }

      var3.translate(-0.5F, -0.5F, -0.5F);
      return var3;
   }

   public static boolean method29(Horsestats20Extension2[] var0, boolean var1, HorsestatsType_2 var2) {
      Itemcounter6Extension var3 = ThreadModuleDump63.method8();
      if (var3 == null) {
         return false;
      }

      for (Horsestats20Extension2 var7 : var0) {
         if (!method30(var1, var3, var7, var2)) {
            return false;
         }
      }

      return true;
   }

   public static boolean method30(boolean var0, Itemcounter6 var1, Horsestats20Extension2 var2, HorsestatsType_2 var3) {
      Bridge2_17 var4 = var1.method2(var2);
      if (var4.bridge$getBlock().bridge$isAir()) {
         return false;
      } else if (var4.bridge$getRenderShape() == ItemcounterType_4.INVISIBLE) {
         return false;
      } else {
         return var0
            ? var4.bridge$isCollisionFaceFull(var1, var2, var3)
            : var4.bridge$isOcclusionFaceFull(var1, var2, var3) && var4.bridge$isViewBlocking(var1, var2);
      }
   }

   private static Vector3f[] method31(Vector3f var0, Vector3f var1, Vector3f var2, Vector3f var3) {
      return new Vector3f[]{
         method32(var0.add(var1, new Vector3f()), var2, var3),
         method32(var0.add(new Vector3f(var1.x, -var1.y, -var1.z), new Vector3f()), var2, var3),
         method32(var0.add(new Vector3f(-var1.x, var1.y, -var1.z), new Vector3f()), var2, var3),
         method32(var0.add(new Vector3f(-var1.x, -var1.y, var1.z), new Vector3f()), var2, var3)
      };
   }

   private static Vector3f method32(Vector3f var0, Vector3f var1, Vector3f var2) {
      Vector3f var3 = new Vector3f(var0).sub(var1);
      float var4 = var3.dot(var2);
      return var0.sub(new Vector3f(var2).mul(var4));
   }

   private static boolean method33(SprayManager.Data var0, Vector3f var1, Vector3f var2) {
      Vector3f var3 = method32(var1, var0.field1, var0.field5);
      Vector3f[] var4 = method31(var1, var2, var0.field1, var0.field5);
      Vector3f[] var5 = new Vector3f[]{var0.field4[0], var0.field4[1]};

      for (Vector3f var9 : var5) {
         if (!method35(var0, var3, var4, var9)) {
            return false;
         }
      }

      return true;
   }

   private static boolean method34(SprayManager.Data var0, SprayManager.Data var1) {
      Vector3f[] var2 = var1.method1();
      Vector3f[] var3 = new Vector3f[]{var0.field4[0], var0.field4[1]};

      for (Vector3f var7 : var3) {
         if (!method35(var0, var1.field1, var2, var7)) {
            return false;
         }
      }

      return true;
   }

   private static boolean method35(SprayManager.Data var0, Vector3f var1, Vector3f[] var2, Vector3f var3) {
      float var4 = var0.field1.dot(var3);
      float var5 = var0.field2 * Math.abs(var3.dot(var0.field4[0])) + var0.field3 * Math.abs(var3.dot(var0.field4[1]));
      float var6 = Float.MAX_VALUE;
      float var7 = -Float.MAX_VALUE;

      for (Vector3f var11 : var2) {
         float var12 = var11.dot(var3);
         var6 = Math.min(var6, var12);
         var7 = Math.max(var7, var12);
      }

      float var13 = var1.dot(var3);
      float var14 = Math.max(Math.abs(var7 - var13), Math.abs(var6 - var13));
      return Math.abs(var4 - var13) <= var5 + var14;
   }

   private static double method36(double var0, float var2, float var3) {
      double var4 = (float)Math.round(var0 / var2) * var2;
      return Math.abs(var0 - var4) <= var3 ? var4 : var0;
   }

   public void method37(List<Integer> var1) {
      this.field12 = var1;
      this.field13.clear();

      for (Integer var3 : var1) {
         SprayEntry var4 = this.method5(var3);
         if (var4 == null) {
            Slayer.method5("Could not find free lunar+ spray: %d", var3);
         } else {
            this.field13.add(var4);
         }
      }
   }

   public Optional<EquippedSpray> method38(int var1) {
      return this.field10.stream().filter(var1x -> var1x.getSlotNumber() == var1).findFirst();
   }

   @Generated
   @Override
   public GuiIterator getProvider() {
      return this.field7;
   }

   @Generated
   public Int2ObjectMap<SprayEntry> method40() {
      return this.field8;
   }

   @Generated
   public Object2LongMap<SprayEntry> method41() {
      return this.field9;
   }

   @Generated
   public Set<EquippedSpray> method42() {
      return this.field10;
   }

   @Generated
   public Map<UUID, LinkedList<SprayPlacementTracker>> method43() {
      return this.field11;
   }

   @Nullable
   @Generated
   public List<Integer> method44() {
      return this.field12;
   }

   @Generated
   public List<SprayEntry> method45() {
      return this.field13;
   }

   @Generated
   public int getMaxActiveSprays() {
      return this.field14;
   }

   @Generated
   public void method46(int var1) {
      this.field14 = var1;
   }

   private static class Data {
      private final Vector3f field1;
      private final float field2;
      private final float field3;
      private final Vector3f[] field4;
      private final Vector3f field5;

      public Data(Matrix4f var1, float var2, float var3, float var4, float var5) {
         this.field2 = var2;
         this.field3 = var3;
         this.field1 = new Vector3f();
         var1.getTranslation(this.field1);
         this.field4 = new Vector3f[]{
            new Vector3f(var1.m00(), var1.m01(), var1.m02()).normalize(), new Vector3f(var1.m10(), var1.m11(), var1.m12()).normalize()
         };
         this.field5 = new Vector3f(var1.m20(), var1.m21(), var1.m22()).normalize();
         this.field1.add(new Vector3f(this.field4[0]).mul(var4 + this.field2)).add(new Vector3f(this.field4[1]).mul(var5 + this.field3));
      }

      public Vector3f[] method1() {
         return new Vector3f[]{
            new Vector3f(this.field1).add(new Vector3f(this.field4[0]).mul(this.field2)).add(new Vector3f(this.field4[1]).mul(this.field3)),
            new Vector3f(this.field1).add(new Vector3f(this.field4[0]).mul(this.field2)).sub(new Vector3f(this.field4[1]).mul(this.field3)),
            new Vector3f(this.field1).sub(new Vector3f(this.field4[0]).mul(this.field2)).add(new Vector3f(this.field4[1]).mul(this.field3)),
            new Vector3f(this.field1).sub(new Vector3f(this.field4[0]).mul(this.field2)).sub(new Vector3f(this.field4[1]).mul(this.field3))
         };
      }
   }

   @FunctionalInterface
   public interface Extension {
      void accept(Itemcounter6 var1, Bridge5Extension_5 var2, HorsestatsType_2 var3, Vector3i var4, @NotNull Vector3f var5, float var6, boolean var7);
   }
}
