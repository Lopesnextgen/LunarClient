package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.Bridge_38;
import com.moonsworth.lunar.client.highlight.Highlight;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import lombok.Generated;
import org.jetbrains.annotations.VisibleForTesting;

@Annotation2(min = 8)
public abstract class TurboBatchRecorder implements EventRegistrar {
   protected int field1 = 0;
   protected boolean field2;
   protected final TurboEngineManager field3;
   protected final FragDataFactory<?> field4;

   public TurboBatchRecorder(TurboEngineManager var1) {
      this.field3 = var1;
      if (!LunarBuildData.field4) {
         this.field4 = new SimpleFragDataFactory<>(this.method2());
      } else {
         this.field4 = this.method2();
      }
   }

   public abstract BatchEntityType method1();

   public abstract FragDataFactory<?> method2();

   public abstract void clear();

   public abstract void method3();

   public abstract void method4(List<String> var1);

   @VisibleForTesting
   public abstract List<Bridge_38> method5(Predicate<Bridge_38> var1);

   public <T extends Highlight> void handle(Class<T> var1, Consumer<T> var2) {
      this.field3.method14(this, var1, var2);
   }

   public <T extends Highlight> void method2(Class<T> var1, Consumer<T> var2, int value) {
      this.field3.method15(this, var1, var2, value);
   }

   @Generated
   public int method7() {
      return this.field1;
   }

   @Generated
   public boolean isRecording() {
      return this.field2;
   }
}
