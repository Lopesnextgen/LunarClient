package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Ascii;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.base.Equivalence;

@GwtCompatible(emulated = true)
public final class MixinHelper26 {
   private static final int field1 = 16;
   private static final int field2 = 4;
   static final int field3 = -1;
   boolean useCustomMap;
   int initialCapacity = -1;
   int concurrencyLevel = -1;
   AbstractMapLoader.@Nullable Type5 field4;
   AbstractMapLoader.@Nullable Type5 field5;
   @Nullable Equivalence<Object> field6;

   @CanIgnoreReturnValue
   @Annotation3
   MixinHelper26 method1(Equivalence<Object> var1) {
      Preconditions.checkState(this.field6 == null, "key equivalence was already set to %s", this.field6);
      this.field6 = Preconditions.checkNotNull(var1);
      this.useCustomMap = true;
      return this;
   }

   Equivalence<Object> method2() {
      return MoreObjects.firstNonNull(this.field6, this.method7().defaultEquivalence());
   }

   @CanIgnoreReturnValue
   public MixinHelper26 method3(int var1) {
      Preconditions.checkState(this.initialCapacity == -1, "initial capacity was already set to %s", this.initialCapacity);
      Preconditions.checkArgument(var1 >= 0);
      this.initialCapacity = var1;
      return this;
   }

   int getInitialCapacity() {
      return this.initialCapacity == -1 ? 16 : this.initialCapacity;
   }

   @CanIgnoreReturnValue
   public MixinHelper26 method4(int var1) {
      Preconditions.checkState(this.concurrencyLevel == -1, "concurrency level was already set to %s", this.concurrencyLevel);
      Preconditions.checkArgument(var1 > 0);
      this.concurrencyLevel = var1;
      return this;
   }

   int getConcurrencyLevel() {
      return this.concurrencyLevel == -1 ? 4 : this.concurrencyLevel;
   }

   @CanIgnoreReturnValue
   @Annotation3
   public MixinHelper26 method5() {
      return this.method6(AbstractMapLoader.Type5.WEAK);
   }

   MixinHelper26 method6(AbstractMapLoader.Type5 var1) {
      Preconditions.checkState(this.field4 == null, "Key strength was already set to %s", this.field4);
      this.field4 = Preconditions.checkNotNull(var1);
      if (var1 != AbstractMapLoader.Type5.STRONG) {
         this.useCustomMap = true;
      }

      return this;
   }

   AbstractMapLoader.Type5 method7() {
      return MoreObjects.firstNonNull(this.field4, AbstractMapLoader.Type5.STRONG);
   }

   @CanIgnoreReturnValue
   @Annotation3
   public MixinHelper26 method8() {
      return this.method9(AbstractMapLoader.Type5.WEAK);
   }

   MixinHelper26 method9(AbstractMapLoader.Type5 var1) {
      Preconditions.checkState(this.field5 == null, "Value strength was already set to %s", this.field5);
      this.field5 = Preconditions.checkNotNull(var1);
      if (var1 != AbstractMapLoader.Type5.STRONG) {
         this.useCustomMap = true;
      }

      return this;
   }

   AbstractMapLoader.Type5 method10() {
      return MoreObjects.firstNonNull(this.field5, AbstractMapLoader.Type5.STRONG);
   }

   public <K, V> ConcurrentMap<K, V> makeMap() {
      return !this.useCustomMap ? new ConcurrentHashMap<>(this.getInitialCapacity(), 0.75F, this.getConcurrencyLevel()) : AbstractMapLoader.method1(this);
   }

   @Override
   public String toString() {
      MoreObjects$ToStringHelper var1 = MoreObjects.method1(this);
      if (this.initialCapacity != -1) {
         var1.method7("initialCapacity", this.initialCapacity);
      }

      if (this.concurrencyLevel != -1) {
         var1.method7("concurrencyLevel", this.concurrencyLevel);
      }

      if (this.field4 != null) {
         var1.method2("keyStrength", Ascii.toLowerCase(this.field4.toString()));
      }

      if (this.field5 != null) {
         var1.method2("valueStrength", Ascii.toLowerCase(this.field5.toString()));
      }

      if (this.field6 != null) {
         var1.method9("keyEquivalence");
      }

      return var1.toString();
   }

   enum Type {
      VALUE;
   }
}
