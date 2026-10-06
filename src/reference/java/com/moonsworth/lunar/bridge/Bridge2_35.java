package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.config.Config;
import javax.annotation.Nullable;
import org.cadixdev.bombe.provider.ClassProvider;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.model.Mapping;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class Bridge2_35 {
   private final String field1;
   private final Bridge4_2 field2;
   private final Bridge3_5[] field3;

   public Bridge2_35(String text, Bridge4_2 bridge4_22, Bridge3_5[] items3) {
      this.field1 = text;
      this.field2 = bridge4_22;
      this.field3 = items3;
   }

   @Nullable
   public String method1(Config config1) {
      Bridge6_6[] items2 = this.field2.method3(config1);
      return items2 != null && items2.length > 0 ? items2[0].method3()[0] : null;
   }

   public void method2(Bridge2_24 bridge2_241, ClassNode node2, Config config3, ClassProvider provider) {
      boolean flag5 = this.field1.equals(node2.name);
      if (!flag5 && !node2.interfaces.contains(this.field1)) {
         node2.interfaces.add(this.field1);
      }

      for (Bridge3_5 bridge3_59 : this.field3) {
         if (flag5 == bridge3_59.isStatic()) {
            try {
               this.method3(bridge2_241, node2, bridge3_59, config3, provider);
            } catch (Exception exception12) {
               MethodNode method11 = bridge3_59.method2();
               throw new RuntimeException("Failed to generate bridge implementation for '%s%s' in '%s'".formatted(method11.name, method11.desc, node2.name), exception12);
            }
         }
      }
   }

   private void method3(Bridge2_24 bridge2_241, ClassNode node2, Bridge3_5 bridge3_53, Config config4, ClassProvider provider) {
      String text6 = this.method1(config4);
      if (text6 != null) {
         MappingSet mappingset7 = bridge2_241.getMappings();
         if (mappingset7 != null) {
            text6 = mappingset7.getClassMapping(text6).<String>map(Mapping::getFullDeobfuscatedName).orElse(text6);
         }

         MethodNode method8 = bridge3_53.method2();
         Bridge6_6[] items9 = bridge3_53.method1().method3(config4);
         if (items9 != null && items9.length != 0) {
            if (bridge3_53.isStatic()) {
               MethodNode method10 = node2.methods.stream().filter(arg1x -> arg1x.name.equals(method8.name) && arg1x.desc.equals(method8.desc)).findFirst().orElseThrow();
               ClassNode node11 = provider.getAsNode(text6, 1);
               if (node11 == null) {
                  throw new IllegalStateException("%s was expecting a ClassNode for %s.".formatted(node2.name, text6));
               }

               method10.instructions = new BridgeMethodGenerator(bridge3_53, bridge2_241, config4, provider, node11).method1();
            } else {
               MethodNode method12 = new MethodNode();
               method12.name = method8.name;
               method12.desc = method8.desc;
               method12.signature = method8.signature;
               method12.access = 1;
               method12.instructions = new BridgeMethodGenerator(bridge3_53, bridge2_241, config4, provider, node2).method1();
               node2.methods.add(method12);
            }
         }
      }
   }

   public String method4() {
      return this.field1;
   }

   public Bridge4_2 method5() {
      return this.field2;
   }

   public Bridge3_5[] method6() {
      return this.field3;
   }
}
