package com.fungus_soft.bukkitfabric.mixin;

import java.util.Map;
import java.util.Queue;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.event.server.ServerLoadEvent.LoadType;
import org.bukkit.plugin.PluginLoadOrder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fungus_soft.bukkitfabric.interfaces.IMixinMinecraftServer;
import com.fungus_soft.bukkitfabric.interfaces.IMixinServerWorld;
import com.google.gson.JsonElement;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListenerFactory;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.level.LevelGeneratorType;
import net.minecraft.world.level.LevelInfo;
import net.minecraft.world.level.LevelProperties;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin implements IMixinMinecraftServer {

    @Shadow
    private final Map<DimensionType, ServerWorld> worlds;

    @Shadow
    private final WorldGenerationProgressListenerFactory worldGenerationProgressListenerFactory;

    @Shadow
    public void upgradeWorld(String name) {
    }

    @Shadow
    public CommandManager commandManager;

    public java.util.Queue<Runnable> processQueue = new java.util.concurrent.ConcurrentLinkedQueue<Runnable>();

    public MinecraftServerMixin() {
        this.worlds = null; // Won't be called
        this.worldGenerationProgressListenerFactory = null;
    }

    @Inject(at = @At(value = "TAIL"), method = "loadWorld")
    private void finish(String worldName, String serverName, long seed, LevelGeneratorType generatorType, JsonElement generatorSettings, CallbackInfo callbackInfo) {
        CraftServer s = ((CraftServer)Bukkit.getServer());

        s.enablePlugins(PluginLoadOrder.POSTWORLD);
        s.getPluginManager().callEvent(new ServerLoadEvent(LoadType.STARTUP));
    }

    @Overwrite
    public String getServerModName() {
        return "Fabric + Bukkit4Fabric";
    }

    @Override
    public Map<DimensionType, ServerWorld> getWorldMap() {
        return worlds;
    }

    @Override
    public void convertWorld(String name) {
        upgradeWorld(name);
    }

    @Override
    public WorldGenerationProgressListenerFactory getWorldGenerationProgressListenerFactory() {
        return worldGenerationProgressListenerFactory;
    }

    @Override
    public Queue<Runnable> getProcessQueue() {
        return processQueue;
    }

    @Override
    public CommandManager setCommandManager(CommandManager commandManager) {
        return (this.commandManager = commandManager);
    }

    @Override
    public void initWorld(ServerWorld world, LevelProperties prop, LevelInfo info) {
        World bukkit = ((IMixinServerWorld)world).getCraftWorld();
        world.getWorldBorder().load(prop);

        if (bukkit.getGenerator() != null)
            bukkit.getPopulators().addAll(bukkit.getGenerator().getDefaultPopulators(bukkit));

        if (!prop.isInitialized()) {
            try {
                world.init(info);
                if (prop.getGeneratorType() == LevelGeneratorType.DEBUG_ALL_BLOCK_STATES) {
                    // TODO Bukkit4Fabric: we should be fine with not adding this method, as who would run a server with a debug world
                    // this.a(worlddata);
                }

                prop.setInitialized(true);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

            prop.setInitialized(true);
        }
    }

}