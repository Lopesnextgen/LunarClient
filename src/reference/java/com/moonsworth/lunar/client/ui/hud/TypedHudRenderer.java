package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5_19;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.Annotation6;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump65;
import it.unimi.dsi.fastutil.booleans.Boolean2ObjectFunction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class TypedHudRenderer<T> extends HudElementBase {
   protected static final float field9 = 8.0F;
   private static final float field10 = 16.0F;
   private static final Map<Class<?>, TypedHudRenderer.Extension<?>> field11 = ThreadModuleDump65.make(
      new HashMap<>(),
      var0 -> {
         var0.put(
            String.class,
            new TypedHudRenderer.Extension<String>() {
               public String method1(String var1, ColorOption var2) {
                  return "[" + var1 + "]";
               }

               public float method2(TypedHudRenderer<String> var1, String var2) {
                  return ThreadModuleDump63.method10().bridge$getStringWidth(var2);
               }

               public float method3(TypedHudRenderer<String> var1, String var2) {
                  return ThreadModuleDump63.method10().method19();
               }

               public String method4(String var1) {
                  return var1;
               }

               public void method5(
                  MixinHelper_4 var1,
                  TypedHudRenderer<String> var2,
                  String var3,
                  float var4,
                  float var5,
                  ColorOption var6,
                  @Nullable ColorOption var7,
                  boolean var8,
                  boolean var9
               ) {
                  var6.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, var3, var4, var5, var8, var7);
               }

               public boolean isValid(@NotNull String var1) {
                  return !var1.isEmpty();
               }
            }
         );
         var0.put(
            Component.class,
            new TypedHudRenderer.Extension<Component>() {
               public Component method1(Component var1, ColorOption var2) {
                  return ((TextComponent)((TextComponent)Component.text("[").color(TextColor.color(ThreadModuleDump23.method14(var2.method14(0.0F), 4))))
                        .append(var1))
                     .append(Component.text("]").color(TextColor.color(ThreadModuleDump23.method14(var2.method14(0.0F), 4))));
               }

               public float method2(TypedHudRenderer<Component> var1, Component var2) {
                  return AdventureTextBridge.getTextWidth(var2, ThreadModuleDump63.method10());
               }

               public float method3(TypedHudRenderer<Component> var1, Component var2) {
                  return ThreadModuleDump63.method10().method19();
               }

               public String getTextContent(Component var1) {
                  return AdventureTextBridge.getTextContent(var1);
               }

               public void method4(
                  MixinHelper_4 var1,
                  TypedHudRenderer<Component> var2,
                  Component var3,
                  float var4,
                  float var5,
                  ColorOption var6,
                  @Nullable ColorOption var7,
                  boolean var8,
                  boolean var9
               ) {
                  var1.method11(ThreadModuleDump63.method10(), var3, var4, var5, -1, var8);
               }

               @Override
               public boolean method7() {
                  return false;
               }

               public boolean method6(@NotNull Component var1) {
                  return var1 != Component.empty();
               }
            }
         );
         var0.put(
            MixinCore3.class,
            new TypedHudRenderer.Extension<MixinCore3>() {
               public MixinCore3 method1(MixinCore3 var1, ColorOption var2) {
                  return new MixinCore3(
                     var1,
                     ((TextComponent)((TextComponent)Component.text("[").color(TextColor.color(ThreadModuleDump23.method14(var2.method14(0.0F), 4))))
                           .append(var1.getComponent()))
                        .append(Component.text("]").color(TextColor.color(ThreadModuleDump23.method14(var2.method14(0.0F), 4))))
                  );
               }

               public float method2(TypedHudRenderer<MixinCore3> var1, MixinCore3 var2) {
                  return var2.method1(var1.field26 != null && var1.field26.get(), 16.0F);
               }

               public float method3(TypedHudRenderer<MixinCore3> var1, MixinCore3 var2) {
                  return var1.field26 != null && var1.field26.get() ? 16.0F : ThreadModuleDump63.method10().method19();
               }

               public String method4(MixinCore3 var1) {
                  return AdventureTextBridge.getTextContent(var1.getComponent());
               }

               public void method5(
                  MixinHelper_4 var1,
                  TypedHudRenderer<MixinCore3> var2,
                  MixinCore3 var3,
                  float var4,
                  float var5,
                  ColorOption var6,
                  @Nullable ColorOption var7,
                  boolean var8,
                  boolean var9
               ) {
                  boolean var10 = false;
                  if (var2.field26 != null && var2.field26.get()) {
                     var1.push();
                     var1.method39(var4, var5);
                     if (var3.method3() != null) {
                        Bridge5_19 var11 = ThreadModuleDump63.method3().bridge$getRenderItem();
                        float var12 = var11.bridge$getZLevel();
                        var1.method44(var1x -> var1x.method29().method6(var1xx -> {
                           var1xx.IHORHICICIHRCOCRROCHHOROCHCHCR();
                           Bridge.method14().method2();
                           var11.bridge$setZLevel(-200.0F);
                        }));
                        var1.method34(var3.method3(), 0, 0, ThreadModuleDump63.method3());
                        var1.method44(var2x -> var2x.method29().method6(var2xx -> {
                           Bridge.method14().method3();
                           var2xx.ICOHHORICHCROOOCOHIRIHOHORRCHH();
                           var11.bridge$setZLevel(var12);
                        }));
                        var10 = true;
                     } else if (var3.getIcon() != null) {
                        var1.method24(var3.getIcon(), 0, 0, 16, 16, -1);
                        var10 = true;
                     } else if (var3.method4() != null) {
                        var3.method4().accept(var1);
                        var10 = true;
                     }

                     var1.pop();
                  }

                  if (var3.getComponent() != Component.empty()) {
                     if (var10) {
                        var4 += 24.0F;
                        var5 += 8.0F - ThreadModuleDump63.method10().method19() / 2.0F;
                     }

                     var1.method11(ThreadModuleDump63.method10(), var3.getComponent(), var4, var5, -1, var8);
                  }
               }

               @Override
               public boolean method7() {
                  return false;
               }

               public boolean method7(@NotNull MixinCore3 var1) {
                  return var1.shouldRender();
               }
            }
         );
      }
   );
   @Nullable
   protected final EnumOption<HudPlacement> field12;
   protected final ToggleOption field13 = (ToggleOption)this.method1(
         (ToggleOption.ToggleOptionBuilder)OptionFactory.method7("background").OOOIROIIOCOOHICRIRHHHRROHHHHIO(this.method17())
      )
      .method31();
   protected final ToggleOption field14 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("brackets")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(this.method19()))
      .method31();
   protected final ToggleOption field15 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("staticBackgroundWidth")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(this.method20()))
      .method31();
   protected final ToggleOption field16 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("staticBackgroundHeight")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(this.method22()))
      .method31();
   protected final IntegerOption field17 = (IntegerOption)((IntegerOption.Data)((IntegerOption.Data)OptionFactory.method4(
               "backgroundWidth"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(this.method15().getWidth()))
         .method7(this.method15().getMinWidth(), this.method15().getMaxWidth()))
      .method31();
   protected final IntegerOption field18 = (IntegerOption)((IntegerOption.Data)((IntegerOption.Data)OptionFactory.method4(
               "backgroundHeight"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(this.method15().getHeight()))
         .method7(this.method15().method2(), this.method15().method3()))
      .method31();
   protected final ToggleOption field19 = (ToggleOption)OptionFactory.method7("border").method31();
   protected final ToggleOption field20 = (ToggleOption)this.method2(
         (ToggleOption.ToggleOptionBuilder)OptionFactory.method7("textShadow").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true)
      )
      .method31();
   protected final ColorOption field21 = (ColorOption)this.method3(
         (ColorOption.Data)OptionFactory.method8("textColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1)
      )
      .method31();
   protected final ColorOption field22 = (ColorOption)((ColorOption.Data)OptionFactory.method8("bracketColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   protected final ColorOption field23 = (ColorOption)((ColorOption.Data)OptionFactory.method8("backgroundColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1862270976))
      .method31();
   protected final ColorOption field24 = (ColorOption)((ColorOption.Data)OptionFactory.method8("borderColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1627389952))
      .method31();
   protected final FloatOption field25 = (FloatOption)((FloatOption.Data)((FloatOption.Data)OptionFactory.method2(
               "borderThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .method8(0.5F, 3.0F))
      .method31();
   @Nullable
   protected final ToggleOption field26;
   @Nullable
   protected final ToggleOption field27;
   protected final HudConditionSet field28 = this.method5();
   private TypedHudRenderer.Extension<T> field29;
   @Nullable
   protected T field30;

   public TypedHudRenderer(
      @Annotation6(method1 = Annotation6.Type.X) float var1,
      @Annotation6(method1 = Annotation6.Type.Y) float var2,
      @Annotation6(method1 = Annotation6.Type.POSITION) HudAnchor var3
   ) {
      this(var1, var2, var3, false);
   }

   public TypedHudRenderer(
      @Annotation6(method1 = Annotation6.Type.X) float var1,
      @Annotation6(method1 = Annotation6.Type.Y) float var2,
      @Annotation6(method1 = Annotation6.Type.POSITION) HudAnchor var3,
      boolean var4
   ) {
      this(var1, var2, var3, var4, false);
   }

   public TypedHudRenderer(
      @Annotation6(method1 = Annotation6.Type.X) float var1,
      @Annotation6(method1 = Annotation6.Type.Y) float var2,
      @Annotation6(method1 = Annotation6.Type.POSITION) HudAnchor var3,
      boolean var4,
      boolean var5
   ) {
      super(var1, var2, var3);
      this.field12 = var4 ? (EnumOption)OptionFactory.method10("alignment", this.method16()).method31() : null;
      this.field26 = var5
         ? (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showHudIcons").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
            .method31()
         : null;
      this.field27 = var5
         ? (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showText").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
            .method31()
         : null;
      this.field17.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> this.method16(var1x.intValue(), this.field18.get().intValue()));
      this.field18.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> this.method16(this.field17.get().intValue(), var1x.intValue()));
   }

   @Contract("_->param1")
   protected ToggleOption.ToggleOptionBuilder method1(ToggleOption.ToggleOptionBuilder var1) {
      return var1;
   }

   @Contract("_->param1")
   protected ToggleOption.ToggleOptionBuilder method2(ToggleOption.ToggleOptionBuilder var1) {
      return var1;
   }

   @Contract("_->param1")
   protected ColorOption.Data method3(ColorOption.Data var1) {
      return var1;
   }

   @OverridingMethodsMustInvokeSuper
   @Override
   public void method1(RootSettingsAssembler var1) {
      super.method1(var1);
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.GENERAL,
         var1x -> {
            var1x.method9(new ClientOption[]{this.field20});
            var1x.method9(new ClientOption[]{this.field14})
               .method3(() -> this.field13.get() || !this.field28.method3().isConditional());
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.field13,
                  var1xx -> {
                     var1xx.method9(new ClientOption[]{this.field15}).method3(() -> !this.field28.method1(null).isConditional());
                     var1xx.method9(new ClientOption[]{this.field16})
                        .method3(() -> !this.field13.get() || !this.field28.method2().isConditional());
                     var1xx.method9(new ClientOption[]{this.field17})
                        .method3(() -> this.field28.method1(null).isConditionalOrFalse(() -> !this.field15.get()));
                     var1xx.method9(new ClientOption[]{this.field18})
                        .method3(() -> this.field28.method2().isConditionalOrFalse(() -> !this.field16.get()));
                     var1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                        this.field19, var1xxx -> var1xxx.method9(new ClientOption[]{this.field25})
                     );
                  }
               )
               .method3(() -> !this.field28.method4().isConditional());
            if (this.field12 != null) {
               var1x.method9(new ClientOption[]{this.field12});
            }

            if (this.field26 != null) {
               var1x.method9(new ClientOption[]{this.field26});
            }

            if (this.field27 != null) {
               var1x.method9(new ClientOption[]{this.field27}).method3(() -> !this.method23());
            }
         }
      );
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(SettingsPage.COLOR, var1x -> {
         var1x.method9(new ClientOption[]{this.field21}).method3(() -> this.field29 != null && !this.field29.method7());
         var1x.method9(new ClientOption[]{this.field22}).method1(this.field14);
         var1x.method9(new ClientOption[]{this.field23}).method1(this.field13);
         var1x.method9(new ClientOption[]{this.field24}).method1(this.field19);
      });
   }

   public HudConditionSet method5() {
      return HudConditionSet.field1;
   }

   public abstract MixinCore2 method15();

   @Nullable
   public abstract T method7(boolean var1);

   protected com.moonsworth.lunar.client.ui.hud.row.Gui2Extension method16() {
      return com.moonsworth.lunar.client.ui.hud.row.Gui2Extension.CENTER;
   }

   protected boolean method17() {
      return true;
   }

   protected boolean method19() {
      return true;
   }

   protected boolean method20() {
      return true;
   }

   protected boolean method22() {
      return true;
   }

   protected boolean method23() {
      return false;
   }

   protected float method14(boolean var1, T var2, float var3) {
      boolean var4 = var1 && this.field28.method1(this.field29.method4((T)var2)).isConditionalOrTrue(this.field15::get);
      return var4 ? this.field17.get().intValue() : var3 + (var1 ? 8.0F : 0.0F);
   }

   protected float method15(boolean var1, float var2) {
      boolean var3 = var1 && this.field28.method2().isConditionalOrTrue(this.field16::get);
      return var3 ? this.field18.get().intValue() : var2 + (var1 ? 8.0F : 0.0F);
   }

   @Override
   public void method3(HudBaseRenderEvent var1, float var2, float var3, boolean var4) {
      MixinHelper_4 var5 = var1.method2();
      boolean var6 = this.field28.method4().isConditionalOrTrue(this.field13::get);
      boolean var7 = !var6 && this.field28.method3().isConditionalOrTrue(this.field14::get);
      float var8 = 0.0F;
      float var9 = 0.0F;
      Object var10 = null;
      Iterable var11 = null;
      if (this.field30 instanceof Iterable var12) {
         var11 = var12;
         if (var7) {
            ArrayList var22 = new ArrayList();

            for (Object var15 : var11) {
               var22.add(this.field29.method1((T)var15, this.field22));
            }

            var11 = var22;
         }

         for (Object var25 : var11) {
            float var28 = this.field29.method2(this, (T)var25);
            if (var28 > var8) {
               var8 = var28;
               var10 = var25;
            }

            var9 += this.field29.method3(this, (T)var25) + 1.0F;
         }
      } else {
         var10 = this.field30;
         if (var7) {
            var10 = this.field29.method1((T)var10, this.field22);
         }

         var8 = this.field29.method2(this, (T)var10);
         var9 = this.field29.method3(this, this.field30);
      }

      float var21 = this.method14(var6, (T)var10, var8);
      float var24 = this.method15(var6, var9);
      this.method16(var21, var24);
      if (var6) {
         this.field23.method11(var5, var2, var3, var21, var24);
         if (this.field19.get()) {
            this.field24.method11(var5, this, var2, var3, var21, var24, this.field25.get());
         }
      }

      this.method18(var5, var2, var3, var4, false);
      if (var11 != null) {
         int var26 = 0;
         float var29 = var3 + (var24 / 2.0F - (var9 + var26) / 2.0F + 1.0F);
         boolean var16 = this.field20.get();

         for (Object var18 : var11) {
            float var19 = this.field29.method2(this, (T)var18);
            float var20 = this.method19(var2, var21, var19, var6);
            this.method17(var5, this, (T)var18, var20, var29, var7, var16, var6);
            var29 += this.field29.method3(this, (T)var18) + 1.0F;
            var26++;
         }
      } else {
         float var27 = var3 + (var24 / 1.88F - var9 / 2.0F + 0.5F);
         float var30 = this.method19(var2, var21, var8, var6);
         this.method17(var5, this, (T)var10, var30, var27, var7, this.field20.get(), var6);
      }

      this.method18(var5, var2, var3, var4, true);
   }

   protected void method17(MixinHelper_4 var1, TypedHudRenderer<T> var2, T var3, float var4, float var5, boolean var6, boolean var7, boolean var8) {
      this.field29.method5(var1, var2, (T)var3, var4, var5, this.field21, var6 ? this.field22 : null, var7, var8);
   }

   protected void method18(MixinHelper_4 var1, float var2, float var3, boolean var4, boolean var5) {
   }

   protected float method19(float var1, float var2, float var3, boolean var4) {
      return switch (this.field12 != null ? (com.moonsworth.lunar.client.ui.hud.row.Gui2Extension)this.field12.get() : this.method16()) {
         case LEFT -> var1 + (var4 ? 4.0F : 0.0F);
         case CENTER -> var1 + var2 / 2.0F - var3 / 2.0F;
         case RIGHT -> var1 + var2 - (var4 ? 4.0F : 0.0F) - var3;
      };
   }

   @OverridingMethodsMustInvokeSuper
   @Override
   public boolean method4(boolean var1) {
      this.field30 = this.method7(var1);
      if (this.field30 instanceof Iterable var2) {
         Iterator var5 = var2.iterator();
         if (var5.hasNext()) {
            if (this.field29 == null) {
               Object var4 = var5.next();
               this.field29 = method21(var4.getClass());
            }

            return true;
         } else {
            return false;
         }
      } else if (this.field30 != null) {
         if (this.field29 == null) {
            this.field29 = method21(this.field30.getClass());
         }

         return this.field29.method6(this.field30);
      } else {
         return false;
      }
   }

   private static <T> TypedHudRenderer.Extension<T> method21(@NotNull Class<?> var0) {
      for (Entry var2 : field11.entrySet()) {
         if (((Class)var2.getKey()).isAssignableFrom(var0)) {
            return (TypedHudRenderer.Extension<T>)var2.getValue();
         }
      }

      throw new RuntimeException("TypedHudRenderer doesn't know how to render object: " + var0);
   }

   public static <T> TypedHudRenderer<T> method22(
      @Annotation6(method1 = Annotation6.Type.X) float var0,
      @Annotation6(method1 = Annotation6.Type.Y) float var1,
      @Annotation6(method1 = Annotation6.Type.POSITION) HudAnchor var2,
      final MixinCore2 var3,
      final Boolean2ObjectFunction<T> var4
   ) {
      return new TypedHudRenderer<T>(var0, var1, var2) {
         @Override
         public MixinCore2 method15() {
            return var3;
         }

         @Override
         public T method7(boolean var1) {
            return (T)var4.get(var1);
         }
      };
   }

   public static <T> TypedHudRenderer<T> method23(
      @Annotation6(method1 = Annotation6.Type.X) float var0,
      @Annotation6(method1 = Annotation6.Type.Y) float var1,
      @Annotation6(method1 = Annotation6.Type.POSITION) HudAnchor var2,
      final MixinCore2 var3,
      final Boolean2ObjectFunction<T> var4,
      final HudConditionSet var5
   ) {
      return new TypedHudRenderer<T>(var0, var1, var2) {
         @Override
         public MixinCore2 method15() {
            return var3;
         }

         @Override
         public T method7(boolean var1) {
            return (T)var4.get(var1);
         }

         @Override
         public HudConditionSet method5() {
            return var5;
         }
      };
   }

   public static <T> TypedHudRenderer<T> method24(
      @Annotation6(method1 = Annotation6.Type.X) float var0,
      @Annotation6(method1 = Annotation6.Type.Y) float var1,
      @Annotation6(method1 = Annotation6.Type.POSITION) HudAnchor var2,
      boolean var3,
      final MixinCore2 var4,
      final BiFunction<TypedHudRenderer<T>, @NotNull Boolean, T> var5
   ) {
      return new TypedHudRenderer<T>(var0, var1, var2, var3) {
         @Override
         public MixinCore2 method15() {
            return var4;
         }

         @Override
         public T method7(boolean var1) {
            return (T)var5.apply(this, var1);
         }
      };
   }

   public static <T> TypedHudRenderer<T> method25(
      @Annotation6(method1 = Annotation6.Type.X) float var0,
      @Annotation6(method1 = Annotation6.Type.Y) float var1,
      @Annotation6(method1 = Annotation6.Type.POSITION) HudAnchor var2,
      boolean var3,
      boolean var4,
      final MixinCore2 var5,
      final BiFunction<TypedHudRenderer<T>, @NotNull Boolean, T> var6
   ) {
      return new TypedHudRenderer<T>(var0, var1, var2, var3, var4) {
         @Override
         public MixinCore2 method15() {
            return var5;
         }

         @Override
         public T method7(boolean var1) {
            return (T)var6.apply(this, var1);
         }
      };
   }

   @Generated
   public ToggleOption method27() {
      return this.field13;
   }

   @Generated
   public ToggleOption method34() {
      return this.field20;
   }

   @Generated
   public ColorOption method35() {
      return this.field21;
   }

   @Nullable
   @Generated
   public ToggleOption method36() {
      return this.field26;
   }

   @Nullable
   @Generated
   public ToggleOption method37() {
      return this.field27;
   }

   interface Extension<T> {
      T method1(T var1, ColorOption var2);

      float method2(TypedHudRenderer<T> var1, T var2);

      float method3(TypedHudRenderer<T> var1, T var2);

      String method4(T var1);

      void method5(
         MixinHelper_4 var1,
         TypedHudRenderer<T> var2,
         T var3,
         float var4,
         float var5,
         ColorOption var6,
         @Nullable ColorOption var7,
         boolean var8,
         boolean var9
      );

      default boolean method6(@NotNull T var1) {
         return true;
      }

      default boolean method7() {
         return true;
      }
   }
}
