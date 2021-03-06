package com.javazilla.bukkitfabric.mixin.screen;

import org.bukkit.craftbukkit.inventory.CraftInventoryView;
import org.bukkit.entity.Player;
import org.spongepowered.asm.mixin.Mixin;

import com.javazilla.bukkitfabric.interfaces.IMixinServerEntityPlayer;

import net.minecraft.screen.SmithingScreenHandler;

@Mixin(SmithingScreenHandler.class)
public class MixinSmithingScreenHandler extends MixinForgingScreenHandler {

    private CraftInventoryView bukkitEntity;

    @Override
    public CraftInventoryView getBukkitView() {
        if (bukkitEntity != null)
            return bukkitEntity;

        org.bukkit.craftbukkit.inventory.CraftInventory inventory = new org.bukkit.craftbukkit.inventory.CraftInventorySmithing(this.input, this.output);
        bukkitEntity = new CraftInventoryView((Player)((IMixinServerEntityPlayer)this.player).getBukkitEntity(), inventory, (SmithingScreenHandler)(Object)this);
        return bukkitEntity;
    }

}