package com.moonsworth.lunar.client.framework.feature.knockbacktrainer.mixin;

import com.moonsworth.lunar.bridge.horsestats.DamageSourceQuery;
import com.moonsworth.lunar.client.guiRewindhandlers.Annotation3;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler29;
import com.moonsworth.lunar.client.guiRewindhandlers.nameplate.Nameplate2;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

@Annotation3(GuiRewindhandlersHandler29.class)
public class Knockbacktrainer extends Highlight implements Nameplate2 {
   private final boolean field1;
   @Nullable
   private final DamageSourceQuery field2;

   @Generated
   public boolean method1() {
      return this.field1;
   }

   @Nullable
   @Generated
   public DamageSourceQuery method2() {
      return this.field2;
   }

   @Generated
   public Knockbacktrainer(boolean flag, @Nullable DamageSourceQuery var2) {
      this.field1 = flag;
      this.field2 = var2;
   }
}
