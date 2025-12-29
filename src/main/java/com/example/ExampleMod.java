package com.hackerini;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

public class TreeCutter implements ModInitializer {

    @Override
    public void onInitialize() {
        // Rejestrujemy event niszczenia bloku
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, entity) -> {
            // Sprawdzamy, czy gracz nie jest w creative i czy trzyma siekierę
            if (!player.isCreative() && player.getMainHandStack().getItem().toString().contains("_axe")) {
                // Sprawdzamy, czy blok to "Log" (używając tagów Minecrafta)
                if (state.isIn(BlockTags.LOGS)) {
                    cutTree(world, pos, new HashSet<>());
                }
            }
            return true; // true oznacza, że pozwalamy na zniszczenie bloku
        });
    }

    private void cutTree(World world, BlockPos pos, Set<BlockPos> visited) {
        // Zabezpieczenie przed nieskończoną rekurencją i zbyt dużymi drzewami
        if (visited.size() > 256 || visited.contains(pos)) return;
        visited.add(pos);

        BlockState state = world.getBlockState(pos);
        if (state.isIn(BlockTags.LOGS)) {
            // Niszczymy blok i upuszczamy przedmioty
            world.breakBlock(pos, true);

            // Szukamy sąsiednich bloków (góra, dół, boki)
            for (int x = -1; x <= 1; x++) {
                for (int y = 0; y <= 1; y++) { // Głównie patrzymy w górę
                    for (int z = -1; z <= 1; z++) {
                        BlockPos neighbor = pos.add(x, y, z);
                        cutTree(world, neighbor, visited);
                    }
                }
            }
        }
    }
}