package com.moonsworth.lunar.client.cosmetics;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.protobuf.Any;
import com.lunarclient.common.v1.Color;
import com.lunarclient.common.v1.Uuid;
import com.lunarclient.websocket.cosmetic.v2.EquippedCosmetic;
import com.lunarclient.websocket.cosmetic.v2.LoginResponse;
import com.lunarclient.websocket.cosmetic.v2.Outfit;
import com.lunarclient.websocket.cosmetic.v2.OwnedCosmetic;
import com.lunarclient.websocket.cosmetic.v2.PlayerCosmeticsPushV2;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.IResourceBridge;
import com.moonsworth.lunar.bridge.Bridge2_10;
import com.moonsworth.lunar.bridge.Bridge2_33;
import com.moonsworth.lunar.bridge.Bridge4_21;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.Bridge8Handler2;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.ClientPacketListenerBridge;
import com.moonsworth.lunar.bridge.Bridge_58;
import com.moonsworth.lunar.bridge.MExtension;
import com.moonsworth.lunar.bridge.MixinHelper_6;
import com.moonsworth.lunar.bridge.SExtension;
import com.moonsworth.lunar.bridge.horsestats.Horsestats;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.TexturedBoxRenderer;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.Gui2Handler2;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.feature.Module2;
import com.moonsworth.lunar.client.feature.ModuleType2;
import com.moonsworth.lunar.client.cosmetics.AbstractCosmetic;
import com.moonsworth.lunar.client.feature.ModuleType3_2;
import com.moonsworth.lunar.client.cosmetics.CosmeticLayerExtension;
import com.moonsworth.lunar.client.framework.loading.ItemSetHandler;
import com.moonsworth.lunar.client.render.jit.JitPaths;
import com.moonsworth.lunar.client.render.jit.JitEmoteResource;
import com.moonsworth.lunar.client.render.jit.JitAnimatedResource;
import com.moonsworth.lunar.client.cosmetics.CosmeticLayerRendererImpl;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteModel;
import com.moonsworth.lunar.client.cosmetics.CosmeticIndexEntry;
import com.moonsworth.lunar.client.cosmetics.ClothCloakSolver;
import com.moonsworth.lunar.client.cosmetics.ClothCloakUpdater;
import com.moonsworth.lunar.client.cosmetics.CosmeticType;
import com.moonsworth.lunar.client.cosmetics.OwnedCosmetic;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.cosmetics.emote.ConditionalOutfitTree;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.event.mixin.gui.ServerChangeEvent;
import com.moonsworth.lunar.client.cosmetics.gecko.ItemRenderMaterial;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.mod.misc.radio.Radio;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;
import com.moonsworth.lunar.client.mod.misc.debug.ShaderDebugMod;
import com.moonsworth.lunar.client.mod.render.skins3d.Skins3d;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91.ScaleTransform;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91.TranslateTransform;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91.RotateTransform;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91.Type;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91.Type2;
import com.moonsworth.lunar.client.util.alert.Alert5;
import com.moonsworth.lunar.client.util.alert.Alert6;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.Annotation2;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.function.Predicate;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import com.moonsworth.lunar.client.account.BadgeManager;

public class CosmeticManager extends ItemSetHandler<OwnedCosmetic> implements Extension, EventRegistrar {
   public static final ResourceLocationBridge field2 = ResourceLocationBridge.create("lunar", "misc/solid_white.png");
   public static final TexturedBoxRenderer field3 = Bridge.method8().method53(22, 17);
   public static final TexturedBoxRenderer field4 = Bridge.method8().method53(64, 32);
   public static final Bridge4_21 field5 = Bridge.method8().method54();
   private static final Gson field6 = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ssX").create();
   private final GuiIterator field7 = new GuiIterator();
   private final Map<Long, OwnedCosmetic> field8 = new HashMap<>();
   private static final ConcurrentHashMap<UUID, ClothCloakSolver> field9 = new ConcurrentHashMap<>();
   private final Map<Integer, CosmeticIndexEntry> field10 = new HashMap<>();
   private final Int2ObjectMap<com.moonsworth.lunar.client.cosmetics.emote.MorphTracker.Data> field11 = new Int2ObjectOpenHashMap();
   private final List<Integer> field12 = new ArrayList<>();
   private final Map<UUID, CosmeticManager.Data> field13 = new HashMap<>();
   private final BiMap<String, UUID> field14 = HashBiMap.create();
   private List<Integer> field15 = new ArrayList<>();
   private final List<CosmeticMetadata> field16 = new ArrayList<>();
   private List<Integer> field17 = new ArrayList<>();
   private List<Integer> field18 = new ArrayList<>();
   private final Set<Integer> field19 = new HashSet<>();
   private final Set<ResourceLocationBridge> field20 = new HashSet<>();
   private final Set<ResourceLocationBridge> field21 = new HashSet<>();
   private final Set<ResourceLocationBridge> field22 = new HashSet<>();
   private static final Set<String> field23 = Set.of("file", "category", "name", "animated", "index_type");
   private final Map<ResourceLocationBridge, ResourceLocationBridge> field24 = new LinkedHashMap<>();
   private static final Map<ModuleType2, Map<String, AbstractCosmetic>> field25 = new HashMap<>();
   private final Map<ResourceLocationBridge, Alert6> field26 = new HashMap<>();
   private static final Function<String, com.moonsworth.lunar.client.feature.HeightOffsetModuleType> field27 = var0 -> {
      Map var1x = field25.get(ModuleType2.HATS);
      return (com.moonsworth.lunar.client.feature.HeightOffsetModuleType)var1x.get(var0.toLowerCase());
   };
   public static final Function<String, AbstractCosmetic> field28 = var0 -> {
      com.moonsworth.lunar.client.feature.HeightOffsetModuleType var1x = field27.apply(var0);
      if (var1x != null) {
         return var1x;
      }

      Map var2 = field25.get(ModuleType2.BODYWEAR);
      return (AbstractCosmetic)var2.get(var0.toLowerCase());
   };
   private CosmeticLayerExtension field29;
   private com.moonsworth.lunar.client.cosmetics.emote.EmoteRenderLayer field30;
   private Bridge2_10 field31;
   private ClothCloakUpdater field32;
   private CosmeticLayerRendererImpl field33;
   @Annotation2(min = 33)
   private List<MExtension<Bridge5_11, EntityPlayerBridge>> field34 = new ArrayList<>();
   public static final AtomicBoolean field35 = new AtomicBoolean(false);
   public static final ResourceLocationBridge field36 = ResourceLocationBridge.create("lunar", "icons/add-16x16.png");
   public static final ResourceLocationBridge field37 = ResourceLocationBridge.create("lunar", "icons/add-64x64.png");
   public static final ResourceLocationBridge field38 = ResourceLocationBridge.create("lunar", "logo/logo-64x64.png");
   public static final ResourceLocationBridge field39 = ResourceLocationBridge.create("lunar", "logo/logo-100x100.png");
   public static final ResourceLocationBridge field40 = ResourceLocationBridge.create("lunar", "logo/logo-256x256.png");
   public static final ResourceLocationBridge field41 = ResourceLocationBridge.create("lunar", "logo/logo-16x16.png");
   public static final ResourceLocationBridge field42 = ResourceLocationBridge.create("lunar", "logo/logo-24x24.png");
   public static final ResourceLocationBridge field43 = ResourceLocationBridge.create("lunar", "logo/logo-32x32.png");
   public static final ResourceLocationBridge field44 = ResourceLocationBridge.create("lunar", "logo/logo-branding-215x33.png");
   public static final ResourceLocationBridge field45 = ResourceLocationBridge.create("lunar", "logo/logo-branding-light-215x33.png");
   public static final ResourceLocationBridge field46 = ResourceLocationBridge.create("lunar", "logo/logo-branding-light-430x66.png");
   @Nullable
   private static final Method field47;

