package com.moonsworth.lunar.client.cosmetics.inactive.mixin;

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
import com.moonsworth.lunar.bridge.ResourceBridge;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.TextureManagerBridge;
import com.moonsworth.lunar.bridge.ModelPlayerBridge;
import com.moonsworth.lunar.bridge.TextureQuality;
import com.moonsworth.lunar.bridge.horsestats.Horsestats14;
import com.moonsworth.lunar.bridge.horsestats.Horsestats6;
import com.moonsworth.lunar.bridge.horsestats.Horsestats8;
import com.moonsworth.lunar.bridge.horsestats.Horsestats8.Data;
import com.moonsworth.lunar.client.Client;
import com.moonsworth.lunar.client.LunarLogger;
import com.moonsworth.lunar.client.framework.LoadableHandler;
import com.moonsworth.lunar.client.fog.holograms.nameplate.FogHandler;
import com.moonsworth.lunar.client.fog.holograms.nameplate.Nameplate2;
import com.moonsworth.lunar.client.fog.holograms.nameplate.NameplateBase2;
import com.moonsworth.lunar.client.fog.holograms.nameplate.NameplateBase3;
import com.moonsworth.lunar.client.fog.holograms.nameplate.NameplateBase4;
import com.moonsworth.lunar.client.fog.holograms.nameplate.NameplateBase_2;
import com.moonsworth.lunar.client.fog.holograms.nameplate.NameplateUpdater;
import com.moonsworth.lunar.client.fov.Gui2Iterator;
import com.moonsworth.lunar.client.fov.mixin.Gui2Handler3;
import com.moonsworth.lunar.client.cosmetics.molang.MolangVariablePath;
import com.moonsworth.lunar.client.cosmetics.molang.MolangScope;
import com.moonsworth.lunar.client.fps.Fps6;
import com.moonsworth.lunar.client.cosmetics.molang.MolangSymbol;
import com.moonsworth.lunar.client.cosmetics.molang.MolangCustomFunction;
import com.moonsworth.lunar.client.cosmetics.molang.MolangVariable;
import com.moonsworth.lunar.client.cosmetics.molang.ast.ConstantEvaluatable;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderModel.EventRenderBipedModel;
import com.moonsworth.lunar.client.cosmetics.inactive.CosmeticDefinitionMapper;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.colorsaturation.mixin.AnimationKeyframeParser;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.highlight.mixin.FunctionImpl;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.nameplate.AnimationFileLoader;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.nameplate.ModelGeometryLoader;
import com.moonsworth.lunar.client.cosmetics.inactive.rewindhandlers.BedrockGeometry;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.util.SupplierExtension;
import com.moonsworth.lunar.client.util.Ref;
import com.moonsworth.lunar.client.util.Util2Handler;
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

public class GeckolibCosmeticManager implements LoadableHandler {
   private static final SupplierExtension<Horsestats14> field1 = SupplierExtension.method1(() -> Horsestats14.create("lunar", "cosmetics/functions.molang"));
   private static final SupplierExtension<Horsestats14> field2 = SupplierExtension.method1(() -> Horsestats14.create("lunar", "cosmetics/constants.molang"));
   private static String field3 = null;
   private static String field4 = null;
   private final Map<FunctionDefinition, FunctionImpl> field5 = new HashMap<>();
   private static Bridge5_11 field6;
   private final AnimationFileLoader field7;
   public boolean field8;
   private Evaluatable field9 = new ConstantEvaluatable(0.0);
   public final Gson field10 = new GsonBuilder()
      .registerTypeAdapter(Horsestats8.class, new Data())
      .registerTypeAdapter(Horsestats6.class, new com.moonsworth.lunar.bridge.horsestats.Horsestats6.Data())
      .create();
   @Nullable
   private Map<Horsestats14, Gui2Handler> field11;
   @Nullable
   private Map<Horsestats14, Object> field12;

   public GeckolibCosmeticManager() {
      this.field7 = new AnimationFileLoader();
   }

