package latmod.ftbu.api.item;

public interface IItemLM
{
	public String getItemID();

	public void onPostLoaded();

	public void loadRecipes();
}