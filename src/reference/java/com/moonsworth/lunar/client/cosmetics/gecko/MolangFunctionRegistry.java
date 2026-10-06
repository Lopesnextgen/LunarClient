package com.moonsworth.lunar.client.cosmetics.gecko;

import com.eliotlash.molang.Molang;
import com.eliotlash.molang.ast.Evaluatable;
import com.eliotlash.molang.ast.Expr;
import com.eliotlash.molang.ast.Stmt;
import com.eliotlash.molang.ast.Expr.Access;
import com.eliotlash.molang.ast.Expr.Call;
import com.eliotlash.molang.ast.Stmt.Expression;
import com.eliotlash.molang.functions.FunctionDefinition;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lunarclient.apollo.module.limb.BodyPart;
import com.moonsworth.lunar.MixinHelper27;
import com.moonsworth.lunar.MixinHelper542;
import com.moonsworth.lunar.MixinHelper_20;
import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.IResourceBridge;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge8Handler2;
import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.bridge.BridgeType2_5;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.ItemTransformsBridge;
import com.moonsworth.lunar.bridge.horsestats.ItemTransformVec3fBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.loading.LoadableHandler;
import com.moonsworth.lunar.client.render.jit.JitAssetIndex;
import com.moonsworth.lunar.client.render.jit.JitPaths;
import com.moonsworth.lunar.client.render.jit.JitEntityResource;
import com.moonsworth.lunar.client.render.jit.JitItemResource;
import com.moonsworth.lunar.client.render.jit.JitItemModelResource;
import com.moonsworth.lunar.client.render.jit.JitImageResource;
import com.moonsworth.lunar.client.render.jit.JitAnimatedResource;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteModel;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.molang.MolangVariablePath;
import com.moonsworth.lunar.client.cosmetics.molang.MolangScope;
import com.moonsworth.lunar.client.cosmetics.molang.MolangStmtCompiler;
import com.moonsworth.lunar.client.cosmetics.molang.MolangSymbol;
import com.moonsworth.lunar.client.cosmetics.molang.MolangCustomFunction;
import com.moonsworth.lunar.client.cosmetics.molang.MolangVariable;
import com.moonsworth.lunar.client.cosmetics.molang.ast.ConstantEvaluatable;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.highlight.ModelRenderEvent;
import com.moonsworth.lunar.client.inactive.Inactive3_2;
import com.moonsworth.lunar.client.cosmetics.gecko.AnimationKeyframeParserLegacy;
import com.moonsworth.lunar.client.cosmetics.molang.FunctionImpl;
import com.moonsworth.lunar.client.cosmetics.gecko.AnimationFileLoader;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelGeometryLoader;
import com.moonsworth.lunar.client.cosmetics.gecko.BoneList;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.util.concurrent.SupplierExtension;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.render.texture.Util2Handler;
import io.sentry.Hint;
import it.unimi.dsi.fastutil.Pair;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Supplier;
import lombok.Generated;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.inactive.mixin.Gui2Handler;
import com.moonsworth.lunar.client.inactive.mixin.Gui2Impl2;

public class MolangFunctionRegistry implements LoadableHandler {
   private static final SupplierExtension<ResourceLocationBridge> field1 = SupplierExtension.lazy(() -> ResourceLocationBridge.create("lunar", "cosmetics/functions.molang"));
   private static final SupplierExtension<ResourceLocationBridge> field2 = SupplierExtension.lazy(() -> ResourceLocationBridge.create("lunar", "cosmetics/constants.molang"));
   private static String field3 = null;
   private static String field4 = null;
   private final Map<FunctionDefinition, FunctionImpl> field5 = new HashMap<>();
   private static Bridge5_11 field6;
   private final AnimationFileLoader field7;
   public boolean field8;
   private Evaluatable field9 = new ConstantEvaluatable(0.0);
   public final Gson field10 = new GsonBuilder()
      .registerTypeAdapter(ItemTransformVec3fBridge.class, new ItemTransformVec3fBridge.Data())
      .registerTypeAdapter(ItemTransformsBridge.class, new ItemTransformsBridge.Data())
      .create();
   @Nullable
   private Map<ResourceLocationBridge, Gui2Handler> field11;
   @Nullable
   private Map<ResourceLocationBridge, Object> field12;