   public CosmeticManager() {
      this.handle(ServerChangeEvent.class, var1 -> {
         UUID var2 = ThreadModuleDump63.method3().bridge$getSession().bridge$getProfile().getId();
         CosmeticManager.Data var3 = this.field13.get(var2);
         this.field13.clear();
         if (var3 != null) {
            this.field13.put(var2, var3);
         }

         ThreadModuleDump63.method4().method81().method22();
      });
      this.handle(EventEverySecond.class, var1 -> this.method39());
      this.method2();
   }

   public void method1(boolean var1) {
      if (!LunarBuildData.field4 || ThreadModuleDump63.method4().method31() == null || ThreadModuleDump63.method4().method31().method29()) {
         Bridge8Handler2 var2 = ThreadModuleDump63.method3().bridge$getTextureManager();

         for (ResourceLocationBridge var4 : this.field22) {
            var2.bridge$deleteTexture(var4);
         }

         this.field22.clear();
         this.field26.clear();
         field35.set(false);
         ThreadModuleDump63.method4().method102().reload();
         ThreadModuleDump63.method4().method96().method7().clear();
         com.moonsworth.lunar.client.cosmetics.gecko.MolangFunctionRegistry var5 = Client.method109().method76();
         var5.clearCache();
         this.method2();
         var5.method1();
         ThreadModuleDump63.method4().method46().method8();
         ShaderDebugMod var6 = ThreadModuleDump63.method4().method40().method74();
         var6.method22();
         this.method53();
         if (var1) {
            Client.method109().method69().method3("Cosmetics reloaded.");
         }
      }
   }

   public void method2() {
      this.field10.clear();
      this.field20.clear();
      this.field21.clear();
      this.method13().removeIf(var1x -> this.field12.contains((int)var1x.method9()));
      this.field12.clear();

      try {
         ResourceLocationBridge var1 = ResourceLocationBridge.create("lunar", "cosmetics.json");
         IResourceBridge var2 = ThreadModuleDump63.method3().bridge$getResourceManager().bridge$getResource(var1);
         if (var2 == null) {
            Slayer.method7("Could not find cosmetics index file: " + var1, new Object[0]);
         } else {
            TypeToken var3 = new TypeToken<List<CosmeticIndexEntry>>() {};

            try (InputStreamReader var4 = new InputStreamReader(var2.bridge$getInputStream())) {
               for (CosmeticIndexEntry var7 : (List)field6.fromJson(var4, var3)) {
                  this.field10.put(var7.getId(), var7);
                  if (var7.method3(CosmeticCategoryType.CLOAK) && !var7.method9()) {
                     ResourceLocationBridge var8 = ResourceLocationBridge.create(var7.method1());
                     if (var8.bridge$getPath().endsWith(".webp")) {
                        this.field20.add(var8);
                     }
                  }
               }
            }
         }
      } catch (Exception var31) {
         Inventorymod2.method5(var31, "CosmeticManager");
      }

      ResourceLocationBridge var32 = ResourceLocationBridge.create("lunar", "dev_cosmetics.json");
      IResourceBridge var33 = ThreadModuleDump63.method3().bridge$getResourceManager().bridge$getResource(var32);
      if (var33 == null) {
         Slayer.method5("Couldn't find dev cosmetics file: " + var32, new Object[0]);
      } else {
         try {
            JsonArray var38 = (JsonArray)ThreadModuleDump48.field22
               .fromJson(new BufferedReader(new InputStreamReader(var33.bridge$getInputStream())), JsonArray.class);
            int var41 = -1000;

            for (JsonElement var51 : var38) {
               ArrayList var54 = new ArrayList();
               ArrayList var57 = new ArrayList();
               if (var51 instanceof JsonObject var9) {
                  method30(var9, var54);
                  String var10 = method31(var9, "file", var57);
                  String var11 = method31(var9, "category", var57);
                  if (var11 == null) {
                     var11 = CosmeticCategoryType.DEV_COSMETICS.getName();
                  }

                  if (var10 == null) {
                     var41--;
                  } else {
                     String var12;
                     if (var9.has("name")) {
                        var12 = var9.get("name").getAsString();
                     } else {
                        String[] var13 = var10.split("/");
                        var12 = var13[var13.length - 1].split("\\.")[0];
                     }

                     boolean var68 = false;
                     if (var9.has("animated")) {
                        var68 = var9.get("animated").getAsBoolean();
                     }

                     String var14 = "NONE";
                     if (var9.has("index_type")) {
                        var14 = var9.get("index_type").getAsString();
                     }

                     boolean var15 = var10.endsWith(".gek.json");
                     if (!var15 && CosmeticCategoryType.from(var11).map(var0 -> var0.getRenderAs() == CosmeticType.GECKOLIB).orElse(false)) {
                        var15 = true;
                     }

                     ItemRenderMaterial var16 = ItemRenderMaterial.ANY;
                     if (var9.has("item_material")) {
                        try {
                           var16 = ItemRenderMaterial.valueOf(var9.get("item_material").getAsString());
                        } catch (IllegalArgumentException var24) {
                        }
                     }

                     CosmeticIndexEntry var17 = new CosmeticIndexEntry(
                        var41,
                        var12,
                        var10,
                        var11,
                        var14,
                        "",
                        null,
                        var15,
                        false,
                        var68,
                        var16,
                        false,
                        Collections.emptyList(),
                        Collections.emptyList(),
                        new Date(),
                        true
                     );
                     this.field10.put(var41, var17);
                     this.field12.add(var41);
                     OwnedCosmetic var18 = this.method12(
                        var17.getId(),
                        var17.getName(),
                        var17.method2().orElse(CosmeticCategoryType.DEV_COSMETICS),
                        var17.getResource(),
                        var17.method8(),
                        -1L,
                        null,
                        var17.method12(),
                        var17.getColors(),
                        var17.method9(),
                        var17.method10(),
                        null
                     );
                     if (var17.method2().isEmpty()) {
                        var18.method8("Unknown category (" + var11 + ")", false);
                     }

                     for (String var20 : var54) {
                        var18.method7(var20, false);
                     }

                     for (String var75 : var57) {
                        var18.method8(var75, false);
                     }

                     this.method13().add(var18);
                     this.method58().put(var18.method9(), var18);
                     Optional var74 = var17.method2();
                     ResourceLocationBridge var76 = ResourceLocationBridge.method1(var17.method1(), "lunar");
                     if (var74.isPresent() && var74.get() == CosmeticCategoryType.CLOAK && !var17.method9()) {
                        try {
                           if (var76.bridge$getPath().endsWith(".webp")) {
                              this.field20.add(var76);
                           }
                        } catch (Exception var23) {
                           Inventorymod2.method5(var23, "CosmeticManager");
                        }
                     }

                     this.field21.add(var76);
                     var41--;
                  }
               }
            }
         } catch (JsonSyntaxException var28) {
            Client.method109().method69().method9("Invalid dev cosmetics file");
         } catch (Exception var29) {
            throw new RuntimeException("Could not load dev cosmetics file", var29);
         }
      }

      try {
         ResourceLocationBridge var39 = ResourceLocationBridge.create("lunar", "cosmetics/indexes/shader_textures.json");
         var33 = ThreadModuleDump63.method3().bridge$getResourceManager().bridge$getResource(var39);
         if (var33 == null) {
            throw new RuntimeException("Shader textures resource does not exist: " + var39);
         }

         BufferedReader var42 = new BufferedReader(new InputStreamReader(var33.bridge$getInputStream()));
         JsonObject var47 = (JsonObject)ThreadModuleDump48.field22.fromJson(var42, JsonObject.class);
         this.method3(var47, false);
      } catch (Exception var22) {
         Inventorymod2.method5(var22, "Reading Shader Texture");
      }

      ResourceLocationBridge var40 = ResourceLocationBridge.create("lunar", "dev_cosmetics/indexes/shader_textures.json");
      var33 = ThreadModuleDump63.method3().bridge$getResourceManager().bridge$getResource(var40);
      if (var33 != null) {
         BufferedReader var43 = new BufferedReader(new InputStreamReader(var33.bridge$getInputStream()));
         JsonObject var48 = (JsonObject)ThreadModuleDump48.field22.fromJson(var43, JsonObject.class);
         this.method3(var48, true);
      }

      field25.clear();

      for (ModuleType2 var55 : ModuleType2.values()) {
         HashMap var58 = new HashMap();

         try {
            var33 = ThreadModuleDump63.method3().bridge$getResourceManager().bridge$getResource(var55.getLocation());
            if (var33 == null) {
               throw new RuntimeException("Cosmetic type location does not exist: " + var55.getLocation());
            }

            InputStream var60 = var33.bridge$getInputStream();
            BufferedReader var62 = new BufferedReader(new InputStreamReader(var60));
            JsonObject var64 = (JsonObject)ThreadModuleDump48.field22.fromJson(var62, JsonObject.class);

            for (Entry var69 : var64.entrySet()) {
               JsonObject var71 = ((JsonElement)var69.getValue()).getAsJsonObject();
               AbstractCosmetic var72 = method29(var71);
               if (var72 != null) {
                  var58.put(var72.getName(), var72);
               }
            }
         } catch (Exception var27) {
            Inventorymod2.method5(var27, "CosmeticManager");
         }

         field25.put(var55, var58);
      }

      try {
         ResourceLocationBridge var45 = ResourceLocationBridge.create("lunar", "cosmetics/indexes/cosmetic_morphs.json");
         var33 = ThreadModuleDump63.method3().bridge$getResourceManager().bridge$getResource(var45);
         if (var33 == null) {
            throw new RuntimeException("Shader textures resource does not exist: " + var45);
         }

         BufferedReader var50 = new BufferedReader(new InputStreamReader(var33.bridge$getInputStream()));
         JsonObject var53 = (JsonObject)ThreadModuleDump48.field22.fromJson(var50, JsonObject.class);

         for (Entry var59 : var53.entrySet()) {
            JsonObject var61 = ((JsonElement)var59.getValue()).getAsJsonObject();
            int var63 = Integer.parseInt((String)var59.getKey());
            String var65 = var61.get("morph").getAsString();
            int var67 = var61.get("cycleTimeTicks").getAsInt();
            com.moonsworth.lunar.client.cosmetics.emote.MorphTracker.Data var70 = new com.moonsworth.lunar.client.cosmetics.emote.MorphTracker.Data(var65, var67);
            this.field11.put(var63, var70);
            ThreadModuleDump63.method4().method70().method11(var65);
         }
      } catch (Exception var26) {
         Inventorymod2.method5(var26, "CosmeticManager");
      }
   }