   public void init() {
      Client.field2.submit(this::method1);
      LunarEventBus.method29().method2(EventRenderBipedModel.class, arg0 -> {
         if (arg0.IRCCIOOCORRHIROCHOCORHROCOHIHH() instanceof ModelPlayerBridge bridgeextension2_71 && (Ref.MC_VERSION > 0 || bridgeextension2_71.bridge$isMainModel())) {
            Set set11 = arg0.method4();
            List list3 = Ref.method4().method53().method19(arg0.CCOOOHHHROORHRHORHCOIIHOHCRCRC().bridge$getUniqueID());
            if (list3 != null) {
               for (Gui2Handler3 gui2handler35 : list3) {
                  com.moonsworth.lunar.client.fov.mixin.Gui2Handler gui2handler6 = gui2handler35.method4();
                  if (gui2handler6 instanceof Gui2Iterator gui2iterator7 && gui2handler6.method10().canShowCosmetic()) {
                     try {
                        gui2iterator7.method6().ifPresent(arg1x -> {
                           if (arg1x.method14()) {
                              set11.add(BodyPart.HEAD);
                           }

                           if (arg1x.method15()) {
                              set11.add(BodyPart.TORSO);
                           }

                           if (arg1x.method16()) {
                              set11.add(BodyPart.RIGHT_ARM);
                           }

                           if (arg1x.method17()) {
                              set11.add(BodyPart.LEFT_ARM);
                           }

                           if (arg1x.method18()) {
                              set11.add(BodyPart.RIGHT_LEG);
                           }

                           if (arg1x.method19()) {
                              set11.add(BodyPart.LEFT_LEG);
                           }
                        });
                     } catch (Exception exception10) {
                        Hint hint9 = new Hint();
                        hint9.set("geckolib_cosmetic", gui2iterator7.RIOHIIHICOOCHRRRIRHOIOHHHRCIIO().toString());
                        CrashReporter.method6(exception10, "GeckolibCosmeticManager", true, hint9);
                     }
                  }
               }
            }
         }
      });
   }

   public void method1() {
      this.field5.clear();
      Bridge11_2 bridge11_21 = Ref.method3().bridge$getResourceManager();
      MixinHelper27 mixinhelper272 = new MixinHelper27(new MixinHelper542());
      ResourceBridge bridge153 = bridge11_21.bridge$getResource((Horsestats14)field1.get());
      if (bridge153 == null) {
         LunarLogger.method5("Couldn't find file: " + field1.get(), new Object[0]);
      } else {
         try {
            String text4 = IOUtils.toString(bridge153.bridge$getInputStream());
            if (!text4.isBlank()) {
               Map map19 = (Map)mixinhelper272.method178(text4, new MixinHelper_20<Map<String, String>>() {});

               for (Entry entry7 : map19.entrySet()) {
                  try {
                     if (Molang.parseExpression((String)entry7.getKey()) instanceof Call call22) {
                        FunctionDefinition functiondefinition10 = new FunctionDefinition(call22.target(), call22.member());
                        ArrayList list11 = new ArrayList();

                        for (Expr expr13 : call22.arguments()) {
                           if (expr13 instanceof Access access14) {
                              list11.add(access14);
                           } else {
                              LunarLogger.method7(expr13 + " is not a variable!", new Object[0]);
                           }
                        }

                        Evaluatable evaluatable23 = AnimationKeyframeParser.method10((String)entry7.getValue());
                        FunctionImpl functionimpl24 = new FunctionImpl(call22.member(), evaluatable23, list11);
                        this.field5.put(functiondefinition10, functionimpl24);
                     }
                  } catch (Exception exception16) {
                     InactiveException inactiveexception9 = new InactiveException("Could not parse molang function: " + (String)entry7.getKey(), exception16);
                     CrashReporter.method5(inactiveexception9, "GeckolibCosmeticManager");
                  }
               }
            }
         } catch (Exception exception17) {
            InactiveException inactiveexception5 = new InactiveException("Could not load functions.molang", exception17);
            CrashReporter.method5(inactiveexception5, "GeckolibCosmeticManager");
         }
      }

      ResourceBridge bridge1518 = bridge11_21.bridge$getResource((Horsestats14)field2.get());
      if (bridge1518 == null) {
         LunarLogger.method5("Couldn't find file: " + field2.get(), new Object[0]);
      } else {
         try {
            String text20 = IOUtils.toString(bridge1518.bridge$getInputStream());
            if (!text20.isBlank()) {
               this.field9 = AnimationKeyframeParser.method10(text20);
            }
         } catch (Exception exception15) {
            InactiveException inactiveexception21 = new InactiveException("Could not load constants.molang", exception15);
            CrashReporter.method5(inactiveexception21, "GeckolibCosmeticManager");
         }
      }

      this.field8 = true;
      LunarLogger.method3("Reloaded molang functions.", new Object[0]);
   }

