package com.feed_the_beast.ftbu.client;

import com.feed_the_beast.ftbl.lib.client.ClientUtils;
import com.feed_the_beast.ftbl.lib.guide.GuidePage;
import com.feed_the_beast.ftbu.FTBUCommon;
import com.feed_the_beast.ftbu.gui.GuiEditNBT;
import com.feed_the_beast.ftbu.gui.GuiGuide;
import com.feed_the_beast.ftbu.gui.Guides;
import com.feed_the_beast.ftbu.integration.IJMIntegration;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class FTBUClient extends FTBUCommon // FTBLibModClient
{
	public static final String KEY_CATEGORY = "key.categories.ftbu";
	public static final KeyBinding KEY_GUIDE = new KeyBinding("key.ftbu.guide", KeyConflictContext.IN_GAME, KeyModifier.NONE, Keyboard.KEY_NONE, KEY_CATEGORY);
	public static final KeyBinding KEY_WARP = new KeyBinding("key.ftbu.warp", KeyConflictContext.UNIVERSAL, KeyModifier.NONE, Keyboard.KEY_NONE, KEY_CATEGORY);

	public static IJMIntegration JM_INTEGRATION = null;

	@Override
	public void preInit()
	{
		super.preInit();

		FTBUClientConfig.sync();
		ClientRegistry.registerKeyBinding(KEY_GUIDE);
		ClientRegistry.registerKeyBinding(KEY_WARP);
	}

	@Override
	public void postInit()
	{
		super.postInit();

		ClientUtils.MC.getRenderManager().getSkinMap().get("default").addLayer(LayerBadge.INSTANCE);
		ClientUtils.MC.getRenderManager().getSkinMap().get("slim").addLayer(LayerBadge.INSTANCE);
		//GuideRepoList.refresh();

		if (ClientUtils.MC.getResourceManager() instanceof SimpleReloadableResourceManager)
		{
			((SimpleReloadableResourceManager) ClientUtils.MC.getResourceManager()).registerReloadListener(Guides.INSTANCE);
		}
	}

	@Override
	public void onReloadedClient()
	{
		CachedClientData.clear();
	}

	@Override
	public void openNBTEditorGui(NBTTagCompound info, NBTTagCompound mainNbt)
	{
		new GuiEditNBT(info, mainNbt).openGui();
	}

	@Override
	public void displayGuide(GuidePage page)
	{
		new GuiGuide(page).openGui();
	}
}