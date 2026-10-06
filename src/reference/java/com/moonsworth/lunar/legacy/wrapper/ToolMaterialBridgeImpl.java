package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.ToolMaterialBridge;
import com.moonsworth.lunar.bridge.TiersBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import lombok.Generated;
import net.minecraft.item.Item.ToolMaterial;

public class ToolMaterialBridgeImpl implements TiersBridge {
   private final AtomicReference<Object> field1 = new AtomicReference<>();

   public ToolMaterialBridgeImpl() {
   }

   public ToolMaterialBridge method1() {
      return (ToolMaterialBridge)ToolMaterial.WOOD;
   }

   public ToolMaterialBridge method2() {
      return (ToolMaterialBridge)ToolMaterial.STONE;
   }

   public ToolMaterialBridge method3() {
      return (ToolMaterialBridge)ToolMaterial.IRON;
   }

   public ToolMaterialBridge method4() {
      return (ToolMaterialBridge)ToolMaterial.goldColor;
   }

   public ToolMaterialBridge method5() {
      return Ref.MC_VERSION <= 1 ? (ToolMaterialBridge)ToolMaterial.EMERALD : (ToolMaterialBridge)ToolMaterial.DIAMOND$v1_12;
   }

   public ToolMaterialBridge method6() {
      throw new AbstractMethodErrorImpl();
   }

   public List<ToolMaterialBridge> method7() {
      return this.method8();
   }

   @Generated
   public List<ToolMaterialBridge> method8() {
      Object obj1 = this.field1.get();
      if (obj1 == null) {
         synchronized (this.field1) {
            obj1 = this.field1.get();
            if (obj1 == null) {
               List list3 = List.of(this.method1(), this.method2(), this.method3(), this.method4(), this.method5());
               obj1 = list3 == null ? this.field1 : list3;
               this.field1.set(obj1);
            }
         }
      }

      return (List<ToolMaterialBridge>)(obj1 == this.field1 ? null : obj1);
   }
}
