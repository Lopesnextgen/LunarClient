package com.moonsworth.lunar.client.render.texture;

import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Generated;

class ClickImpl extends com.moonsworth.lunar.client.render.texture.ModelTextureUpdater {
   private final Set<String> field1 = new LinkedHashSet<>();

   ClickImpl() {
   }

   @Override
   protected boolean method5(String text) {
      this.field1.add(text);
      return false;
   }

   @Generated
   public Set<String> method2() {
      return this.field1;
   }
}