   public static void method2(MolangScope fps110) {
      String text1 = method5();
      if (text1 != null) {
         method6(fps110, text1);
      }
   }

   public static Fps6 method3(MolangScope fps110) {
      List list1 = Molang.parse(method4());
      return Fps6.method1(list1, fps110);
   }

   private static String method4() {
      if (field4 != null) {
         return field4;
      }

      Bridge11_2 bridge11_20 = Ref.method3().bridge$getResourceManager();
      ResourceBridge bridge151 = bridge11_20.bridge$getResource((Horsestats14)field2.get());
      if (bridge151 == null) {
         LunarLogger.method5("Couldn't find file: " + field2.get(), new Object[0]);
      } else {
         try {
            field4 = IOUtils.toString(bridge151.bridge$getInputStream());
         } catch (IOException exception4) {
            InactiveException inactiveexception3 = new InactiveException("Could not load constants.molang", exception4);
            CrashReporter.method5(inactiveexception3, "GeckolibCosmeticManager");
         }
      }

      return field4;
   }

   private static String method5() {
      if (field3 != null) {
         return field3;
      }

      Bridge11_2 bridge11_20 = Ref.method3().bridge$getResourceManager();
      ResourceBridge bridge151 = bridge11_20.bridge$getResource((Horsestats14)field1.get());
      if (bridge151 == null) {
         LunarLogger.method5("Couldn't find file: " + field1.get(), new Object[0]);
      } else {
         try {
            field3 = IOUtils.toString(bridge151.bridge$getInputStream());
         } catch (IOException exception4) {
            InactiveException inactiveexception3 = new InactiveException("Could not load functions.molang", exception4);
            CrashReporter.method5(inactiveexception3, "GeckolibCosmeticManager");
         }
      }

      return field3;
   }

   public static void method6(MolangScope fps110, String text1) {
      MixinHelper27 mixinhelper272 = new MixinHelper27(new MixinHelper542());
      Map map3 = (Map)mixinhelper272.method178(text1, new MixinHelper_20<Map<String, String>>() {});
      List list4 = method7(map3);

      for (GeckolibCosmeticManager.MolangFunction data76 : list4) {
         fps110.method2(data76.id(), data76.method1());
      }

      for (GeckolibCosmeticManager.MolangFunction data710 : list4) {
         for (Pair pair8 : data710.arguments()) {
            fps110.method2((String)pair8.first(), (MolangSymbol)pair8.second());
         }

         Fps6 fps611 = Fps6.method1(data710.method2(), fps110);
         data710.method1().method4(fps611);
      }
   }

   private static List<GeckolibCosmeticManager.MolangFunction> method7(Map<String, String> map0) {
      ArrayList list1 = new ArrayList();

      for (Entry entry3 : map0.entrySet()) {
         if (!(Molang.parseExpression((String)entry3.getKey()) instanceof Call call5)) {
            throw new IllegalStateException("Could not parse molang function: " + (String)entry3.getKey());
         }

         FunctionDefinition functiondefinition6 = new FunctionDefinition(call5.target(), call5.member());
         List list7 = method8(call5.arguments());
         List list8;
         if (!((String)entry3.getValue()).contains("\n") && !((String)entry3.getValue()).contains(";")) {
            list8 = List.of(new Expression(Molang.parseExpression((String)entry3.getValue())));
         } else {
            list8 = Molang.parse((String)entry3.getValue());
         }

         list1.add(new GeckolibCosmeticManager.MolangFunction(MolangVariablePath.method3(functiondefinition6), new MolangCustomFunction(list7.size(), list7.stream().map(Pair::second).toList(), null), list8, list7));
      }

      return list1;
   }

   private static List<Pair<String, MolangVariable>> method8(List<Expr> list0) {
      ArrayList list1 = new ArrayList();

      for (Expr expr3 : list0) {
         if (!(expr3 instanceof Access access4)) {
            throw new RuntimeException(expr3 + " is not a variable!");
         }

         list1.add(Pair.of(MolangVariablePath.method1(access4), new MolangVariable()));
      }

      return list1;
   }

