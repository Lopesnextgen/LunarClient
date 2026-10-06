package com.moonsworth.lunar.client.framework.feature.rewind.highlight;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.util.Annotation7;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import lombok.Generated;

@Annotation7
public class Highlight2 {
   private final HashMapImpl field1;
   @SerializedName("links")
   private final Map<UUID, Set<UUID>> field2 = new HashMap<>();

   private RewindIterator<?> method1(UUID var1) {
      Entry var2 = this.field1.get(var1);
      return var2 == null ? null : (RewindIterator)var2.getValue();
   }

   private Set<RewindIterator<?>> method2(Set<UUID> var1) {
      HashSet var2 = new HashSet();

      for (UUID var4 : var1) {
         RewindIterator var5 = this.method1(var4);
         if (var5 != null) {
            var2.add(var5);
         }
      }

      return var2;
   }

   public Set<UUID> method3(UUID var1) {
      return this.method4(this.method1(var1));
   }

   public Set<UUID> method4(RewindIterator<?> var1) {
      if (var1 == null) {
         return Collections.emptySet();
      }

      UUID var2 = var1.method17();
      if (var2 == null) {
         return Collections.emptySet();
      }

      Set var3 = this.field2.get(var2);
      return var3 == null ? Collections.emptySet() : var3;
   }

   public Set<RewindIterator<?>> method5(UUID var1) {
      return this.method2(this.method3(var1));
   }

   public Set<RewindIterator<?>> method6(RewindIterator<?> var1) {
      return this.method2(this.method4(var1));
   }

   public void method7(UUID var1, UUID var2) {
      Set var3 = this.field2.computeIfAbsent(var1, var0 -> new HashSet<>());
      var3.add(var2);
   }

   public void method8(Nameplate2 var1, Set<UUID> var2) {
      this.method9(var1, this.method2(var2));
   }

   public void method9(Nameplate2 var1, Set<RewindIterator<?>> var2) {
      if (var2.size() > 1) {
         boolean var3 = var1.method3();
         if (!var3) {
            var1.method1();
         }

         UUID var4 = UUID.randomUUID();
         HashSet var5 = new HashSet();
         HashMap var6 = new HashMap();

         for (RewindIterator var8 : var2) {
            var6.put(var8.getId(), var8.method17());
         }

         this.method11(var1, var2);

         for (RewindIterator var10 : var2) {
            var10.method23(var4);
            var5.add(var10.getId());
         }

         var1.method4(() -> this.field2.remove(var4), () -> this.field2.put(var4, var5));
         this.field2.put(var4, var5);
         if (!var3) {
            var1.endBatch();
         }
      }
   }

   public void method10(Nameplate2 var1, Set<UUID> var2) {
      this.method11(var1, this.method2(var2));
   }

   public void method11(Nameplate2 var1, Set<RewindIterator<?>> var2) {
      if (var2 != null && !var2.isEmpty()) {
         boolean var3 = var1.method3();
         if (!var3) {
            var1.method1();
         }

         for (RewindIterator var5 : var2) {
            UUID var6 = var5.method17();
            if (var6 == null) {
               return;
            }

            Set var7 = this.field2.get(var6);
            if (var7 != null) {
               var1.method4(() -> var7.add(var5.getId()), () -> var7.remove(var5.getId()));
               var7.remove(var5.getId());
               if (var7.size() <= 1) {
                  this.method12(var1, var6);
               }
            }

            var1.method4(() -> var5.method23(var6), () -> var5.method23(null));
            var5.method23(null);
         }

         if (!var3) {
            var1.endBatch();
         }
      }
   }

   public void method12(Nameplate2 var1, UUID var2) {
      Set var3 = this.field2.remove(var2);
      if (var3 != null) {
         var1.method4(() -> this.field2.put(var2, var3), () -> this.field2.remove(var2));

         for (UUID var5 : var3) {
            RewindIterator var6 = this.method1(var5);
            if (var6 != null) {
               var1.method4(() -> var6.method23(var2), () -> var6.method23(null));
               var6.method23(null);
            }
         }
      }
   }

   @Generated
   public Highlight2(HashMapImpl var1) {
      this.field1 = var1;
   }
}
