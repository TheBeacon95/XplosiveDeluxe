package ui_Impl;

import ui_Interfaces.ScreenServiceIfc;
import ui_Interfaces.UiNames;

public class ScreenService implements ScreenServiceIfc {

    private final int ORIGINAL_TILE_SIZE = 32;
    private final int SCALE = 1; // TODO: In the future this will be changeable.
    private final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    // Todo: remove this.
    private final int STAGE_TILE_WIDTH = 19;
    private final int STAGE_TILE_HEIGHT = 15;
    private final int STAGE_WIDTH = STAGE_TILE_WIDTH * TILE_SIZE;
    private final int STAGE_HEIGHT = STAGE_TILE_HEIGHT * TILE_SIZE;

    @Override
    public String getId() {
        return UiNames.Services.ScreenService;
    }

    @Override
    public int getOriginalTileSize() {
        return ORIGINAL_TILE_SIZE;
    }

    @Override
    public int getScale() {
        return SCALE;
    }

    @Override
    public int getTileSize() {
        return TILE_SIZE;
    }

    @Override
    public int getStageWidth() {
        return STAGE_WIDTH;
    }

    @Override
    public int getStageHeight() {
        return STAGE_HEIGHT;
    }

    @Override
    public void initializeService() {
        // Nothing to initialize
    }

    ScreenService() {
        // Nothing to do here.
    }
}