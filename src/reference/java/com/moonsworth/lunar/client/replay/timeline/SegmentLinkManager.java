package com.moonsworth.lunar.client.replay.timeline;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.replay.timeline.RewindIterator;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import lombok.Generated;

@SerializedNameOnly
public class SegmentLinkManager {
   private final HashMapImpl field1;
   @SerializedName("links")
   private final Map<UUID, Set<UUID>> field2 = new HashMap<>();

   private RewindIterator<?> method1(UUID uuid1) {
      Entry entry2 = this.field1.get(uuid1);
      return entry2 == null ? null : (RewindIterator)entry2.getValue();
   }

   private Set<RewindIterator<?>> method2(Set<UUID> set1) {
      HashSet set2 = new HashSet();

      for (UUID uuid4 : set1) {
         RewindIterator rewinditerator5 = this.method1(uuid4);
         if (rewinditerator5 != null) {
            set2.add(rewinditerator5);
         }
      }

      return set2;
   }

   public Set<UUID> method3(UUID uuid1) {
      return this.method4(this.method1(uuid1));
   }

   public Set<UUID> method4(RewindIterator<?> rewinditerator1) {
      if (rewinditerator1 == null) {
         return Collections.emptySet();
      }

      UUID uuid2 = rewinditerator1.method17();
      if (uuid2 == null) {
         return Collections.emptySet();
      }

      Set set3 = this.field2.get(uuid2);
      return set3 == null ? Collections.emptySet() : set3;
   }

   public Set<RewindIterator<?>> method5(UUID uuid1) {
      return this.method2(this.method3(uuid1));
   }

   public Set<RewindIterator<?>> method6(RewindIterator<?> rewinditerator1) {
      return this.method2(this.method4(rewinditerator1));
   }

   public void method7(UUID uuid1, UUID uuid2) {
      Set set3 = this.field2.computeIfAbsent(uuid1, arg0 -> new HashSet<>());
      set3.add(uuid2);
   }

   public void method8(UndoRedoManager nameplate21, Set<UUID> set2) {
      this.method9(nameplate21, this.method2(set2));
   }

   public void method9(UndoRedoManager nameplate21, Set<RewindIterator<?>> set2) {
      if (set2.size() > 1) {
         boolean flag3 = nameplate21.method3();
         if (!flag3) {
            nameplate21.method1();
         }

         UUID uuid4 = UUID.randomUUID();
         HashSet set5 = new HashSet();
         HashMap map6 = new HashMap();

         for (RewindIterator rewinditerator8 : set2) {
            map6.put(rewinditerator8.getId(), rewinditerator8.method17());
         }

         this.method11(nameplate21, set2);

         for (RewindIterator rewinditerator10 : set2) {
            rewinditerator10.method23(uuid4);
            set5.add(rewinditerator10.getId());
         }

         nameplate21.method4(() -> this.field2.remove(uuid4), () -> this.field2.put(uuid4, set5));
         this.field2.put(uuid4, set5);
         if (!flag3) {
            nameplate21.endBatch();
         }
      }
   }

   public void method10(UndoRedoManager nameplate21, Set<UUID> set2) {
      this.method11(nameplate21, this.method2(set2));
   }

   public void method11(UndoRedoManager nameplate21, Set<RewindIterator<?>> set2) {
      if (set2 != null && !set2.isEmpty()) {
         boolean flag3 = nameplate21.method3();
         if (!flag3) {
            nameplate21.method1();
         }

         for (RewindIterator rewinditerator5 : set2) {
            UUID uuid6 = rewinditerator5.method17();
            if (uuid6 == null) {
               return;
            }

            Set set7 = this.field2.get(uuid6);
            if (set7 != null) {
               nameplate21.method4(() -> set7.add(rewinditerator5.getId()), () -> set7.remove(rewinditerator5.getId()));
               set7.remove(rewinditerator5.getId());
               if (set7.size() <= 1) {
                  this.method12(nameplate21, uuid6);
               }
            }

            nameplate21.method4(() -> rewinditerator5.method23(uuid6), () -> rewinditerator5.method23(null));
            rewinditerator5.method23(null);
         }

         if (!flag3) {
            nameplate21.endBatch();
         }
      }
   }

   public void method12(UndoRedoManager nameplate21, UUID uuid2) {
      Set set3 = this.field2.remove(uuid2);
      if (set3 != null) {
         nameplate21.method4(() -> this.field2.put(uuid2, set3), () -> this.field2.remove(uuid2));

         for (UUID uuid5 : set3) {
            RewindIterator rewinditerator6 = this.method1(uuid5);
            if (rewinditerator6 != null) {
               nameplate21.method4(() -> rewinditerator6.method23(uuid2), () -> rewinditerator6.method23(null));
               rewinditerator6.method23(null);
            }
         }
      }
   }

   @Generated
   public SegmentLinkManager(HashMapImpl hashmapimpl1) {
      this.field1 = hashmapimpl1;
   }
}
