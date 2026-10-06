package com.moonsworth.lunar.genesis;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

@Annotation2
public class MixinHelper2_9 {
   private final Object field1;
   private final Object field2;

   public MixinHelper2_9(Object var1, Object var2) {
      this.field1 = Preconditions.checkNotNull(var1);
      this.field2 = Preconditions.checkNotNull(var2);
   }

   public Object getSource() {
      return this.field1;
   }

   public Object getEvent() {
      return this.field2;
   }

   @Override
   public String toString() {
      return MoreObjects.method1(this).method2("source", this.field1).method2("event", this.field2).toString();
   }
}
