package com.feed_the_beast.ftbutilities.client;

import com.feed_the_beast.ftblib.FTBLib;
import com.feed_the_beast.ftblib.lib.client.ClientUtils;
import com.feed_the_beast.ftbutilities.FTBUtilitiesCommon;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class FTBUtilitiesClient extends FTBUtilitiesCommon // FTBLibClient
{
	public static final KeyBinding KEY_WARP = new KeyBinding("key.ftbutilities.warp", KeyConflictContext.UNIVERSAL, KeyModifier.NONE, Keyboard.KEY_H, FTBLib.KEY_CATEGORY);

	@Override
	public void preInit()
	{
		super.preInit();

		FTBUtilitiesClientConfig.sync();
		ClientRegistry.registerKeyBinding(KEY_WARP);
	}

	@Override
	public void postInit()
	{
		super.postInit();

		ClientUtils.MC.getRenderManager().getSkinMap().get("default").addLayer(LayerBadge.INSTANCE);
		ClientUtils.MC.getRenderManager().getSkinMap().get("slim").addLayer(LayerBadge.INSTANCE);
	}
}