   private void method3(JsonObject var1, boolean var2) {
      for (Entry var4 : var1.entrySet()) {
         String var5 = (String)var4.getKey();
         JsonObject var6 = ((JsonElement)var4.getValue()).getAsJsonObject();
         ResourceLocationBridge var7 = var2 ? ResourceLocationBridge.create(var5) : ResourceLocationBridge.create("lunar-jit", var5);
         if (var2 && JitPaths.method1(var7)) {
            Slayer.method5("Dev cosmetics aren't allowed to override JIT assets! %s", new Object[]{var7});
         } else {
            Alert6 var8 = com.moonsworth.lunar.client.render.shader.DevShaderEditor.method7(var7, var6, var2);
            if (var8 != null) {
               this.field26.put(var7, var8);
               if (var2) {
                  com.moonsworth.lunar.client.render.jit.JitAssetIndex var9 = ThreadModuleDump63.method4().method96();
                  if (var9 != null) {
                     var9.method1(var7).ifPresent(var1x -> var9.method7().method5(var1x.method9()));
                  }
               }
            }
         }
      }

      if (var2) {
         ShaderDebugMod var10 = ThreadModuleDump63.method4().method40().method74();
         var10.method15();
         var10.method14();
      }
   }

   public void method4(ResourceLocationBridge var1, Alert6 var2) {
      this.field26.put(var1, var2);
      com.moonsworth.lunar.client.render.jit.JitAssetIndex var3 = ThreadModuleDump63.method4().method96();
      if (var3 != null) {
         var3.isPerPlayer(var1).ifPresent(var1x -> var3.method7().method5(var1x.method9()));
      }
   }

   public static ClothCloakSolver method5(EntityPlayerBridge var0) {
      ClothCloakSolver var1 = field9.get(var0.bridge$getUniqueID());
      if (var1 == null) {
         var1 = field9.put(var0.bridge$getUniqueID(), new ClothCloakSolver());
      }

      return var1 == null ? field9.get(var0.bridge$getUniqueID()) : var1;
   }

   public com.moonsworth.lunar.client.feature.HeightOffsetModuleType method6(CosmeticIndexEntry var1) {
      return field27.apply(var1.method5());
   }

   public AbstractCosmetic method7(CosmeticIndexEntry var1) {
      return field28.apply(var1.method5());
   }

   @Override
   protected Set<OwnedCosmetic> method3() {
      return new HashSet<>();
   }

