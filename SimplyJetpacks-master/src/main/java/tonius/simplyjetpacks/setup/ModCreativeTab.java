package tonius.simplyjetpacks.setup;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModCreativeTab extends CreativeTabs {
    
    public static final ModCreativeTab instance = new ModCreativeTab();
    
    private ModCreativeTab() {
        super("tabSimplyJetpacks");
        this.func_111229_a(ModEnchantments.enchantType);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return ModItems.components;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack() {
        return ModItems.jetpackIcon;
    }
    
}
