package tiles;


import gfx.ImgAssets;

public class WaterTile extends Tile{

	public WaterTile(int id) {
		super(ImgAssets.water, id);
		
	}
	
	public boolean isSolid() {
		return true;
	}

}