   public void clearCache() {
      if (this.field11 != null) {
         this.field11.clear();
      }

      if (this.field12 != null) {
         this.field12.clear();
      }
   }

   public void close() {
   }

   public Optional<Gui2Handler> method9(com.moonsworth.lunar.client.fov.mixin.Gui2Handler gui2handler1) {
      Horsestats14 horsestats142 = gui2handler1.method3();
      if (Nameplate2.method1(horsestats142)) {
         FogHandler foghandler3 = Ref.method4().method96();
         com.moonsworth.lunar.client.fog.holograms.nameplate.Nameplate nameplate4 = foghandler3.method2(horsestats142, NameplateBase_2::new);
         return nameplate4.method1();
      }

      if (this.field11 == null) {
         this.field11 = new HashMap<>();
      }

      if (this.field12 == null) {
         this.field12 = new HashMap<>();
      }

      return Optional.ofNullable(this.field11.computeIfAbsent(horsestats142, arg3x -> {
         Bridge11_2 bridge11_24x = Ref.method3().bridge$getResourceManager();
         ResourceBridge bridge155 = bridge11_24x.bridge$getResource(horsestats142);
         if (bridge155 == null) {
            this.method15(gui2handler1.method9(), new RuntimeException("Unable to find resource file: " + horsestats142));
            return null;
         }

         try {
            String text6 = IOUtils.toString(bridge155.bridge$getInputStream(), StandardCharsets.UTF_8);
            Gui2Handler gui2handler7 = CosmeticDefinitionMapper.method1(text6);
            gui2handler7.method20(horsestats142);
            return gui2handler7;
         } catch (IOException exception8) {
            exception8.printStackTrace();
            return null;
         }
      }));
   }

   public Optional<com.moonsworth.lunar.client.cosmetics.inactive.mixin.nameplate.AnimationCache> method10(Horsestats14 horsestats141) {
      if (Nameplate2.method1(horsestats141)) {
         FogHandler foghandler2 = Ref.method4().method96();
         com.moonsworth.lunar.client.fog.holograms.nameplate.Nameplate nameplate3 = foghandler2.method2(horsestats141, arg1x -> new NameplateBase2(this.field7, arg1x));
         return nameplate3.method1();
      } else {
         return this.method13(horsestats141, () -> {
            Bridge11_2 bridge11_22x = Ref.method3().bridge$getResourceManager();

            try {
               return this.field7.method1(horsestats141, bridge11_22x);
            } catch (IOException exception4) {
               exception4.printStackTrace();
               return null;
            }
         });
      }
   }

   public Optional<BedrockGeometry> method11(Horsestats14 horsestats141) {
      if (Nameplate2.method1(horsestats141)) {
         FogHandler foghandler2 = Ref.method4().method96();
         com.moonsworth.lunar.client.fog.holograms.nameplate.Nameplate nameplate3 = foghandler2.method2(horsestats141, NameplateBase3::new);
         return nameplate3.method1();
      } else {
         return this.method13(horsestats141, () -> {
            Bridge11_2 bridge11_21x = Ref.method3().bridge$getResourceManager();

            try {
               return (BedrockGeometry)ModelGeometryLoader.method1(bridge11_21x, horsestats141).value();
            } catch (RuntimeException exception3x) {
               exception3x.printStackTrace();
               return null;
            }
         });
      }
   }

   public Optional<Horsestats6> method12(Horsestats14 horsestats141) {
      if (Nameplate2.method1(horsestats141)) {
         FogHandler foghandler2 = Ref.method4().method96();
         com.moonsworth.lunar.client.fog.holograms.nameplate.Nameplate nameplate3 = foghandler2.method2(horsestats141, NameplateBase4::new);
         return nameplate3.method1();
      } else {
         return this.method13(horsestats141, () -> {
            Bridge11_2 bridge11_21x = Ref.method3().bridge$getResourceManager();
            String text2x = AnimationFileLoader.method3(horsestats141, bridge11_21x);
            return (Horsestats6)Horsestats6.field1.fromJson(text2x, Horsestats6.class);
         });
      }
   }