   public MolangFunctionRegistry() {
      this.field7 = new AnimationFileLoader();
   }

   @Override
   public void init() {
      Client.field2.submit(this::method1);
      ClientEventBus.method29().method2(ModelRenderEvent.BipedModelRenderEvent.class, var0 -> {
         if (var0.method2() instanceof BridgeExtension2_7 var1 && (ThreadModuleDump63.MC_VERSION > 0 || var1.bridge$isMainModel())) {
            Set var11 = var0.method4();
            List var3 = ThreadModuleDump63.method4().method53().method19(var0.method1().bridge$getUniqueID());
            if (var3 != null) {
               for (CosmeticMetadata var5 : var3) {
                  com.moonsworth.lunar.client.cosmetics.OwnedCosmetic var6 = var5.method4();
                  if (var6 instanceof EmoteModel var7 && var6.method10().canShowCosmetic()) {
                     try {
                        var7.method6().ifPresent(var1x -> {
                           if (var1x.method14()) {
                              var11.add(BodyPart.HEAD);
                           }

                           if (var1x.method15()) {
                              var11.add(BodyPart.TORSO);
                           }

                           if (var1x.method16()) {
                              var11.add(BodyPart.RIGHT_ARM);
                           }

                           if (var1x.method17()) {
                              var11.add(BodyPart.LEFT_ARM);
                           }

                           if (var1x.method18()) {
                              var11.add(BodyPart.RIGHT_LEG);
                           }

                           if (var1x.method19()) {
                              var11.add(BodyPart.LEFT_LEG);
                           }
                        });
                     } catch (Exception var10) {
                        Hint var9 = new Hint();
                        var9.set("geckolib_cosmetic", var7.method3().toString());
                        Inventorymod2.method6(var10, "GeckolibCosmeticManager", true, var9);
                     }
                  }
               }
            }
         }
      });
   }

   public void method1() {
      this.field5.clear();
      Bridge11_2 var1 = ThreadModuleDump63.method3().bridge$getResourceManager();
      MixinHelper27 var2 = new MixinHelper27(new MixinHelper542());
      IResourceBridge var3 = var1.bridge$getResource(field1.get());
      if (var3 == null) {
         Slayer.method5("Couldn't find file: " + field1.get());
      } else {
         try {
            String var4 = IOUtils.toString(var3.bridge$getInputStream());
            if (!var4.isBlank()) {
               Map var19 = var2.method178(var4, new MixinHelper_20<Map<String, String>>() {});

               for (Entry var7 : var19.entrySet()) {
                  try {
                     if (Molang.parseExpression((String)var7.getKey()) instanceof Call var22) {
                        FunctionDefinition var10 = new FunctionDefinition(var22.target(), var22.member());
                        ArrayList var11 = new ArrayList();

                        for (Expr var13 : var22.arguments()) {
                           if (var13 instanceof Access var14) {
                              var11.add(var14);
                           } else {
                              Slayer.method7(var13 + " is not a variable!");
                           }
                        }

                        Evaluatable var23 = AnimationKeyframeParserLegacy.method10((String)var7.getValue());
                        FunctionImpl var24 = new FunctionImpl(var22.member(), var23, var11);
                        this.field5.put(var10, var24);
                     }
                  } catch (Exception var16) {
                     InactiveException var9 = new InactiveException("Could not parse molang function: " + (String)var7.getKey(), var16);
                     Inventorymod2.method5(var9, "GeckolibCosmeticManager");
                  }
               }
            }
         } catch (Exception var17) {
            InactiveException var5 = new InactiveException("Could not load functions.molang", var17);
            Inventorymod2.method5(var5, "GeckolibCosmeticManager");
         }
      }

      IResourceBridge var18 = var1.bridge$getResource(field2.get());
      if (var18 == null) {
         Slayer.method5("Couldn't find file: " + field2.get());
      } else {
         try {
            String var20 = IOUtils.toString(var18.bridge$getInputStream());
            if (!var20.isBlank()) {
               this.field9 = AnimationKeyframeParserLegacy.method10(var20);
            }
         } catch (Exception var15) {
            InactiveException var21 = new InactiveException("Could not load constants.molang", var15);
            Inventorymod2.method5(var21, "GeckolibCosmeticManager");
         }
      }

      this.field8 = true;
      Slayer.method3("Reloaded molang functions.");
   }

