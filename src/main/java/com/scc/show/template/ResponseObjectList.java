package com.scc.show.template;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class ResponseObjectList<T> {

   @ApiModelProperty(notes = "The Total of clubs/shows", position = 1, required = true)
   private int itemsCount;

   @ApiModelProperty(notes = "The list of clubs/shows", position = 2, required = true, allowEmptyValue = true)
   private List<T> items;

   public ResponseObjectList(int itemsCount, List<T> items) {
      super();
      this.itemsCount = itemsCount;
      this.items = items;
   }

   public int getItemsCount() {
      return itemsCount;
   }

   public void setItemsCount(int itemsCount) {
      this.itemsCount = itemsCount;
   }

   public List<T> getItems() {
      return items;
   }

   public void setItems(List<T> items) {
      this.items = items;
   }

   public int size() {
      return this.items.size();
   }

}
