package tiles;

import gfx.ImgAssets;

public class Water1Tile extends Tile{
	public Water1Tile(int id) {
		super(ImgAssets.water1, id);
		
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}

