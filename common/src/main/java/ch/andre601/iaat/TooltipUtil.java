package ch.andre601.iaat;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

public class TooltipUtil{
    public static String getCustomId(ItemStack stack){
        CustomData data = stack.get(DataComponents.CUSTOM_DATA);
        if(data == null)
            return null;
        
        CompoundTag tag = data.copyTag();
        if(!tag.contains("itemsadder"))
            return null;
        
        CompoundTag itemsadder = tag.getCompoundOrEmpty("itemsadder");
        if(itemsadder.isEmpty())
            return null;
        
        String namespace = itemsadder.getStringOr("namespace", null);
        String id = itemsadder.getStringOr("id", null);
        if(namespace == null || id == null || namespace.isEmpty() || id.isEmpty())
            return null;
        
        return namespace + ":" + id;
    }
}
