package ch.andre601.iaat.neoforge;

import ch.andre601.iaat.TooltipUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.List;

@Mod(value = "iaat", dist = Dist.CLIENT)
public final class ItemsAdderAdvancedTooltip{
    
    public ItemsAdderAdvancedTooltip(IEventBus eventBus){
        NeoForge.EVENT_BUS.addListener(ItemsAdderAdvancedTooltip::onTooltip);
    }
    
    private static void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        List<Component> lines = event.getToolTip();
        
        String custom = TooltipUtil.getCustomId(stack);
        
        if(custom != null && event.getFlags().isAdvanced()){
            for(int i = 0; i < lines.size(); i++){
                Component line = lines.get(i);
                // TODO: Find better way of detection (BuiltInRegistries.ITEM.getKey doesn't work..?)
                if(!line.getString().startsWith("minecraft:"))
                    continue;
                
                lines.set(i, Component.literal(custom).withStyle(ChatFormatting.DARK_GRAY));
            }
        }
    }
}
