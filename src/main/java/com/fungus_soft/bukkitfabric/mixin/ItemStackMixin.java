package com.fungus_soft.bukkitfabric.mixin;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.util.CraftMagicNumbers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.fungus_soft.bukkitfabric.interfaces.IMixinItemStack;
import com.mojang.datafixers.Dynamic;

import net.minecraft.datafixer.NbtOps;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;

@Mixin(ItemStack.class)
public class ItemStackMixin implements IMixinItemStack {

    @Shadow
    private Item item;

    public ItemStackMixin() {
    }

    @Override
    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public void convertStack(int version) {
        if (0 < version && version < CraftMagicNumbers.INSTANCE.getDataVersion()) {
            CompoundTag savedStack = new CompoundTag();
            ItemStack i = ((ItemStack)(Object)this);

            ((ItemStack)(Object)this).toTag(savedStack);
            savedStack = (CompoundTag) CraftServer.server.getDataFixer().update(TypeReferences.ITEM_STACK, new Dynamic(NbtOps.INSTANCE, savedStack), version, CraftMagicNumbers.INSTANCE.getDataVersion()).getValue();
            ((ItemStack)(Object)this).setTag(savedStack);
        }
    }

}