package com.ry.editor.template;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.Timer;

public class Tetris extends JFrame {
	public Tetris() {
		Tetrisblok t = new Tetrisblok();
		addKeyListener(t);
		add(t);
	}

	public static void main(String[] args) {
		Tetris frame = new Tetris();
		JMenuBar menu = new JMenuBar();
		frame.setJMenuBar(menu);
		JMenu game = new JMenu("游戏");
		JMenuItem newgame = game.add("新游戏");
		JMenuItem pause = game.add("暂停");
		JMenuItem goon = game.add("继续");
		JMenuItem exit = game.add("退出");
		JMenu help = new JMenu("帮助");
		JMenuItem about = help.add("关于");
		menu.add(game);
		menu.add(help);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(450, 600);
		frame.setTitle("Tetris内测版");
		// frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setResizable(false);

	}
}

// 创建一个俄罗斯方块类
class Tetrisblok extends JPanel implements KeyListener {

	// blockType 代表方块类型
	// turnState代表方块状态
	private int blockType;
	private int score = 0;

	private int turnState;

	private int x;

	private int y;

	private int i = 0;

	int j = 0;
	int flag = 0;
	// 定义已经放下的方块x=0-11,y=0-21;
	int[][] map = new int[13][23];

	// 方块的形状 第一组代表方块类型有S、Z、L、J、I、O、T 7种 第二组 代表旋转几次 第三四组为 方块矩阵
	private final int shapes[][][] = new int[][][] {
			// i
			{ { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 },
					{ 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 } },
			// s
			{ { 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } },
			// z
			{ { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 } },
			// j
			{ { 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
					{ 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
			// o
			{ { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
			// l
			{ { 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
			// t
			{ { 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 } } };

	// 生成新方块的方法
	public void newblock() {
		blockType = (int) (Math.random() * 1000) % 7;
		turnState = (int) (Math.random() * 1000) % 4;
		x = 4;
		y = 0;
		if (gameover(x, y) == 1) {

			newmap();
			drawwall();
			score = 0;
			JOptionPane.showMessageDialog(null, "GAME OVER");
		}
	}

	// 画围墙
	public void drawwall() {
		for (i = 0; i < 12; i++) {
			map[i][21] = 2;
		}
		for (j = 0; j < 22; j++) {
			map[11][j] = 2;
			map[0][j] = 2;
		}
	}

	// 初始化地图
	public void newmap() {
		for (i = 0; i < 12; i++) {
			for (j = 0; j < 22; j++) {
				map[i][j] = 0;
			}
		}
	}

	// 初始化构造方法
	Tetrisblok() {
		newblock();
		newmap();
		drawwall();
		//Timer timer = new Timer(1000, new TimerListener());
		//timer.start();
	}

	// 旋转的方法
	public void turn() {
		int tempturnState = turnState;
		turnState = (turnState + 1) % 4;
		if (blow(x, y, blockType, turnState) == 1) {
		}
		if (blow(x, y, blockType, turnState) == 0) {
			turnState = tempturnState;
		}
		repaint();
	}

	// 左移的方法
	public void left() {
		if (blow(x - 1, y, blockType, turnState) == 1) {
			x = x - 1;
		}
		;
		repaint();
	}

	// 右移的方法
	public void right() {
		if (blow(x + 1, y, blockType, turnState) == 1) {
			x = x + 1;
		}
		;
		repaint();
	}

	// 下落的方法
	public void down() {
		if (blow(x, y + 1, blockType, turnState) == 1) {
			y = y + 1;
			delline();
		}
		;
		if (blow(x, y + 1, blockType, turnState) == 0) {
			add(x, y, blockType, turnState);
			newblock();
			delline();
		}
		;
		repaint();
	}

	// 是否合法的方法
	public int blow(int x, int y, int blockType, int turnState) {
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				if (((shapes[blockType][turnState][a * 4 + b] == 1) && (map[x
						+ b + 1][y + a] == 1))
						|| ((shapes[blockType][turnState][a * 4 + b] == 1) && (map[x
						+ b + 1][y + a] == 2))) {

					return 0;
				}
			}
		}
		return 1;
	}

	// 消行的方法
	public void delline() {
		int c = 0;
		for (int b = 0; b < 22; b++) {
			for (int a = 0; a < 12; a++) {
				if (map[a][b] == 1) {

					c = c + 1;
					if (c == 10) {
						score += 10;
						for (int d = b; d > 0; d--) {
							for (int e = 0; e < 11; e++) {
								map[e][d] = map[e][d - 1];

							}
						}
					}
				}
			}
			c = 0;
		}
	}

	// 判断你挂的方法
	public int gameover(int x, int y) {
		if (blow(x, y, blockType, turnState) == 0) {
			return 1;
		}
		return 0;
	}

	// 把当前添加map
	public void add(int x, int y, int blockType, int turnState) {
		int j = 0;
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				if (map[x + b + 1][y + a] == 0) {
					map[x + b + 1][y + a] = shapes[blockType][turnState][j];
				}
				;
				j++;
			}
		}
	}

	// 画方块的的方法
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// 画当前方块
		for (j = 0; j < 16; j++) {
			if (shapes[blockType][turnState][j] == 1) {
				g.fillRect((j % 4 + x + 1) * 10, (j / 4 + y) * 10, 10, 10);
			}
		}
		// 画已经固定的方块
		for (j = 0; j < 22; j++) {
			for (i = 0; i < 12; i++) {
				if (map[i][j] == 1) {
					g.fillRect(i * 10, j * 10, 10, 10);

				}
				if (map[i][j] == 2) {
					g.drawRect(i * 10, j * 10, 10, 10);

				}
			}
		}
		g.drawString("score=" + score, 125, 10);
		g.drawString("抵制不良游戏，", 125, 50);
		g.drawString("拒绝盗版游戏。", 125, 70);
		g.drawString("注意自我保护，", 125, 90);
		g.drawString("谨防受骗上当。", 125, 110);
		g.drawString("适度游戏益脑，", 125, 130);
		g.drawString("沉迷游戏伤身。", 125, 150);
		g.drawString("合理安排时间，", 125, 170);
		g.drawString("享受健康生活。", 125, 190);
	}

	// 键盘监听
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_DOWN:
				down();
				break;
			case KeyEvent.VK_UP:
				turn();
				break;
			case KeyEvent.VK_RIGHT:
				right();
				break;
			case KeyEvent.VK_LEFT:
				left();
				break;
		}

	}

	// 无用
	public void keyReleased(KeyEvent e) {
	}

	// 无用
	public void keyTyped(KeyEvent e) {
	}

	// 定时器监听
	class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			repaint();
			if (blow(x, y + 1, blockType, turnState) == 1) {
				y = y + 1;
				delline();
			}
			;
			if (blow(x, y + 1, blockType, turnState) == 0) {

				if (flag == 1) {
					add(x, y, blockType, turnState);
					delline();
					newblock();
					flag = 0;
				}
				flag = 1;
			}
			;
		}
	}
}