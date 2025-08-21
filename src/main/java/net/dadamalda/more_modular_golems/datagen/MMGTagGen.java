package net.dadamalda.more_modular_golems.datagen;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import dev.xkmc.modulargolems.init.ModularGolems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class MMGTagGen {
    private static final TagKey<Item> SPECIAL_CRAFTING_MATERIAL = createItemTag(ModularGolems.MODID, "special_crafting_material");

    public static void genItemTags(RegistrateItemTagsProvider pvd) {
        pvd.addTag(SPECIAL_CRAFTING_MATERIAL)
                .addOptionalTag(ResourceLocation.parse("forge:ingots/pig_iron"));
    }

    private static TagKey<Item> createItemTag(String namespace, String id) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(namespace, id));
    }
}
