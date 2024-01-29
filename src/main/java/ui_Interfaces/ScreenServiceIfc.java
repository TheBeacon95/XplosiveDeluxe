package ui_Interfaces;

import common.ServiceIfc;

public interface ScreenServiceIfc extends ServiceIfc {
	
	/**
	 * Represents the width and the height of each tile before scaling.
	 * @return tile size in pixels.
	 */
	int getOriginalTileSize();
	
	/**
	 * The game is scaled to be visible on multiple resolutions. This represents the scale as a factor.
	 * @return the scale factor
	 */
	int getScale();
	
	/**
	 * Represents the width and the height of each tile after scaling.
	 * @return tile size in pixels
	 */
	int getTileSize();
	
	/**
	 * Shows how wide the game is.
	 * @return width of the game in pixels
	 */
	int getStageWidth();
	
	/**
	 * Shows how high the game is.
	 * @return height of the game in pixels
	 */
	int getStageHeight();
}