   public void method9(UUID var1, PlayerCosmeticsPushV2 var2, int var3) {
      Horsestats var4 = ThreadModuleDump63.method3().bridge$getSession();
      if (var4 != null) {
         boolean var5 = var1.equals(var4.bridge$getProfile().getId());
         if (Bridge.getMinecraftVersion() == Config.field1 && ThreadModuleDump63.method3().bridge$getWorld() != null) {
            ThreadModuleDump63.method3()
               .bridge$getWorld()
               .bridge$getPlayerByUniqueId(var1)
               .ifPresent(var2x -> this.method64().forcePut(var2x.bridge$getName(), var1));
         }

         if (var5) {
            this.method53();
         }

         int var6 = var2.getLogoColor().getColor();
         int var7 = var2.getPlusColor().getColor();
         boolean var8 = var2.getLogoAlwaysShow();
         float var9 = ThreadModuleDump23.method5(var6);
         float var10 = ThreadModuleDump23.greenFloat(var6);
         float var11 = ThreadModuleDump23.method7(var6);
         int var12 = var7 > 0 ? var7 | 0xFF000000 : 0;
         BadgeManager var13 = ThreadModuleDump63.method4().method95();
         this.method63().compute(var1, (var8x, var9x) -> {
            boolean var10x = false;
            if (var9x == null) {
               if (var5) {
                  Radio var11x = ThreadModuleDump63.method4().method40().method62();
                  if (var11x != null) {
                     var10x = var11x.method13();
                  }
               }
            } else {
               var10x = var9x.field6;
            }

            return new CosmeticManager.Data(var9, var10, var11, var8, var12, var10x, (Gui2Handler2)var13.method2().get(var3));
         });
         ThreadModuleDump63.method4().method81().method22();
      }
   }

   public void method10(LoginResponse var1) {
      this.field19.clear();
      this.field19.addAll(var1.getFavoriteCosmeticIdsList());
      this.field17 = new ArrayList<>(var1.getDiscordRewardsList());
      this.field18 = new ArrayList<>(var1.getDiscordTagRewardsList());
      this.method13().clear();
      this.method58().clear();
      this.method54(var1.getLunarPlusFreeCosmeticIdsList());
      List var2 = var1.getOwnedCosmeticsList();
      boolean var3 = var1.getHasAllCosmeticsFlag();
      if (var3) {
         for (CosmeticIndexEntry var5 : this.method60().values()) {
            if (!var5.method2().isEmpty()) {
               OwnedCosmetic var6 = this.method12(
                  var5.getId(),
                  var5.getName(),
                  var5.method2().get(),
                  var5.getResource(),
                  var5.method8(),
                  -1L,
                  null,
                  var5.method12(),
                  var5.getColors(),
                  var5.method9(),
                  var5.method10(),
                  null
               );
               this.method13().add(var6);
               this.method58().put(var6.method9(), var6);
            }
         }
      } else {
         for (OwnedCosmetic var15 : var2) {
            CosmeticIndexEntry var17 = this.method60().get(var15.getCosmeticId());
            if (var17 != null && !var17.method2().isEmpty()) {
               long var7 = !var15.hasExpiresAt() ? -1L : var15.getExpiresAt().getSeconds() * 1000L;
               if (this.method58().containsKey((long)var17.getId())) {
                  OwnedCosmetic var9 = this.method58().get((long)var17.getId());
                  long var10 = var9.method20();
                  if (var10 < 0L || var7 > 0L && var10 > var7) {
                     continue;
                  }

                  this.method13().remove(var9);
               }

               OwnedCosmetic var20 = this.method12(
                  var17.getId(),
                  var17.getName(),
                  var17.method2().get(),
                  var17.getResource(),
                  var17.method8(),
                  var7,
                  ThreadModuleDump66.method5(var15.getGrantedAt()),
                  var17.method12(),
                  var17.getColors(),
                  var17.method9(),
                  var17.method10(),
                  var15.hasGiftInfo()
                     ? new com.moonsworth.lunar.client.cosmetics.emote.EmoteGiftInfo(
                        ThreadModuleDump66.method1(var15.getGiftInfo().getGiftedBy().getUuid()),
                        var15.getGiftInfo().getGiftedBy().getUsername(),
                        var15.getGiftInfo().getMessage(),
                        var15.getGiftInfo().getIsAnonymous()
                     )
                     : null
               );
               this.method13().add(var20);
               this.method58().put(var20.method9(), var20);
            }
         }
      }

      Horsestats var14 = ThreadModuleDump63.method3().bridge$getSession();
      if (var14 != null && var14.bridge$getProfile() != null) {
         UUID var16 = var14.bridge$getProfile().getId();
         int var18 = var1.getLogoColor().getColor();
         float var19 = ThreadModuleDump23.method5(var18);
         float var8 = ThreadModuleDump23.greenFloat(var18);
         float var21 = ThreadModuleDump23.method7(var18);
         Gui2Handler2 var22 = null;
         if (var1.hasOutfitTree()) {
            Uuid var11 = var1.getOutfitTree().getDefaultOutfitId();
            Optional var12 = var1.getOutfitsList().stream().filter(var1x -> var1x.getId().equals(var11)).findFirst().map(Outfit::getBadgeId);
            if (var12.isPresent()) {
               var22 = (Gui2Handler2)ThreadModuleDump63.method4().method95().method2().get(var12.get());
            }
         }

         int var23 = var1.getPlusColor().getColor();
         this.method63().put(var16, new CosmeticManager.Data(var19, var8, var21, var1.getLogoAlwaysShow(), var23 > 0 ? var23 | 0xFF000000 : 0, false, var22));
         this.method53();
      }
   }

   private boolean method11(UUID var1) {
      ClientPacketListenerBridge var2 = ThreadModuleDump63.method9();
      if (var2 != null) {
         for (Bridge2_33 var4 : var2.bridge$getPlayerInfoMap()) {
            if (var1.equals(var4.bridge$getProfileTextureId()) || var4.bridge$getGameProfile() != null && var1.equals(var4.bridge$getGameProfile().getId())) {
               return var4.bridge$hasMismatchedId();
            }
         }
      }

      return false;
   }

   private OwnedCosmetic method12(
      int var1,
      String var2,
      CosmeticCategoryType var3,
      String var4,
      boolean var5,
      long var6,
      Instant var8,
      List<String> var9,
      List<String> var10,
      boolean var11,
      ItemRenderMaterial var12,
      com.moonsworth.lunar.client.cosmetics.emote.EmoteGiftInfo var13
   ) {
      ResourceLocationBridge var14 = ResourceLocationBridge.create(var4);
      if (var5) {
         return new EmoteModel(var1, var2, var14, var3, var6, var8, var9, var10, var11, var12, var13);
      }

      Alert6 var15 = this.field26.get(var14);
      boolean var16 = var15 != null && var15.isPerPlayer();
      return new OwnedCosmetic(var1, var2, var14, var3, var5, var6, var8, var9, var10, var11, var12, var13, var16);
   }

   public void removePlayer(UUID var1) {
      this.field14.inverse().remove(var1);
      this.field13.remove(var1);
   }

