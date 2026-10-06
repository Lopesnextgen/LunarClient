package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;

enum AbstractMapLoader$Type2 {
   STRONG {
      @Override
      <K, V> MixinHelper6_5<K, V> newEntry(AbstractMapLoader$Data30<K, V> var1, K var2, int var3, @Nullable MixinHelper6_5<K, V> var4) {
         return new AbstractMapLoader$Data20<>((K)var2, var3, var4);
      }
   },
   STRONG_ACCESS {
      @Override
      <K, V> MixinHelper6_5<K, V> newEntry(AbstractMapLoader$Data30<K, V> var1, K var2, int var3, @Nullable MixinHelper6_5<K, V> var4) {
         return new AbstractMapLoader$Data44<>((K)var2, var3, var4);
      }

      @Override
      <K, V> MixinHelper6_5<K, V> copyEntry(AbstractMapLoader$Data30<K, V> var1, MixinHelper6_5<K, V> var2, MixinHelper6_5<K, V> var3) {
         MixinHelper6_5 var4 = super.copyEntry(var1, var2, var3);
         this.copyAccessEntry(var2, var4);
         return var4;
      }
   },
   STRONG_WRITE {
      @Override
      <K, V> MixinHelper6_5<K, V> newEntry(AbstractMapLoader$Data30<K, V> var1, K var2, int var3, @Nullable MixinHelper6_5<K, V> var4) {
         return new AbstractMapLoader$Data31<>((K)var2, var3, var4);
      }

      @Override
      <K, V> MixinHelper6_5<K, V> copyEntry(AbstractMapLoader$Data30<K, V> var1, MixinHelper6_5<K, V> var2, MixinHelper6_5<K, V> var3) {
         MixinHelper6_5 var4 = super.copyEntry(var1, var2, var3);
         this.copyWriteEntry(var2, var4);
         return var4;
      }
   },
   STRONG_ACCESS_WRITE {
      @Override
      <K, V> MixinHelper6_5<K, V> newEntry(AbstractMapLoader$Data30<K, V> var1, K var2, int var3, @Nullable MixinHelper6_5<K, V> var4) {
         return new AbstractMapLoader$Data28<>((K)var2, var3, var4);
      }

      @Override
      <K, V> MixinHelper6_5<K, V> copyEntry(AbstractMapLoader$Data30<K, V> var1, MixinHelper6_5<K, V> var2, MixinHelper6_5<K, V> var3) {
         MixinHelper6_5 var4 = super.copyEntry(var1, var2, var3);
         this.copyAccessEntry(var2, var4);
         this.copyWriteEntry(var2, var4);
         return var4;
      }
   },
   WEAK {
      @Override
      <K, V> MixinHelper6_5<K, V> newEntry(AbstractMapLoader$Data30<K, V> var1, K var2, int var3, @Nullable MixinHelper6_5<K, V> var4) {
         return new AbstractMapLoader$Data29<>(var1.field3, (K)var2, var3, var4);
      }
   },
   WEAK_ACCESS {
      @Override
      <K, V> MixinHelper6_5<K, V> newEntry(AbstractMapLoader$Data30<K, V> var1, K var2, int var3, @Nullable MixinHelper6_5<K, V> var4) {
         return new AbstractMapLoader$Data46<>(var1.field3, (K)var2, var3, var4);
      }

      @Override
      <K, V> MixinHelper6_5<K, V> copyEntry(AbstractMapLoader$Data30<K, V> var1, MixinHelper6_5<K, V> var2, MixinHelper6_5<K, V> var3) {
         MixinHelper6_5 var4 = super.copyEntry(var1, var2, var3);
         this.copyAccessEntry(var2, var4);
         return var4;
      }
   },
   WEAK_WRITE {
      @Override
      <K, V> MixinHelper6_5<K, V> newEntry(AbstractMapLoader$Data30<K, V> var1, K var2, int var3, @Nullable MixinHelper6_5<K, V> var4) {
         return new AbstractMapLoader$Data42<>(var1.field3, (K)var2, var3, var4);
      }

      @Override
      <K, V> MixinHelper6_5<K, V> copyEntry(AbstractMapLoader$Data30<K, V> var1, MixinHelper6_5<K, V> var2, MixinHelper6_5<K, V> var3) {
         MixinHelper6_5 var4 = super.copyEntry(var1, var2, var3);
         this.copyWriteEntry(var2, var4);
         return var4;
      }
   },
   WEAK_ACCESS_WRITE {
      @Override
      <K, V> MixinHelper6_5<K, V> newEntry(AbstractMapLoader$Data30<K, V> var1, K var2, int var3, @Nullable MixinHelper6_5<K, V> var4) {
         return new AbstractMapLoader$Data22<>(var1.field3, (K)var2, var3, var4);
      }

      @Override
      <K, V> MixinHelper6_5<K, V> copyEntry(AbstractMapLoader$Data30<K, V> var1, MixinHelper6_5<K, V> var2, MixinHelper6_5<K, V> var3) {
         MixinHelper6_5 var4 = super.copyEntry(var1, var2, var3);
         this.copyAccessEntry(var2, var4);
         this.copyWriteEntry(var2, var4);
         return var4;
      }
   };

   static final int ACCESS_MASK = 1;
   static final int WRITE_MASK = 2;
   static final int WEAK_MASK = 4;
   static final AbstractMapLoader$Type2[] factories = new AbstractMapLoader$Type2[]{
      STRONG, STRONG_ACCESS, STRONG_WRITE, STRONG_ACCESS_WRITE, WEAK, WEAK_ACCESS, WEAK_WRITE, WEAK_ACCESS_WRITE
   };

   AbstractMapLoader$Type2() {
   }

   static AbstractMapLoader$Type2 getFactory(AbstractMapLoader$Type4 var0, boolean var1, boolean var2) {
      int var3 = (var0 == AbstractMapLoader$Type4.WEAK ? 4 : 0) | (var1 ? 1 : 0) | (var2 ? 2 : 0);
      return factories[var3];
   }

   abstract <K, V> MixinHelper6_5<K, V> newEntry(AbstractMapLoader$Data30<K, V> var1, K var2, int var3, @Nullable MixinHelper6_5<K, V> var4);

   <K, V> MixinHelper6_5<K, V> copyEntry(AbstractMapLoader$Data30<K, V> var1, MixinHelper6_5<K, V> var2, MixinHelper6_5<K, V> var3) {
      return this.newEntry(var1, (K)var2.getKey(), var2.getHash(), var3);
   }

   <K, V> void copyAccessEntry(MixinHelper6_5<K, V> var1, MixinHelper6_5<K, V> var2) {
      var2.setAccessTime(var1.getAccessTime());
      AbstractMapLoader_2.method13(var1.getPreviousInAccessQueue(), var2);
      AbstractMapLoader_2.method13(var2, var1.getNextInAccessQueue());
      AbstractMapLoader_2.method14(var1);
   }

   <K, V> void copyWriteEntry(MixinHelper6_5<K, V> var1, MixinHelper6_5<K, V> var2) {
      var2.setWriteTime(var1.getWriteTime());
      AbstractMapLoader_2.method15(var1.getPreviousInWriteQueue(), var2);
      AbstractMapLoader_2.method15(var2, var1.getNextInWriteQueue());
      AbstractMapLoader_2.method16(var1);
   }
}
