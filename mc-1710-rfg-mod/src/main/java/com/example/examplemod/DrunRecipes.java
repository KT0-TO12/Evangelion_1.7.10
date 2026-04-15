package com.example.examplemod;

import net.minecraft.item.ItemStack;
import java.util.ArrayList;
import java.util.List;

public class DrunRecipes {

    private static final List<RecipeDrun> recipeList = new ArrayList<RecipeDrun>();

    public static void init() {

        addRecipe(ModItems.kvant_fog, ModItems.kvant_drun, new ItemStack(ModItems.kvantodrunovaya_chastica), 150, 1500);
    }

    public static void addRecipe(net.minecraft.item.Item input1, net.minecraft.item.Item input2, ItemStack output, int cooling, int drun) {
        recipeList.add(new RecipeDrun(input1, input2, output, cooling, drun));
    }

    public static RecipeDrun getMatchingRecipe(ItemStack s1, ItemStack s2) {
        if (s1 == null || s2 == null) return null;

        for (RecipeDrun recipe : recipeList) {
            boolean matchDirect = recipe.input1 == s1.getItem() && recipe.input2 == s2.getItem();
            boolean matchReverse = recipe.input1 == s2.getItem() && recipe.input2 == s1.getItem();

            if (matchDirect || matchReverse) {
                return recipe;
            }
        }
        return null;
    }

    public static class RecipeDrun {
        public final net.minecraft.item.Item input1;
        public final net.minecraft.item.Item input2;
        public final ItemStack output;
        public final int coolingCost;
        public final int drunentumCost;

        public RecipeDrun(net.minecraft.item.Item in1, net.minecraft.item.Item in2, ItemStack out, int cool, int drun) {
            this.input1 = in1;
            this.input2 = in2;
            this.output = out;
            this.coolingCost = cool;
            this.drunentumCost = drun;
        }
    }
}
