//package level_Impl.Blocks;
//
//import level_Interfaces.*;
//
///**
// *
// * @author Yanick
// */
//public class OffBlock extends OnOffBlockAbs {
//
//    public OffBlock() {
//        super(BlockType.OffBlock);
//    }
//
//    @Override
//    public boolean isWalkable() {
//        return m_stageManagementService.IsOnState();
//    }
//
//    @Override
//    public boolean canBlockExplosions() {
//        return !m_stageManagementService.IsOnState();
//    }
//}