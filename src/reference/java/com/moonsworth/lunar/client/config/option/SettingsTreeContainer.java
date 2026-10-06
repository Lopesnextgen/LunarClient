package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.config.option.OptionDisplay;
import com.moonsworth.lunar.client.config.option.SettingsSectionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSection;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.driver.PhosphorIconLegacy;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.Annotation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;
import lombok.Generated;
import org.intellij.lang.annotations.Subst;
import org.jspecify.annotations.Nullable;

public class SettingsTreeContainer<Setting extends SettingsSectionBuilder<Setting>, Parent extends SettingsParent<Setting, Parent>>
   implements CategorizedSettingsBuilder<Setting, OptionGraphNode, Parent> {
   private final SettingsGroupCreator<Setting> field1;
   private final SettingsParentCreator<Setting, Parent> field2;
   private final LinkedList<Setting> field3 = new LinkedList<>();

   @Override
   public Setting method1(@Annotation(method1 = Annotation.Type.SETTING_LABELS) String var1, Consumer<Parent> var2) {
      SettingsSectionBuilder var3 = this.method20(var1, var2, null);
      if (var3 != null) {
         return (Setting)var3;
      }

      ClientOption var4 = OptionFactory.method15(var1).method31();
      return this.method5(var4, var2);
   }

   @Override
   public Setting method2(@Annotation(method1 = Annotation.Type.SETTING_LABELS) String var1, PhosphorIconLegacy var2, Consumer<Parent> var3) {
      if (var2.getType() != PhosphorIconLegacy.Type.SOLID) {
         throw new IllegalArgumentException("Category icons must be of type 'solid'");
      }

      SettingsSectionBuilder var4 = this.method20(var1, var3, var1x -> {
         if (!LunarBuildData.field4) {
            OptionDisplay var2x = (OptionDisplay)var1x.method7(com.moonsworth.lunar.client.config.option.OptionTraits.field2);
            if (var2x == null || var2x.icon() != var2) {
               throw new IllegalStateException("Attempting to add new Icon to existing label: " + var1x.getId());
            }
         }
      });
      if (var4 != null) {
         return (Setting)var4;
      }

      ClientOption var5 = ((LabelOption.Data)OptionFactory.method15(var1).method12(var2)).method31();
      return this.method5(var5, var3);
   }

   @Override
   public Setting method3(Supplier<String> var1, Consumer<Parent> var2) {
      ClientOption var3 = ((LabelOption.Data)OptionFactory.method15("dynamicCategory").method14(var1))
         .method31();
      return this.method5(var3, var2);
   }

   @Override
   public void method1(Consumer<SettingsComposer<Setting, Parent>> var1, Consumer<Setting> var2) {
      SettingsTreeContainer var3 = new SettingsTreeContainer<>(this.field1, this.field2);
      var1.accept(var3);
      this.method7(var3, var2);
   }

   public Setting method5(ClientOption<?> var1, Consumer<Parent> var2) {
      return this.method6(var1, var2, var1x -> var1x.method1(var1));
   }

   public Setting method6(ClientOption<?> var1, Consumer<Parent> var2, Consumer<Setting> var3) {
      SettingsSectionBuilder var4 = this.field1.apply(var1);
      this.field3.add((Setting)var4);
      SettingsParent var5 = this.field2.apply(new SettingsTreeContainer<>(this.field1, this.field2), var4);
      var2.accept(var5);
      this.method7(var5, var3);
      return (Setting)var4;
   }

   private void method7(SettingsComposer<Setting, ?> var1, Consumer<Setting> var2) {
      for (SettingsSectionBuilder var5 : var1.method18()) {
         if (var5 instanceof SettingsSection var6 && var6.method2().isEmpty()) {
            var2.accept(var5);
         }

         this.field3.add((Setting)var5);
      }
   }

   public Setting method8(ClientOption<?>... var1) {
      SettingsSectionBuilder var2 = this.field1.apply(var1);
      this.field3.add(0, (Setting)var2);
      return (Setting)var2;
   }

   public Setting method11(ClientOption<?> var1, ClientOption<?>... var2) {
      for (int var3 = 0; var3 < this.field3.size(); var3++) {
         SettingsSectionBuilder var4 = this.field3.get(var3);
         if (var4 instanceof SettingsSection var5) {
            List var6 = var5.method1();

            for (int var7 = 0; var7 < var6.size(); var7++) {
               if (((ClientOption)var6.get(var7)).equals(var1)) {
                  if (var7 == 0) {
                     SettingsSectionBuilder var10 = this.field1.apply(var2);
                     this.field3.add(var3, (Setting)var10);
                     return (Setting)var10;
                  }

                  SettingsSectionBuilder var8 = (SettingsSectionBuilder)var5.method6(var7);
                  this.field3.add(var3 + 1, (Setting)var8);
                  SettingsSectionBuilder var9 = this.field1.apply(var2);
                  this.field3.add(var3 + 1, (Setting)var9);
                  return (Setting)var9;
               }
            }
         }
      }

      return null;
   }

   public Setting method12(ClientOption<?> var1, ClientOption<?>... var2) {
      for (int var3 = 0; var3 < this.field3.size(); var3++) {
         SettingsSectionBuilder var4 = this.field3.get(var3);
         if (var4 instanceof SettingsSection var5) {
            List var6 = var5.method1();

            for (int var7 = 0; var7 < var6.size(); var7++) {
               if (((ClientOption)var6.get(var7)).equals(var1)) {
                  if (var7 == var6.size() - 1) {
                     SettingsSectionBuilder var10 = this.field1.apply(var2);
                     this.field3.add(var3 + 1, (Setting)var10);
                     return (Setting)var10;
                  }

                  SettingsSectionBuilder var8 = (SettingsSectionBuilder)var5.method6(var7 + 1);
                  this.field3.add(var3 + 1, (Setting)var8);
                  SettingsSectionBuilder var9 = this.field1.apply(var2);
                  this.field3.add(var3 + 1, (Setting)var9);
                  return (Setting)var9;
               }
            }
         }
      }

      return null;
   }

   public Setting method11(ClientOption<?>... var1) {
      SettingsSectionBuilder var2 = this.field1.apply(var1);
      this.field3.add((Setting)var2);
      return (Setting)var2;
   }

   public Setting method12(@Annotation(method1 = Annotation.Type.SETTING_LABELS) String var1) {
      SettingsSectionBuilder var2 = this.field1.apply(OptionFactory.method15(var1).method11().method31());
      this.field3.add((Setting)var2);
      return (Setting)var2;
   }

   public Setting method13() {
      SettingsSectionBuilder var1 = this.field1.apply(OptionFactory.method15("").method31());
      this.field3.add((Setting)var1);
      return (Setting)var1;
   }

   public Setting method14() {
      SettingsSectionBuilder var1 = this.field1.apply(OptionFactory.method15("").method12().method31());
      this.field3.add((Setting)var1);
      return (Setting)var1;
   }

   @Override
   public List<Setting> method18() {
      return this.field3;
   }

   @Override
   public Setting method5(@Subst("generalOptions") SettingsPage var1) {
      PhosphorIconLegacy var2 = var1.getIcon();
      if (var2.getType() != PhosphorIconLegacy.Type.SOLID) {
         throw new IllegalArgumentException("Category icons must be of type 'solid'");
      } else {
         SettingsSectionBuilder var3 = this.method20(var1.getName(), null, var1x -> {
            if (!LunarBuildData.field4) {
               OptionDisplay var2x = (OptionDisplay)var1x.method7(com.moonsworth.lunar.client.config.option.OptionTraits.field2);
               if (var2x == null || var2x.icon() != var2) {
                  throw new IllegalStateException("Attempting to add new Icon to existing label: " + var1x.getId());
               }
            }
         });
         if (var3 != null) {
            var3.method5(true);
            return (Setting)var3;
         } else {
            ClientOption var4 = ((LabelOption.Data)OptionFactory.method15(var1.getName()).method12(var2)).method31();
            SettingsSectionBuilder var5 = this.field1.apply(var4);
            this.field3.add((Setting)var5);
            var5.method5(true);
            return (Setting)var5;
         }
      }
   }

   @Override
   public void method6(ClientOption<?> var1) {
      for (SettingsSectionBuilder var3 : this.field3) {
         if (var3 instanceof SettingsSection var4) {
            var4.method1().remove(var1);
            var4.method2().remove(var1);
         }
      }
   }

   @Override
   public void method7(ClientOption<?> var1, ClientOption<?>... var2) {
      SettingsSectionBuilder var3 = this.field1.apply(var2);
      var3.method1(var1);
      this.field3.add((Setting)var3);
   }

   @Override
   public void method9(ClientOption<?> var1, ClientOption<?>... var2) {
      SettingsSectionBuilder var3 = this.field1.apply(var1);
      var3.method1(var2);
      this.field3.add((Setting)var3);
   }

   private @Nullable Setting method20(String var1, @Nullable Consumer<Parent> var2, @Nullable Consumer<LabelOption> var3) {
      for (SettingsSectionBuilder var5 : this.field3) {
         if (var5 instanceof SettingsSection var6 && var6.method2().isEmpty()) {
            for (ClientOption var8 : var6.method1()) {
               if (var8 instanceof LabelOption var9 && var9.getId().equals(var1)) {
                  if (var2 != null) {
                     SettingsParent var10 = this.field2.apply(new SettingsTreeContainer<>(this.field1, this.field2), var5);
                     var2.accept(var10);
                     this.method7(var10, var1x -> var1x.method1(var8));
                  }

                  if (var3 != null) {
                     var3.accept(var9);
                  }

                  return (Setting)var5;
               }
            }
         }
      }

      return null;
   }

   @Override
   public Map<ClientOption<?>, OptionGraphNode> method12() {
      LinkedHashMap var1 = new LinkedHashMap();

      for (SettingsSectionBuilder var3 : this.field3) {
         if (var3 instanceof SettingsSection var4) {
            ArrayList var5 = new ArrayList();
            List var6 = this.method23(var1, var4.method1());

            for (ClientOption var8 : var4.method2()) {
               OptionGraphNode var9 = (OptionGraphNode)var1.get(var8);
               if (var9 == null) {
                  var9 = new OptionGraphNode(new ArrayList<>(), var8, new ArrayList<>(var6), null, false, false);
                  var1.put(var8, var9);
                  var5.add(var9);
               } else {
                  var9.getChildren().addAll(var6);
                  var5.add(var9);
               }
            }

            for (ClientOption var14 : var4.method1()) {
               OptionGraphNode var16 = (OptionGraphNode)var1.get(var14);
               if (var16 == null) {
                  var1.put(var14, new OptionGraphNode(new ArrayList<>(var5), var14, new ArrayList<>(), var4.method3(), var4.method4(), var4.method5()));
               } else {
                  var16.method2().addAll(var5);
                  BooleanSupplier var10 = var4.method3();
                  if (var10 != null) {
                     BooleanSupplier var11 = var16.method1();
                     if (var11 != null) {
                        var16.method6(() -> var10.getAsBoolean() || var11.getAsBoolean());
                     } else {
                        var16.method6(var10);
                     }
                  }

                  var16.method7(var16.method4() || var4.method4());
                  var16.method8(var4.method5());
               }
            }
         }
      }

      Iterator var12 = var1.values().iterator();

      while (var12.hasNext()) {
         this.method22((OptionGraphNode)var12.next(), var12);
      }

      return var1;
   }

   protected void method22(OptionGraphNode var1, Iterator<OptionGraphNode> var2) {
      if (var1.method3() instanceof LabelOption var3 && !Objects.equals(var3.getId(), "")) {
         List var13 = var1.getChildren();
         if (!var1.method5() && var13.isEmpty()) {
            var2.remove();
            return;
         }

         BooleanSupplier var5 = var1.method1();
         boolean var6 = true;

         for (int var7 = 0; var7 < var13.size(); var7++) {
            OptionGraphNode var8 = (OptionGraphNode)var13.get(var7);
            if (var7 == 0 && var5 == null) {
               var5 = var8.method1();
            } else if (var5 != null && !var5.equals(var8.method1())) {
               var6 = false;
               break;
            }
         }

         if (var6) {
            var1.method6(var5);
         }

         if (!var1.method2().isEmpty()) {
            BooleanSupplier var14 = null;
            Iterator var15 = var1.method2().iterator();

            while (var15.hasNext()) {
               OptionGraphNode var9 = (OptionGraphNode)var15.next();
               ClientOption var10 = var9.method3();
               if (var10.getDefaultValue() instanceof Boolean) {
                  ClientOption var11 = var10;
                  if (var14 == null) {
                     var14 = () -> !(Boolean)var11.get() || var11.isHidden();
                  } else {
                     BooleanSupplier var12 = var14;
                     var14 = () -> (!(Boolean)var11.get() || var11.isHidden()) && var12.getAsBoolean();
                  }
               } else if (var14 == null) {
                  var14 = var10::isHidden;
               } else {
                  BooleanSupplier var18 = var14;
                  var14 = () -> var10.isHidden() && var18.getAsBoolean();
               }

               var9.getChildren().remove(var1);
               var15.remove();
            }

            BooleanSupplier var16 = var1.method1();
            if (var16 == null) {
               var1.method6(var14);
            } else if (var14 != null) {
               BooleanSupplier var17 = var14;
               var1.method6(() -> var17.getAsBoolean() || var16.getAsBoolean());
            }
         }
      }
   }

   private List<OptionGraphNode> method23(Map<ClientOption<?>, OptionGraphNode> var1, List<ClientOption<?>> var2) {
      ArrayList var3 = new ArrayList();

      for (ClientOption var5 : var2) {
         var3.add(this.method24(var1, var5));
      }

      return var3;
   }

   private OptionGraphNode method24(Map<ClientOption<?>, OptionGraphNode> var1, ClientOption<?> var2) {
      return var1.computeIfAbsent(var2, var0 -> new OptionGraphNode(new ArrayList<>(), (ClientOption<?>)var0, new ArrayList<>(), null, false, false));
   }

   @Generated
   public SettingsTreeContainer(SettingsGroupCreator<Setting> var1, SettingsParentCreator<Setting, Parent> var2) {
      this.field1 = var1;
      this.field2 = var2;
   }
}
