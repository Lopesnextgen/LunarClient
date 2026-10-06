package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge8_6;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.Bridge_22;
import com.moonsworth.lunar.bridge.Bridge_69;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class EntityListBridgeAdapter implements Bridge_69 {
   public Bridge8_6 method1() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? new Bridge8Handler(EntityRabbit::new) : null;
   }

   public Bridge8_6 method2() {
      return new Bridge8Handler(EntityCreeper::new);
   }

   public Bridge8_6 method3() {
      return new Bridge8Handler(EntityChicken::new);
   }

   public Map<String, Class<BridgeExtension>> method5() {
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         return EntityList.stringToClassMapping$v1_7;
      }

      if (ThreadModuleDump63.MC_VERSION == 1) {
         return EntityList.stringToClassMapping;
      }

      HashMap var1 = new HashMap();

      for (ResourceLocation var3 : EntityList.getEntityNameList()) {
         if (var3.getNamespace$v1_12().equals("minecraft")) {
            String var4 = EntityList.getTranslationName$v1_12(var3);
            if (var4 != null) {
               var1.put(var4, EntityList.getClassFromName$v1_12(var3.toString()));
            }
         }
      }

      return var1;
   }

   public Component method4(String var1) {
      String var2 = "entity." + var1 + ".name";
      String var3 = StatCollector.translateToLocal(var2);
      return var3 != null && !var2.equals(var3) && !var3.equalsIgnoreCase("unknown") ? Component.text(var3) : Component.empty();
   }

   public int bridge$getEntityId(String var1) {
      if (ThreadModuleDump63.MC_VERSION == 0) {
         Integer var4 = (Integer)EntityList.stringToIDMapping$v1_7.get(var1);
         return var4 == null ? -1 : var4;
      }

      if (ThreadModuleDump63.MC_VERSION == 1) {
         return (Integer)EntityList.stringToIDMapping.get(var1);
      }

      Optional var2 = Bridge.method6();
      if (var2.isPresent()) {
         return ((Bridge_22)var2.get()).bridge$getEntityId(var1);
      }

      Class var3 = (Class)EntityList.REGISTRY$v1_12.getObject(ResourceLocation.newInstance(var1));
      return var3 == null ? -1 : EntityList.REGISTRY$v1_12.getIDForObject(var3);
   }
}
