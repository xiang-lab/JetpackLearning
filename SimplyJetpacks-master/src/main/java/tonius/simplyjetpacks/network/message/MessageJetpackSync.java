package tonius.simplyjetpacks.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.setup.ParticleType;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageJetpackSync implements IMessage, IMessageHandler<MessageJetpackSync, IMessage> {
    
    public int entityId;
    public int particleId;
    
    public MessageJetpackSync() {
    }
    
    public MessageJetpackSync(int entityId, int particleId) {
        this.entityId = entityId;
        this.particleId = particleId;
    }
    
    @Override
    public void fromBytes(ByteBuf buf) {
        this.entityId = buf.readInt();
        this.particleId = buf.readInt();
    }
    
    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.entityId);
        buf.writeInt(this.particleId);
    }
    
    @Override
    public IMessage onMessage(MessageJetpackSync msg, MessageContext ctx) {
        Entity entity = FMLClientHandler.instance().getClient().theWorld.getEntityByID(msg.entityId);
        if (entity != null && entity instanceof EntityLivingBase && entity != FMLClientHandler.instance().getClient().thePlayer) {
            if (msg.particleId >= 0) {
                ParticleType particle = ParticleType.values()[msg.particleId];
                SyncHandler.processJetpackUpdate(msg.entityId, particle);
            } else {
                SyncHandler.processJetpackUpdate(msg.entityId, null);
            }
        }
        return null;
    }
}
