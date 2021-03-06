package org.bukkit.craftbukkit.inventory;

import net.minecraft.village.Trader;
import net.minecraft.village.TraderInventory;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantInventory;
import org.bukkit.inventory.MerchantRecipe;

import com.javazilla.bukkitfabric.interfaces.IMixinTradeOffer;
import com.javazilla.bukkitfabric.interfaces.IMixinTrader;

public class CraftInventoryMerchant extends CraftInventory implements MerchantInventory {

    private final Trader merchant;

    public CraftInventoryMerchant(Trader merchant, TraderInventory inventory) {
        super(inventory);
        this.merchant = merchant;
    }

    @Override
    public int getSelectedRecipeIndex() {
        return getInventory().recipeIndex;
    }

    @Override
    public MerchantRecipe getSelectedRecipe() {
        net.minecraft.village.TradeOffer nmsRecipe = getInventory().getTradeOffer();
        return (nmsRecipe == null) ? null : ((IMixinTradeOffer)nmsRecipe).asBukkit();
    }

    @Override
    public TraderInventory getInventory() {
        return (TraderInventory) inventory;
    }

    @Override
    public Merchant getMerchant() {
        return ((IMixinTrader)merchant).getCraftMerchant();
    }

}