package tonius.simplyjetpacks.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import tonius.simplyjetpacks.client.particle.EntityColoredSmokeFX;
import tonius.simplyjetpacks.client.particle.EntityCustomBubbleFX;
import tonius.simplyjetpacks.client.particle.EntityCustomFlameFX;
import tonius.simplyjetpacks.client.particle.EntityCustomSmokeFX;
import tonius.simplyjetpacks.setup.ParticleType;

public abstract class ParticleUtils {
    
    private static final Minecraft mc = Minecraft.getMinecraft();
    
    public static void spawnParticle(ParticleType particle, World world, double posX, double posY, double posZ, double velX, double velY, double velZ) {
        switch (particle) {
        case NONE:
            return;
        case DEFAULT:
            mc.effectRenderer.addEffect(new EntityCustomFlameFX(world, posX, posY, posZ, velX, velY, velZ));
        case SMOKE:
            mc.effectRenderer.addEffect(new EntityCustomSmokeFX(world, posX, posY, posZ, velX, velY - 0.1D, velZ));
            return;
        case RAINBOW_SMOKE:
            mc.effectRenderer.addEffect(EntityColoredSmokeFX.getRainbowSmoke(world, posX, posY, posZ, velX, velY - 0.1D, velZ));
            mc.effectRenderer.addEffect(EntityColoredSmokeFX.getRainbowSmoke(world, posX, posY - 0.2D, posZ, velX, velY - 0.1D, velZ));
            return;
        case BUBBLE:
            mc.effectRenderer.addEffect(new EntityCustomBubbleFX(world, posX, posY, posZ, velX, velY, velZ));
        }
    }
}