   private <T> Optional<T> method13(Horsestats14 horsestats141, Supplier<T> supplier2) {
      if (this.field12 == null) {
         return Optional.empty();
      } else if (!this.field12.containsKey(horsestats141)) {
         Object obj4 = supplier2.get();
         this.field12.put(horsestats141, obj4);
         return Optional.of((T)obj4);
      } else {
         Object obj3 = this.field12.get(horsestats141);
         return obj3 == null ? Optional.empty() : Optional.of((T)obj3);
      }
   }

   public boolean method14(@NotNull Gui2Iterator gui2iterator1) {
      Optional optional2 = this.method9(gui2iterator1);
      if (optional2.isEmpty()) {
         return false;
      }

      boolean flag3 = true;
      Gui2Handler gui2handler4 = (Gui2Handler)optional2.get();
      if (gui2handler4.method5() != null) {
         FogHandler foghandler5 = Ref.method4().method96();

         for (Pair pair8 : gui2handler4.method5().method2()) {
            Horsestats14 horsestats149 = (Horsestats14)pair8.first();
            if (Nameplate2.method1(horsestats149)) {
               NameplateUpdater nameplateupdater10 = (NameplateUpdater)foghandler5.method2(horsestats149, arg0 -> new NameplateUpdater(arg0, false));
               if (!nameplateupdater10.method3()) {
                  flag3 = false;
               }
            } else {
               TextureManagerBridge bridge8handler227 = Ref.method3().bridge$getTextureManager();
               if (!bridge8handler227.bridge$getTextureMap().containsKey(horsestats149)) {
                  flag3 = false;
                  bridge8handler227.method3(horsestats149, new Util2Handler(horsestats149, TextureQuality.FULL));
               }
            }
         }
      }

      if (gui2handler4.method3() != null) {
         for (Pair pair20 : gui2handler4.method3().method2()) {
            Horsestats14 horsestats1423 = (Horsestats14)pair20.first();

            try {
               if (this.method10(horsestats1423).isEmpty()) {
                  flag3 = false;
               }
            } catch (Exception exception13) {
               flag3 = false;
               this.method15(gui2iterator1.method9(), exception13);
            }
         }
      }

      for (Pair pair21 : gui2handler4.method4().method2()) {
         Horsestats14 horsestats1424 = (Horsestats14)pair21.first();

         try {
            if (this.method11(horsestats1424).isEmpty()) {
               flag3 = false;
            }
         } catch (Exception exception12) {
            flag3 = false;
            this.method15(gui2iterator1.method9(), exception12);
         }
      }

      if (gui2handler4 instanceof ItemCosmeticDefinition gui2impl219 && gui2impl219.method23() != null) {
         for (Pair pair25 : gui2impl219.method23().method2()) {
            Horsestats14 horsestats1426 = (Horsestats14)pair25.first();

            try {
               if (this.method12(horsestats1426).isEmpty()) {
                  flag3 = false;
               }
            } catch (Exception exception11) {
               flag3 = false;
               this.method15(gui2iterator1.method9(), exception11);
            }
         }
      }

      return flag3;
   }

   private void method15(long index1, Exception exception3) {
      if (Ref.method4().method31() != null && Ref.method4().method31().method29()) {
         com.moonsworth.lunar.client.fov.mixin.Gui2Handler gui2handler4 = (com.moonsworth.lunar.client.fov.mixin.Gui2Handler)Ref.method4()
            .method53()
            .method58()
            .get(index1);
         if (gui2handler4 != null) {
            String text5 = exception3.getMessage();
            gui2handler4.method8(text5 == null ? exception3.toString() : text5, true);
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
   public static void method18(Bridge5_11 bridge5_110) {
      field6 = bridge5_110;
   }

   @Generated
   public Evaluatable method19() {
      return this.field9;
   }

   private class MolangFunction {
      private final String field1;
      private final MolangCustomFunction field2;
      private final List<Stmt> field3;
      private final List<Pair<String, MolangVariable>> field4;

      private MolangFunction(String text1, MolangCustomFunction fps7extension172, List<Stmt> list3, List<Pair<String, MolangVariable>> list4) {
         this.field1 = text1;
         this.field2 = fps7extension172;
         this.field3 = list3;
         this.field4 = list4;
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
