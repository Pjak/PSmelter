package pjak.psmelter.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.SkillData;
import org.powerbot.game.api.util.Timer;

public class Paint extends Thread  {
	
    private static MouseTrail mouseTrail;
	private static ArrayList<String> log = new ArrayList<>();
	private boolean running = true;
    private static boolean showMouseTrail = true;
    private static final int delay = 20;
    private static int logSize = 3;
    private static final Color clrBorder = new Color(255, 0, 0, 90);
    private static final Color clrBackground = new Color(25, 25, 25);
    private static final Color clrTitle = new Color(255, 127, 39);
    private static final Color clrText = new Color(255, 127, 39, 200);
    private static final Color clrLogText = new Color(0, 136, 139);
    private static final Color clrLogBackground = new Color(25, 25, 25, 200);
    private static Timer runTime = new Timer(0);
    public static int currentLevel;
	public static int levelsGained;
	public static int experienceGained;
	public static int expHour;
	public static String Status;
	public static int expTNL;
	public static int nextLevel;
	public static String timeTL;
	public static int startLevel;
	public static int barsMade;
	public static SkillData SD = new SkillData();
	public static long startTime;

    public static Graphics getPaint(Graphics g) {
        return Edit((Graphics2D) g);
    }

    public void run() {
        mouseTrail = new MouseTrail();
        while(running) {
            mouseTrail.add(Mouse.getLocation());
            try {
                sleep(10 + delay);
            } catch (InterruptedException e) { e.printStackTrace(); running = false; }
        }
    }

    private static Graphics Edit(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        levelsGained = SD.level(Skills.SMITHING);
        experienceGained = SD.experience(Skills.SMITHING);
        currentLevel = Skills.getLevel(Skills.SMITHING);
        expHour = SD.experience(SkillData.Rate.HOUR, Skills.SMITHING);
        nextLevel = currentLevel + 1;
        expTNL = Skills.getExperienceToLevel(Skills.SMITHING, nextLevel);

            /**************
             * Background *
             **************/
            // Red transparent border
            g.setColor(clrBorder);
            g.fillRoundRect(1, 388, 518, 142, 5, 5);
            // Gray center
            g.setColor(clrBackground);
            g.fillRoundRect(4, 393, 510, 132, 5, 5);
            // White border
            g.setColor(Color.WHITE);
            g.drawRoundRect(4, 393, 510, 132, 5, 5);
            /**************
             * Foreground *
             **************/
            g.setFont(new Font("Segoe UI", Font.BOLD, 35));
			g.setColor(clrTitle);
            g.drawString("pSmelter", 180, 426);
            g.setFont(new Font("Segoe UI", Font.BOLD, 14));
			g.setColor(clrText);
            g.drawString("Bars made: " + barsMade, 10, 445);
            g.drawString("Bars P/HR: " + formatNum(getBarsforHour()), 10, 463);
            g.drawString("Level: " + currentLevel, 200, 445);
            g.drawString("Levels Gained: " + levelsGained, 200, 463);
            g.drawString("Exp Gained: " + experienceGained, 200, 481);
            g.drawString("Exp P/HR: " + formatNum(expHour), 200, 499);
            g.drawString("Exp TNL: " + formatNum(expTNL), 200, 517);
            g.drawString("Running: " + runTime.toElapsedString(), 375, 445);
            g.drawString("Status: " + Status, 375, 463);
  
        /**************
         *    Mouse   *
         **************/
        if(showMouseTrail && mouseTrail != null) {
            mouseTrail.draw(g);
        }
        /**************
         *   Logger   *
         **************/
            g.setColor(clrLogBackground);
            g.fillRect(0, 50, 519, 5 + logSize * 16);
            for(int i = 0; i < logSize; i++){
                int index = (log.size() -1) - i;
                if(index >= 0 ){
                    g.setColor(clrLogText);
                    g.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                    g.drawString(log.get(index), 10, 64 + (16 * (i)));
                }
            }
        return g;
    }

	public static void log(String s){
		log.add("[" + getComputerTime() + "] " + s);
	}


	public static String getComputerTime(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("H:mm:ss");
		return sdf.format(cal.getTime());
	}

    /**
     * @author Methamist
     */
    static class MouseTrail {
        private final int SIZE = 50;
        private final double ALPHA_STEP = (255.0 / SIZE);
        private final Point[] points;
        private int index;
        public MouseTrail() {
            points = new Point[SIZE];
            index = 0;
        }
        public void add(final Point p) {
            points[index++] = p;
            index %= SIZE;
        }

        public void draw(final Graphics g) {
            double alpha = 0;
            for (int i = index; i != (index == 0 ? SIZE - 1 : index - 1); i = (i + 1) % SIZE) {
                if (points[i] != null && points[(i + 1) % SIZE] != null) {
                    Color rainbow = Color.getHSBColor((float)(alpha / 255), 1, 1);
                    g.setColor(new Color(rainbow.getRed(), rainbow.getGreen(), rainbow.getBlue(), (int)alpha));

                    g.drawLine(points[i].x, points[i].y, points[(i + 1) % SIZE].x, points[(i + 1) % SIZE].y);

                    alpha += ALPHA_STEP;
                }
            }
        }
    }
    
    private static int getBarsforHour(){
		long runTime = System.currentTimeMillis() - startTime;
		return (int) ((barsMade *3600000D)/runTime);
	}
    
    private static String formatNum(int number) {
        return NumberFormat.getInstance().format(number);
    }
    
}