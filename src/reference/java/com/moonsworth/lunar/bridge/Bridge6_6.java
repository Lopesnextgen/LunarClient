package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.MappingSetUtils;
import com.moonsworth.lunar.ichor.mixin.MethodSignature;
import com.moonsworth.lunar.ichor.mixin.MixinHelper3;
import com.moonsworth.lunar.ichor.mixin.ClassTypeSignature;
import com.moonsworth.lunar.ichor.mixin.GenericSignatureParser;
import com.moonsworth.lunar.ichor.util.AsmUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.model.ClassMapping;
import org.cadixdev.lorenz.model.FieldMapping;
import org.cadixdev.lorenz.model.MethodMapping;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public class Bridge6_6 {
   private final String[] field1;

   public Bridge6_6(String[] items1) {
      this.field1 = items1;
   }

   public List<MixinTargetMember> method1(BridgeMethodGenerator bridge_671, ClassNode node2) {
      ArrayList list3 = new ArrayList();
      ClassNode node4 = node2;
      MixinHelper3 mixinhelper35 = new MixinHelper3(new ClassTypeSignature(node2.name));
      MappingSet mappingset6 = bridge_671.method7().getMappings();
      ClassMapping classmapping7 = mappingset6 == null ? null : mappingset6.getOrCreateClassMapping(MappingSetUtils.method2(mappingset6, node2.name));

      for (String text11 : this.field1) {
         if (node4 == null) {
            throw new IllegalStateException(node2.name + " " + String.join(",", this.field1) + ": tried to find " + text11 + " in null class node");
         }

         MixinTargetMember bridge_1312 = this.method2(node4, mixinhelper35, text11, classmapping7);
         if (bridge_1312 == null) {
            throw new IllegalStateException(
               node2.name + " " + String.join(",", this.field1) + ": uh what where the memberNode at??? looking for: " + text11 + " in " + node4.name
            );
         }

         list3.add(bridge_1312);
         if (bridge_1312.method1().method2() instanceof ClassTypeSignature mixinhelper713) {
            node4 = bridge_671.method5().getAsNode(mixinhelper713.name(), 5);
            mixinhelper35 = bridge_1312.method1();
            if (mappingset6 != null) {
               classmapping7 = mappingset6.getOrCreateClassMapping(MappingSetUtils.method2(mappingset6, node4.name));
            }
         } else {
            node4 = null;
         }
      }

      return list3;
   }

   @Nullable
   private MixinTargetMember method2(ClassNode node1, MixinHelper3 mixinhelper32, String text, @Nullable ClassMapping<?, ?> classmapping4) {
      FieldNode field5 = null;
      if (text.indexOf(40) == -1) {
         String text6 = text;
         if (classmapping4 != null) {
            for (FieldMapping fieldmapping8 : classmapping4.getFieldMappings()) {
               if (fieldmapping8.getObfuscatedName().equals(text)) {
                  text6 = fieldmapping8.getDeobfuscatedName();
                  break;
               }
            }
         }

         for (FieldNode field21 : node1.fields) {
            if (field21.name.equals(text6)) {
               field5 = field21;
               break;
            }
         }
      }

      if (field5 != null) {
         return new MixinTargetMember(node1, mixinhelper32, field5, null, null, AsmUtils.method34(node1));
      }

      if (classmapping4 != null) {
         if (text.indexOf(40) != -1) {
            for (MethodMapping methodmapping18 : classmapping4.getMethodMappings()) {
               if (methodmapping18.getSignature().toJvmsIdentifier().equals(text)) {
                  text = methodmapping18.getDeobfuscatedSignature().toJvmsIdentifier();
                  break;
               }
            }
         } else {
            for (MethodMapping methodmapping19 : classmapping4.getMethodMappings()) {
               if (methodmapping19.getObfuscatedName().equals(text)) {
                  text = methodmapping19.getDeobfuscatedName();
                  break;
               }
            }
         }
      }

      MethodSignature mixinhelper216 = null;
      boolean flag20 = text.indexOf(40) != -1;
      String text22 = flag20 ? text.substring(0, text.indexOf(40)) : text;
      if (flag20) {
         try {
            mixinhelper216 = GenericSignatureParser.method1(text.substring(text.indexOf(40)), node1.signature, mixinhelper32);
         } catch (Exception exception13) {
         }
      }

      MethodNode method9 = null;
      if (mixinhelper216 != null) {
         for (MethodNode method11 : node1.methods) {
            MethodSignature mixinhelper212 = GenericSignatureParser.method1(Objects.requireNonNullElse(method11.signature, method11.desc), node1.signature, mixinhelper32);
            if (method11.name.equals(text22) && mixinhelper212.equals(mixinhelper216)) {
               method9 = method11;
               break;
            }
         }
      } else {
         for (MethodNode method24 : node1.methods) {
            if (method24.name.equals(text)) {
               if (method9 != null) {
                  throw new IllegalStateException(
                     "Ambiguous method reference '%s' in %s! Both %s and %s match".formatted(text, node1.name, method9.desc, method24.desc)
                  );
               }

               method9 = method24;
               mixinhelper216 = GenericSignatureParser.method1(Objects.requireNonNullElse(method24.signature, method24.desc), node1.signature, mixinhelper32);
            }
         }
      }

      return method9 != null ? new MixinTargetMember(node1, mixinhelper32, null, method9, mixinhelper216, AsmUtils.method34(node1)) : null;
   }

   public String[] method3() {
      return this.field1;
   }
}