   public static void method2(MolangScope var0) {
      String var1 = method5();
      if (var1 != null) {
         method6(var0, var1);
      }
   }

   public static MolangStmtCompiler method3(MolangScope var0) {
      List var1 = Molang.parse(method4());
      return MolangStmtCompiler.method1(var1, var0);
   }

   private static String method4() {
      if (field4 != null) {
         return field4;
      }

      Bridge11_2 var0 = ThreadModuleDump63.method3().bridge$getResourceManager();
      IResourceBridge var1 = var0.bridge$getResource(field2.get());
      if (var1 == null) {
         Slayer.method5("Couldn't find file: " + field2.get());
      } else {
         try {
            field4 = IOUtils.toString(var1.bridge$getInputStream());
         } catch (IOException var4) {
            InactiveException var3 = new InactiveException("Could not load constants.molang", var4);
            Inventorymod2.method5(var3, "GeckolibCosmeticManager");
         }
      }

      return field4;
   }

   private static String method5() {
      if (field3 != null) {
         return field3;
      }

      Bridge11_2 var0 = ThreadModuleDump63.method3().bridge$getResourceManager();
      IResourceBridge var1 = var0.bridge$getResource(field1.get());
      if (var1 == null) {
         Slayer.method5("Couldn't find file: " + field1.get());
      } else {
         try {
            field3 = IOUtils.toString(var1.bridge$getInputStream());
         } catch (IOException var4) {
            InactiveException var3 = new InactiveException("Could not load functions.molang", var4);
            Inventorymod2.method5(var3, "GeckolibCosmeticManager");
         }
      }

      return field3;
   }

   public static void method6(MolangScope var0, String var1) {
      MixinHelper27 var2 = new MixinHelper27(new MixinHelper542());
      Map var3 = var2.method178(var1, new MixinHelper_20<Map<String, String>>() {});
      List var4 = method7(var3);

      for (MolangFunctionRegistry.Data7 var6 : var4) {
         var0.method2(var6.id(), var6.method1());
      }

      for (MolangFunctionRegistry.Data7 var10 : var4) {
         for (Pair var8 : var10.arguments()) {
            var0.method2((String)var8.first(), (MolangSymbol)var8.second());
         }

         MolangStmtCompiler var11 = MolangStmtCompiler.method1(var10.method2(), var0);
         var10.method1().method4(var11);
      }
   }

   private static List<MolangFunctionRegistry.Data7> method7(Map<String, String> var0) {
      ArrayList var1 = new ArrayList();

      for (Entry var3 : var0.entrySet()) {
         if (!(Molang.parseExpression((String)var3.getKey()) instanceof Call var5)) {
            throw new IllegalStateException("Could not parse molang function: " + (String)var3.getKey());
         }

         FunctionDefinition var6 = new FunctionDefinition(var5.target(), var5.member());
         List var7 = method8(var5.arguments());
         List var8;
         if (!((String)var3.getValue()).contains("\n") && !((String)var3.getValue()).contains(";")) {
            var8 = List.of(new Expression(Molang.parseExpression((String)var3.getValue())));
         } else {
            var8 = Molang.parse((String)var3.getValue());
         }

         var1.add(
            new MolangFunctionRegistry.Data7(MolangVariablePath.method3(var6), new MolangCustomFunction(var7.size(), var7.stream().<MolangVariable>map(Pair::second).toList(), null), var8, var7)
         );
      }

      return var1;
   }

   private static List<Pair<String, MolangVariable>> method8(List<Expr> var0) {
      ArrayList var1 = new ArrayList();

      for (Expr var3 : var0) {
         if (!(var3 instanceof Access var4)) {
            throw new RuntimeException(var3 + " is not a variable!");
         }

         var1.add(Pair.of(MolangVariablePath.method1(var4), new MolangVariable()));
      }

      return var1;
   }

