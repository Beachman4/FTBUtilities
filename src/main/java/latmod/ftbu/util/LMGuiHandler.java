package latmod.ftbu.util;

import cpw.mods.fml.relauncher.*;
import ftb.lib.FTBLib;
import ftb.lib.mod.FTBLibMod;
import latmod.ftbu.mod.FTBU;
import latmod.ftbu.net.MessageOpenGui;
import latmod.lib.FastMap;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.FakePlayer;

public abstract class LMGuiHandler
{
	public final String ID;
	
	public LMGuiHandler(String s)
	{ ID = s; }
	
	@SideOnly(Side.CLIENT)
	public void registerClient()
	{ Registry.guiHandlers.put(ID, this); }
	
	public abstract Container getContainer(EntityPlayer ep, int id, NBTTagCompound data);
	
	@SideOnly(Side.CLIENT)
	public abstract GuiScreen getGui(EntityPlayer ep, int id, NBTTagCompound data);
	
	public void openGui(EntityPlayer ep, int id, NBTTagCompound data)
	{
		if(ep instanceof FakePlayer) return;
		else if(ep instanceof EntityPlayerMP)
		{
			Container c = getContainer(ep, id, data);
			if(c == null) return;
			
			EntityPlayerMP epM = (EntityPlayerMP)ep;
			epM.getNextWindowId();
			epM.closeContainer();
			epM.openContainer = c;
			epM.openContainer.windowId = epM.currentWindowId;
			epM.openContainer.addCraftingToCrafters(epM);
			new MessageOpenGui(ID, id, data, epM.currentWindowId).sendTo(epM);
		}
		else if(!FTBLib.isServer())
			FTBU.proxy.openClientGui((ep == null) ? FTBLibMod.proxy.getClientPlayer() : ep, ID, id, data);
	}
	
	@SideOnly(Side.CLIENT)
	public static class Registry
	{
		private static final FastMap<String, LMGuiHandler> guiHandlers = new FastMap<String, LMGuiHandler>();
		
		public static LMGuiHandler getLMGuiHandler(String id)
		{ return guiHandlers.get(id); }
	}
}