   public Optional<CosmeticMetadata> method13(UUID var1) {
      List var2 = this.method19(var1);
      if (var2 != null) {
         for (CosmeticMetadata var4 : new ArrayList(var2)) {
            if (var4.method4().method10() == CosmeticCategoryType.CLOAK) {
               return Optional.of(var4);
            }
         }
      }

      return Optional.empty();
   }

   public List<CosmeticMetadata> method14(UUID var1, CosmeticCategoryType var2) {
      ArrayList var3 = new ArrayList();
      List var4 = this.method19(var1);
      if (var4 != null) {
         for (CosmeticMetadata var6 : new ArrayList(var4)) {
            if (var6.method4().method10() == var2) {
               var3.add(var6);
            }
         }
      }

      return var3;
   }

   public CosmeticMetadata getProvider(UUID var1, CosmeticCategoryType var2) {
      List var3 = this.method19(var1);
      if (var3 != null) {
         for (CosmeticMetadata var5 : new ArrayList(var3)) {
            if (var5.method4().method10() == var2) {
               return var5;
            }
         }
      }

      return null;
   }

   public List<CosmeticMetadata> method16(UUID var1, CosmeticType var2) {
      ArrayList var3 = new ArrayList();
      List var4 = this.method19(var1);
      if (var4 != null) {
         for (CosmeticMetadata var6 : new ArrayList(var4)) {
            if (var6.method4().method10().getRenderAs() == var2) {
               var3.add(var6);
            }
         }
      }

      return var3;
   }

   public List<CosmeticMetadata> method17(UUID var1, Predicate<CosmeticType> var2) {
      ArrayList var3 = new ArrayList();
      List var4 = this.method19(var1);
      if (var4 != null) {
         for (CosmeticMetadata var6 : new ArrayList(var4)) {
            if (var2.test(var6.method4().method10().getRenderAs())) {
               var3.add(var6);
            }
         }
      }

      return var3;
   }

   public List<CosmeticMetadata> method18(UUID var1) {
      ArrayList var2 = new ArrayList();
      List var3 = this.method19(var1);
      if (var3 != null) {
         for (CosmeticMetadata var5 : new ArrayList(var3)) {
            if (var5.method4() instanceof EmoteModel var6) {
               var2.add(var5);
            }
         }
      }

      return var2;
   }

   public List<CosmeticMetadata> method19(UUID var1) {
      com.moonsworth.lunar.client.cosmetics.Outfit var2 = ThreadModuleDump63.method4().method55().method4(var1);
      return var2 == null ? Collections.emptyList() : var2.method4();
   }

   public boolean method20(ResourceLocationBridge var1) {
      return this.field24.containsKey(var1);
   }

   @Override
   public void init() {
      try {
         super.init();
         this.field29 = new CosmeticLayerExtension(this);
         this.field30 = new com.moonsworth.lunar.client.cosmetics.emote.EmoteRenderLayer(this);
         this.field32 = new ClothCloakUpdater(this);
         this.field33 = new CosmeticLayerRendererImpl(this);
         Bridge.method9().bridge$getEntityRenderDispatcher().bridge$onPlayerRenderersReloaded(() -> {
            for (MixinHelper_6 var2 : Bridge.method9().bridge$getEntityRenderDispatcher().bridge$getSkinMap().values()) {
               if (var2 instanceof SExtension var3x) {
                  if (ThreadModuleDump63.MC_VERSION >= 33) {
                     this.field34.clear();
                     this.field34.add(this.field29);
                     this.field34.add(this.field30);
                     this.field34.add(this.field32);
                     Skins3d.method13().method7(this.field34::add);
                     this.field34.add(this.field33);
                  } else {
                     var3x.bridge$addLayer(this.field29, true);
                     var3x.bridge$addLayer(this.field30);
                     var3x.bridge$addLayer(this.field32);
                     Skins3d.method13().method7(var3x::bridge$addLayer);
                     var3x.bridge$addLayer(this.field33);
                  }
               }

               if (Bridge.getMinecraftVersion() != Config.field1) {
                  this.field31 = ((SExtension)Bridge.method9().bridge$getEntityRenderDispatcher().bridge$defaultPlayerRenderer()).bridge$getLayerCape();
               }
            }
         });
         ArrayList var1 = new ArrayList();

         for (Map var3 : field25.values()) {
            var1.addAll(var3.values());
         }

         this.handle(EventClientTick.class, var2 -> {
            if (ThreadModuleDump63.method3().bridge$areResourcesLoaded()) {
               if (!field35.get()) {
                  this.field10.forEach((var1xx, var2x) -> {
                     if (!var2x.method8()) {
                        ResourceLocationBridge var3x = ResourceLocationBridge.create(var2x.method1());
                        if (!ThreadModuleDump63.method3().bridge$getTextureManager().bridge$getTextureMap().containsKey(var3x)) {
                           this.field22.add(var3x);
                        }

                        if (!var2x.method3(CosmeticCategoryType.CLOAK) && var2x.method9() && !this.field24.containsKey(var3x)) {
                           AbstractCosmetic var4xx = field28.apply(var2x.method5());
                           if (var4xx != null) {
                              this.field24.put(var3x, var4xx.method2());
                           }
                        }
                     }
                  });
                  field35.set(true);
               }

               if (!ThreadModuleDump63.method4().method40().method85().method17(var0 -> var0.method41().method18() / 50L > 20L)) {
                  for (AbstractCosmetic var4x : var1) {
                     if (var4x.method4()) {
                        var4x.tick();
                     }
                  }

                  for (ResourceLocationBridge var10 : this.field24.values()) {
                     Bridge8Extension3 var5 = ThreadModuleDump63.method3().bridge$getTextureManager().method1(var10);
                     if (var5 != null && var5.method2() instanceof Alert5 var7) {
                        var7.method12();
                        Bridge_58 var8 = var7.method7();
                        if (var8 != null) {
                           var8.updateAnimation();
                        }
                     }
                  }
               }
            }
         });
      } catch (Throwable var4) {
         throw var4;
      }
   }

   public void method21() {
      this.method52();
   }

   public Optional<CosmeticMetadata> method22(long var1) {
      ConditionalOutfitTree var3 = ThreadModuleDump63.method4().method55().method17();
      return var3 != null && var3.method5() != null
         ? var3.method5().method4().stream().filter(var2 -> var2.method4().method9() == var1).findFirst()
         : Optional.empty();
   }

   public CosmeticMetadata method23(long var1) {
      return this.method22(var1).orElseGet(() -> {
         OwnedCosmetic var3 = this.method58().get(var1);
         return var3 == null ? null : new CosmeticMetadata(var3, new JsonObject());
      });
   }

   public void method24() {
      this.method53();
   }

