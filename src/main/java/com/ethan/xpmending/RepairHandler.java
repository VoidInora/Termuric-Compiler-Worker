package com.ethan.xpmending;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.enchantment.Enchantments;

public class RepairHandler {

    public static void repairWithXP(PlayerEntity player) {
        if (player.experienceLevel <= 0) return;

        for (ItemStack stack : player.getInventory().main) {
            if (stack.hasEnchantments() && stack.getEnchantments().toString().contains(Enchantments.MENDING.getName(0))) {
                if (stack.isDamaged()) {
                    int cost = 1; // cost per repair action
                    if (player.experienceLevel >= cost) {
                        stack.setDamage(Math.max(stack.getDamage() - 25, 0)); // repair 25 durability per level
                        player.addExperienceLevels(-cost);
                    }
                }
            }
        }
    }
}
