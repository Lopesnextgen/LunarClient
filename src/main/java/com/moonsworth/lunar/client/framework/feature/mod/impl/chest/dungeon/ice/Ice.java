package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.ice;

import com.google.common.collect.ImmutableMap.Builder;
import java.util.List;
import java.util.Map;
import lombok.Generated;
import org.joml.Vector2i;

public class Ice {
   private final Map<Integer, Ice.Data> field1 = new Builder()
      .put(
         -968983981,
         new Ice.Data(
            List.of(new Vector2i(4, 9), new Vector2i(6, 8), new Vector2i(7, 6), new Vector2i(10, 6), new Vector2i(12, 2), new Vector2i(13, 0)),
            List.of(new Vector2i(10, 9))
         )
      )
      .put(
         210097055,
         new Ice.Data(
            List.of(new Vector2i(4, 9), new Vector2i(6, 8), new Vector2i(7, 6), new Vector2i(10, 6), new Vector2i(12, 2), new Vector2i(13, 0)),
            List.of(new Vector2i(10, 9))
         )
      )
      .put(-1994201379, new Ice.Data(List.of(new Vector2i(1, 3)), List.of(new Vector2i(1, 3))))
      .put(
         1622123947,
         new Ice.Data(
            List.of(
               new Vector2i(9, 11),
               new Vector2i(8, 9),
               new Vector2i(7, 6),
               new Vector2i(9, 5),
               new Vector2i(8, 3),
               new Vector2i(10, 3),
               new Vector2i(12, 2),
               new Vector2i(6, 2),
               new Vector2i(7, 0)
            ),
            List.of(new Vector2i(3, 8), new Vector2i(9, 5), new Vector2i(9, 2), new Vector2i(10, 0))
         )
      )
      .put(1631406229, new Ice.Data(List.of(new Vector2i(1, 0)), List.of(new Vector2i(1, 0))))
      .put(
         -415058227,
         new Ice.Data(
            List.of(new Vector2i(12, 8), new Vector2i(11, 6), new Vector2i(9, 2), new Vector2i(8, 0)), List.of(new Vector2i(9, 8), new Vector2i(4, 0))
         )
      )
      .put(
         -1576776993,
         new Ice.Data(
            List.of(new Vector2i(9, 11), new Vector2i(9, 8), new Vector2i(6, 2), new Vector2i(5, 0)),
            List.of(new Vector2i(0, 11), new Vector2i(0, 8), new Vector2i(0, 5), new Vector2i(0, 2), new Vector2i(1, 0))
         )
      )
      .put(906853499, new Ice.Data(List.of(new Vector2i(3, 11), new Vector2i(1, 0)), List.of(new Vector2i(3, 11), new Vector2i(1, 0))))
      .build();

   public Ice() {
   }

   @Generated
   public Map<Integer, Ice.Data> method1() {
      return this.field1;
   }

   public static class Data {
      private final List<Vector2i> field1;
      private final List<Vector2i> field2;

      @Generated
      public List<Vector2i> method1() {
         return this.field1;
      }

      @Generated
      public List<Vector2i> method2() {
         return this.field2;
      }

      @Generated
      public Data(List<Vector2i> list, List<Vector2i> list2) {
         this.field1 = list;
         this.field2 = list2;
      }
   }
}
