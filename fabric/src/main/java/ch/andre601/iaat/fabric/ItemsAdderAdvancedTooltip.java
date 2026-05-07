package ch.andre601.iaat.fabric;

import ch.andre601.iaat.TooltipUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

@Environment(EnvType.CLIENT)
public final class ItemsAdderAdvancedTooltip implements ModInitializer{
    
    @Override
    public void onInitialize(){
        ItemTooltipCallback.EVENT.register(this::onTooltip);
    }
    
    private void onTooltip(ItemStack stack, Item.TooltipContext context, TooltipFlag type, List<Component> lines){
        String custom = TooltipUtil.getCustomId(stack);
        
        if(custom != null && type.isAdvanced()){
            for(int i = 0; i < lines.size(); i++){
                Component line = lines.get(i);
                if(!line.getString().equals(BuiltInRegistries.ITEM.getKey(stack.getItem()).toString()))
                    continue;
                
                lines.set(i, Component.literal(custom).withStyle(ChatFormatting.DARK_GRAY));
            }
        }
    }
}
