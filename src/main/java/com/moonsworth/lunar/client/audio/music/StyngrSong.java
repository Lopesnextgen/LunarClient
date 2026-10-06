package com.moonsworth.lunar.client.audio.music;

import com.google.gson.annotations.SerializedName;
import java.util.UUID;
import lombok.Generated;

public class StyngrSong {
   @SerializedName("id")
   private int id;
   @SerializedName("styngrId")
   private UUID field1;
   @SerializedName("name")
   private String name;
   @SerializedName("image")
   private String field2;
   @SerializedName("song")
   private String field3;
   @SerializedName("artist")
   private String artist;
   @SerializedName("album")
   private String album;
   @SerializedName("lyrics")
   private String field4;
   @SerializedName("durationMillis")
   private long field5;
   @SerializedName("copyrightSafe")
   private boolean field6;
   @SerializedName("genres")
   private String[] field7;

   public StyngrSong() {
   }

   @Generated
   public int getId() {
      return this.id;
   }

   @Generated
   public UUID method1() {
      return this.field1;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public String method2() {
      return this.field2;
   }

   @Generated
   public String method3() {
      return this.field3;
   }

   @Generated
   public String getArtist() {
      return this.artist;
   }

   @Generated
   public String getAlbum() {
      return this.album;
   }

   @Generated
   public String method5() {
      return this.field4;
   }

   @Generated
   public long method6() {
      return this.field5;
   }

   @Generated
   public boolean method7() {
      return this.field6;
   }

   @Generated
   public String[] method8() {
      return this.field7;
   }
}
