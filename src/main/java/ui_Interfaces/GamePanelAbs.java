package ui_Interfaces;

import common.*;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Yanick
 */
public abstract class GamePanelAbs extends JPanel {

    public GamePanelAbs() {
        m_screenService = (ScreenServiceIfc) ServiceManager.getService(UiNames.Services.ScreenService);
        m_displayService = (DisplayServiceIfc) ServiceManager.getService(UiNames.Services.DisplayService);
        setPreferredSize(new Dimension(m_screenService.getStageWidth(), m_screenService.getStageHeight()));
        setBackground(Color.black);
        setDoubleBuffered(true);
        setFocusable(true);
    }

    @Override
    public final void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, m_alphaValue));
        onPaintComponent(g2);
        g2.dispose();
        m_displayService.notifyDrawingDone();
    }

    public void setAlpha(float alpha) {
        m_alphaValue = alpha;
    }

    protected void onPaintComponent(Graphics2D g2) {
        // Do nothing
    }

    private final ScreenServiceIfc m_screenService;
    private final DisplayServiceIfc m_displayService;
    private float m_alphaValue;
}
