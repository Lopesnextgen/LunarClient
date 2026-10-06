package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge8_6;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityRegistryBridge;
import com.moonsworth.lunar.bridge.EntityListBridge;
import com.moonsworth.lunar.client.framework.Ref;
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

public class EntityListBridgeImpl implements EntityListBridge {
   public Bridge8_6 method1() {
      return Ref.MC_VERSION >= 1 ? new EntityFactoryBridgeImpl(EntityRabbit::new) : null;
   }

   public Bridge8_6 method2() {
      return new EntityFactoryBridgeImpl(EntityCreeper::new);
   }

   public Bridge8_6 method3() {
      return new EntityFactoryBridgeImpl(EntityChicken::new);
   }

   public Map<String, Class<BridgeExtension>> method5() {
      if (Ref.MC_VERSION <= 0) {
         return EntityList.stringToClassMapping$v1_7;
      }

      if (Ref.MC_VERSION == 1) {
         return EntityList.stringToClassMapping;
      }

      HashMap map1 = new HashMap();

      for (ResourceLocation location3 : EntityList.getEntityNameList()) {
         if (location3.getNamespace$v1_12().equals("minecraft")) {
            String text4 = EntityList.getTranslationName$v1_12(location3);
            if (text4 != null) {
               map1.put(text4, EntityList.getClassFromName$v1_12(location3.toString()));
            }
         }
      }

      return map1;
   }

   public Component method4(String text1) {
      String text2 = "entity." + text1 + ".name";
      String text3 = StatCollector.translateToLocal(text2);
      return text3 != null && !text2.equals(text3) && !text3.equalsIgnoreCase("unknown") ? Component.text(text3) : Component.empty();
   }

   public int bridge$getEntityId(String text1) {
      if (Ref.MC_VERSION == 0) {
         Integer number4 = (Integer)EntityList.stringToIDMapping$v1_7.get(text1);
         return number4 == null ? -1 : number4;
      }

      if (Ref.MC_VERSION == 1) {
         return (Integer)EntityList.stringToIDMapping.get(text1);
      }

      Optional optional2 = Bridge.method6();
      if (optional2.isPresent()) {
         return ((EntityRegistryBridge)optional2.get()).bridge$getEntityId(text1);
      }

      Class clazz3 = (Class)EntityList.REGISTRY$v1_12.getObject(ResourceLocation.newInstance(text1));
      return clazz3 == null ? -1 : EntityList.REGISTRY$v1_12.getIDForObject(clazz3);
   }

   @Generated
   public EntityListBridgeImpl() {
   }
}
