package ch.andre601.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemStack.class)
public abstract class ItemStackTooltipMixin{
	
    @Shadow
    public abstract DataComponentMap getComponents();
    
    @ModifyExpressionValue(
        method = "addDetailsToTooltip",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/core/DefaultedRegistry;getKey(Ljava/lang/Object;)Lnet/minecraft/resources/Identifier;")
    )
    public Identifier getTooltip(Identifier original){
        CustomData data = this.getComponents().get(DataComponents.CUSTOM_DATA);
        if(data == null)
            return original;
        
        CompoundTag tag = data.copyTag();
        if(!tag.contains("itemsadder"))
            return original;
        
        CompoundTag itemsadder = tag.getCompoundOrEmpty("itemsadder");
        if(itemsadder.isEmpty())
            return original;
        
        String namespace = itemsadder.getStringOr("namespace", "");
        String id = itemsadder.getStringOr("id", "");
        if(namespace.isBlank() || id.isBlank())
            return original;
        
        return Identifier.fromNamespaceAndPath(namespace, id);
    }
}