   public void method25() {
      RewindHandlers5 var1 = ThreadModuleDump63.method4().method40().method85().method34();
      if (var1 != null) {
         for (Entry var3 : this.field13.entrySet()) {
            UUID var4 = (UUID)var3.getKey();
            CosmeticManager.Data var5 = (CosmeticManager.Data)var3.getValue();
            List var6 = this.method19(var4)
               .stream()
               .map(var0 -> EquippedCosmetic.newBuilder().setCosmeticId((int)var0.method4().method9()).setGeckolibMetadata(var0.method2()).build())
               .toList();
            PlayerCosmeticsPushV2 var7 = PlayerCosmeticsPushV2.newBuilder()
               .setPlayerUuid(ThreadModuleDump66.method3(var4))
               .setLogoColor(Color.newBuilder().setColor(ThreadModuleDump23.method11(var5.method5(), var5.method6(), var5.method7(), 1.0F)))
               .setPlusColor(Color.newBuilder().setColor(var5.method9() & 16777215))
               .setLogoAlwaysShow(var5.method8())
               .setBadgeId(var5.method11() != null ? var5.method11().id() : 0)
               .addAllDefaultCosmetics(var6)
               .build();
            var1.method1(Any.pack(var7));
         }
      }
   }

   @Nullable
   public UUID method26(GameProfile var1) {
      try {
         if (var1.getProperties().containsKey("textures")) {
            Property var2 = (Property)Iterables.getFirst(var1.getProperties().get("textures"), null);
            if (var2 == null) {
               return null;
            }

            String var3 = method27(var2);
            String var4 = new String(Base64.getDecoder().decode(var3), StandardCharsets.UTF_8);
            JsonObject var5 = new JsonParser().parse(var4).getAsJsonObject();
            String var6 = var5.get("profileId").getAsString().replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5");
            return UUID.fromString(var6);
         }
      } catch (Exception var7) {
      }

      return null;
   }

   private static String method27(Property var0) {
      return ThreadModuleDump63.MC_VERSION >= 19 ? var0.value() : (String)field47.invoke(var0);
   }

   public void method28(UUID var1, UUID var2) {
      ThreadModuleDump63.method4().method55().method15(var1, var2);
      if (this.field13.containsKey(var1)) {
         CosmeticManager.Data var3 = this.field13.get(var1);
         if (!this.field13.containsKey(var2)) {
            this.field13.put(var2, new CosmeticManager.Data(var3.field1, var3.field2, var3.field3, var3.field4, var3.field5, var3.field6, var3.field7));
            ThreadModuleDump63.method4().method81().method22();
         }

         ThreadModuleDump63.method4().method54().method9(var3.isPerPlayer());
      }
   }

   private static AbstractCosmetic method29(JsonObject var0) {
      if (!var0.has("cosmeticType")) {
         return null;
      }

      ModuleType2 var1 = ModuleType2.valueOf(var0.get("cosmeticType").getAsString().toUpperCase(Locale.ROOT));

      return (AbstractCosmetic)(switch (var1) {
         case HATS -> new com.moonsworth.lunar.client.feature.HeightOffsetModuleType(var0);
         case BODYWEAR -> new ModuleType3_2(var0);
         default -> throw new IncompatibleClassChangeError();
      });
   }

   private static void method30(JsonObject var0, List<String> var1) {
      for (String var3 : var0.keySet()) {
         if (!field23.contains(var3)) {
            Slayer.method5("Unknown dev cosmetic key '" + var3 + "': " + var0, new Object[0]);
            var1.add("Unknown dev_cosmetics key '" + var3 + "'.");
         }
      }
   }

   private static String method31(JsonObject var0, String var1, List<String> var2) {
      if (var0.has(var1) && !var0.get(var1).isJsonNull()) {
         return var0.get(var1).getAsString();
      }

      Slayer.method7("Dev cosmetic missing required key '" + var1 + "': " + var0, new Object[0]);
      var2.add("Missing required dev_cosmetics key '" + var1 + "'.");
      return null;
   }

   public static List<ThreadModuleDump91> method32(JsonObject var0) {
      ArrayList var1 = new ArrayList();
      if (!var0.has("transformType")) {
         Slayer.method7("Could not find transformationType in json: " + var0, new Object[0]);
         return var1;
      }

      Type2 var2 = Type2.valueOf(var0.get("transformType").getAsString().toUpperCase(Locale.ROOT));
      JsonObject var3 = var0.get("values").getAsJsonObject();
      boolean var4 = false;

      for (Type var8 : Type.values()) {
         if (var3.has(var8.name().toLowerCase(Locale.ROOT))) {
            var4 = true;
            JsonObject var9 = var3.getAsJsonObject(var8.name().toLowerCase(Locale.ROOT));
            Vector3f var10 = method33(var9);
            switch (var2) {
               case SCALE:
                  var1.add(new ScaleTransform(var10, var8));
                  break;
               case TRANSLATE:
                  var1.add(new TranslateTransform(var10, var8));
                  break;
               case ROTATE:
                  var1.add(new RotateTransform(var9.get("angle").getAsFloat(), var10.x(), var10.y(), var10.z(), var8));
            }
         }
      }

      if (var4) {
         return var1;
      }

      if (var3.has("x") && var3.has("y") && var3.has("z")) {
         var1.add(switch (var2) {
            case SCALE -> new ScaleTransform(var0, Type.NONE);
            case TRANSLATE -> new TranslateTransform(var0, Type.NONE);
            case ROTATE -> new RotateTransform(var0, Type.NONE);
            case MIXED -> new com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91.Data(var0);
            default -> throw new IncompatibleClassChangeError();
         });
      }

      return var1;
   }

   private static Vector3f method33(JsonObject var0) {
      float var1 = var0.get("x").getAsFloat();
      float var2 = var0.get("y").getAsFloat();
      float var3 = var0.get("z").getAsFloat();
      return new Vector3f(var1, var2, var3);
   }

   public boolean method34(CosmeticMetadata var1) {
      return var1 != null && ThreadModuleDump63.method4().method41().method6().method36().get() ? var1.method6().method3() : false;
   }

   public Optional<Bridge8Extension3> method35(CosmeticMetadata var1, UUID var2) {
      return this.method37(var1.method4().method3(), var2);
   }

   public Optional<Bridge8Extension3> method36(OwnedCosmetic var1, UUID var2) {
      return this.method37(var1.method3(), var2);
   }

   public Optional<Bridge8Extension3> method37(ResourceLocationBridge var1, @Nullable UUID var2) {
      if (var1 == null) {
         return Optional.empty();
      }

      var1 = method38(var1, var2);
      Alert6 var3 = this.field26.get(var1);
      if (!JitPaths.method1(var1)) {
         Bridge8Handler2 var8 = ThreadModuleDump63.method3().bridge$getTextureManager();
         Bridge8Extension3 var9 = var8.bridge$getTexture(var1);
         ShaderDebugMod var6 = ThreadModuleDump63.method4().method40().method74();
         if (var3 != null && var6.isValid()) {
            var6.method6(var1, var3, var9, var2);
         }

         return Optional.of(var9);
      } else {
         com.moonsworth.lunar.client.render.jit.JitAssetIndex var4 = ThreadModuleDump63.method4().method96();
         com.moonsworth.lunar.client.render.jit.JitResource var5;
         if (var3 != null) {
            if (var2 != null && var3.isPerPlayer()) {
               var1 = ResourceLocationBridge.create(var1.bridge$getDomain(), var1.bridge$getPath() + "_" + var2);
            }

            var5 = var4.method2(var1, var1x -> new JitEmoteResource(var1x, var3));
         } else {
            var5 = var4.method2(var1, var0 -> new JitAnimatedResource(var0, true));
         }

         return var5.method1();
      }
   }

