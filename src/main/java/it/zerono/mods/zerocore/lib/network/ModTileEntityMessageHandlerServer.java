package it.zerono.mods.zerocore.lib.network;

import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public abstract class ModTileEntityMessageHandlerServer extends ModTileEntityMessageHandler {
      protected IThreadListener getThreadListener(MessageContext ctx) {
            return (WorldServer)ctx.getServerHandler().player.getEntityWorld();
      }

      protected World getWorld(MessageContext ctx) {
            return ctx.getServerHandler().player.getEntityWorld();
      }
}
