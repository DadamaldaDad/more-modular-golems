package net.dadamalda.more_modular_golems.compat.tconstruct.automation;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import dev.xkmc.l2library.serial.recipe.ConditionalRecipeWrapper;
import dev.xkmc.modulargolems.content.item.golem.GolemPart;
import net.dadamalda.more_modular_golems.More_modular_golems;
import net.dadamalda.more_modular_golems.datagen.MMGRecipeGen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.ForgeRegistries;
import slimeknights.mantle.recipe.helper.ItemOutput;
import slimeknights.mantle.registration.object.FlowingFluidObject;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;

public class TConstructRecipeGen {

    public static void genRecipe(RegistrateRecipeProvider pvd) {
        LogUtils.getLogger().info("genRecipe");
        findAndTry(pvd, "pig_iron", TinkerFluids.moltenPigIron, 90);
    }

    private static void findAndTry(RegistrateRecipeProvider pvd, String id, FlowingFluidObject<ForgeFlowingFluid> fluid, int amount) {
        LogUtils.getLogger().info(id);
        genCasting(pvd, ResourceLocation.fromNamespaceAndPath(TConstruct.MOD_ID, id), fluid, amount);
    }

    private static void genCasting(RegistrateRecipeProvider pvd, ResourceLocation id, FlowingFluidObject<ForgeFlowingFluid> fluid, int ingot) {
        for (var part : GolemPart.LIST) {
            var part_rl = ForgeRegistries.ITEMS.getKey(part);
            assert part_rl != null;
            String item_name = part_rl.getPath();
            var rl = new ResourceLocation(More_modular_golems.MODID, "casting/" + id.getPath() + "_casting_" + item_name);

            ItemStack result = GolemPart.setMaterial(part.getDefaultInstance(), id);
            MMGRecipeGen.unlock(pvd, ItemCastingRecipeBuilder.basinRecipe(ItemOutput.fromStack(result))::unlockedBy, part)
                    .setCast(part, true).setFluidAndTime(fluid, ingot * part.count)
                    .save(ConditionalRecipeWrapper.mod(pvd, TConstruct.MOD_ID, id.getNamespace()), rl);
        }
    }
}