   public static ResourceLocationBridge method38(ResourceLocationBridge var0, @Nullable UUID var1) {
      if (var1 == null) {
         return var0;
      }

      String var2 = "_" + var1;
      if (var0.bridge$getPath().endsWith(var2)) {
         String var3 = var0.bridge$getPath();
         int var4 = var3.length() - var2.length();
         if (var4 > 0) {
            return ResourceLocationBridge.create(var0.bridge$getDomain(), var3.substring(0, var4));
         }
      }

      return var0;
   }

   private void method39() {
      Bridge5Extension_5 var1 = ThreadModuleDump63.method7();
      com.moonsworth.lunar.client.render.jit.JitAssetIndex var2 = ThreadModuleDump63.method4().method96();
      if (var1 != null && var2 != null) {
         UUID var3 = var1.bridge$getUniqueID();

         for (CosmeticMetadata var6 : this.method19(var3)) {
            ResourceLocationBridge var7 = var6.method4().method6(var3);
            if (JitPaths.method1(var7)) {
               var2.method1(var7);
            }
         }
      }
   }

   public static CosmeticMetadata method40(List<CosmeticMetadata> var0, Predicate<CosmeticType> var1, int var2) {
      if (var0 == null) {
         return null;
      }

      int var3 = 0;

      for (CosmeticMetadata var5 : var0) {
         OwnedCosmetic var6 = var5.method4();
         if (var1.test(var6.method10().getRenderAs())) {
            if (var3 == var2) {
               return var5;
            }

            var3++;
         }
      }

      return null;
   }

   public static OwnedCosmetic method41(List<CosmeticMetadata> var0, CosmeticCategoryType var1, int var2) {
      if (var0 == null) {
         return null;
      }

      int var3 = 0;

      for (CosmeticMetadata var5 : var0) {
         OwnedCosmetic var6 = var5.method4();
         if (var6.method10() == var1) {
            if (var3 == var2) {
               return var6;
            }

            var3++;
         }
      }

      return null;
   }

   public static CosmeticMetadata method42(List<CosmeticMetadata> var0, CosmeticCategoryType var1, int var2) {
      if (var0 == null) {
         return null;
      }

      int var3 = 0;

      for (CosmeticMetadata var5 : var0) {
         if (var5.method4().method10() == var1) {
            if (var3 == var2) {
               return var5;
            }

            var3++;
         }
      }

      return null;
   }

   public static CosmeticMetadata method43(List<CosmeticMetadata> var0, int var1) {
      if (var0 == null) {
         return null;
      }

      int var2 = 0;

      for (CosmeticMetadata var4 : var0) {
         if (var4.method4() instanceof EmoteModel) {
            if (var2 == var1) {
               return var4;
            }

            var2++;
         }
      }

      return null;
   }

   public static int method44(List<CosmeticMetadata> var0, Predicate<CosmeticType> var1) {
      if (var0 == null) {
         return 0;
      }

      int var2 = 0;

      for (CosmeticMetadata var4 : var0) {
         OwnedCosmetic var5 = var4.method4();
         if (var1.test(var5.method10().getRenderAs())) {
            var2++;
         }
      }

      return var2;
   }

   public static int method45(List<CosmeticMetadata> var0, CosmeticCategoryType var1) {
      if (var0 == null) {
         return 0;
      }

      int var2 = 0;

      for (CosmeticMetadata var4 : var0) {
         OwnedCosmetic var5 = var4.method4();
         if (var5.method10() == var1) {
            var2++;
         }
      }

      return var2;
   }

   public static int method46(List<CosmeticMetadata> var0) {
      int var1 = 0;
      if (var0 != null) {
         for (CosmeticMetadata var3 : var0) {
            if (var3.method4() instanceof EmoteModel) {
               var1++;
            }
         }
      }

      return var1;
   }

   public static boolean method47(List<CosmeticMetadata> var0, CosmeticCategoryType var1) {
      if (var0 == null) {
         return false;
      }

      for (CosmeticMetadata var3 : var0) {
         OwnedCosmetic var4 = var3.method4();
         if (var4.method10() == var1) {
            return true;
         }
      }

      return false;
   }

   @Nullable
   public CosmeticMetadata method48(int var1, JsonObject var2) {
      CosmeticIndexEntry var3 = this.field10.get(var1);
      return var3 != null && !var3.method2().isEmpty()
         ? new CosmeticMetadata(
            this.method12(
               var1,
               var3.getName(),
               var3.method2().get(),
               var3.getResource(),
               var3.method8(),
               -1L,
               null,
               var3.isPerPlayer2(),
               var3.getColors(),
               var3.method9(),
               var3.isPerPlayer0(),
               null
            ),
            var2 == null ? new JsonObject() : var2
         )
         : null;
   }

   public ClothCloakSolver method49(UUID var1) {
      return field9.get(var1);
   }

   @Nullable
   public CosmeticMetadata method50(int var1, Module2 var2) {
      OwnedCosmetic var3 = this.method51(var1);
      if (var3 == null) {
         return null;
      } else {
         return var2 == null ? new CosmeticMetadata(var3, new JsonObject()) : new CosmeticMetadata(var3, var2);
      }
   }

   @Nullable
   public OwnedCosmetic method51(int var1) {
      CosmeticIndexEntry var2 = this.field10.get(var1);
      return var2 != null && !var2.method2().isEmpty()
         ? this.method12(
            var1,
            var2.getName(),
            var2.method2().get(),
            var2.getResource(),
            var2.method8(),
            -1L,
            null,
            var2.method12(),
            var2.getColors(),
            var2.method9(),
            var2.method10(),
            null
         )
         : null;
   }

   public void method52() {
      this.method13().clear();
      this.method53();
   }