   public void clearCache() {
      if (this.field11 != null) {
         this.field11.clear();
      }

      if (this.field12 != null) {
         this.field12.clear();
      }
   }

   @Override
   public void close() {
   }

   public Optional<Gui2Handler> method9(com.moonsworth.lunar.client.cosmetics.OwnedCosmetic var1) {
      ResourceLocationBridge var2 = var1.method3();
      if (JitPaths.method1(var2)) {
         JitAssetIndex var3 = ThreadModuleDump63.method4().method96();
         com.moonsworth.lunar.client.render.jit.JitResource var4 = var3.method2(var2, JitImageResource::new);
         return var4.method1();
      }

      if (this.field11 == null) {
         this.field11 = new HashMap<>();
      }

      if (this.field12 == null) {
         this.field12 = new HashMap<>();
      }

      return Optional.ofNullable(this.field11.computeIfAbsent(var2, var3x -> {
         Bridge11_2 var4x = ThreadModuleDump63.method3().bridge$getResourceManager();
         IResourceBridge var5 = var4x.bridge$getResource(var2);
         if (var5 == null) {
            this.method15(var1.method9(), new RuntimeException("Unable to find resource file: " + var2));
            return null;
         }

         try {
            String var6 = IOUtils.toString(var5.bridge$getInputStream(), StandardCharsets.UTF_8);
            Gui2Handler var7 = Inactive3_2.method1(var6);
            var7.method20(var2);
            return var7;
         } catch (IOException var8) {
            var8.printStackTrace();
            return null;
         }
      }));
   }

   public Optional<com.moonsworth.lunar.client.cosmetics.gecko.AnimationCache> method10(ResourceLocationBridge var1) {
      if (JitPaths.method1(var1)) {
         JitAssetIndex var2 = ThreadModuleDump63.method4().method96();
         com.moonsworth.lunar.client.render.jit.JitResource var3 = var2.method2(var1, var1x -> new JitEntityResource(this.field7, var1x));
         return var3.method1();
      } else {
         return this.method13(var1, () -> {
            Bridge11_2 var2x = ThreadModuleDump63.method3().bridge$getResourceManager();

            try {
               return this.field7.method1(var1, var2x);
            } catch (IOException var4) {
               var4.printStackTrace();
               return null;
            }
         });
      }
   }

   public Optional<BoneList> method11(ResourceLocationBridge var1) {
      if (JitPaths.method1(var1)) {
         JitAssetIndex var2 = ThreadModuleDump63.method4().method96();
         com.moonsworth.lunar.client.render.jit.JitResource var3 = var2.method2(var1, JitItemResource::new);
         return var3.method1();
      } else {
         return this.method13(var1, () -> {
            Bridge11_2 var1x = ThreadModuleDump63.method3().bridge$getResourceManager();

            try {
               return (BoneList)ModelGeometryLoader.method1(var1x, var1).value();
            } catch (RuntimeException var3x) {
               var3x.printStackTrace();
               return null;
            }
         });
      }
   }

   public Optional<ItemTransformsBridge> method12(ResourceLocationBridge var1) {
      if (JitPaths.method1(var1)) {
         JitAssetIndex var2 = ThreadModuleDump63.method4().method96();
         com.moonsworth.lunar.client.render.jit.JitResource var3 = var2.method2(var1, JitItemModelResource::new);
         return var3.method1();
      } else {
         return this.method13(var1, () -> {
            Bridge11_2 var1x = ThreadModuleDump63.method3().bridge$getResourceManager();
            String var2x = AnimationFileLoader.method3(var1, var1x);
            return (ItemTransformsBridge)ItemTransformsBridge.field1.fromJson(var2x, ItemTransformsBridge.class);
         });
      }
   }

   private <T> Optional<T> method13(ResourceLocationBridge var1, Supplier<T> var2) {
      if (this.field12 == null) {
         return Optional.empty();
      } else if (!this.field12.containsKey(var1)) {
         Object var4 = var2.get();
         this.field12.put(var1, var4);
         return Optional.of((T)var4);
      } else {
         Object var3 = this.field12.get(var1);
         return var3 == null ? Optional.empty() : Optional.of((T)var3);
      }
   }

