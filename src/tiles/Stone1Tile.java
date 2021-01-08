package tiles;

import gfx.ImgAssets;

public class Stone1Tile extends Tile {

	public Stone1Tile(int id) {
		super(ImgAssets.stone1, id);
		
	}
	@Override
	public boolean isSolid() {
		return true;
	}

}