   public void method53() {
      CosmeticCategoryType[] var1 = CosmeticCategoryType.values();
      JsonArray var2 = new JsonArray(var1.length);

      for (CosmeticCategoryType var6 : var1) {
         if (var6.getParent() == null
            && (
               var6 != CosmeticCategoryType.DEV_COSMETICS
                  || ThreadModuleDump63.method4().method31() != null
                     && ThreadModuleDump63.method4().method31().method29()
                     && !ThreadModuleDump63.method4().method53().method62().isEmpty()
            )) {
            var2.add(var6.provide());
         }
      }

      Set var14 = this.method13();
      HashSet var15 = new HashSet(var14.size());
      JsonArray var16 = new JsonArray(var14.size());

      for (OwnedCosmetic var7 : var14) {
         var15.add((int)var7.method9());
         var16.add(var7.provide());
      }

      JsonArray var18 = new JsonArray(this.field17.size());

      for (Integer var8 : this.field17) {
         var18.add(var8);
      }

      JsonArray var20 = new JsonArray(this.field18.size());

      for (Integer var9 : this.field18) {
         var20.add(var9);
      }

      LinkedHashSet var22 = new LinkedHashSet<>(this.field17);
      var22.addAll(this.field18);
      JsonArray var23 = new JsonArray(var22.size());

      for (Integer var11 : var22) {
         CosmeticMetadata var12 = this.method48(var11, new JsonObject());
         if (var12 != null) {
            JsonObject var13 = var12.provide().getAsJsonObject();
            var13.addProperty("owned", var15.contains(var11));
            var23.add(var13);
         }
      }

      JsonArray var24 = new JsonArray(this.field16.size());

      for (CosmeticMetadata var26 : this.field16) {
         var24.add(var26.provide());
      }

      this.field7.method3("types", var2);
      this.field7.method3("ownedCosmetics", var16);
      this.field7.method3("discordCosmeticIds", var18);
      this.field7.method3("discordTagCosmeticIds", var20);
      this.field7.method3("discordRewardCosmetics", var23);
      this.field7.method3("freeLunarPlusCosmetics", var24);
   }

   public void method54(List<Integer> var1) {
      this.field15 = var1;
      this.field16.clear();

      for (Integer var3 : var1) {
         CosmeticMetadata var4 = this.method48(var3, null);
         if (var4 == null) {
            Slayer.method5("Could not find free lunar+ cosmetic: %d", new Object[]{var3});
         } else {
            this.field16.add(var4);
         }
      }

      this.method53();
   }

   public void method55(UUID var1, List<Integer> var2, @Nullable Map<Integer, Module2> var3) {
      OutfitManager var4 = ThreadModuleDump63.method4().method55();
      ArrayList var5 = new ArrayList<>(this.method19(var1));

      for (int var7 : var2) {
         Module2 var8 = var3 != null ? (Module2)var3.get(var7) : null;
         CosmeticMetadata var9 = this.method50(var7, var8);
         if (var9 == null) {
            Slayer.method5("Could not find cosmetic id %d for NPC %s", new Object[]{var7, var1});
         } else {
            if (var9.method4().method10() == CosmeticCategoryType.COMPANION) {
               var5.removeIf(var0 -> var0.method4().method10() == CosmeticCategoryType.COMPANION);
            }

            boolean var10 = var5.stream().anyMatch(var1x -> var1x.method4().method9() == var7);
            if (!var10) {
               var5.add(var9);
            }
         }
      }

      var4.method10(var1, var5);
      ThreadModuleDump63.method4().method88().method11(var1);
   }

   public void method56(UUID var1, List<Integer> var2) {
      OutfitManager var3 = ThreadModuleDump63.method4().method55();
      ArrayList var4 = new ArrayList<>(this.method19(var1));
      HashSet var5 = new HashSet(var2);
      var4.removeIf(var1x -> var5.contains((int)var1x.method4().method9()));
      var3.isPerPlayer0(var1, var4);
      ThreadModuleDump63.method4().method88().method11(var1);
   }

   @Generated
   public GuiIterator getProvider() {
      return this.field7;
   }

   @Generated
   public Map<Long, OwnedCosmetic> method58() {
      return this.field8;
   }

   @Generated
   public static ConcurrentHashMap<UUID, ClothCloakSolver> method59() {
      return field9;
   }

   @Generated
   public Map<Integer, CosmeticIndexEntry> method60() {
      return this.field10;
   }

   @Generated
   public Int2ObjectMap<com.moonsworth.lunar.client.cosmetics.emote.MorphTracker.Data> method61() {
      return this.field11;
   }

   @Generated
   public List<Integer> method62() {
      return this.field12;
   }

   @Generated
   public Map<UUID, CosmeticManager.Data> method63() {
      return this.field13;
   }

   @Generated
   public BiMap<String, UUID> method64() {
      return this.field14;
   }

   @Generated
   public List<Integer> method65() {
      return this.field15;
   }

   @Generated
   public List<CosmeticMetadata> method66() {
      return this.field16;
   }

   @Generated
   public List<Integer> method67() {
      return this.field17;
   }

   @Generated
   public List<Integer> method68() {
      return this.field18;
   }

   @Generated
   public Set<Integer> method69() {
      return this.field19;
   }

   @Generated
   public Set<ResourceLocationBridge> method70() {
      return this.field20;
   }

   @Generated
   public Set<ResourceLocationBridge> method71() {
      return this.field21;
   }

   @Generated
   public Map<ResourceLocationBridge, ResourceLocationBridge> method72() {
      return this.field24;
   }

   @Generated
   public Map<ResourceLocationBridge, Alert6> method73() {
      return this.field26;
   }

   @Generated
   public CosmeticLayerExtension method74() {
      return this.field29;
   }

   @Generated
   public com.moonsworth.lunar.client.cosmetics.emote.EmoteRenderLayer method75() {
      return this.field30;
   }

   @Generated
   public Bridge2_10 method76() {
      return this.field31;
   }

   @Generated
   public ClothCloakUpdater method77() {
      return this.field32;
   }

   @Generated
   public CosmeticLayerRendererImpl method78() {
      return this.field33;
   }

   @Generated
   public List<MExtension<Bridge5_11, EntityPlayerBridge>> method79() {
      return this.field34;
   }

   static {
      try {
         field47 = ThreadModuleDump63.MC_VERSION >= 19 ? null : Property.class.getDeclaredMethod("getValue");
      } catch (NoSuchMethodException var1) {
         throw new RuntimeException(var1);
      }
   }

   public static class Data {
      private final float field1;
      private final float field2;
      private final float field3;
      private final boolean field4;
      private final int field5;
      private final boolean field6;
      private Gui2Handler2 field7;

      public boolean method1() {
         return this.field5 != 0;
      }

      public CosmeticManager.Data method2(int var1) {
         return this.field5 == var1 ? this : new CosmeticManager.Data(this.field1, this.field2, this.field3, this.field4, var1, this.field6, this.field7);
      }

      public CosmeticManager.Data method3(boolean var1) {
         return this.field6 == var1 ? this : new CosmeticManager.Data(this.field1, this.field2, this.field3, this.field4, this.field5, var1, this.field7);
      }

      public CosmeticManager.Data method4(Gui2Handler2 var1) {
         return this.field7 == var1 ? this : new CosmeticManager.Data(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, var1);
      }

      @Generated
      public Data(float var1, float var2, float var3, boolean var4, int var5, boolean var6, Gui2Handler2 var7) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
         this.field5 = var5;
         this.field6 = var6;
         this.field7 = var7;
      }

      @Generated
      public float method5() {
         return this.field1;
      }

      @Generated
      public float method6() {
         return this.field2;
      }

      @Generated
      public float method7() {
         return this.field3;
      }

      @Generated
      public boolean method8() {
         return this.field4;
      }

      @Generated
      public int method9() {
         return this.field5;
      }

      @Generated
      public boolean method10() {
         return this.field6;
      }

      @Generated
      public Gui2Handler2 method11() {
         return this.field7;
      }
   }
}