   public boolean method14(@NotNull EmoteModel var1) {
      Optional var2 = this.method9(var1);
      if (var2.isEmpty()) {
         return false;
      }

      boolean var3 = true;
      Gui2Handler var4 = (Gui2Handler)var2.get();
      if (var4.method5() != null) {
         JitAssetIndex var5 = ThreadModuleDump63.method4().method96();

         for (Pair var8 : var4.method5().method2()) {
            ResourceLocationBridge var9 = (ResourceLocationBridge)var8.first();
            if (JitPaths.method1(var9)) {
               JitAnimatedResource var10 = var5.method2(var9, var0 -> new JitAnimatedResource(var0, false));
               if (!var10.HRCHCCCICOCHCHCRIRORCCCHHORIOO()) {
                  var3 = false;
               }
            } else {
               Bridge8Handler2 var27 = ThreadModuleDump63.method3().bridge$getTextureManager();
               if (!var27.bridge$getTextureMap().containsKey(var9)) {
                  var3 = false;
                  var27.method3(var9, new Util2Handler(var9, BridgeType2_5.FULL));
               }
            }
         }
      }

      if (var4.method3() != null) {
         for (Pair var20 : var4.method3().method2()) {
            ResourceLocationBridge var23 = (ResourceLocationBridge)var20.first();

            try {
               if (this.method10(var23).isEmpty()) {
                  var3 = false;
               }
            } catch (Exception var13) {
               var3 = false;
               this.method15(var1.HHRHHCIOIOIHOHRCCHRHOOHRHRIHRO(), var13);
            }
         }
      }

      for (Pair var21 : var4.method4().method2()) {
         ResourceLocationBridge var24 = (ResourceLocationBridge)var21.first();

         try {
            if (this.method11(var24).isEmpty()) {
               var3 = false;
            }
         } catch (Exception var12) {
            var3 = false;
            this.method15(var1.HHRHHCIOIOIHOHRCCHRHOOHRHRIHRO(), var12);
         }
      }

      if (var4 instanceof Gui2Impl2 var19 && var19.method23() != null) {
         for (Pair var25 : var19.method23().method2()) {
            ResourceLocationBridge var26 = (ResourceLocationBridge)var25.first();

            try {
               if (this.method12(var26).isEmpty()) {
                  var3 = false;
               }
            } catch (Exception var11) {
               var3 = false;
               this.method15(var1.HHRHHCIOIOIHOHRCCHRHOOHRHRIHRO(), var11);
            }
         }
      }

      return var3;
   }

   private void method15(long var1, Exception var3) {
      if (ThreadModuleDump63.method4().method31() != null && ThreadModuleDump63.method4().method31().method29()) {
         com.moonsworth.lunar.client.cosmetics.OwnedCosmetic var4 = ThreadModuleDump63.method4().method53().method58().get(var1);
         if (var4 != null) {
            String var5 = var3.getMessage();
            var4.method8(var5 == null ? var3.toString() : var5, true);
         }
      }
   }

   @Generated
   public Map<FunctionDefinition, FunctionImpl> method16() {
      return this.field5;
   }

   @Generated
   public static Bridge5_11 method17() {
      return field6;
   }

   @Generated
   public static void method18(Bridge5_11 var0) {
      field6 = var0;
   }

   @Generated
   public Evaluatable method19() {
      return this.field9;
   }

   private class Data7 {
      private final String field1;
      private final MolangCustomFunction field2;
      private final List<Stmt> field3;
      private final List<Pair<String, MolangVariable>> field4;

      private Data7(String var1, MolangCustomFunction var2, List<Stmt> var3, List<Pair<String, MolangVariable>> var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
      }

      public String id() {
         return this.field1;
      }

      public MolangCustomFunction method1() {
         return this.field2;
      }

      public List<Stmt> method2() {
         return this.field3;
      }

      public List<Pair<String, MolangVariable>> arguments() {
         return this.field4;
      }
   }
}
