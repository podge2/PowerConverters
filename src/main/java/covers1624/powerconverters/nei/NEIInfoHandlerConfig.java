package covers1624.powerconverters.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.GuiCraftingRecipe;
import codechicken.nei.recipe.ICraftingHandler;
import codechicken.nei.recipe.ShapedRecipeHandler;
import codechicken.nei.recipe.ShapedRecipeHandler.CachedShapedRecipe;
import codechicken.nei.recipe.ShapelessRecipeHandler;
import codechicken.nei.recipe.ShapelessRecipeHandler.CachedShapelessRecipe;
import covers1624.powerconverters.init.Recipes;
import covers1624.powerconverters.util.LogHelper;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

public class NEIInfoHandlerConfig implements IConfigureNEI {

	@Override
	public void loadConfig() {
		LogHelper.info("NEI Has called us to init.");
		// API.registerRecipeHandler(new InfoHandler());
		API.registerUsageHandler(new InfoHandler());

	}

	@Override
	public String getName() {
		return "PowerConverters: Nei Integration";
	}

	@Override
	public String getVersion() {
		return "1";
	}

	@Deprecated
	public static void addRecipesToNEI() {
		for (ICraftingHandler handler : GuiCraftingRecipe.craftinghandlers) {
			if (handler.getClass().getName().equals(ShapedRecipeHandler.class.getName())) {
				ShapedRecipeHandler shapedRecipeHandler = (ShapedRecipeHandler) handler;
				for (IRecipe recipe : Recipes.getCurrentRecipes()) {
					if (recipe instanceof ShapedRecipes) {
						CachedShapedRecipe shapedRecipe = shapedRecipeHandler.new CachedShapedRecipe((ShapedRecipes) recipe);
						shapedRecipe.computeVisuals();
						shapedRecipeHandler.arecipes.add(shapedRecipe);
					}
				}
			}
			if (handler.getClass().getName().equals(ShapelessRecipeHandler.class.getName())) {
				ShapelessRecipeHandler shapelessRecipeHandler = (ShapelessRecipeHandler) handler;
				for (IRecipe recipe : Recipes.getCurrentRecipes()) {
					if (recipe instanceof ShapelessRecipes) {
						ShapelessRecipes shapelessRecipe = (ShapelessRecipes) recipe;
						CachedShapelessRecipe cachedShapelessRecipe = shapelessRecipeHandler.new CachedShapelessRecipe(shapelessRecipe.recipeItems, shapelessRecipe.getRecipeOutput());
						// cachedShapelessRecipe.
						shapelessRecipeHandler.arecipes.add(cachedShapelessRecipe);
					}
				}
			}
		}
	}

}
