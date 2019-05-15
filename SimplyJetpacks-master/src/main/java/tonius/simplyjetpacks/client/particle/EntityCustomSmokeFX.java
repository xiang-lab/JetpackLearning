package tonius.simplyjetpacks.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class EntityCustomSmokeFX extends EntitySmokeFX {
    
    private static final Minecraft mc = Minecraft.getMinecraft();
    
    public EntityCustomSmokeFX(World world, double posX, double posY, double posZ, double velX, double velY, double velZ) {
        super(world, posX, posY, posZ, velX, velY, velZ);
    }
    
    @Override
    public int getBrightnessForRender(float p_70013_1_) {
        return 190 + (int) (20F * (1.0F - mc.gameSettings.gammaSetting));
    }
    
    @Override
    public void renderParticle(Tessellator p_70539_1_, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_) {
        if (this.particleAge > 0) {
            super.renderParticle(p_70539_1_, p_70539_2_, p_70539_3_, p_70539_4_, p_70539_5_, p_70539_6_, p_70539_7_);
        }
    }
    
}
